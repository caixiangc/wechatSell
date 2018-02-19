package com.test.test.utils;

import java.util.Random;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 下午9:40
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * */
    public static synchronized String genUniqueKey(){ //要加synchronized关键字 多线程高迸发的时候 也可能会产生相同
        Random random = new Random();
        //System.currentTimeMillis(); //获取毫秒数
        Integer a = random.nextInt(900000)+100000; //这个是获取6位 随机数，目的是使随机数长度相同（永远6位）
        return System.currentTimeMillis() + String.valueOf(a);

    }

}
