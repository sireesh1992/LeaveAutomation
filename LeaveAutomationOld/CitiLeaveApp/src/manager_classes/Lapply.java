package manager_classes;

import java.util.List;

import javax.servlet.http.HttpSession;

import leave_app.Emp;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Lapply extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private List<Emp> l_Emp;
	private List<String> uemps;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Emp> getL_Emp() {
		return l_Emp;
	}

	public void setL_Emp(List<Emp> l_Emp) {
		this.l_Emp = l_Emp;
	}

	public List<String> getUemps() {
		return uemps;
	}

	public void setUemps(List<String> uemps) {
		this.uemps = uemps;
	}

	public Lapply()
	{
		HttpSession sess = ServletActionContext.getRequest().getSession();
		if(sess.getAttribute("username")!=null){
			username = (String)sess.getAttribute("username");
		}
		
	}
	
	public String execute()
	{
		l_Emp=DAL_2.getEmps(username);
		uemps=DAL_2.getUEmps(username);
		return "success";
	}

	
}
