package com.test.test.enums;

import org.aspectj.apache.bcel.classfile.Code;

/**
 * 状态 枚举（如果有些变量会常常被用到，又方便后期维护，那么枚举是非常必要的）
 * @author 浙外吴彦祖
 * @data 2018/2/3 下午1:27
 */
public enum  ProductStatusEnums implements CodeEnum{
    //枚举 相当于Const 常量
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProductStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    //因为是枚举，所以只需要Get方法，不需要set。
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
