package com.test.test.repositorys;

import com.test.test.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/16 下午4:11
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> { //两个参数 一个是数据库实体，第二个是主键类型
    SellerInfo findByOpenid(String openid);
}
