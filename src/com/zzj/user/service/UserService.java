package com.zzj.user.service;

import java.util.List;

import com.zzj.user.model.Role;
import com.zzj.user.model.Theme;
import com.zzj.user.model.User;

public interface UserService {
	/**
	 * 新增用户
	 * @param user		用户信息
	 * @throws Exception
	 */
	void addUser(User user) throws Exception;
	/**
	 * 删除用户
	 * @param userId		用户ID
	 * @throws Exception
	 */
	void delUser(String userId) throws Exception;
	/**
	 * 根据条件查询用户条数
	 * @param user			查询条件
	 * @return
	 * @throws Exception
	 */
	Long userCount(User user) throws Exception;
	/**
	 * 根据用户账号查询用户数量
	 * @param account		用户账号
	 * @return
	 * @throws Exception
	 */
	Long userAccountCount(String account) throws Exception;
	/**
	 * 根据用户名称查询用户数量
	 * @param name			用户名称
	 * @return
	 * @throws Exception
	 */
	Long userNameCount(String name) throws Exception;
	/**
	 * 新增角色
	 * @param role			角色信息
	 * @return
	 * @throws Exception
	 */
	String saveRole(Role role) throws Exception;
	/**
	 * 在角色下新增用户
	 * @param roleId		角色id
	 * @param userIds		用户id集合
	 * @throws Exception
	 */
	void saveRoleUsers(String roleId,String[] userIds) throws Exception;
	/**
	 * 修改角色信息
	 * @param role		角色信息
	 * @throws Exception
	 */
	void updateRole(Role role) throws Exception;
	/**
	 * 删除角色
	 * @param roleId		角色id
	 * @throws Exception
	 */
	void delRole(String roleId) throws Exception;
	/**
	 * 查询所有用户集合
	 * @param findRoles		是否查询用户所在角色
	 * @return
	 * @throws Exception
	 */
	List<User> getUserList(boolean findRoles) throws Exception;
	/**
	 * 分页查询用户集合
	 * @param findRoles		是否查询用户所在角色
	 * @param firstResult	开始页码
	 * @param maxResilts	结束页码
	 * @return
	 * @throws Exception
	 */
	List<User> getUserList(boolean findRoles,int firstResult,int maxResilts) throws Exception;
	/**
	 * 根据id查询角色信息
	 * @param findUsers		是否查询角色下的用户
	 * @param roleId		角色id
	 * @return
	 * @throws Exception
	 */
	Role getRoleById(boolean findUsers,String roleId) throws Exception;
	/**
	 * 根据id查询用户信息
	 * @param findRoles		是否查询用户下的角色
	 * @param userId		用户id
	 * @return
	 * @throws Exception
	 */
	User getUserById(boolean findRoles,String userId) throws Exception;
	/**
	 * 保存主题
	 * @param theme		主题信息
	 * @return
	 * @throws Exception
	 */
	String saveTheme(Theme theme) throws Exception;
	/**
	 * 删除主题
	 * @throws Exception
	 */
	void delTheme(String themeId) throws Exception;
	/**
	 * 根据id查询主题
	 * @param themeId
	 * @return
	 * @throws Exception
	 */
	Theme findThemeById(String themeId) throws Exception;
	/**
	 * 根据用户id查询主题列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Theme> findUserTheme(String userId) throws Exception;
	
}
