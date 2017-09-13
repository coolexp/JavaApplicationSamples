package com.aicarb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aicarb.vo.User;
import com.alibaba.fastjson.JSON;

@RestController
public class HTMLController {
	@RequestMapping("/hi")
	@ResponseBody
	public String hi(){
		User user = new User();
		user.setName("sloppy");
//		JSON.parseObject(jsonString, user.class);
		return JSON.toJSONString(user);
	}
}
