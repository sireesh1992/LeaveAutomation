package leave_app;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class Leave_Available {
	private String username;
	public int cl;
	public int sl;
	public int ml;
	public int pml;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCl() {
		return cl;
	}

	public void setCl(int cl) {
		this.cl = cl;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public int getMl() {
		return ml;
	}

	public void setMl(int ml) {
		this.ml = ml;
	}

	public int getPml() {
		return pml;
	}

	public void setPml(int pml) {
		this.pml = pml;
	}

	public Leave_Available()
	{
		HttpSession sess = ServletActionContext.getRequest().getSession();
		if(sess.getAttribute("username")!=null){
			username = (String)sess.getAttribute("username");
		}	
	}
	
	public String execute()
	{
		cl=DAL_1.get_cl(username);
		sl=DAL_1.get_sl(username);
		ml=DAL_1.get_ml(username);
		pml=DAL_1.get_pml(username);
		return "success";
	}
}
