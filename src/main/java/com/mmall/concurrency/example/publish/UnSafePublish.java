package com.mmall.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 21:26 2018/12/25
 */
@Slf4j
public class UnSafePublish {
    private String[] status = {"a", "b", "c"};

    public String[] getStatus (){
        return status;
    }

    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish();
        String[] result = unSafePublish.getStatus();
         log.info("{}",Arrays.toString(result));
         result[0] = "d";
        log.info("{}",Arrays.toString(result));
    }
}
