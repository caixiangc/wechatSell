package com.test.test.repositorys;

import com.test.test.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午10:28
 */
public interface ProductRepository extends JpaRepository<ProductCategory,Integer> {
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIntList);  //这里只是仓库然后申明方法，这样才能调用
}
