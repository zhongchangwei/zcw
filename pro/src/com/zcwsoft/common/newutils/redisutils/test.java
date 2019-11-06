package com.zcwsoft.common.newutils.redisutils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zcwsoft.business.enforce.base.service.impl.GenertaFieldServiceImpl;
import com.zcwsoft.common.newutils.BaseSqlUtils;
import com.zcwsoft.common.newutils.pub.util.TableIndexUtils;
import com.zcwsoft.common.newutils.redisutils.core.RedisCore;
import com.zcwsoft.common.newutils.redisutils.core.RedisEache;
import com.zcwsoft.common.newutils.redisutils.where.RedisMultiUtil;
import com.zcwsoft.common.newutils.redisutils.where.RedisSingleUtil;

public class test {

	public static void a(){
		String w= "";


		String str="hello          song";
		Pattern p = Pattern.compile("\\s*");
		Matcher m = p.matcher(str);
		w = m.replaceAll(" ");

		System.out.println(w);
	} 
	@SuppressWarnings({ "static-access", "rawtypes" })
	public static void main(String[] args) {
		
		a();
		String str = " ss    	\n\t" +
				"s";        
		System.out.println(str.replaceAll("\\s{2,}", " "));
		
		
		String sql = BaseSqlUtils.getSql(new GenertaFieldServiceImpl(), "genertaFieldSql.xml", "getTableFieldByTableName2");
		System.out.println(sql);
		System.out.println(sql.replaceAll("\\s{2,}", " "));
		
		RedisMultiUtil rs = new RedisMultiUtil();
		rs.setTableName("t_s_menu");
		rs.setEqualAnd("id", "c,b,d,a");
		rs.setEqualOr("id", "b");
		rs.setEqualOr("id", "a");
		rs.setEqualOr("id", "d");
		rs.setEqualAnd("NAME_", "用户管理");
		rs.setLikeOr("NAME_", "部门");
		rs.setGreaterEqAnd("sort", "2");
		rs.setBeginEndNotEq("sort", "1", "9");
		rs.setNotEqual("id", "b");
		List<Map> execute = rs.execute();
		System.out.println(execute.size());
		for (int i = 0; i < execute.size(); i++) {
			System.out.println(execute.get(i).get("ID")+"||"+execute.get(i).get("SORT")+"||"+execute.get(i));
		}
//		
//		
		
		//List<Map> in = new RedisUtil().getIn("t_base_field", "id","20171219221145V8681");  
		//String key = new RedisUtil().getValueByStringkey("20171219221145V8681");
		//Map valueByMapkey = new RedisSingleUtil().getValueByMapkey("t_s_menu");
//		List<Map> like = new RedisSingleUtil().getEqualAnd("t_s_menu", "id","c","id","a");//2017/12/19 "id","c",
//		List<Map> like = new RedisSingleUtil().getEqualAnd("t_s_menu","remark",null,"remark","3");//"name_","生成模式，菜单", "id","a,b,c,d");//2017/12/19
		List<Map> like = new RedisSingleUtil().getGreaterAnd("t_s_menu", "CREATE_DATE","2017-12-1", "sort","6");//2017/12/19
//		List<Map> like = new RedisSingleUtil().getGreaterEq("t_s_menu","sort","3", "sort","2");//2017/12/19
//		List<Map> like = new RedisSingleUtil().getLess("t_s_menu", "sort","6", "CREATE_DATE","2017-12-1");//2017/12/19
//		List<Map> like = new RedisSingleUtil().getLess("t_s_menu","sort","3", "sort","2");//2017/12/19
//		List<Map> like = new RedisSingleUtil().getLike("t_s_menu","sort","3", "sort","12");//2017/12/19
//		List<Map> like = new RedisSingleUtil().getLike("t_s_menu", "sort","12","sort","3", "sort","12");//2017/12/19
//		List<Map> like = new RedisSingleUtil().getNotLike("t_s_menu", "sort","12","sort","3", "sort","1", "sort","12");//2017/12/19
//		List<Map> like = new RedisSingleUtil().getDateAnd("t_s_menu","modify_date","2018-01-05", "CREATE_DATE","2017-12-19");//2017/12/19 2017/12/19
		//List<Map> like = new RedisSingleUtil().getDateBeginEnd("t_s_menu", "CREATE_DATE","2017-12-01","2017-12-19");//2017/12/19
//		List<Map> like = new RedisSingleUtil().getBeginEndNotEq("t_s_menu", "CREATE_DATE","2017-12-01","2017-12-09");//2017/12/19
		
		
		
//		List<Map> date = new RedisSingleUtil().getBegin_End_Eq("t_base_field", "SORT","3","6");//new RedisUtil().get
//		List<Map> date2 = new RedisSingleUtil().getBegin_End_NotEq("t_base_field", "SORT","3","6");//new RedisUtil().get
	/*	System.out.println(like.size());
		//System.out.println(date.size());
		for (int i = 0; i < like.size(); i++) {
			System.out.println(like.get(i).get("CREATE_DATE")+"||"+like.get(i));
		}*/
		/*for (int i = 0; i < date2.size(); i++) {
			System.out.println(date2.get(i));
		}*/
		//System.out.println("2018/02/13".replace("/", "-"));
		/*
		RedisMultiUtil redis = new RedisMultiUtil();
		redis.setTableName("t_base_field");
		//redis.setBegin_End_NotEq("SORT","3","6");
		//redis.setIn("id","20171219221114V8307,20171219221749V10496,20171219221749V8731");//20171219221749V8731  20171219221749V10496
		//redis.setNotEq("id","20171219221749V10496");//20171219221749V8731  20171219221749V10496
		//redis.setEq("id","20171219221749V8731");//20171219221749V8731  20171219221749V10496
		//redis.setDate("create_date","2018-07-07");
		redis.setNotIn("id","20171219221749V10496");
		redis.setIn("id","20180707024143895V14367,20180707024143895V15298");
		redis.setIn("id","20171219221749V10496");
		List<Map> execute = redis.execute();
		System.out.println(execute.size());
		for (int i = 0; i < execute.size(); i++) {
			System.out.println(execute.get(i));
		}*/
		//System.out.println(execute);
		//loadKeys();
		//RedisEache rsd = new RedisEache();
		//String key = "t_s_table";
		//rsd.deleteAllEacheKey();
		//rsd.test1();
		 //Map lsm = getValueByMapkey("nimabi");
		//List<Map> lsm = rsd.getValueByListKeyInWhere(key,"name",">=2017-03-12 00:00:02&&<=2018-04-12 00:00:02");//,"name",">=2018-03-16 14:43:57&&<=2018-04-13");//,"age",">50&&<=70");//,"name",">2017-03-14 14:43:5"
		List<Map> lsm = RedisEache.getValueByListkey("INDEX_T_S_USER_ID");
		System.out.println(lsm);
	}
	

