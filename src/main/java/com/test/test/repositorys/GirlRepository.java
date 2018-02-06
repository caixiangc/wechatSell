package com.test.test.repositorys;

import com.test.test.dataobject.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午7:46
 */
// repository 就是平常说所的DAO层
public interface GirlRepository extends JpaRepository<Girl,Integer>{ //JpaRepository<数据库(class对象),主键(就是你依据的id)>

}
