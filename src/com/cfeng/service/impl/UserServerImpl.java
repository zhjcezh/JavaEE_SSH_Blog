package com.cfeng.service.impl;

import com.cfeng.dao.UserDao;
import com.cfeng.domain.Team;
import com.cfeng.domain.User;
import com.cfeng.service.UserServer;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServerImpl implements UserServer {
    @Setter
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {
        List<User> list = userDao.getAllUser();
        return list;
    }

    @Override
    public void save(User resUser) {
        userDao.save(resUser);
    }

    @Override
    public User find(User user) {
        User user1 = userDao.find(user);
        return user1;
    }

    @Override
    public User findByName(String username) {
        User user1 = userDao.findByName(username);
        return user1;
    }

    @Override
    public List<User> getByTeam(Integer team_id) {
        List<User> list = userDao.getByTeam(team_id);
        return list;
    }

    @Override
    public Team saveTeam(Team team) {
        team = userDao.saveTeam(team);
        return team;
    }

    @Override
    public Team findTeam(Team team) {
        team = userDao.findTeam(team);
        return team;
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findById(User user) {
        user = userDao.findById(user);
        return user;
    }


}
