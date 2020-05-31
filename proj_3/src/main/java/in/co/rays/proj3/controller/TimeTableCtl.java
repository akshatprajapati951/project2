
package in.co.rays.proj3.controller;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.TimeTableDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.CourseModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.SubjectModelInt;
import in.co.rays.proj3.model.TimeTableModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;



/**
 * TimeTable functionality Controller. Performs operation for add, update,
 * delete and get TimeTable
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "TimeTableCtl", urlPatterns = { "/ctl/TimeTableCtl" })
public class TimeTableCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(TimeTableCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		
		
			/*SubjectModelHibImpl subjectModel = new SubjectModelHibImpl();*/
		SubjectModelInt model1= ModelFactory.getInstance().getSubjectModel();
		
		/*CourseModelHibImpl courseModel = new CourseModelHibImpl();*/
		CourseModelInt model2= ModelFactory.getInstance().getCourseModel();
		try{
			List l1 = model1.list();
			List l2 = model2.list();
			request.setAttribute("courseList", l2);
			request.setAttribute("subjectList", l1);
		} catch (ApplicationException e) {
			log.error(e);
		}

}


	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("TimeTableCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		String date = request.getParameter("date");
		String ExamTime = request.getParameter("ExamTime");
		String semester = request.getParameter("semester");

		if (DataUtility.getString(request.getParameter("courseId")).equalsIgnoreCase("select")) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataUtility.getString(request.getParameter("subjectId")).equalsIgnoreCase("select")) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		if (DataValidator.isNull(date)) {
			request.setAttribute("date", PropertyReader.getValue("error.require", " Exam Date"));
			pass = false;
		} else if (!DataValidator.isDate(date)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}
		if (ExamTime.equalsIgnoreCase("Select")) {
			request.setAttribute("ExamTime", PropertyReader.getValue("error.require", "ExamTime"));
			pass = false;
		}

		if (semester.equalsIgnoreCase("Select")) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester Name"));
			pass = false;
		}

		log.debug("TimeTableCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("TimeTableCtl Method populatebean Started");

		TimeTableDTO bean = new TimeTableDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("date")));
		
		bean.setExamTime(DataUtility.getString(request.getParameter("ExamTime")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		populateDTO(bean, request);

		log.debug("TimeTableCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("TimeTableCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));



		// get model
				/*TimeTableModelHibImpl model = new TimeTableModelHibImpl();*/
				TimeTableModelInt model= ModelFactory.getInstance().getTimeTableModel();
				
		if (id > 0 || op != null) {
			TimeTableDTO bean = null;
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
		log.debug("TimeTableCtl Method doGett Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("TimeTableCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		String op1 = DataUtility.getString(request.getParameter("operation1"));
		// get model
				/*TimeTableModelHibImpl model = new TimeTableModelHibImpl();*/
				TimeTableModelInt model= ModelFactory.getInstance().getTimeTableModel();
				
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			TimeTableDTO bean = (TimeTableDTO) populateDTO(request);

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
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDTO(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);
		log.debug("TimeTableCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}
