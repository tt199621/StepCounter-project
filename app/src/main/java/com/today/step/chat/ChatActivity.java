package com.today.step.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.today.step.MyActivity;
import com.today.step.chat.chatutils.ChatMessageUtils;
import com.today.step.chat.chatutils.Entity.ChatMessage;
import com.today.step.chat.chatutils.NettlyClient.ChatClient;
import com.today.step.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.netty.channel.ChannelFuture;


/**
 * 聊天界面
 * */
public class ChatActivity extends MyActivity implements View.OnClickListener {


    private static String url = "47.112.205.148";//


    private List<ChatMessageUtils> chatList = new ArrayList<>();

    public static String username;
    private String receive_id;
    public static final int SHOW_MSG=1;
    private Button connectbtn;
    private Button sendbtn;
    private TextView messageview;
    private EditText editText;
    private EditText editsender;
    private EditText editreceiver;
    private ChatClient client;
    private String receive_text = "";
    private static ChatActivity chatactivit;
    public ChatActivity(){
        chatactivit=this;
    }

    public static ChatActivity getChatActivity() {
        return chatactivit;
    }

    //关闭
	private ChannelFuture channelFuture;

    private String order_id,order_text;
    Button obj;//模拟对方发送消息给自己
    Button mys;//模拟自己发送消息给对方
    EditText sendmsg;//输入的内容(发送的消息内容)

    private List<ChatData> messageList = new ArrayList<>();//聊天显示内容

    private RecyclerView recyclerView;
    private ChatAdapter adapter;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                //1   对方发送给自己的消息
                case 1://接收消息
                    Log.d("-----00","00");
                    ChatData objdata = new ChatData(receive_text,R.drawable.head_image,""+1);
                    messageList.add(objdata);
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(messageList.size());
//                    sendmsg.setText("");
                    break;
                //2   自己发送给对方的消息
                case 2://发送消息
                    Log.d("-----11","11");
                    ChatData mydata = new ChatData(sendmsg.getText().toString(),R.drawable.head_image,""+2);
                    messageList.add(mydata);
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(messageList.size());
                    sendmsg.setText("");
                    break;

                case 3://接收订单消息
                    Log.d("---id",""+order_id);
                    ChatData dealdata = new ChatData(receive_text,R.drawable.head_image,""+3,""+order_id);
                    messageList.add(dealdata);
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(messageList.size());
                    //sendmsg.setText("");
                    break;
                case 4://发送订单消息
                    Log.d("---id",""+order_id);
                    String str = sendmsg.getText().toString();
                    String [] sList = str.split("oiregn");
                    ChatData mdealdata = new ChatData(""+order_text,R.drawable.head_image,""+4,""+order_id);
                    messageList.add(mdealdata);
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(messageList.size());
                    sendmsg.setText("");
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /*******获取对应 id 的 聊天数据*******/
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        receive_id = sp.getString("team_id","");
        username=sp.getString("userid","");

        connectService();

        /**********聊天记录导入**********/
		Set<String> stringSet = sp.getStringSet(receive_id+username, Collections.singleton(""));
		if (stringSet.size() > 0){
			//循环遍历set集合
			for (String str : stringSet){
			    //截取字符串以逗号隔开
				String [] strings = str.split(",");
				//set集合默认size就为1
				if (stringSet.size() == 1){
                    if (str.equals("") || str.equals(null)){

                    }else {
                        Log.d("---String[0]:",""+strings[0]);
                        Log.d("---String[1]:",""+strings[1]);
                        Log.d("---String[2]:",""+strings[2]);//时间
						Log.d("---Stringsize:",""+strings.length);//时间
                        if (strings[3].equals("1")){
                            ChatMessageUtils messageUtils = new ChatMessageUtils(""+strings[0],""+strings[1],""+strings[2],"");
                            chatList.add(messageUtils);
						}else {
                            ChatMessageUtils messageUtils = new ChatMessageUtils(""+strings[0],""+strings[1],""+strings[2],""+strings[3]);
                            chatList.add(messageUtils);
                        }

                    }
                }else if (stringSet.size() > 1){
                    Log.d("---String[0]:",""+strings[0]);
                    Log.d("---String[1]:",""+strings[1]);
                    Log.d("---String[2]:",""+strings[2]);
//                    ChatMessageUtils messageUtils = new ChatMessageUtils(""+strings[0],""+strings[1],""+strings[2]);
//                    chatList.add(messageUtils);
                    if (strings[3].equals("1")){
                        ChatMessageUtils messageUtils = new ChatMessageUtils(""+strings[0],""+strings[1],""+strings[2],"");
                        chatList.add(messageUtils);
                    }else {
                        ChatMessageUtils messageUtils = new ChatMessageUtils(""+strings[0],""+strings[1],""+strings[2],""+strings[3]);
                        chatList.add(messageUtils);
                    }

                }
			}/************遍历结束***/

			/**
             * 按照时间进行消息排序(Set集合)
             * */
            for (int i = 0; i < chatList.size()-1; i++) {
                for (int j = 0; j < chatList.size()-1-i; j++) {
                    if (Long.parseLong(chatList.get(j).getTime()) > Long.parseLong(chatList.get(j+1).getTime()) ){
//                        List<> list = new ArrayList<>();
                        ChatMessageUtils big = chatList.get(j);
                        ChatMessageUtils small = chatList.get(j+1);
                        chatList.set(j,small);
                        chatList.set(j+1,big);
                    }
                }
            }
            for (int i = 0; i < chatList.size(); i++) {
                if (chatList.get(i).getType().equals("")){
                    ChatData a = new ChatData(""+chatList.get(i).getMessage(),R.drawable.head_image,""+chatList.get(i).getId());
                    messageList.add(a);
                }else {
                    ChatData a = new ChatData(""+chatList.get(i).getMessage(),R.drawable.head_image,""+chatList.get(i).getId(),chatList.get(i).getType());
                    messageList.add(a);
                }

            }
		}

        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);

        title.setText(""+sp.getString("team_name",""));
        //标题右侧按钮
        TextView title1 = (TextView)findViewById(R.id.title_btn);
        title1.setText("发起交易");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatActivity.this, StartDealActivity.class));

            }
        });
        //标题返回按钮
        Button button = (Button)findViewById(R.id.title_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				System.out.println("发送消息："+sendmsg.getText().toString());//
				ChatMessage msg=new ChatMessage(""+username,""+receive_id,"我下线了",2);
				//ChatMessage msg=new ChatMessage(""+username,""+receive_id,""+ sendmsg.getText().toString(),2);
				send(msg);
				finish();
                //Toast.makeText(IdentityInfoActivity.this,"onclick button",Toast.LENGTH_SHORT).show();
            }
        });


        sendmsg = (EditText)findViewById(R.id.chat_editview) ;

        obj = (Button)findViewById(R.id.chat_send_message);
