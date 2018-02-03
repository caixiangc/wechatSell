package com.test.test.controller;

import com.test.test.domain.Girl;
import com.test.test.repositorys.GirlRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

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