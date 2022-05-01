// MessageProviderImpl.java
package org.didnelpsun.service.impl;

import org.didnelpsun.service.IMessageProvider;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class MessageProviderImpl implements IMessageProvider {
    @Resource
    private StreamBridge streamBridge;
    // 引入交换机名
    @Value("${spring.cloud.name}")
    private String exchangeName;

    @Override
    public String send(String text) {
        // 发送消息，第一个为通道名称，第二个为发送消息内容
        streamBridge.send(exchangeName, MessageBuilder.withBody(text.getBytes(StandardCharsets.UTF_8)).build());
        return text;
    }
}
