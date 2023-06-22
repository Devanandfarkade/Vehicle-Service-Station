package com.project.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.project.entity.Maintainance;
import com.project.entity.Oil;
import com.project.entity.Service;
import com.project.entity.ServiceRequest;
import com.project.util.DBUtil;

public class ServiceDao implements AutoCloseable{
	private Connection connection;
	public ServiceDao() throws SQLException {
		this.connection=DBUtil.getConnection();
	}

	public ServiceRequest addNewService(String vehicleNumber) throws SQLException {
		String sql="INSERT INTO service_requests (vehicle_number)VALUES (?)";
		PreparedStatement pst=this.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, vehicleNumber);
		pst.executeUpdate();
		ResultSet rs=pst.getGeneratedKeys();
		if (rs.next()) {
			ServiceRequest service=new ServiceRequest(rs.getInt(1), vehicleNumber);
			return service;
		}
		return null;
		
	}
	public List<ServiceRequest> fetchTodayServiceList() throws SQLException {
		String sql="SELECT id,vehicle_number,DATE(request_date)as 'request_date',bill_amount FROM service_requests WHERE date(request_date)=date(sysdate()); ";
		PreparedStatement pst=this.connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		List<ServiceRequest>serviceList=new ArrayList<>();
		while(rs.next()) {
			
			ServiceRequest serviceReq=new ServiceRequest(rs.getInt("id"),rs.getString("vehicle_number"),rs.getDate("request_date"),rs.getDouble("bill_amount"));
			serviceList .add(serviceReq);
		}
		
		return serviceList;
	}
	


	public List<Service> serviceProvided(ServiceRequest serviceRequest) throws SQLException {
		String sql="select * from services where service_request_id=?";
		PreparedStatement pst=this.connection.prepareStatement(sql);
		pst.setInt(1, serviceRequest.getId());
		ResultSet rs= pst.executeQuery();
		serviceRequest.setServiceList(new ArrayList<>());
		while(rs.next()) {
			if(rs.getString("type").equals("maintenance")) {
				Service maintenance=new Maintainance(rs.getInt("id"),
						rs.getString("type"),
						rs.getDouble("labour_charges"),
						rs.getDouble("total_cost"),
						rs.getString("remark"),
						rs.getInt("service_request_id")
						);
				serviceRequest.getServiceList().add(maintenance);
			}
			else {
				Service oil=new Oil(rs.getInt("id"),
						rs.getString("type"),
						rs.getDouble("oil_cost"),
						rs.getDouble("total_cost"),
						rs.getString("remark"));
				serviceRequest.getServiceList().add(oil);
			}
		}
		return serviceRequest.getServiceList();
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
