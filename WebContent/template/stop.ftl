<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#l-map{height:300px;width:100%;}
		#r-result {width:100%;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BC46e2b24290dba4d0267e2430f512fe"></script>
	<title>路线显示</title>
</head>
<body>
	<div id="l-map"></div>
	<div id="r-result"></div>
	<input id="busName" type="hidden" value="${busName}">
	<input id="firstStop" type="hidden" value="${firstStop}">
</body>
</html>
<script type="text/javascript">
	function  init() {
		window.busName = document.getElementById('busName').value;
		window.firstStop = document.getElementById('firstStop').value;
		// console.log(window.busName);
		// console.log(window.firstStop);
	}

	// 百度地图API功能
	var map = new BMap.Map("l-map");            // 创建Map实例
	map.centerAndZoom(new BMap.Point(125.434025,43.83246), 12);

	var busline = new BMap.BusLineSearch(map,{
		renderOptions:{map:map,panel:"r-result"},
			onGetBusListComplete: function(result){
				var directionArr = result.PA;
				console.log(result);
			   	if(result) {
			   		var line = result.getBusListItem(0);
			   		var backLine = result.getBusListItem(1);
			   		console.log(line);
			   		var regx = /\([\u4E00-\u9FA5\uF900-\uFA2D]*-/;
			   		var str = line.name;
			   		console.log(str);
					var rs = str.match(regx)[0];
					rs = rs.slice(1,-1);
					console.log(rs);
			   		if(rs == window.firstStop){
			   			busline.getBusLine(line);
			   		}else{
			   			busline.getBusLine(backLine);
			   		}
			   	}
			}
	});

	function busSearch(){
		busline.getBusList(window.busName);
		//console.log(busline);
	}

	init();
	setTimeout(function(){
		busSearch();
	},1500);
</script>
