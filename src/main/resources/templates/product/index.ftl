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
                        <form role="form" method="post" action="/sell/seller/product/save"> <!--表单里面的action 会把这个表单里面的数据打包传过去，对面只要定义一个数据类型 包含这些数据来接收就行了-->
                            <div class="form-group ">
                                <label>名称</label>
                                <input type="text" style="width: 50%" name="productName" class="form-control" value="${(productInfo.productName)!''}" /> <!--如果是字段那么用括号括起来 后面加默认值，如果是对象，那么就不用括号括-->
                            </div>
                            <div class="form-group">
                                <label>价格</label>
                                <input type="text" name="productPrice" class="form-control" value="${(productInfo.productPrice)!''}" />
                            </div>
                            <div class="form-group">
                                <label>库存</label>
                                <input type="number" name="productStock" class="form-control" value="${(productInfo.productStock)!''}" />
                            </div>
                            <div class="form-group">
                                <label>描述</label>
                                <input type="text" name="productDescription" class="form-control" value="${(productInfo.productDescription)!''}" />
                            </div>
                            <div class="form-group">
                                <label>图片</label>
                                <img style="width: 200px;height: 200px" src="${(productInfo.productIcon)!''}" alt="">
                                <input type="text" name="productIcon" class="form-control" value="${(productInfo.productIcon)!''}" />
                            </div>
                            <div class="form-group">
                                <label>类目</label>
                                <select name="categoryType">
                                    <#list productCategoryList as category>
                                        <#if (productInfo.categoryType)??&&category.categoryType == productInfo.categoryType> <!--(productInfo.categoryType)??&& 代表productInfo.categoryType是否存在-->
                                            <option selected value="${category.categoryType}">
                                                ${category.categoryName}
                                            </option>
                                        <#else >
                                            <option value="${category.categoryType}">
                                                ${category.categoryName}
                                            </option>
                                        </#if>

                                    </#list>
                                </select>
                            </div>
                            <input hidden type="text" name="productId" value="${(productInfo.productId)!''}"> <!--hidden是bootstrap的 css类-->
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>