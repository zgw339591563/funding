package com.atguigu.atcrowdfunding.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.common.Member;
import com.atguigu.atcrowdfunding.common.User;
import com.atguigu.atcrowdfunding.util.Const;

/**
 * 权限拦截器 对用户发送的请求进行权限的控制 1) 如果用户的请求路径在合法的权限路径中，那么请求继续执行 2）
 * 如果用户的请求路径不在合法的权限路径中，那么需要跳转到错误页面
 * 
 * @author 18801
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getServletPath();

		// 白名单
		List<String> whiteUrls = new ArrayList<String>();
		String contextPath = request.getContextPath();
		whiteUrls.add("/index.htm");
		whiteUrls.add("/login.htm");
		whiteUrls.add("/dologin.do");
		whiteUrls.add("/doMemberLogin.do");
		if (whiteUrls.contains(uri)) {
			return true;
		}
		// 获取当前会话对象
		HttpSession session = request.getSession();

		// 从会话对象中获取用户登录信息
		User loginUser = (User) session.getAttribute(Const.LOGIN_USER);
		Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		if (loginUser == null && loginMember == null) {
			// 如果没有登陆，那么跳转回到登陆页面
			response.sendRedirect(contextPath + "/login.htm");
			return false;
		}

		// 从应用对象中获取系统中需要进行权限控制的路径集合

		ServletContext servletContext = request.getSession().getServletContext();
		Set<String> uriSet = (Set<String>) servletContext.getAttribute("uriSet");

		if (uriSet.contains(uri)) {
			// 权限控制
			Set<String> authUris = (Set<String>) session.getAttribute(Const.AUTH_URIS);

			if (authUris.contains(uri)) {
				// 合法访问
				return true;
			} else {
				// 非法访问
				response.sendRedirect(contextPath + "/error.htm");
				return false;
			}
		}
		return true;
	}
}
