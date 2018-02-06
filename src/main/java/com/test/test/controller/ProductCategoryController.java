package com.test.test.controller;

import com.test.test.dataobject.ProductCategory;
import com.test.test.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午10:14
 */
@RestController
public class ProductCategoryController {
    private final static Logger logger = LoggerFactory.getLogger(ProductCategoryController.class); //在写好日志的配置文件后，那么需要监听，就把后面你的需要监听的类写到getLogger后面

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "proGetOne/{categoryId}")
    public ProductCategory findOne(@PathVariable("categoryId") Integer categoryId){
        return categoryService.findOne(categoryId);
    }

    @GetMapping(value ="proGetAll")
    public List<ProductCategory> findAll(){
        return categoryService.findAll();
    }

    @PostMapping(value = "AddOne")
    public ProductCategory AddOne(ProductCategory productCategory){
        // 在如果需要传入多个对象的时候，不必一个一个写参数，只要传个对象就行，浏览器url里面只要穿需要传的但是这边取得时候，名字一定要对应的上（最好和数据库对象里面定义的变量一样），就算为空也没关系，因为我们定义的时候就是Integer对象
        ProductCategory productCategory1 = new ProductCategory(productCategory.getCategoryName(),productCategory.getCategoryType());
        return categoryService.AddOne(productCategory1);
    }

    @GetMapping(value ="findByCategoryTypeIn")
    public List<ProductCategory> findByCategoryTypeIn(){
        List<Integer> cc = Arrays.asList(1,3);
        return categoryService.findByCategoryTypeIn(cc);
    }
}
