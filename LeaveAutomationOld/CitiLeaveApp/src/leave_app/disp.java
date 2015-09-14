package leave_app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




import java.util.List;

import javax.servlet.http.HttpSession;

import manager_classes.DAL_2;
import manager_classes.Mgr;

import org.apache.struts2.ServletActionContext;

public class disp {
	private String username;
	private int num_days;
	private String st_date;
	private Date start_date;
	private String category;
	private String comments;
	private java.sql.Date sqlDate;
	private List<Mgr> mlist, lrep, rrep, crep;
	
	public List<Mgr> getMlist() {
		return mlist;
	}

	public void setMlist(List<Mgr> mlist) {
		this.mlist = mlist;
	}

	public List<Mgr> getLrep() {
		return lrep;
	}

	public void setLrep(List<Mgr> lrep) {
		this.lrep = lrep;
	}

	public List<Mgr> getRrep() {
		return rrep;
	}

	public void setRrep(List<Mgr> rrep) {
		this.rrep = rrep;
	}

	public List<Mgr> getCrep() {
		return crep;
	}

	public void setCrep(List<Mgr> crep) {
		this.crep = crep;
	}

	public disp()
	{
		HttpSession sess = ServletActionContext.getRequest().getSession();
		if(sess.getAttribute("username")!=null){
			username = (String)sess.getAttribute("username");
		}	
	}
	
	public int getNum_days() {
		return num_days;
	}
	public void setNum_days(int num_days) {
		this.num_days = num_days;
	}
	public String getSt_date() {
		return st_date;
	}
	public void setSt_date(String st_date) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		start_date=dateFormat.parse(st_date);
		this.st_date = st_date;
		System.out.println("setter: " + start_date);
		sqlDate = new java.sql.Date(start_date.getTime());
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String execute()
	{
		mlist = DAL_2.getleavedesc_pending(username);
		lrep = DAL_2.getleavedesc_accept(username);
		rrep = DAL_2.getleavedesc_reject(username);
		crep = DAL_2.getleavedesc_cancel(username);
		if(category.equals("Casual"))
		{
			if(this.num_days <= DAL_1.get_cl(username))
			{
				DAL_1.take_leave(username, (num_days-calculate_holidays.test(st_date,num_days)), sqlDate, category, comments);
				//DAL_1.dec_cl(num_days, username);
				return "success";
			}
			else
				return "failed";
		}
		
		else if(category.equals("Sick"))
		{
			if(this.num_days <= DAL_1.get_sl(username))
			{
				DAL_1.take_leave(username, (num_days-calculate_holidays.test(st_date,num_days)), sqlDate, category, comments);
				//DAL_1.dec_sl(num_days, username);
				return "success";
			}
			else
				return "failed";
		}
		
		else if(category.equals("Mandatory"))
		{
			if(this.num_days <= DAL_1.get_ml(username))
			{
				DAL_1.take_leave(username, (num_days-calculate_holidays.test(st_date,num_days)), sqlDate, category, comments);
				//DAL_1.dec_ml(num_days, username);
				return "success";
			}
			else
				return "failed";
		}
		
		else if(category.equals("Paternity/Maternity"))
		{
			if(this.num_days <= DAL_1.get_pml(username))
			{
				DAL_1.take_leave(username, (num_days-calculate_holidays.test(st_date,num_days)), sqlDate, category, comments);
				//DAL_1.dec_pml(num_days, username);
				return "success";
			}
			else
				return "failed";
		}
		
		else
			return "failed";
		
	}
}
