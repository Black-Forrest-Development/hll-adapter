package de.sambalmueslie.hll.adapter.rcon


import de.sambalmueslie.hll.adapter.rcon.handler.*
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal class RconClientInitializer(private val client: RconClient) : ChannelInitializer<SocketChannel>() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RconClientInitializer::class.java)
    }

    override fun initChannel(ch: SocketChannel) {
        logger.info("Init channel")
        ch.pipeline().addLast("logging", LoggingHandler(LogLevel.DEBUG))
        ch.pipeline().addLast("connection", ChannelConnectionHandler(client))
        ch.pipeline().addLast("decoder", ChannelMessageDecoder(client))
        ch.pipeline().addLast("encoder", ChannelMessageEncoder(client))
        ch.pipeline().addLast("response", ChannelResponseHandler(client))
//        ch.pipeline().addLast("login", ChannelLoginHandler(client))
    }


}
