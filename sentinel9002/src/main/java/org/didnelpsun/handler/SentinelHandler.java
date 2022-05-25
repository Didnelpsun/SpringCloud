// SentinelHandler.java
package org.didnelpsun.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SentinelHandler {
    public static String defaultHandler(String text, BlockException exception){
        exception.printStackTrace();
        return "default-" + text;
    }

    public static String hotkeyHandler(String text, BlockException exception){
        exception.printStackTrace();
        return "hotkey-" + text;
    }
}
