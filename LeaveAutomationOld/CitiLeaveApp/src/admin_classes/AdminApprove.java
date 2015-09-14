package admin_classes;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpSession;

import leave_app.Emp;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class AdminApprove extends ActionSupport {
	private List<Emp> adminlist;
	private String username;
	private String password;
	private int num_inactive;
	private int num_active;
	private Date date;
	private List<Leaves> temp;
	
	public List<Leaves> getTemp() {
		return temp;
	}

	public void setTemp(List<Leaves> temp) {
		this.temp = temp;
	}

	public int getNum_inactive() {
		return num_inactive;
	}

	public void setNum_inactive(int num_inactive) {
		this.num_inactive = num_inactive;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNum_active() {
		return num_active;
	}

	public void setNum_active(int num_active) {
		this.num_active = num_active;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Emp> getAdminlist() {
		return adminlist;
	}

	public void setAdminlist(List<Emp> mlist) {
		this.adminlist = adminlist;
	}

	public AdminApprove()
	{
		HttpSession sess = ServletActionContext.getRequest().getSession();
		if(sess.getAttribute("username")!=null){
			username = (String)sess.getAttribute("username");
		}
		if(sess.getAttribute("password")!=null){
			password = (String)sess.getAttribute("password");
		}
	}

	public String execute() {
		if(username.equals("admin") && password.equals("admin"))
		{
			adminlist = DAL_3.getAdminDetails();
			num_inactive = DAL_3.num_inactive();
			num_active = DAL_3.num_active();
			date = new Date();
			temp=DAL_3.get_leaves();
			return "success";
		}
		return "failed";
	}
}
