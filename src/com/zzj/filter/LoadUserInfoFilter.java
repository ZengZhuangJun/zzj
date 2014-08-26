package com.zzj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;

public class LoadUserInfoFilter extends ActionSupport implements Filter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -144159638821956181L;
	private static final String LOADED_USER_ID = "USER_ID";
	
	public void init(FilterConfig config) throws ServletException {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {	 
		if (!(req instanceof HttpServletRequest)|| !(res instanceof HttpServletResponse)) {
			throw new ServletException("just supports HTTP requests");
		}
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getRequestURI(); 
		String userId=(String) request.getSession().getAttribute(LOADED_USER_ID);
		if(null==userId || userId.equals("")){
			response.sendRedirect(request.getContextPath()+"/login.jsp?url="+url);
			return;
		}
		chain.doFilter(req, res);
	}

	public void destroy() {
		
	}
}
