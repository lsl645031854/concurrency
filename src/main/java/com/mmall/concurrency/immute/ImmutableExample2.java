package com.mmall.concurrency.immute;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 21:12 2019/1/7
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private final static Integer a = 1;
    private final static String b = "2";
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        //Collections.unmodifiableXXX 处理之后不可以被修改
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}",map.get(1));
    }
}
