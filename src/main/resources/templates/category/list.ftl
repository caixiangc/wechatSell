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
                            <th>类目id</th>
                            <th>名字</th>
                            <th>type</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list productCategoryList as productCategory > <!--遍历的话 如果是page属性那么后面要跟.content，完成写成productCategoryList.content-->
                            <tr>
                                <td>${productCategory.categoryId}</td>
                                <td>${productCategory.categoryName}</td>
                                <td>${productCategory.categoryType}</td>
                                <td>${productCategory.createTime}</td>
                                <td>${productCategory.updateTime}</td>
                                <td><a href="/sell/seller/category/index?categoryId=${productCategory.categoryId}">修改</a></td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>