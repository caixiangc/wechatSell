package com.test.test.repositorys;

import com.test.test.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
        Assert.assertNotEquals(0,result.size());
    }
}