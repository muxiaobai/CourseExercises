<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    
    
    目前对于Comet的实现方式有三种，基于长轮询(long polling)、基于iframe“、基于流(stream)三种实现comet的方式。这里我们将采用iframe的方式来编写一个web版的聊天室来测试Comet的效果
  	<a href="http://blog.csdn.net/cyxlzzs/article/details/8609965" target="_black">测试</a>  
 	<a href="http://10.1.48.121:8089/chat.jsp?name=jack" target="_black">jack</a>
 	<a href="http://10.1.48.121:8089/chat.jsp?name=lily" target="_black">lily</a>
 	
  </body>
</html>
