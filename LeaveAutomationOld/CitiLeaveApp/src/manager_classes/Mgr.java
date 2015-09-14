package manager_classes;

import java.util.Date;

public class Mgr {
	private int leave_id;
	private String soeid_emp;
	private String soeid_mgr;
	private int num_days;
	private Date st_date;
	private String comments;
	private String category;
	private String stat;
	
	public int getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	public String getSoeid_emp() {
		return soeid_emp;
	}
	public void setSoeid_emp(String soeid_emp) {
		this.soeid_emp = soeid_emp;
	}
	public String getSoeid_mgr() {
		return soeid_mgr;
	}
	public void setSoeid_mgr(String soeid_mgr) {
		this.soeid_mgr = soeid_mgr;
	}
	
	public int getNum_days() {
		return num_days;
	}
	public void setNum_days(int num_days) {
		this.num_days = num_days;
	}
	public Date getSt_date() {
		return st_date;
	}
	public void setSt_date(Date st_date) {
		this.st_date = st_date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	
}
