package admin_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import leave_app.Emp;
import manager_classes.Mgr;

public class DAL_3 {
	private static Connection getConnection() {
		Connection cn = null;
		try {
			// Dynamically load the Driver Class
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/proj_data", "root", "password");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading JDBC driver: " + e);
		} catch (SQLException e) {
			System.out.println("Connection Error: " + e);
		}
		return cn;
	}

	public static List<Emp> getAdminDetails() {
		List<Emp> temp = new ArrayList<Emp>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select * from employees");
			Emp obj1;
			while (rs.next()) {
				obj1 = new Emp();
				obj1.setSoeid(rs.getString(1));
				obj1.setLname(rs.getString(2));
				obj1.setFname(rs.getString(3));
				obj1.setTitle(rs.getString(4));
				obj1.setGender(rs.getString(5));
				obj1.setAddress(rs.getString(8));
				obj1.setCity(rs.getString(9));
				obj1.setRegion(rs.getString(10));
				obj1.setPcode(rs.getString(11));
				obj1.setCountry(rs.getString(12));
				obj1.setHphone(rs.getString(13));
				obj1.setExt(rs.getString(14));
				obj1.setMgr(rs.getString(15));
				obj1.setEmp_stat(rs.getString(16));
				obj1.setLvl(rs.getInt(17));
				temp.add(obj1);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return temp;
	}

	public static List<Mgr> getAdminLeaveDetails() {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select * from leave_desc ");
			Mgr m;
			while (rs.next()) {
				m = new Mgr();
				m.setLeave_id(rs.getInt(1));
				m.setSoeid_emp(rs.getString(2));
				m.setSoeid_mgr(rs.getString(3));
				m.setNum_days(rs.getInt(4));
				m.setSt_date(rs.getDate(5));
				m.setCategory(rs.getString(6));
				m.setComments(rs.getString(7));
				m.setStat(rs.getString(8));
				temp.add(m);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return temp;
	}

	public static void addEmployee(String soeid, String lname, String fname,
			String title, String gender, Date BirthDate, Date HireDate,
			String address, String city, String region, String pcode,
			String country, String hphone, String ext, String mgr, int lvl)
			throws Exception {
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			st.setString(1, soeid);
			st.setString(2, lname);
			st.setString(3, fname);
			st.setString(4, title);
			st.setString(5, gender);
			st.setDate(6, BirthDate);
			st.setDate(7, HireDate);
			st.setString(8, address);
			st.setString(9, city);
			st.setString(10, region);
			st.setString(11, pcode);
			st.setString(12, country);
			st.setString(13, hphone);
			st.setString(14, ext);
			st.setString(15, mgr);
			st.setString(16, "active");
			st.setInt(17, lvl);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error adding data " + e);
		}
	}

	public static void addlogin(String soeid) {
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("insert into login values(?,?)");
			st.setString(1, soeid);
			st.setString(2, "password");
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error getting data " + e);
		}
	}
	
	public static void addleave(String soeid,String gender) {
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("insert into leave_details values(?,?,?,?,?,?,?,?,?)");
			st.setString(1, soeid);
			st.setInt(2, 15);
			st.setInt(3, 15);
			st.setInt(4, 2);
			st.setInt(5, 2);
			st.setInt(6, 10);
			st.setInt(7, 10);
			if("female".equalsIgnoreCase(gender))
			{
				st.setInt(8, 90);
				st.setInt(9, 90);
			}
			else if("male".equalsIgnoreCase(gender))
			{
				st.setInt(8, 10);
				st.setInt(9, 10);
			}
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error getting data " + e);
		}
	}
	
	public static void delLeave(String soeid)
	{
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("delete from leave_details where soeid=?");
			st.setString(1, soeid);
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error getting data " + e);
		}
	}
	
	public static void delLogin(String soeid)
	{
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("delete from login where soeid=?");
			st.setString(1, soeid);
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error getting data " + e);
		}
	}
	
	public static void delEmp(String soeid)
	{
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("update employees set emp_stat=? where soeid=?");
			st.setString(1, "inactive");
			st.setString(2, soeid);
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error getting data " + e);
		}
	}
	
	public static int num_active()
	{
		int num;
		num=0;
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select count(*) from employees where Emp_Stat='active'");
			while (rs.next()) {
				num = rs.getInt(1);
				System.out.println(num);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return num;
	}
	
	public static int num_inactive()
	{
		int num=0;
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select count(*) from employees where Emp_Stat='inactive'");
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return num;
	}
	
	public static List<Leaves> get_leaves()
	{
		List<Leaves> temp = new ArrayList<Leaves>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from leave_details as l inner join employees as e on l.soeid = e.soeid;");
			Leaves m;
			while (rs.next()) {
				m = new Leaves();
				m.setSoeid(rs.getString(1));
				m.setFname(rs.getString(12));
				m.setLname(rs.getString(11));
				m.setCla(rs.getInt(3));
				m.setSla(rs.getInt(5));
				m.setMla(rs.getInt(7));
				m.setPmla(rs.getInt(9));
				temp.add(m);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return temp;
	}
	
}