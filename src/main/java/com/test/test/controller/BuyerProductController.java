package com.test.test.controller;

import com.test.test.VO.ProductInfoVO;
import com.test.test.VO.ProductVO;
import com.test.test.VO.ResultVO;
import com.test.test.dataobject.ProductCategory;
import com.test.test.dataobject.ProductInfo;
import com.test.test.service.CategoryService;
import com.test.test.service.ProductService;
import com.test.test.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 卖家商品，controller 要规范规格
 * @author 浙外吴彦祖
 * @data 2018/2/4 上午8:15
 */
@RestController //因为要返回 json格式 所以要这个注解
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired   //如果这里没有注解 也会报空指针异常
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")   //@GetMapping 后面不能写 分号
    @Cacheable(cacheNames = "product",key = "123")
    //Cacheable这个注解 访问的流程是：会访问list() 方法，然后会有一个ResultVO返回值，然后通过上面给的cacheName 和key，会设置一个redis，redis里面存储的就是返回值的内容
    //如果是CachePut()那么每次都会执行这个方法，并且每次都存储，而Cacheable则 不会每次都执行方法，缓存信息和方法都存储下来了当再次访问的时候不会调用后台程序，从而减小服务器压力。
    public ResultVO list(){
        //1. 查询所有上架商品。
        List<ProductInfo> productInfoList  = productService.findByProductUpStatus();
        //2. 查询类目（一次性查询） 为了 性能提升一次性把类目全都查询出来
        //List<Integer> categoryTypeList = new ArrayList<>(); //定义一组新的 categoryType
        //传统方法 foreach 每次get categoryType
        //for(ProductInfo productInfo: productInfoList){ //(定义一个数据类型 类型方法重命名 : 你要遍历的内容)
        //    categoryTypeList.add(productInfo.getCategoryType()); //因为添加了 @DATA 的注解（import lombok.Data;）所以所有的get set 方法都不用你自己写，在编译的时候会自动生成
        //}
        //精简方法 java8的特性(lambda)
        List<Integer> categoryTypeList = productInfoList.stream() //比如去空格。。。等等 java8完全可以投入生产环境，如果用上述方法可能会参数数据冗余
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        //检测这个集合是否重复
        for (Integer ids : categoryTypeList){
            System.out.println(ids);
        }

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 上面操作的目的就是查询上架商品所有的类目，这里类目是，不能包含重复,步骤就是(1.查找所有上架商品，然后通过遍历把所有出现过的categoryType插入一个集合中，然后通过findByCategoryTypeIn，生成一个包含ProductCategory对象的集合)
        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());


            List<ProductInfoVO> productInfoVOList = new ArrayList<>();  //**** 这个是定义特定集合数组的方式
            for (ProductInfo productInfo : productInfoList){ //****切记 千万不要把 数据库查询操作放到for循环里面去，这样开销是很大的，因为时间复杂度是O(n2),有空可以把优化成O(nlogn)
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //只要在遍历 productInfo的时候，只要type对上了，那么信息加到这个infoVo里面去，又不是全部加进去的
                    //下面这个方法是 把 productInfo这个对象里面的信息全部拷贝到 productVO（两行代码就可以把数据全部拷贝过去完全不需要写多余重复的操作）
                    BeanUtils.copyProperties(productInfo,productInfoVO); //****这个方法比较实用这个方法可以吧一个对象里面的属性值 全部copy到另外一个对象里面去
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO); //一个一个种类 加进productVOList 这个集合
        }


        //因为ResultVO中的data 是个泛型，所以这里是直接可以把productVO 直接塞到resultVO 的data里面去
        //ResultVOUtil resultVOUtil = new ResultVOUtil();
        //resultVOUtil.success(productVOList);
        //如果在类里面定义一个方法，可以直接 类名.方法名  这样返回就是 方法名的返回类型，而不是类的类型
        return ResultVOUtil.success(productVOList); //这不能new 一个ResultVOUtil ，然后把结果塞进去，因为如果这样那么返回类型就是ResultVOUtil而不是ResultVO，虽然让门里面结果是相同的
    }

}
