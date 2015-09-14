package leave_app;

import java.util.List;

import javax.servlet.http.HttpSession;

import manager_classes.DAL_2;
import manager_classes.Mgr;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class MyData extends ActionSupport {
	private Emp obj1;
	private String username;
	private String password;
	public int cl;
	public int sl;
	public int ml;
	public int pml;

	private List<Mgr> mlist, lrep, rrep, crep, mlistapp, mlistapa, mlistapr, mlistapc;

	public List<Mgr> getMlistapp() {
		return mlistapp;
	}

	public void setMlistapp(List<Mgr> mlistapp) {
		this.mlistapp = mlistapp;
	}

	public List<Mgr> getMlistapa() {
		return mlistapa;
	}

	public void setMlistapa(List<Mgr> mlistapa) {
		this.mlistapa = mlistapa;
	}

	public List<Mgr> getMlistapr() {
		return mlistapr;
	}

	public void setMlistapr(List<Mgr> mlistapr) {
		this.mlistapr = mlistapr;
	}

	public List<Mgr> getMlistapc() {
		return mlistapc;
	}

	public void setMlistapc(List<Mgr> mlistapc) {
		this.mlistapc = mlistapc;
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

	public List<Mgr> getLrep() {
		return lrep;
	}

	public void setLrep(List<Mgr> lrep) {
		this.lrep = lrep;
	}

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
	private List<Emp> l_Emp =null;
	private List<String> uemps=null;
	

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
	public void Leave_Available() {
		HttpSession sess = ServletActionContext.getRequest().getSession();
		if (sess.getAttribute("username") != null) {
			username = (String) sess.getAttribute("username");
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void MyDatacall() {
		HttpSession sess = ServletActionContext.getRequest().getSession();
		if (sess.getAttribute("username") != null) {
			username = (String) sess.getAttribute("username");
		}
		if (sess.getAttribute("password") != null) {
			password = (String) sess.getAttribute("password");
		}
	}

	public String execute() {
		MyDatacall();
		//Leave_Available();
		obj1 = DAL_1.get_details(username);
		cl = DAL_1.get_cl(username);
		sl = DAL_1.get_sl(username);
		ml = DAL_1.get_ml(username);
		pml = DAL_1.get_pml(username);
		mlist = DAL_2.getleavedesc_pending(username);
		lrep = DAL_2.getleavedesc_accept(username);
		rrep = DAL_2.getleavedesc_reject(username);
		crep = DAL_2.getleavedesc_cancel(username);
		
		if (obj1.getLvl() != 0) {
			l_Emp = DAL_2.getEmps(username);
			uemps = DAL_2.getUEmps(username);
			mlistapp = DAL_2.mgetleavedesc_pending(username);
			mlistapa = DAL_2.mgetleavedesc_accept(username);
			mlistapr = DAL_2.mgetleavedesc_reject(username);
			mlistapc = DAL_2.mgetleavedesc_cancel(username);
			return "success";
		} else if (obj1.getLvl() == 0){
			return "successe";
		}
			
		else
			return "failed";
	}

	public Emp getObj1() {
		return obj1;
	}

	public void setObj1(Emp obj1) {
		this.obj1 = obj1;
	}
}
