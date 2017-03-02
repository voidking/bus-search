<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

  <title>公交查询后台管理</title>

  <!-- Bootstrap core CSS -->
  <link href="${basePath}/public/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${basePath}/public/css/dashboard.css" rel="stylesheet">
  <link href="${basePath}/public/css/manage.css" rel="stylesheet">
</head>

<body>

  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">公交查询后台管理</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="javascript:void(0);">${admin.username}</a></li>
          <li><a href="javascript:void(0);" id="logout">退出</a></li>
        </ul>
        <!--
        <form class="navbar-form navbar-right">
          <input type="text" class="form-control" placeholder="Search...">
        </form>
        -->
      </div>
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-sidebar">
          <li ><a href="${basePath}/Manage">管理路线</a></li>
          <li class="active"><a href="${basePath}/AddLinePage">添加路线</a></li>
        </ul>
      </div>
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h1 class="page-header">添加路线</h1>
        <div class="info">
          <p>
            提示：路线简称、路线全称、首站、尾站的数据，请和百度地图保持一致，否则无法进行路线查询。
          </p>
        </div>
        <div class="table-responsive">
          <form action="" class="line-form" role="form">
            <div class="form-group">
              <input type="text" id="busName" name="busName" class="form-control" placeholder="路线简称">
            </div>
            <div class="form-group">
              <input type="text" id="fullName" name="fullName" class="form-control" placeholder="路线全称">
            </div>
            <div class="form-group">
              <input type="text" id="firstStop" name="firstStop" class="form-control" placeholder="首站">
            </div>
            <div class="form-group">
              <input type="text" id="lastStop" name="lastStop" class="form-control" placeholder="尾站">
            </div>
            <button type="submit" class="btn btn-default btn-primary">确认添加</button>
          </form>
        </div>
      </div>
    </div>
  </div>

<input id="basePath" type="hidden" value="${basePath}">
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script> 
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/js/adminlogout.js"></script>
<script>
$(function(){
  $('.line-form').submit(function(event) {
    event.preventDefault();
    var data = {
      busName: $('#busName').val(),
      fullName: $('#fullName').val(),
      firstStop: $('#firstStop').val(),
      lastStop: $('#lastStop').val()
    }
    var basePath = $('#basePath').val();
    $.ajax({
      url: basePath+'/AddLine',
      type: 'POST',
      dataType: 'json',
      data: data,
      success: function(data){
        console.log(data);
        if(data.code == 0){
          layer.msg('添加成功');
          setTimeout(function(){
            window.location.href = basePath+'/Manage';
          },1500);
        }else{
          layer.msg(data.ext);
        }
      },
      error: function(xhr){
        console.log(xhr);
      }
    });
    
  });
});
</script>
</body>
</html>
