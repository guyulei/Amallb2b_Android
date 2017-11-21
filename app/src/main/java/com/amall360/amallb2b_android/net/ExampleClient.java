package com.amall360.amallb2b_android.net;

import com.amall360.amallb2b_android.utils.ToastUtil;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by A on 2017/11/21.
 */

public class ExampleClient{
    //WebSocketClient 和 address
    private WebSocketClient mWebSocketClient;
    private String address = "ws://47.93.223.13:9501";

    //初始化WebSocketClient
    public void initSocketClient() throws URISyntaxException {
        if(mWebSocketClient == null) {
            mWebSocketClient = new WebSocketClient(new URI(address)) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    //连接成功
                    ToastUtil.showToast("opened connection");
                }
                @Override
                public void onMessage(String s) {
                    //服务端消息
                    ToastUtil.showToast("received:" + s);
                }
                @Override
                public void onClose(int i, String s, boolean remote) {
                    //连接断开，remote判定是客户端断开还是服务端断开
                    ToastUtil.showToast("Connection closed by " + ( remote ? "remote peer" : "us" ) + ", info=" + s);
                    //
                    closeConnect();
                }
                @Override
                public void onError(Exception e) {
                    ToastUtil.showToast("error:" + e);
                    mWebSocketClient.connect();
                }
            };
        }
    }


    //断开连接
    public void closeConnect() {
        try {
            mWebSocketClient.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            mWebSocketClient = null;
        }
    }


    //发送消息
    public void sendMsg(String msg) {
        mWebSocketClient.send(msg);
    }
}
