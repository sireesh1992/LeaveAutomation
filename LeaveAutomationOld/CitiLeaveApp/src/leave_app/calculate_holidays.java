package leave_app;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class calculate_holidays {
	 public static String convert(String startDate)
     {
 		String sdate="";
 		String[] st;
 		st = startDate.split("-");
 		sdate=st[2]+"-"+st[1]+"-"+st[0];
 		//System.out.println(sdate);
 		return sdate;
     }
     
     
     public static String calcuDate(String st,int n )
     {
    	 String[] str;
    	 str = st.split("-");
    	 Calendar cal = Calendar.getInstance();
    
		   cal.set(Calendar.DATE, Integer.valueOf(str[0]));
		   cal.set(Calendar.MONTH,Integer.valueOf(str[1])-1);
		   cal.set(Calendar.YEAR,Integer.valueOf(str[2]) );
		   //System.out.println(cal.get(Calendar.DATE));
		  
		   cal.set(Calendar.DATE,cal.get(Calendar.DATE)+n);
		 
		//System.out.println(cal.get(Calendar.DATE));		   
		Date date = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//System.out.println( "The date is: "+  sdf.format(date));
		String end =sdf.format(date);
		
		String[] monthNames = new String[12]; 
		String name = monthNames[cal.get(Calendar.MONTH)];
		return end;
     }
     
     
     public static int test(String sD, int numb) {
         int number=0;
        
         String startDate=convert(sD);
         String endDate=calcuDate(startDate,numb);
          if (checkDateString(startDate)
                   && checkDateString(endDate)) {
     
              GregorianCalendar firstdate = getDate(startDate);
              GregorianCalendar seconddate = getDate(endDate);
              number = countNumberOfSaturdaysAndSundays(firstdate, seconddate);                           
          } 
          return number;
     }

     public static boolean checkDateString(String datestring) {

          boolean status = false;
          if ((datestring.indexOf("-") > 0)
                   && (datestring.lastIndexOf("-") > datestring.indexOf("-"))) {
              int day = toInt(datestring.substring(0, datestring.indexOf("-")));
              int month = toInt(datestring.substring(datestring.indexOf("-") + 1,
                        datestring.lastIndexOf("-")));
              int year = toInt(datestring
                        .substring(datestring.lastIndexOf("-") + 1));
              status = ((day > 0) && (month > 0) && (year > 0) && (day <= 31)
                        && (month <= 12) && (String.valueOf(year).length() == 4));
          }
          return status;
     }

     public static GregorianCalendar getDate(String datestring) {
          int day = toInt(datestring.substring(0, datestring.indexOf("-")));
          int month = toInt(datestring.substring(datestring.indexOf("-") + 1,
                   datestring.lastIndexOf("-")));
          int year = toInt(datestring.substring(datestring.lastIndexOf("-") + 1));
          return new GregorianCalendar(year, month - 1, day);
     }

     private static int toInt(String s) {
          int n = -1;
          try {
              n = Integer.parseInt(s);
          } catch (NumberFormatException nfe) {
              System.err.println("NumberFormatException: " + nfe.getMessage());
              JOptionPane.showMessageDialog(null, s
                        + "is in the incorrect date format.",
                        "NumberFormatException", JOptionPane.ERROR_MESSAGE);
          }
          return n;
     }

     public static int countNumberOfSaturdaysAndSundays(GregorianCalendar first,
              GregorianCalendar second) {
          int count = 0;
          Calendar currentcalendarday = first;
          Calendar lastcalendarday = second;
          while (!currentcalendarday.equals(lastcalendarday)) {
              if (isOnSaturday(currentcalendarday))
                   count++;
              else if (isOnSunday(currentcalendarday))
                   count++;
              else if(isNational(currentcalendarday))
            	  count++;
              currentcalendarday.add(Calendar.DATE, 1);
          }
          return count;
     }

     public static boolean isOnSaturday(Calendar calendardate) {
        
          return (calendardate.get(Calendar.DAY_OF_WEEK) == 1);
     }

     public static boolean isOnSunday(Calendar calendardate) {
          
          return (calendardate.get(Calendar.DAY_OF_WEEK) == 7);
     }
     public static boolean isNational(Calendar calendardate) {
    	 {
    		 String h1[] ={"26-1-2015","15-8-2015","2-10-2015","3-4-2015","19-7-2015","22-10-2015","11-11-2015","25-12-2015"};
    		 boolean a =false;
    		String str= String.valueOf(calendardate.get(Calendar.DATE)) + "-"
             + String.valueOf(calendardate.get(Calendar.MONTH) + 1)
             + "-" + String.valueOf(calendardate.get(Calendar.YEAR));
    		

    		 for(String i:h1)
    		 {	  
    		
    			 if(str.equals(i))
    			 {      				  				
    				 a=true;
    				
    			 }
    		 }
    		
    		
    		 return a;
    	 }
     }
}
