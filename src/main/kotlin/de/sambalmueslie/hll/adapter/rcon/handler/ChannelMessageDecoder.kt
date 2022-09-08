package de.sambalmueslie.hll.adapter.rcon.handler


import de.sambalmueslie.hll.adapter.rcon.RconClient
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import io.netty.util.AttributeKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.experimental.xor

class ChannelMessageDecoder(private val client: RconClient, private val attributeKey: AttributeKey<ByteArray>) : MessageToMessageDecoder<ByteBuf>() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ChannelMessageDecoder::class.java)
    }


    override fun decode(ctx: ChannelHandlerContext, msg: ByteBuf, out: MutableList<Any>) {
        val channel = ctx.channel()
        val key = channel.attr(attributeKey)?.get()
        if (key != null) {
            out.add(parseResponse(msg, key))
        }
    }

    private fun parseResponse(msg: ByteBuf, key: ByteArray): String {
        val data = ByteArray(msg.readableBytes())
        msg.readBytes(data)
        return data.mapIndexed { index, byte -> byte.xor(key[index % key.size]) }.toByteArray().toString(Charsets.UTF_8)
    }

}
