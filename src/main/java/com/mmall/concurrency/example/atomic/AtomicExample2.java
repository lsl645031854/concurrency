package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: lsl
 * @Description:并发测试
 * @Date: Created on 15:39 2018/12/23
 */
@Slf4j
@ThreadSafe
public class AtomicExample2 {

    private static AtomicIntegerFieldUpdater<AtomicExample2> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample2.class,"count");

    @Getter
    public volatile int count = 100;

    private static AtomicExample2 atomicExample2 = new AtomicExample2();
    public static void main(String[] args){
        if(updater.compareAndSet(atomicExample2,100,120)){
            log.info("update success, {}",atomicExample2.getCount());
        }

        if(updater.compareAndSet(atomicExample2,100,120)){
            log.info("update success, {}",atomicExample2.getCount());
        }else {
            log.info("update fail, {}",atomicExample2.getCount());
        }
    }
}
