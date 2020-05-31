package in.co.rays.proj3.test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.RoleModelHibImpl;
import in.co.rays.proj3.model.RoleModelInt;

/** 
 * Role Model Test classes 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 *  
 */ 
public class RoleModelTest {
	/** 
     * Model object to test 
     */ 
	
	public static RoleModelInt model=new RoleModelHibImpl();
	
	//public static UserModelInt model=new UserModelJDBCImpl();
	/** 
     * Main method to call test methods. 
     *  
     * @param args 
	 * @throws DuplicateRecordException 
     * @throws ParseException 
     */ 
	public static void main(String[] args) throws DuplicateRecordException {
		  testAdd(); 
        //testDelete(); 
       // testUpdate(); 
       // testFindByPK(); 
        //testFindByLogin(); 
         //testSearch(); 
       // testList(); 
		

	}

	 /** 
     * Tests add a User 
     *  
     * @throws ParseException 
     */ 
	public static void testAdd(){
		try{
			RoleDTO dto=new RoleDTO();
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
			// dto.setId(1L); 
			//dto.setName("gurudatt");
			//dto.setDescription("shivhare");
			dto.setName("admin");
			dto.setDescription("Administartor");
			long pk=model.add(dto);
			System.out.println(pk);
			
			 RoleDTO addeddto =model.findBYPK(pk);
			 if(addeddto ==null){
				 System.out.println("test add fail");
			 }
			 }catch(ApplicationException e){
				 e.printStackTrace();
			 }catch(DuplicateRecordException e){
				 e.printStackTrace();
		
			}
		System.out.println("add successfully");	
	}

	
	/** 
     * Tests delete a User 
     */
	public static void testDelete(){
		try{
			RoleDTO dto=new RoleDTO();
			long pk=1;
			dto.setId(pk);
			model.delete(dto);
			System.out.println("role delete successfully");
			RoleDTO deletedto=model.findBYPK(pk);
			if(deletedto!=null){
				System.out.println("test delete fail");
				
			}
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}
	
	 /** 
     * Tests update a User 
     */ 
	public static void testUpdate() throws DuplicateRecordException{
		RoleDTO dto=new RoleDTO();
		try{
			dto =model.findBYPK(1);
			dto.setName("guru");
			dto.setDescription("shivharee");
			model.update(dto);
			System.out.println("updated role successfully");
			RoleDTO updateddto=model.findBYPK(1);
			if(!"guru".equals(updateddto.getName())){
				System.out.println("role updated fail");
			}
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}

	/** 
     * Tests find a User by PK. 
     */ 
	public static void testFindByPK() { 
        try { 
            RoleDTO dto = new RoleDTO(); 
            long pk = 1; 
            dto = model.findBYPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
	/** 
     * Tests find a User by Login. 
     */ 
    public static void testFindByLogin() { 
        try { 
            RoleDTO dto = new RoleDTO(); 
            dto = model.findByName("admin"); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 	

    /** 
     * Tests get Search 
     */ 
    
    public static void testSearch(){
    	try{
    		RoleDTO dto=new RoleDTO ();
    		List list=new ArrayList();
    		dto.setName("admin");
    		list=model.search(dto,0,0);
    		if(list.size()<0){
    			System.out.println("role search fail");
    		}
    		Iterator it=list.iterator();
    		while(it.hasNext()){
    			dto=(RoleDTO)it.next();
    			System.out.println(dto.getId()); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getDescription()); 
            } 
 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
    /** 
     * Tests get List. 
     */ 
    public static void testList() {
    	 try { 
             RoleDTO dto = new RoleDTO(); 
             List list = new ArrayList();
             list = model.list(0, 10); 
             if (list.size()== 0) { 
                 System.out.println("Test list fail"); 
             } 
             Iterator it = list.iterator(); 
             while (it.hasNext()) { 
                 dto = (RoleDTO) it.next(); 
                 System.out.println(dto.getId()); 
                 System.out.println(dto.getName()); 
                 System.out.println(dto.getDescription()); 
             } 
  
         } catch (ApplicationException e) { 
             e.printStackTrace(); 
         } 
     } 
 } 

