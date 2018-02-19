package com.test.test.service;

import com.test.test.dataobject.ProductInfo;
import com.test.test.dto.CartDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品
 * @author 浙外吴彦祖
 * @data 2018/2/3 上午11:24
 */
public interface ProductService {
    //查询单个产品信息
    ProductInfo findOne(String productId);

    //查询所有在货产品信息
    List<ProductInfo> findByProductUpStatus();

    //查询所有产品信息 因为有分页的所以这里要有一个pageable信息
    Page<ProductInfo> findSomePage(Integer page, Integer size); //返回时一个page 对象（是集合，里面是ProductInfo数据类型）

    //增加商品
    ProductInfo addProductInfo(ProductInfo productInfo);

    List<ProductInfo> findByCategoryStatusByparams(Integer status);

    //加库存
    void addStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String orderId);
    //下架
    ProductInfo offSale(String orderId);

    ProductInfo save(ProductInfo productInfo);

}
