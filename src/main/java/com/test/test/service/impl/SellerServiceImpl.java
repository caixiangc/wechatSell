package com.test.test.service.impl;

import com.test.test.dataobject.SellerInfo;
import com.test.test.repositorys.SellerInfoRepository;
import com.test.test.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/16 下午6:16
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByopenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
