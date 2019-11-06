package com.zcwsoft.common.newutils.redisutils.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zcwsoft.common.newutils.BaseSqlUtils;
import com.zcwsoft.common.newutils.BaseSysUtils;
import com.zcwsoft.common.newutils.redisutils.where.WhereOperation;

public class SqlExceut {

	public static Map<String,String> keyWdMap = new HashMap<String,String>();
	public static Map<String,String> materMap = new HashMap<String,String>();
	public static Map<String,Object> pubMap = new HashMap<String,Object>();
	
	
	public static Map<String,String>  fymoblMap = new HashMap<String,String>();

	public static ArrayList arrayList = new ArrayList();
	private static final String blank = " ";
	
	static{
		keyWdMap.put("select", "SELECT ");
		keyWdMap.put("(select", "(SELECT ");
		keyWdMap.put("from", "FROM ");
		keyWdMap.put("as", " AS ");
		keyWdMap.put("where", " WHERE ");
		keyWdMap.put("and", " AND ");
		keyWdMap.put("or", " OR ");
		keyWdMap.put("on", " ON ");
		keyWdMap.put("leftJoin", " LEFT JOIN ");
		keyWdMap.put("innerJoin", " INNER JOIN ");
		keyWdMap.put("groupBy", " GROUP BY ");
		keyWdMap.put("orderBy", " ORDER BY ");
		
		
		fymoblMap.put(WhereOperation.FYMOBL_EQUAL, WhereOperation.FYMOBL_EQUAL);
		fymoblMap.put(WhereOperation.FYMOBL_GREATER, WhereOperation.FYMOBL_GREATER);
		fymoblMap.put(WhereOperation.FYMOBL_LESS, WhereOperation.FYMOBL_LESS);
		fymoblMap.put(WhereOperation.FYMOBL_GREATER + WhereOperation.FYMOBL_EQUAL, WhereOperation.FYMOBL_GREATER + WhereOperation.FYMOBL_EQUAL);
		fymoblMap.put(WhereOperation.FYMOBL_LESS + WhereOperation.FYMOBL_EQUAL, WhereOperation.FYMOBL_LESS + WhereOperation.FYMOBL_EQUAL);
		fymoblMap.put(WhereOperation.FYMOBL_NOTEQUAL, WhereOperation.FYMOBL_NOTEQUAL);
		fymoblMap.put("<>", WhereOperation.FYMOBL_NOTEQUAL);
		
		
		arrayList.add(WhereOperation.FYMOBL_EQUAL);
		arrayList.add(WhereOperation.FYMOBL_GREATER);
		arrayList.add(WhereOperation.FYMOBL_LESS);
		arrayList.add(WhereOperation.FYMOBL_GREATER + WhereOperation.FYMOBL_EQUAL);
		arrayList.add(WhereOperation.FYMOBL_LESS + WhereOperation.FYMOBL_EQUAL);
		arrayList.add(WhereOperation.FYMOBL_NOTEQUAL);
		
	}
	public static void select(String sql){
		if(BaseSysUtils.isBlank(sql))
		{
			System.out.println("无效sql表达式：sql语句为空");
			return;
		}
		sql = SqlOperation.replaceBlank(sql);
		System.out.println("1=:"+sql);
		sql = SqlOperation.keywordUpperToLower(sql);
		System.out.println("2=:"+sql);
		
		operation(sql);
		
		
	}
	public static void operation(String sql)
	{

		Map<String,Map> itMap = new HashMap<String,Map>();
		itMap.put("zResult", new HashMap());
		HashMap tableNameMap = new HashMap();
		//sql 示例：
		//	SELECT t.table_name,t.column_name,t.data_type AS dda,t.data_length,t.column_id 
		//	FROM SYS.USER_TAB_COLUMNS t LEFT JOIN table t2 ON (t.table_name=t1.table_name) 
		//  WHERE t.table_name=?
		int selectNum = sql.indexOf(keyWdMap.get("select")) ;
		int fromNum = sql.indexOf(keyWdMap.get("from")) ;
		if( selectNum != -1 && fromNum != -1  )
		{
			//截取结果集  示例：t.table_name,t.column_name,t.data_type AS dda,t.data_length,t.column_id
			String getResult = sql.substring(selectNum + 7, fromNum - 1).trim();
			System.out.println(getResult);
			if(getResult.indexOf(keyWdMap.get("select")) ==-1)//表示结果集没有子查询语句
			{
				getData(sql, getResult,fromNum,itMap);
			}else{//表示结果集中有子查询语句
				
			}
		}
	}
	
	
	/**
	 * 设置主从表参数，itMap存放
	 * 
	 * @param sql		sql语句
	 * @param getResult	from前面select后面的结果集段
	 * @param fromNum	第一个From关键字所在位置
	 * @param itMap		存放本次sql涉及参数
	 */
	public static void getData(String sql,String getResult,int fromNum,Map itMap)
	{
		String getTable = sql.substring(fromNum+4);//截取from后面的所有字符串
		int whereNum2 = getTable.indexOf(keyWdMap.get("where"));
		
		if(whereNum2 != -1 && getTable.indexOf(keyWdMap.get("select")) ==-1)
		{//如果from后面字符串存在where条件关键字 且没有子查询
			//截取主表名称  示例：SYS.USER_TAB_COLUMNS t LEFT JOIN table t2 ON (t.a=t1.a)
			String tableResult = getTable.substring(0,whereNum2).trim();
			System.out.println(tableResult);
			setMaterMap(tableResult);
			String leftJoin = keyWdMap.get("leftJoin");
			String innerJoin = keyWdMap.get("innerJoin");
			if(tableResult.indexOf(leftJoin) != -1)
			{
				
				setRfTb(tableResult,leftJoin);//
			}
			if(tableResult.indexOf(innerJoin) != -1)
			{
				
			}
		}
		else if(whereNum2 == -1 && getTable.indexOf(keyWdMap.get("select")) ==-1)
		{//如果没有where 且没有子查询
			
		}
		else
		{//where 条件有子查询语句
		
		}
		
		if(true)//这里把函数替换掉
		{
			
		}
		if(true)//这里把子查询替换掉
		{
			
		}
		String[] split = getResult.split(",");//吧结果集割接
	}
	/**
	 * 把所有关联表属性封装到Map（主表名称，关联表名称，别名，域名，关联关系，条件类型...等等）
	 * Map格式参照XX协议
	 * @param tableResult  样例：//Map<String,Object> itMap = new HashMap<String,Object>();
							   //SYS.USER_TAB_COLUMNS t LEFT JOIN table t1 ON (t.a1=t1.a1) INNER JOIN table2 t3 ON (t2.a2=t1.a2)
	 * 
	 * 
	 * @param type		        样例：left join 、  inner join 等等
	 */
	public static Map setRfTb(String tableResult,String type)
	{
		String[] split = tableResult.split(type);
		for (int i = 1; i < split.length; i++) //从1开始，因为0元素不可能是从表
		{
			Map<String,Object> itMap2 = new HashMap<String,Object>();
			String itTbName = null;//表名，有可能（ 域名.表明），，，，
			String string = split[i];
			if(string.indexOf(keyWdMap.get("on")) !=-1)
			{//分割关键字join后 on前面（其实就是表明 加上别名）
				String[] sste = string.split(keyWdMap.get("on"));
				String split2 = sste[0].trim();//这里得到可能是   （用户.从表	别名 ）	或者  （用户.从表	as 别名）
				String asTbname = null;//别名
				String doName = null;//域用户
				if(split2.indexOf(keyWdMap.get("as")) !=-1)
				{
					itTbName = split2.split(keyWdMap.get("as"))[0];//存储主表信息，
					asTbname = split2.split(keyWdMap.get("as"))[1];//如果数组越界就表明sql不符合格式
				}else{
					itTbName = split2.split(blank)[0];
					asTbname = split2.split(blank)[1];//如果数组越界就表明sql不符合格式
				}
				if(itTbName.indexOf(".") != -1)
				{//这里是如果表明前带上域用户名称 user.table,则切割获取
					String[] split3 = itTbName.split("\\.");
					doName = split3[0];//域用户名称
					itTbName = split3[1];//最终只存储表明表名
				}
				itMap2.put("doName", doName);					//域用户名称
				itMap2.put("tAs", asTbname);					//别名
				itMap2.put("type", type);						//关联类型
				itMap2.put("isMaterOrSlave", "slave");			//是否主表 mater/slave
				itMap2.put("pid", materMap.get("tableName"));	//主表名称
				itMap2.put("pidAs",  materMap.get("tAs"));		//主表别名
				if(sste.length > 1)
				{//这里是关联查询后面的 条件 例如： on (t.a=t2.a AND t.b=t2.b)
					String onWhere = sste[1].trim();
					System.out.println(onWhere.indexOf("("));
					if(onWhere.indexOf("(") == 0)
					{//on后面带括号
						onWhere = onWhere.substring(1,onWhere.indexOf(")"));//获取t.a=t2.a
					}else
					{
						onWhere = onWhere.replace("(","").replace(")","");
						if(onWhere.indexOf(" " ) != -1)
						{
							String[] ks = onWhere.split(" ");
							for (int j = 1; j < ks.length; j+=2) 
							{
								if(j==1)
								{
									onWhere = ks[0] +" ";
								}
								if(ks[j] != "AND" &&  ks[j] != "OR" )
								{
									break;
								}
								if(j!=1)
								{
									onWhere = onWhere +" " +ks[j] + " " ;
								}
							}
						}
					}
					String[] split3 = onWhere.split(" ");
					HashMap andOrMap = new HashMap();
					HashMap andMap = new HashMap();
					HashMap orMap = new HashMap();
					if(split3.length == 1)
					{
						setAndOrWhereMap(split3[0],andMap);
					}
					for (int j = 1; j < split3.length; j+=2) 
					{
						//...........非常难
						if(split3[j].indexOf("AND") != -1 )
						{
							String string2 = split3[j-1];// t.a=t2.a
							setAndOrWhereMap(string2,andMap);
							if(j+2 == split3.length)
							{
								String string3 = split3[j+1];// t.a=t2.a
								setAndOrWhereMap(string3,andMap);
							}
						}
						else if(split3[j].indexOf("OR") != -1)
						{
							String string2 = split3[j-1];// t.a=t2.a
							setAndOrWhereMap(string2,orMap);
							if(j+2 == split3.length)
							{
								String string3 = split3[j+1];// t.a=t2.a
								setAndOrWhereMap(string3,orMap);
							}
						}
						
					}
					andOrMap.put("AND", andMap);
					andOrMap.put("Or", orMap);
					itMap2.put("where", andOrMap);
				}
			}
			pubMap.put("tableName", itTbName);					//关联表名称
			pubMap.put(itTbName, itMap2);															
		}
		return null;
		/**
		 * 这里返回值样例：
		 * {table2={pidAs=null, isMaterOrSlave=slave, tAs=t3, pid=null, where={}, type=INNER JOIN, doName=null},
		 *  tableName=table2, table={pidAs=null, isMaterOrSlave=slave, tAs=t1, pid=null, where={}, type=LEFT JOIN, doName=hh}}
		 */
	}
	/**
	 * 设置主表属性
	 * @param tableResult
	 */
	public static void setMaterMap(String tableResult)
	{
		String[] split = tableResult.split(blank);
		/*if(tableResult.indexOf(blank))
		{
			
		}*/
	}
	/**
	 * 把条件存放至Map
	 */
	@SuppressWarnings("unchecked")
	public static void setAndOrWhereMap(String whereValue,Map andOrMap)
	{
		for(String key:fymoblMap.keySet())
		{
			if(whereValue.indexOf(key) != -1)
			{
				String[] split = whereValue.split(key);
				if(split.length>1)
				{
					andOrMap.put(split[0], fymoblMap.get(key) + split[1]);
				}
			}
		}
	}
	public static void main(String[] args) {
		String s = "SYS.USER_TAB_COLUMNS t LEFT JOIN hh.table t1 ON (t.a1=t1.a1) INNER JOIN table2 t3 ON (t2.a2=t1.a2)";
		s = BaseSqlUtils.getSql(new SqlExceut(), "sql.xml", "getFieldNameBytableName");
		
		s = SqlOperation.replaceBlank(s);
		System.out.println("1=:"+s);
		s = SqlOperation.keywordUpperToLower(s);
		System.out.println("2=:"+s);
		setRfTb(s,"LEFT JOIN");
		System.out.println(pubMap);
		setRfTb(s,"INNER JOIN");
		System.out.println(pubMap);
//		String sql = BaseSqlUtils.getSql(new SqlExceut(), "sql.xml", "getTableIndexs");
		/*String sql = BaseSqlUtils.getSql(new SqlExceut(), "sql.xml", "getFieldNameBytableName");
		select(sql);*/
		/*String sql = "select 	a.b,a.c,b.a," +
				"(  	select * from table3 where a=a	)	" +
				"from table a left join table2 b on ( a.bat = b.alt ) where    	\n\n\t\t a.name = b.name and a= b or b =a";
		sql = replaceBlank(sql);
		System.out.println("1=:"+sql);
		sql = keywordUpperToLower(sql);
		System.out.println("2=:"+sql);
		select();
		a();*/
	}
}
