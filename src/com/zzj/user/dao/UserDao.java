package com.zzj.user.dao;

import java.util.List;

import com.zzj.user.model.Role;
import com.zzj.user.model.Theme;
import com.zzj.user.model.User;

public interface UserDao {
	
	String saveUser(User user);
	
	void delUser(User user);
	
	void updateUser(User user);
	
	Long userCount(User user);
	
	String saveRole(Role role);
	
	void updateRole(Role role);
	
	void delRole(Role role);
	
	List<User> userList(boolean findRoles);
	
	List<User> userList(boolean findRoles,int firstResult,int maxResilts);
	
	Role getRoleById(boolean findUsers,String roleId);
	
	User getUserById(boolean findRoles,String userId);
	
	String saveTheme(Theme theme);
	
	void delTheme(Theme theme);
	
	Theme findThemeById(String themeId);
	
	List<Theme> findUserTheme(String userId);
	
}
