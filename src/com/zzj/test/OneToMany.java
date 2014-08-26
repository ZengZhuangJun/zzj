package com.zzj.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.user.model.Theme;
import com.zzj.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:applicationContext-resource.xml"})
public class OneToMany {
	private UserService userService;
	
	@Test
	public void findUserTheme() throws Exception{
		List<Theme> themes=userService.findUserTheme("8a14c58246ccc1720146ccc1784e0002");
		for(Theme t:themes){
			System.out.println(t.getTitle());
		}
	}
	
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
