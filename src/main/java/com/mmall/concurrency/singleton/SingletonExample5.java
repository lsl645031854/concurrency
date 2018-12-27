package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * @Author: lsl
 * @Description: 安全发布对象
 * @Date: Created on 21:20 2018/12/27
 */
@ThreadSafe
public class SingletonExample5 {
    //私有化构造方法
    private SingletonExample5(){

    }
    //单例对象 volatile关键字 + 双重检测 来限制代码发生指令重排
    private volatile static SingletonExample5 instance = null;
    //静态工厂方法（双重同步锁单例模式）
    public static synchronized SingletonExample5 getInstance() {
        if (instance == null){//使用双重检测机制
            synchronized (SingletonExample5.class){
                if(instance == null){
                    instance = new SingletonExample5();
                }
            }

        }
        return instance;
    }
}
