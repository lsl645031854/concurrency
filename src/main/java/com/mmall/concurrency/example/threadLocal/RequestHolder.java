package com.mmall.concurrency.example.threadLocal;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 13:43 2019/1/13
 */
public class RequestHolder {
    private final static  ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }
    public static Long getId() {
        return requestHolder.get();
    }
    public static void remove() {
        requestHolder.remove();
    }
}
