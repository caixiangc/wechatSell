package com.test.test.service.impl;

import com.test.test.dataobject.ProductInfo;
import com.test.test.enums.ProductStatusEnums;
import com.test.test.repositorys.ProductInfoRepository;
import com.test.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/3 下午1:19
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findByProductUpStatus() {
        return repository.findByProductStatus(ProductStatusEnums.UP.getCode());
    }

    @Override
    public List<ProductInfo> findByCategoryStatusByparams(Integer status) {
        return repository.findByProductStatus(status);
    }

    @Override
    public Page<ProductInfo> findSomePage(Integer page, Integer size) {
        //这里之所以不用pageable 是因为 PageRequest 继承于 AbstractPageRequest，而AbstractPageRequest又是由pageable 实现
        PageRequest pageRequest = new PageRequest(page,size); //这里表示 第0页，每页内容2个，具体实现类里面有解释
        Page<ProductInfo> productInfoPage = repository.findAll(pageRequest);   //Page对象也就是Page<ProductInfo>数据类型 都有这些实用的getTotalElements(),getTotalPages()方法

        return repository.findAll(pageRequest);
    }


    @Override
    public ProductInfo addProductInfo(ProductInfo productInfo) {
        //如果数据库设置不为空的话，那么你每条数据都要写进去。
        ProductInfo productInfo1 = new ProductInfo();

        productInfo1.setProductId(productInfo.getProductId());
        productInfo1.setProductName(productInfo.getProductName());
        productInfo1.setProductPrice(productInfo.getProductPrice());
        productInfo1.setProductStock(productInfo.getProductStock());
        productInfo1.setProductDescription(productInfo.getProductDescription());
        productInfo1.setProductIcon(productInfo.getProductIcon());
        productInfo1.setProductStatus(productInfo.getProductStatus());
        productInfo1.setCategoryType(productInfo.getCategoryType());

        return repository.save(productInfo1);
    }
}
