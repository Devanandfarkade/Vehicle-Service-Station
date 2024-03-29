package testCases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.dao.PartsDao;
import com.project.entity.Parts;

public class PartsDaoTest {
	//Test case for insert parts
	@Test
	public void testAddParts() {
		PartsDao partsDao;
		try {
			partsDao =new PartsDao();
			partsDao.addParts(new Parts(8,"Break_kit", "all thimngs are available hare about the breaks and everything", 2000));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Test case for get all parts 
	@Test
	public void testGetAllParts() {
		PartsDao partsDao;
		try {
			partsDao=new PartsDao();
			List<Parts>partsList=new ArrayList<>();
			partsDao.getAllParts(partsList);
			partsList.forEach(System.out::println);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateParts() {
		PartsDao partsDao;
		try {
			partsDao=new PartsDao();
			partsDao.updateParts(8, 50000d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteParts() {
		PartsDao partsDao;
		try {
			partsDao=new PartsDao();
			partsDao.deleteParts(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
