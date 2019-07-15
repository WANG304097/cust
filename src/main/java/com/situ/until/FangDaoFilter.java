package com.situ.until;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FangDaoFilter
 */

//@WebFilter("/FangDaoFilter")
public class FangDaoFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public FangDaoFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest hsRequest = (HttpServletRequest) request;
		HttpServletResponse hsResponse = (HttpServletResponse) response;
		Object user = hsRequest.getSession().getAttribute("user");
		if (user == null) {// 盗链
			hsResponse.sendRedirect("/b2c/web/login.jsp");

		} else {// 放行
			chain.doFilter(request, response);

		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
