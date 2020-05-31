package in.co.rays.proj3.model;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.dto.SubjectDTO;
import in.co.rays.proj3.dto.TimeTableDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HibDataSource;

public class TimeTableModelHibImpl implements TimeTableModelInt {

	private static Logger log = Logger.getLogger(TimeTableModelHibImpl.class);
	/**
	 * Add a TimeTable
	 * 
	 * @param dto
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 * @throws DatabaseException
	 * 
	 */

	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		
		log.debug("Model add started");
		long pk=0;
		
		CourseModelInt cModel=ModelFactory.getInstance().getCourseModel();
		CourseDTO coursedto=cModel.findByPK(dto.getCourseId());
		dto.setCourseName(coursedto.getName());
		
		SubjectModelInt cModel1=ModelFactory.getInstance().getSubjectModel();
		SubjectDTO subjectdto=cModel1.findByPK(dto.getSubjectId());
		dto.setSubjectName(subjectdto.getName());
		
		
		TimeTableDTO duplicatename=findTimeTableDuplicacy(dto.getCourseId(),dto.getSemester(),dto.getExamDate());
		TimeTableDTO duplicatename1=findTimeTableDuplicacy(dto.getCourseId(),dto.getSemester(),dto.getSubjectId());
		if(duplicatename!=null){
			throw new DuplicateRecordException("Exam for this subject already scheduled on this Exam date");
		}
		 if(duplicatename1!=null){
				throw new DuplicateRecordException("course/subject/semester already exist");
				
			}
	
		 Session session = HibDataSource.getSession();
	        Transaction transaction = null;
	        
