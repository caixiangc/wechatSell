package com.test.test.exception;

import com.test.test.enums.ResultEnum;

/**
 * 这是个自定义异常，推荐使用，内置的自定义异常太简陋
 * @author 浙外吴彦祖
 * @data 2018/2/6 下午8:51
 */
public class SellException extends RuntimeException { //异常一定要 继承RuntimeException 这个异常的内置方法
    private Integer code;
    private String msg;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg()); //把resultEnum的msg 传到父类的构造方法里面去
        this.code = resultEnum.getCode(); //因为上面传进来的是ResultEnum.PRODUCT_NOT_EXIT ，所以这里直接getCode()就行利润
    }

    public SellException(Integer code,String msg) {
        super(msg); //这个会把 msg 传给父级元素，因为我们调用bindingResult.getFieldError().getDefaultMessage() 就是父级里面实现的
        this.code = code;
    }
}
