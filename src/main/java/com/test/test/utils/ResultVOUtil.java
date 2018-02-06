package com.test.test.utils;

import com.test.test.VO.ResultVO;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/5 上午7:56
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null); //这里依据java的多态可以这样写 ，简化代码
    }
    public static ResultVO fail(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setData(msg);
        return resultVO;
    }
}
