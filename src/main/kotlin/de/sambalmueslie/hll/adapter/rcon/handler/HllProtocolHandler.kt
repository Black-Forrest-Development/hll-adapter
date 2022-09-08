package de.sambalmueslie.hll.adapter.rcon.handler


import io.netty.buffer.ByteBuf
import io.netty.channel.Channel
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import io.netty.util.AttributeKey
import io.netty.util.ByteProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.experimental.xor


class HllProtocolHandler : ChannelDuplexHandler() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(HllProtocolHandler::class.java)
    }

    private val keyAttribute = AttributeKey.valueOf<ByteArray>("key")


    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val m = msg as ByteBuf
        val channel = ctx.channel()

        val key = channel.attr(keyAttribute).get()
        if (key == null) {
            setupConnectionKey(m, channel)
        } else {
            val message = parseResponse(m, key)
            super.channelRead(ctx, message)
        }
    }

    override fun write(ctx: ChannelHandlerContext, msg: Any?, promise: ChannelPromise) {
        val m = msg as ByteBuf

        val channel = ctx.channel()
        val key = channel.attr(keyAttribute).get()

        if (key != null) {
            super.write(ctx, xor(msg, key), promise)
        }
    }

    private fun parseResponse(msg: ByteBuf, key: ByteArray): String {
        val data = ByteArray(msg.readableBytes())
        msg.readBytes(data)
        return data.mapIndexed { index, byte -> byte.xor(key[index % key.size]) }.toByteArray().toString(Charsets.UTF_8)
    }

    private fun xor(msg: ByteBuf, key: ByteArray): ByteBuf {

        val readerIndex = msg.readerIndex()
        msg.forEachByte(object : ByteProcessor {
            private var i = 0

            @Throws(Exception::class)
            override fun process(value: Byte): Boolean {
                val result = value.xor(key[i % key.size])
                msg.setByte(readerIndex + i, result.toInt())
                i++
                return true
            }
        })

        return msg
    }

    private fun setupConnectionKey(m: ByteBuf, channel: Channel) {
        if (m.readableBytes() != 4) return
        val key = ByteArray(4)
        m.readBytes(key)
        channel.attr(keyAttribute).set(key)
        logger.debug("Found key '${key.joinToString(separator = " ") { "%02x".format(it) }}'")
    }

    override fun channelRegistered(ctx: ChannelHandlerContext) {
        ctx.channel().attr(keyAttribute).set(null)
        super.channelRegistered(ctx)
    }


}
