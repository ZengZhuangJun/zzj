package com.zzj.user.dao.impl;


import java.util.List;

import org.apache.tomcat.util.buf.UDecoder;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zzj.user.dao.UserDao;
import com.zzj.user.model.Role;
import com.zzj.user.model.Theme;
import com.zzj.user.model.User;
import com.zzj.utils.BaseDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{

	public String saveUser(User user) {	
		String userId="";
		getSession().save(user);
		userId=user.getUserId();
		return userId;
	}

	public void updateUser(User user) {
		getSession().update(user);
	}
	
	public Long userCount(User user){
		String sql=null;
		Query query = null;
		Long count=0L;
		sql="select count(*) from User u ";
		if(null!=user){
			sql+=" where 1=1";
			if(null!=user.getUserId()){
				sql+=" and u.userId=:userId";
			}
			if(null!=user.getAccount()){
				sql+=" and u.account=:account";
			}
			if(null!=user.getName()){
				sql+=" and u.name=:name";
			}
			query=getSession().createQuery(sql);
			if(null!=user.getUserId()){
				query.setString("userId", user.getUserId());
			}
			if(null!=user.getAccount()){
				query.setString("account", user.getAccount());
			}
			if(null!=user.getName()){
				query.setString("name", user.getName());
			}
			count=(Long) query.uniqueResult();
		}
		return count;
	}

	public String saveRole(Role role) {
		getSession().save(role);
		String roleId=role.getRoleId();
		return roleId;
	}
	
	public void updateRole(Role role) {
		getSession().update(role);
	}
	
	public void delRole(Role role){
		getSession().delete(role);
	}
	
	public List<User> userList(boolean findRoles){
		List<User> users = null;
		Criteria c = getSession().createCriteria(User.class);
		users=(List<User>)c.list();
		if(findRoles&&null!=users&&!users.isEmpty()){
			for(User u:users){
				Hibernate.initialize(u.getRoleList());
			}
		}
		return users;
	}
	
	public List<User> userList(boolean findRoles,int firstResult,int maxResilts){
		List<User> users = null;
		Criteria c = getSession().createCriteria(User.class);
		c.setFirstResult(firstResult);
		c.setMaxResults(maxResilts);
		users=(List<User>)c.list();
		if(findRoles&&null!=users&&!users.isEmpty()){
			for(User u:users){
				Hibernate.initialize(u.getRoleList());
			}
		}
		return users;
	}

	public Role getRoleById(boolean findUsers,String roleId) {
		Role role=null;
		role=(Role) getSession().get(Role.class, roleId);
		if(findUsers&&null!=role){
			Hibernate.initialize(role.getUserList());
		}
		return role;
	}
	
	public User getUserById(boolean findRoles,String userId){
		User user=null;
		user=(User) getSession().get(User.class, userId);
		if(findRoles&&null!=user){
			Hibernate.initialize(user.getRoleList());
		}
		return user;
	}
	
	public void delUser(User user) {
		getSession().delete(user);
	}

	@Override
	public String saveTheme(Theme theme) {
		getSession().save(theme);
		String themeId=theme.getThemeId();
		return themeId;
	}

	@Override
	public void delTheme(Theme theme) {
		getSession().delete(theme);
	}

	@Override
	public Theme findThemeById(String themeId) {
		Theme theme=(Theme) getSession().get(Theme.class, themeId);
		return theme;
	}

	@Override
	public List<Theme> findUserTheme(String userId) {
		String sql=null;
		Query query = null;
		List<Theme> themes=null;
		sql="from Theme t where user.userId=:userId";
		query=getSession().createQuery(sql);
		query.setString("userId", userId);
		themes=query.list();
		return themes;
	}

}
