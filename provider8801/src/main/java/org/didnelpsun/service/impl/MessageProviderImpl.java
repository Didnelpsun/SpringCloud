// MessageProviderImpl.java
package org.didnelpsun.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

// EnableBinding将channel和exchange绑定到一起
// Source类即定义消息推送的管道，即通过Source类将输入的不同种类的消息全部转换为源消息
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements IMessageProvider {
    @Resource
    // 消息发送管道
    private MessageChannel output;

    @Override
    public String send(String text) {
        // 发送消息
        output.send(MessageBuilder.withPayload(text).build());
        log.info("send:" + text);
        return text;
    }
}
