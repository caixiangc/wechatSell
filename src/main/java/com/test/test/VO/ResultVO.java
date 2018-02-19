package com.test.test.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * VO 是 视图对象，主要是为了返回给前端视图用的
 * 也就是：http请求返回的最外层对象
 * @author 浙外吴彦祖
 * @data 2018/2/4 上午8:21
 */
@Data  //使用lombok.Data ,就让你不用谢get，set 那些方法了
@JsonInclude(JsonInclude.Include.NON_NULL) //这个注解 解释把 为空的字段 不返回 ,如果要把它设置成全局那么就要 卸载yml文件里面
//implements Serializable 让这个类序列化(继承一个Serializable的接口),,ID可以通过Serializable去生成
public class ResultVO<T> implements Serializable {//******因为data里面是个对象，所以我们这里可以定义一个泛型,这个对象不具体，可能是一种对象的嵌套结构所以不能像
    // shift + controller + i 生成一个唯一的序列化id 到时候就可以直接用了
    private static final long serialVersionUID = -2604484582034068928L;
    //错误码  0-成功 ，1-失败
    private Integer code;
    //提示信息
    private String msg = ""; //代表赋予的初始值 为空
    //返回具体内容
    private T data;
}
