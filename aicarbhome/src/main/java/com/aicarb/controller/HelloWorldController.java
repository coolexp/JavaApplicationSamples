package com.aicarb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aicarb.vo.User;
@Controller
public class HelloWorldController {
	private String message = "Welcome to Spring MVC!";
	@RequestMapping("/hello")
	public ModelAndView showMessage(
		@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		
		List<User> users = new ArrayList<User>();
		for(int i=0;i<10;i++){
			User u = new User();
			users.add(u);
			u.setName("S_"+i);
			u.setSex("Sex_"+i);
		}
		mv.addObject("users", users);
		return mv;
	}
}