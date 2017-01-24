<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${basePath}/public/css/home.css">
	<title>公交查询</title>
</head>
<body>
	<div class="header">
		<h1>公交查询</h1>
	</div>
	<div class="container">
		<div class="search">
			<input class="input" id="key" type="text" placeholder="公交路线">
			<input class="btn" id="search" type="button" value="搜索">
		</div>
		<div class="content">
			<ul>
				<#list lineList as line>
				<a href="${basePath}/Direction?busName=${line.busName}">
					<li>${line.fullName}(${line.firstStop}-${line.lastStop})</li>
				</a>
				</#list>
			</ul>
		</div>
	</div>
	<div class="footer">
		
	</div>
<input id="basePath" type="hidden" value="${basePath}">
<script src="${basePath}/public/lib/jquery/jquery.min.js"></script>
<script src="${basePath}/public/lib/art-template/dist/template.js"></script>
<script src="${basePath}/public/js/home.js"></script>
<script id="line-template" type="text/html">
<ul>
    {{each lineList as line}}
    <a href="${basePath}/Direction?busName={{line.busName}}">
		<li>{{line.fullName}}({{line.firstStop}}-{{line.lastStop}})</li>
	</a>
    {{/each}}
</ul>
</script>
</body>
</html>