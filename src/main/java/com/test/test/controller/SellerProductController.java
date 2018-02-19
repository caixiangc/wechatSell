package com.test.test.controller;

import com.test.test.dataobject.ProductCategory;
import com.test.test.dataobject.ProductInfo;
import com.test.test.enums.ResultEnum;
import com.test.test.form.ProductForm;
import com.test.test.repositorys.ProductInfoRepository;
import com.test.test.service.CategoryService;
import com.test.test.service.ProductService;
import com.test.test.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * @author 浙外吴彦祖
 * @data 2018/2/14 下午1:14
 */
@Controller //一定要加 这个注解，外界无法通过url 访问过来
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "3") Integer size,
                             Map<String,Object> map){
        Page<ProductInfo> productInfoPage = productService.findSomePage(page-1,size);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/product/list",map);
    }

    @GetMapping("/onSale")
    public ModelAndView onSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productService.onSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list"); //*****首段一定要加 "/" 代表根目录下 开始，如果不加，那么从上个目录下开始
        }
        map.put("msg", ResultEnum.PRODUCT_UP_SUCCESS.getMsg());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/offSale")
    public ModelAndView offSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productService.offSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
        }
        map.put("msg",ResultEnum.PRODUCT_DOWN_SUCCESS.getMsg());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("product/index",map);
    }

    // 更新 和 新增
    @RequestMapping("/save")
    //@CachePut(cacheNames = "productName",key = "123")
    @CacheEvict(cacheNames = "productName",key = "123") //在方法执行完成之后，会执行这个清除 缓存
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            if(!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = productService.findOne(productForm.getProductId()); //如果productInfo 里面有字段为null 那么也会出错的即使数据库可以为空，在读取的时候回报错
            }else{
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm,productInfo); //如果productInfo中有某些字段 而productForm中 没有，那么这些字段是不会被productForm覆盖掉的
            productService.save(productInfo);
        }catch (Exception e){
            map.put("msg","可能是设置的type重复了请注意"+e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","成功");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
