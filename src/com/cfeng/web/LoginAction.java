package com.cfeng.web;

import com.cfeng.domain.User;
import com.cfeng.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

public class LoginAction extends ActionSupport implements ModelDriven<User>{
    private User user = new User();
    @Override
    public User getModel() {
        return user;
    }
    private LoginService loginService;
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    public String login(){
//        System.out.println("login来了");
//        System.out.println(user);
        User resUser = loginService.login(user);
        if (resUser == null){
            this.addActionError("用户名或密码错");
            return LOGIN;
        }else {
            ActionContext.getContext().getSession().put("curUser",resUser);
            return SUCCESS;
        }
    }
    public String loginout(){
//        System.out.println("来到了退出");
        ActionContext.getContext().getSession().remove("curUser");
        return "login_out";
    }
    public String admin(){
        User user1 = (User) ServletActionContext.getRequest().getSession().getAttribute("curUser");
        if(user1==null)
            return "login_out";
        return "admin";
    }
}
