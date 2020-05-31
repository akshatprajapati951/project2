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
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.CollegeModelInt;
import in.co.rays.proj3.model.CourseModelInt;
import in.co.rays.proj3.model.FacultyModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.SubjectModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

/**
 * Faculty functionality Controller. Performs operation for add, update, delete
 * and get Faculty
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl" })
public class FacultyCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(FacultyCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModelInt collegeModel = ModelFactory.getInstance().getCollegeModel();
		SubjectModelInt subjectModel = ModelFactory.getInstance().getSubjectModel();
		
		try {
			List l = collegeModel.list();
			

			List l2 = subjectModel.list();


			request.setAttribute("collegeList", l);
			request.setAttribute("subjectList", l2);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FacultyCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		String email = request.getParameter("login");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.string", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.string", "Name"));
			pass = false;
		} else if (request.getParameter("lastName").contains(" ")) {
			request.setAttribute("lastName", PropertyReader.getValue("Space Not Allowed"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (DataUtility.getString(request.getParameter("mobileNo")).length() != 10) {
			request.setAttribute("mobileNo", PropertyReader.getValue("Mobile No must be of 10 digits"));
			pass = false;
		}

		else if (!(DataValidator.isMobile(request.getParameter("mobileNo")))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile", "Mobile No"));
			pass = false;
		}

		if (DataValidator.isNull(email)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Email"));
			pass = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Email "));
			pass = false;
		}
		if (DataUtility.getString(request.getParameter("collegeId")).equalsIgnoreCase("select")) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		/*
		 * if (DataUtility.getString(request.getParameter("courseId")).
		 * equalsIgnoreCase("select")) { request.setAttribute("courseId",
		 * PropertyReader.getValue("error.require", "Course Name")); pass =
		 * false; }
		 */
		if (DataUtility.getString(request.getParameter("subjectId")).equalsIgnoreCase("select")) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		} else if (DataValidator.getAge(dob) < 18) {
			request.setAttribute("dob", PropertyReader.getValue("error.validage", "Age"));
			pass = false;
		}

		log.debug("FacultyCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("FacultyCtl Method populatebean Started");

		FacultyDTO bean = new FacultyDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		bean.setExperience(DataUtility.getInt(request.getParameter("experience")));

		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirm password")));
        
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		
		

		populateDTO(bean, request);

		log.debug("FacultyCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("FacultyCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		// get model
		/* FacultyModelHibImpl model = new FacultyModelHibImpl(); */
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();

		if (id > 0 || op != null) {
			FacultyDTO bean = null;
			try {

				bean = model.findByPK(id);
				ServletUtility.setDTO(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("FacultyCtl Method doGett Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("FacultyCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		String op1 = DataUtility.getString(request.getParameter("operation1"));

		// get model
		/* FacultyModelHibImpl model = new FacultyModelHibImpl(); */
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			FacultyDTO bean = (FacultyDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setDTO(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully updated", request);

				} else {
					long pk = model.add(bean);
					bean.setId(pk);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDTO(bean, request);
				ServletUtility.setErrorMessage("Faculty Email Id already exists", request);
			}

		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			FacultyDTO bean = (FacultyDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
			return;

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("FacultyCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

}
