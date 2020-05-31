package in.co.rays.proj3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HibDataSource;

/**
 * Hibernate Implementation of CourseModel
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class CourseModelHibImpl implements CourseModelInt {

	private static Logger log = Logger.getLogger(CourseModelHibImpl.class);

	/**
	 * Add a Course
	 * 
	 * @param dto
	 * @throws DatabaseException
	 * 
	 */

	@Override
	public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");
		long pk = 0;
		CourseDTO duplicateCourseName = findByName(dto.getName());

		if (duplicateCourseName != null) {
			throw new DuplicateRecordException("Course Name already exists");
		}

		Session session = HibDataSource.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ApplicationException("Exception in Course Add " + e.getMessage());
		} finally {
			session.close();
		}

		log.debug("Model add End");
		return dto.getId();
	}

	/**
	 * Update a Course
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	@Override
	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Session session = null;
		Transaction transaction = null;

		CourseDTO dtoExist = findByName(dto.getName());

		// Check if updated Course already exist
		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException("Course is already exist");
		}

		try {

			session = HibDataSource.getSession();
			transaction = session.beginTransaction();
			session.update(dto);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
				throw new ApplicationException("Exception in Course Update" + e.getMessage());
			}
		} finally {
			session.close();
		}
		log.debug("Model update End");
	}

	/**
	 * Delete a Course
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	@Override
	public void delete(CourseDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in course Delete" + e.getMessage());
		} finally {
			session.close();
		}
		log.debug("Model delete End");
	}

	/**
	 * Find User by Course Name
	 * 
	 * @param collage
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	@Override
	public CourseDTO findByName(String name) throws ApplicationException {
		log.debug("Model findByName Started");
		Session session = null;
		CourseDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseDTO.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (CourseDTO) list.get(0);
			}

		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception in getting User by Login " + e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findByName End");
		return dto;
	}

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	@Override
	public CourseDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		Session session = null;
		CourseDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (CourseDTO) session.get(CourseDTO.class, pk);
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Course by pk");
		} finally {
			session.close();
		}

		log.debug("Model findByPK End");
		return dto;
	}

	/**
	 * Searches Course with pagination
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	@Override
	public List<CourseDTO> search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		Session session = null;
		List<CourseDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseDTO.class);

			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				criteria.add(Restrictions.like("address", dto.getDescription() + "%"));
			}
			if (dto.getSemester() > 0) {
				criteria.add(Restrictions.eq("semester", dto.getSemester()));
			}
			if (dto.getDuration() != null && dto.getDuration().length() > 0) {
				criteria.add(Restrictions.like("duration", dto.getDuration() + "%"));
			}
			// if page size is greater than zero the apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult(((pageNo - 1) * pageSize));
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception in course search");
		} finally {
			session.close();
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Search Course
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(CourseDTO dto) throws ApplicationException {

		return search(dto, 0, 0);
	}

	/**
	 * Gets List of Course
	 * 
	 * @return list : List of Course
	 * @throws DatabaseException
	 */
	@Override
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * get List of Course with pagination
	 * 
	 * @return list : List of Course
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseDTO.class);

			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in  Course list");
		} finally {
			session.close();
		}

		log.debug("Model list End");
		return list;
	}
}
