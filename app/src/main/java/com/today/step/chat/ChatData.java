package com.today.step.chat;

public class ChatData {
    String msg_text;//发送的内容
    int msg_head;//头像
    String msg_type;//发送的类型
    String order_id;//订单id\



    public ChatData(String msg_text, int msg_head, String msg_type, String order_id) {
        this.msg_text = msg_text;
        this.msg_head = msg_head;
        this.msg_type = msg_type;
        this.order_id = order_id;
    }

    public ChatData(String msg_text, int msg_head, String msg_type) {
        this.msg_text = msg_text;
        this.msg_head = msg_head;
        this.msg_type = msg_type;
    }

    public String getMsg_text() {
        return msg_text;
    }

    public int getMsg_head() {
        return msg_head;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public String getOrder_id() {
        return order_id;
    }
}
