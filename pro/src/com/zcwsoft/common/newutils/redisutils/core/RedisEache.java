package com.zcwsoft.common.newutils.redisutils.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.common.newutils.BaseSysUtils;
import com.zcwsoft.common.newutils.pub.util.TableIndexUtils;
import com.zcwsoft.common.newutils.redisutils.JSONUtils;
import com.zcwsoft.common.newutils.redisutils.where.WhereOperation;
import com.zcwsoft.common.newutils.redisutils.where.ScreenDataUtils;


public class RedisEache {

	public static RedisCore jedis = new RedisCore();//redis工具类对象
	private static CommonDao dao;
	//private static String primary;

	/** 大于 */
	public static final String maxFymbol =WhereOperation.FYMOBL_GREATER;//大于
	/** 小于*/
	public static final String minFymbol =WhereOperation.FYMOBL_LESS;//小于
	/** 等于 */
	public static final String eqlFymbol =WhereOperation.FYMOBL_EQUAL;//等于
	/** 不等于 */
	public static final String notEqlFymbol =WhereOperation.FYMOBL_NOTEQUAL;//不等于
	/** 组合查询拼接符号 */
	public static final String groupFymbol =WhereOperation.FYMOBL_GROUP;//组合查询拼接符号
	/** 日期查询符号 */
	public static final String dateFymbol =WhereOperation.FYMOBL_DATE;//日期查询符号
	/**	 模糊查询符号	 */
	public static final String likeFymbol =WhereOperation.FYMOBL_LIKE;//模糊查询符号
	
	
//******************insert插入缓存方法  start****************************************

	public static void setDao(CommonDao daoBean)
	{
		dao = daoBean;
	}
/*	public static void setDao(String primarya)
	{
		primary = primarya;
	}
	*/
	private static RedisEache redisEache;
    public static RedisEache getInstance() {
        if (redisEache == null) {
        	redisEache = new RedisEache();
        }
        return redisEache;
    }
	/**
	 * 全局缓存更新操作方法。
	 * 存入表中所有数据，数据格式 List<key=表名,value=行数据json格式(单条row)>
	 * 另：把每条数据单独使用Map格式加入缓存，主键的值作为key
	 * @param keyTableName	表名作为redis的key
	 * @param rows	多行数据list存放
	 */
	public static  int insertMultiDataToList(String keyTableName,List rows){
		/**
		 **该方法存值示例如下**
		   list.add("{'id':'123','name':'zcw'}");
		   list.add("{'id':'1234','name':'zcw2'}");
		          或者	list.add(map);
		   list中可存入json或则Map 进入方法后统一转成json
		   
		 **该方法取值示例如下**
		   Long llen = jedis.llen(key);//获取总数
		  List<String> lrange = jedis.lrange(key, 0, llen);//获取所有数据
		 */
		
		try{
			TableIndexUtils tbn =null;
			if(dao != null)
			{
				tbn = new TableIndexUtils(keyTableName,dao);
			}else{
				tbn = new TableIndexUtils(keyTableName);
			}
			String primary = tbn.getUniquePrimaryField();
			for (int i = 0; i < rows.size(); i++) {
				//System.out.println(rows.get(i).toString());
				String value = rows.get(i).toString();//这里存放表中的某一行
				if(rows.get(i) instanceof Map){
					value = JSONUtils.toStringByJSONObject((Map)rows.get(i));
				}
				if(!BaseSysUtils.isBlank(value)){
					value = value.trim();
				}
				//key=表名,值=json所有
				jedis.lpush(keyTableName, value);
				//key=主键id,值=json行所有(这里json会转成Map存储)
				if(BaseSysUtils.isNotBlank(primary))
				{
					insertSingleDataByKeyFieldToMap(primary, value);
				}
				//System.out.println(value);
			}
		}catch (Exception e) {
			System.out.println(e);
			jedis.del(keyTableName);//缓存失败，清空内容
			return -1;
		}
		return 0;
	}
	
