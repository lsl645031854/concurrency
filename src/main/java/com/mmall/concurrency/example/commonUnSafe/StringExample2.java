package com.mmall.concurrency.example.commonUnSafe;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 14:34 2019/1/13
 */
@Slf4j
@ThreadSafe
public class StringExample2 {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //计数
    public static StringBuffer stringBuffer = new StringBuffer();
    public static void main(String[] args) throws Exception{
        //线程池
        ExecutorService ExecutorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            ExecutorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        ExecutorService.shutdown();
        log.info("size:{}", stringBuffer.length());
    }

    private static void update(){
        stringBuffer.append("1");
    }
}
