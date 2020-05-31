	package in.co.rays.proj3.model;

	import java.util.Date;
import java.util.List;

import in.co.rays.proj3.dto.TimeTableDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;

	/**
	 * Data Access Object of TimeTables
	 * 
	 * @author Strategy
	 * @version 1.0
	 * @Copyright (c) SunilOS
	 */
	public interface TimeTableModelInt {
		/**
	     * Add a user
	     * 
	     * @param dto
	     * @throws ApplicationException
	     * @throws DuplicateRecordException
	     *             : throws when user already exists
	     */
		public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException;
		 /**
	     * Update a TimeTable
	     * 
	     * @param dto
	     * @throws ApplicationException
	     * @throws DuplicateRecordException
	     *             : if updated user record is already exist
	     */
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException;
	/**
	 * Delete a user
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException;
	/**
	 * Delete a user
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public TimeTableDTO findTimeTableDuplicacy(Long courseId,String semester,Date examdate) throws ApplicationException;
	/**
     * Find timetable duplicacy
     * 
     * @param TimeTableDuplicacy
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     */
	public TimeTableDTO findTimeTableDuplicacy(Long courseId,String semester,Long subjectId) throws ApplicationException;
	/**
     * Find user by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
	public TimeTableDTO findByPK(long pK) throws ApplicationException;

    /**
     * Search TimeTables
     * 
     * @return list : List of TimeTables
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
public List search(TimeTableDTO dto) throws ApplicationException;
/**
 * Search TimeTables with pagination
 * 
 * @return list : List of TimeTables
 * @param dto
 *            : Search Parameters
 * @param pageNo
 *            : Current Page No.
 * @param pageSize
 *            : Size of Page
 * @throws ApplicationException
 */
public List search(TimeTableDTO dto,int pageNo,int pageSize) throws ApplicationException;
/**
 * Get List of TimeTables
 * 
 * @return list : List of TimeTables
 * @throws ApplicationException 
 * @throws DatabaseException
 */
public List list() throws ApplicationException;
/**
 * Get List of TimeTables with pagination
 * 
 * @return list : List of TimeTables
 * @param pageNo
 *            : Current Page No.
 * @param pageSize
 *            : Size of Page
 * @throws ApplicationException
 */
public List list(int pageNo,int pageSize) throws ApplicationException;
}
