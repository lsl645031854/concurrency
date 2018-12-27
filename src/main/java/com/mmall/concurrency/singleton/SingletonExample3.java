package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * @Author: lsl
 * @Description: 安全发布对象
 * @Date: Created on 21:20 2018/12/27
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    //私有化构造方法
    private SingletonExample3(){

    }
    //单例对象
    private static SingletonExample3 instance = null;
    //静态工厂方法（懒汉模式转线程安全的）
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