	private static RedisCore jedis = new RedisCore();//redis工具类对象
	public static void loadKeys(){
		Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key1 = it.next();   
            System.out.println(key1);   
        }
	}
	/**
	 * 	m.put("id", "hhe");
		m.put("name", "zcw");
		arrayList.add(m);
		m = new HashMap();
		m.put("id", "hhe2");
		m.put("name", "zcw2");
		arrayList.add(m);
		arrayList.add("{'id':'我的天','age':'10','name':'2017-03-12'}");
		arrayList.add("{'id':'1234','age':'20','name':'2017-03-13 14:43:57'}");
		arrayList.add("{'id':'我的天','age':'30','name':'2017-03-14 14:43:57'}");
		arrayList.add("{'id':'我的天','age':'40','name':'2017-03-15 14:43:57'}");
		arrayList.add("{'id':'我的天','age':'45','name':'2017-03-16 14:43:57'}");
		arrayList.add("{'id':'nimabi','age':'50','name':'2018-03-16 14:43:57'}");
		arrayList.add("{'id':'nimabi','age':'60','name':'2018-04-15'}");
		arrayList.add("{'id':'nimabi','age':'70','name':'2018-04-14'}");
		arrayList.add("{'id':'nimabi','age':'80','name':'2018-04-13'}");
		arrayList.add("{'id':'nimabi','age':'90','name':'2018-04-12'}");
	 */
}
