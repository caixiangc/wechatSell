package com.test.test.service;

import com.test.test.dataobject.ProductCategory;

import java.util.List;

/**
 * 商品类目
 * @author 浙外吴彦祖
 * @data 2018/2/3 上午11:24
 */
public interface CategoryService {
    //找出一个商品
    ProductCategory findOne(Integer categoryId);
    //找出所有商品
    List<ProductCategory> findAll();
    //查找同类目的商品
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    //商品种类的新增
    ProductCategory AddOne(ProductCategory productCategory);

    //和修改(新增) todo 商品种类的修改
    ProductCategory save(ProductCategory productCategory);
}
