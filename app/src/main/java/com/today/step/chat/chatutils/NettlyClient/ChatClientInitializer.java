package com.today.step.chat.chatutils.NettlyClient;


import com.today.step.chat.chatutils.Coder.ChatMsgDecoder;
import com.today.step.chat.chatutils.Coder.ChatMsgEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline=channel.pipeline();//建立管道

        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65536, 0, 2, 0, 2));
        pipeline.addLast("msgpack decoder",new ChatMsgDecoder());
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(2));
        pipeline.addLast("msgpack encoder",new ChatMsgEncoder());

        pipeline.addLast("handler",new ChatClientHandler());

    }
}
