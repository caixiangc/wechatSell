package com.test.test.service;

import com.test.test.dto.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * 买家
 * @author 浙外吴彦祖
 * @data 2018/2/10 下午12:05
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOneOrder(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
