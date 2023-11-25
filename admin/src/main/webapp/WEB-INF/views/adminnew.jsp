<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新規タスク</title>
<style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
</head>
<body>
	<h1>新規タスク</h1>
	<% String message = (String)request.getAttribute("message"); %>
	<p><%= message %></p>
	<form action="create" method="get">
		<label for="title">タイトル</label><br>
		<input type="text" name="title" value=''><br>
		<br>
		<label for="duedata">締切</label><br>
		<input type="data" name="duedata" value''><br>
		<br>
		<label>重要度<input type="radio" name="status" value="高">高</label>
		<label><input type="radio" name="status" value="中">中</label>
		<label><input type="radio" name="status" value="低">低</label><br>
		<p></p>
		<label for="content">メモ</label><br>
        <textarea name="content" id="" cols="30" rows="10"></textarea>
        <P></P>
        <button type="submit">保存する</button>
        <button type="submit"><a href='list'>キャンセル</a></button>
     </form>
 <ul>
 		<li><p><a href='list'>戻る</a></p></li>
 </ul>
</body>
</html>