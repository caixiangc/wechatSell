package com.test.test.utils;

import com.test.test.enums.CodeEnum;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/13 下午1:25
 */
public class EnumUtil {
    //Class<T> enumClass 是个枚举类，类也可以当做参数传
    //如果类当参数传，那么 要和 EnumUtil 的返回值 <T extends CodeEnum> 保持一致 ，不然报参数类型错误
    // 你要传的类 是T ，你们的继承 要保持一致
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){ //****对枚举进行一个说明 <T extends CodeEnum>  T 继承于 CodeEnum
        for(T each: enumClass.getEnumConstants()){ //这里getEnumConstants 就是获取那个类 里面自定义的枚举
            if(each.getCode().equals(code)){
                return each; //这里return 是return一个对象的枚举(到时候可以自行 getCode 或者 getMsg)
            }
        }
        return null;
    };
}
