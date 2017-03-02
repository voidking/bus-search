<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${basePath}/public/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/public/lib/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${basePath}/public/css/login.css">
    <title>管理员登录</title>
</head>
<body>
    <div class="container">
        <form class="form-reg">
            <h2 class="form-signin-heading">管理员登录</h2>
            <input id="username" type="text" class="form-control" placeholder="请输入管理员用户名" required="" autofocus="">
            <input id="password" type="password" class="form-control" placeholder="请输入密码" required="">
            <button id="confirm" class="btn btn-lg btn-primary btn-block" type="button">登录</button>
        </form>
    </div>
<input id="basePath" type="hidden" value="${basePath}">
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/js/adminlogin.js"></script>
</body>
</html>