package com.sky.aop.impl;

import com.sky.aop.Validator;
import com.sky.exception.FormatException;
import jakarta.validation.Valid;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @projectName: sky_take_out
 * @package: com.sky.aop.impl
 * @className: PasswordValidator
 * @author: Eric
 * @description: TODO
 * @date: 2024/1/3 16:46
 * @version: 1.0
 */
@Component
@Order(2)
public class PasswordValidator implements Validator {

    /**
     * 使用正则校验密码位数,要满足6-18位,且字符开头，只能包含大小写字母、数字和下划线
     * @param userName
     * @param password
     * @param phone
     * @param idNumber
     */

    @Override
    public void validate(String userName, String password, String phone, String idNumber) {
        if (!password.matches("[a-zA-Z]\\w{5,17}")) {
            throw new FormatException(String.format("invalid password:%s,must above 6 and below 18 bits",password));
        }
    }
}
