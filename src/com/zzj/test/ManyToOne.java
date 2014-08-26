package com.zzj.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.user.model.Theme;
import com.zzj.user.model.User;
import com.zzj.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:applicationContext-resource.xml"})
public class ManyToOne {
	private UserService userService;
	@Test
	public void saveTheme() throws Exception{
		User user=new User();
		user.setUserId("8a14c58246ccc1720146ccc1784e0002");
		for(int i=32;i<41;i++){
			Theme theme=new Theme();
			theme.setCreateTime(new Date());
			theme.setTitle("nimeizi"+i);
			String c="你妹子呀你妹子"+i;
			byte[] b=c.getBytes("UTF-8");
			theme.setDescribe(b);
			theme.setUser(user);
			userService.saveTheme(theme);
		}
	}
	
	@Test
	public void delTheme() throws Exception{
		userService.delTheme("8a14c58246ccc3230146ccc328e20001");
	}
	
	@Test
	public void findThemeById() throws Exception{
		Theme theme=userService.findThemeById("8a14c58246ccc3230146ccc328f10002");
		User user=theme.getUser();
		System.out.println(user.getName());
	}
	
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
