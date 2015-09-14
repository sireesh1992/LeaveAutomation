package manager_classes;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Lapprove extends ActionSupport{
	private List<Mgr> mlist;
	private String username;
	
	public List<Mgr> getMlist() {
		return mlist;
	}

	public void setMlist(List<Mgr> mlist) {
		this.mlist = mlist;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Lapprove()
	{
		HttpSession sess = ServletActionContext.getRequest().getSession();
		if(sess.getAttribute("username")!=null){
			username = (String)sess.getAttribute("username");
		}	
	}
	
	public String execute()
	{
		mlist = DAL_2.getleavedesc_pending(username);
		
		return "success";
	}
}
