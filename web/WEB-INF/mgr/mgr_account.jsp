<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css" />
    <link rel="stylesheet" href="../../css/amazeui.min.css" />
    <link rel="stylesheet" href="js/pageStyle.css">
    <script src="../../js/jquery.min.js"></script>
    <style>
        #modal_content_account2{
            padding: 30px 20px;
            width: 400px;
            height: 300px;
            background: white;
            position: fixed;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -100px;
            display: none;
        }
        #close2{
            position: absolute;
            right: 10px;
            top: 10px;
        }
    </style>
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">管理员列表</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加成员</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>用户</li>
        <li>团队名称</li>
        <li>团队编号</li>
        <li>权限等级</li>
        <li>修改信息</li>
        <li>移除成员</li>
    </ul>
    <s:iterator value="userlist" var="user">
        <ul class="list_goods_ul">
            <li><s:property value="#user.id"/></li>
            <li><s:property value="#user.username"/></li>
            <li><s:property value="#user.team.team_name"/></li>
            <li><s:property value="#user.team.team_id"/></li>
            <li><s:property value="#user.type"/></li>
            <li><a href="#" class="updatebtn" data-id="<s:property value="#user.username"/>"><img class="img_icon" src="../../images/edit_icon.png" alt=""></a></li>
            <li><a href="${pageContext.request.contextPath}/user_delete.action?id=<s:property value="#user.id"/>"><img class="img_icon" src="../../images/delete_icon.png" alt=""></a></li>
        </ul>
    </s:iterator>
</div>

    <div id="modal_view">
    </div>

<div id="modal_content_account">
    <div id="close"><img src="../../images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加成员：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>用户名：</span>
                <input type="text" class="am-form-field" id="username">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>密码：</span>
                <input type="password" class="am-form-field" id="password">&nbsp;&nbsp;
            </div>
        </div>
        <s:if test="%{ #session.curUser.id == 1 }">
            <div class="item1">
                <div>
                    <span>团队名称</span>
                    <input type="text" class="am-form-field" id="teamname">&nbsp;&nbsp;
                </div>
            </div>
        </s:if>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="adduser">添加</button>
        </div>
    </div>
</div>

<div id="modal_content_account2">
    <div id="close2"><img src="../../images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加成员：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>用户名：</span>
                <input type="text" class="am-form-field" id="username2">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>密码：</span>
                <input type="password" class="am-form-field" id="password2">&nbsp;&nbsp;
            </div>
        </div>
        <s:if test="%{ #session.curUser.id == 1 }">
            <div class="item1">
                <div>
                    <span>团队编号</span>
                    <input type="text" class="am-form-field" id="teamid2">&nbsp;&nbsp;
                </div>
            </div>
        </s:if>
        <input type="hidden" id="id2">
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="updatebtn">添加</button>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content_account").fadeIn();
        });
        $("#adduser").click(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            var teamname = $("#teamname").val();
            $(window).attr('location','${pageContext.request.contextPath}/user_add.action?username='+username+'&password='+password+'&teamname='+teamname);
        });
        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_account").fadeOut();
        });


        $(".updatebtn").click(function () {
            /*1.当前点的是哪一条数据*/
            var id = $(this).data("id");

            /*取出对应id的数据,到数据库当中查询当前记录
             * 返回给页面
             * 展示到页面当中.
             * 发送请求的时候,不需要页面的跳转 Ajax(前端技术)   能够取当前返回数据  展示到界面
             * */
            $.post("${pageContext.request.contextPath}/user_updateUI.action",{"username":id},function (data) {
                console.log("-------json------");
                console.log(data);
                /*把json数据展示到文本框 */
                $("#username2").val(data[0].username);
                $("#password2").val(data[0].password);
                $("#id2").val(data[0].id);
                // $("#teamname2").val(data[0].team.team_name);
            },"json");

            /*2.把修改界面弹出*/
            $("#modal_view").fadeIn();
            $("#modal_content_account2").fadeIn();
        });

        $("#close2").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_account2").fadeOut();
        });

        $("#updatebtn").click(function () {
            /*1.获取文本框值*/
            var username2 = $("#username2").val();
            var password2 = $("#password2").val();
            var teamid2 = $("#teamid2").val();
            var id2 = $("#id2").val();
            if(teamid2==undefined)
                $(window).attr('location','${pageContext.request.contextPath}/user_update.action?username='+username2+'&password='+password2+'&id='+id2);
            else{
                $(window).attr('location','${pageContext.request.contextPath}/user_update.action?username='+username2+'&password='+password2+'&id='+id2+'&teamid='+teamid2);
            }
            /*2.发送请求*/


        });
    });
</script>
</body>
</html>