<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${basePath}/public/css/direction.css">
	<title>线路方向</title>
</head>
<body>
	<div class="header">
		<h1>线路方向</h1>
	</div>
	<div class="container">
		<div class="info">
			请选择(${line.fullName})行车方向
		</div>
		<div class="content">
			<ul>
				<a href="${basePath}/Stop?busName=${line.busName}&firstStop=${line.firstStop}">
					<li>
						<p>${line.firstStop}->${line.lastStop}</p>
						<!-- <p>05:00至23:00</p> -->
					</li>
				</a>
				<a href="${basePath}/Stop?busName=${line.busName}&firstStop=${line.lastStop}">
					<li>
						<p>${line.lastStop}->${line.firstStop}</p>
						<!-- <p>05:00至00:00</p> -->
					</li>
				</a>
			</ul>
		</div>
	</div>
</body>
</html>