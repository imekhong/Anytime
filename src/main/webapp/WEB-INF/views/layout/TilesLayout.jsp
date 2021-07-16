<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AnyTime</title>
</head>
<body>
	<header id="siteTop">
		<tiles:insertAttribute name="siteTop"/>
	</header>

	<div id="siteBody">
		<tiles:insertAttribute name="siteBody"/>
	</div>
 
	<footer id="siteBottom">
		<tiles:insertAttribute name="siteBottom"/>
	</footer>
</body>
</html>