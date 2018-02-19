package com.test.test.service.impl;

import com.test.test.dataobject.OrderDetail;
import com.test.test.dto.CartDTO;
import com.test.test.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/7 上午8:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    private final String openid = "123456";

    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("达康");
        orderDTO.setBuyerPhone("12345556");
        orderDTO.setBuyerAddress("杭州市");
        orderDTO.setBuyerOpenid(openid);


        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1");  //这里用OrderDetail 和 CartDTO  都可以
        orderDetail.setProductQuantity(5);
        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);

    }

    @Test
    public void findOrder() {
    }

    @Test
    public void findOrderList() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void finishOrder() {
    }

    @Test
    public void payOrder() {
    }
}