package in.co.rays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.ServletUtility;


/**
 * Error functionality Controller. Performs operation for a error.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "ErrorCtl", urlPatterns = { "/ErrorCtl" })
public class ErrorCtl extends BaseCtl {

	public static Logger log = Logger.getLogger(Error.class);
	private static final long serialVersionUID = 1L;

	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null){
			request.setAttribute("message", "Your session has been expired. Please re-Login.");
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		}
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		
		if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
			return;
		} else {
			
		}

	}

	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}
