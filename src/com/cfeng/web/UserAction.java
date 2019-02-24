package com.cfeng.web;

import com.cfeng.domain.Team;
import com.cfeng.domain.User;
import com.cfeng.service.LoginService;
import com.cfeng.service.UserServer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    @Setter
    private UserServer userServer;
    @Setter
    private LoginService loginService;
    private User user = new User();
    @Setter
    private String teamname;
    @Override
    public User getModel() {
        return user;
    }

    public String list() {
//        System.out.println(user+"---------------------------------");
        user = userServer.find(user);
        List<User> list = null;
        if (user.getType() > 2) {
            return ERROR;
        }
        else if(user.getType() == 2){
            list = userServer.getByTeam(user.getTeam().getTeam_id());
        }
        else{
            list = userServer.getAllUser();
        }
//        System.out.println(list);
        ActionContext.getContext().getValueStack().set("userlist", list);
        return "list";
    }
    public String list1() {
        User user1 = (User) ServletActionContext.getRequest().getSession().getAttribute("curUser");
        user1=userServer.find(user1);
        List<User> list = null;
        if(user1.getType() == 2){
            list = userServer.getByTeam(user1.getTeam().getTeam_id());
        }
        else{
            list = userServer.getAllUser();
        }
//        System.out.println(list);
        ActionContext.getContext().getValueStack().set("userlist", list);
        return "list";
    }
    public String add() {
        User resUser = userServer.find(user);
        User user1 = (User) ServletActionContext.getRequest().getSession().getAttribute("curUser");
        user1=userServer.find(user1);
        if (resUser == null) {
//            System.out.println(user1.getTeam());
            if(user1.getType()==2){
                user.setTeam(user1.getTeam());
                user.setType(3);
            }
            else if(user1.getType()==1){
                Team team = new Team();
                team.setTeam_name(teamname);
                team = userServer.saveTeam(team);
                user.setTeam(team);
                user.setType(2);
//                Team team = userServer.creatTeam();
            }
            userServer.save(user);
        }else{
            this.addActionError("创建相同用户");
            return ERROR;
        }
        return "listAction";
    }
    public String updateUI() throws IOException {
        User user1 = userServer.find(user);
        user1.setTeam(null);
        JSONArray jsonArray = JSONArray.fromObject(user1, new JsonConfig());
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }
    @Setter
    private Integer teamid;
    public String update(){
        System.out.println("-----------------------------------------------------");
        User user1 = userServer.findById(user);
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        System.out.println(teamid);
        if(teamid!=null){
            Team team = new Team();
            team.setTeam_id(teamid);
            team = userServer.findTeam(team);
//        System.out.println(team);
            user1.setTeam(team);
        }
        userServer.update(user1);
        return "listAction";
    }
    public String delete(){
        user = userServer.findById(user);
        user.setType(null);
        userServer.update(user);
        return "listAction";
    }
}
