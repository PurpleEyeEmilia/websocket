package com.demo.websocket.socket;

import com.alibaba.fastjson.JSON;
import com.demo.websocket.modules.user.constant.MessageConstant;
import com.demo.websocket.modules.user.entity.User;
import com.demo.websocket.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.*;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Component
public class MessageWebSocketHandler extends AbstractWebSocketHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(MessageWebSocketHandler.class);

    public static final Map<String, List<WebSocketSession>> USER_SOCKET_SESSION_MAP = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String userId = (String) webSocketSession.getHandshakeAttributes().get("userId");
        List<WebSocketSession> webSocketSessions;
        if (USER_SOCKET_SESSION_MAP.containsKey(userId)) {
            webSocketSessions = USER_SOCKET_SESSION_MAP.get(userId);
        } else {
            webSocketSessions = new ArrayList<>();
            USER_SOCKET_SESSION_MAP.put(userId, webSocketSessions);
        }
        webSocketSessions.add(webSocketSession);

        while (true) {
            User user = new User();
            user.setName(DateUtils.getString(new Date()));
            Thread.sleep(1000);
            webSocketSession.sendMessage(new TextMessage(JSON.toJSONString(user)));
        }

        /*for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setKid(123L + i);
            user.setPhone("15165465465" + i);
            if (i == 0) {
                user.setName("门前大桥下，有过一群鸭，");
            } else if (i == 1) {
                user.setName("快来快来数一数，二四六七八");
            } else if (i == 2) {
                user.setName("咕嘎咕嘎真呀真多鸭，");
            } else if (i == 3) {
                user.setName("别考个鸭蛋抱回家。");
            } else if (i == 4) {
                user.setName("别考个鸭蛋抱回家！");
            }
            Thread.sleep(2000);
            webSocketSession.sendMessage(new TextMessage(JSON.toJSONString(user, SerializerFeature.WriteMapNullValue)));
        }*/

    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(message);
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        session.sendMessage(message);
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        session.sendMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        removeSession(session);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Websocket:" + session.getId() + "已经关闭");
        removeSession(session);
    }

    private void removeSession(WebSocketSession session) throws IOException {
        if (CollectionUtils.isEmpty(USER_SOCKET_SESSION_MAP)) {
            return;
        }
        for (String userId : USER_SOCKET_SESSION_MAP.keySet()) {
            List<WebSocketSession> webSocketSessions = USER_SOCKET_SESSION_MAP.get(userId);
            if (!CollectionUtils.isEmpty(webSocketSessions)) {
                for (WebSocketSession webSocketSession : webSocketSessions) {
                    if (session != null && webSocketSession != null && session.getId().equals(webSocketSession.getId())) {
                        webSocketSession.close();
                    }
                }
            }
        }
    }

    public void sendMessage(final TextMessage textMessage, String type) {
        if (MessageConstant.ALL.equalsIgnoreCase(type)) {
            if (!CollectionUtils.isEmpty(USER_SOCKET_SESSION_MAP)) {
                for (String key : USER_SOCKET_SESSION_MAP.keySet()) {
                    threadSendMessage(textMessage, key);
                }
            }
        } else {
            threadSendMessage(textMessage, type);
        }

    }

    public void sendAttachment(final BinaryMessage binaryMessage, String type) {
        if (MessageConstant.ALL.equalsIgnoreCase(type)) {
            if (!CollectionUtils.isEmpty(USER_SOCKET_SESSION_MAP)) {
                for (String key : USER_SOCKET_SESSION_MAP.keySet()) {
                    threadSendAttachment(binaryMessage, key);
                }
            }
        } else {
            threadSendAttachment(binaryMessage, type);
        }
    }

    private void threadSendAttachment(BinaryMessage binaryMessage, String sendToUserId) {
        List<WebSocketSession> webSocketSessions = USER_SOCKET_SESSION_MAP.get(sendToUserId);
        if (!CollectionUtils.isEmpty(webSocketSessions)) {
            for (WebSocketSession webSocketSession : webSocketSessions) {
                if (webSocketSession.isOpen()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (webSocketSession.isOpen()) {
                                    webSocketSession.sendMessage(binaryMessage);
                                }
                            } catch (IOException e) {
                                LOGGER.error("推送附件异常！", e);
                            }
                        }
                    }).start();
                }
            }
        }
    }

    private void threadSendMessage(TextMessage textMessage, String sendToUserId) {
        List<WebSocketSession> webSocketSessions = USER_SOCKET_SESSION_MAP.get(sendToUserId);
        send(textMessage, webSocketSessions);
    }

    private void send(TextMessage textMessage, List<WebSocketSession> webSocketSessions) {
        if (!CollectionUtils.isEmpty(webSocketSessions)) {
            for (WebSocketSession webSocketSession : webSocketSessions) {
                if (webSocketSession.isOpen()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (webSocketSession.isOpen()) {
                                    webSocketSession.sendMessage(textMessage);
                                }
                            } catch (IOException e) {
                                LOGGER.error("推送消息异常！", e);
                            }
                        }
                    }).start();
                }
            }
        }
    }
}
