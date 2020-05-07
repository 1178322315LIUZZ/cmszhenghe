package com.liuzhenzhen.cms.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuzhenzhen.cms.entity.User;
import com.liuzhenzhen.cms.service.UserService;
import com.liuzhenzhen.cms.util.CMSException;
import com.liuzhenzhen.cms.util.Result;
@RequestMapping("passport")
@Controller
public class PassportController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("reg")
	public String reg() {
		return "passport/reg";
	}
	@RequestMapping("login")
	@ResponseBody
	public Result<User> login(User user){
		Result<User> result=  new Result<User>();
		try {
			 if(userService.login(user)>0) {//如果注册成功
				 result.setCode(200);//成功
				 result.setMsg("注册成功");
			 }
		} catch (CMSException e) {//如果是自定义异常
			 e.printStackTrace();
			 result.setCode(300);//注册失败
			 result.setMsg("注册失败:"+e.getMessage());
			
		}catch (Exception e) {//其他异常
			e.printStackTrace();//把异常消息在控制台打印，以便程序员找BUG
			 result.setCode(500);//注册失败,不可预知的异常
			 result.setMsg("注册失败，系统出现不可预知异常，请联系管理员");//给用户看的
		}
		return result;
	}
	@RequestMapping("deng")
	public String deng(HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		if(user!=null) {
			return "redirect:/";
		}else {
			return "passport/login";
		}
		
	}
	@PostMapping("denglu")
	@ResponseBody
	public Result<User> login(User user,Model model,HttpSession session) {
		Result<User> result=  new Result<User>();
		try {
			//去登录，如果成功则返回用户的基本信息 
			User us = userService.denglu(user);
			if(null != user) {
				result.setCode(200);
				result.setMsg("登录成功");
				session.setAttribute("user", us);//登录成功，把用户信息存入session
			}
		} catch (CMSException e) {//如果是自定义异常
			 e.printStackTrace();
			 result.setCode(300);//登录失败
			 result.setMsg("登录失败:"+e.getMessage());
			
		}catch (Exception e) {//其他异常
			e.printStackTrace();//把异常消息在控制台打印，以便程序员找BUG
			 result.setCode(500);//登录失败,不可预知的异常
			 result.setMsg("登录失败，系统出现不可预知异常，请联系管理员");//给用户看的
		}
		return result;
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@PostMapping("admindenglu")
	@ResponseBody
	public Result<User> adminlogin(User user,Model model,HttpSession session) {
		Result<User> result=  new Result<User>();
		try {
			//去登录，如果成功则返回用户的基本信息 
			user.setRole(1);
			User us = userService.admindenglu(user);
			if(null != user) {
				result.setCode(200);
				result.setMsg("登录成功");
				session.setAttribute("admin", us);//登录成功，把用户信息存入session
			}
		} catch (CMSException e) {//如果是自定义异常
			 e.printStackTrace();
			 result.setCode(300);//登录失败
			 result.setMsg("登录失败:"+e.getMessage());
			
		}catch (Exception e) {//其他异常
			e.printStackTrace();//把异常消息在控制台打印，以便程序员找BUG
			 result.setCode(500);//登录失败,不可预知的异常
			 result.setMsg("登录失败，系统出现不可预知异常，请联系管理员");//给用户看的
		}
		return result;
	}
}
