package com.liuzhenzhen.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Object object = session.getAttribute("admin");
		if(object!=null) {
			return true;
		}else {
			session.setAttribute("err", "请登录后进行管理");
			response.sendRedirect("view/passport/adminlogin.jsp");
			return false;
		}
	}
}
