package com.net.mall.server.websocket;

import com.net.mall.pojo.vo.OrderMessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebSocketServer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void send(OrderMessageVO vo){
        log.info("发送消息：{}",vo);
        messagingTemplate.convertAndSend("/topic/send",vo);
    }

}
