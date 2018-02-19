package com.test.test.service.impl;

import com.test.test.dto.OrderDTO;
import com.test.test.enums.ResultEnum;
import com.test.test.exception.SellException;
import com.test.test.service.BuyerService;
import com.test.test.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/10 下午12:09
 */
@Service  //要在impl实现类 里面写server 注解
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) { //如果在public后面使用 synchronized 那么把 这段程序cancelOrder() 变成单线程，就不会出错，如果线程放多了，那么放出去的回不来就会出错
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        if(orderDTO == null){
            log.error("【取消订单】没有此订单，orderId ={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        return orderService.cancelOrder(orderDTO);
    }

    public OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOrder(orderId);
        if(orderDTO == null){
            return null;
        }
        //判断是不是自己的订单
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){ //equalsIgnoreCase 忽略了大小写，而equals 是不能忽略的
            log.error("[查询一个订单]订单openid不一致。openid = {}",openid);
            throw new SellException(ResultEnum.ORDER_OWN_ERROR); //这个异常也会返回给前端的。
        }

        return orderDTO;
    }
}