	/**
	 * 把多行List<Map>类型的数据插入到redis缓存List类型
	 * @param TableName		表名，用于取索引，主键等信息
	 * @param ListTypeKey	缓存key
	 * @param rows			数据
	 * @return
	 */
	public static  int insertMultiDataNotPrimaryToList(String ListTypeKey,List rows){
	
		try{
			
			for (int i = 0; i < rows.size(); i++) {
				//System.out.println(rows.get(i).toString());
				String value = rows.get(i).toString();//这里存放表中的某一行
				if(rows.get(i) instanceof Map){
					value = JSONUtils.toStringByJSONObject((Map)rows.get(i));
				}
				if(!BaseSysUtils.isBlank(value)){
					value = value.trim();
				}
				//key=主键的值,值=json所有
				jedis.lpush(ListTypeKey, value);
			}
		}catch (Exception e) {
			System.out.println(e);
			jedis.del(ListTypeKey);//缓存失败，清空内容
			return -1;
		}
		return 0;
	}
	/**
	 * 把一行Map类型的数据插入到redis缓存List类型
	 * @param TableName		表名，用于取索引，主键等信息
	 * @param ListTypeKey	缓存key
	 * @param rows			数据
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static  int insertSingleMapDataToList(String ListTypeKey,Map row){
		List ls = new ArrayList();
		ls.add(row);
		return insertMultiDataNotPrimaryToList(ListTypeKey,ls);
	}
	
	/**
	 * 插入一条数据进入已有list中，插入位置：头部
	 * @param ListTypeKey	已有的key,
	 * @param row	新增行数据，json格式或者Map
	 * @return
	*/
	public static  int insertSingleDataToListF(String ListTypeKey,Object row){
		String value = null;
		try{
			if(null != row && !"".equals(row)){
				value = row.toString();
			}
			if(row instanceof Map){
				value = JSONUtils.toStringByJSONObject((Map)row);
			}
			jedis.lpush(ListTypeKey, value);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
			return 0;
	}
	
	/**
	 * 插入一条数据进入已有list中，插入位置：尾部
	 * @param keyTableName	已有的key
	 * @param row	新增行数据，json格式或者Map
	 * @return
	*/
	public static  int insertSingleDataToListL(String ListTypeKey,Object row){
		String value = null;
		
		try{
			if(null != row && !"".equals(row)){
				value = row.toString();
			}
			if(row instanceof Map){
				value = JSONUtils.toStringByJSONObject((Map)row);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
		Long rpush = jedis.rpush(ListTypeKey, value);
		int res = new Long(rpush).intValue();
		return res;
	}
	/**
	 * 插入一条数据进缓存，hash类型
	 * @param keyId		缓存key
	 * @param row		Map类型，该Map的值是一个List<Map>
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static  int insertSingleDataByKeyToMap(String keyId,Map row)
	{
		Map itm = new HashMap();
		try{
			if(null == row ){
				return -1;
			}
				try{
					for(Object key:row.keySet())
					{
						itm.put(key, JSONUtils.toStringByJSONArray((List)row.get(key)));
					}
				//String jsonToMap = JSONUtils.toStringByJSONArray(row);
				jedis.hmset(keyId, itm);
				}catch(Exception e0){
					System.out.println("转换map失败、不是有效jsonObject:"+row);
					return -1;
				}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
		return 0;
	}
	
	/**
	 * 插入一条数据入缓存，取map中key值作为缓存key
	 * @param keyId	field名称，自动匹配row值作为缓存key(一般都是存id主键、外键值)
	 * @param row	id对应的数据，json格式或者Map
	 * @return		
	 */
	public static  int insertSingleDataByKeyFieldToMap(String keyId,Object row){
		try{
			if(null == row || "".equals(row)){
				return -1;
			}
			if(row instanceof Map){
				Map value = (Map)row;
				jedis.hmset((String)value.get(keyId), value);
 			}else if(row instanceof String){
				try{
				Map<String,String> jsonToMap = JSONUtils.jsonToMapSet((String)row);
				jedis.hmset((String)jsonToMap.get(keyId), jsonToMap);
				}catch(Exception e0){
					System.out.println("转换map失败、不是有效jsonArray:"+row);
					return -1;
				}
			}else{return -1;}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
		return 0;
	}
	public static int insertSingleDataByKeyToString(String key,String value){
		jedis.set(key, value);
		return 0;
	}
	
//******************insert插入缓存方法  end****************************************
	
//******************update更新缓存方法  start****************************************
	
//******************update更新缓存方法  end****************************************	
	
//******************delete删除缓存方法  start****************************************	
	/**
	 * 删除当前缓存中的所有key
	 * @return	删除数量
	 */
	public static int deleteAllEacheKey(){
		//System.out.println("开始删除Redis缓存KEY");
		Set<String> keys = jedis.keys("*"); 
		Object[] array = keys.toArray();
		String[] sa = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			sa[i] = array[i]+"";
		}
		Long del = jedis.del(sa);
		int a = new Long(del).intValue();
		Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key1 = it.next();   
            //System.out.println("删除了key:"+key1);   
        }
       // System.out.println("删除结束，删除数："+a);
		return Integer.parseInt(String.valueOf(del));
	} 
	
//******************delete删除缓存方法  end****************************************	

	
//******************select查询缓存方法  start****************************************	
	
	/**
	 * 利用条件查询表级别数据
	 * @param TableNameKey	表名缓存key	
	 * @param FieldAndValue	数组类型:双数=条件，单数=值<br>
	 * 
	 *  值样例1：（只能是数字型与日期型）
	 * <br>	>1&&<9  		获取1到9的值不包含 
	 * <br> >=1&&<=9		获取1-9之间并包含1与9的值	
	 * <br> <?		小于 
	 * <br> <=?  	小于并等于
	 * <br> >? 		大于
	 * <br> >=?		大于等于	<br>
	 * 
	 * 值样例2：(逗号隔开，可多值判断，类似数据库中的in)
	 * <br>	a,b,c,d	或 1,2,3,4	<br>
	 * 
	 * 值样例3：(逗号隔开，可多值判断，首端加上!=即可,类似数据库中的not in)	
	 * <br> !=a	或	!=a,b,c,d	或   !=1,2,3,4  <br>
	 * 
	 * 值样例4：(日期匹配，样例1中日期是换成getTime时间戳来匹配，准确姓高。此处以天为单位，意为包含)	
	 * <br> $d$2017-03-01	或	$d$2017-03-01~2017-04-01<br>
	 * 
	 * 值样例5：(模糊查询，与数据库类似，区别在于此处为全量模糊如：like '%?%')
	 * <br> %*%你好	包含‘你好’2个字的内容都能查出来，不存在左模糊、右模糊，而是包含查询
	 */
	public static List<Map> getValueByListKeyInWhere(String TableNameKey,Object...FieldAndValue){
		List<Map> lsm = getValueByListkey(TableNameKey);
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
				String whereValue = FieldAndValue[j+1]+"";//条件值
				if(whereValue.indexOf(minFymbol)!=-1 || whereValue.indexOf(maxFymbol)!=-1 || whereValue.indexOf(groupFymbol)!=-1)
				{
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
					
			  }else{
				  if((whereValue.indexOf(",")!=-1 || whereValue.indexOf("，")!=-1)&&whereValue.indexOf(notEqlFymbol)==-1)
				  {
					 flag = WhereOperation.checkEq(Eachevalue,whereValue);
				  }else if(whereValue.indexOf(notEqlFymbol)!=-1){
					  flag = WhereOperation.checkNotEq(Eachevalue,whereValue);
				  }else if(whereValue.indexOf(dateFymbol)!=-1){
					  if(BaseSysUtils.isBlank(Eachevalue) || StringUtils.equals(Eachevalue, "null"))//值等于空就不做处理
						{
							flag = false;
							  break;
						}
					  flag = WhereOperation.checkDateEq(Eachevalue,whereValue);
				  }else if(whereValue.indexOf(likeFymbol)!=-1){
					  if(!Eachevalue.contains(whereValue.replace(likeFymbol, ""))){
						  flag = false;
						  break;
					  }
				  }else{
					  if(!Eachevalue.equals(whereValue))
					  {
						  flag = false;
						  break;
					  }
					  
				  }
			  }
		   }//for 
			if(flag){
				rslm.add(lsm.get(i));
			}
	   }//for 
	   return rslm;
		
	}
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("rawtypes")
	public static List<Map> getTnEqValueByListKeyInWhereOr(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByIndexListkey(tableName,FieldAndValue);
		return ScreenDataUtils.getEqValueByListKeyInWhereOr(lsm,FieldAndValue);
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> getTnEqValueByListKeyInWhereAnd(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByIndexListkey(tableName);
		return ScreenDataUtils.getEqValueByListKeyInWhereAnd(lsm,FieldAndValue);
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> getTnGreaterAndLessValueByListKeyInWhereOr(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByListkey(tableName);
		return ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereOr(lsm,FieldAndValue);
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByListkey(tableName);
		return ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereAnd(lsm,FieldAndValue);
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> getTnNotEqValueByListKeyInWhere(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByListkey(tableName);
		return ScreenDataUtils.getNotEqValueByListKeyInWhere(lsm,FieldAndValue);
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> getTnLikeAndValueByListKeyInWhere(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByListkey(tableName);
		return ScreenDataUtils.getLikeAndValueByListKeyInWhere(lsm,FieldAndValue);
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Map> getTnLikeOrValueByListKeyInWhere(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByListkey(tableName);
		return ScreenDataUtils.getLikeOrValueByListKeyInWhere(lsm,FieldAndValue);
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> getTnNotLikeValueByListKeyInWhere(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByListkey(tableName);
		return ScreenDataUtils.getNotLikeValueByListKeyInWhere(lsm,FieldAndValue);
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> getTnDateValueByListKeyInWhereOr(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByListkey(tableName);
		return ScreenDataUtils.getDateValueByListKeyInWhereOr(lsm,FieldAndValue);
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Map> getTnDateValueByListKeyInWhereAnd(String tableName, Object...FieldAndValue)
	{
		List<Map> lsm = getValueByListkey(tableName);
		return ScreenDataUtils.getDateValueByListKeyInWhereAnd(lsm,FieldAndValue);
	}
	
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////	
	
	
	
	/**
	 * 获取值，根据Map类型key
	 * @param key	缓存中存放map类型的key
	 * @return	map
	 */
	public static Map getValueByMapkey(String key){
		return jedis.hgetall(key);
	}
	/**
	 * 获取值，根据String类型key
	 * @param key	缓存中存放String类型的key
	 * @return	map
	 */
	public static String getValueByStringkey(String key){
		return jedis.get(key);
	}

	/**
	 * 获取所有值，根据List类型key
	 * @param key	缓存中存放List类型的key
	 * @return	map
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map> getValueByListkey(String key){
		key = key.toLowerCase();
		List<Map> lsm = new ArrayList<Map>(); 
		List<String> lrange = jedis.lrange(key,0,-1);
		 for (int i = 0; i < lrange.size(); i++) {
			Map jsonToMap = JSONUtils.jsonToMapGet(lrange.get(i));
			lsm.add(jsonToMap);
		 }
		 return lsm;
	}
	/**
	 * 获取所有值，根据List类型key
	 * @param key	缓存中存放List类型的key
	 * @return	map
	 */
	public static List<Map> getValueByIndexListkey(String key,Object...parms){
		List<Map> lsm = new ArrayList<Map>(); 
		if(parms.length > 0)
		{
			TableIndexUtils tbn = new TableIndexUtils(key);
			System.out.println("是否使用了索引字段:"+tbn.checkIndexField(parms));
			if(tbn.size()>0 && tbn.isHaveUniquePrimaryField() && tbn.checkIndexField(parms))
			{
				Map indexCheck = indexCheck(tbn,key,parms);
				for(Object obj:indexCheck.keySet())
				{
					lsm.add(getValueByMapkey((String)obj));
				}
				return lsm;
			}
		}
		key = key.toLowerCase();
		List<String> lrange = jedis.lrange(key,0,-1);
		 for (int i = 0; i < lrange.size(); i++) {
			Map jsonToMap = JSONUtils.jsonToMapGet(lrange.get(i));
			lsm.add(jsonToMap);
		 }
		 return lsm;
	}
	/**
	 * 获取所有值，根据List类型key
	 * @param key	缓存中存放List类型的key
	 * @return	map
	 */
	public static List<Map> getIndexAndByListkey(String key,Object...parms){
		List<Map> lsm = new ArrayList<Map>(); 
		if(parms.length > 0 && parms.length % 2 ==0)
		{
			TableIndexUtils tbn = new TableIndexUtils(key);
			System.out.println("是否使用了索引字段:"+tbn.checkIndexField(parms));
			if(tbn.size()>0 && tbn.isHaveUniquePrimaryField() && tbn.checkIndexField(parms))
			{
				Map indexCheck = indexCheck(tbn,key,parms);
				for(Object obj:indexCheck.keySet())
				{
					lsm.add(getValueByMapkey((String)obj));
				}
				return lsm;
			}
		}
		key = key.toLowerCase();
		List<String> lrange = jedis.lrange(key,0,-1);
		 for (int i = 0; i < lrange.size(); i++) {
			Map jsonToMap = JSONUtils.jsonToMapGet(lrange.get(i));
			lsm.add(jsonToMap);
		 }
		 return lsm;
	}
	
	/** 
     * <p>通过key获取list指定下标位置的value</p> 
     * <p>如果start 为 0 end 为 -1 则返回全部的list中的value</p> 
     * @param key 
     * @param start 
     * @param end 
     * @return 
     */ 
	public static List<Map> getValueByListkey(String key,long start,long end){
		List<Map> lsm = new ArrayList<Map>(); 
		List<String> lrange = jedis.lrange(key,start,end);
		 for (int i = 0; i < lrange.size(); i++) {
			Map jsonToMap = JSONUtils.jsonToMapGet(lrange.get(i));
			lsm.add(jsonToMap);
		 }
		 return lsm;
		
	}
	
	/**
	 * 取值前检测是否采用索引字段，如果使用了索引字段则不全表扫描
	 * @param tbn    	索引、主键查询对象
	 * @param key 		redis缓存中的list类型的键
	 * @param parms  	查询条件，奇数=字段名称，偶数=字段对应的条件值
	 * @return map		获取索引对应的主键id，外面使用这些主键id拿数据，Map中key存主键id，value是空的，（利用了map类型的Key不能重复原理）
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map indexCheck(TableIndexUtils tbn, String ListTypeKey, Object[] parms) 
	{
		Map resultMap = new HashMap();//存放结果
		for (int j = 0; j < parms.length; j+=2)
		{
			String field = (String)parms[j];//字段名
			String whereValue = null;//条件值
			if(j+1 < parms.length)
			{
				whereValue =  (String)parms[j+1];
			}
			if(BaseSysUtils.isNotBlank(field))
			{
				field = field.toUpperCase();//表中取出的数据Field字段都是大写的，所以需要转换成大写
			}
			String indexName = tbn.getIndexNameByIndexField(field);
			if(BaseSysUtils.isBlank(indexName))
			{
				continue;
			}
			//获取索引存储数据
			//例如：[{USER_ID=[{admin=u_admin}], PASSWORD=[{123=u_admin}]}]
			//List<Map> valueByListkey = RedisCore.hget(indexName,field);
			String ListMapStr = jedis.hget(indexName,field);
			List<Map> ls = new ArrayList();
			try {
				ls = JSONUtils.jsonToListMap(ListMapStr);
			} catch (Exception e) {
				System.out.println("RedisEache.indexCheck()使用索引查询异常："+e);
			}
			for (int k = 0; k < ls.size(); k++) //遍历缓存索引值与主键
			{
				if(ls.get(k) != null)//如果不等于空 示例：USER_ID=[{}]
				{
					Map m = ls.get(k);
					for(Object ok:m.keySet())
					{
						if(whereValue.indexOf(WhereOperation.FYMOBL_SPRIT) != -1)
						{	//如果传如的条件是多个，使用都好隔开，割掉
							String[] split = whereValue.split(WhereOperation.FYMOBL_SPRIT);
							for (int l = 0; l < split.length; l++) 
							{
								if(((String)ok).equals(split[l]))
								{
									String Primary = (String)m.get(ok);//获取到主键
									if(Primary.indexOf(tbn.subSy) != -1)//如果索引中不是唯一索引，也会有多个主键
									{
										String[] split2 = Primary.split(tbn.subSy);
										for (int n = 0; n < split2.length; n++) 
										{
											resultMap.put(split2[n],"");
										}
									}else{
										resultMap.put(Primary,"");
									}
								}
							}
						}else{
							if(((String)ok).equals(whereValue))
							{
								String Primary = (String)m.get(ok);//获取到主键
								if(Primary.indexOf(tbn.subSy) != -1)//如果索引中不是唯一索引，也会有多个主键
								{
									String[] split2 = Primary.split(tbn.subSy);
									for (int n = 0; n < split2.length; n++) 
									{
										resultMap.put(split2[n],"");
									}
								}else{
									resultMap.put(Primary,"");
								}
							}
						}
					}
				}
			}
			/*for (int i = 0; i < valueByListkey.size(); i++) 
			{
				Object object = valueByListkey.get(i).get(field);//直接取出该索引的对应Map（值对应主键）
				if(object != null && object instanceof List)	
				{
					List<Map> ls = (List<Map>)object;
					for (int k = 0; k < ls.size(); k++) //遍历缓存索引值与主键
					{
						if(ls.get(k) != null)//如果不等于空 示例：USER_ID=[{}]
						{
							Map m = ls.get(k);
							for(Object ok:m.keySet())
							{
								if(whereValue.indexOf(BaseExceut.FYMOBL_SPRIT) != -1)
								{	//如果传如的条件是多个，使用都好隔开，割掉
									String[] split = whereValue.split(BaseExceut.FYMOBL_SPRIT);
									for (int l = 0; l < split.length; l++) 
									{
										if(((String)ok).equals(split[l]))
										{
											String Primary = (String)m.get(ok);//获取到主键
											if(Primary.indexOf(tbn.subSy) != -1)//如果索引中不是唯一索引，也会有多个主键
											{
												String[] split2 = Primary.split(tbn.subSy);
												for (int n = 0; n < split2.length; n++) 
												{
													resultMap.put(split2[n],"");
												}
											}else{
												resultMap.put(Primary,"");
											}
										}
									}
								}else{
									if(((String)ok).equals(whereValue))
									{
										String Primary = (String)m.get(ok);//获取到主键
										if(Primary.indexOf(tbn.subSy) != -1)//如果索引中不是唯一索引，也会有多个主键
										{
											String[] split2 = Primary.split(tbn.subSy);
											for (int n = 0; n < split2.length; n++) 
											{
												resultMap.put(split2[n],"");
											}
										}else{
											resultMap.put(Primary,"");
										}
									}
								}
							}
						}
					}
				}
			}*/
		}
		return resultMap;
	}
	
//******************select查询缓存方法  end****************************************	
		
	public static void main(String[] args) {
		//String key = "t_s_table";
		//deleteAllEacheKey();
		//test1();
		Map valueByStringkey = getValueByMapkey("20171219231545V3333");
		System.out.println(valueByStringkey);
		 //Map lsm = getValueByMapkey("nimabi");
		//List<Map> lsm = getValueByListKeyInWhere(key,"id","!=nimabi");//,"name",">=2018-03-16 14:43:57&&<=2018-04-13");//,"age",">50&&<=70");//,"name",">2017-03-14 14:43:5"
		//System.out.println(lsm.size()+"结果="+lsm);
		//loadKeys();
		//Map valueByListkey = getValueByMapkey("hhe");
		//List<Map> valueByListkey = getValueByListkey(key,1,2);
		//System.out.println(valueByListkey);
		
		
		
		
	}

	
	
	public static void test1(){
		String key = "t_s_table";
		Long del = jedis.del(key);
		ArrayList<Object> arrayList = new ArrayList<Object>();
		Map m = new HashMap();
		m.put("id", "hhe");
		m.put("name", "zcw");
		arrayList.add(m);
		//insertSingleDataByKeyTableNameToListF("a",m);
		//insertSingleDataByKeyTableNameToListL("a",m);
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
		insertMultiDataToList(key,arrayList);
		Long llen = jedis.llen(key);
		List<String> lrange = jedis.lrange(key, 0, llen);
		List<String> lrange2 = jedis.lrange("a", 0, -1);
		//List<String> hvals = jedis.hvals(key);
		for (int i = 0; i < llen; i++) {
			//System.out.println(i+"=i:"+lrange.get(i));
		}
	}
	
	public static void test2(){
		insertSingleDataByKeyFieldToMap("id","{'id':'zhongchang2','name':'zcw','age':'6'}");
		String val = jedis.get("zhongchang2");
		Map mapBykey = getValueByMapkey("zhongchang2");
		Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }
	}
	public static void loadKeys(){
		Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key1 = it.next();   
            System.out.println(key1);   
        }
	}
}
