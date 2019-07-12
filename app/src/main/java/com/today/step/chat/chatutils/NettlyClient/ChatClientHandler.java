package com.today.step.chat.chatutils.NettlyClient;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.today.step.chat.ChatActivity;
import com.today.step.chat.chatutils.Entity.ChatMessage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import static com.today.step.chat.chatutils.ChatTestActivity.SHOW_MSG;


public class ChatClientHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChatMessage msg=new ChatMessage(ChatActivity.username, "服务器","认证消息！" ,1);
        System.out.println(msg);
        ctx.channel().writeAndFlush(msg);
    }



    //接收的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String strmsg="";
        ChatMessage cmsg=(ChatMessage)msg;
        if(cmsg.getReceiveUser().equals("")){//发给所有人
//            strmsg="[全体-"+cmsg.getSendUser()+"：]"+cmsg.getMessage();
            strmsg = cmsg.getMessage()+","+cmsg.getMessagetype();

        }else{//发给指定用户
//            strmsg="[私聊-"+cmsg.getSendUser()+"：]"+cmsg.getMessage();
            strmsg = cmsg.getMessage()+","+cmsg.getMessagetype();
        }
        Log.d("--strmsg = ","`"+strmsg);

        Message message=new Message();
        message.what=SHOW_MSG;
        Bundle bundle=new Bundle();
        bundle.putString("msg",strmsg);//包含传递信息(聊天消息内容)
        message.setData(bundle);
        // TODO
        ChatActivity.getChatActivity().getMsghandler().sendMessage(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel newchannel=ctx.channel();
        System.out.println("["+newchannel.remoteAddress()+"]：通讯异常");
        System.out.println(cause.getMessage());
        newchannel.close();
    }
}
