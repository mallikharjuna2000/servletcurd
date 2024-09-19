package com.codegnan.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.codegnan.beans.Employee;

public class EmpDBHandler {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adjava", "root", "root");
			;
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int save(Employee e) {
		int status = 0;
		try {
			Connection con = EmpDBHandler.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into emp(name,dept) values (?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getDept());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int update(Employee e) {
		int status = 0;
		try {
			Connection con = EmpDBHandler.getConnection();
			PreparedStatement ps = con.prepareStatement("update emp set name=?,dept=? where id=?");
			ps.setString(1, e.getName());
			ps.setString(2, e.getDept());
			ps.setInt(3, e.getId());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = EmpDBHandler.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from emp where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static Employee getEmployeeById(int id) {
		Employee e = new Employee();
		try {
			Connection con = EmpDBHandler.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from emp where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setDept(rs.getString(3));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	public static List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<Employee>();
		try {
			Connection con = EmpDBHandler.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from emp");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setDept(rs.getString(3));

				list.add(e);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
