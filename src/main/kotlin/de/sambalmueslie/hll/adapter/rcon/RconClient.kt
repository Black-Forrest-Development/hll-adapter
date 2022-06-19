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


class RconClient() : HllRconClient {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(RconClient::class.java)
    }

    private val workerGroup: EventLoopGroup = NioEventLoopGroup()
    internal val isConnected = AtomicBoolean(false)
    private val isLoggedIn = AtomicBoolean(false)
    internal var key: ByteArray = ByteArray(4)
    private var channel: Channel? = null
    internal var config: RconClientConfig = RconClientConfig("127.0.0.1", 28316, "")

    private var connectionFuture = CompletableFuture<Boolean>()
    private var commandFuture = CompletableFuture<String>()

    private val bootstrap = Bootstrap().group(workerGroup)
        .channel(NioSocketChannel::class.java)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(RconClientInitializer(this))

    override fun connect(config: RconClientConfig) {
        if (channel != null) disconnect()
        this.config = config
        connect()
    }

    private fun connect() {
        connectionFuture = CompletableFuture<Boolean>()
        channel = bootstrap.connect(config.host, config.port).sync().channel()
        connectionFuture.get()
    }

    override fun disconnect() {
        if (channel == null) return
        channel!!.disconnect().sync()
        channel = null
    }

    override fun sendCommand(command: String): String {
        if (!isLoggedIn()) return ""
        commandFuture = CompletableFuture<String>()
        logger.trace("Send command: [$command]")
        channel?.writeAndFlush(command)?.sync()
        return commandFuture.get()
    }

    override fun send(request: HllRconRequest): HllRconResponse {
        if (!isLoggedIn()) return HllRconResponse(false, "", "Client is not logged in")
        commandFuture = CompletableFuture<String>()
        logger.trace("Send command: [${request.content}]")
        channel?.writeAndFlush(request.content)?.sync()
        val result = commandFuture.get()
        return HllRconResponse(true, result, "")
    }


    fun handleResponse(msg: String) {
        logger.trace("Got a message: [$msg]")
        commandFuture.complete(msg)
    }

    fun handleLogin(success: Boolean) {
        logger.debug("Login done: [$success]")
        isLoggedIn.set(success)
        this.connectionFuture.complete(success)
    }

    fun isLoggedIn() = isLoggedIn.get()

    fun handleError(cause: Throwable) {
        logger.error("Connection failed", cause)
        commandFuture.complete("")
        connectionFuture.complete(false)
    }


}
