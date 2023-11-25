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
<title>タスク編集</title>
<style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
</head>
<body>
	<h1>タスク編集</h1>
	<% String message = (String)request.getAttribute("message"); %>
	<p><%= message %></p>
	<form action="update" method="get">
		<input type="hidden" name="id" value='<%= request.getAttribute("id") %>'>
		<label for="title">タイトル</label><br>
		<input type="text" name="title" value='<%= request.getAttribute("title") %>'><br>
		<br>
		<label for="duedata">締切</label><br>
		<input type="data" name="duedata" value='<%= request.getAttribute("duedata") %>'><br>
		<br>
		<label>重要度<input type="radio" name="status" value="high">高</label>
		<label><input type="radio" name="status" value="middle">中</label>
		<label><input type="radio" name="status" value="low">低</label><br>
		<p></p>
		<label for="content">メモ</label><br>
        <textarea name="content" id="" cols="30" rows="10"><%= request.getAttribute("content") %></textarea>
        <P></P>
        <button type="submit">保存する</button>
        <a href='show?id=<%= request.getAttribute("id") %>
        <button type="submit"><a href='list'>キャンセル</a></button>
     </form>
 <ul>
 		<li><p><a href='list'>戻る</a></p></li>
 </ul>
</body>
</html>