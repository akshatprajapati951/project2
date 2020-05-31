package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.CourseModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;


/**
 * Course functionality Controller. Performs operation for add, update, delete
 * and get Course
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl" })
public class CourseCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(CourseCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CourseCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (DataValidator.isNull(request.getParameter("Name"))) {
			request.setAttribute("Name", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		/*} else if (!DataValidator.isName(request.getParameter("Name"))) {
			request.setAttribute("Name", PropertyReader.getValue("error.string", "Course Name"));
			pass = false;
		}*/} else if (request.getParameter("Name").contains(" ")) {
			request.setAttribute("Name", PropertyReader.getValue("Space not allowed"));
			pass = false;
		}

		if (request.getParameter("Duration").equalsIgnoreCase("select")) {
			request.setAttribute("Duration", PropertyReader.getValue("error.require", "Course Duration"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Desc"))) {
			request.setAttribute("Desc", PropertyReader.getValue("error.require", "Course Description"));
			pass = false;
		}

		/*else if (!(DataValidator.isFirstLetterCapital(request.getParameter("Desc")))) {
			request.setAttribute("Desc", PropertyReader.getValue("Course Description must start with capital letter"));
			pass = false;
		}*/

		log.debug("CourseCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("CourseCtl Method populatebean Started");

		CourseDTO bean = new CourseDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("Name")));

		bean.setDuration(DataUtility.getString(request.getParameter("Duration")));

		bean.setDescription(DataUtility.getString(request.getParameter("Desc")));

		populateDTO(bean, request);

		log.debug("CourseCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CourseCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		// get model
				/*CourseModelHibImpl model = new CourseModelHibImpl();*/
				CourseModelInt model= ModelFactory.getInstance().getCourseModel();
		if (id > 0 || op != null) {
			CourseDTO bean = null;
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
		log.debug("CourseCtl Method doGett Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CourseCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		String op1 = DataUtility.getString(request.getParameter("operation1"));
		// get model
				/*CourseModelHibImpl model = new CourseModelHibImpl();*/
				CourseModelInt model= ModelFactory.getInstance().getCourseModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			CourseDTO bean = (CourseDTO) populateDTO(request);

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
				ServletUtility.setErrorMessage("Course already exists", request);
			}

		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			CourseDTO bean = (CourseDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
			return;

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("CourseCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
