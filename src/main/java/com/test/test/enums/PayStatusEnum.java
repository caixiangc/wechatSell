package com.test.test.enums;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 上午8:49
 */
public enum PayStatusEnum {
    NOTPAY(0,"未支付"),
    PAYED(1,"已支付");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
