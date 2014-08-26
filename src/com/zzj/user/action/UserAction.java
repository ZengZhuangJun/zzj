package com.zzj.user.action;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zzj.user.model.Role;
import com.zzj.user.model.User;
import com.zzj.user.service.UserService;
import com.zzj.utils.BaseAction;
import com.zzj.utils.HTLogger;

@Controller("userAction") 
@Scope("prototype") 
public class UserAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 160050538550855520L;
	
	private static HTLogger log = HTLogger.getLogger(UserAction.class);
	
	private User user;
	private UserService userService;
	
	public String register(){
		if(null==user){
			message="未接受到要注册的信息";
			error(message);
			return ERROR;
		}
		try {
			long count=userService.userAccountCount(user.getAccount());
			if(count>0){
				message="账号: "+user.getAccount()+" 已被注册";
				return "register";
			}
		} catch (Exception e1) {
			message="查询账号是否已被注册失败";
			error(message+e1);
			return ERROR;
		}
		try {
			long count=userService.userNameCount(user.getName());
			if(count>0){
				message="昵称: "+user.getName()+" 已被注册";
				return "register";
			}
		} catch (Exception e1) {
			message="查询昵称是否被已注册失败";
			error(message+e1);
			return ERROR;
		}
		try {
			userService.addUser(user);
		} catch (Exception e) {
			message="保存信息失败";
			error(message+e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String saveRole() throws Exception{
		Role role=new Role();
		role.setRoleCode("admin");
		role.setRoleName("管理员");
		List<User> users=userService.getUserList(false);
		role.setUserList(users);
		userService.saveRole(role);
		return SUCCESS;
	}
	
	public String users(){
		
		return SUCCESS;
	}
	
	
	public void debug(Object message){
		log.debug(message);
	}
	public void error(Object message){
		log.error(message);
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