//        mys = (Button)findViewById(R.id.myself_btn);
        obj.setOnClickListener(this);
        //mys.setOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.chat_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChatActivity.this);//默认垂直布局
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ChatAdapter(this,messageList);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(messageList.size());


        EventBus.getDefault().register(this);  //事件的注册
    }

    @Override
    protected void onResume() {
        super.onResume();
//        username=editsender.getText().toString();
    }

    private void connectService(){
		System.out.println("连接服务器！");
		new Thread(){
			@Override
			public void run() {
				connect();
			}
		}.start();

	}


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //发送消息
            case R.id.chat_send_message:
				// -Demarcation-
                System.out.println("发送消息："+sendmsg.getText().toString());//
                ChatMessage msg=new ChatMessage(""+username,""+receive_id,""+ sendmsg.getText().toString(),2);
                //ChatMessage msg=new ChatMessage(""+username,""+receive_id,""+ sendmsg.getText().toString(),2);
                send(msg);

                handler.sendEmptyMessage(2);
                break;


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

    //接收消息展示
    private Handler msghandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==SHOW_MSG){
                //设置显示内容
                //消息内容为  msg.getData().getString("msg")
                //messageview.setText(messageview.getText().toString()+msg.getData().getString("msg")+"\n");
                //Log.d("---message text",""+messageview.getText().toString());
				//
				Log.d("---message text msg",""+msg.getData().getString("msg"));

                String [] test = msg.getData().getString("msg").split(",");//死哦的公交规划,2
//                receive_text = test[0];//发送的消息内容
				Log.d("---message text test",""+test[0]);
				if (test[0].indexOf("·Demarcation") != -1){
				    //!=-1即为包含有字符串 ·Demarcation
                    String [] s = test[0].split("·Demarcation");
                    receive_text = s[0];
                    order_id = s[2];
                    String type = s[1];
                    Log.d("---message text type",""+s[1]);
                    handler.sendEmptyMessage(3);
                }else {
                    receive_text = test[0];
                    handler.sendEmptyMessage(1);
                }

            }
        }
    };

    public Handler getMsghandler() {
        return msghandler;
    }





    // 普通事件的处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(ChatMessageEvent event) {
//        Toast.makeText(ChatActivity.this, event.order_id+""+event.amount+""+event.moeny, Toast.LENGTH_SHORT).show();
		String str = "这是我的订单需求，请查收！\n我需要"+event.amount +"个人参果，将支付" + event.moeny+"元。" ;
		ChatMessage msg=new ChatMessage(""+username,""+receive_id,""+ str +"·Demarcation3"+"·Demarcation"+event.order_id,2);
		send(msg);// |Demarcation|
		order_id = event.order_id;
		order_text = str;
		handler.sendEmptyMessage(4);

    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//2.EventBus解注册
        EventBus.getDefault().unregister(this); //解除注册
		if (messageList.size() < 1){

		}else {
			SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();

			//存储聊天记录
			LinkedHashSet<String> setList = new LinkedHashSet<>();
			for (int i = 0 ; i < messageList.size(); i++) {
			    if (messageList.get(i).getOrder_id() != null){
			        //
                    Log.d("--messageList",""+messageList.get(i).getOrder_id());
                    setList.add(""+messageList.get(i).getMsg_text()+","+messageList.get(i).getMsg_type()+","+Calendar.getInstance().getTimeInMillis()+","+ messageList.get(i).getOrder_id());
                }else {
                    setList.add(""+messageList.get(i).getMsg_text()+","+messageList.get(i).getMsg_type()+","+Calendar.getInstance().getTimeInMillis()+","+ "1");//"" null
                }

				Log.d("---setList text",""+messageList.get(i).getMsg_text());
                Log.d("---setList type",""+messageList.get(i).getMsg_type());
                Log.d("---setList time",""+Calendar.getInstance().getTimeInMillis());
			}
			//存储聊天记录，名字为 对方的id 即sp.getString("team_id","");
			editor.putStringSet(""+receive_id+""+username, setList);
			editor.commit();
		}

		/****字符串逗号截取*******/
//		String a = "asdf,cxg,dfyr,ery";
//		String[] s = a.split(",");

    }
}
