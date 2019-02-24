<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/css/style.css"
          type="text/css" />

</head>
<body>
    <h1>你不是管理员 hhhhhhhhh 辣鸡 点不进去 hhhhhhhhhh </h1>
    <h1>也有可能您是尊敬的管理员老爷，但是给小弟添加账号的时候，写错了点东西</h1>
</body>
</html>
