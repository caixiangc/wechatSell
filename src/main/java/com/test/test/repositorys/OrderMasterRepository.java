package com.test.test.repositorys;

import com.test.test.dataobject.OrderMaster;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 上午9:39
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable); //按照买家的findByBuyerOpenid 来查，如果不传 pageable 那么会把所有订单信息都查出来，信息量太大
}
