package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * @Author: lsl
 * @Description: 安全发布对象
 * @Date: Created on 21:20 2018/12/27
 */
@NotThreadSafe
public class SingletonExample1 {
    //私有化构造方法
    private SingletonExample1 (){

    }
    //单例对象
    private static SingletonExample1 instance = null;
    //静态工厂方法（懒汉模式：单线程没事，多线程可能会出错，线程不安全的）
    public static SingletonExample1 getInstance() {
        if (instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
