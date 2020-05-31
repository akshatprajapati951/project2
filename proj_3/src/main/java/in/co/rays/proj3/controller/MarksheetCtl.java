package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.MarksheetModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.StudentModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;


/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "MarksheetCtl", urlPatterns = { "/ctl/MarksheetCtl" })
public class MarksheetCtl extends BaseCtl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		/*StudentModelHibImpl model = new StudentModelHibImpl();*/
		
		StudentModelInt  model = ModelFactory.getInstance().getStudentModel();

		try {
			List l = model.list();
			request.setAttribute("studentList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		} else if (request.getParameter("rollNo").contains(" ")) {
			request.setAttribute("rollNo", PropertyReader.getValue("Space not allowed"));
			pass = false;
		} else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.roll", "Roll No"));
			pass = false;
		}

		if (DataValidator.isNotNull(request.getParameter("physics"))
				&& !DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}

		if (DataUtility.getInt(request.getParameter("physics")) > 100) {
			request.setAttribute("physics", "Marks can not be greater than 100");
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("physics")) < 0) {
			request.setAttribute("physics", "Marks can not be negative");
			pass = false;
		} else if (DataUtility.getString(request.getParameter("physics")).equals("")) {
			request.setAttribute("physics", PropertyReader.getValue("error.require", "Physics Marks"));
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("chemistry"))
				&& !DataValidator.isInteger(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}

		if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry", "Marks can not be greater than 100");
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("chemistry")) < 0) {
			request.setAttribute("chemistry", "Marks can not be negative");
			pass = false;
		} else if (DataUtility.getString(request.getParameter("chemistry")).equals("")) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.require", "Chemistry Marks"));
			pass = false;
		}

		if (DataValidator.isNotNull(request.getParameter("maths"))
				&& !DataValidator.isInteger(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}

		if (DataUtility.getInt(request.getParameter("maths")) > 100) {
			request.setAttribute("maths", "Marks can not be greater than 100");
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("maths")) < 0) {
			request.setAttribute("maths", "Marks can not be negative");
			pass = false;
		} else if (DataUtility.getString(request.getParameter("maths")).equals("")) {
			request.setAttribute("maths", PropertyReader.getValue("error.require", "Maths Marks"));
			pass = false;
		}

		if (DataUtility.getString(request.getParameter("studentId")).equalsIgnoreCase("select")) {
			request.setAttribute("studentId", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}

		log.debug("MarksheetCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("MarksheetCtl Method populatebean Started");

		MarksheetDTO bean = new MarksheetDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));

		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		System.out.println(request.getParameter("studentId"));

		populateDTO(bean, request);

		log.debug("MarksheetCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MarksheetCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		/*MarksheetModelHibImpl model = new MarksheetModelHibImpl();*/
		MarksheetModelInt model= ModelFactory.getInstance().getMarksheetModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			MarksheetDTO bean;
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
		log.debug("MarksheetCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MarksheetCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		String op1 = DataUtility.getString(request.getParameter("operation1"));
		/*MarksheetModelHibImpl model = new MarksheetModelHibImpl();*/
		MarksheetModelInt model= ModelFactory.getInstance().getMarksheetModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			MarksheetDTO bean = (MarksheetDTO) populateDTO(request);
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
				// ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDTO(bean, request);
				ServletUtility.setErrorMessage("Roll no already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MarksheetDTO bean = (MarksheetDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MarksheetCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}
