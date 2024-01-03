package com.sky.aop.impl;

import com.sky.aop.Validator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @projectName: sky_take_out
 * @package: com.sky.aop.impl
 * @className: IDNumberValidator
 * @author: Eric
 * @description: TODO
 * @date: 2024/1/3 16:47
 * @version: 1.0
 */
@Component
@Order(1)
public class IDNumberValidator implements Validator {

    /**
     * 使用正则校验身份证号数字位数,要满足十八位
     * @param
     */
    @Override
    public void validate(String userName, String password, String phone, String idNumber) {
        if (!idNumber.matches("^\\d{18}$")) {
            throw new IllegalArgumentException(String.format("invalid IDNumber:%s,must be 18 numbers", idNumber));
        }
    }
}
