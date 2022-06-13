package de.sambalmueslie.hll.adapter.rcon


import de.sambalmueslie.hll.adapter.rcon.api.HllRconClient
import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicBoolean


class RconClient(val config: RconClientConfig) : HllRconClient {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(RconClient::class.java)
    }

    private val workerGroup: EventLoopGroup = NioEventLoopGroup()
    internal val isConnected = AtomicBoolean(false)
    private val isLoggedIn = AtomicBoolean(false)
    internal var key: ByteArray = ByteArray(4)
    private lateinit var channel: Channel

    private var connectionFuture = CompletableFuture<Boolean>()
    private var commandFuture = CompletableFuture<String>()
    private val bootstrap = Bootstrap().group(workerGroup)
        .channel(NioSocketChannel::class.java)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(RconClientInitializer(this))

   override fun connect() {
        connectionFuture = CompletableFuture<Boolean>()
        channel = bootstrap.connect(config.host, config.port).sync().channel()
        connectionFuture.get()
    }

    override   fun disconnect() {
        channel.disconnect().sync()
    }

    override  fun sendCommand(command: String): String {
        if (!isLoggedIn()) return ""
        commandFuture = CompletableFuture<String>()
        logger.trace("Send command: [$command]")
        channel.writeAndFlush(command).sync()
        return commandFuture.get()
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

        connect()
    }


}
