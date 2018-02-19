package com.test.test.enums;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 上午8:33
 */
public enum OrderStatusEnum implements CodeEnum { //这里必须 要实现 CodeEnum 否者外部无法把这个类当做参数传
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

    /*public static OrderStatusEnum getOrderStatusEnum(Integer code){
        for(OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()){ //这里的OrderStatusEnum.values()是指所有我们定义的枚举
            if(orderStatusEnum.getCode().equals(code)){
                return orderStatusEnum;
            }
        }
        return null;
    }*/
}
