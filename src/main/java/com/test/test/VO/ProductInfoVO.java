package com.test.test.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品详情vo
 * @author 浙外吴彦祖
 * @data 2018/2/4 上午9:13
 */
@Data
public class ProductInfoVO implements Serializable {
    //前端都是要用到这个序列化id的
    private static final long serialVersionUID = -2735776000899849813L;

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
