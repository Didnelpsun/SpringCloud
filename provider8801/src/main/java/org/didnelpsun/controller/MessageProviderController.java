// MessageProviderController.java
package org.didnelpsun.controller;

import org.didnelpsun.service.impl.MessageProviderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageProviderController {
    @Resource
    private MessageProviderImpl messageProvider;

    @GetMapping("/send/{text}")
    public String send(@PathVariable String text) {
        return messageProvider.send(text);
    }
}
