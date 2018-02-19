package com.test.test.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 数据库对应的实体类
 * @author 浙外吴彦祖
 * @data 2018/2/16 下午4:06
 */
@Entity // 因为是 实体类，所以要 加上这个注解
@Data // 加上这个注解就不用谢 get set 了
@DynamicInsert //****有一个坑点 就是如果是插入数据 那么必须导入这个注解 否者createTime 会无效，还有数据库字段命名的时候一定要命名成create_time 和 update_time
@DynamicUpdate //让updateTime 和 createTime 自动更新
//No identifier specified for entity: com.test.test.dataobject.SellerInfo 这个报错是数据库映射对象中没有 没有定义主键 @Id 定义好了 就行了
public class SellerInfo {
    @Id
    private String sellerId; //这里定义的变量名一定要对应数据库里面的字段
    private String username; // 有'_' 这个符号 那么要把它 转变成大写的。
    private String password;
    private String openid;
    //创建和修改 时间 数据库里面设置了 自增 和 加了 @DynastUpdate.. 那个注解所以这里先不写
}
