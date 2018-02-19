package com.test.test.service;

import com.test.test.dataobject.OrderDetail;
import com.test.test.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 下午7:06
 */
public interface OrderService   {

    //创建订单
    OrderDTO createOrder(OrderDTO orderDTO); //这层只需要 申明方法就行了，不用写private, 后面还要设计数据转换
    //查询订单
    OrderDTO findOrder(String orderId);
    //查询订单列表
    Page<OrderDTO> findOrderList(String buyerOpenId, Pageable pageable);
    //取消订单
    OrderDTO cancelOrder(OrderDTO orderDTO);
    //完结订单
    OrderDTO finishOrder(OrderDTO orderDTO);
    //支付订单
    OrderDTO payOrder(OrderDTO orderDTO);
    //查询订单列表 （卖家端）
    Page<OrderDTO> findOrderList(Pageable pageable);
}
