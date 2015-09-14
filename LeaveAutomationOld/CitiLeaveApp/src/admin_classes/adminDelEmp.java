package admin_classes;

import com.opensymphony.xwork2.ActionSupport;

public class adminDelEmp extends ActionSupport{
	private String soeid;

	public String getSoeid() {
		return soeid;
	}

	public void setSoeid(String soeid) {
		this.soeid = soeid;
	}
	
	public String execute() throws Exception {
		DAL_3.delLeave(soeid);
		DAL_3.delLogin(soeid);
		DAL_3.delEmp(soeid);
		return "success";
	}
}
