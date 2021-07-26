package com.anytime.root.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.anytime.root.common.session.SessionName;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		if(session.getAttribute(SessionName.ID) == null) {
			response.setContentType("text/html; charset=utf-8");
			out.print(
					"<script>"+
					"alert('로그인 먼저 하세요'); location.href='/root/loginView'"+
					"</script>");
			return false; 
		}
		
		String auth = (String)session.getAttribute(SessionName.AUTH);
		if(auth.equals("false")) {
			response.setContentType("text/html; charset=utf-8");
			out.print(
					"<script>"+
					"alert('권한이 없습니다. 관리자에게 문의하세요.'); history.back();"+
					"</script>");
			return false; 
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
