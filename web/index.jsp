<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script src="${ctx}/js/template.js"></script>
<style>
    .content_item {
        height: 160px;
        position: relative;
    }

    .content_item img {
        position: absolute;
        right: 10px;
        top: 10px;
        width: 250px;
        height: 140px;
    }
</style>
<section class="layout main-wrap  content">
    <section class='col-main'>




        <article class="mainarea" style="display:block;">
            <div class="blog-tab">
                <div class="tab-content" id="tab-content">

                </div>
            </div>
        </article>
        <article class="mainarea" style="display:block;">
            <div class="blog-tab">

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="home">

                        <div id="lk_blog_list" class="container">
                            <div class="row">
                                <ul class="blog-list" id="content">


                                </ul>
                                <div id="page" class="page_div"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </section>

</section>
<footer id="lk_footer">
    <div class="container">
        <div class="footer-top">
        </div>
        <div class="footer-bottom col-sm-offset-2 hidden-sm hidden-xs">
            <ul>
                <li><a href="">扫码给钱</a></li>
                <li><a href="">喜加一</a></li>
                <li><a href="">一杯咖啡</a></li>
                <li><a href="">一罐可乐</a></li>
                <li><a href="">一包瓜子</a></li>
                <li><a href="">浙江**大学 计*机学院 软*1*0*班 某某某</a></li>
            </ul>
        </div>
    </div>
</footer>

<script id="mytpl" type="text/html">
    {{each list as value}}
    <li class="content_item">
        <div class="blog-list-left" style="float: left;">
            <div class="main-title">
                <a href="${ctx}/article_detail.action?article_id={{value.article_id}}">{{value.article_title}}</a>
            </div>
            <p class="sub-title">{{value.article_desc}}</p>
            <div class="meta">
                日期： {{value.article_time | dateFormat:'yyyy-MM-dd '}} 作者：{{value.user.username}} 附属标签：{{value.article_label}}
            </div>
        </div>
        <img src="${ctx}/upload/{{value.article_pic}}" alt="" class="img-rounded">
    </li>
    {{/each}}
</script>
<script id="mytpl2" type="text/html">
    <div role="tabpanel" class="tab-pane fade in active" id="tab">
        <div id="lk_blog_two" class="container">
            <div class="row">
                {{each list as value}}
                <button class="btn-tag">{{value.cname}}</button>
                {{/each}}
            </div>
        </div>
    </div>
</script>
<script>
    template.helper('dateFormat', function (date, format) {

        date = new Date(date);

        var map = {
            "M": date.getMonth() + 1, //月份
            "d": date.getDate(), //日
            "h": date.getHours(), //小时
            "m": date.getMinutes(), //分
            "s": date.getSeconds(), //秒
            "q": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds() //毫秒
        };
        format = format.replace(/([yMdhmsqS])+/g, function (all, t) {
            var v = map[t];
            if (v !== undefined) {
                if (all.length > 1) {
                    v = '0' + v;
                    v = v.substr(v.length - 2);
                }
                return v;
            } else if (t === 'y') {
                return (date.getFullYear() + '').substr(4 - all.length);
            }
            return all;
        });
        return format;
    });

    function getParams(key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

    var res = getParams("parentid");
    var username = getParams("username");
    var label = getParams("label");
    var title = getParams("title");
    if (res != null) {
        $.post("${pageContext.request.contextPath}/article_getCategory.action", {"parentid": res}, function (data) {
            var html = template('mytpl2', {list: data});
            $("#tab-content").html(html);
            getPageList(1,res,username,label,title);
        }, "json");
    } else {
        getPageList(1,null,username,label,title);
    }

    function getPageList(curPage,res,username,label,title) {

        $.post("${ctx}/web_getPageList.action", {currPage: curPage,parentid:res,username:username,label:label,title:title}, function (data) {
            var html = template('mytpl', {list: data.list});
            $("#content").html(html);
            $("#page").paging({
                pageNo: data.currentPage,
                totalPage: data.totalPage,
                totalSize: data.totalCount,
                callback: function (num) {
                    getPageList(num,res,username,label,title);
                }
            });
        });
    }
</script>
</body>
</html>