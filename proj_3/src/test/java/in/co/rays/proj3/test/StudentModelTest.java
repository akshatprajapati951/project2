package in.co.rays.proj3.test; 
 
import in.co.rays.proj3.dto.StudentDTO; 
import in.co.rays.proj3.exception.ApplicationException; 
import in.co.rays.proj3.exception.DuplicateRecordException; 
import in.co.rays.proj3.model.StudentModelHibImpl; 
import in.co.rays.proj3.model.StudentModelInt; 
//import in.co.rays.proj3.model.StudentModelJDBCImpl; 
 
import java.sql.Timestamp; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.Iterator; 
import java.util.List; 
 
/** 
 * Student Model Test classes 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 *  
 */ 
public class StudentModelTest { 
 
    /** 
     * Model object to test 
     */ 
 
     public static StudentModelInt model = new StudentModelHibImpl(); 
 
  //  public static StudentModelInt model = new StudentModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     * @throws ParseException 
     */ 
    public static void main(String[] args) throws ParseException { 
       testAdd(); 
        //testDelete(); 
        // testUpdate(); 
         //testFindByPK(); 
       // testFindByEmailId(); 
        //testSearch(); 
        // testList(); 
 
    } 
 
    /** 
     * Tests add a Student 
     *  
     * @throws ParseException 
     */ 
    public static void testAdd() throws ParseException { 
 
        try { 
            StudentDTO dto = new StudentDTO(); 
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
 
          // dto.setId(1L); 
            dto.setFirstName("ram"); 
            dto.setLastName("kumawat"); 
            dto.setDob(sdf.parse("05/12/1990")); 
            dto.setMobileNo("9165254357"); 
            dto.setEmail("vipin.ch@nenosystems.com"); 
            dto.setCollegeId(1L); 
            dto.setCollegeName("jnu"); 
            dto.setCreatedBy("Admin"); 
            dto.setModifiedBy("Admin"); 
            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
            long pk = model.add(dto); 
            System.out.println("Test add succ"); 
            StudentDTO addedDto = model.findByPK(pk); 
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
     * Tests delete a Student 
     */ 
    public static void testDelete() { 
 
        try { 
            StudentDTO dto = new StudentDTO(); 
            long pk = 1; 
            dto.setId(pk); 
            model.delete(dto); 
            System.out.println("Test Delete succ"); 
            StudentDTO deletedDto = model.findByPK(pk); 
            if (deletedDto != null) { 
                System.out.println("Test Delete fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests update a Student 
     */ 
    public static void testUpdate() { 
 
        try { 
            System.out.println("Test Update scc111"); 
            StudentDTO dto = model.findByPK(1); 
            System.out.println("Test Update scc222"+dto.getCollegeName()); 
            dto.setFirstName("ram"); 
            dto.setLastName("sharma"); 
            dto.setMobileNo("9165254357"); 
            dto.setEmail("ghht@hg.com"); 
            dto.setCollegeId(1); 
            dto.setCollegeName("LNCT"); 
            model.update(dto); 
            StudentDTO updatedDTO = model.findByPK(1); 
            if (!"ram".equals(updatedDTO.getFirstName())) { 
                System.out.println("Test Update fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests find a Student by PK. 
     */ 
    public static void testFindByPK() { 
        try { 
            StudentDTO dto = new StudentDTO(); 
            long pk = 1L; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getFirstName()); 
            System.out.println(dto.getLastName()); 
            System.out.println(dto.getDob()); 
            System.out.println(dto.getMobileNo()); 
            System.out.println(dto.getEmail()); 
            System.out.println(dto.getCollegeId()); 
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests find a Student by EmailId. 
     */ 
    public static void testFindByEmailId() { 
        try { 
            StudentDTO dto = new StudentDTO(); 
            dto = model.findByEmailId("ghht@hg.com"); 
            if (dto == null) { 
                System.out.println("Test Find By EmailId fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getFirstName()); 
            System.out.println(dto.getLastName()); 
            System.out.println(dto.getDob()); 
            System.out.println(dto.getMobileNo()); 
            System.out.println(dto.getEmail()); 
            System.out.println(dto.getCollegeId()); 
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getModifiedDatetime()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests get Search 
     */ 
    public static void testSearch() { 
 
        try { 
            StudentDTO dto = new StudentDTO(); 
            List list = new ArrayList(); 
            dto.setFirstName("ravi"); 
            list = model.search(dto, 0, 10); 
            if (list.size() ==0) { 
                System.out.println("Test Serach fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (StudentDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getFirstName()); 
                System.out.println(dto.getLastName()); 
                System.out.println(dto.getDob()); 
                System.out.println(dto.getMobileNo()); 
                System.out.println(dto.getEmail()); 
                System.out.println(dto.getCollegeId()); 
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
            StudentDTO dto = new StudentDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() < 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (StudentDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getFirstName()); 
                System.out.println(dto.getLastName()); 
                System.out.println(dto.getDob()); 
                System.out.println(dto.getMobileNo()); 
                System.out.println(dto.getEmail()); 
                System.out.println(dto.getCollegeId()); 
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
