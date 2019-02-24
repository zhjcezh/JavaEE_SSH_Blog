package com.cfeng.web;

import com.cfeng.domain.Article;
import com.cfeng.domain.Category;
import com.cfeng.domain.PageBean;
import com.cfeng.domain.User;
import com.cfeng.service.ArticleService;
import com.cfeng.service.UserServer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class ArticleAction extends ActionSupport implements ModelDriven<Article> {
    @Setter
    private UserServer userServer;
    private Article article = new Article();
    @Override
    public Article getModel() {
        return article;
    }
    @Setter
    private ArticleService articleService;
    public String list(){
//        System.out.println("list---------------");
        List<Article> allArticle = articleService.getAllArticle();
//        System.out.println(allArticle);
        ActionContext.getContext().getValueStack().set("allArticle",allArticle);
        return "list";
    }

     @Setter
     private Integer currPage = 1;
     @Setter
     private String keyWord;
     @Setter
     private Integer id;
     public String pageList(){
//         System.out.println(currPage);
//         System.out.println(keyWord);
//         System.out.println(id);
//         return null;
         User user1 = (User) ServletActionContext.getRequest().getSession().getAttribute("curUser");
         user1 = userServer.find(user1);
         Integer type = user1.getType();
         DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
         if(keyWord != null){
             detachedCriteria.add(Restrictions.like("article_title","%"+keyWord+"%"));
         }
         if(type==3){
             detachedCriteria.add(Restrictions.eq("user.id", user1.getId()));
         }else if (type==2){
             List<User> userlist = userServer.getByTeam(user1.getTeam().getTeam_id());
             System.out.println(userlist);
             Object[] userList = new Object[userlist.size()];
             for (int i=0;i<userlist.size();i++){
                 User user2 = userlist.get(i);
                 userList[i]=user2.getId();
             }
             System.out.println(userList);
             detachedCriteria.add(Restrictions.in("user.id",userList));
             detachedCriteria.add(Restrictions.lt("article_type",3 ));
         }
         PageBean pageBean = articleService.getPageData(detachedCriteria,currPage,5);
         System.out.println(pageBean);
         ActionContext.getContext().getValueStack().push(pageBean);
         return "list";
     }
    public String delete(){
        Article article2 = new Article();
        article2.setArticle_id(article.getArticle_id());
        articleService.delete(article2);
        return "listres";
    }

    @Setter
    private Integer parentid;
    public  String getCategory() throws IOException {
//        System.out.println(parentid);
        List<Category> list = articleService.getCategory(parentid);
//        System.out.println(list);
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
        System.out.println(jsonArray);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

        //添加文章
        /**
         * 文件上传提供的三个属性:
         */
        @Setter
        private String uploadFileName; // 文件名称
        @Setter
        private File upload; // 上传文件
        @Setter
        private String uploadContentType; // 文件类型
    public String add() throws IOException {
//            System.out.println(article);
//            return null;
            if(upload != null){
                int index = uploadFileName.lastIndexOf(".");
                String etx = uploadFileName.substring(index);
                String uuid = UUID.randomUUID().toString();
                String uuidFileName = uuid.replace("-", "") + etx;
                System.out.println(uuidFileName);

                String path = ServletActionContext.getServletContext().getRealPath("/upload");
                File file = new File(path);
                if(!file.exists()){
                    file.mkdirs();
                }
                File desFile = new File(path + "/" + uuidFileName);
                FileUtils.copyFile(upload,desFile);
                article.setArticle_pic(uuidFileName);
            }
            article.setArticle_time(new Date().getTime());
//            System.out.println(article);
            articleService.save(article);
        return "listres";
    }

    public String edit(){
//        System.out.println(article.getArticle_id());
        Article resarticle = articleService.getOneArticle(article.getArticle_id());
//        System.out.println(resarticle);
        ActionContext.getContext().getValueStack().push(resarticle);
         return  "edit";
    }

    public String update() throws IOException {
//        System.out.println(article);
        if(upload != null){
            String path = ServletActionContext.getServletContext().getRealPath("/upload");
            String picname = article.getArticle_pic();
            if(picname != null || "".equals(picname)){
                File file = new File(path + picname);
                file.delete();
            }
            int index = uploadFileName.lastIndexOf(".");
            String etx = uploadFileName.substring(index);
            String uuid = UUID.randomUUID().toString();
            String uuidFileName = uuid.replace("-", "") + etx;
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            File desFile = new File(path + "/" + uuidFileName);
            FileUtils.copyFile(upload,desFile);
            article.setArticle_pic(uuidFileName);
        }
        article.setArticle_time(System.currentTimeMillis());
        articleService.update(article);
        return "listres";
    }

    public String detail(){
        Article article1 = articleService.getOneArticle(article.getArticle_id());
        ActionContext.getContext().getValueStack().push(article1);
        return "detail";
    }

}
