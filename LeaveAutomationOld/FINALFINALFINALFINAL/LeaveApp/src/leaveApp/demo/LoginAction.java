package leaveApp.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.HttpContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class LoginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String SOEID;
	String password;
	String name;
	String details[];
	String leaves[];
	private String company;
	private String address;
	private String designation;
	private String contact;
	private String team;
	private List<EmployeeLeaveDetails> leaveStatusQueue;
	private List<EmployeeLeaveDetails> leaveStatusQueueTeam;

	public List<EmployeeLeaveDetails> getLeaveStatusQueueTeam() {
		return leaveStatusQueueTeam;
	}
	public void setLeaveStatusQueueTeam(
			List<EmployeeLeaveDetails> leaveStatusQueueTeam) {
		this.leaveStatusQueueTeam = leaveStatusQueueTeam;
	}

	private String StatusQwithbreak="";
	int casualLeavesTaken;
	int sickLeavesTaken;
	int mandatoryLeavesTaken;
	int maternityLeavesTaken;

	int casualLeavesAvailable;
	int sickLeavesAvailable;
	int mandatoryLeavesAvailable;
	int maternityLeavesAvailable;
	List<String> teamMembers;//=new ArrayList<String>();
	String selectedTeamMember;
public String getSelectedTeamMember() {
		return selectedTeamMember;
	}
	public void setSelectedTeamMember(String selectedTeamMember) {
		this.selectedTeamMember = selectedTeamMember;
	}
	//	public LoginAction()
//	{	
//		
//		//HttpSession session = ServletActionContext.getRequest().getSession();
//		//session.setAttribute("SOEID", this.SOEID);
//		System.out.println("SOEID in loginaction "+SOEID);
//		//System.out.println("in constructor of login action");
//		//teamMembers=DAL.getTeamMembers(SOEID);
//		//System.out.println(teamMembers);
//	}
	public List<String> getTeamMembers() {
		return teamMembers;
	}
	public void setTeamMembers(List<String> teamMembers) {
		this.teamMembers = teamMembers;
	}
	public void setSOEID(String sOEID) {
		SOEID = sOEID;
	}

	public String getSOEID() {
		return SOEID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDetails() {
		if (this.execute().equals("success")) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("SOEID", this.SOEID);
	
			System.out.println("YOUR ID IS" + SOEID);
			details = DAL.getDetails(SOEID).split("  ");
			leaves = DAL.getLeaveDetails(SOEID).split("  ");
			teamMembers=DAL.getTeamMembers(SOEID);
					System.out.println(this.casualLeavesAvailable + "there there");		
			// System.out.println("here is"+ name);
			leaveStatusQueue=DAL.getLeaveQueueToDisplay(SOEID);
			
//			for(int i=0;i<leaveStatusQueue.length;i++)
//			{
//				StatusQwithbreak+=String.format("%s<br>",leaveStatusQueue[i]);
//			}		
			this.setValues();
			System.out.println("you are in login action get details and the value of designation is +"+designation);;
			session.setAttribute("designation", this.designation);

			if(this.designation.equals("Manager")){
				leaveStatusQueueTeam=DAL.getTeamMemberLeaveDetails(SOEID);
			}
			
			return this.designation;
		} else
			return "failure";
	}
	public void setValues()
	{
		this.name = details[0];
		this.SOEID = details[1];
		this.team = details[2];
		this.contact = details[3];
		this.designation = details[4];
		this.address = details[5];
		this.company = details[6];
		this.casualLeavesTaken = Integer.parseInt(leaves[0]);
		this.sickLeavesTaken = Integer.parseInt(leaves[1]);
		this.mandatoryLeavesTaken = Integer.parseInt(leaves[2]);
		this.maternityLeavesTaken = Integer.parseInt(leaves[3]);
		this.casualLeavesAvailable = Integer.parseInt(leaves[4]);
		this.sickLeavesAvailable = Integer.parseInt(leaves[5]);
		this.mandatoryLeavesAvailable = Integer.parseInt(leaves[6]);
		this.maternityLeavesAvailable = Integer.parseInt(leaves[7]);
	}
	public String setPageAgain()
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		String SOEIDnew=(String)session.getAttribute("SOEID");
		System.out.println("YOUR ID IS" + SOEIDnew);
		details = DAL.getDetails(SOEIDnew).split("  ");
		leaves = DAL.getLeaveDetails(SOEIDnew).split("  ");
		leaveStatusQueue=DAL.getLeaveQueueToDisplay(SOEIDnew);		
