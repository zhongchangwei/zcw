package com.zcwsoft.common.newutils.redisutils.sql;

import java.util.HashMap;

public class SqlOperation {


	/**
	 * 把sql语句中的关键字 小写 转换成 大写
	 * @param sql
	 * @return
	 */
	public static String keywordUpperToLower(String sql)
	{
		HashMap<String, String> keyword = new HashMap<String,String>();
		keyword.put("select ", "SELECT ");
		keyword.put(" from ", " FROM ");
		keyword.put(" where ", " WHERE ");
		keyword.put(" and ", " AND ");
		keyword.put(" or ", " OR ");
		keyword.put(" as ", " AS ");
		keyword.put("left join ", "LEFT JOIN "); 
		keyword.put("inner join ", "INNER JOIN "); 
		keyword.put(" on ", " ON "); 
		keyword.put(" =", "=");
		keyword.put("= ", "=");
		keyword.put(" <", "<");
		keyword.put("< ", "<");
		keyword.put(" >", ">");
		keyword.put("> ", ">");
		keyword.put(", ", ",");
		keyword.put(" ,", ",");
		keyword.put("group by ", "GROUP BY ");
		keyword.put("order by ", "ORDER BY ");
		for(String key:keyword.keySet())
		{
			sql = sql.replaceAll(key, keyword.get(key));
		}
		return sql;
	}
	/**
	 * 替换空格，吧多个空格、换行、制表符、换列...替换成一个空格
	 * @param sql
	 * @return
	 */
	public static String replaceBlank(String sql)
	{
		System.out.println("原："+sql);
		sql = sql.replaceAll("\\s{2,}|\t|\n|\r", " ");
		System.out.println("0=:"+sql);
		sql = sql.replaceAll("\\( ", "(");
		sql = sql.replaceAll(" \\)", ")");
		return sql;
	}
}
