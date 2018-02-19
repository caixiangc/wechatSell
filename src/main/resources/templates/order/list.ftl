<html>
    <#include "../common/header.ftl">
    <body>
    <div id="wrapper" class="toggled">
        <!--边栏sidebar-->
        <#include "../common/nav.ftl">
        <!--主要内容content-->
        <div id="page-content-wrapper">
            <div class="container"> <!--加上fluid 就可以流动布局-->
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>订单id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list orderDTOPage.content as orderDTO >
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.getOrderStatusEnum().getMsg()}</td>
                        <td>${orderDTO.getPayStatusEnum().getMsg()}</td>
                        <td>${orderDTO.createTime}</td>
                        <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                        <td>
                                <#if orderDTO.getOrderStatusEnum().getMsg() == "新订单" >
                                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                </#if>

                        </td>
                    </tr>
                    </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            <#--分页-->
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                    <#if currentPage lte 1> <#--代表如果currentPage 小于 1-->
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage -1}&size=${size}">上一页</a></li>
                    </#if>

                        <#--<#list ></#list> 是循环遍历会把它里面的东西 全都重复遍历打印一遍-->
                        <#--1..orderDTOPage.getTotalPages() 有时候也能写成 0..orderDTOPage.getTotalPages()，如果这里是从0 开始 那么就算只有1页 他也是显示0，1的，从1页开始就好了-->
                    <#list 1..orderDTOPage.getTotalPages() as index> <#--因为orderDTOPage.getTotalPages()返回的就是数字所以要建立所以序列要写成1..orderDTOPage.getTotalPages()形式-->
                        <#if currentPage==index>
                            <li class="disabled"><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                        <#else> <#--要小心if elseif else 要写在 最外面那个if里面-->
                            <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte orderDTOPage.getTotalPages()> <#--代表如果currentPage 小于 1-->
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage +1}&size=${size}">下一页</a></li>
                    </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>

<#--
<h1></h1>
<#list orderDTOPage.content as orderDTO >
    ${orderDTO.orderId}<br>
</#list>-->
