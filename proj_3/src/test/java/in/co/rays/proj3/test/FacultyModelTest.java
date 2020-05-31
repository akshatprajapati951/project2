package in.co.rays.proj3.test; 
 
import in.co.rays.proj3.dto.FacultyDTO;
import in.co.rays.proj3.exception.ApplicationException; 
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.FacultyModelHibImpl;
import in.co.rays.proj3.model.FacultyModelInt; 
//import in.co.rays.proj3.model.FacultyModelJDBCImpl; 
 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date; 
import java.util.Iterator; 
import java.util.List; 
 
/** 
 * Faculty Model Test classes 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 *  
 */ 
public class FacultyModelTest { 
 
    /** 
     * Model object to test 
     */ 
 
     public static FacultyModelInt model = new FacultyModelHibImpl(); 
 
   // public static FacultyModelInt model = new FacultyModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     */ 
    public static void main(String[] args) { 
         testAdd(); 
         //testDelete(); 
       // testUpdate(); 
        // testFindByName(); 
       // testFindByPK(); 
       // testFindByLogin();
        //testSearch(); 
        // testList(); 
 
    } 
 
    /** 
     * Tests add a Faculty 
     */ 
    public static void testAdd() { 
 
        try { 
            FacultyDTO dto = new FacultyDTO(); 
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            //dto.setId(1L); 
            dto.setFirstName("guru");
            dto.setLastName("shivhare");
            dto.setExperience(2);
            dto.setSubjectId(1L);
            dto.setLogin("guru@gmail.com");
            dto.setCollegeId(3);
            dto.setCollegeName("mnit");
            dto.setSubjectName("physics");
            dto.setGender("male");
            dto.setMobileNo("9874565221");
            dto.setRoleId(5L);
            dto.setPassword("datt");
            dto.setConfirmPassword("datt");
            dto.setCreatedBy("Admin"); 
            dto.setModifiedBy("Admin"); 
            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
            long pk = model.add(dto); 
            System.out.println("Test add succ"); 
            /*FacultyDTO addedDto = model.findByPK(pk); 
            if (addedDto == null) { 
                System.out.println("Test add fail"); 
            } */
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests delete a Faculty 
     */ 
    public static void testDelete() { 
 
        try { 
            FacultyDTO dto = new FacultyDTO(); 
            long pk = 1; 
            dto.setId(pk); 
            model.delete(dto); 
            System.out.println("Test Delete succ"); 
            FacultyDTO deletedDto = model.findByPK(pk); 
            if (deletedDto != null) { 
                System.out.println("Test Delete fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests update a Faculty 
     */ 
    public static void testUpdate() { 
 
        try { 
            FacultyDTO dto = model.findByPK(1); 
            dto.setLogin("gurudatt@gmail.com");
            model.update(dto); 
            FacultyDTO updatedDTO = model.findByPK(1); 
            System.out.println("Test Update succ"); 
            if (!"gurudatt@gmail.com".equals(updatedDTO.getLogin())) { 
                System.out.println("Test Update fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests find a Faculty by Name. 
     */ 
 
   /* public static void testFindByName() { 
 
        try { 
            FacultyDTO dto = model.findByName("guru"); 
            if (dto == null) { 
                System.out.println("Test Find By Name fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getFirstName()); 
            System.out.println(dto.getLastName()); 
            System.out.println(dto.getExperience()); 
            System.out.println(dto.getCollegeId()); 
            System.out.println(dto.getRoleId());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getGender());
            System.out.println(dto.getSubjectId());
            System.out.println(dto.getLogin());
            System.out.println(dto.getPassword());
            System.out.println(dto.getConfirmPassword());
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } */
 
    /** 
     * Tests find a Faculty by PK. 
     */ 
    public static void testFindByPK() { 
        try { 
            FacultyDTO dto = new FacultyDTO(); 
            long pk = 1; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getFirstName()); 
            System.out.println(dto.getLastName()); 
            System.out.println(dto.getExperience()); 
            System.out.println(dto.getCollegeId()); 
            System.out.println(dto.getRoleId());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getGender());
            System.out.println(dto.getSubjectId());
            System.out.println(dto.getLogin());
            System.out.println(dto.getPassword());
            System.out.println(dto.getConfirmPassword());
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests search a Faculty 
     */ 
 
    public static void testSearch() { 
        try { 
            FacultyDTO dto = new FacultyDTO(); 
            List list = new ArrayList(); 
            dto.setLogin("gurudatt@gmail.com"); 
            list = model.search(dto, 0, 10); 
            if (list.size() == 0) { 
                System.out.println("Test Search fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (FacultyDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getFirstName()); 
                System.out.println(dto.getLastName()); 
                System.out.println(dto.getExperience()); 
                System.out.println(dto.getCollegeId()); 
                System.out.println(dto.getRoleId());
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getGender());
                System.out.println(dto.getSubjectId());
                System.out.println(dto.getLogin());
                System.out.println(dto.getPassword());
                System.out.println(dto.getConfirmPassword());
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
            FacultyDTO dto = new FacultyDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size()== 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (FacultyDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getFirstName()); 
                System.out.println(dto.getLastName()); 
                System.out.println(dto.getExperience()); 
                System.out.println(dto.getCollegeId()); 
                System.out.println(dto.getRoleId());
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getGender());
                System.out.println(dto.getSubjectId());
                System.out.println(dto.getLogin());
                System.out.println(dto.getPassword());
                System.out.println(dto.getConfirmPassword());
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
     * Tests find a Faculty by Login. 
     */ 
    public static void testFindByLogin() { 
        FacultyDTO dto = new FacultyDTO(); 
        try { 
            dto = model.findByLogin("gurudatt@gmail.com"); 
            if (dto == null) { 
                System.out.println("Test Find By login fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getFirstName()); 
            System.out.println(dto.getLastName()); 
            System.out.println(dto.getExperience()); 
            System.out.println(dto.getCollegeId()); 
            System.out.println(dto.getRoleId());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getGender());
            System.out.println(dto.getSubjectId());
            System.out.println(dto.getLogin());
            System.out.println(dto.getPassword());
            System.out.println(dto.getConfirmPassword());
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
}

} 
