package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * @Author: lsl
 * @Description: 安全发布对象
 * @Date: Created on 21:20 2018/12/27
 */
@NotThreadSafe //可能会发生指令重排，影响双重检测机制
public class SingletonExample4 {
    //私有化构造方法
    private SingletonExample4(){

    }
    //单例对象
    private static SingletonExample4 instance = null;
    //静态工厂方法（双重同步锁单例模式）
    public static synchronized SingletonExample4 getInstance() {
        if (instance == null){//使用双重检测机制
            synchronized (SingletonExample4.class){
                if(instance == null){
                    instance = new SingletonExample4();
                }
            }

        }
        return instance;
    }
}
