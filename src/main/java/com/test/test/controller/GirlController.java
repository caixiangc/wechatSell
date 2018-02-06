package com.test.test.controller;

import com.test.test.dataobject.Girl;
import com.test.test.repositorys.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/2 下午8:06
 */
@RestController
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;

    @GetMapping(value = "/findAlls")
    public List<Girl> findAllGirl(){
        return girlRepository.findAll();
    }
}
