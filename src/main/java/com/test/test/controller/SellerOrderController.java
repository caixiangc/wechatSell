package com.test.test.controller;

import com.test.test.dto.OrderDTO;
import com.test.test.enums.ResultEnum;
import com.test.test.service.OrderService;
import com.test.test.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端
 * @author 浙外吴彦祖
 * @data 2018/2/13 上午8:51
 */
@Controller //这里用的是freemark 所以不是添加@RestController 这个注解
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;
    //订单列表，，page是第几页(从第1页开始 ) size是一页有几条数据（查的是主订单(master)）
    @GetMapping("/list")
    public ModelAndView List(@RequestParam(value = "page",defaultValue = "1") Integer page, // 这个page 就是当前冲url 获取的
                             @RequestParam(value = "size",defaultValue = "3") Integer size,
                             Map<String,Object> map){ //ModelAndView是一种数据类型 用的是freeMark（这里自带分页功能）
        PageRequest pageRequest = new PageRequest(page-1,size); //******就是url 里面请求到的 数据，每次a 标签跳转的时候，这边都会获取过来
        Page<OrderDTO> orderDTOPage = orderService.findOrderList(pageRequest); //Page 点进去可以看看方法
        map.put("orderDTOPage",orderDTOPage); // 视图中之所以能够 找出orderDTOPage 是因为，这里put了
        map.put("currentPage",page);  //传参数
        map.put("size",size);  //传参数
        return new ModelAndView("order/list",map); //这里会指定到 那个freemark 模板，模板里面的变量也是从这里区的
    }

    // 取消页面
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,//ModelAndView 返回一个页面
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOrder(orderId);
            orderService.cancelOrder(orderDTO);
        }catch (Exception e){
            log.error("[买家端取消订单]发生异常 e={}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    //订单详情
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOrder(orderId);
        }catch (Exception e){
            log.error("[买家端查询订单详情]发生异常 e={}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOrder(orderId);
            orderService.finishOrder(orderDTO);
        }catch (Exception e){
            log.error("[买家端取消订单]发生异常 e={}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

}
