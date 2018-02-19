package com.test.test.service;

import com.test.test.dataobject.SellerInfo;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/16 下午6:11
 */
public interface SellerService {
    SellerInfo findSellerInfoByopenid(String openid);
}

