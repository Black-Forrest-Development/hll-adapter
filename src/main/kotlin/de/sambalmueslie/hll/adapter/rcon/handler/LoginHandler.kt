package de.sambalmueslie.hll.adapter.rcon.handler


import de.sambalmueslie.hll.adapter.rcon.RconClient
import io.netty.channel.ChannelHandlerContext
import io.netty.util.AttributeKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoginHandler(private val client: RconClient) : io.netty.channel.ChannelInboundHandlerAdapter() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(LoginHandler::class.java)
    }

    private val loginAttribute = AttributeKey.valueOf<Boolean>("login")

    private val receivedLines = mutableListOf<String>()
    private var loginInProcess: Boolean = false

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        if (msg is String) {
            receivedLines.add(msg)
        }
        super.channelRead(ctx, msg)
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        val login = ctx.channel().attr(loginAttribute)
        if (!login.get()) {
            if (receivedLines.isEmpty() && !loginInProcess) {
                val command = "login ${client.config.password}"
                ctx.writeAndFlush(command).sync()
                loginInProcess = true
            } else if (receivedLines.firstOrNull() == "SUCCESS") {
                login.set(true)
                loginInProcess = false
            }
        } else {
            super.channelReadComplete(ctx)
        }
    }

    override fun channelRegistered(ctx: ChannelHandlerContext) {
        reset(ctx)
        super.channelRegistered(ctx)
    }

    override fun channelUnregistered(ctx: ChannelHandlerContext) {
        reset(ctx)
        super.channelUnregistered(ctx)
    }

    private fun reset(ctx: ChannelHandlerContext) {
        ctx.channel().attr(loginAttribute).set(false)
        loginInProcess = false
    }
}
