package com.test.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.test.dataobject.OrderDetail;
import com.test.test.enums.OrderStatusEnum;
import com.test.test.enums.PayStatusEnum;
import com.test.test.utils.EnumUtil;
import com.test.test.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 下午8:01
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //这个注解 解释把 为空的字段 不返回
public class OrderDTO {

    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    // 订单状态默认，为新下单
    private Integer orderStatus; // 这里我们不需要初始化状态 ， 因为他就是实现 新数据对象的
    // 支付状态 默认为0 未支付
    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    //这个是新的字段，汇总detailList的 我们就是为了它 才新建orderDTO
    // 之所以有这个是因为前端会这么传进来，因为一个用户可能生成多个order_detail(就是点多个商品)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore //如果不写JsonIgnore 那么也会把他们俩返回出去
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
