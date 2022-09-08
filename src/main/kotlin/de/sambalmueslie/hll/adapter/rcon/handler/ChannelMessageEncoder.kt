package de.sambalmueslie.hll.adapter.rcon.handler

import de.sambalmueslie.hll.adapter.rcon.RconClient
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder
import io.netty.util.AttributeKey
import kotlin.experimental.xor

class ChannelMessageEncoder(private val client: RconClient, private val attributeKey: AttributeKey<ByteArray>) : MessageToMessageEncoder<String>() {


    override fun encode(ctx: ChannelHandlerContext, msg: String, out: MutableList<Any>) {
        if (msg.isEmpty()) return

        val channel = ctx.channel()
        val key = channel.attr(attributeKey)?.get()
        if (key != null) {
            out.add(xor(msg, key))
        }
    }

    private fun xor(command: String, key: ByteArray): ByteBuf {
        val raw = command.toByteArray(Charsets.UTF_8)
        val buf = Unpooled.buffer(raw.size)
        raw.mapIndexed { index, byte -> byte.xor(key[index % key.size]) }.forEach { buf.writeByte(it.toInt()) }
        return buf
    }

}
