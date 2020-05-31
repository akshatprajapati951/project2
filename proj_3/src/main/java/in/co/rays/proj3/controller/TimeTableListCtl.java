package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.TimeTableDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.model.CourseModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.SubjectModelInt;
import in.co.rays.proj3.model.TimeTableModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;



/**
 * TimeTable List functionality Controller. Performs operation for list, search
 * and delete operations of TimeTable
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name = "TimeTableListCtl", urlPatterns = { "/ctl/TimeTableListCtl" })
public class TimeTableListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(TimeTableListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		/*CourseModelHibImpl courseModel = new CourseModelHibImpl();*/
		CourseModelInt model2= ModelFactory.getInstance().getCourseModel();
		
		/*SubjectModelHibImpl subjectModel = new SubjectModelHibImpl();*/
		SubjectModelInt model= ModelFactory.getInstance().getSubjectModel();
		
		try {
			List l2 = model2.list();
			List l = model.list();
			request.setAttribute("courseList", l2);
			request.setAttribute("subjectList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		TimeTableDTO bean = new TimeTableDTO();
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("date")));
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimeTableListCtl doGet Start");

		List list = null;
		int record = 0;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		TimeTableDTO bean = (TimeTableDTO) populateDTO(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		/*TimeTableModelHibImpl model = new TimeTableModelHibImpl();
		*/
				TimeTableModelInt model= ModelFactory.getInstance().getTimeTableModel();
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
		log.debug("TimeTableListCtl doGet End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimeTableListCtl doPost Start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		int record = DataUtility.getInt(request.getParameter("record"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		TimeTableDTO bean = (TimeTableDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		/*TimeTableModelHibImpl model = new TimeTableModelHibImpl();
		*/
				TimeTableModelInt model= ModelFactory.getInstance().getTimeTableModel();
		String[] ids = request.getParameterValues("ids");
		try {
			if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
				return;
			}

			if (OP_BACK.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {

				pageNo = 1;
				if (ids != null && ids.length > 0) {
					TimeTableDTO deletebean = new TimeTableDTO();
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
					ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
					return;
				}

			}

			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if ((list == null || list.size() == 0) && !OP_DELETE.equalsIgnoreCase(op)) {
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
		log.debug("TimeTableListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
