package com.amall360.amallb2b_android.net;

import android.content.Context;

import com.amall360.amallb2b_android.utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import de.tavendo.autobahn.WebSocketOptions;

/**
 * Created by A on 2017/11/22.
 */

public class ConnManager {

    private WebSocketConnection mWebSocketConnection;
    private Timer               mTimer;
    private TimerTask           mTimerTask;
    private WebSocketOptions    mWebSocketOptions;
    private Context             mContext;
    String uri = null;
    private long nowdate;

    public ConnManager(Context context) {
        mWebSocketConnection = new WebSocketConnection();
        mTimer = new Timer();
        this.uri = ApiUrlBase.api_connmanager;
        mWebSocketOptions = new WebSocketOptions();
        setwebsocketoptions();
        this.mContext = context;
        //心跳包，不停地发送消息给服务器
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mWebSocketConnection.sendTextMessage("心跳包");
                LogUtils.e("连接中。。。。。");
            }
        };
    }

    //链接服务器端的代码
    public void connect() {
        try {
            mWebSocketConnection.connect(uri, new WebSocketHandler() {
                @Override
                public void onOpen() {
                    LogUtils.e("Status: Connected to " + uri);
                    sendMessage("guyulei");
                    sendHB();
                }

                @Override
                public void onTextMessage(String payload) {
                    LogUtils.e("服务器返回: " + payload);
                    nowdate = System.currentTimeMillis();
                }

                @Override
                public void onClose(int code, String reason) {
                    LogUtils.e("Connection lost." + reason);
                }
            }, mWebSocketOptions);
        } catch (WebSocketException e) {
            LogUtils.e(e.toString());
        }
    }

    //开启心跳包，每一秒发送一次消息，如果返回lost再重连
    public void sendHB() {
        mTimer.schedule(mTimerTask, 8000, 8000);
        //每次发送心跳包，服务器接收到响应就会返回一个值，如果查过5s还没有收到返回值，那么就判定是断网。
        if ((System.currentTimeMillis() - nowdate) > 5000 && nowdate != 0) {
            mWebSocketConnection.disconnect();
            mTimer.cancel();
            connect();
            return;
        }
    }

    public void disconnect() {
        if (mWebSocketConnection != null) {
            mWebSocketConnection.disconnect();
        }
    }

    public void sendMessage(String data) {
        mWebSocketConnection.sendTextMessage(data);
    }

    //调整链接是否超时的时间限制
    public void setwebsocketoptions() {
        mWebSocketOptions.setSocketConnectTimeout(30000);
        mWebSocketOptions.setSocketReceiveTimeout(10000);
    }
}
