package com.test.test.repositorys;

import com.test.test.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/3 上午10:26
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus); //找到那些商品状态，0位正常上架，1为下架
}
