
package in.co.rays.proj3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DatabaseException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HibDataSource;
/**
 * Hibernate Implementation of RoleModel
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class RoleModelHibImpl implements RoleModelInt {
	
	public static Logger log=Logger.getLogger(RoleModelHibImpl.class);
	 /**
     * Add a Role
     * 
     * @param dto
	 * @throws DuplicateRecordException 
     * @throws DatabaseException
     * 
     */
	

	@Override
	public long add(RoleDTO dto) throws ApplicationException,DuplicateRecordException {
	
		log.debug("Model add started");
		long pk =0;
		RoleDTO duplicaterole=findByName(dto.getName());
		
		if(duplicaterole!=null){
			throw new DuplicateRecordException("Role already exists");
		}
		Session session=HibDataSource.getSession();
		Transaction transaction=null;
		try{
			transaction=session.beginTransaction();
			session.save(dto);
			
			pk =dto.getId();
			
			transaction.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			log.error("DatabaseException...",e);
			if(transaction!=null){
				transaction.rollback();
			}
			throw new ApplicationException("Exception in add ROle"+ e.getMessage());
			}finally{
				if(session!=null){
					session.close();
				}
				log.debug("Model add end");
				return dto.getId();
			}
		
		
	}

	@Override
	public void update(RoleDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update started");
		Session session =null;
		Transaction transaction=null;
		RoleDTO duplicaterole=findByName(dto.getName());
		if(duplicaterole!=null){
			throw new DuplicateRecordException("Role already exists");
		}
		try{
			session =HibDataSource.getSession();
			transaction=session.beginTransaction();
			session.update(dto);
			transaction.commit();
			
		}catch(HibernateException e){
			log.error("DatabaseException..",e);
			if(transaction!=null){
				transaction.rollback();
			}
			 throw new ApplicationException("Exception in Role Update"
                     + e.getMessage());
		}finally{
			session.close();
		}
		log.debug("model update end");
		
	}
	/**
     * Delete a Role
     * 
     * @param dto
	 * @throws ApplicationException 
     * @throws DatabaseException
     */
	@Override
	public void delete(RoleDTO dto) throws ApplicationException {
	
		log.debug("Model delete started ");
		Session session =null;
		Transaction transaction =null;
		
		try{
			 session =HibDataSource.getSession();
			 transaction =session.beginTransaction();
			 session.delete(dto);
			 transaction.commit();
		}catch(HibernateException e){
			log.error("DatabaseException..",e);
			if (transaction!=null){
				transaction.rollback();
			}
			throw new ApplicationException("Exception in role delete"+e.getMessage());
		}finally{
			session.close();
		}
		log.debug("Model delete end");
	}
	 /**
     * Find Role by Name
     * 
     * @param name
     *            : get parameter
     * @return dto
	 * @throws ApplicationException 
	 * @throws DatabaseException
     */
	@Override
	public RoleDTO findByName(String name) throws ApplicationException {
		Session session =null;
		RoleDTO dto=null;
		try {
			session =HibDataSource.getSession();
			Criteria criteria=session.createCriteria(RoleDTO.class);
			criteria.add(Restrictions.eq("name",name));
			List list=criteria.list();
			if(list.size()==1){
				dto=(RoleDTO)list.get(0);
			}else{
				dto=null;
			}
		}catch(Exception e){
			log.error("DatabaseException..",e);
			throw new ApplicationException(
                    "Exception in getting User by Login " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByName End");
        return dto;
    }

	/**
     * Find Role by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
	@Override
	public RoleDTO findBYPK(long pk) throws ApplicationException {
		
		log.debug("model findbypk statred");
		Session session=null;
		RoleDTO dto=null;
		
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(RoleDTO.class);
			criteria.add(Restrictions.eq("id", pk));
			List list=criteria.list();
			if(list.size()==1){
				dto=(RoleDTO)list.get(0);
			}else{
				dto=null;
			}
				
			
		}catch(Exception e){
			log.error(e);
            throw new ApplicationException("Exception in getting Role by pk "
                    + e.getMessage());

		}finally {
            session.close();
        }
        log.debug("Model findBypk End");
        return dto;
		
		
	}

	@Override
	public List search(RoleDTO dto, int pageNo, int pageSize) throws ApplicationException {
		
		 log.debug("model search started");
		 Session session=null;
		 List list=null;
		 try{
			 session=HibDataSource.getSession();
			 Criteria criteria=session.createCriteria(RoleDTO.class);
			 if(dto.getId()>0){
				 criteria.add(Restrictions.eq("id", dto.getId()));
			 }
			 if(dto.getName()!=null&&dto.getName().length()>0){
				 criteria.add(Restrictions.like("name", dto.getName()+"%"));
			 }
			 if(dto.getDescription()!=null&& dto.getDescription().length()>0){
				 criteria.add(Restrictions.like("Description", dto.getDescription()+"%"));
			 }
			// if page size is greater than zero the apply pagination
			 if(pageSize>0){
				 criteria.setFirstResult(((pageNo-1)*pageSize));
				 criteria.setMaxResults(pageSize);
			 }
			 list=criteria.list();
		 } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException("Exception in Role search");
	        } finally {
	            session.close();
	        }

	        log.debug("Model search End");
	        return list;
	    }



	@Override
	public List Search(RoleDTO dto) throws ApplicationException {
		
		return search(dto,0,0);
	}
	/**
     * Gets List of Role
     * 
     * @return list : List of Roles
	 * @throws ApplicationException 
     * @throws DatabaseException
     */
	@Override
	public List list() throws ApplicationException {
		
		return list(0,0);
	}
	/**
     * get List of Role with pagination
     * 
     * @return list : List of Roles
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
	 * @throws ApplicationException 
     * @throws DatabaseException
     */
	@Override
	public List list(int pageNo, int PageSize) throws ApplicationException {
		log.debug("model list started");
		Session session=null;
		List list=null;
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(RoleDTO.class);
			// if page size is greater than zero then apply pagination
            if (PageSize > 0) {
                pageNo = ((pageNo - 1) * PageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(PageSize);
            }

            list = criteria.list();
          
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in  Roles list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
    }
}
