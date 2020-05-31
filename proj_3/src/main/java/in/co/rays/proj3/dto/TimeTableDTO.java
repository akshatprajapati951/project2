package in.co.rays.proj3.dto;

import java.util.Date;

public class TimeTableDTO extends BaseDTO{

	/**
	 * Id Of Course
	 */
	private long courseId;

	/**
	 * Id Of Subject
	 */
	private long subjectId;

	/**
	 * Name Of Course
	 */
	private String courseName;

	/**
	 * Name Of Subject
	 */
	private String subjectName;

	/**
	 * Date Of Exam
	 */
	private Date examDate;

	/**
	 * Semester of Exam 
	 */
	
	private String semester;
	/**
	 * 
	 */
	private String ExamTime;

	/**
	 * accessor
	 */
	
	
	
	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getExamTime() {
		return ExamTime;
	}

	public void setExamTime(String examTime) {
		ExamTime = examTime;
	}
	
	@Override
	public String getKey() {
		
		return null;
	}

	@Override
	public String getValue() {
		
		return null;
	}

}