package com.test.test.service.impl;

import com.test.test.dataobject.ProductInfo;
import com.test.test.dto.CartDTO;
import com.test.test.enums.ProductStatusEnums;
import com.test.test.enums.ResultEnum;
import com.test.test.exception.SellException;
import com.test.test.repositorys.ProductInfoRepository;
import com.test.test.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        return productInfoPage;
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

    @Override
    @Transactional
    public void addStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());

            if(productInfo.getProductId() == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if(cartDTO.getProductId() == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){   //todo 如果当用户量很大的时候，这里涉及了多线程， 和超卖的问题，到时候会讲 互锁机制来避免这个问题
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            repository.save(productInfo); //注：若是更改(update)，entity中必须设置了主键字段，不然不能对应上数据库中的记录，变成新增（数据库自动生成主键）或报错（数据库不自动生成主键）了
        }

    }

    @Override
    public ProductInfo onSale(String orderId) {
        ProductInfo productInfo = repository.findOne(orderId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        productInfo.setProductStatus(ProductStatusEnums.UP.getCode());
        repository.save(productInfo);
        return null;
    }

    @Override
    public ProductInfo offSale(String orderId) {
        ProductInfo productInfo = repository.findOne(orderId);
        productInfo.setProductStatus(ProductStatusEnums.DOWN.getCode());
        repository.save(productInfo);
        return null;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
