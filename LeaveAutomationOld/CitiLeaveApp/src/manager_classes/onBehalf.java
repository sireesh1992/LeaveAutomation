package manager_classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import leave_app.DAL_1;
import leave_app.calculate_holidays;

public class onBehalf {
	private String title;
	private int bnum_days;
	private String bst_date;
	private Date start_date;
	private String bcategory;
	private String bcomments;
	private java.sql.Date sqlDate;
	

	public int getBnum_days() {
		return bnum_days;
	}


	public void setBnum_days(int bnum_days) {
		this.bnum_days = bnum_days;
	}


	public String getBcategory() {
		return bcategory;
	}


	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}


	public String getBcomments() {
		return bcomments;
	}


	public void setBcomments(String bcomments) {
		this.bcomments = bcomments;
	}


	
	
	public String getBst_date() {
		return bst_date;
	}


	public void setBst_date(String bst_date) throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		start_date=dateFormat.parse(bst_date);
		this.bst_date = bst_date;
		sqlDate = new java.sql.Date(start_date.getTime());
	}


	public String execute()
	{
		System.out.println(title+"\t"+bcategory);
		if(bcategory.equals("Casual"))
		{
			if(this.bnum_days <= DAL_1.get_cl(title))
			{
				DAL_1.take_leave_behalf(title, (bnum_days-calculate_holidays.test(bst_date,bnum_days)), sqlDate, bcategory, bcomments);
				//DAL_1.dec_cl(num_days, title);
				return "success";
			}
			else
				return "failed";
		}
		
		else if(bcategory.equals("Sick"))
		{
			if(this.bnum_days <= DAL_1.get_sl(title))
			{
				DAL_1.take_leave_behalf(title, (bnum_days-calculate_holidays.test(bst_date,bnum_days)), sqlDate, bcategory, bcomments);
				//DAL_1.dec_sl(num_days, title);
				return "success";
			}
			else
				return "failed";
		}
		
		else if(bcategory.equals("Mandatory"))
		{
			if(this.bnum_days <= DAL_1.get_ml(title))
			{
				DAL_1.take_leave_behalf(title, (bnum_days-calculate_holidays.test(bst_date,bnum_days)), sqlDate, bcategory, bcomments);
				//DAL_1.dec_ml(num_days, title);
				return "success";
			}
			else
				return "failed";
		}
		
		else if(bcategory.equals("Paternity/Maternity"))
		{
			if(this.bnum_days <= DAL_1.get_pml(title))
			{
				DAL_1.take_leave_behalf(title, (bnum_days-calculate_holidays.test(bst_date,bnum_days)), sqlDate, bcategory, bcomments);				//DAL_1.dec_pml(num_days, title);
				return "success";
			}
			else
				return "failed";
		}
		
		else
			return "failed";
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
