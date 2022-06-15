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
import java.util.concurrent.atomic.AtomicBoolean


class RconClient() : HllRconClient {

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

    override fun connect(config: RconClientConfig) {
        connectionFuture = CompletableFuture<Boolean>()
        val bootstrap = Bootstrap().group(workerGroup)
            .channel(NioSocketChannel::class.java)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(RconClientInitializer(this, config))
        channel = bootstrap.connect(config.host, config.port).sync().channel()
        connectionFuture.get()
    }

    override fun disconnect() {
        channel.disconnect().sync()
    }

    override fun sendCommand(command: String): String {
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
    }

    override fun getBoolean(command: String): Boolean {
        return sendCommand(command) == "on"
    }

    override fun setBoolean(command: String, value: Boolean): String {
        return sendCommand("$command ${if (value) "on" else "off"}")
    }

    override fun getInt(command: String): Int {
        return sendCommand(command).toIntOrNull() ?: -1
    }

    override fun getList(command: String): List<String> {
        return getArray(command)
    }

    override fun getSet(command: String): Set<String> {
        return getArray(command).toSet()
    }

    private fun getArray(command: String): List<String> {
        val result = sendCommand(command)
        val fields = result.split("\t")
        if (fields.isEmpty()) return emptyList()
        val length = fields.first().toIntOrNull() ?: return emptyList()
        return fields.slice(1..length)
    }

    override fun setInt(command: String, value: Int): String {
        return sendCommand("$command $value")
    }

    override fun setList(command: String, words: List<String>): String {
        return sendCommand("$command ${words.joinToString(",")}")
    }


}
