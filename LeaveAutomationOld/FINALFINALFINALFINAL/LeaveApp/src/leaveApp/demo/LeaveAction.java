package leaveApp.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LeaveAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startDate;
	private String endDate;
	private int noOfDays;
	private String confirmedDet;
	private String category;
	private String selectedTeamMember;
	private int leaveID;
	private String status;
	private String SOEID;
	private String cancel;

	

	public int getLeaveID() {
		return leaveID;
	}

	public void setLeaveID(int leaveID) {
		this.leaveID = leaveID;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSOEID() {
		return SOEID;
	}

	public void setSOEID(String sOEID) {
		SOEID = sOEID;
	}

	public String getSelectedTeamMember() {
		return selectedTeamMember;
	}

	public void setSelectedTeamMember(String selectedTeamMember) {
		this.selectedTeamMember = selectedTeamMember;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public SimpleDateFormat getDf() {
		return df;
	}

	public void setDf(SimpleDateFormat df) {
		this.df = df;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			// excluding start date
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				++workDays;
			}
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); // excluding
																			// end
																			// date

		return workDays+1;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String ret;
		String status = "Pending";
		ret = "fail";
		HttpSession session = ServletActionContext.getRequest().getSession();
		String SOEID = (String) session.getAttribute("SOEID");
		String designation = (String) session.getAttribute("designation");
		System.out.println("THE DESIGNATION FOR THE CURRENT USER IS "
				+ designation);
		if (designation.equals("Analyst") == false)
			status = "Approved";
		Date sd = df.parse(startDate);
		Date ed = df.parse(endDate);
		noOfDays = getWorkingDaysBetweenTwoDates(sd, ed);
		System.out.println("category before switch " + category);
		System.out.println("category is this " + category);
		confirmedDet = String.format(
				"startDate: %s<br>endDate: %s<br>noOfDays: %d", sd, ed,
				noOfDays);
		if (checkAvailability(category, SOEID).equals("success")) {
			DAL.addToLeaveQueue(SOEID, category, status, sd, ed, noOfDays);
			if (designation.equals("Analyst") == false)
				DAL.updateLeaveDetailsForManager(SOEID, category, noOfDays);
			ret = "success";
		}
		return ret;
	}

	public String checkAvailability(String category, String SOEID) {
		String returnAvailability = "success";
		int leavesAvailable;
		switch (category) {
		case "CasualLeave": {
			System.out.println("category AFTER switch" + category);

			leavesAvailable = DAL.getLeaveCategoryDetails(SOEID, category);
			if (noOfDays > leavesAvailable) {
				System.out.println("++++" + leavesAvailable);
				System.out.println("++++" + noOfDays);
				System.out.println("category AFTER switch" + category);

				returnAvailability = "fail";
			}
			break;
		}
		case "SickLeave": {
			leavesAvailable = DAL.getLeaveCategoryDetails(SOEID, category);
			if (noOfDays > leavesAvailable)
				returnAvailability = "fail";
			break;
		}
		case "MandatoryLeave": {
			leavesAvailable = DAL.getLeaveCategoryDetails(SOEID, category);
			if (noOfDays > leavesAvailable)
				returnAvailability = "fail";
			break;
		}
		case "MaternityLeave": {
			leavesAvailable = DAL.getLeaveCategoryDetails(SOEID, category);
			if (noOfDays > leavesAvailable)
				returnAvailability = "fail";
			break;
		}
		default: {
			returnAvailability = "success";
		}
		}
		return returnAvailability;
	}

	public String applyForMember() throws Exception {
		String memberDetails[];
		HttpSession session = ServletActionContext.getRequest().getSession();
		selectedTeamMember = (String) session
				.getAttribute("selectedTeamMember");
		System.out.println("selected team member " + selectedTeamMember);
		memberDetails = selectedTeamMember.split("  ");
		System.out.println("soeid of the selected member " + memberDetails[1]);
		Date sd = df.parse(startDate);
		Date ed = df.parse(endDate);
		noOfDays = getWorkingDaysBetweenTwoDates(sd, ed);

		DAL.addToLeaveQueue(memberDetails[1].trim(), category, "Approved", sd,
				ed, noOfDays);
		return "teammemberselected";
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getConfirmedDet() {
		return confirmedDet;
	}

	public void setConfirmedDet(String confirmedDet) {
		this.confirmedDet = confirmedDet;
	}
	
	
	public String leaveCancel() throws ParseException
	{
	System.out.println("i am inside the leave action 1");	
	Date sd = df.parse(startDate);
	
	DAL.deleteFromLeaveQueue(SOEID,leaveID,status,category,sd,noOfDays);
	
	System.out.println("i am inside the leave action 2");	

	return "cancelled";
	}
	

}
