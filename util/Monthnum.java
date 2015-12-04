package com.haier.neusoft.o2o.common.util;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 根据两个时间获取到逗号隔开日期的列表 
 * @author lichh
 * @since:2014-6-6
 */
@Slf4j
public class Monthnum {
    public static String getDayList(String beginDateStr, String endDateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sRet = new StringBuffer();
        
        Date beginDate = null;
        Date endDate = null;
        
        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        
        try {
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);
            
            beginGC = new GregorianCalendar(); 
            beginGC.setTime(beginDate); 
            
            endGC = new GregorianCalendar(); 
            endGC.setTime(endDate);
            
            while(beginGC.getTime().compareTo(endGC.getTime())<=0){
                if ("".equals(sRet.toString())) {
                    sRet.append("'").append(beginGC.get(Calendar.YEAR)).append("-");
                    if(beginGC.get(Calendar.MONTH)+1<10)
                    {
                    	sRet.append("0");
                    }
                    sRet.append(beginGC.get(Calendar.MONTH)+1).append("-");
                    if(beginGC.get(Calendar.DAY_OF_MONTH)<10)
                    {
                    	sRet.append("0");
                    }
                    sRet.append(beginGC.get(Calendar.DAY_OF_MONTH)).append("'");
                }
                else {
                    sRet.append(",'").append(beginGC.get(Calendar.YEAR)).append("-") ;
                    if(beginGC.get(Calendar.MONTH)+1<10)
                    {
                    	sRet.append("0");
                    }
                    sRet.append(beginGC.get(Calendar.MONTH)+1).append("-");
                    if(beginGC.get(Calendar.DAY_OF_MONTH)<10)
                    {
                    	sRet.append("0");
                    }
                    sRet.append(beginGC.get(Calendar.DAY_OF_MONTH)).append("'");
                }
                beginGC.add(Calendar.DAY_OF_MONTH,1);
            }
            return sRet.toString();
        }
        catch(Exception e) {
            return "";
        }
    }
    public static String[] getDayListArr(String beginDateStr, String endDateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        
        try {
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);
            
            beginGC = new GregorianCalendar(); 
            beginGC.setTime(beginDate); 
            
            endGC = new GregorianCalendar(); 
            endGC.setTime(endDate);
            String[] sRetarr = new String[(int)TimeUtilis.datemindate(endDate, beginDate)+1];
            String sRet = "";
            int i = 0;
            while(beginGC.getTime().compareTo(endGC.getTime())<=0){
                sRet = beginGC.get(Calendar.YEAR) + "-" ;
                if(beginGC.get(Calendar.MONTH)+1<10)
                {
                	sRet+="0";
                }
                sRet += (beginGC.get(Calendar.MONTH)+1)+"-";
                if(beginGC.get(Calendar.DAY_OF_MONTH)<10)
                {
                	sRet+="0";
                }
                sRet += (beginGC.get(Calendar.DAY_OF_MONTH))+"";
                beginGC.add(Calendar.DAY_OF_MONTH,1);
                sRetarr[i] = sRet;
                i++;
            }
            return sRetarr;
        }
        catch(Exception e) {
            return null;
        }
    }
    public static String getMonthList(String beginDateStr, String endDateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        StringBuffer sRet = new StringBuffer();
        
        Date beginDate = null;
        Date endDate = null;
        
        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        
        try {
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);
            
            beginGC = new GregorianCalendar(); 
            beginGC.setTime(beginDate); 
            
            endGC = new GregorianCalendar(); 
            endGC.setTime(endDate);
  
            while(beginGC.getTime().compareTo(endGC.getTime())<=0){
            	
                if ("".equals(sRet.toString())) {
                    sRet.append("").append(beginGC.get(Calendar.YEAR)).append("-");
                    if(beginGC.get(Calendar.MONTH)+1<10)
                    {
                    	sRet.append("0");
                    }
                    sRet.append(beginGC.get(Calendar.MONTH)+1).append("'");
                }
                else {
                    sRet.append(",'").append(beginGC.get(Calendar.YEAR)).append("-");
                    if(beginGC.get(Calendar.MONTH)+1<10)
                    {
                    	sRet.append("0");
                    }
                    sRet.append(beginGC.get(Calendar.MONTH)+1).append("'");
                }
                beginGC.add(Calendar.MONTH,1);
            }
            return sRet.toString();
        }
        catch(Exception e) {
            return "";
        }
    }
    public static String[] getMonthListArr(String beginDateStr, String endDateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        Date beginDate = null;
        Date endDate = null;
        
        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        
        try {
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);
            
            beginGC = new GregorianCalendar(); 
            beginGC.setTime(beginDate); 
            
            endGC = new GregorianCalendar(); 
            endGC.setTime(endDate);
            String[] sRetarr = new String[TimeUtilis.datemindateMonth(endDate, beginDate)+1];
            String sRet = "";
            int i = 0;
            while(beginGC.getTime().compareTo(endGC.getTime())<=0){
            	
                sRet = beginGC.get(Calendar.YEAR) + "-" ;
                if(beginGC.get(Calendar.MONTH)+1<10)
                {
                	sRet+="0";
                }
                sRet += (beginGC.get(Calendar.MONTH)+1);
               
                beginGC.add(Calendar.MONTH,1);
                sRetarr[i] = sRet;
                i++;
            }
            return sRetarr;
        }
        catch(Exception e) {
            return null;
        }
    }
    public static String getThisMonthList() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        StringBuffer sRet = new StringBuffer();
        
        Date beginDate = null;
        Date endDate = null;
        
        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;

        try {
            beginDate = f.parse(CommonTool.getCurrentDate("yyyy-01"));
            endDate = f.parse(CommonTool.getCurrentDate("yyyy-MM"));
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
            return "";
        }

            beginGC = new GregorianCalendar();
            beginGC.setTime(beginDate);

            endGC = new GregorianCalendar();
            endGC.setTime(endDate);

            while(beginGC.getTime().compareTo(endGC.getTime())<=0){
            	int day=0;
            	if(beginGC.getTime().compareTo(endGC.getTime())==0)
            	{
            		day= Integer.parseInt(CommonTool.getCurrentDate("dd"))-2;
            		if(day<1)
            		{
            			day=1;
            		}
            	}
            	else
            	{
            		day= beginGC.getActualMaximum(beginGC.DAY_OF_MONTH);
            	}
                if ("".equals(sRet.toString())) {
                    sRet.append("'").append(beginGC.get(Calendar.YEAR)).append("-") ;
                    if(beginGC.get(Calendar.MONTH)+1<10)
                    {
                    	sRet.append("0");
                    }
                    sRet.append(beginGC.get(Calendar.MONTH)+1).append("-").append(day<10?"0"+day:day).append("'");
                }
                else {
                    sRet.append(",'").append(beginGC.get(Calendar.YEAR)).append("-");
                    if(beginGC.get(Calendar.MONTH)+1<10)
                    {
                    	sRet.append("0");
                    }
                    sRet.append(beginGC.get(Calendar.MONTH)+1).append("-").append(day<10?"0"+day:day).append("'");
                }
                beginGC.add(Calendar.MONTH,1);
            }

        return sRet.toString();
    }
}