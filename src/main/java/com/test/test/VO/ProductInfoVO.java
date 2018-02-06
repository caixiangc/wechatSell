package com.test.test.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 商品详情vo
 * @author 浙外吴彦祖
 * @data 2018/2/4 上午9:13
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private Integer productPrice;
    @JsonProperty("description")
    private String productDescribe;
    @JsonProperty("icon")
    private String productIcon;
}
