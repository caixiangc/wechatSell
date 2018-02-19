<html>
    <head>
        <meta charset="utf-8">
        <title>error</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="alert alert-dismissable alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4>
                        注意!
                    </h4> <strong>错误!</strong> ${msg}. <a href="#" class="alert-link">3s后自定跳转</a>
                </div>
            </div>
        </div>
    </div>
    <script>
        setTimeout('location.href="${url}"',3000); //setTimeout 还能这么用
    </script>
    </body>
</html>