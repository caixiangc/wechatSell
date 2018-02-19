package com.test.test.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.test.dataobject.OrderDetail;
import com.test.test.dto.OrderDTO;
import com.test.test.enums.ResultEnum;
import com.test.test.exception.SellException;
import com.test.test.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个只是把传进来的参数 items 解析成 List
 * @author 浙外吴彦祖
 * @data 2018/2/9 上午9:04
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO conver(OrderForm orderForm){
        Gson gson = new Gson();  // gson 可以吧 string 类型转化为 json    （json 可以转化为list）

        //这里就不能用 Bean.copyPropety ，是因为 两个字段名 类型不一样 复制不过去，
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),                         //fromJson(string,class) 第一个参数是你要转的对象，第二个参数是你要转换成的内容
                    new TypeToken<List<OrderDetail>>(){}.getType());    //List<OrderDetail> 就是我们要转成的对象
        }catch (Exception e){
            log.error("[对象转换]错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
