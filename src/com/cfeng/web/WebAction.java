package com.cfeng.web;


import com.cfeng.domain.Article;
import com.cfeng.domain.Category;
import com.cfeng.domain.PageBean;
import com.cfeng.domain.User;
import com.cfeng.service.ArticleService;
import com.cfeng.service.UserServer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.List;

public class WebAction extends ActionSupport {
    @Setter
    private UserServer userServer;
    @Setter
    private ArticleService articleService;
    @Setter
    private Integer currPage = 1;
    @Setter
    private Integer parentid;
    @Setter
    private String username=null;
    @Setter
    private String label=null;
    @Setter
    private String title=null;
    public void getPageList() throws IOException {
//        System.out.println(username);
//        System.out.println("---------------------------");
        User user1 = (User) ServletActionContext.getRequest().getSession().getAttribute("curUser");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        if(user1==null){
            detachedCriteria.add(Restrictions.eq("article_type", 1));
        }else{
            detachedCriteria.add(Restrictions.lt("article_type", 3));
        }
        if(parentid!=null){
            List<Category> categories = articleService.getCategory(parentid);
            Object[] cidArrays = new Object[categories.size()];
            for (int i=0;i<categories.size();i++){
                Category category = categories.get(i);
                cidArrays[i]=category.getCid();
//                System.out.println(cidArrays[i]);;
            }
            detachedCriteria.add(Restrictions.in("category.cid",cidArrays));
        }
        if(username!=null){
            User resuser = userServer.findByName(username);
            if(resuser!=null)   detachedCriteria.add(Restrictions.eq("user.id", resuser.getId()));
        }
        if(label!=null){
            detachedCriteria.add(Restrictions.like("article_label","%"+label+"%"));
        }
        if(title!=null){
            detachedCriteria.add(Restrictions.like("article_title","%"+title+"%"));
        }

        PageBean pageBean = articleService.getPageData(detachedCriteria,currPage,5);
//        System.out.println(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"handler", "hibernateLazyInitializer"});
        JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
    }
}