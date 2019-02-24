<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="header.jsp"%>
<section class="layout main-wrap  content">
    <section class='col-main'>
        <p class="tips">温馨提示：为了您的体验更佳，请在PC端使用</p>
        <article class="mainarea" style="display:block;">
            <div class="blog-tab">
                <div class="tab-content" >
                    <div role="tabpanel" style="margin-left: 100px;" class="tab-pane fade in active" id="home">

                        <div id="lk_blog_list" style="padding-left: 20px; width: 1000px" class="container">
                            <div class="row">
                                <ul class="blog-list">
                                    <li>
                                        <div class="blog-list-left">
                                            <div class="main-title"><s:property value="article_title"/> </div>
                                            <div style="color: #c2c2c2"><s:date name="article_time" format="yyyy-MM-dd"/></div>
                                            <hr>
                                            <div id="content">
                                                <s:property value="article_content" escapeHtml="false"/>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
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

</body>
</html>