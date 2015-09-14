package admin_classes;

import javax.servlet.http.HttpSession;

import leave_app.DAL_1;
import admin_classes.DAL_3;
import leave_app.Emp;

import org.apache.struts2.ServletActionContext;

public class Session_admin {
	

	private String username;

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

	public Emp getObj1() {
		return obj1;
	}

	public void setObj1(Emp obj1) {
		this.obj1 = obj1;
	}

	private String password;
	private Emp obj1;

	public String execute() {
		if (username.equals("admin") && password.equals("admin")) {
			obj1 = DAL_1.get_details(username);
			HttpSession sess = ServletActionContext.getRequest().getSession();
			sess.setAttribute("username", username);
			sess.setAttribute("password", password);
						return "success";
		} else {
			return "failure";
		}
	}
}
