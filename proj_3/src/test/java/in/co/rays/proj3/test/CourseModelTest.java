package in.co.rays.proj3.test; 
 

import in.co.rays.proj3.dto.CourseDTO; 
import in.co.rays.proj3.exception.ApplicationException; 
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.CourseModelHibImpl;
import in.co.rays.proj3.model.CourseModelInt; 
//import in.co.rays.proj3.model.CourseModelJDBCImpl; 
 
import java.sql.Timestamp; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.Iterator; 
import java.util.List; 
 
/** 
 * Course Model Test classes 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 *  
 */ 
public class CourseModelTest { 
 
    /** 
     * Model object to test 
     */ 
 
     public static CourseModelInt model = new CourseModelHibImpl(); 
 
   // public static CourseModelInt model = new CourseModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     */ 
    public static void main(String[] args) { 
         testAdd(); 
        // testDelete(); 
        //testUpdate(); 
        // testFindByName(); 
        //testFindByPK(); 
       // testSearch(); 
        // testList(); 
    }
   
    /** 
     * Tests add a Course
     */ 
    public static void testAdd() { 
 
        try { 
            CourseDTO dto = new CourseDTO();
            dto.setName("mjjjit"); 
            dto.setDescription("akudbkasjb");
            dto.setSemester(1);
            dto.setDuration("3year");
            dto.setCreatedBy("Admin"); 
            dto.setModifiedBy("Admin"); 
            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
            long pk = model.add(dto); 
            System.out.println("Test add succ"); 
            CourseDTO addedDto = model.findByPK(pk); 
            if (addedDto == null) { 
                System.out.println("Test add fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
 
    } 
    /** 
     * Tests delete a Course
     */ 
    public static void testDelete() { 
 
        try { 
            CourseDTO dto = new CourseDTO(); 
            long pk = 1; 
            dto.setId(pk); 
            model.delete(dto); 
            System.out.println("Test Delete succ"); 
            CourseDTO deletedDto = model.findByPK(pk); 
            if (deletedDto != null) { 
                System.out.println("Test Delete fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
    
    /** 
     * Tests update a Course
     */ 
    public static void testUpdate() { 
 
        try { 
            CourseDTO dto = model.findByPK(1); 
            dto.setName("Amit"); 
            model.update(dto); 
            CourseDTO updatedDTO = model.findByPK(1); 
            System.out.println("Test Update succ"); 
            if (!"Amit".equals(updatedDTO.getName())) { 
                System.out.println("Test Update fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
 
    } 
    /** 
     * Tests find a Course by Name. 
     */ 
 
    public static void testFindByName() { 
 
        try { 
            CourseDTO dto = model.findByName("Amit"); 
            if (dto == null) { 
                System.out.println("Test Find By Name fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getName()); 
           System.out.println(dto.getDescription());
           System.out.println(dto.getSemester());
           System.out.println(dto.getDuration());
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
    /** 
     * Tests find a Course by PK. 
     */ 
    public static void testFindByPK() { 
        try { 
            CourseDTO dto = new CourseDTO(); 
            long pk = 1; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription());
            System.out.println(dto.getDuration());
            System.out.println(dto.getSemester());
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
    /** 
     * Tests search a Course 
     */ 
 
    public static void testSearch() { 
        try { 
            CourseDTO dto = new CourseDTO(); 
            List list = new ArrayList(); 
            dto.setName("Amit"); 
            list = model.search(dto, 0, 10); 
            if (list.size() == 0) { 
                System.out.println("Test Search fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (CourseDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getDescription());
                System.out.println(dto.getDuration());
                System.out.println(dto.getSemester());
                System.out.println(dto.getCreatedBy()); 
                System.out.println(dto.getCreatedDatetime()); 
                System.out.println(dto.getModifiedBy()); 
                System.out.println(dto.getModifiedDatetime()); 
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
            CourseDTO dto = new CourseDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() == 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (CourseDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getName()); 
               System.out.println(dto.getDescription());
               System.out.println(dto.getDuration());
               System.out.println(dto.getSemester());
                System.out.println(dto.getCreatedBy()); 
                System.out.println(dto.getCreatedDatetime()); 
                System.out.println(dto.getModifiedBy()); 
                System.out.println(dto.getModifiedDatetime()); 
            } 
 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
} 
