package com.ssm.demo.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.w3c.dom.Document;
import sun.misc.BASE64Encoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Tools {
	/**
	 * 替换单引号 " 为中文引号“”
	 */
	public static String replaceYinHao(String s){
		int i=0;
		if(s!=null){
			while(s.indexOf("\"")>=0){
				if(i%2==0){
					s=s.replaceFirst("\"", "“");
				}else{
					s=s.replaceFirst("\"", "”");
				}
				i++;
			}
		}
		return s;
	}
	public static Document loadXML(String filename) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filename));
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	public static boolean doc2XmlFile(Document document, File targetFile) {
		boolean flag = true;
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(targetFile.toURI().getPath());
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}
	/**
	 * 删掉List中空对象
	 * @param l
	 * @return
	 */
	public static List removeEmptyObj(List l){
		if(l!=null&&l.size()!=0&&!l.isEmpty()){
			for(int i=0;i<l.size();i++){
				if(l.get(i)==null){
					l.remove(i);
					i--;
				}
			}
		}
		return l;
	}
	/*public static Page findPage(List l, Page page){
		List ls = null;
		if(isNotEmptyList(l)){
			page = PageFactory.createPage(page, l.size());	
			int offset = page.getCurrentRecNo();
			int length = page.getMaxNo();
			ls=new ArrayList();
			int maxNum=0;
			if((offset+length)>l.size()){
				maxNum=l.size();
			}else{
				maxNum=offset+length;
			}
			for(int i=offset;i<maxNum;i++){
				ls.add(l.get(i));	
			}
		}
		page.setLst(ls);
		return page;
	}*/
	
	
	public static String formatDigital (String digital,String style){
		DecimalFormat df = new DecimalFormat(style);
		String a = df.format(Double.valueOf(digital));
		return a;
	}

	/**
	 * 读取*.properties的信息
	 */
	public static String gainMessageFromConfig(String fileNameStr,String AttrStr){
		String s = "";
		 if(Tools.isNotEmptyString(AttrStr)){
				InputStream inputStream = Tools.class.getClassLoader().getResourceAsStream(fileNameStr);
				Properties properties = new Properties();
				try {
					properties.load(inputStream);
					s = properties.getProperty(AttrStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return s;
	}
	/**
	 * 为模糊查询提供字符串转换：
	 * example：中科软-->%中%科%软%
	 * @param s
	 * @return
	 */
	public static String fuzzyQueryString(String s){
		StringBuffer sb = new StringBuffer();
		sb.append("%");
		if(isNotEmptyString(s)){
			s=s.replaceAll(" ", "");
			for(int i=0;i<s.length();i++){
				sb.append(s.charAt(i)+"%");
			}
		}
		return sb.toString();
	}
	public static String isoToUtf8(String s){
		try {
			if(Tools.isNotEmptyString(s)){
				s=new String(s.getBytes("iso-8859-1"),"utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	//数据格式转换BASE64
	public static String toBASE64codeFile(File file){
		String codeString="";
		try{
			FileInputStream fis=new FileInputStream(file);
			BASE64Encoder encoder=new BASE64Encoder();
			byte[] b=new byte[228];
			int count=-1;
			while((count=fis.read(b))!=-1){
				codeString+=encoder.encode(b);
			}
			return codeString;
		}catch(Exception e){
			e.printStackTrace();
			return "";
			}
		}
	
	/**
	 * 检查String是否为空,不空 返回true
	 * @param s
	 * @return
	 */
	public static boolean isNotEmptyString(String s){
		if(s!=null&&!s.isEmpty()&&!"".equals(s)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 检查List是否为空,不空 返回true
	 * @param l
	 * @return
	 */
	public static boolean isNotEmptyList(List l){
		if(l!=null&&l.size()!=0&&!l.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	 /**
	  * 计算两个字符串的差异值  
	  * @param s
	  * @param t
	  * @return
	  */
	 public static int getLevenshteinDistance(CharSequence s, CharSequence t) {  
	         if (s == null || t == null) {  
	            //容错，抛出的这个异常是表明在传参的时候，传递了一个不合法或不正确的参数。 好像都这样用，illegal:非法。Argument:参数，证据。  
	           throw new IllegalArgumentException("Strings must not be null");  
           }  
	         //计算传入的两个字符串长度  
	         int n = s.length();   
	         int m = t.length();   
	         //容错，直接返回结果。这个处理不错  
	         if (n == 0) {  
	             return m;  
	         } else if (m == 0) {  
	             return n;  
       }  
      //这一步是根据字符串长短处理，处理后t为长字符串，s为短字符串，方便后面处理  
	        if (n > m) {  
	            CharSequence tmp = s;  
	            s = t;  
	            t = tmp;  
	            n = m;  
	            m = t.length();  
	         }  
	         //开辟一个字符数组，这个n是短字符串的长度  
	         int p[] = new int[n + 1];   
	         int d[] = new int[n + 1];   
	         //用于交换p和d的数组  
	         int _d[];  
	         int i;   
	         int j;   
	         char t_j;   
	         int cost;   
	         //赋初值  
	         for (i = 0; i <= n; i++) {  
	             p[i] = i;  
	         }  
       for (j = 1; j <= m; j++) {  
	            //t是字符串长的那个字符  
	           t_j = t.charAt(j - 1);  
	             d[0] = j;  
	             for (i = 1; i <= n; i++) {  
	                //计算两个字符是否一样，一样返回0。  
	                cost = s.charAt(i - 1) == t_j ? 0 : 1;  
	                 //可以将d的字符数组全部赋值。  
	                 d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);  
	            }  
	             //交换p和d  
	             _d = p;  
	             p = d;  
	             d = _d;  
	         }  
	         //最后的一个值即为差异值  
	        return p[n];  
	 } 
	/**
	 * 求两个字符串的相似度,返回相似度百分比
	 * @param s
	 * @param t
	 * @return
	 */
	public static double similarDegree(String s,String t){
	 if(s!=null&&t!=null){
		 int l=s.length()>t.length()?s.length():t.length();  
		 int d=getLevenshteinDistance(s,t); 
		 return 1-Double.parseDouble(d+"")/Double.parseDouble(l+""); 
	 }else{
		 return 0;
	 }
	} 
	
	
	public static String md5(String sourceText ) { 
		MD5 m = new MD5();
		return m.getMD5ofStr(sourceText);
	}
	/**
	 * @param date
	 * @return Date
	 */ 
	public static Date STD(String date) {
		if(!Tools.isNotEmptyString(date)){
			return new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date day = sdf.parse(date);
			return day;
		} catch (ParseException e) {
			
		}
		return null;
	}
	public static Date STD(String date,String type) {
		if(!Tools.isNotEmptyString(date)){
			return new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(isNotEmptyString(type)?type:"yyyy-MM-dd");
		try {
			Date day = sdf.parse(date);
			return day;
		} catch (ParseException e) {
			
		}
		return null;
	}
	/**字符串日格式化为type
	 * @param date
	 * @return Date
	 */ 
	public static String SDFormat(String date,String type) {
		if(!Tools.isNotEmptyString(type)){
			type = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		if(!Tools.isNotEmptyString(date)){
			return sdf.format(new Date());
		}
		try {
			Date day = sdf.parse(date);
			return sdf.format(date);
		} catch (ParseException e) {
			
		}
		return null;
	}
	/**
	 * 
	 * @param day
	 * @return yyyy-MM-dd
	 */
	public static String DTS(Date day) {
		if(day==null){
			day=new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(day);
	}
	public static String DTS(Date day,String type) {
		if(type==null||"".equals(type)){
			type="yyyy-MM-dd";
		}
		if(day==null){
			day=new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return sdf.format(day);
	}
	/**
	 * 对Date进行格式化，并返回Date型
	 * @param day
	 * @return yyyy-MM-dd
	 * @throws ParseException 
	 */
	public static Date DFormat(Date day,String type){
		if(day==null){
			day=new Date();
		}
		if(!isNotEmptyString(type))type = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		try {
			return sdf.parse(sdf.format(day));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return Date yyyy-MM-dd HH:mm:ss
	 */
	public static Date STD24(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date day = sdf.parse(date);
			return day;
		} catch (ParseException e) {

		}
		return null;
	}
	/**
	 * 
	 * @param day
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String DTS24(Date day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(day);
	}
	public static String DTSChina(String date){
		if(!Tools.isNotEmptyString(date)){
			date=Tools.DTS(new Date());
		}
		String chinaDate = "";
		if(date!=null&&!date.isEmpty()){
			String[] dates = date.split("-");
			if(dates.length>=1){
				chinaDate+=Integer.valueOf(dates[0])+"年";
			}
			if(dates.length>=2){
				chinaDate+=Integer.valueOf(dates[1])+"月";
			}
			if(dates.length>=3){
				chinaDate+=Integer.valueOf(dates[2])+"日";
			}
		}
		return chinaDate;
	}
	/**
	 * 格式为2012-05-10的当前日期：在页面显示默认日期时用的
	 * @param date
	 * @return
	 */
	public static String DTSChinaFormat(String date){
		if(!Tools.isNotEmptyString(date)){
			date=Tools.DTS(new Date());
		}
		String chinaDate = "";
		if(date!=null&&!date.isEmpty()){
			String[] dates = date.split("-");
			if(dates.length>=1){
				chinaDate+=Integer.valueOf(dates[0])+"-";
			}
			if(dates.length>=2){
				chinaDate+=Integer.valueOf(dates[1])+"-";
			}
			if(dates.length>=3){
				chinaDate+=Integer.valueOf(dates[2])+"";
			}
		}
		return chinaDate;
	}
	public static String checkIsStringNull(String input){//////判断字符串是空还是或null，用于生成sql
		if(input==null||"".equals(input)){
			return "null";
		}else{
			return input;
		}
	}
	public static String[] splitString(String resource,String regex){
		if(resource==null||"".equals(resource)){
			return null;
		}else{
			return resource.split(regex);
		}
	}

	public static double get2Decimal(double sourceData) {
		BigDecimal b = new BigDecimal(sourceData);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static Double toDoubleFromString(String input)throws Exception{
		if(input==null||"".equals(input)){
			return null;
		}else{
			return Double.parseDouble(input);
		}
	}
	
	
	public static String doubleToString(double d, int fNumber,boolean fillzero){
		if (fNumber < 0)
			fNumber = 0;
		String pattern = null;
		switch (fNumber){
			case 0: pattern = "#0"; 
			break;
			default: pattern = "#0."; 
			StringBuffer b = new StringBuffer(pattern);
			for (int i = 0; i < fNumber; i++){
				if(!fillzero){
					b.append('#');
				}else{
					b.append('0');
				}
			}
			pattern = b.toString();
			break;
		}
		DecimalFormat formatter = new DecimalFormat();
		formatter.applyPattern(pattern);
		String value = formatter.format(d);
		return value;
	}

	public static String replacehundred(String oldstr){
		if(oldstr==null||"null".equals(oldstr)){
			oldstr="";
		}
		if("%".equals(oldstr)){
			oldstr+="25";
		}
		oldstr="("+oldstr+")";
		return oldstr;
	}
	
	//从一号到当前时间的天数
	public static int days2current(Date date){
		int days = 0;
		try
		{
		    Date d1 = date;
		    Date d2 = STD(date.getYear()+1900+"-01-01");
		    long diff = d1.getTime() - d2.getTime();
		    days = (int)(diff / (1000 * 60 * 60 * 24));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return days+1;
	} 
	/**
	 * 按照指标极性以及指标是否需要按时间进度折算判断实际值与基准值的时间进度值大小关系
	 * 如果比基准值时间进度值差返回string的false
	 * @param date
	 * @param realValue
	 * @param baseValue
	 * @param polar
	 * @param isDateConvert
	 * @return
	 */
	public static String isLargeBase(Date date,Double realValue,Double baseValue,String polar,int isDateConvert){
		String flag = "true";
		int days = Tools.days2current(date);
		if("+".equals(polar)&&isDateConvert==1&&(realValue < baseValue*days/365)){
			flag = "false";
		}else if("+".equals(polar)&&isDateConvert==0&&(realValue < baseValue)){
			flag = "false";
		}
		if("-".equals(polar)&&isDateConvert==1&&(realValue > baseValue*days/365)){
			flag = "false";
		}else if("-".equals(polar)&&isDateConvert==0&&(realValue > baseValue)){
			flag = "false";
		}
		return flag;
	}
	
	/**
	 * 
	 * 传进一个yyyy-MM-dd或者是yyyy-MM的String类型，返回这个时间锁对应的上一个月份yyyy-MM
	 * 
	 * 默认如果date为""或null的话则以当前的时间
	 * 
	 * */
	public static String toLastMonth(String date)throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM");
		if("".equals(date)||date==null){
			date=sdf.format(new Date());
		}
		if(date.length()>7){
			Date d=sdf.parse(date.substring(0,date.lastIndexOf("-"))+"-01");
			d.setTime(d.getTime()-86400000);
			return sdf1.format(d);
		}else{
			Date d=sdf.parse(date+"-01");
			d.setTime(d.getTime()-86400000);
			return sdf1.format(d);
		}
		
	}
	/**
	 * 
	 * 传进一个yyyy-MM-dd或者是yyyy-MM的String类型，返回这个时间锁对应的上一个月份yyyy-MM
	 * 
	 * 默认如果date为""或null的话则以当前的时间
	 * 
	 * */
	public static String toThisMonth(String date)throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM");
		if("".equals(date)||date==null){
			return sdf1.format(new Date());
		}
		if(date.length()>7){
			return sdf1.format(sdf.parse(date));
		}else{
			return date;
		}
		
	}
	/**
	 * 返回上个月的最后一天
	 * 
	 * */
	public static String toLastMonthLastDay(String date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM");
		if("".equals(date)||date==null){
			date=sdf.format(new Date());
		}
		Calendar cal=Calendar.getInstance();
		try {
		if(date.length()>7){
			Date d=sdf.parse(date.substring(0,date.lastIndexOf("-"))+"-01");
			d.setTime(d.getTime()-86400000);
			cal.setTime(d);
			return sdf1.format(d)+"-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}else{
			Date d=sdf.parse(date+"-01");
			d.setTime(d.getTime()-86400000);
			cal.setTime(d);
			return sdf1.format(d)+"-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**根据传进来的yyyy-MM字符串，获取到对应的时间的最后一天
	 * 
	 * */
	public static String toThisMonthLastDay(String date)throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		if(date==null||"".equals(date)){
			date=sdf.format(new Date());
		}
		if(date.length()>7){
			date=date.substring(0,date.lastIndexOf("-"));
		}
		Calendar cal=Calendar.getInstance();
			
		cal.setTime(sdf.parse(date));
		date=date+"-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return date;
	}
	public static String toThisYearLastDay(String date){
		if(date!=null&&!"".equals(date)){
			if(date.length()>=4){
				return date.substring(0,4)+"-12-31";
			}
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		return sdf.format(new Date())+"-12-31";
	}
	public static String toThisYear(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		if(date==null||"".equals(date)){
			return sdf.format(new Date());
		}else{
			if(date.length()>4){
				return date.substring(0,4);
			}else{
				return sdf.format(new Date());
			}
		}
	}
	public static String toThisYearThisMonth(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		if(date==null||"".equals(date)){
			return sdf.format(new Date());
		}else{
			if(date.length()>7){
				return date.substring(0,7);
			}else{
				return "";
			}
		}
	}
	public static Double stringToDouble(String string){
		if(string==null||"".equals(string)){
			return new Double(0);
		}else{
			return Double.parseDouble(string.replaceAll(",", ""));
		}
	}
	/**
	 * 返回起止时间的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int daysBetween2date(Date startDate,Date endDate){
		if(startDate == null || endDate == null)
			return -1;
		startDate = STD(DTS(startDate));
		endDate = STD(DTS(endDate));
		return (int)((endDate.getTime()-startDate.getTime())/(1000*3600*24));
	}
	/**
	 * 0上午；1下午,12点整也认为是上午
	 * @param date
	 * @return
	 */
	public static int dateIsAMorPM(Date date){
		int hour = date.getHours();
		if(hour>12)
			return 1;
		else if(hour== 12){
			if(date.getMinutes()>0)
				return 1;
			else 
				return 0;
		}
		else
			return 0;
	}
	/**
	 * 0~999以内的正整数转成汉字形式
	 * 如：23-->二十三
	 * @param num
	 * @return
	 */
	public static String numToCN(Long num){
		String parseCN = "";
		String[] unit = {"百","十"};
		String[] numCN = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"}; 
		int length = num.toString().length();
        if(length==3){//3位
        	parseCN += numCN[Integer.parseInt(num.toString().substring(0,1))]+unit[0];
        	if(!"00".equals(num.toString().substring(1))){
        		if(!"0".equals(num.toString().substring(1,2))){
        			parseCN += num.toString().substring(1,2)+unit[1];
        		}else{
        			parseCN += numCN[0];
        		}
        		if(!"0".equals(num.toString().substring(2,3))){
        			parseCN += numCN[Integer.parseInt(num.toString().substring(2,3))];
        		}
        	}
        }else if(length==2){//2位
        	if(!"1".equals(num.toString().substring(0,1))){
        		parseCN += numCN[Integer.parseInt(num.toString().substring(0,1))]+unit[1];
        	}else{
        		parseCN += unit[1];
        	}
        	if(!"0".equals(num.toString().substring(1,2))){
    			parseCN += numCN[Integer.parseInt(num.toString().substring(1,2))];
    		}
        }else if(length==1){//1位
        	parseCN += numCN[Integer.parseInt(num.toString())];
        }
		return parseCN;
	}
	/**
	 * 获得当前日期和 当年1月1号
	 * @return
	 */
	public static String findCurrentDate(){
		Calendar   c   =   Calendar.getInstance();//可以对每个时间域单独修改 
		  int   year   =   c.get(Calendar.YEAR); 
		  int   month   =   c.get(Calendar.MONTH)+1; 
		  int   date   =   c.get(Calendar.DATE);
		  String dateMonth=null;
		  String dateDay=null;
		  if(month<10){
			   dateMonth="-0"+month;
		  }else{
			   dateMonth="-"+month;
		  }
		  if(date<10){
			  dateDay="-0"+date;
		  }else{
			  dateDay="-"+date;
		  }
		  String date1=year+dateMonth+dateDay; 
		return date1;
	}
	public static String findYearStartDate(){
		Calendar ca=Calendar.getInstance();
		int Year=ca.get(Calendar.YEAR);
		String date=Year+"-01-01";
		return date;
	}
	
	/**
	 * 将给定的Date 类型日期转换为 本年度第一天String 日期 例如 1990-12-06  转换为：1990-01-01
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String getBeginDateOfYear(Date date){
		if(date==null) date = new Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy");
		String dateBegin = localSimpleDateFormat.format(date);
		return dateBegin+"-01-01";
		
	}

	/**
	 * 由给定的时间取得年份
	 * @param date
	 * @return
	 */
	public static String getYearFromDate(Date date) {
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy");
		String dateBegin = localSimpleDateFormat.format(date);
		return dateBegin;
	}
	
	//根据用户超找其所在部门
	/*public static SysDepartment findRootDepByUser(SysUser user){
		SysDepartment dep = null;
		if(user!=null){
			dep=user.getSysDepartment();
			while(dep.getSysDepartment()!=null){
				dep=dep.getSysDepartment();
			}
		}
		return dep;
	}*/
	
	public static double calculateContractValue(double value , String unit){
		double calValue = 0;
		 if("元".equals(unit)){
			 calValue = value ;
		 }else if("万元".equals(unit)){
			 calValue = value * 10000 ;
		 }else if("亿元".equals(unit)){
			 calValue = value * 100000000 ;
		 }
		 return calValue ;
	}
	
	//获得当年年份
	public static String getThisYear(){
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String thisYear = sf.format(d);
		return thisYear;
	}
	
	public static void creatJarFile(String jarPath,List<File> fileList) throws Exception{
		File file_rar = new File(jarPath);
        if (!file_rar.exists()){   
        	file_rar.createNewFile();   
        }
		FileOutputStream outputStream = new FileOutputStream(file_rar);
		ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
		zipFile(fileList,zipOutputStream);
		zipOutputStream.close();
		outputStream.close();
	}
	
    public static void zipFile(File inputFile,ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中*/
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据   
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象   
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	
    
	 public static  void zipFile (List files,ZipOutputStream outputStream) {
		int size = files.size();
		for(int i = 0; i < size; i++) {
		    File file = (File) files.get(i);
		    zipFile(file, outputStream);
		}
	 }	
}
