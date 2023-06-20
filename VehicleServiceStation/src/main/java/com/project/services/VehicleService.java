package com.project.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.dao.VehicleDao;
import com.project.entity.Customer;
import com.project.entity.CustomerVehicle;
import com.project.entity.SpecificCustomerVehicle;
import com.project.entity.Vehicle;

public class VehicleService {

	public static void addVehicle() {
//		CustomerService.getSpecificCustomer();
		Customer customer = CustomerService.getSpecificCustomer();
		try {
			if (customer != null) {
				System.out.println("Enter Vehicle number =");
				String vehicleNo = new Scanner(System.in).next();
				getAllVehicle();
				System.out.println("Select The Vehicle --> ");
				System.out.println("Enter The Vehicle id or Enter 0 for adding the company & Model ");

				int id = new Scanner(System.in).nextInt();
				if (id == 0) {
					System.out.println("Enter the company = ");
					String company = new Scanner(System.in).next();
					System.out.println("Enter Model =");
					String model = new Scanner(System.in).next();
					VehicleDao vehicleDao = new VehicleDao();

					vehicleDao.addVehicle(new Vehicle(company, model));
					System.out.println("Vehicle Added ....!!!");

				} else {
					VehicleDao vehicleDao = new VehicleDao();
					vehicleDao.customerVehicle(new CustomerVehicle(vehicleNo, customer.getId(), id));

					System.out.println("Vehicle  Added.....!!!! ");

				}
			} else {
				CustomerService.addCustomer();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// display all Vehicles
	public static void getAllVehicle() {
		List<Vehicle> vehicleList = new ArrayList<>();
		try (VehicleDao vehicleDao = new VehicleDao()) {
			vehicleDao.getAllVehicle(vehicleList);
			for (Vehicle Vehicle : vehicleList) {
				System.out.println(Vehicle);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// display Specific Vehicle
	public static List<SpecificCustomerVehicle> getSpecificVehicle() {
		Customer cust = CustomerService.getSpecificCustomer();
		 List<SpecificCustomerVehicle> vehicles = null;
		 try (VehicleDao vehicleDao = new VehicleDao()) {
			 vehicles = vehicleDao.specificCustomerVehicles(cust.getId());
			System.out.println("all vehicle for customer " + cust.getName() + " " + cust.getMobile());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicles;

	}

	public static void updateVehicle() {
		Scanner choice = new Scanner(System.in);
		System.out.print("Enter old_vehicleNumber = ");
		String old_vehicle_number=choice.next();
		try {
			if (old_vehicle_number != null) {
				VehicleDao vehicleDao=new VehicleDao();
				System.out.println("Enter new vehicle number = ");
				String new_vehicle_Number=choice.next();
				System.out.println("Vehicle number updated successfully...");

			System.out.println(vehicleDao.updateVehicle(old_vehicle_number, new_vehicle_Number));	
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

	public static void deleteVehicle() {
		getSpecificVehicle();
		System.out.print("enter Vehicle number to delete details of Vehicle = ");
		String vehicle_number= new Scanner(System.in).next();
		try {
			VehicleDao vehicleDao = new VehicleDao();
			if (vehicleDao.deleteVehicle(vehicle_number) > 0) {
				System.out.println("Vehicle data successfully deleted....!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
