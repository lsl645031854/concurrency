package com.mmall.concurrency.juc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 16:25 2020/8/9
 */
@Getter
@AllArgsConstructor
public enum CountryEnum {

    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕");

    private Integer code;
    private String message;

    public static CountryEnum getByIndex(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (index == value.getCode()) {
                return value;
            }
        }
        return null;
    }
}
