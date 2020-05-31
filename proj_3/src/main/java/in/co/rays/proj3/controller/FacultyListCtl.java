
package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.FacultyDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.model.CollegeModelInt;
import in.co.rays.proj3.model.FacultyModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;


/**
 * Faculty List functionality Controller. Performs operation for list, search
 * and delete operations of Faculty
 * 
 * @author SunilOS
 * @version 1.0
 * 
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "FacultyListCtl", urlPatterns = { "/ctl/FacultyListCtl" })
public class FacultyListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(FacultyListCtl.class);

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		FacultyDTO bean = new FacultyDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		
		bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		
		bean.setExperience(DataUtility.getInt(request.getParameter("experience")));
		
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
         
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirm password")));
		
;

		return bean;
	}

	@Override
	protected void preload(HttpServletRequest request) {
		/*CollegeModelHibImpl collegeModel = new CollegeModelHibImpl();*/
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
		try {
			List l = model.list();
			request.setAttribute("collegeList", l);
			
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyListCtl doGet Start");

		List list = null;
		int pageNo = 1;
		int record = 0;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		FacultyDTO bean = (FacultyDTO) populateDTO(request);

		String op = DataUtility.getString(request.getParameter("operation"));
/*FacultyModelHibImpl model = new FacultyModelHibImpl();*/
		
		FacultyModelInt model =ModelFactory.getInstance().getFacultyModel();
		try {
			list = model.search(bean, pageNo, pageSize);
			/*record = model.totalRecord();*/
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setRecord(record, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		} /*catch (DatabaseException e) {
			ServletUtility.handleException(e, request, response);
		}*/
		log.debug("FacultyListCtl doGet End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyListCtl doPost Start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		int record = DataUtility.getInt(request.getParameter("record"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		FacultyDTO bean = (FacultyDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
/*FacultyModelHibImpl model = new FacultyModelHibImpl();*/
		
		FacultyModelInt model =ModelFactory.getInstance().getFacultyModel();
		String[] ids = request.getParameterValues("ids");
		try {
			if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;
			}
			
			if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;
			}
			else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					FacultyDTO deletebean = new FacultyDTO();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Record is succcessfully deleted", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)
					|| "New".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} else if (OP_NEW.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
					return;
				}

			}

			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if ((list == null || list.size() == 0)&&!OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setRecord(record, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setDTO(bean, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("FacultyListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_LIST_VIEW;
	}

}
