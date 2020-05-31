package in.co.rays.proj3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.TimeTableDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.TimeTableModelHibImpl;
import in.co.rays.proj3.model.TimeTableModelInt;

public class TimeTableModelTest {
	/** 
     * Model object to test 
     */ 
 
     public static TimeTableModelInt model = new TimeTableModelHibImpl(); 
 
     // public static StudentModelInt model = new StudentModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     * @throws ParseException 
     */ 
    public static void main(String[] args) throws ParseException { 
  testAdd(); 
       // testDelete(); 
       // testUpdate(); 
      // testFindByPK(); 
  
     testSearch(); 
       //  testList(); 
 
    }
    public static void testAdd() throws ParseException {
		 try { 
	            TimeTableDTO dto = new TimeTableDTO(); 
	            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
	 
	           // dto.setId(1); 
	           dto. setExamDate(sdf.parse("12/06/2018"));
	            dto.setSubjectId(2);
	            dto.setSubjectName("fhgfhhhggf");
	            dto.setCourseId(2);
	            dto.setCourseName("schbbhvd");
	            dto.setSemester("2");
	            dto.setExamTime("03:00-06:00pm");
	            dto.setCreatedBy("Admin"); 
	            dto.setModifiedBy("Admin"); 
	            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
	            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
	            long pk = model.add(dto); 
	            System.out.println("Test add succ"); 
	           TimeTableDTO addedDto = model.findByPK(pk); 
	            if (addedDto == null) { 
	                System.out.println("Test add fail"); 
	            }
	        } catch (ApplicationException e) { 
	            e.printStackTrace(); 
	        } catch (DuplicateRecordException e) { 
	            e.printStackTrace(); 
	        } 
	 
	    		
	}
	
	
    private static void testDelete() {
		// TODO Auto-generated method stub
		 try { 
	            TimeTableDTO dto = new TimeTableDTO(); 
	            long pk = 1; 
	            dto.setId(pk); 
	            model.delete(dto); 
	            System.out.println("Test Delete succ"); 
	            TimeTableDTO deletedDto = model.findByPK(pk); 
	            if (deletedDto != null) { 
	                System.out.println("Test Delete fail"); 
	            } 
	        } catch (ApplicationException e) { 
	            e.printStackTrace(); 
	        } 
	}

	public static void testUpdate() {
		try { 
            System.out.println("Test Update scc111"); 
            TimeTableDTO dto = model.findByPK(2); 
            dto.setSubjectName("new"); 
          
             
            model.update(dto); 
            TimeTableDTO updatedDTO = model.findByPK(2); 
            if (!"new".equals(updatedDTO.getSubjectName())) { 
                System.out.println("Test Update fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
		
	}

	public static void testFindByPK() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
        try { 
            TimeTableDTO dto = new TimeTableDTO(); 
            long pk = 1; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println( dto.getId()); 
            System.out.println (dto.getSemester());
            System.out.println(dto.getExamDate());
            System.out.println(dto.getExamTime());
            System.out.println(dto.getSubjectId());
            System.out.println( dto.getSubjectName());
            System.out.println (dto.getCourseId());
            System.out.println(dto.getCourseName());
            
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedDatetime()); 
              
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
	}

	/**UserDTO dto = new UserDTO(); 
     * Tests get Search 
     */ 
    public static void testSearch() { 
 
        try { 
            TimeTableDTO dto = new TimeTableDTO(); 
            List list = new ArrayList(); 
           // dto.setCourseName("schbbhvd"); 
             //dto.setId(1);
          //  dto.setSubjectName("new");
            dto.setSubjectId(1);
            //dto.setSemester("1");
            list = model.search(dto,0,10); 
            if (list.size() == 0) { 
                System.out.println("Test Serach fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (TimeTableDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getCourseId()); 
                System.out.println(dto.getCourseName()); 
                System.out.println(dto.getSemester()); 
                System.out.println(dto.getExamTime()); 
                System.out.println(dto.getExamDate());
                System.out.println(dto.getSubjectId());
                System.out.println(dto.getSubjectName()); 
                System.out.println(dto.getCreatedBy()); 
                System.out.println(dto.getModifiedBy()); 
                System.out.println(dto.getCreatedDatetime()); 
                System.out.println(dto.getModifiedDatetime()); 
                
                System.out.println("*********************");
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
            TimeTableDTO dto = new TimeTableDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() == 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (TimeTableDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getCourseId()); 
                System.out.println(dto.getCourseName()); 
                System.out.println(dto.getSemester()); 
                System.out.println(dto.getExamTime()); 
                System.out.println(dto.getExamDate());
                System.out.println(dto.getSubjectId());
                System.out.println(dto.getSubjectName());
                System.out.println(dto.getCreatedBy()); 
                System.out.println(dto.getModifiedBy()); 
                System.out.println(dto.getCreatedDatetime()); 
                System.out.println(dto.getModifiedDatetime()); 
            } 
 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 

}
