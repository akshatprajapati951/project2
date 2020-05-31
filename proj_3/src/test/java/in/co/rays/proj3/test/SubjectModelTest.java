package in.co.rays.proj3.test; 
 
import in.co.rays.proj3.dto.SubjectDTO; 
import in.co.rays.proj3.exception.ApplicationException; 
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.SubjectModelHibImpl;
import in.co.rays.proj3.model.SubjectModelInt; 
//import in.co.rays.proj3.model.SubjectModelJDBCImpl; 
 
import java.sql.Timestamp; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.Iterator; 
import java.util.List; 
 
/** 
 * Subject Model Test classes 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 *  
 */ 
public class SubjectModelTest { 
 
    /** 
     * Model object to test 
     */ 
 
     public static SubjectModelInt model = new SubjectModelHibImpl(); 
 
   // public static SubjectModelInt model = new SubjectModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     */ 
    public static void main(String[] args) { 
         testAdd(); 
        // testDelete(); 
       // testUpdate(); 
        // testFindByName(); 
        //testFindByPK(); 
        //testSearch(); 
        // testList(); 
 
    } 
 
    /** 
     * Tests add a Subject 
     */ 
    public static void testAdd() { 
 
        try { 
            SubjectDTO dto = new SubjectDTO(); 
            //dto.setId(1L); 
            dto.setName("mjjjit"); 
            dto.setDescription("shdfbksdjb");
            dto.setCourseId(1);
            dto.setCreatedBy("Admin"); 
            dto.setModifiedBy("Admin"); 
            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
            long pk = model.add(dto); 
            System.out.println("Test add succ"); 
            SubjectDTO addedDto = model.findByPK(pk); 
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
     * Tests delete a Subject 
     */ 
    public static void testDelete() { 
 
        try { 
            SubjectDTO dto = new SubjectDTO(); 
            long pk = 1; 
            dto.setId(pk); 
            model.delete(dto); 
            System.out.println("Test Delete succ"); 
            SubjectDTO deletedDto = model.findByPK(pk); 
            if (deletedDto != null) { 
                System.out.println("Test Delete fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests update a Subject 
     */ 
    public static void testUpdate() { 
 
        try { 
            SubjectDTO dto = model.findByPK(1); 
            dto.setName("amit"); 
            model.update(dto); 
            SubjectDTO updatedDTO = model.findByPK(1); 
            System.out.println("Test Update succ"); 
            if (!"amit".equals(updatedDTO.getName())) { 
                System.out.println("Test Update fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests find a Subject by Name. 
     */ 
 
    public static void testFindByName() { 
 
        try { 
            SubjectDTO dto = model.findByName("amit"); 
            if (dto == null) { 
                System.out.println("Test Find By Name fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription()); 
            System.out.println(dto.getCourseId()); 
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests find a Subject by PK. 
     */ 
    public static void testFindByPK() { 
        try { 
            SubjectDTO dto = new SubjectDTO(); 
            long pk = 1; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription()); 
            System.out.println(dto.getCourseId()); 
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests search a Subject 
     */ 
 
    public static void testSearch() { 
        try { 
            SubjectDTO dto = new SubjectDTO(); 
            List list = new ArrayList(); 
            dto.setName("amit"); 
            list = model.search(dto, 0, 10); 
            if (list.size() == 0) { 
                System.out.println("Test Search fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (SubjectDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getDescription()); 
                System.out.println(dto.getCourseId()); 
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
            SubjectDTO dto = new SubjectDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() ==0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (SubjectDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getDescription()); 
                System.out.println(dto.getCourseId()); 
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
