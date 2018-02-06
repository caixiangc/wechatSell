package com.test.test.enums;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 上午8:33
 */
public enum OrderStatusEnum {
    NEW(0,"新订单"), //这些都是枚举
    FINISH(1,"完结订单"),
    CANCEL(2,"已取消订单"); //要有构造方法，不然会标红

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
