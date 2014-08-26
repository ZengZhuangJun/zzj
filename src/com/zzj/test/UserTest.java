package com.zzj.test;

import java.util.ArrayList;
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
public class UserTest {
	
	private static HTLogger log = HTLogger.getLogger(UserTest.class);
	private UserService userService;
//	@BeforeClass 
//	 public static void init() { 
//		 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
//		 userService = (UserService) context.getBean("userService",UserService.class); 
//	 } 
	@Test
	public void findUser() throws Exception{
		List<User> userList=userService.getUserList(false);
		for(User u:userList){
			System.out.println(u.getName());
//			System.out.println(u.getRoleList().size());
		}
	}
	
	@Test
	public void getUserById() throws Exception{
		User user=userService.getUserById(true,"8a14c58246c74be20146c74be80d0001");
		System.out.println(user.getName());
		System.out.println(user.getRoleList().get(0).getRoleName());
	}
	
	@Test
	public void saveUser() throws Exception {
		for(int i=1;i<11;i++){
			User user=new User();
			user.setAccount("zengzhj"+i);
			user.setPassword("1234567890");
			user.setName("zzj"+i);
			userService.addUser(user);
		}
	}
	
	@Test
	public void delUser() throws Exception{
		userService.delUser("8a14c58246c7b7bc0146c7b7c18e0001");
	}
	
	@Test
	public void saveRole() throws Exception{
		Role role=new Role();
		role.setRoleCode("default");
		role.setRoleName("默认角色");
//		User user=userService.getUserById(false,"8a14c58246c74be20146c74be80d0001");
//		System.out.println(user.getName());
//		List<User> userList=new ArrayList<User>();
//		userList.add(user);
		List<User> userList=userService.getUserList(false);
		role.setUserList(userList);
		userService.saveRole(role);
	}
	
	@Test
	public void getRoleById() throws Exception{
		Role role=userService.getRoleById(true,"8a14c58246c759450146c7594a040001");
		System.out.println(role.getRoleName());
	}
	
	@Test
	public void saveRoleUsers() throws Exception{
		userService.saveRoleUsers("8a14c58246c759450146c7594a040001", new String[]{"8a14c58246c7b7bc0146c7b7c18e0001"});
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
