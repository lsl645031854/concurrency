package com.mmall.concurrency.guava.cache;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 18:20 2019/8/18
 */
public class LinkedHashLRUCasheExample {
    public static void main(String[] args) {
        cashe();
    }

    private static void cashe() {
        LRUCashe<String, String> cashe = new LinkedHashLRUCashe<>(3);
        cashe.put("1", "1");
        cashe.put("2", "2");
        cashe.put("3", "3");
        cashe.put("4", "4");
        System.out.println(cashe.toString());
    }
}
