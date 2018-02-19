package com.test.test.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品包含类目(就是resultVO 里面的data)
 * @author 浙外吴彦祖
 * @data 2018/2/4 上午9:02
 */
@Data
public class ProductVO implements Serializable {

    //这个序列化的id 是唯一的
    private static final long serialVersionUID = 3028410686849680609L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty(value = "foods")
    private List<ProductInfoVO> productInfoVOList;
    //这里有一个很坑的点，在用@JsonProperty 的注解的时候，下面变量名一定不要开头大写，开头大写是接口，或者类名，并不是变量名
    //如果变量名开头大写，那么@JsonProperty 侦测不到

}
