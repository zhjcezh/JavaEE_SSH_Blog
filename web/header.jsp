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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>Cfeng的啦啦啦啦啦啦啦啦</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" href="${ctx }/css/amazeui.min.css" />
    <link rel="stylesheet" href="js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/lkblog.css">
    <link rel="stylesheet" href="css/blog_con.css">
    <link rel="stylesheet" href="${ctx }/css/pageStyle.css">
    <script src="${ctx}/js/jquery.min.js"></script>
    <script src="${ctx}/js/paging.js"></script>
    <style>
        .admin_top{
            height: 70px;
            background:white;
            text-align: right;
            padding-right: 70px;
            color: black;
            position: relative;
        }

        .admin_top span{
            position: absolute;
            right: 10px;
            top: 30px;

        }
        .admin_top img{
            margin-top: 20px;
        }
        .admin_top .top_left{
            width: 250px;
            height: 70px;
            background:white;
            float: left;
            color: darkblue;
            font-weight: bold;
            text-align: center;
            padding-top: 20px;
            font-size: 20px;

        }



    </style>
</head>

<body>
<!--头-->
<div class="admin_top">
    <div class="top_left">
        博客系统
    </div>
    <div class="h_top_right">
        <s:if test="%{ #session.curUser.id != null }">
        <span style="margin-right: 50px"><s:property value="#session.curUser.username"/></span>
        <span>
            <a href="${pageContext.request.contextPath}/loginAciton_loginout.action"
               style="color:
        lightseagreen" target="_parent">退出</a>
        </span>
        </s:if>
        <s:else>
            <span>
            <a href="${ctx}/mgr_login.jsp" style="color:
        lightseagreen" target="_parent">登陆</a>
            </span>
        </s:else>
    </div>

</div>
<!--导航-->
<nav class="navbar navbar-default navbar-lk">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <a class="navbar-brand" href="index.html" style="margin-left: 180px">

            </a>

            <ul class="nav navbar-nav" id="nav" style="font-size: 20px">
                <li class="active"><a href="index.jsp">博客首页</a></li>
            </ul>
            <a href="${ctx}/loginAciton_admin.action" style="line-height:95px; height: 95px;font-size: 18px; color: #0a628f">
                发布文章
            </a>
        </div>
    </div>
</nav>
<!--banner-->
<section id="lk_blog_one">
    <img src="images/about_java.png" alt="" class="one-img" width="280">
    <div class="one-right">
        <h1>XX团队内部经验分享blog</h1>
        <span>喜欢他,就来扫码吧!</span>
        <div style="position: relative;" class="one-bottom">
            <button>一杯咖啡就好</button>
            <img src="images/ewm.jpg" alt="" width="100" class="one-ewm">
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-3" style="margin-top: 40px">
        <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field" placeholder="作者" id="input_search1" style="width: 127px">
            <input type="text" class="am-form-field" placeholder="附属标签" id="input_search2" style="width: 127px">
            <input type="text" class="am-form-field" placeholder="题目" id="input_search3" style="width: 127px">
            <span class="am-input-group-btn">
                <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
            </span>
        </div>
    </div>
</section>

<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/article_getCategory.action",{"parentid":0},function (data) {
            $(data).each(function (i,obj) {
               $("#nav").append("<li class='active'><a href='index.jsp?parentid="+obj.cid+"'>"+ obj.cname +"</a></li>");
            });
        },"json");
        $("#input_search_btn").click(function () {
            var username = $("#input_search1").val();
            var label = $("#input_search2").val();
            var title = $("#input_search3").val();
            $(window).attr('location','/index.jsp?username='+username+"&label="+label+"&title="+title);
        });
    });

</script>
