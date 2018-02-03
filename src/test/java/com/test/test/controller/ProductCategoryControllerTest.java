package com.test.test.controller;

import com.test.test.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午10:38
 */
public class ProductCategoryControllerTest {
    @Autowired
    private ProductCategoryController productCategoryController;

    @Test
    public void getAll() {
        //ProductCategory productCategory = productCategoryController.getAll();
        //Assert.assertNotEquals(0,productCategory);
        //return ;
    }
}