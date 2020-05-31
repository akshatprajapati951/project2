package in.co.rays.proj3.model;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.FacultyDTO;
import in.co.rays.proj3.dto.SubjectDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HibDataSource;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate Implementation of FacultyModel
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class FacultyModelHibImpl implements FacultyModelInt {

	private static Logger log = Logger.getLogger(FacultyModelHibImpl.class);

	/**
	 * Add a Faculty
	 * 
	 * @param dto
	 * @throws DatabaseException
	 * 
	 */
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		// get college name
		CollegeModelInt collegeModel = ModelFactory.getInstance().getCollegeModel();

		CollegeDTO collegedto = collegeModel.findByPK(dto.getCollegeId());

		dto.setCollegeName(collegedto.getName());

		// get subject name

		SubjectModelInt subjectModel = ModelFactory.getInstance().getSubjectModel();
		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
		dto.setSubjectName(subjectdto.getName());

		long pk = 0;
		/*
		 * FacultyDTO duplicateFacultyName = findByLogin(dto.getLogin());
		 * 
		 * if (duplicateFacultyName != null) { throw new
		 * DuplicateRecordException("Faculty login already exists"); }
		 */

		Session session = HibDataSource.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			session.save(dto);
			pk = dto.getId();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ApplicationException("Exception in Faculty Add " + e.getMessage());
		} finally {
			session.close();
		}

		log.debug("Model add End");
		return dto.getId();
	}

	/**
	 * Delete a Faculty
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	@Override
	public void delete(FacultyDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Faculty Delete" + e.getMessage());
		} finally {
			session.close();
		}
		log.debug("Model delete End");
	}

	/**
	 * Find Faculty by Faculty Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 *//*
		 * @Override public FacultyDTO findByName(String name) throws
		 * ApplicationException { log.debug("Model findByName Started"); Session
		 * session = null; FacultyDTO dto = null; try { session =
		 * HibDataSource.getSession(); Criteria criteria =
		 * session.createCriteria(FacultyDTO.class);
		 * criteria.add(Restrictions.eq("name", name)); List list =
		 * criteria.list(); if (list.size() > 0) { dto = (FacultyDTO)
		 * list.get(0); }
		 * 
		 * } catch (HibernateException e) { log.error("Database Exception..",
		 * e); throw new ApplicationException(
		 * "Exception in getting Faculty by name " + e.getMessage());
		 * 
		 * } finally { session.close(); }
		 * 
		 * log.debug("Model findByName End"); return dto; }
		 */
	/**
	 * Find Collage by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	@Override
	public FacultyDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		Session session = null;
		FacultyDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (FacultyDTO) session.get(FacultyDTO.class, pk);
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Faculty by pk");
		} finally {
			session.close();
		}

		log.debug("Model findByPK End");
		return dto;
	}

	/**
	 * Update a Collage
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	@Override
	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");

		// get college name
		CollegeModelInt collegeModel = ModelFactory.getInstance().getCollegeModel();

		CollegeDTO collegedto = collegeModel.findByPK(dto.getCollegeId());

		dto.setCollegeName(collegedto.getName());

		// get subject name

		SubjectModelInt subjectModel = ModelFactory.getInstance().getSubjectModel();
		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
		dto.setSubjectName(subjectdto.getName());

		Session session = null;
		Transaction transaction = null;

		FacultyDTO dtoExist = findByLogin(dto.getLogin());

		// Check if updated Faculty already exist
		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException("Loginid is already exist");
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
				throw new ApplicationException("Exception in Faculty Update" + e.getMessage());
			}
		} finally {
			session.close();
		}
		log.debug("Model update End");
	}

	/**
	 * Searches Facultys with pagination
	 * 
	 * @return list : List of Facultys
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
	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);

			if (dto != null) {
				if (dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
					criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
				}

				if (dto.getLastName() != null && dto.getLastName().length() > 0) {
					criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
				}

				if (dto.getLogin() != null && dto.getLogin().length() > 0) {
					criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
				}
				if (dto.getCollegeId() > 0) {
					criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
				}
			}
			// if page size is greater than zero the apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult(((pageNo - 1) * pageSize));
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		
			
		} catch (HibernateException e) {

			log.error("Database Exception..", e);
			throw new ApplicationException("Exception in Faculty search");
		} finally {
			session.close();
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Search Faculty
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	@Override
	public List search(FacultyDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Gets List of Faculty
	 * 
	 * @return list : List of Faculty
	 * @throws DatabaseException
	 */
	@Override
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * get List of Faculty with pagination
	 * 
	 * @return list : List of Faculty
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
			Criteria criteria = session.createCriteria(FacultyDTO.class);

			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in  Faculty list");
		} finally {
			session.close();
		}

		log.debug("Model list End");
		return list;
	}

	@Override
	public FacultyDTO findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLoginId Started");
		Session session = null;
		FacultyDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();

			if (list.size() == 1) {
				dto = (FacultyDTO) list.get(0);
			}

		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception in getting Faculty by Login " + e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findByLoginId End");
		return dto;
	}

}
