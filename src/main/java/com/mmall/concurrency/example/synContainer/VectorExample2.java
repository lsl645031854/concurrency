package com.mmall.concurrency.example.synContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

/**
 * @Author: lsl
 * @Description: 同步容器
 * @Date: Created on 20:53 2019/1/13
 */
@Slf4j
public class VectorExample2 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
    }
}
