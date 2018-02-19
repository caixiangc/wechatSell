package com.test.test.dataobject.mapper;

import com.test.test.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/16 下午9:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper; //这里虽然报错但是不影响我们操作

    @Test
    public void insertByMap() throws Exception {
        Map<String,Object> map = new HashMap<>(); //定义一个新的 不重复的hashMap
        map.put("product_name","师哥最爱");
        map.put("product_type",201);
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("师哥最爱");
        productCategory.setCategoryType(201);
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateByObject() throws Exception{
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("师哥最爱");
        productCategory.setCategoryType(202);
        int result = productCategoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoryId() throws Exception{
        int result = productCategoryMapper.deleteByCategoryId(11);
        Assert.assertEquals(1,result);
    }

    @Test
    public void selectByCategoryType() throws Exception{
        ProductCategory productCategory = productCategoryMapper.selectByCategoryType(1);
        Assert.assertEquals(1,productCategory);
    }

}