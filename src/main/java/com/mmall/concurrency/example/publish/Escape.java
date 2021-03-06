package com.mmall.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lsl
 * @Description:溢出
 * @Date: Created on 21:07 2018/12/27
 */
@Slf4j
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass (){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
       new Escape();
    }
}
