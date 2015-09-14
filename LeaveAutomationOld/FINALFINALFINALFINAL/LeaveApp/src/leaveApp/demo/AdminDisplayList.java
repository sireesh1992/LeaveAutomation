package leaveApp.demo;

import java.sql.Date;

public class AdminDisplayList {
	private String EmployeeName;
	private String SOEID;
	private String Team;
	private String category;
	private Date startDate;
	private Date endDate;
	private String status; 

	public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	public String getSOEID() {
		return SOEID;
	}
	public void setSOEID(String sOEID) {
		SOEID = sOEID;
	}
	public String getTeam() {
		return Team;
	}
	public void setTeam(String team) {
		Team = team;
	}
	public AdminDisplayList(String employeeName, String sOEID, String team,
			String category, Date startDate, Date endDate, String status) {
		super();
		EmployeeName = employeeName;
		SOEID = sOEID;
		Team = team;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
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
