package com.test.test.dataobject;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午7:46
 */
@Entity    //把数据库映射成对象的话 还要加@Entity 注解否者报错，就是报错( Error creating bean with name 'girlController':.....)
public class Girl {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String cupSize;
    private Integer age;

    public Girl(String cupSize, Integer age) {
        this.cupSize = cupSize;
        this.age = age;
    }

    public Girl() { //一定要一个空的构造方法否者会报错
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
