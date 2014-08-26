package com.zzj.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.user.dao.UserDao;
import com.zzj.user.model.Role;
import com.zzj.user.model.Theme;
import com.zzj.user.model.User;
import com.zzj.user.service.UserService;
import com.zzj.utils.MD5;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	public void addUser(User user) throws Exception {
		user.setCreateTime(new Date());
		String userId=userDao.saveUser(user);
		saveRoleUsers("8a14c58246cc950f0146cc9514dd0001",new String[]{userId});
		String mdPwd=MD5.crypt(userId+user.getPassword());
		user.setPassword(mdPwd);
		userDao.updateUser(user);
	}
	
	public void delUser(String userId) throws Exception {
		User user=getUserById(true,userId);
		if(null!=user){
			List<Role> roles=user.getRoleList();
			if(null!=roles&&!roles.isEmpty()){
				for(Role role:roles){
					role.getUserList().remove(user);
					updateRole(role);
				}
			}
			userDao.delUser(user);
		}
	}
	
	public Long userAccountCount(String account) throws Exception {
		User user=new User();
		user.setAccount(account);
		return userCount(user);
	}

	public Long userNameCount(String name) throws Exception {
		User user=new User();
		user.setName(name);
		return userCount(user);
	}
	
	public Long userCount(User user) throws Exception {
		return userDao.userCount(user);
	}
	
	public String saveRole(Role role) throws Exception {
		return userDao.saveRole(role);
	}
	
	public void saveRoleUsers(String roleId,String[] userIds) throws Exception {
		Role role=getRoleById(true,roleId);
		List<User> users=role.getUserList();
		nmz:
		for(String userId:userIds){
			User user=getUserById(false,userId);
			if(null!=users&&!users.isEmpty()){
				for(User u:users){
					if(u.getUserId().equals(userId)){
						continue nmz;
					}
				}
			}
			users.add(user);
		}
		role.setUserList(users);
		updateRole(role);
	}
	
	public void updateRole(Role role) throws Exception {
		userDao.updateRole(role);
	}
	
	public void delRole(String roleId) throws Exception{
		Role role=getRoleById(true, roleId);
		if(null!=role){
			role.setUserList(null);
			userDao.delRole(role);
		}
	}
	
	public List<User> getUserList(boolean findRoles) throws Exception {
		return userDao.userList(findRoles);
	}

	public List<User> getUserList(boolean findRoles,int firstResult, int maxResilts) throws Exception {
		return userDao.userList(findRoles,firstResult, maxResilts);
	}
	
	public Role getRoleById(boolean findUsers,String roleId){
		return userDao.getRoleById(findUsers,roleId);
	}
	
	public User getUserById(boolean findRoles,String userId){
		return userDao.getUserById(findRoles,userId);
	}
	
	public String saveTheme(Theme theme) throws Exception {
		return userDao.saveTheme(theme);
	}
	
	public void delTheme(String themeId) throws Exception {
		Theme theme=findThemeById(themeId);
		userDao.delTheme(theme);
	}
	
	public Theme findThemeById(String themeId) throws Exception{
		return userDao.findThemeById(themeId);
	}
	
	@Override
	public List<Theme> findUserTheme(String userId) throws Exception {
		return userDao.findUserTheme(userId);
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

}
