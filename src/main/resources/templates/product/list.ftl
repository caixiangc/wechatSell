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
                                <th>商品id</th>
                                <th>名称</th>
                                <th>图片</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productInfoPage.content as productInfo >
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td><img width="100" height="100" src="${productInfo.productIcon}" alt=""></td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                <td>

                                <#if productInfo.getProductStatusEnums().getMessage() == "在架" >
                                    <a href="/sell/seller/product/offSale?productId=${productInfo.productId}">下架</a>
                                <#else>
                                    <a href="/sell/seller/product/onSale?productId=${productInfo.productId}">上架</a>
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
                        <li><a href="/sell/seller/product/list?page=${currentPage -1}&size=${size}">上一页</a></li>
                    </#if>

                        <#--<#list ></#list> 是循环遍历会把它里面的东西 全都重复遍历打印一遍-->
                        <#--1..orderDTOPage.getTotalPages() 有时候也能写成 0..orderDTOPage.getTotalPages()，如果这里是从0 开始 那么就算只有1页 他也是显示0，1的，从1页开始就好了-->
                    <#list 1..productInfoPage.getTotalPages() as index> <#--因为orderDTOPage.getTotalPages()返回的就是数字所以要建立所以序列要写成1..orderDTOPage.getTotalPages()形式-->
                        <#if currentPage==index>
                            <li class="disabled"><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                        <#else> <#--要小心if elseif else 要写在 最外面那个if里面-->
                            <li><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte productInfoPage.getTotalPages()> <#--代表如果currentPage 小于 1-->
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/product/list?page=${currentPage +1}&size=${size}">下一页</a></li>
                    </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>