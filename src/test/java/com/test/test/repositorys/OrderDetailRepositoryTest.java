package com.test.test.repositorys;

import com.test.test.dataobject.OrderDetail;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 下午6:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveaTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("2");
        orderDetail.setOrderId("2");
        orderDetail.setProductId("2");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(2.5)); //因为 new BigDecimal(2.5) 是一个对象，所以要new 一个出来，不能直接填
        orderDetail.setProductIcon("www.cc.com/img.img");
        orderDetail.setProductQuantity(800);
        orderDetailRepository.save(orderDetail);
    }



    @Test
    public void findByOrderId() {

    }
}