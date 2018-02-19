package com.test.test.dataobject.mapper;

import com.test.test.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/16 下午8:20
 */
public interface ProductCategoryMapper {
    // 使用注解的方式 就是 直接写sql 语句
    @Insert("insert into product_category(product_name,product_type) values(#{product_name，jdbc=VARCHAR},#{product_type，jdbc=INTEGER})")
    int insertByMap(Map<String,Object> map) //（1成功 0 失败）所以返回类型是int 类型，因为插入参数一个string 一个int 所以索性插入 一个map就好了,后面定义的方法 是给我们后面自己调用用的
    ;

    @Insert("insert into product_category(product_name,product_type) values(#{product_name,jdbc=VARCHAR},#{product_type,jdbc=INTERGER})")
    int insertByObject(ProductCategory productCategory);


    @Select("select * from product_category where product_type = #{product_type,jdbc = INTERGER}")
    @Results({ //这个注解表示你想要获取什么结果, 只有这里注解出来的字段才能显示(property 变量要和数据库映射对象里面的变量值相同)
            @Result(column = "category_id",property = "categoryId"), //把数据库里面的字段名（category_type）映射成这个变量(categoryType)，categoryType这个数据类型就是要映射到ProductCategory这个对象上的
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{category_name,jdbc = INTERGER}")
    @Results({ //这个注解表示你想要获取什么结果, 只有这里注解出来的字段才能显示(property 变量要和数据库映射对象里面的变量值相同)
            @Result(column = "category_id",property = "categoryId"), //把数据库里面的字段名（category_type）映射成这个变量(categoryType)，categoryType这个数据类型就是要映射到ProductCategory这个对象上的
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
    })
    List<ProductCategory> findByCategoryName(Integer categoryName); //如果查询出来的结果是多条的 ，那么必须写成一个集合

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")//#{} 里面包含着的是变量名，是从下面updateBycategoryType()参数里面传过来的
    int updateBycategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType); //如果要传多个参数 那么必须加入@Param("")这个注解，如果只有一个参数那么不用加注解

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")//#{} 里面的变量都来自 下面那个参数里面的Object
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_id = #{categoryId}")
    int deleteByCategoryId(Integer categoryId); //这要传一个值就行了

    // 还有种就是用xml来写sql语句的 ，在mapper 中只需要 写这个申明就行了。具体实现要写在xml里面
    ProductCategory selectByCategoryType(Integer categoryType);


}
