// SentinelController.java
package org.didnelpsun.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.didnelpsun.handler.SentinelHandler;
import org.didnelpsun.service.SentinelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SentinelController {
//    @GetMapping("/text/{text}")
//    public String getText(@PathVariable String text) {
//        return text;
//    }

//    @GetMapping("/text/{text}")
//    public String getText(@PathVariable String text) {
//        return testText(text);
//    }
//
//    // 加工输入的字符
//    @GetMapping("/test/{text}")
//    public String testText(@PathVariable String text) {
//        return "test " + text;
//    }

//    @Resource
//    private SentinelService service;
//
//    @GetMapping("/text/{text}")
//    public String getText(@PathVariable String text) {
//        return service.service() + "-text-" + text;
//    }
//
//    // 加工输入的字符
//    @GetMapping("/test/{text}")
//    public String testText(@PathVariable String text) {
//        return service.service() + "-test-" + text;
//    }
//
//    // 测试热点Key
//    @GetMapping("/hotkey/{key}")
//    // blockHandler指定处理Hotkey规则异常的处理方法
//    @SentinelResource(value = "hotkey", blockHandler = "dealHotkey")
//    public String getHotkey(@PathVariable String key){
//        return key;
//    }
//
//    // 参数跟getHotkey应该是一样的，并加上一个异常
//    public String dealHotkey(String key, BlockException exception){
//        exception.printStackTrace();
//        return "deal-" + key;
//    }

    @Resource
    private SentinelService service;

    @GetMapping("/text/{text}")
    @SentinelResource(value = "text", blockHandlerClass = SentinelHandler.class, blockHandler = "defaultHandler")
    public String getText(@PathVariable String text) {
        return service.service() + "-text-" + text;
    }

    // 加工输入的字符
    @GetMapping("/test/{text}")
    @SentinelResource(value = "test", blockHandlerClass = SentinelHandler.class, blockHandler = "defaultHandler")
    public String testText(@PathVariable String text) {
        return service.service() + "-test-" + text;
    }

    // 测试热点Key
    @GetMapping("/hotkey/{key}")
    @SentinelResource(value = "hotkey", blockHandlerClass = SentinelHandler.class, blockHandler = "hotkeyHandler")
    public String getHotkey(@PathVariable String key){
        return key;
    }
}
