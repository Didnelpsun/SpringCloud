// MessageConsumer.java
package org.didnelpsun.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

// 将channel和exchange绑定在一起
// 通过Sink类将源消息转换类型为不同种类的消息，这里是RabbitMQ类型消息
@EnableBinding(Sink.class)
@Slf4j
public class MessageConsumer {
    @Value("${server.port}")
    private String port;


    // 监听队列，用于消费者队列的消息接收
    // Sink.INPUT为常量，表示接收输入
    @StreamListener(Sink.INPUT)
    public void receive(Message<String> message) {
        log.info("receive " + this.port + ":" + message.getPayload());
    }
}
