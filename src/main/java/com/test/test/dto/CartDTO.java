package com.test.test.dto;

import lombok.Data;

/**
 * 购物车DTO  DTO:就是不是数据库映射的对象 ，只是业务中需要用到的对象
 * @author 浙外吴彦祖
 * @data 2018/2/6 下午10:24
 */
@Data
public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
