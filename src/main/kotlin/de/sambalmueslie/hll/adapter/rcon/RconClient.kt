package de.sambalmueslie.hll.adapter.rcon


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import de.sambalmueslie.hll.adapter.rcon.api.HllRconRequest
import de.sambalmueslie.hll.adapter.rcon.api.HllRconResponse
import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicBoolean


class RconClient(internal val config: RconClientConfig) : HllRconClient {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(RconClient::class.java)
    }

    private val workerGroup: EventLoopGroup = NioEventLoopGroup()
    internal val isConnected = AtomicBoolean(false)
    private val isLoggedIn = AtomicBoolean(false)
    internal var key: ByteArray = ByteArray(4)
    private var channel: Channel? = null
    private var connectionFuture = CompletableFuture<Boolean>()
    private var commandFuture = CompletableFuture<String>()

    private val bootstrap = Bootstrap().group(workerGroup)
        .channel(NioSocketChannel::class.java)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(RconClientInitializer(this))

    init {
        connect()
    }

    private fun connect() {
        logger.info("Start connection to host ${config.host}")
        connectionFuture = CompletableFuture<Boolean>()
        channel = bootstrap.connect(config.host, config.port).sync().channel()
        connectionFuture.get()
    }

    override fun disconnect() {
        if (channel == null) return
        channel!!.disconnect().sync()
        channel = null
    }

    override fun send(request: HllRconRequest): HllRconResponse {
        logger.warn("START SEND '${request.content}'")
        if (!isLoggedIn()) return HllRconResponse(false, "", "Client is not logged in")
        commandFuture = CompletableFuture<String>()
        logger.info("[${config.host}] - Request '${request.content}'")
        logger.trace("Send command: [${request.content}]")
        channel?.writeAndFlush(request.content)?.sync()
        val result = commandFuture.get()
        logger.warn("FINISH SEND '${request.content}'")
        return HllRconResponse(true, result, "")
    }


    fun handleResponse(msg: String) {
        if (!isLoggedIn()) {
            handleLogin(msg)
        } else {
            logger.trace("Got a message: [$msg]")
            commandFuture.complete(msg)
        }
    }

    fun handleLogin(msg: String) {
        if (msg.isBlank()) {
            val command = "login ${config.password}"
            channel?.writeAndFlush(command)?.sync()
        } else {
            val success = msg == "SUCCESS"
            logger.debug("Login done: [$success]")
            isLoggedIn.set(success)
            this.connectionFuture.complete(success)
        }
    }

    fun isLoggedIn() = isLoggedIn.get()

    fun handleError(cause: Throwable) {
        logger.error("Connection failed", cause)
        commandFuture.complete("")
        connectionFuture.complete(false)
        connect()
    }


}
