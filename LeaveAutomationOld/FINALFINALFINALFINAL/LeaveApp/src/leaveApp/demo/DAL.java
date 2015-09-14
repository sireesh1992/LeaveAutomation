package leaveApp.demo;

//import AdminDisplayList;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.*;

public class DAL {

	// note the keyword static : enables usage without instance
	public static String testConnection() {
		String ret = "";
		Connection cn = null;

		try {
			// Dynamically load the driver class
			// the thing in the bracket is a class from the jar file loaded
			Class.forName("com.mysql.jdbc.Driver");
			ret += "Driver Loaded";
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/AutomationLeave", "root",
					"password");
			ret += "\nConnected to AutomationLeave Database";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(" Error loading JDBC Driver: " + e);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}

		return ret;
	}

	public static Connection getConnection() {
		Connection cn = null;
		try {
			// Dynamically load the driver class
			// the thing in the bracket is a class from the jar file loaded
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/AutomationLeave", "root",
					"password");
		} catch (ClassNotFoundException e) {
			System.out.println(" Error loading JDBC Driver: " + e);
		} catch (SQLException e) {
			System.out.println("Error getting the data : " + e);
		}

		return cn;
	}

	public static String getPassword(String SOEID) {
		String ret = "";
		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am in getpassword method");
			ResultSet rs = st
					.executeQuery("select password from LoginDetails where SOEID = '"
							+ SOEID + "'");
			while (rs.next()) {
				System.out.println("present");
				ret = rs.getString(1);
			}

			// System.out.println("password : "+ret);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		System.out.println("password : " + ret);
		return ret;

	}

	public static String getDetails(String SOEID) {
		String ret = "";
		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am in getdetails method");
			ResultSet rs = st
					.executeQuery("select * from EmployeeDetails where SOEID = '"
							+ SOEID + "'");
			while (rs.next()) {
				System.out.println("present");
				ret = rs.getString(1) + "  " + rs.getString(2) + "  "
						+ rs.getString(3) + "  " + rs.getString(5) + "  "
						+ rs.getString(6) + "  " + rs.getString(7) + "  "
						+ rs.getString(8);
			}

			// System.out.println("password : "+ret);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		System.out.println("getdetails method returns this " + ret);
		return ret;

	}

	public static String getLeaveDetails(String SOEID) {
		String ret = "";
		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am in getLeaveDetails");
			ResultSet rs = st
					.executeQuery("select * from leavedetails where SOEID = '"
							+ SOEID + "'");
			while (rs.next()) {
				System.out.println("present");
				ret = rs.getInt(3) + "  " + rs.getInt(4) + "  " + rs.getInt(5)
						+ "  " + rs.getInt(6) + "  " + rs.getInt(7) + "  "
						+ rs.getInt(8) + "  " + rs.getInt(9) + "  "
						+ rs.getInt(10) + "  ";
			}

			// System.out.println("password : "+ret);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}

		return ret;

	}

	public static int getLeaveCategoryDetails(String SOEID, String category) {
		int ret = 0;
		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am in getLeaveCategoryDetails method");
			ResultSet rs = st.executeQuery("select " + category
					+ "available from leavedetails where SOEID = '" + SOEID
					+ "'");
			while (rs.next()) {
				System.out.println("leaves in category " + category);
				ret = rs.getInt(1);
			}

			// System.out.println("password : "+ret);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		System.out.println("getLeaveCategoryDetails returns this " + ret);
		return ret;

	}

	public static void addToLeaveQueue(String SOEID, String category,
			String status, Date startDate, Date endDate, int noOfDays) {
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

		try {

			System.out.println("LEAVE APPLIED FOR " + SOEID);
			Connection cn = getConnection();
			java.sql.PreparedStatement st = cn
					.prepareStatement("insert into leavequeue values (NULL,?,?,?,?,?,?)");
			st.setString(1, SOEID);
			st.setString(2, category);
			st.setDate(3, sqlStartDate);
			st.setDate(4, sqlEndDate);
			st.setInt(5, noOfDays);
			st.setString(6, status);
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error getting the data : " + e);
		}

	}

	public static List<EmployeeLeaveDetails> getLeaveQueueToDisplay(String SOEID) {
		String ret = "";
		List<EmployeeLeaveDetails> EmployeeDet = new ArrayList<EmployeeLeaveDetails>();
		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am in getLeaveQueueToDisplay");
			ResultSet rs = st
					.executeQuery("select * from LeaveQueue where SOEID = '"
							+ SOEID + "'");
			while (rs.next()) {
				System.out.println(ret);
				System.out.println("this is how it comes out from the db "+rs.getDate(4));
				ret += rs.getInt(1) + "  " + rs.getString(2) + "  "
						+ rs.getString(3) + "  " + rs.getDate(4) + "  "
						+ rs.getDate(5) + "  " + rs.getInt(6) + "  "
						+ rs.getString(7) + "\n";

				System.out
						.println("before constructing employeeleavedetails list");

				EmployeeLeaveDetails temp = new EmployeeLeaveDetails(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4), rs.getDate(5), rs.getInt(6),
						rs.getString(7));
				System.out
						.println("after constructing employeeleavedetails list");

				EmployeeDet.add(temp);

			}

			// System.out.println("password : "+ret);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}

		return EmployeeDet;

	}

	public static List<EmployeeLeaveDetails> getTeamMemberLeaveDetails(
			String SOEID) {
		String ret = "";
		List<EmployeeLeaveDetails> EmployeeDet = new ArrayList<EmployeeLeaveDetails>();

		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am in getTeamMemberLeaveDetails");
			ResultSet rs = st.executeQuery("select * from leavequeue where "
					+ "soeid in (select soeid from employeedetails where"
					+ " team=(select team from employeedetails where soeid='"
					+ SOEID + "'))" + " and soeid not like '" + SOEID + "'");
			while (rs.next()) {

				// ret += rs.getString(1) + "  " + rs.getString(2) + "  "
				// + rs.getDate(3) + "  " + rs.getDate(4) + "  "
				// + rs.getString(5) + "\n";
				// System.out.println(ret);

				System.out
						.println("before constructing employeeleavedetails list");

				EmployeeLeaveDetails temp = new EmployeeLeaveDetails(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4), rs.getDate(5), rs.getInt(6),
						rs.getString(7));
				System.out
						.println("after constructing employeeleavedetails list");
				if (temp.status.equals("Pending"))
					EmployeeDet.add(temp);

			}
			// System.out.println("password : "+ret);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		return EmployeeDet;
	}

	public static void changeStatusOfLeave(int leaveid, String status,
			String category, int noOfDays) {

		try {

			status = status + "d";

			System.out.println("the status that has been passed is " + status);
			Connection cn = getConnection();
			java.sql.PreparedStatement st = cn
					.prepareStatement("update leavequeue set status=(?) where leaveid = (?)");
			st.setString(1, status);
			st.setInt(2, leaveid);
			st.executeUpdate();
			if (status.equals("Approved")) {

				System.out
						.println("i am here because status has been updated AND THE NOOFDAYS = "
								+ noOfDays);
				updateLeaveDetails(leaveid, category, noOfDays);
			}
		} catch (SQLException e) {
			System.out.println("Error changing the data : " + e);
		}

	}

	public static void updateLeaveDetails(int leaveid, String category,
			int noOfDays) {
		System.out.println("i am inside updateLeaveDetails");
		try {

			Connection cn = getConnection();

			java.sql.PreparedStatement st = cn
					.prepareStatement("update leavedetails set "
							+ category
							+ "taken="
							+ category
							+ "taken+"
							+ noOfDays
							+ ","
							+ category
							+ "available="
							+ category
							+ "available-"
							+ noOfDays
							+ " where soeid=(select soeid from leavequeue where leaveid="
							+ leaveid + ")");

			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error changing the data : " + e);
		}
		System.out.println("I AM GOING TO EXIT updateLeaveDetails");
	}

	public static List<String> getTeamMembers(String SOEID) {
		List<String> teamMemberList = new ArrayList<String>();
		String ret = "";
		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am IN getTeamMembers");
			ResultSet rs = st
					.executeQuery("select EmployeeName,soeid from employeedetails "
							+ "where team=(select team from employeedetails where soeid='"
							+ SOEID + "') and soeid not like '" + SOEID + "'");
			// +SOEID+
			// "') and soeid not like '"+SOEID+"'");
			System.out.println("i have done the query");
			while (rs.next()) {
				System.out.println("i am inside the while");
				// System.out.println("hey there ret "+ret);
				ret = rs.getString(1) + "  " + rs.getString(2) + "\n";
				teamMemberList.add(ret);
			}
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}

		return teamMemberList;

	}

	public static List<AdminDisplayList> getDetailsByName(
			String correspondingvalue) {
		List<AdminDisplayList> AdminDispList = new ArrayList<AdminDisplayList>();

		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("Before Query");
			ResultSet rs = st
					.executeQuery("select e.EmployeeName,e.SOEID,e.Team,lq.category,"
							+ "lq.startdate,lq.enddate,lq.status from employeedetails e join LeaveDetails"
							+ " ld on e.SOEID=ld.SOEID join leavequeue lq on ld.SOEID=lq.SOEID "
							+ "where  e.EmployeeName= '"
							+ correspondingvalue
							+ "'");
			System.out.println("After Query");
			while (rs.next()) {
				AdminDisplayList adl = new AdminDisplayList(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getDate(6), rs.getString(7));
				AdminDispList.add(adl);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection Error : " + e);
		}
		return AdminDispList;
	}

	public static List<AdminDisplayList> getDetailsBySOEID(
			String correspondingvalue) {
		String ret = "";
		List<AdminDisplayList> AdminDispList = new ArrayList<AdminDisplayList>();
		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT e.EmployeeName Name, e.SOEID SOEID, e.Team Team,"
							+ " lq.category Category, lq.startdate Start_Date,lq.enddate End_Date,"
							+ "lq.status Status from employeedetails e join LeaveDetails ld"
							+ " on e.SOEID=ld.SOEID join leavequeue lq on ld.SOEID=lq.SOEID"
							+ " where  e.SOEID='" + correspondingvalue + "'");
			while (rs.next()) {

				AdminDisplayList adl = new AdminDisplayList(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getDate(6), rs.getString(7));
				AdminDispList.add(adl);

			}

		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		return AdminDispList;

	}

	public static List<AdminDisplayList> getDetailsByTeam(
			String correspondingvalue) {
		String ret = "";
		List<AdminDisplayList> AdminDispList = new ArrayList<AdminDisplayList>();

		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT e.EmployeeName Name, e.SOEID SOEID, e.Team Team, lq.category Category,"
							+ " lq.startdate Start_Date,lq.enddate End_Date,lq.status Status"
							+ " from employeedetails e join LeaveDetails ld on e.SOEID=ld.SOEID"
							+ " join leavequeue lq on ld.SOEID=lq.SOEID where  e.Team='"
							+ correspondingvalue + "'");
			while (rs.next()) {

				AdminDisplayList adl = new AdminDisplayList(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getDate(6), rs.getString(7));
				AdminDispList.add(adl);

			}

		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		return AdminDispList;

	}

	public static List<AdminDisplayList> getDetailsByCategory(
			String correspondingvalue) {
		String ret = "";
		List<AdminDisplayList> AdminDispList = new ArrayList<AdminDisplayList>();

		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT e.EmployeeName Name, e.SOEID SOEID, e.Team Team, lq.category Category,"
							+ " lq.startdate Start_Date,lq.enddate End_Date,lq.status Status"
							+ " from employeedetails e join LeaveDetails ld on e.SOEID=ld.SOEID"
							+ " join leavequeue lq on ld.SOEID=lq.SOEID where  lq.category='"
							+ correspondingvalue + "'");
			while (rs.next()) {

				AdminDisplayList adl = new AdminDisplayList(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getDate(6), rs.getString(7));
				AdminDispList.add(adl);

			}
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		return AdminDispList;
	}

	public static List<AdminDisplayList> getDetailsByLeaveDate(
			String correspondingvalue) {
		String ret = "";
		List<AdminDisplayList> AdminDispList = new ArrayList<AdminDisplayList>();

		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT e.EmployeeName Name, e.SOEID SOEID, e.Team Team, lq.category Category,"
							+ " lq.startdate Start_Date,lq.enddate End_Date,lq.status Status"
							+ " from employeedetails e join LeaveDetails ld on e.SOEID=ld.SOEID"
							+ " join leavequeue lq on ld.SOEID=lq.SOEID where '"
							+ correspondingvalue
							+ "'"
							+ " between lq.startdate and lq.enddate");
			while (rs.next()) {

				AdminDisplayList adl = new AdminDisplayList(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getDate(6), rs.getString(7));
				AdminDispList.add(adl);

			}
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		return AdminDispList;
	}

	public static void createEmployee(String employeeName, String SOEID,
			String team, String contact, Date DOJ, String designation,
			String address, String company, String gender) {
		java.sql.Date sqlDOJ = new java.sql.Date(DOJ.getTime());

		try {

			System.out.println("creating new employee " + SOEID);
			Connection cn = getConnection();
			java.sql.PreparedStatement st = cn
					.prepareStatement("insert into employeedetails values (?,?,?,?,?,?,?,?,'Active')");
			st.setString(1, employeeName);
			st.setString(2, SOEID);
			st.setString(3, team);
			st.setDate(4, sqlDOJ);// parse
			st.setString(5, contact);
			st.setString(6, designation);
			st.setString(7, address);
			st.setString(8, company);
			st.executeUpdate();

			st = cn.prepareStatement("insert into LOGINDETAILS values (?,?)");
			st.setString(1, SOEID);
			st.setString(2, SOEID);
			st.executeUpdate();
			int maternityLeave = 0;
			if (gender.equalsIgnoreCase("F"))
				maternityLeave = 180;
			st = cn.prepareStatement("insert into LEAVEDETAILS values (?,?,0,0,0,0,20,180,10,"
					+ maternityLeave + ")");
			st.setString(1, employeeName);
			st.setString(2, SOEID);

			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error getting the data : " + e);
		}
	}

	// self report generation
	public static List<EmployeeLeaveDetails> getLeaveQueueToDisplayForSelf(
			String SOEID) {
		String ret = "";

		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date today = c.getTime(); // the midnight, that's the first second of
									// the day.
		java.sql.Date sqlToday = new java.sql.Date(today.getTime());
		System.out.println("todays date is " + sqlToday);
		List<EmployeeLeaveDetails> EmployeeDet = new ArrayList<EmployeeLeaveDetails>();
		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am in getLeaveQueueToDisplayForSelf");
			ResultSet rs = st
					.executeQuery("select * from LeaveQueue where SOEID = '"
							+ SOEID + "' and status='approved' and '"
							+ sqlToday + "' >startdate");
			while (rs.next()) {
				System.out.println(ret);
				ret += rs.getInt(1) + "  " + rs.getString(2) + "  "
						+ rs.getString(3) + "  " + rs.getDate(4) + "  "
						+ rs.getDate(5) + "  " + rs.getInt(6) + "  "
						+ rs.getString(7) + "\n";

				System.out
						.println("before constructing employeeleavedetails list");

				EmployeeLeaveDetails temp = new EmployeeLeaveDetails(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4), rs.getDate(5), rs.getInt(6), "Availed");
				System.out
						.println("after constructing employeeleavedetails list");

				EmployeeDet.add(temp);

			}

			// System.out.println("password : "+ret);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}

		return EmployeeDet;

	}

	public static List<EmployeeLeaveDetails> getTeamMemberLeaveReport(
			String SOEID) {
		List<EmployeeLeaveDetails> EmployeeDet = new ArrayList<EmployeeLeaveDetails>();

		try {
			Connection cn = getConnection();
			java.sql.Statement st = cn.createStatement();
			System.out.println("i am in getTeamMemberLeaveDetails");
			ResultSet rs = st.executeQuery("select * from leavequeue where "
					+ "soeid in (select soeid from employeedetails where"
					+ " team=(select team from employeedetails where soeid='"
					+ SOEID + "'))" + " and soeid not like '" + SOEID + "'");
			while (rs.next()) {

				// ret += rs.getString(1) + "  " + rs.getString(2) + "  "
				// + rs.getDate(3) + "  " + rs.getDate(4) + "  "
				// + rs.getString(5) + "\n";
				// System.out.println(ret);

				System.out
						.println("before constructing employeeleavedetails list");

				EmployeeLeaveDetails temp = new EmployeeLeaveDetails(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4), rs.getDate(5), rs.getInt(6),
						rs.getString(7));
				System.out
						.println("after constructing employeeleavedetails list");
				if (!temp.status.equals("Pending"))
					EmployeeDet.add(temp);

			}
			// System.out.println("password : "+ret);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
		return EmployeeDet;
	}

	public static void updateLeaveDetailsForManager(String SOEID,
			String category, int noOfDays) {
		System.out.println("i am inside updateLeaveDetailsForManager");
		try {

			Connection cn = getConnection();

			java.sql.PreparedStatement st = cn
					.prepareStatement("update leavedetails set " + category
							+ "taken=" + category + "taken+" + noOfDays + ","
							+ category + "available=" + category + "available-"
							+ noOfDays + " where soeid='" + SOEID + "'");

			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error changing the data : " + e);
		}
		System.out.println("I AM GOING TO EXIT updateLeaveDetails");
	}

	public static void deleteEmployee(String SOEID) {

		try {
			Connection cn = getConnection();
			java.sql.PreparedStatement st = cn
					.prepareStatement("update employeedetails set CURRENTSTATUS='Inactive' where soeid= '"
							+ SOEID + "'");
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error changing the data : " + e);
		}
	}

	public static void changePassword(String SOEIDnew, String newPassword) {
		try {

			Connection cn = getConnection();
			java.sql.PreparedStatement st = cn
					.prepareStatement("update loginDetails set password='"
							+ newPassword + "' where SOEID= '" + SOEIDnew + "'");
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error changing the data : " + e);
		}

	}

	public static void deleteFromLeaveQueue(String SOEID, int leaveID,
			String status, String category, Date startDate, int noOfDays) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date today = c.getTime(); // the midnight, that's the first second of
									// the day.
		java.sql.Date sqlToday = new java.sql.Date(today.getTime());
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		
		
		System.out.println("todays date is here : "+ sqlToday);
		System.out.println("start date is here : "+ sqlStartDate);
		
		try {

			Connection cn = getConnection();
		
		
				System.out.println("you can peacefully delete");
				if (status.equals("approved")) {
					java.sql.PreparedStatement st = cn
							.prepareStatement("update leavedetails set "
									+ category + "taken=" + category + "taken-"
									+ noOfDays + "," + category + "available="
									+ category + "available+" + noOfDays
									+ " where soeid='" + SOEID + "'");
					st.executeUpdate();

				}
				java.sql.PreparedStatement st = cn
						.prepareStatement("delete from leavequeue where leaveID='"
								+ leaveID + "'");
				st.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error changing the data : " + e);
		}

	}

}