			try {
				transaction = session.beginTransaction();
	            session.save(dto);
	            pk = dto.getId();
	            transaction.commit();
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	           e.printStackTrace();
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            throw new ApplicationException("Exception in User Add "
	                    + e.getMessage());
	        } finally {
	            session.close();
	        }
	        log.debug("Model add End");
	        return dto.getId();

		}


	
	
	/**
	 * Update a TimeTable
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 * @throws @throws
	 *             DatabaseException
	 */
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;
    	int pk = 0;
      //check duplicacy
    	


    CourseModelInt cModel = ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDto = cModel.findByPK(dto.getCourseId());
		dto.setCourseName(courseDto.getName());

		SubjectModelInt sModel = ModelFactory.getInstance().getSubjectModel();
		SubjectDTO subjectDto = sModel.findByPK(dto.getSubjectId());
		dto.setSubjectName(subjectDto.getName());

		TimeTableDTO duplicatename = findTimeTableDuplicacy(dto.getCourseId(),
				 dto.getSemester(),dto.getExamDate());
				TimeTableDTO duplicatename1 = findTimeTableDuplicacy(dto.getCourseId(),
						 dto.getSemester(),dto.getSubjectId());
				
			
				 if(duplicatename1!=null){
					throw new DuplicateRecordException("Time Table already exist");
					
				}

				 if (duplicatename!=null ) {
					throw new DuplicateRecordException("Time Table already exist");}

				 
		

        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in User Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");


	}

	/**
	 * Delete a TimeTable
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DatabaseException
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in User Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }
	/**
	 * Find TimeTableDuplicacy
	 * 
	 * @throws ApplicationException
	 */

	
	public TimeTableDTO findTimeTableDuplicacy(Long courseId, String semester, Date examDate) throws ApplicationException {
		log.debug("Method FindTimeTable of Model TimeTable started");
		Session session=null;
		TimeTableDTO dto = null;
		try{
			session =HibDataSource.getSession();
			Criteria criteria=session.createCriteria(TimeTableDTO.class);
			criteria.add(Restrictions.eq("courseId", courseId));
			criteria.add(Restrictions.eq("semester", semester));
			criteria.add(Restrictions.eq("examDate", examDate));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (TimeTableDTO) list.get(0);
			}
			} catch (HibernateException e) {
				e.printStackTrace();
				log.error("Database Exception..", e);
				throw new ApplicationException("Exception in getting TimeTable " + e.getMessage());

			} finally {
				session.close();
			}

			log.debug("Model TimeTable End");
			return dto;
	}
			/**
			 * Find TimeTableDuplicacy
			 * 
			 * @throws ApplicationException
			 */
			public TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Long subjectId)
					throws ApplicationException {
		log.debug("Method FindTimeTable of Model TimeTable started");

				

				Session session= null;
				TimeTableDTO dto = null;
				try {
					session = HibDataSource.getSession();
		            Criteria criteria = session.createCriteria(TimeTableDTO.class);
		            criteria.add(Restrictions.eq("courseId", courseId));
		            criteria.add(Restrictions.eq("semester", Semester));
		            criteria.add(Restrictions.eq("subjectId", subjectId));
		            List list = criteria.list();
		            if (list.size() >0) {
		                dto = (TimeTableDTO) list.get(0);
		            }

		        } catch (HibernateException e) {
		            log.error("Database Exception..", e);
		            throw new ApplicationException(
		                    "Exception in getting TimeTable " + e.getMessage());

		        } finally {
		            session.close();
		        }

		        log.debug("Model TimeTable End");
		        return dto;	
			}



			/**
			 * Find TimeTable by PK
			 * 
			 * @param pk
			 *            : get parameter
			 * @return bean
			 * @throws ApplicationException
			
			 */
			public TimeTableDTO findByPK(long pk) throws ApplicationException {
				 log.debug("Model findByPK Started");
			        Session session = null;
			        TimeTableDTO dto = null;
			        try {
			            session = HibDataSource.getSession();
			            dto = (TimeTableDTO) session.get(TimeTableDTO.class, pk);
			        } catch (HibernateException e) {
			            log.error("Database Exception..", e);
			            throw new ApplicationException(
			                    "Exception : Exception in getting User by pk");
			        } finally {
			            session.close();
			        }

			        log.debug("Model findByPK End");
			        return dto;
			}


			/**
		     * Searches User
		     * 
		     * @param dto
		     *            : Search Parameters
		     * @throws DatabaseException
		     */

			public List search(TimeTableDTO dto) throws ApplicationException {
				// TODO Auto-generated method stub
				return search(dto,0,0);
			}
			
			 /**
		     * Searches Users with pagination
		     * 
		     * @return list : List of TimeTable
		     * @param dto
		     *            : Search Parameters
		     * @param pageNo
		     *            : Current Page No.
		     * @param pageSize
		     *            : Size of Page
		     * 
		     * @throws DatabaseException
		     */
			public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
				log.debug("Model search Started");
		        Session session = null;
		        List list = null;
		        try {
		            session = HibDataSource.getSession();
		            Criteria criteria = session.createCriteria(TimeTableDTO.class);

		            if (dto.getId() > 0) {
		                criteria.add(Restrictions.eq("id", dto.getId()));
		            }
		            if (dto.getExamDate() != null) {
		            	 criteria.add(Restrictions.eq("examDate", dto.getExamDate()));
		                     
		            
		              /* criteria.add(Restrictions.like("examDate",  new java.sql.Date(dto.getExamDate().getTime())+"%"));*/
		                 
		               System.out.println(new java.sql.Date(dto.getExamDate().getTime()));
		                

		            }
		           
		            if (dto.getSubjectId()>0) {
		                criteria.add(Restrictions.eq("subjectId",dto.getSubjectId()));
		                
		            }
		            if (dto.getCourseId()>0) {
		                criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
		            }
		            
		            // if page size is greater than zero then apply pagination
		            if (pageSize > 0) {
		                criteria.setFirstResult(((pageNo - 1) * pageSize));
		                criteria.setMaxResults(pageSize);
		            }

		            list = criteria.list();
		            
		        } catch (HibernateException e) {
		        
		        	e.printStackTrace();
		            log.error("Database Exception..", e);
		            throw new ApplicationException("Exception in user search");
		        } finally {
		            session.close();
		        }

		        log.debug("Model search End");
		        return list;
		        }

	

			/**
			 * Get List of TimeTable
			 * 
			 * @return list : List of TimeTable
			 * @throws DatabaseException
			 */
			public List list() throws ApplicationException {
				return list(0,0);
			}
			/**
			 * Get List of TimeTable with pagination
			 * 
			 * @return list : List of TimeTable
			 * @param pageNo
			 *            : Current Page No.
			 * @param pageSize
			 *            : Size of Page
			 * @throws ApplicationException
			 * @throws DatabaseException
			 */
			public List list(int pageNo, int pageSize) throws ApplicationException {
				 log.debug("Model list Started");
			        Session session = null;
			        List list = null;
			        try {
			            session = HibDataSource.getSession();
			            Criteria criteria = session.createCriteria(TimeTableDTO.class);

			            // if page size is greater than zero then apply pagination
			            if (pageSize > 0) {
			                pageNo = ((pageNo - 1) * pageSize) + 1;
			                criteria.setFirstResult(pageNo);
			                criteria.setMaxResults(pageSize);
			            }

			            list = criteria.list();
			        } catch (HibernateException e) {
			            log.error("Database Exception..", e);
			            throw new ApplicationException(
			                    "Exception : Exception in  TimeTable List");
			        } finally {
			            session.close();
			        }

			        log.debug("Model list End");
			        return list;
			}

		}

