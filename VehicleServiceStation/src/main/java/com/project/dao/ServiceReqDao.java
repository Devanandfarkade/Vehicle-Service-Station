package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.entity.Customer;
import com.project.entity.CustomerVehicle;
import com.project.util.DBUtil;

public class ServiceReqDao implements AutoCloseable{

private Connection connection;
	
	public ServiceReqDao() throws SQLException{
		 this.connection=DBUtil.getConnection();
	 } 
	public void vehicleRequest(String vehicle_number,int cutsomer_id,int vehicle_id) throws SQLException {
		String sql="INSERT INTO customer_vehicles (vehicle_number,cutsomer_id,vehicle_id) values(?,?,?)";
		PreparedStatement pst=this.connection.prepareStatement(sql);
		pst.setString(1, vehicle_number);
		pst.setInt(2, cutsomer_id);
		pst.setInt(3, cutsomer_id);
	}
	
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public int addBill(double bill, int id) throws SQLException {
		String sql="UPDATE service_requests SET bill_amount =? where id=?";
		try(PreparedStatement pst=this.connection.prepareStatement(sql)) {
			pst.setDouble(1, bill);
			pst.setInt(2, id);
			pst.executeUpdate();
			return id; 
		} 
//		public Customer customerDetails(String vehicle_number)throws SQLException {
//			String sql="SELECT ID ,NAME ,MOBILE,ADDRESS FROM CUSTOMER INNER JOIN CUSTOMER_VEHICLE ON CUSTOMER.ID=CUSTOMER_VEHICLE.CUTOMER_ ID WHERE VEHICLE_number=?";
//			try (PreparedStatement pst=this.connection.prepareStatement(sql)) {
//				pst.setString(1, vehicle_number);
//				pst.executeQuery();
//				ResultSet rs=pst.executeQuery();
//				if(rs.next()) {
//					
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
	}
	
	
}
