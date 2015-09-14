package manager_classes;

import java.util.List;

import javax.servlet.http.HttpSession;

import leave_app.DAL_1;

import org.apache.struts2.ServletActionContext;

public class UpdateLeave {
	private int leave_id;
	private String A_Rbut;
	private String username;
	private List<Mgr> mlist,lrep,crep,rrep;
	public List<Mgr> getLrep() {
		return lrep;
	}

	public void setLrep(List<Mgr> lrep) {
		this.lrep = lrep;
	}

	public List<Mgr> getCrep() {
		return crep;
	}

	public void setCrep(List<Mgr> crep) {
		this.crep = crep;
	}

	public List<Mgr> getRrep() {
		return rrep;
	}

	public void setRrep(List<Mgr> rrep) {
		this.rrep = rrep;
	}

	private String soeid_emp;
	private int num_days;
	private String category;
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSoeid_emp() {
		return soeid_emp;
	}

	public void setSoeid_emp(String soeid_emp) {
		this.soeid_emp = soeid_emp;
	}

	
	public int getNum_days() {
		return num_days;
	}

	public void setNum_days(int num_days) {
		this.num_days = num_days;
	}

	public UpdateLeave()
	{
		HttpSession sess = ServletActionContext.getRequest().getSession();
		if(sess.getAttribute("username")!=null){
			username = (String)sess.getAttribute("username");
		}
	}
	
	public String execute()
	{
		DAL_2.updateStatus(leave_id, num_days, category, username, A_Rbut);
		if(category.equals("Casual") && A_Rbut.equals("Accept"))
		{
			DAL_1.dec_cl(num_days, username);
		}
		
		else if(category.equals("Sick") && A_Rbut.equals("Accept"))
		{
			DAL_1.dec_sl(num_days, username);
				
		}
		
		else if(category.equals("Mandatory") && A_Rbut.equals("Accept"))
		{
				DAL_1.dec_ml(num_days, username);
		}
		
		else if(category.equals("Paternity/Maternity") && A_Rbut.equals("Accept"))
		{
			DAL_1.dec_pml(num_days, username);
		}
		mlist = DAL_2.getleavedesc_pending(username);
		lrep = DAL_2.getleavedesc_accept(username);
		rrep = DAL_2.getleavedesc_reject(username);
		crep = DAL_2.getleavedesc_cancel(username);
		
		return "success";
	}

	public int getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}

	public String getA_Rbut() {
		return A_Rbut;
	}

	public void setA_Rbut(String a_Rbut) {
		A_Rbut = a_Rbut;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Mgr> getMlist() {
		return mlist;
	}

	public void setMlist(List<Mgr> mlist) {
		this.mlist = mlist;
	}
}
