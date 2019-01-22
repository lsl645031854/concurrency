package com.mmall.concurrency.lambda;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 21:21 2019/1/22
 */
@Slf4j
public class TestInterFace {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        List<User> list = new ArrayList<>();
        Class<ConvertJson> convertJsonClass = ConvertJson.class;

        list.add(new User("tom", 18));
        list.add(new User("mary", 16));

        //jsonArray = myMethod(list);
    }

    @Test
    public void myMethod() {
        List<User> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        list.add(new User("tom", 18));
        list.add(new User("mary", 16));
//        list.forEach(user -> jsonArray.add(convertJson.convertJsonEntity(user)));
        log.info("jsonArray{}",jsonArray);
    }
    @Test
    public void test(){
        List<User> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        list.add(new User("tom", 18));
        list.add(new User("mary", 16));
//        list.forEach(user -> System.out.println(user.getAge()));
        list.stream().map(user -> user.getAge()).collect(Collectors.toList());
    }
}
