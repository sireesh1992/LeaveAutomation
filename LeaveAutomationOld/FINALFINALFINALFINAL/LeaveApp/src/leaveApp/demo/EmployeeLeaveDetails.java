package leaveApp.demo;

import java.sql.Date;

public class EmployeeLeaveDetails {
//	leaveID int not null auto_increment primary key,
//	SOEID varchar(10) not null,
//	category varchar(40) not null,
//	startdate date not null,
//	enddate date not null,
//	status varchar(40) not null
	
	int leaveID;
	String SOEID;
	String category;
	Date startDate;
	Date endDate;
	int noOfDays;
	
	
	
	
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	String status;
	public EmployeeLeaveDetails(int leaveID, String sOEID, String category,
			Date startDate, Date endDate,int noOfDetails, String status) {
		super();
		this.leaveID = leaveID;
		SOEID = sOEID;
		this.category = category;
		this.noOfDays=noOfDetails;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	public EmployeeLeaveDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public int getLeaveID() {
		return leaveID;
	}
	public void setLeaveID(int leaveID) {
		this.leaveID = leaveID;
	}
	public String getSOEID() {
		return SOEID;
	}
	public void setSOEID(String sOEID) {
		SOEID = sOEID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
