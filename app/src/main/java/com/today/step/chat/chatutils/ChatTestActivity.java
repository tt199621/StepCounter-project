package com.today.step.chat.chatutils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.today.step.chat.chatutils.Entity.ChatMessage;
import com.today.step.chat.chatutils.NettlyClient.ChatClient;
import com.today.step.R;

import java.io.IOException;

public class ChatTestActivity extends AppCompatActivity implements View.OnClickListener{

    private static String url = "192.168.0.106";

    public static String username;
    public static final int SHOW_MSG=1;
    private Button connectbtn;
    private Button sendbtn;
    private TextView messageview;
    private EditText editText;
    private EditText editsender;
    private EditText editreceiver;
    private ChatClient client;
    private static ChatTestActivity chatTestActivity;
    public ChatTestActivity(){
        chatTestActivity=this;
    }

    public static ChatTestActivity getChatTestActivity() {
        return chatTestActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_test);


        connectbtn=(Button)findViewById(R.id.connectbtn);//连接 按钮
        sendbtn=(Button)findViewById(R.id.sendbtn);//发送消息按钮
        messageview=(TextView)findViewById(R.id.messageview);//消息展示view
        editText=(EditText)findViewById(R.id.editmessage);//
        editsender=(EditText)findViewById(R.id.editsender);
        editreceiver=(EditText)findViewById(R.id.editreceiver);
        connectbtn.setOnClickListener(this);
        sendbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.connectbtn){

            System.out.println("连接服务器！");
            username=editsender.getText().toString();
            new Thread(){
                @Override
                public void run() {
                    connect();
                }
            }.start();

        }
        if(v.getId()==R.id.sendbtn){
            System.out.println("发送消息："+editText.getText().toString());//
            ChatMessage msg=new ChatMessage(editsender.getText().toString(),editreceiver.getText().toString(),editText.getText().toString() ,2);
            send(msg);
        }
    }
    public void connect(){
        try {
            client=new ChatClient(url,8888);
            client.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(ChatMessage cmsg){
        client.sendMsg(cmsg);
    }

    private Handler msghandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==SHOW_MSG){
                //设置显示内容
                messageview.setText(messageview.getText().toString()+msg.getData().getString("msg")+"\n");
                Log.d("---message text",""+messageview.getText().toString());
            }
        }
    };

    public Handler getMsghandler() {
        return msghandler;
    }
}