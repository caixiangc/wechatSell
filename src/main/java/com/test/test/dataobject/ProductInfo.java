package com.test.test.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.test.enums.ProductStatusEnums;
import com.test.test.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品（数据库映射表）
 * @author 浙外吴彦祖
 * @data 2018/2/3 上午10:01
 */
@Entity
@Data
@DynamicInsert //****有一个坑点 就是如果是插入数据 那么必须导入这个注解 否者createTime 会无效，还有数据库字段命名的时候一定要命名成create_time 和 update_time
@DynamicUpdate //让updateTime 和 createTime 自动更新
public class ProductInfo {
    @Id  //虽然是字符类型，但是我们id 还是需要设置的，不然 我们jpa 就找不到主键了
    //@GeneratedValue  这里就不用 设置自增了，因为这里的主键我们设置的是字符类型
    private String productId;
    //商品名字
    private String productName;
    //商品单价
    private BigDecimal productPrice; //BigDecimal的数据类型就是对应数据库中类型decimal类型
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品图片
    private String productIcon; // 这边不文件上传了，只传路径，因为我们这个是支持分布式的，所以要么所有资源都会放到一个（服务器，或者cdn）
    //商品状态 0正常1下架
    private Integer productStatus = ProductStatusEnums.UP.getCode();
    //商品类型
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


    @JsonIgnore  //这个地方别忘记把他忽略了，不然数据库里面要出问题的
    public ProductStatusEnums getProductStatusEnums(){ //方法一定要设置成 public 公有的否则调用不到会报错
        return EnumUtil.getByCode(productStatus,ProductStatusEnums.class); //这边一定要 在那个枚举类里面 implement实现 CodeEnum
        //在 把类当做参数的时候，光泛型 是不管用的，要保持 implement 的数据类型也是相同
    }
}
