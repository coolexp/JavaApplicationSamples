package com.aicarb.controller;

import com.aicarb.impl.MessageServiceImpl;
import com.aicarb.impl.UserServiceImpl;
import com.aicarb.vo.ProductVO;

import org.apache.activemq.ActiveMQConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aicarb.vo.User;
import com.alibaba.fastjson.JSON;

@RestController
public class HTMLController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private MessageServiceImpl messageServiceImpl;
	@RequestMapping("/hi")
	@ResponseBody
	public String hi(){
		User user = new User();
		user.setName("sloppy");
		ProductVO product = userServiceImpl.getProductData(7);
		String json = JSON.toJSONString(product);
		//User u = JSON.parseObject(json, User.class);
		String url = ActiveMQConnection.DEFAULT_BROKER_URL;
		return json;
	}
	
	@RequestMapping("/send")
	@ResponseBody
	public String send(){
		User user = new User();
		user.setName("sloppy");
		messageServiceImpl.send(user);
		ProductVO product = userServiceImpl.getProductData(7);
		String json = JSON.toJSONString(product);
		//User u = JSON.parseObject(json, User.class);
		return json;
	}
}
