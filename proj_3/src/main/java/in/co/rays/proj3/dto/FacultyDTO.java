package in.co.rays.proj3.dto;

import java.util.Date;

public class FacultyDTO extends BaseDTO {


	/**
	 * First Name of Faculty
	 */

	private String firstName;
	/**
	 * Last Name of Faculty
	 */
	private String lastName;
	/**
	 * experience of Faculty
	 */
	private int experience;
	/**
	 * subjectId
	 */
	private Long SubjectId;
	/**
	 * 
	 * subjectName of faculty
	 */
	private String subjectName;
	
	/**
	 * Date of Birth of Faculty
	 */
	private Date dob;
	/**
	 * Login ID of Faculty
	 */
	private String login;
	/**
	 * College Id of Faculty
	 */
	private long collegeId;
	/**
	 * 
	 * collegeName of faculty
	 */
	private String collegeName;
	
	
	/**
	 * Gender of Faculty
	 */
	private String gender;
	/**
	 * Mobile No of Faculty
	 */
	private String mobileNo;
	/**
	 * Role Id of Faculty
	 */
	private long roleId;
	/**
	 * password of Faculty
	 */
	private String password;
	/**
	 * Confirm Password of Faculty
	 */
	private String confirmPassword;
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Long getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(Long subjectId) {
		SubjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
