package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.util.ServletUtility;

/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebFilter(filterName = "FrontCtl", urlPatterns = { "/ctl/*", "/doc/*" })
public class FrontController implements Filter {

	private static Logger log = Logger.getLogger(FrontController.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		/*
		 * StringBuffer uri1 = request.getRequestURL(); String uri =
		 * uri1.toString(); session.setAttribute("uri", uri);
		 */
		HttpSession session = request.getSession(false);
	/*if(session==null){
		request.setAttribute("message", "Your session has been expired. Please re-Login.");
		ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
	}*/
		try {
			
			if (session.getAttribute("user") == null) {
				request.setAttribute("message", "Your session has been expired. Please re-Login.");
				String string = request.getRequestURI();
				session.setAttribute("URI", string);

				ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);

			} else {
				chain.doFilter(req, resp);
			}
		} catch (Exception e) {
			
			log.error(e);
		ServletUtility.handleException(e, request, response);
			return;
		}
	}

	public void init(FilterConfig conf) throws ServletException {
	}

}
