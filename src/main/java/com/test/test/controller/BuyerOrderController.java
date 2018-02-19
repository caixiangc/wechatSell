package com.test.test.controller;

import com.test.test.VO.ResultVO;
import com.test.test.converter.OrderForm2OrderDTOConverter;
import com.test.test.dataobject.OrderDetail;
import com.test.test.dataobject.OrderMaster;
import com.test.test.dto.OrderDTO;
import com.test.test.enums.ResultEnum;
import com.test.test.exception.SellException;
import com.test.test.form.OrderForm;
import com.test.test.repositorys.OrderDetailRepository;
import com.test.test.service.BuyerService;
import com.test.test.service.OrderService;
import com.test.test.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/8 下午10:00
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){ //***这里就需要传 OrderForm，格式的数据过来，在传数据参数的时候，数据名字要严格依据OrderForm 里面定义的
        if(bindingResult.hasErrors()){
            log.error("[创建订单]参数不正确，orderForm = {}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.conver(orderForm); // 把orderForm对象转化成orderDTO（里面有很多一些对象是null的，如果调用错对象就会包空指针)***这里主要的目的是 处理 string-》json的转换通过Gson
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单]购物车不能为空 items={}",orderDTO.getOrderDetailList());
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.createOrder(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId()); //刚刚这里有个空指针 不能orderDTO.getOrderId()

        //由断点 调试 看出，在对象拷贝的 时候，缺一个orderId ，就直接把他return 出来了
        return ResultVOUtil.success(map);
    }
    //订单列表

    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam String openid,@RequestParam Integer page,@RequestParam Integer size){ //返回ResultVO<> 这样一个数据类型，里面data List<OrderDTO>是这样的数据类型
        //返回 ResultVO<List<OrderDTO>> 这个数据类型的意思就是 ResultVO<T>它是泛型，而那个T 就是OrderDTO
        if(StringUtils.isEmpty(openid)){
            log.error("[订单列表]openId不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        // 虽然findOrderList()里面要传一个Pageable类型的数据，而我们穿了pageRequest 是因为PageRequest是由Pageable实现的
        Page<OrderDTO> orderDTOPage = orderService.findOrderList(openid,pageRequest); //page 是对数据进行分页的，Page<OrderDTO>就是一页数据里面的内容
        //Page 这个对象里面还有很多属性 不单单有Content() 有时我们还需要其他内容，但是这里我们只需要getContent()
        return ResultVOUtil.success(orderDTOPage.getContent()); //ResultVOUtil是返回格式
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<List<OrderDetail>> detail(@RequestParam String openid,@RequestParam String orderId){ //如果是@RequestParam，那么url地址里面的参数要和这里定义的参数名保持一致，如果这里参数多放的是一个对象，那么要和对象里面的变量保持一致
        if(StringUtils.isEmpty(openid)){
            log.error("[订单详情]openid不存在");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        // todo 不安全的做法，改进 openid 防止越权
        OrderDTO orderDTO = buyerService.findOneOrder(openid, orderId);

        return ResultVOUtil.success(orderDTO);
    }
    //取消订单

    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam String openid,@RequestParam String orderId){
        //OrderDTO orderDTO = orderService.findOrder(orderId);
        //orderService.cancelOrder(orderDTO);

        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);

        return ResultVOUtil.success(); //data 部分直接返回null 就行的
    }
}
