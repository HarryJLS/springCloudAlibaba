package com.example.webSocketConfig;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author JLS
 * @description:
 * @since 2023-06-19 18:48
 */
@ServerEndpoint("/notice")
@Component
public class NoticeWebsocket {

    private static Session session;

    @OnOpen
    public void open(Session session) {
        this.session = session;
        System.out.println("连接成功");
    }

    /**
     * 发送给所有用户
     * @param noticeType
     */
    public static void sendMessage(String noticeType) throws IOException {
        NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
        noticeWebsocketResp.setNoticeType(noticeType);
        sendMessage(noticeWebsocketResp);
    }

    /**
     * 发送给所有用户
     * @param noticeWebsocketResp
     */
    public static void sendMessage(NoticeWebsocketResp noticeWebsocketResp) throws IOException {
        String message = JSONObject.toJSONString(noticeWebsocketResp);
        session.getBasicRemote().sendText(message);

    }
}
