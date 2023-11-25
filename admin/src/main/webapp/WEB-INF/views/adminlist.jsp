<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>todoリスト</title>
</head>
<body>
	<h1>Todoリスト</h1>
	<% String message = (String)request.getAttribute("message"); %>
	<p><%= message %></p>
	
	<span><strong>社員番号</strong></span>
	<span><strong>名前</strong></span></br>
	
	<%
	  ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String,String>>)request.getAttribute("rows");
	%>
	
	<%
	  for (HashMap<String, String> columns : rows) {
	%>
	<span><%= columns.get("id") %></span>
	<span><a href='show?id=<%= columns.get("id") %>'><%= columns.get("name") %></a></span><br>
	<% } %>
	<p><a href="new">新規作成</a></p>

</body>
</html>