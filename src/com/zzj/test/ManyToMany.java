package com.zzj.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.user.model.Role;
import com.zzj.user.model.User;
import com.zzj.user.service.UserService;
import com.zzj.utils.HTLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:applicationContext-resource.xml"})
public class ManyToMany {
	
	private static HTLogger log = HTLogger.getLogger(ManyToMany.class);
	private UserService userService;
	
	/**
	 * 往角色中添加用户
	 * @throws Exception
	 */
	@Test
	public void saveRoleUsers() throws Exception{
		userService.saveRoleUsers("8a14c58246c759450146c7594a040001", new String[]{"8a14c58246c7b7bc0146c7b7c18e0001"});
	}
	@Test
	public void saveUser() throws Exception {
		for(int i=1;i<11;i++){
			User user=new User();
			user.setAccount("hdy"+i);
			user.setPassword("1234567890");
			user.setName("hdy"+i);
			userService.addUser(user);
		}
	}
	/**
	 * 新增角色
	 * @throws Exception
	 */
	@Test
	public void saveRole() throws Exception{
		Role role=new Role();
		role.setRoleCode("default");
		role.setRoleName("默认角色");
		List<User> userList=userService.getUserList(false);
		role.setUserList(userList);
		userService.saveRole(role);
	}
	/**
	 * 根据Id查询角色信息
	 * @throws Exception
	 */
	@Test
	public void getRoleById() throws Exception{
		Role role=userService.getRoleById(true,"8a14c58246cbed0c0146cbed11230001");
		System.out.println(role.getRoleName());
	}
	/**
	 * 删除角色
	 * @throws Exception
	 */
	@Test
	public void delRole() throws Exception{
		userService.delRole("8a14c58246cbefa40146cbefa98e0001");
	}
	/**
	 * 删除用户
	 * @throws Exception
	 */
	@Test
	public void delUser() throws Exception{
		userService.delUser("8a14c58246cbed0c0146cbed11230001");
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void debug(Object message){
		log.debug(message);
	}
	public void error(Object message){
		log.error(message);
	}
}
