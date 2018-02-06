package com.test.test.VO;

import lombok.Data;

/**
 * VO 是 视图对象，主要是为了返回给前端视图用的
 * 也就是：http请求返回的最外层对象
 * @author 浙外吴彦祖
 * @data 2018/2/4 上午8:21
 */
@Data  //使用lombok.Data ,就让你不用谢get，set 那些方法了
public class ResultVO<T> { //******因为data里面是个对象，所以我们这里可以定义一个泛型,这个对象不具体，可能是一种对象的嵌套结构所以不能像
    //错误码  0-成功 ，1-失败
    private Integer code;
    //提示信息
    private String msg;
    //返回具体内容
    private T data;
}
