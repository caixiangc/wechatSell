package com.test.test.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/8 下午10:06
 */
@Data
public class OrderForm {
    //姓名必填
    @NotEmpty(message = "姓名不能为空")
    private String name;   // 应为传过来的 参数是这个对象， 所以变量名和参数名要保持一致

    //手机号必填
    @NotEmpty(message = "手机不能为空")
    private String phone;

    //买家地址必填
    @NotEmpty(message = "地址不能为空")
    private String address;

    //openId 必填
    @NotEmpty(message = "openId不能为空")
    private String openid;

    //购物车 必填
    @NotEmpty(message = "items不能为空")
    private String items;
}
