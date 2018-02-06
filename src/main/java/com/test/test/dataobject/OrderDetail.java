package com.test.test.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 上午8:58
 */
@Entity //这个注解必须加，把数据库映射成对象的话
//@DynamicUpdate 若果createtime 和updatetime 没有用到那么可以不写
@Data
public class OrderDetail {
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //商品数量
    private Integer productQuality;
    private String productIcon;


}
