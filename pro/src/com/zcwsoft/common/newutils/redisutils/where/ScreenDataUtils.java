package com.zcwsoft.common.newutils.redisutils.where;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zcwsoft.common.newutils.BaseSysUtils;

public class ScreenDataUtils  {

	
	
	public ScreenDataUtils(){}
	
	/**
	 * 等于等于等于等于等于等于等于等于等于等于  or
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getEqValueByListKeyInWhereOr(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				/*if("20180707204505411V18677".equals(Eachevalue))
				{
					System.out.println(Eachevalue);
				}*/
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
			   if((whereValue.indexOf(",")!=-1 || whereValue.indexOf("，")!=-1))
			   {
				 flag = WhereOperation.checkEq(Eachevalue,whereValue);
				 if(flag)
				 {
					  break;
				 }
			   }else{
				  if(Eachevalue.equals(whereValue))
				  {
					  flag = true;
					  break;
				  }else{
					  flag = false;
				  }
			  }
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
		
	}	
	
	/**
	 * 等于等于等于等于等于等于等于等于等于等于  and
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getEqValueByListKeyInWhereAnd(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue =replaceNull(FieldAndValue[j+1]);//条件值
			   if((whereValue.indexOf(",")!=-1 || whereValue.indexOf("，")!=-1))
			   {
				 flag = WhereOperation.checkEq(Eachevalue,whereValue);
				 if(!flag)
				 {
					 break;
				 }
			   }else{
				   //System.out.println(Eachevalue+"||"+whereValue);
				  // System.out.println(!Eachevalue.equals(whereValue));
				  if(!Eachevalue.equals(whereValue))
				  {
					  flag = false;
					  break;
				  }
				  
			  }
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
		
	}	
	
	
	/**
	 * 大于、小于、大于等于、小于等于  or
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getGreaterAndLessValueByListKeyInWhereOr(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
				if(BaseSysUtils.isBlank(Eachevalue) || StringUtils.equals(Eachevalue, "null"))//值等于空就不做处理
				{
					flag = false;
					  break;
				}
				flag = WhereOperation.checkGreaterAndLess(Eachevalue,whereValue);
				if(flag)
				{
					break;
				}
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
		
	}
	
	/**
	 * 大于、小于、大于等于、小于等于  and
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getGreaterAndLessValueByListKeyInWhereAnd(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
				if(BaseSysUtils.isBlank(Eachevalue) || StringUtils.equals(Eachevalue, "null"))//值等于空就不做处理
				{
					flag = false;
					  break;
				}
				flag = WhereOperation.checkGreaterAndLess(Eachevalue,whereValue);
				if(!flag)
				{
					break;
				}
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
		
	}

	/**
	 * 不等于
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getNotEqValueByListKeyInWhere(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
				flag = WhereOperation.checkNotEq(Eachevalue,whereValue);
				if(!flag)
				{
					break;
				}
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
		
	}
	
	/**
	 * 模糊查询所有值 And
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getLikeAndValueByListKeyInWhere(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
				if(Eachevalue.contains(whereValue.replace( WhereOperation.FYMOBL_LIKE, ""))){
					flag = true;
					//break;
				}else{
					flag = false;
					break;
				}
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
	}

	/**
	 * 模糊查询所有值 OR
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getLikeOrValueByListKeyInWhere(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
				if(Eachevalue.contains(whereValue.replace( WhereOperation.FYMOBL_LIKE, ""))){
					flag = true;
					break;
				}else{
					flag = false;
				}
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
	}

	
	/**
	 * 模糊查询外的所有值
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getNotLikeValueByListKeyInWhere(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
				if(Eachevalue.contains(whereValue.replace( WhereOperation.FYMOBL_LIKE, ""))){
					flag = false;
					break;
				}
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
		
	}

	/**
	 * 时间匹配 or
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getDateValueByListKeyInWhereOr(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
				if(BaseSysUtils.isBlank(Eachevalue) || StringUtils.equals(Eachevalue, "null"))//值等于空就不做处理
				{
					flag = false;
					  break;
				}
			  flag =  WhereOperation.checkDateEq(Eachevalue,whereValue);
			  if(flag)
			  {
				  break;
			  }
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
	}
	/**
	 * 时间匹配 and
	 * @param TableNameKey
	 * @param FieldAndValue
	 * @return
	 */
	public static List<Map> getDateValueByListKeyInWhereAnd(List<Map> lsm ,Object...FieldAndValue){
		List<Map> rslm  = new ArrayList<Map>();
		for (int i = 0; i < lsm.size(); i++) 
		{
			boolean flag = false;//如果初始值不变，代表匹配到
			for (int j = 0; j < FieldAndValue.length; j+=2) 
			{
				flag = true;
				String field = FieldAndValue[j]+"";//字段
				if(StringUtils.isNotBlank(field))
				{
					field = field.toUpperCase();
				}
				String Eachevalue = lsm.get(i).get(field)+"";//缓存中的值
				String whereValue = replaceNull(FieldAndValue[j+1]);//条件值
				if(BaseSysUtils.isBlank(Eachevalue) || StringUtils.equals(Eachevalue, "null"))//值等于空就不做处理
				{
					flag = false;
					  break;
				}
			  flag =  WhereOperation.checkDateEq(Eachevalue,whereValue);
			  if(!flag)
			  {
				  break;
			  }
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
	}
	
	private static String replaceNull(Object obj){
		if(BaseSysUtils.isBlank((String)obj))
		{
			return "";
		}
		return (String)obj;
	}
}
