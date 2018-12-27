package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * @Author: lsl
 * @Description: 安全发布对象
 * @Date: Created on 21:20 2018/12/27
 */
@ThreadSafe
public class SingletonExample2 {
    //私有化构造方法
    private SingletonExample2(){

    }
    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();
    //静态工厂方法（饿汉模式：）
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
