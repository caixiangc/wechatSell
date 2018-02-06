package com.test.test.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品（数据库映射表）
 * @author 浙外吴彦祖
 * @data 2018/2/3 上午10:01
 */
@Entity
public class ProductInfo {
    @Id  //虽然是字符类型，但是我们id 还是需要设置的，不然 我们jpa 就找不到主键了
    //@GeneratedValue  这里就不用 设置自增了，因为这里的主键我们设置的是字符类型
    private String productId;
    //商品名字
    private String productName;
    //商品单价
    private BigDecimal productPrice; //BigDecimal的数据类型就是对应数据库中类型decimal类型
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品图片
    private String productIcon;
    //商品状态 0正常1下架
    private Integer productStatus;
    //商品类型
    private Integer categoryType;


    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public ProductInfo() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }
}
