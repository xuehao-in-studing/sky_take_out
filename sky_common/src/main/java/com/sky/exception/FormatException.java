package com.sky.exception;

/**
 * @projectName: sky_take_out
 * @package: com.sky.exception
 * @className: PhoneException
 * @author: Eric
 * @description: TODO
 * @date: 2024/1/3 16:55
 * @version: 1.0
 */
public class FormatException extends RuntimeException{

        public FormatException(){
        }

        public FormatException(String msg){
            super(msg);
        }

}
