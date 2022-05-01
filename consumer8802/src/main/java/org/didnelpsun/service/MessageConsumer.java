// MessageConsumer.java
package org.didnelpsun.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class MessageConsumer {
    @Bean
    public Consumer<String> channel8002(){
        return mes -> log.info("Message:" + mes);
    }
}
