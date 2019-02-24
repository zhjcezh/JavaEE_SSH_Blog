package com.cfeng.web;

import com.cfeng.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("curUser");
        if(user == null){
            ActionSupport action = (ActionSupport)actionInvocation.getAction();
            action.addActionError("你还没有登录,没有权限访问");
            return "login";
        }else{
            return actionInvocation.invoke();
        }
    }
}