//		for(int i=0;i<leaveStatusQueue.length;i++)
//		{
//			StatusQwithbreak+=String.format("%s<br>",leaveStatusQueue[i]);
//		}
		this.setValues();
		
		System.out.println("YOU HAVE BEEN HERE ALL ALONG");
		
		
		return "set";
	}
	public String getStatusQwithbreak() {
		return StatusQwithbreak;
	}

	public void setStatusQwithbreak(String statusQwithbreak) {
		StatusQwithbreak = statusQwithbreak;
	}

	public List<EmployeeLeaveDetails> getLeaveStatusQueue() {
		return leaveStatusQueue;
	}

	public void setLeaveStatusQueue(List<EmployeeLeaveDetails> leaveStatusQueue) {
		this.leaveStatusQueue = leaveStatusQueue;
	}

	public String[] getLeaves() {
		return leaves;
	}

	public void setLeaves(String[] leaves) {
		this.leaves = leaves;
	}

	public int getCasualLeavesTaken() {
		return casualLeavesTaken;
	}

	public void setCasualLeavesTaken(int casualLeavesTaken) {
		this.casualLeavesTaken = casualLeavesTaken;
	}

	public int getSickLeavesTaken() {
		return sickLeavesTaken;
	}

	public void setSickLeavesTaken(int sickLeavesTaken) {
		this.sickLeavesTaken = sickLeavesTaken;
	}

	public int getMandatoryLeavesTaken() {
		return mandatoryLeavesTaken;
	}

	public void setMandatoryLeavesTaken(int mandatoryLeavesTaken) {
		this.mandatoryLeavesTaken = mandatoryLeavesTaken;
	}

	public int getMaternityLeavesTaken() {
		return maternityLeavesTaken;
	}

	public void setMaternityLeavesTaken(int maternityLeavesTaken) {
		this.maternityLeavesTaken = maternityLeavesTaken;
	}

	public int getCasualLeavesAvailable() {
		return casualLeavesAvailable;
	}

	public void setCasualLeavesAvailable(int casualLeavesAvailable) {
		this.casualLeavesAvailable = casualLeavesAvailable;
	}

	public int getSickLeavesAvailable() {
		return sickLeavesAvailable;
	}

	public void setSickLeavesAvailable(int sickLeavesAvailable) {
		this.sickLeavesAvailable = sickLeavesAvailable;
	}

	public int getMandatoryLeavesAvailable() {
		return mandatoryLeavesAvailable;
	}

	public void setMandatoryLeavesAvailable(int mandatoryLeavesAvailable) {
		this.mandatoryLeavesAvailable = mandatoryLeavesAvailable;
	}

	public int getMaternityLeavesAvailable() {
		return maternityLeavesAvailable;
	}

	public void setMaternityLeavesAvailable(int maternityLeavesAvailable) {
		this.maternityLeavesAvailable = maternityLeavesAvailable;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setDetails(String[] details) {
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute() {
		if (this.password.equals(DAL.getPassword(SOEID))) {
			System.out.println("correct pass");
			// HttpSession session =
			// ServletActionContext.getRequest().getSession();
			return "success";
		} else
			return "failure";
	}

	public String checkLeaveAvailability() {
		String returnAvailability = "success";
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		int noOfDays=(int) session.getAttribute("noOfDays");
		String category =  (String) session.getAttribute("category");
		
		System.out.println("here is your DAYS"+noOfDays);
		
		System.out.println("here is your category"+category);
		
		
		
		
		switch(category)
		{
		case "Casual Leave":
		{	
			if(noOfDays > this.casualLeavesAvailable)
				returnAvailability= "fail";
			System.out.println("casual leaves available now = "+this.casualLeavesAvailable);
			break;
		}
		case "Sick Leave":
		{
			if(noOfDays>this.sickLeavesAvailable)
				returnAvailability= "fail";
				break;
		}
		case "Mandatory Leave":
		{
			if(noOfDays>this.mandatoryLeavesAvailable)
				returnAvailability= "fail";
				break;
		}
		case "Maternity Leave":
		{
			if(noOfDays>this.maternityLeavesAvailable)
				returnAvailability= "fail";
				break;
			}
		
		
		}

		System.out.println("JJJJJ"+ returnAvailability);
		System.out.println("JJJJyJ"+ this.casualLeavesAvailable);

		return returnAvailability;
	}

}
