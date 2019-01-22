package com.mmall.concurrency.lambda;

import net.sf.json.JSONObject;

/**
 * @Author: lsl
 * @Description:
 * @Date: Created on 19:46 2019/1/22
 */
@FunctionalInterface
public interface ConvertJson<User> {
    JSONObject convertJsonEntity (User user);
}
