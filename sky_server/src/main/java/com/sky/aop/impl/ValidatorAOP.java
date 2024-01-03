package com.sky.aop.impl;

import com.sky.aop.FormatValidator;
import com.sky.aop.Validator;
import com.sky.constant.PasswordConstant;
import com.sky.dto.EmployeeDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @projectName: sky_take_out
 * @package: com.sky.aop.impl
 * @className: ValidatorAOP
 * @author: Eric
 * @description: TODO
 * @date: 2024/1/3 17:09
 * @version: 1.0
 */
@Aspect
@Component
public class ValidatorAOP implements Validator {

    public List<Validator> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }

    @Autowired
    List<Validator> validators;

    /**
     *
     * @param userName
     * @param password
     * @param phone
     * @param idNumber
     */
    @Override
    public void validate(String userName, String password, String phone, String idNumber) {
        for (Validator val :
                validators) {
            val.validate(userName,password,phone,idNumber);
        }
    }

    /**
     * 在FormatValidator注解上使用环绕通知,当注册新用户时,对用户的信息进行校验
     * 但是好像不需要后端去校验
     * @param joinPoint
     * @param formatValidator
     * @return
     * @throws Throwable
     */
    @Around("@annotation(formatValidator)")
    public Object metric(ProceedingJoinPoint joinPoint, FormatValidator formatValidator) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        EmployeeDTO employeeDTO = (EmployeeDTO) args[0];
        try{
            Object ret = joinPoint.proceed();
            validate(employeeDTO.getName(), PasswordConstant.DEFAULT_PASSWORD,employeeDTO.getPhone(),employeeDTO.getIdNumber());
            return ret;
        }finally {

        }
    }
}

