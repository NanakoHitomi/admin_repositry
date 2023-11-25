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
<title>todo中身</title>
<style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
</head>
<body>
  	<h1>社員中身</h1>
  	<% String message = (String)request.getAttribute("message"); %>
  	<p><%= message %></p>
  	<p><strong>役職:</strong><%= request.getAttribute("grade_name") %></p>
  	<p><strong>所属:</strong><%= request.getAttribute("department") %></p>
 <%--  	<p><strong>重要度:</strong><%= request.getAttribute("status") %></p>
  	<p><strong>メモ:</strong><%= request.getAttribute("content") %></p> --%>
  	<ul>
  		<li><p><a href="list">戻る</a></p></li>
  		<li><p><a href='edit?id=<%= request.getAttribute("id") %>'>編集</a></p></li>
  		<li><p><a href='destroy?id=<%= request.getAttribute("id") %>'>削除</a></p></li>
  	</ul>
</body>
</html>