package de.sambalmueslie.hll.adapter.rcon


import de.sambalmueslie.hll.adapter.rcon.handler.HllProtocolHandler
import de.sambalmueslie.hll.adapter.rcon.handler.LoginHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import io.netty.util.AttributeKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal class RconClientInitializer(private val client: RconClient) : ChannelInitializer<SocketChannel>() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RconClientInitializer::class.java)
    }

    override fun initChannel(ch: SocketChannel) {
        logger.info("Init channel")
        val attributeKey = AttributeKey.newInstance<ByteArray>("key")
        ch.pipeline().addLast("logging", LoggingHandler(LogLevel.DEBUG))
        ch.pipeline().addLast("protocol", HllProtocolHandler())
        ch.pipeline().addLast("frameDecoder", LineBasedFrameDecoder(80))
        ch.pipeline().addLast("decode", StringDecoder(Charsets.UTF_8))
        ch.pipeline().addLast("encode", StringEncoder(Charsets.UTF_8))
        ch.pipeline().addLast("login", LoginHandler(client))

//        ch.pipeline().addLast("connection", ChannelConnectionHandler(client, attributeKey))
//        ch.pipeline().addLast("decoder", ChannelMessageDecoder(client, attributeKey))
//        ch.pipeline().addLast("encoder", ChannelMessageEncoder(client, attributeKey))
//        ch.pipeline().addLast("response", ChannelResponseHandler(client))
//        ch.pipeline().addLast("login", ChannelLoginHandler(client))
    }


}
