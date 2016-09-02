package com.github.andlaz.mesos.ffmpeg.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpResponseStatus.NOT_IMPLEMENTED;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class MesosFfmpegServiceHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
	
		sendError(ctx, NOT_IMPLEMENTED);
	
	}

	private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status,
				Unpooled.copiedBuffer("Failure: " + status + "\r\n", CharsetUtil.UTF_8));
		response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");

		// Close the connection as soon as the error message is sent.
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

}
