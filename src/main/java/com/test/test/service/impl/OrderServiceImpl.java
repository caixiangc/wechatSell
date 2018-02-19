package com.test.test.service.impl;

import com.test.test.converter.OrderMaster2OrderDTOConverter;
import com.test.test.dataobject.OrderDetail;
import com.test.test.dataobject.OrderMaster;
import com.test.test.dataobject.ProductInfo;
import com.test.test.dto.CartDTO;
import com.test.test.dto.OrderDTO;
import com.test.test.enums.OrderStatusEnum;
import com.test.test.enums.PayStatusEnum;
import com.test.test.enums.ResultEnum;
import com.test.test.exception.SellException;
import com.test.test.repositorys.OrderDetailRepository;
import com.test.test.repositorys.OrderMasterRepository;
import com.test.test.service.OrderService;
import com.test.test.service.ProductService;
import com.test.test.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/6 下午7:08
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    //orderMaster 中不仅 有地址 电话那些信息， 还有很重要 关联 orderDetail的 orderId
    //orderMaster 和 orderDetail 关系可以这样， 一条orderMaster 信息可以对 多条orderDetail 信息
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public Page<OrderDTO> findOrderList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable); //这里是orderMaster类型的也是数据库的映射表
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());//orderMasterPage是page类型所以要getContent()
        //三个参数：1.List<T> content 2.pageable 3.total  //它的作用是吧List<> 实现成 Page<>
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    // 前端只会传 productId 和 productQuantity
    @Override
    @Transactional //加一个事务，当其中一条失败的时候，就会回滚
    public OrderDTO createOrder(OrderDTO orderDTO){ //这个OrderDTO是新定义对象 在传参数的时候不用传满
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO); //定义一个BigDecimal，初始化为0
        //1.查询商品（价格，数量）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            String c = orderDetail.getProductId();
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId()); //productInfo 这个是null的
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //2.计算总价
            orderAmount = productInfo.getProductPrice() //因为我们只传 两个属性过来，这里productInfo不能改成orderDetail 否者会包空指针异常
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            ////BigDecimal 要用它特定的乘法和特定的加法

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey()); //这两个id 必须要唯一
            orderDetail.setOrderId(orderId); //orderId 应该是整个id 创建的时候就生成了
            BeanUtils.copyProperties(productInfo,orderDetail); //spring 提供的对象拷贝，把productInfo里面属性拷贝到orderDetail 里面去 todo 这部可能会把createtime和updatetime也复制进去
            orderDetailRepository.save(orderDetail);
        }

        //3.写入订单数据库orderMaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster); // 把orderDTO 里面所有的属性全部复制到orderMaster（好处是少些好多set...方法）
        //BeanUtils 进行属性拷贝的时候会把原来的只给覆盖掉 ，所以这里要卸载最前面
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode()); //这两个要重新加，因为orderDTO 未传是 null，而数据库中设置有不能为null
        orderMaster.setPayStatus(PayStatusEnum.NOTPAY.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderId(orderId);
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList = new ArrayList<>(); //定义一个新数组
        cartDTOList = orderDTO.getOrderDetailList()    //任何集合都可以转换为Stream：
                .stream()   // map 就是去遍历，getOrderDetailList可能会有多条信息，map 是可以去遍历数组的形式的
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList()); //collect 收集到List中  ,就是收集到cartDTOList中

        productService.decreaseStock(cartDTOList);
        orderDTO.setOrderId(orderId);
        return orderDTO;
    }

    @Override
    public OrderDTO findOrder(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        List<OrderDetail> orderDetailList = new ArrayList<>();

        orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDetailList)){  //这种情况就是产生了主订单 master 但是没有与之对应的 detail
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIT);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    //findOrderMasterList  是依据openid 来查的
    @Override
    public Page<OrderDTO> findOrderList(String buyerOpenId, Pageable pageable) {
        //这里 pageable 是 要通过PageRequest 来申明一个对象的
        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenId,pageable);
        // 因为page 是一个 分页对象，不是普通的集合 所以 获取里面的内容要getContent()
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasters.getContent()); // getContent()=>Returns the page content as {@link List}.


        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasters.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //取消订单某些状态下的订单才能被取消
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){ //除了新订单其他订单都不能被取消
            log.error("【订单取消】订单状态不正确，orderStatus={},orderId = {}",orderDTO.getOrderStatus(),orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster); //要注意对象拷贝的 顺序
        OrderMaster updateResult = orderMasterRepository.save(orderMaster); //****当save方法映射到DAO的时候，会去查询主键，如果主键存在那么便是修改，如果不存在那么久新增
        if (updateResult == null) {  //jpa 返回的数据类型 就是OrderMasterRepository ，就是我们定义数据库仓库里面的类型
            log.error("【取消订单】更新失败，orderMaster{}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返还库存(库存+1)
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品，orderDTO{}" ,orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = new ArrayList<>();
        cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.addStock(cartDTOList);

        //如果已经支付，那么退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.PAYED.getCode())){
            //todo
        }
        //我们传给前端就是 orderDTO的数据类型，然后通过判断 订单状态就能判别是否取消
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】订单状态不正确 orderId = {},orderStatus = {}",orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {  //jpa 返回的数据类型 就是OrderMasterRepository ，就是我们定义数据库仓库里面的类型
            log.error("【完结订单】更新失败，orderMaster{}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO payOrder(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单支付完成]订单状态异常 orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.PAYED.getCode())){
            log.error("[订单支付完成]订单支付状态不正确 orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.PAYED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {  //jpa 返回的数据类型 就是OrderMasterRepository ，就是我们定义数据库仓库里面的类型
            log.error("【完结订单】更新失败，orderMaster{}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
