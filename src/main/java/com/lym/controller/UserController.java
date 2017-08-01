package com.lym.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lym.dictionary.StringMapper;
import com.lym.entity.User;
import com.lym.service.UserService;
import com.lym.utils.StringUtil;
import com.lym.vo.BaseVo;
import com.lym.vo.UserSingleVo;

@Controller
public class UserController {

	@Autowired
	private HttpServletRequest request;// 这些私有变量是放在ThreadLocal中的，所以这么写是线程安全的
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public String register() {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		BaseVo baseVo = new BaseVo();

		if(StringUtil.isEmpty(username, password)) {
			baseVo.setStatus(StringMapper.FAILURE_STATUS);
			baseVo.setMessage("arguments can't be null");
			
			return JSON.toJSONString(baseVo);
		}
		
		try {
			userService.createUser(username, password);
			baseVo.setMessage("register success");
			baseVo.setStatus(StringMapper.SUCCESS_STATUS);
			
			return JSON.toJSONString(baseVo);
		} catch (Exception e) {
			baseVo.setMessage("register failed, " + e.toString());
			baseVo.setStatus(StringMapper.FAILURE_STATUS);
			
			return JSON.toJSONString(baseVo);
		}
		
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	@ResponseBody
	public String login() {
		String username = request.getParameter("username");
		
		UserSingleVo userSingleVo = new UserSingleVo();
		
		if(StringUtil.isEmpty(username)) {
			userSingleVo.setStatus(StringMapper.FAILURE_STATUS);
			userSingleVo.setMessage("arguments can't be null");
			
			return JSON.toJSONString(userSingleVo);
		}
		
		try {
			User user = userService.findUserByUsername(username);
			if(user != null) {
				userSingleVo.setStatus(StringMapper.SUCCESS_STATUS);
				userSingleVo.setUser(user);
				return JSON.toJSONString(userSingleVo);
			}
			
			userSingleVo.setStatus(StringMapper.FAILURE_STATUS);
			userSingleVo.setMessage("username is not correct");
			return JSON.toJSONString(userSingleVo);
		} catch (Exception e) {
			userSingleVo.setStatus(StringMapper.FAILURE_STATUS);
			userSingleVo.setMessage(e.toString());
			return JSON.toJSONString(userSingleVo);
		}
	}
	
}
