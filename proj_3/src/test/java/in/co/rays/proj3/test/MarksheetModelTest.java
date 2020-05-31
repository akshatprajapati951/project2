package in.co.rays.proj3.test;

import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.MarksheetModelHibImpl;
import in.co.rays.proj3.model.MarksheetModelInt;
//import in.co.rays.proj3.model.MarksheetModelJDBCImpl; 
import in.co.rays.proj3.util.DataValidator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Marksheet Model Test classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
public class MarksheetModelTest {

	/**
	 * Model object to test
	 */

	public static MarksheetModelInt model = new MarksheetModelHibImpl();

	// public static MarksheetModelInt model = new MarksheetModelJDBCImpl();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testAdd();
		// testDelete();
		// testUpdate();
		// testFindByRollNo();
		// testFindByPK();
		// testList();
		testSearch();
		// testMeritList();

	}

	/**
	 * Tests add a Marksheet
	 */
	public static void testAdd() {

		try {
			MarksheetDTO dto = new MarksheetDTO();
			// dto.setId(10L);
			dto.setRollNo("7");
			dto.setName(" Marry");
			dto.setPhysics(88);
			dto.setChemistry(77);
			dto.setMaths(99);
			dto.setStudentId(1L);
			long pk = model.add(dto);
			System.out.println("Test add succ");
			MarksheetDTO addedDto = model.findByPK(pk);
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
	 * Tests delete a Marksheet
	 */
	public static void testDelete() {

		try {
			MarksheetDTO dto = new MarksheetDTO();
			long pk = 1;
			dto.setId(pk);
			model.delete(dto);
			MarksheetDTO deletedDto = model.findByPK(pk);
			if (deletedDto != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Marksheet
	 */
	public static void testUpdate() {

		try {
			MarksheetDTO dto = model.findByPK(1);
			dto.setName("new");
			dto.setChemistry(88);
			dto.setMaths(88);
			model.update(dto);
			System.out.println("Test Update ");
			MarksheetDTO updatedDTO = model.findByPK(1);
			if (!"new".equals(updatedDTO.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a marksheet by Roll No.
	 */

	public static void testFindByRollNo() {

		try {
			MarksheetDTO dto = model.findByRollNo("7");
			if (dto == null) {
				System.out.println("Test Find By RollNo fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getRollNo());
			System.out.println(dto.getName());
			System.out.println(dto.getPhysics());
			System.out.println(dto.getChemistry());
			System.out.println(dto.getMaths());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a marksheet by PK.
	 */
	public static void testFindByPK() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			long pk = 1;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getRollNo());
			System.out.println(dto.getName());
			System.out.println(dto.getPhysics());
			System.out.println(dto.getChemistry());
			System.out.println(dto.getMaths());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests list of Marksheets
	 */
	public static void testList() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			List list = new ArrayList();
			list = model.list(0, 6);
			if (list.size() == 0) {
				System.out.println("Test List fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (MarksheetDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getRollNo());
				System.out.println(dto.getName());
				System.out.println(dto.getPhysics());
				System.out.println(dto.getChemistry());
				System.out.println(dto.getMaths());
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
     * Tests search a Marksheets 
     */ 
 
    public static void testSearch() { 
        try { 
            MarksheetDTO dto = new MarksheetDTO(); 
            List list = new ArrayList(); 
          // dto.setName("Marry");
            dto.setPhysics(88);
            list = model.search(dto, 0, 10); 
            System.out.println(list.size());
            if (list.size() == 0) { 
                System.out.println("Test Search fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (MarksheetDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getRollNo()); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getPhysics()); 
                System.out.println(dto.getChemistry()); 
                System.out.println(dto.getMaths()); 
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
	 * Tests get the meritlist of Marksheets
	 */
	public static void testMeritList() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			List list = new ArrayList();
			list = model.getMeritList(0, 5);
			if (list.size() == 0) {
				System.out.println("Test List fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (MarksheetDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getRollNo());
				System.out.println(dto.getName());
				System.out.println(dto.getPhysics());
				System.out.println(dto.getChemistry());
				System.out.println(dto.getMaths());
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
