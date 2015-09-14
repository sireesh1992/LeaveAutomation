package admin_classes;

import java.util.List;
import manager_classes.Mgr;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class adminLeave extends ActionSupport {
	private List<Mgr> adminlist;

	public List<Mgr> getAdminlist() {
		return adminlist;
	}

	public void setAdminlist(List<Mgr> mlist) {
		this.adminlist = mlist;
	}

	/*
	 * public AdminApprove() { HttpSession sess =
	 * ServletActionContext.getRequest().getSession();
	 * if(sess.getAttribute("username")!=null){ username =
	 * (String)sess.getAttribute("username"); } }
	 */

	public String execute() {
		adminlist = DAL_3.getAdminLeaveDetails();
		for (Mgr i : adminlist) {
			System.out.println(i.getSoeid_mgr());
		}
		return "admin";
	}
}
