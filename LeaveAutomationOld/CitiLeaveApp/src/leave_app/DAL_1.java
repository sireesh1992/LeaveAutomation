package leave_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


public class DAL_1 {
	private static Connection getConnection() {
		Connection cn = null;
		try {
			// Dynamically load the Driver Class
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/proj_data",
					"root", "password");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading JDBC driver: " + e);
		} catch (SQLException e) {
			System.out.println("Connection Error: " + e);
		}
		return cn;
	}
	
	public static boolean Login_Validation(String uName, String pwd)
	{
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select password from login where soeid=\'"+uName+"\'");
			while(rs.next())
			{
			if(pwd.equals(rs.getString(1)))
			{
				return true;
			}
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return false;
	}
	
	public static Emp get_details(String soeid)
	{
		Emp obj=new Emp();
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from employees where soeid='"+soeid+"'");
			while(rs.next())
			{
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
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return obj;
	}
	
	public static void take_leave(String soeid, int num_days, Date st_date,
			String category, String comments) {
		String soeid_mgr;
		soeid_mgr = get_mgr(soeid);
		int ret;
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("insert into leave_desc values (null,?,?,?,?,?,?,?)");
			st.setString(1, soeid);
			st.setString(2, soeid_mgr);
			st.setInt(3, num_days);
			st.setDate(4, st_date);
			st.setString(5, category);
			st.setString(6, comments);
			if (soeid_mgr.equals("self")) {
				st.setString(7, "Accept");
				if(category.equals("Casual"))
				{
					DAL_1.dec_cl(num_days, soeid);
				}
				
				else if(category.equals("Sick"))
				{
					DAL_1.dec_sl(num_days, soeid);
						
				}
				
				else if(category.equals("Mandatory"))
				{
						DAL_1.dec_ml(num_days, soeid);
				}
				
				else if(category.equals("Paternity/Maternity"))
				{
					DAL_1.dec_pml(num_days, soeid);
				}
			} else {
				
				st.setString(7, "pending");
			}
			ret = st.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error getting data in take_leave " + e);
		}
	}
	
	public static String get_mgr(String soeid)
	{
		String cl=null;
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select reportsto from employees where soeid=\'"+soeid+"\'");
			while(rs.next())
			{
				cl = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return cl;
	}
	
	public static int get_cl(String soeid)
	{
		int cl=0;
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select cl_available from leave_details where soeid=\'"+soeid+"\'");
			while(rs.next())
			{
				cl = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return cl;
	}
	public static int get_sl(String soeid)
	{
		int cl=0;
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select sl_available from leave_details where soeid=\'"+soeid+"\'");
			while(rs.next())
			{
				cl = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return cl;
	}
	public static int get_ml(String soeid)
	{
		int cl=0;
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select ml_available from leave_details where soeid=\'"+soeid+"\'");
			while(rs.next())
			{
				cl = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return cl;
	}
	public static int get_pml(String soeid)
	{
		int cl=0;
		try {
			Connection cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select pml_available from leave_details where soeid=\'"+soeid+"\'");
			while(rs.next())
			{
				cl = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Data Read Error: " + e);
		}
		return cl;
	}
	
	public static void dec_cl(int n_d, String soeid)
	{
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update leave_details set cl_available=cl_available-"+n_d+" where soeid='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
	}
	
	public static void dec_sl(int n_d, String soeid)
	{
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update leave_details set sl_available=sl_available-"+n_d+" where soeid='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
	}
	
	public static void dec_ml(int n_d, String soeid)
	{
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update leave_details set ml_available=ml_available-"+n_d+" where soeid='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
	}
	
	public static void dec_pml(int n_d, String soeid)
	{
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update leave_details set pml_available=pml_available-"+n_d+" where soeid='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
	}
	
	public static void inc_cl(int n_d, String soeid)
	{
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update leave_details set cl_available=cl_available+"+n_d+" where soeid='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
	}
	
	public static void inc_sl(int n_d, String soeid)
	{
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update leave_details set sl_available=sl_available+"+n_d+" where soeid='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
	}
	
	public static void inc_ml(int n_d, String soeid)
	{
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update leave_details set ml_available=ml_available+"+n_d+" where soeid='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
	}
	
	public static void inc_pml(int n_d, String soeid)
	{
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update leave_details set pml_available=pml_available+"+n_d+" where soeid='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
	}
	
	public static void fetchApproval(String soeid,int n){
		try{
			Connection cn=getConnection();
			PreparedStatement st=cn.prepareStatement
					("update mgrs set lev="+n+" where soeid_emp='"+soeid+"'");
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error getting data"+e);
		}
		
		
	}

	public static void take_leave_behalf(String soeid, int num_days,
			Date st_date, String category, String comments) {
		String soeid_mgr;
		soeid_mgr = get_mgr(soeid);
		int ret = 0;
		try {
			Connection cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("insert into leave_desc values (null,?,?,?,?,?,?,?)");
			st.setString(1, soeid);
			st.setString(2, soeid_mgr);
			st.setInt(3, num_days);
			st.setDate(4, st_date);
			st.setString(5, category);
			st.setString(6, comments);
			st.setString(7, "Accept");
			if(category.equals("Casual"))
			{
				DAL_1.dec_cl(num_days, soeid);
			}
			
			else if(category.equals("Sick"))
			{
				DAL_1.dec_sl(num_days, soeid);
					
			}
			
			else if(category.equals("Mandatory"))
			{
					DAL_1.dec_ml(num_days, soeid);
			}
			
			else if(category.equals("Paternity/Maternity"))
			{
				DAL_1.dec_pml(num_days, soeid);
			}
			ret = st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error getting data" + e);
		}
	}
	
}
