package admin_classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class adminAddEmp {
	private String soeid;
	private String lname;
	private String fname;
	private String title;
	private String gender;
	private String address;
	private String city;
	private String region;
	private String pcode;
	private String country;
	private String birthDate;
	private String hireDate;
	private String hphone;
	private String ext;
	private String mgr;
	private int lvl;
	private Date bdate;
	private Date hdate;
	private java.sql.Date sqlBdate;
	private java.sql.Date sqlHdate;

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		bdate = dateFormat.parse(birthDate);
		this.bdate = bdate;
		sqlBdate = new java.sql.Date(bdate.getTime());
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		hdate = dateFormat.parse(hireDate);
		this.hdate = hdate;
		sqlHdate = new java.sql.Date(hdate.getTime());
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getSoeid() {
		return soeid;
	}

	public void setSoeid(String soeid) {
		this.soeid = soeid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHphone() {
		return hphone;
	}

	public void setHphone(String hphone) {
		this.hphone = hphone;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getMgr() {
		return mgr;
	}

	public void setMgr(String mgr) {
		this.mgr = mgr;
	}

	public String execute() throws Exception {
		DAL_3.addEmployee(soeid, lname, fname, title, gender, sqlBdate, sqlHdate, address, city, region, pcode, country, hphone, ext, mgr, lvl);
		DAL_3.addlogin(soeid);
		DAL_3.addleave(soeid, gender);
		return "success";
	}
}
