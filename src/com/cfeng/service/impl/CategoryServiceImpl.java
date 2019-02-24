package com.cfeng.service.impl;

import com.cfeng.dao.CategoryDao;
import com.cfeng.domain.Category;
import com.cfeng.service.CategoryService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Setter
    private CategoryDao categoryDao;
    @Override
    public void save(Category category) {
//        System.out.println("调用service");
        categoryDao.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> list = categoryDao.getAllCategory();
        return list;
    }

    @Override
    public Category getOneCategory(Integer cid) {
        //调用dao层
        Category category = categoryDao.getOneCategory(cid);
        return category;
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }


}
