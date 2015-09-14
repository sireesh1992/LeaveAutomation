package leaveApp.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport {
	private String category;
	private String correspondingvalue;
	List<AdminDisplayList> AdminDispList=new ArrayList<AdminDisplayList>();
	private String employeeName;
	private String SOEID;
	private String team;
	private String contact;
	private Date DOJ;
	private String designation;
	private String address;
	private String company;
	private String gender;
	
public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSOEID() {
		return SOEID;
	}

	public void setSOEID(String sOEID) {
		SOEID = sOEID;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getDOJ() {
		return DOJ;
	}

	public void setDOJ(Date dOJ) {
		DOJ = dOJ;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<AdminDisplayList> getAdminDispList() {
		return AdminDispList;
	}

	public void setAdminDispList(List<AdminDisplayList> adminDispList) {
		AdminDispList = adminDispList;
	}

	public String getCorrespondingvalue() {
		return correspondingvalue;
	}

	public void setCorrespondingvalue(String correspondingvalue) {
		this.correspondingvalue = correspondingvalue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String generateReports() {
		String cat = this.category;
		String retvalue="noResultFound";
		switch (cat) {
		case "Name": {
			AdminDispList=DAL.getDetailsByName(correspondingvalue);
			System.out.println("Inside Admin Action--- size of return list:"+AdminDispList.size());
		if(AdminDispList.size()!=0)
			{
			retvalue="fromadmin";
			}
			break;
		}
		case "SOEID": {
			AdminDispList=DAL.getDetailsBySOEID(correspondingvalue);
			if(AdminDispList.size()!=0)
			{
			retvalue="fromadmin";
			}
			break;
		}
		case "Team": {
			AdminDispList=DAL.getDetailsByTeam(correspondingvalue);
			if(AdminDispList.size()!=0)
			{
			retvalue="fromadmin";
			}
			break;
		}
		case "Category": {
			AdminDispList=DAL.getDetailsByCategory(correspondingvalue);
			if(AdminDispList.size()!=0)
			{
			retvalue="fromadmin";
			}
			break;
		}
		case "LeaveDate": {
			AdminDispList=DAL.getDetailsByLeaveDate(correspondingvalue);
			if(AdminDispList.size()!=0)
			{
			retvalue="fromadmin";
			}
			break;
		}
		default: {
			break;
		}
		}
		return retvalue;
	}
	public String addEmployee()
	{
		DAL.createEmployee(employeeName, SOEID, team, contact, DOJ, designation, address, company,gender);
		
		return "created";
	}
	
	public String deleteEmployee()
	{
		DAL.deleteEmployee(SOEID);
		return "deleted";
	}

	
	
	public String execute()
	{
		return "success";
	}
	
	

}
