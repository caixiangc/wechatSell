package com.test.test.controller;

import com.test.test.dataobject.ProductCategory;
import com.test.test.exception.SellException;
import com.test.test.form.CategoryForm;
import com.test.test.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/15 下午10:38
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("category/list",map);
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,Map<String,Object> map){
        if(!StringUtils.isEmpty(categoryId)){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }
        return new ModelAndView("category/index",map); //ModelAndView 这个参数就是 templates 文件夹下的目录
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error",map);
        }

        ProductCategory productCategory = new ProductCategory();

        try {
            if (!StringUtils.isEmpty(categoryForm.getCategoryId())){
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryService.save(productCategory);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg","成功");
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
