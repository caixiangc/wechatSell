package com.test.test.dataobject;

import com.test.test.enums.OrderStatusEnum;
import com.test.test.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 上午8:26
 */
@Entity
@Data
@DynamicUpdate //这个注解会把 update 自动赋值
public class OrderMaster {
    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    // 订单状态默认，为新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    // 支付状态 默认为0 未支付
    private Integer payStatus = PayStatusEnum.NOTPAY.getCode();


    private Date createTime;

    private Date updateTime;

    // 当两个数据库有关联并且， 一对多的时候，我们要重新生成一个对象。
    // @Transient //在数据库表 和 DAO 对应的时候 会忽略下面那个字段
    // private List<OrderDetail> orderDetailList;


}
