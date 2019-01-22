package com.mmall.concurrency.lambda;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 19:43 2019/1/22
 */
@Slf4j
@Getter
@Setter
@Data
public class User {
    private String name;
    private int age;

    public User (String name, int age) {
        this.name = name;
        this.age = age;
    }
}
class Inte {

}
