package com.test.test.controller;

import com.test.test.dataobject.ProductInfo;
import com.test.test.repositorys.ProductInfoRepository;
import com.test.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/3 上午10:31
 */
@RestController
public class ProductInfoController {
    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private ProductService productService;

    //找出所有在架商品信息
    @GetMapping(value = "findByProductUpStatus")
    public List<ProductInfo> findByProductStatus(){
        return productService.findByProductUpStatus();
    }

    //找出一条分类信息
    @GetMapping(value = "findOneProductInfo/{productId}")
    public ProductInfo findOneProductInfo(@PathVariable("productId") String productId){
        return productService.findOne(productId);
    }


    //增加一条商品信息，每个字段都要填
    @PostMapping(value = "addProductInfo")
    public ProductInfo addProductInfo(ProductInfo productInfo){

        return productService.addProductInfo(productInfo);
    }
    //找出所有商品 靠 商品状态，商品状态是参数 0是上架，1是下架
    @GetMapping(value = "findByProductStatus/{ProductStatus}")
    public List<ProductInfo> findByProductStatus(@PathVariable("ProductStatus") Integer ProductStatus){
        return productService.findByCategoryStatusByparams(ProductStatus);
    }

    //实现查询的分页，用到了pageable ，pageable是一个抽象的接口，并不是一个实现的类
    @GetMapping(value = "getOnePage")
    public Page<ProductInfo> findPageAll(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return productService.findSomePage(page,size);
    }
}
