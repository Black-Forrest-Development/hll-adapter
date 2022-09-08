package de.sambalmueslie.hll.adapter.rcon.handler


import de.sambalmueslie.hll.adapter.rcon.RconClient
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.AttributeKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory
// ChannelDuplexHandler
class ChannelConnectionHandler(private val client: RconClient, private val attributeKey: AttributeKey<ByteArray>) : ChannelInboundHandlerAdapter() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ChannelConnectionHandler::class.java)
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val m = msg as ByteBuf
        val channel = ctx.channel()

        if (m.readableBytes() == 4 && !channel.hasAttr(attributeKey)) {
            val key = ByteArray(4)
            m.readBytes(key)
            channel.attr(attributeKey).set(key)
            logger.debug("Found key '${key.joinToString(separator = " ") { "%02x".format(it) }}'")
        } else {
            super.channelRead(ctx, msg)
        }
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        super.channelReadComplete(ctx)
    }

}
