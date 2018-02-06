package com.test.test.repositorys;

import com.test.test.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午11:14
 */
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list = Arrays.asList(3,4,5);

        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        //Assert.assertNotEquals(0,result.size());
        Assert.assertNotSame(0,result.size());
    }
}