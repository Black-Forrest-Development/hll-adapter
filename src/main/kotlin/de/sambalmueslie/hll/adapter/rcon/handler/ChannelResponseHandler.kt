package de.sambalmueslie.hll.adapter.rcon.handler


import de.sambalmueslie.hll.adapter.rcon.RconClient
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ChannelResponseHandler(private val client: RconClient) : SimpleChannelInboundHandler<String>() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ChannelResponseHandler::class.java)
    }

    private val buffer = StringBuilder()

    override fun channelRead0(ctx: ChannelHandlerContext, msg: String) {
        buffer.append(msg)
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        client.handleResponse(buffer.toString())
        buffer.clear()
        super.channelReadComplete(ctx)
    }



    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        client.handleError(cause)
    }

}
