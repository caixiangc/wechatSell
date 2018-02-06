package com.test.test.service.impl;

import com.test.test.dataobject.ProductCategory;
import com.test.test.repositorys.ProductRepository;
import com.test.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/3 下午10:50
 */
@Service  //一定要加上service 的类才能实现 service 申明的方法
public class ProductCategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productRepository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        //List<Integer> cc = Arrays.asList(1,3);
        return productRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory AddOne(ProductCategory productCategory) {
        // 在如果需要传入多个对象的时候，不必一个一个写参数，只要传个对象就行，浏览器url里面只要穿需要传的但是这边取得时候，名字一定要对应的上（最好和数据库对象里面定义的变量一样），就算为空也没关系，因为我们定义的时候就是Integer对象
        ProductCategory productCategory1 = new ProductCategory(productCategory.getCategoryName(),productCategory.getCategoryType());
        return productRepository.save(productCategory1);
    }
}
