package com.cfeng.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer type;
    private Team team;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", team=" + team +
                '}';
    }
}
