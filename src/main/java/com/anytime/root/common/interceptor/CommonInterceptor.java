package com.anytime.root.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.anytime.root.common.session.SessionName;

public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	protected Log log = LogFactory.getLog(CommonInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//if(log.isDebugEnabled()) {
		//	log.debug("---------- START ----------");
		//	log.debug("Request URI : " + request.getRequestURI());
		//}
		HttpSession session = request.getSession();		
		if(session.getAttribute(SessionName.ID) == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(
					"<script>"+
					"alert('로그인 먼저 하세요'); location.href='/root/loginView'"+
					"</script>");
			return false; 
		}
		return super.preHandle(request, response, handler);
	}
}
