package com.test.test.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午8:42
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    //这里定义主键很重要，就是为了 给后面 save()的两个操作(insert and update)用的
    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;
    private Date updateTime; // **这里数据类型是date 千万别写成 data

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {
    }
}
