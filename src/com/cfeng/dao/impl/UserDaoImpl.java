package com.cfeng.dao.impl;

import com.cfeng.dao.UserDao;
import com.cfeng.domain.Team;
import com.cfeng.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Override
    public User getUser(String username, String password) {
//        System.out.println("dao"+username+password);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("username",username));
        detachedCriteria.add(Restrictions.eq("password",password));
        detachedCriteria.add(Restrictions.isNotNull("type"));
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
//        System.out.println(list);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public void save(User resUser) {
        System.out.println(resUser);
        this.getHibernateTemplate().save(resUser);
    }

    @Override
    public User find(User user) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("username",user.getUsername()));
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
//        System.out.println(list);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public User findByName(String username) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("username",username));
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
//        System.out.println(list);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<User> getByTeam(Integer team_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("team.team_id",team_id));
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public Team saveTeam(Team team) {
        this.getHibernateTemplate().save(team);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Team.class);
        detachedCriteria.add(Restrictions.eq("team_name",team.getTeam_name()));
        List<Team> list = (List<Team>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Team findTeam(Team team) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Team.class);
        detachedCriteria.add(Restrictions.eq("team_id",team.getTeam_id()));
        List<Team> list = (List<Team>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void update(User user) {
        this.getHibernateTemplate().update(user);
    }

    @Override
    public User findById(User user) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("id",user.getId()));
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
//        System.out.println(list);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }


}
