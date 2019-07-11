package com.today.step.chat.chatutils.NettlyClient;


import com.today.step.chat.chatutils.Entity.ChatMessage;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {

    private String host;
    private int port;
    private ChannelFuture channelFuture;
    public ChatClient(String host,int port){
        this.host=host;
        this.port=port;
    }

    public void start() throws IOException {
        EventLoopGroup workGroup=new NioEventLoopGroup();
        try {
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());
            channelFuture=bootstrap.connect(host,port).sync();
            System.out.println("已连接到服务器！");

            channelFuture.channel().closeFuture().sync();
            System.out.println("已从服务器断开！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workGroup.shutdownGracefully();
        }

    }
    public void sendMsg(ChatMessage cmsg){
        System.out.println(cmsg.toString());
        channelFuture.channel().writeAndFlush(cmsg);
    }

}
