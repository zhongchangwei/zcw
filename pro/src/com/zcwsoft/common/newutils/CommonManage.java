package com.zcwsoft.common.newutils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kingzheng.sac.control.User;
import com.kingzheng.sac.control.UserUtil;
import com.zcwsoft.common.util.DES;
import com.zcwsoft.common.util.MD5;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 公共应用方法类
 * @author cyc
 *
 */
public class CommonManage { 

	
	/**
	 * 字符判断是否为空,true:空 false:非空
	 */
	public static boolean isNull(String str) {
		if (null != str && !"".equals(str.trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 字符判断是否非空, true:非空 false:空
	 */
	public static boolean notNull(String str) {
		if (null != str && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 页面空字符转换
	 */
	public static String toTDString(Object object) {
		String rn = "&nbsp;";
		if (null != object && !"".equals(object.toString().trim())) {
			rn = object.toString();
		}
		return rn;
	}

	/**
	 * 空字符转换
	 */
	public static String toNotNullString(Object object) {
		String rn = "";
		if (object != null) {
			rn = object.toString();
		}
		return rn;
	}

	/**
	 * 获取系统时间
	 */
	public static String getSysDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(calendar.getTime());
	}
	
	/**
	 * 获取系统日期"yyyy-MM-dd"（cyz于20100319增加）
	 */
	public static String getSysDay() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(calendar.getTime());
	}
	
	/**
	 * 获取系统时间，返回类型为java.util.Date,格式为yyyyy-MM-dd HH:mm:ss的时间
	 * author:lzp
	 */
	public static Date getNowDate(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate= dateFormat.format(now);
		Date nowDate = null;
		try {
			nowDate = dateFormat.parse(currentDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nowDate;
	}
	/**
	 * 获取系统年份
	 */
	public static String getSysYear() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		return simpleDateFormat.format(calendar.getTime());
	}
	
	/**
	 * 获取系统 年月 yyMM
	 */
	public static String getSysYYMM() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMM");
		return simpleDateFormat.format(calendar.getTime());
	}
	/**
	 * 获取系统月份MM 
	 */
	public static String getSysMM(){
		Calendar calendar =Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
		return simpleDateFormat.format(calendar.getTime());
	}
	/**
	 * 获取当天之前days天的日期 （零时零分零秒）
	 * 如果当前时间为   2011-09-25 10:10:10
	 * 则 dayBeforeToday(5) 结果为2011-09-20 00:00:00
	 */
	public static String dayBeforeToday(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);//24小时制，而calendar.set(Calendar.HOUR,0);则是12小时制
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.add(Calendar.HOUR,-24*days);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(calendar.getTime());
	}
	
	/**
	 * 获取当天之后days天的日期 （零时零分零秒）
	 * 如果当前时间为   2011-09-25 10:10:10
	 * 则 dayAfterToday(1) 结果为2011-09-26 00:00:00
	 */
	public static String dayAfterToday(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.add(Calendar.HOUR,24*days);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(calendar.getTime());
	}
	
	/**
	 * 获取系统异常信息
	 */
	public static String getSysErrorMsg(Exception e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer,true));
		return writer.toString();
	}
	
	/**
	 * 检测所输入的手机号码格式是否正确
	 * @return
	 */
	public static boolean isMobilePhone(String mobilePhone) {
		String regExp = "^[1][0-9]{10}$";
		return mobilePhone.matches(regExp);
	}
	
	/**
	 * 检测所输入的email格式是否正确
	 * @return
	 */
	public static boolean isEmail(String email) {
		String regExp = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[_.0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT)$";
		return email.matches(regExp);
	}
	
	/**
	 * 添加script代码
	 */
	public static String addScript(String script) {
		String str=" <script type='text/javascript' defer='defer'> ";
		str+=script;
		str+=" </script> ";
		return str;
	}
	
	/**
	 * 获取一个uuid
	 */
	public static String getUuid() {
		UUID uuid = UUID.randomUUID(); 
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * 除去前后空格，若是null,返回""
	 * @param s
	 * @return
	 */
	public static String trimStr(String s){
		String rn = "";
		if(s!=null){
			rn = s.trim();
		}
		return rn;
	}
	
	/**
	 * 返回加密的字符串
	 */
	public static String getDesAttribute(Object value) throws Exception {
		String sql = DES.encrypt(value.toString());
		JSONObject o = new JSONObject();
		o.put("sql", sql);
		String temp = DES.encrypt(o.toString());
		String md5Str = MD5.getMD5(temp);//校验串
		return temp+md5Str;
	}
	
	/**
	 * 获取用户对象
	 * @param session
	 * @return
	 */
	public static User getUser(HttpSession session){
		UserUtil userUtil = new UserUtil(session);
		User user = userUtil.getCurrentUser();
		if(user == null){
			return new User();
		}
		return user;
	}
	
	/**
	 * 获取用户权限ID
	 * @param roolKey
	 * @return
	 * @throws Exception
	 */
	public static String getKeyValue(String userKey){
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("UserRools.xml");
		
		SAXReader reader=new SAXReader();     
        //创建一个文档对象     
        org.dom4j.Document document;
		try {
			document = reader.read(stream);
		} catch (DocumentException e) {
			
			e.printStackTrace();
			return "";
		}  
        
      //获取文件的根节点     
        Element element=document.getRootElement();  
        for(Iterator i=element.elementIterator("rool");i.hasNext();){  
            //获取节点元素     
            element=(Element)i.next();  
            String key = element.attributeValue("key"); 
            if(userKey.equals(key)){
            	return element.attributeValue("value");
            }
        }     
        
        return "";
	}
	
	/**
	 * 是否为管理员
	 * @return
	 */
	public static boolean isAdmin(HttpSession session){
		return UserUtil.getAdmins().contains(CommonManage.getUser(session).getLoginName());
	}
	
	/**
	 * 是否具有某种权限
	 * @param roolKey(从config/UserRools.xml中获取key值)
	 * @return
	 */
	public static boolean hasAuth(HttpSession session, String userKey){
		return session.getAttribute("isManager").toString().indexOf(CommonManage.getKeyValue(userKey)) > 1;
	}
	
	/**
	* 编码的json转JSONArray
	* 作者：zcw
	* 开发时间：2017-11-17 20:27:29
	* @return
	*/
	public static JSONArray getJSONArrayByJson(String json) throws IOException{
		String jsonStr = URLDecoder.decode(json,"utf-8");
		JSONArray jArray = JSONArray.fromObject(jsonStr);
		return jArray;
	}
	
	/**
	 * 功能：编码的json转JSONObject
	 * 作者：zcw
	 * 开发时间：2017年11月17日20:27:44
	 */
	public static JSONObject getJSONObjectByJson(String json)throws IOException{
		String jsonStr = jodd.servlet.URLDecoder.decode(json, "utf-8");
		JSONObject obejct = JSONObject.fromObject(jsonStr);
		return obejct;
	}
	
	/**
	 * 功能：计算两个时间的时间差，返回xx:xx:xx(时:分:秒)
	 * 作者：zcw
	 * 开发时间：2017年11月17日20:27:58
	 */
	public static String getTwoTimeHourMinSecond(String startTime, String endTime){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date starDate = null; 
		Date endDate = null;

		try {
			starDate = df.parse(startTime);
			endDate = df.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long l = endDate.getTime() - starDate.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String resultStr = "";
		if (hour >= 0) {
			resultStr = resultStr + hour + ":";
		}
		if (min >= 0) {
			resultStr = resultStr + min + ":";
		}
		if (s >= 0) {
			resultStr = resultStr + s;
		}
		return resultStr;
	}

}
