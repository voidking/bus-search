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
          <li class="active"><a href="${basePath}/Manage">管理路线</a></li>
          <li><a href="${basePath}/AddLinePage">添加路线</a></li>
        </ul>
      </div>
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h1 class="page-header">路线管理</h1>
        <div class="search input-group">
          <input type="text" id="key" class="form-control" placeholder="输入路线或首尾站名">
          <span class="input-group-btn">
            <button class="btn btn-default" type="button" id="search">搜索</button>
          </span>
        </div>

        <h2 class="sub-header">路线列表</h2>
        <div class="table-responsive">
          <table class="user-table table table-striped">
            <thead>
              <tr>
                <th>路线简称</th>
                <th>路线全称</th>
                <th>首站</th>
                <th>尾站</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody class="line-tbody">
              <#list lineList as line>
              <tr data-id="${line.id}">
                <td class="busName">${line.busName}</td>
                <td class="fullName">${line.fullName}</td>
                <td class="firstStop">${line.firstStop?default('')}</td>
                <td class="lastStop">${line.lastStop?default('')}</td>
                <td>
                  <!-- <button class="edit btn btn-sm btn-info">修改</button> -->
                  <button class="delete btn btn-sm btn-danger">删除</button>
                </td>
              </tr>
              </#list>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

<input id="basePath" type="hidden" value="${basePath}">
<div class="pane-hide" style="display: none;">
  <div class="pane" data-id="">
    <p>路线简称：<span class="busName">160</span></p>
    <p>路线全称：<span class="username">160路</span></p>
    <p>首站：<span class="firstStop">净月潭</span></p>
    <p>尾站：<span class="lastStop">长春站</span></p>
  </div>
</div>

<script id="line-template" type="text/html">
  {{each lineList as line}}
  <tr data-id="{{line.id}}">
    <td class="busName">{{line.busName}}</td>
    <td class="fullName">{{line.fullName}}</td>
    <td class="firstStop">{{line.firstStop}}</td>
    <td class="lastStop">{{line.lastStop}}</td>
    <td>
      <!-- <button class="edit btn btn-sm btn-info">修改</button> -->
      <button class="delete btn btn-sm btn-danger">删除</button>
    </td>
  </tr>
  {{/each}}
</script>
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script> 
<script src="${basePath}/public/lib/layer/src/layer.js"></script>
<script src="${basePath}/public/js/adminlogout.js"></script>
<script>
$(function(){
  $('.line-tbody').on('click','.edit',function(){
    var index = layer.open({
      type: 1,
      title: '订单详情',
      skin: 'layui-layer-rim', //加上边框
      area: ['500px', '500px'], //宽高
      content: $('.pane-hide').html()
    });
  });
  

  $('.line-tbody').on('click','.delete',function(){
      $tr = $(this).parents('tr');
      var id = $tr.attr('data-id');
      var data = {
          lineId: id
      };
      var index = layer.confirm('确认删除？', {
          btn: ['是的','取消'] //按钮
      }, function(){
          var basePath = $('#basePath').val();
          $.ajax({
              url: basePath + '/DeleteLine',
              type: 'POST',
              dataType: 'json',
              data: data,
              success: function(data){
                  console.log(data);
                  if(data.code == 0){
                    layer.close(index);
                    $tr.remove();
                    layer.msg('删除成功！');
                  }else{
                      layer.msg(data.ext);
                  }
              },
              error: function(xhr){
                  console.log(data);
              }
          });
      }, function(){

      });
  });

  $('#search').click(function(event) {
    var basePath = $('#basePath').val();
    var key = $('#key').val();
    $.ajax({
        url: basePath+'/Search',
        type: 'POST',
        dataType: 'json',
        data: {key: key},
        success: function(data){
            console.log(data);
            var html = template('line-template', data);
            $('.line-tbody').html(html);
        },
        error: function(xhr){
            console.log(xhr);
        }
    });
  });

  $('#key').keypress(function(event) {
    var key = event.which;
    //console.log(key);
    if(key == 13){
        $('#search').trigger('click');
    }
  });
});
</script>
</body>
</html>
