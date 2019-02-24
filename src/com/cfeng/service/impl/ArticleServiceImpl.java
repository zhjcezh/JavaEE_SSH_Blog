package com.cfeng.service.impl;

import com.cfeng.dao.ArticleDao;
import com.cfeng.domain.Article;
import com.cfeng.domain.Category;
import com.cfeng.domain.PageBean;
import com.cfeng.service.ArticleService;
import lombok.Setter;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Setter
    private ArticleDao articleDao;
    @Override
    public List<Article> getAllArticle() {
        List<Article> allArticle = articleDao.getAllArticle();
        return allArticle;
    }

    @Override
    public PageBean getPageData(DetachedCriteria detachedCriteria, Integer currPage, int pageSize) {
        PageBean<Article> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = articleDao.getTotalCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(pageBean.getTotalPage());
        List<Article> list =  articleDao.getPageData(detachedCriteria,pageBean.getIndex(),pageBean.getPageSize());
        pageBean.setList(list);
//        System.out.println(pageBean);
         return pageBean;
    }

    @Override
    public void delete(Article article) {
        articleDao.delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {
        List<Category> list =  articleDao.getCategory(parentid);
        return list;
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public Article getOneArticle(Integer article_id) {

        Article resArticle = articleDao.getOneArticle(article_id);
        return resArticle;
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }


}
