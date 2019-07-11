package com.today.step.chat.chatutils.Coder;


import com.today.step.chat.chatutils.Entity.ChatMessage;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ChatMsgEncoder extends MessageToByteEncoder<ChatMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ChatMessage chatMessage, ByteBuf byteBuf) throws Exception {
        MessagePack msgpack=new MessagePack();
        byte[] msg=msgpack.write(chatMessage);
        byteBuf.writeBytes(msg);
    }
}
