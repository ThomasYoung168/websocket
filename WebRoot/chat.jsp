<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
	
		var username = '${sessionScope.username}';
		var ws; // 一个ws对象就是一个通信管道！！！
		var target = "ws://127.0.0.1:8080/chat/chatSocket?username=" + username; //  
	
		window.onload = function(){
			
			if('WebSocket' in window){
				ws = new WebSocket(target);
			}else if('MozWebSocket' in window){
				ws = new MozWebSocket(target);
			}else{
				alert("当前浏览器不支持websocket！");
				return;
			}
			
			ws.onmessage = function(event){
				
				eval("var msg=" + event.data + ";");
				
				if(undefined!=msg.welcome){
					$("#content").append(msg.welcome + "<br/>");
				}
				
				if(undefined!=msg.usernames){
					$(msg.names).each(function(){
						$("#userList").append(this+"<br/>")
					});
				}
			}
		}
	</script>
  </head>
  
  <body>
    <div id="content"></div>
    <div id="userList" style="border: 1px solid black; width:100px; height: 400px; float: left;"></div>
  </body>
</html>
