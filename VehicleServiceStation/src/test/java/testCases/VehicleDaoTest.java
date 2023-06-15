package testCases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.dao.VehicleDao;
import com.project.entity.Vehicle;

public class VehicleDaoTest {
	//test case for add Vehicles
	@Test
	public void testAdd() {
		VehicleDao vehicleDao;
		try {
			vehicleDao=new VehicleDao();
			vehicleDao.addVehicle(new Vehicle("DUKE", "KTM"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// test case for get all vehicles
	@Test
	public void testGetAllVehicles() {
		VehicleDao vehicleDao;
		try {
			vehicleDao =new VehicleDao();
			List<Vehicle>vehicleList=new ArrayList<>();
			vehicleDao.getAllVehicle(vehicleList);
			vehicleList.forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testGetspecificVehicles() {
		VehicleDao vehicleDao;
		try {
			vehicleDao =new VehicleDao();
			vehicleDao.getSpecificVehicle(13);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
