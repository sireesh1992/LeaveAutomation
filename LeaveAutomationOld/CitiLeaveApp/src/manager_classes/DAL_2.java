package manager_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import leave_app.DAL_1;
import leave_app.Emp;

public class DAL_2 {

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

	public static List<Mgr> getleavedesc_pending(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid='"
							+ username + "' and stat='pending'");
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

	public static List<Mgr> getleavedesc_accept(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid='"
							+ username + "' and stat='Accept'");
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
	
	public static List<Mgr> getleavedesc_reject(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid='"
							+ username + "' and stat='Reject'");
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
	
	public static List<Mgr> getleavedesc_cancel(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid='"
							+ username + "' and stat='Cancel'");
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
	
	public static List<Mgr> mgetleavedesc_pending(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid_mgr='"
							+ username + "' and stat='pending'");
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

	public static List<Mgr> mgetleavedesc_accept(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid_mgr='"
							+ username + "' and stat='Accept'");
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
	
	public static List<Mgr> mgetleavedesc_reject(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid_mgr='"
							+ username + "' and stat='Reject'");
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
	
	public static List<Mgr> mgetleavedesc_cancel(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid_mgr='"
							+ username + "' and stat='Cancel'");
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


	
	public static List<Mgr> get_app_lv_desc(String username) {
		List<Mgr> temp = new ArrayList<Mgr>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));

		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from leave_desc where soeid='"
							+ username + "'");
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

	public static void updateStatus(int leave_id, int num_days, String category, String soeid_emp, String a_rbut) {
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("update leave_desc set stat=? where leave_id=?");
			st.setString(1, a_rbut);
			st.setInt(2, leave_id);
			st.executeUpdate();
			if(a_rbut.equalsIgnoreCase("cancel"))
			{
				if(category.equals("Casual"))
				{
					System.out.println(num_days+" "+soeid_emp);
					DAL_1.inc_cl(num_days, soeid_emp);
				}
				
				else if(category.equals("Sick"))
				{
					DAL_1.inc_sl(num_days, soeid_emp);
						
				}
				
				else if(category.equals("Mandatory"))
				{
						DAL_1.inc_ml(num_days, soeid_emp);
				}
				
				else if(category.equals("Paternity/Maternity"))
				{
					DAL_1.inc_pml(num_days, soeid_emp);
				}
			}
		} catch (Exception e) {
			System.out.println("Error getting data " + e);
		}
	}
	
	public static void updateStatus_cancel(int leave_id) {
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("update leave_desc set stat=? where leave_id=?");
			st.setString(1, "Cancel");
			st.setInt(2, leave_id);
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error getting data " + e);
		}
	}
	
	public static List<String> getUEmps(String username)
	{
		List<String> temp = new ArrayList<String>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select soeid from employees where reportsto='"
							+ username + "'");
			while (rs.next()) {
				temp.add(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return temp;
	}
	public static List<Emp> getEmps(String username)
	{
		List<Emp> temp = new ArrayList<Emp>();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from employees where reportsto='"
							+ username + "'");
			Emp obj;
			while (rs.next()) {

				obj = new Emp();
				obj.setSoeid(rs.getString(1));
				obj.setLname(rs.getString(2));
				obj.setFname(rs.getString(3));
				obj.setTitle(rs.getString(4));
				obj.setGender(rs.getString(5));
				obj.setAddress(rs.getString(8));
				obj.setCity(rs.getString(9));
				obj.setRegion(rs.getString(10));
				obj.setPcode(rs.getString(11));
				obj.setCountry(rs.getString(12));
				obj.setHphone(rs.getString(13));
				obj.setExt(rs.getString(14));
				obj.setMgr(rs.getString(15));
				obj.setEmp_stat(rs.getString(16));
				obj.setLvl(rs.getInt(17));
				temp.add(obj);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return temp;
	}

}
