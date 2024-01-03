package com.sky.aop.impl;

import com.sky.aop.Validator;
import com.sky.exception.FormatException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @projectName: sky_take_out
 * @package: com.sky.aop.impl
 * @className: PhoneValidator
 * @author: Eric
 * @description: TODO
 * @date: 2024/1/3 16:47
 * @version: 1.0
 */
@Component
@Order(3)
public class PhoneValidator implements Validator {

    /**
     * 使用正则校验手机号数字位数,要满足十一位
     * @param
     */

    @Override
    public void validate(String userName, String password, String phone, String idNumber) {
        if (!phone.matches("^\\d{11}$")) {
            throw new FormatException(String.format("invalid phone:%s,must be 11 numbers",phone));
        }
    }
}
