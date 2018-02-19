<html>
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <!--边栏sidebar-->
        <#include "../common/nav.ftl">
    <!--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save"> <!--表单里面的action 会把这个表单里面的数据打包传过去，对面只要定义一个数据类型 包含这些数据来接收就行了-->

                        <div class="form-group">
                            <label>类目名称</label>
                            <input type="text" name="categoryName" class="form-control" value="${(productCategory.categoryName)!''}" />
                        </div>
                        <div class="form-group">
                            <label>类目编号</label>
                            <input type="number" name="categoryType" class="form-control" value="${(productCategory.categoryType)!''}" />
                        </div>


                        <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}"> <!--hidden是bootstrap的 css类-->
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>