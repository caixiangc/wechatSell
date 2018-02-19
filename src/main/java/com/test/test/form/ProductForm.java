package com.test.test.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/15 上午11:53
 */
@Data //添加这个注解以后 就不用写  get 和 set 方法了
public class ProductForm { //ProductForm 里面只填 表单里面包括的内容 别写多谢，如果多写这里null 在update的时候会把原来数据给覆盖掉
    private String productId;
    //商品名字
    private String productName;
    //商品单价
    private BigDecimal productPrice; //BigDecimal的数据类型就是对应数据库中类型decimal类型
    //商品库存
    private Integer productStock;   // 这里库存不能超过1000 如果超过1000 就会有"，" 有逗号就需要 string 类型，在bootstrap 里面就显示不出
    //商品描述
    private String productDescription;
    //商品图片
    private String productIcon;
    //商品类型
    private Integer categoryType;
}
