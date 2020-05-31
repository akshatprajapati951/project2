package in.co.rays.proj3.dto;

/**
 * Subject JavaDTO encapsulates Subject attributes
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class SubjectDTO extends BaseDTO {

	/**
	 * Name of Subject
	 */
	private String name;

	/**
	 * description of Subject
	 */
	private String description;

	/**
	 * courseID of Subject
	 */
	private long courseId;
	/**
	 * courseName of subject
	 */

	private String courseName;
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * accessor
	 */
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String getKey() {

		return id+"";
	}

	@Override
	public String getValue() {

		return name;
	}

}
