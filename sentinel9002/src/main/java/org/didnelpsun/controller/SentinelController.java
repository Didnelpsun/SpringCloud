// SentinelController.java
package org.didnelpsun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {
    @GetMapping("/text/{text}")
    public String getText(@PathVariable String text) {
        return text;
    }
}
