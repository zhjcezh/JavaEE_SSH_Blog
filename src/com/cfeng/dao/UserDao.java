package com.cfeng.dao;

import com.cfeng.domain.Team;
import com.cfeng.domain.User;

import java.util.List;

public interface UserDao {
    public User getUser(String username, String password);
    public List<User> getAllUser();
    public void save(User resUser);
    public User find(User user);
    public User findByName(String username);
    public List<User> getByTeam(Integer team_id);
    public Team saveTeam(Team team);
    public Team findTeam(Team team);
    public void update(User user);
    public User findById(User user);
}
