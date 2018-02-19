package com.test.test.converter;

import com.test.test.dataobject.OrderMaster;
import com.test.test.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/7 下午10:03
 */
public class OrderMaster2OrderDTOConverter { //单个元素的转换
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){ //多个元素的转换
        List<OrderDTO> orderDTOList = orderMasterList.stream()
                .map(e->convert(e))  //这个map语法类似es6  ，这个e 就像每次便利到的那个对象
                .collect(Collectors.toList());
        return orderDTOList;
    }
}
