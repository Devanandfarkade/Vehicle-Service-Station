package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.project.entity.Parts;
import com.project.util.DBUtil;

public class PartsDao implements AutoCloseable{

	private Connection connection;
	public PartsDao() throws SQLException {
		this.connection=DBUtil.getConnection();
	}
	public int addParts(Parts parts) throws SQLException {
		String sql="INSERT INTO parts(id,name,description,price) VALUES(?,?,?,?)";
		PreparedStatement pst=this.connection.prepareStatement(sql);
		pst.setInt(1, parts.getId());
		pst.setString(2, parts.getName());
		pst.setString(3, parts.getDescription());
		pst.setDouble(4, parts.getPrice());
		int cnt=pst.executeUpdate();
		return cnt;
	}
	public void getAllParts(List<Parts> partList) throws SQLException {
	 	String sql="SELECT * FROM parts ";
		try(PreparedStatement getAllParts = this.connection.prepareStatement(sql)) {
	 		ResultSet rs=getAllParts.executeQuery();
			while(rs.next()) {
				Parts part=new Parts(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
				partList.add(part);
			}
		}		
	}
	public int updateParts(int id,Double price) throws SQLException {
		String sql="UPDATE parts SET price =? WHERE id=?";
		try(PreparedStatement updateParts=this.connection.prepareStatement(sql)){
			updateParts.setInt(2, id);
			updateParts.setDouble(1, price);
			return updateParts.executeUpdate();
		}
	}
	
	public int deleteParts(int id) throws SQLException {
		String sql="DELETE FROM parts WHERE id=? ";
		try(PreparedStatement deleteParts=this.connection.prepareStatement(sql)){
			deleteParts.setInt(1, id);
			return deleteParts.executeUpdate();
		}
	}

	@Override
	public void close() throws Exception {
		this.connection.close();

	}
//	public List<Parts> specificParts(int id) throws SQLException {
//		String sql="SELECT * FROM parts WHERE id=?";
//		PreparedStatement pst =this.connection.prepareStatement(sql); 
//		pst.setInt(1, id);
//			ResultSet rs=pst.executeQuery();
//			List<Parts> specificPartsList=new ArrayList<>();
//			
//			while(rs.next()) {
//				Parts parts=new Parts(rs.getInt("id"),
//						rs.getString("name"),
//						rs.getString("description"),
//						rs.getDouble("price"));
//					specificPartsList.add(parts);
//		} 
//			return specificPartsList;
//		
//	}
	




	
}
