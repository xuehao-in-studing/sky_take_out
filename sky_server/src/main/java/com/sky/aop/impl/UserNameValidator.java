package com.sky.aop.impl;

import com.sky.aop.Validator;
import com.sky.exception.FormatException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @projectName: sky_take_out
 * @package: com.sky.aop.impl
 * @className: UserNameValidator
 * @author: Eric
 * @description: TODO
 * @date: 2024/1/3 17:16
 * @version: 1.0
 */
@Component
@Order(4)
public class UserNameValidator implements Validator {
    /**
     *
     * @param userName
     * @param password
     * @param phone
     * @param idNumber
     */
    @Override
    public void validate(String userName, String password, String phone, String idNumber) {
        if (userName.length() < 6 || userName.length() > 18) {
            throw new FormatException(String.format("invalid userName:%s,must be 6-18 numbers", userName));
        }
    }
}
