package com.test.test.controller;

import com.test.test.controller.test.GirlController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午8:29
 */
public class GirlControllerTest {

    @Autowired
    private GirlController girlController;
    @Test
    public void findAllGirl() {
        Assert.assertNotNull(girlController.findAllGirl());
    }
}