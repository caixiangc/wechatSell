package com.test.test.enums;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 下午8:55
 */
public enum  ResultEnum {
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),


    PRODUCT_NOT_EXIT(10,"产品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIT(13,"订单为空"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单中无商品"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    CART_EMPTY(18,"购物车为空"),
    ORDER_OWN_ERROR(19,"该订单不属于该用户"),
    ORDER_CANCEL_SUCCESS(20,"订单取消成功"),
    ORDER_FINISH_SUCCESS(21,"订单完结成功"),
    PRODUCT_DOWN_SUCCESS(22,"商品下架成功"),
    PRODUCT_UP_SUCCESS(23,"商品上架成功")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
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
