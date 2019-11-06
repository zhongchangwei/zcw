package com.zcwsoft.common.newutils.redisutils.where;

import java.util.List;
import java.util.Map;

import com.zcwsoft.common.newutils.redisutils.core.RedisEache;


/**
 * 单条件筛选类
 * @author Administrator
 *
 */
public class RedisSingleUtil {

	
	public RedisSingleUtil()
	{
		
	}
	/**
	 * 获取数据类型为String
	 * @param key	键
	 * @return	
	 */
	public String getValueByStringkey(String key)
	{
		return RedisEache.getValueByStringkey(key);
	}

	/**
	 * 获取数据类型为List<Map>
	 * @param key	
	 * @return	
	 */
	public List<Map> getValueByListkey(String key)
	{
		return RedisEache.getValueByListkey(key);
	}
	
	/**
	 * 获取数据类型为List<Map>
	 * @param key		键
	 * @param start		其实位置
	 * @param end		结束位置
	 * @return
	 */
	public List<Map> getValueByListkey(String key,Long start,Long end)
	{
		return RedisEache.getValueByListkey(key, start, end);
	}
	
	/**
	 * 获取数据类型为Map
	 * @param key	键
	 * @return	
	 */
	public Map getValueByMapkey(String key)
	{
		return RedisEache.getValueByMapkey(key);
	}
	
	/**
	 * 等于 或
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	public List<Map> getEqualOr(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		List<Map> getTnEqValueByListKeyInWhereOr = RedisEache.getTnEqValueByListKeyInWhereOr(TableNameKey, FieldAndValue);
		return getTnEqValueByListKeyInWhereOr;
	}
	/**
	 * 等于 且
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	public List<Map> getEqualAnd(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		List<Map> getTnEqValueByListKeyInWhereAnd = RedisEache.getTnEqValueByListKeyInWhereAnd(TableNameKey, FieldAndValue);
		return getTnEqValueByListKeyInWhereAnd;
	}

	/**
	 * 不等于
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	public List<Map> getNotEqual(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.notEqlFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnNotEqValueByListKeyInWhere = RedisEache.getTnNotEqValueByListKeyInWhere(TableNameKey, FieldAndValue);
		return getTnNotEqValueByListKeyInWhere;
	}

	/**
	 * 等于
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值,多个使用逗号隔开） <br>	a,b,c,d	或 1,2,3,4	<br>
	 * 示范：RedisUtil.getIn("key","filed","a,b,c")
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getInOr(String TableNameKey,Object...FieldAndValue)
	{
		return this.getEqualOr(TableNameKey, FieldAndValue);
	}
	/**
	 * 等于
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值,多个使用逗号隔开） <br>	a,b,c,d	或 1,2,3,4	<br>
	 * 示范：RedisUtil.getIn("key","filed","a,b,c")
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getInAnd(String TableNameKey,Object...FieldAndValue)
	{
		return this.getEqualAnd(TableNameKey, FieldAndValue);
	}

	/**
	 * 不等于
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值,多个使用逗号隔开）<br>	a,b,c,d	或 1,2,3,4	<br>
	 * 示范：RedisUtil。getNotIn("key","filed","a,b,c")
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getNotIn(String TableNameKey,Object...FieldAndValue)
	{
		return this.getNotEqual(TableNameKey, FieldAndValue);
	}
	

	/**
	 * 模糊查询（只支持包含，不存在左、右模糊）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getLikeAnd(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.likeFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnLikeValueByListKeyInWhere = RedisEache.getTnLikeAndValueByListKeyInWhere(TableNameKey, FieldAndValue);
		return getTnLikeValueByListKeyInWhere;
	}
	/**
	 * 模糊查询（只支持包含，不存在左、右模糊）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getLike(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.likeFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnLikeValueByListKeyInWhere = RedisEache.getTnLikeOrValueByListKeyInWhere(TableNameKey, FieldAndValue);
		return getTnLikeValueByListKeyInWhere;
	}
	
	/**
	 * 模糊查询（只支持包含，不存在左、右模糊）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getNotLike(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.likeFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnNotLikeValueByListKeyInWhere = RedisEache.getTnNotLikeValueByListKeyInWhere(TableNameKey, FieldAndValue);
		return getTnNotLikeValueByListKeyInWhere;
	}
	
	/**
	 * 大于 And（且）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getGreaterAnd(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.maxFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereAnd(TableNameKey, FieldAndValue);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}

	/**
	 * 大于 Or（或）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getGreaterOr(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.maxFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereOr(TableNameKey, FieldAndValue);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}
	
	/**
	 * 大于且等于  And(且)
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getGreaterEqAnd(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String eq = RedisEache.eqlFymbol;
		String symbol = RedisEache.maxFymbol + eq;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereAnd(TableNameKey, FieldAndValue);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}
	/**
	 * 大于且等于  And(且)
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getGreaterEqOr(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String eq = RedisEache.eqlFymbol;
		String symbol = RedisEache.maxFymbol + eq;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereOr(TableNameKey, FieldAndValue);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}

	/**
	 * 小于 and（且）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getLessAnd(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.minFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereAnd(TableNameKey, FieldAndValue);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}
	
	/**
	 * 小于 Oe（或）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getLessOr(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.minFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereOr(TableNameKey, FieldAndValue);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}

	/**
	 * 小于且等于 And（且）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getLessEqAnd(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String eq = RedisEache.eqlFymbol;
		String symbol = RedisEache.minFymbol + eq;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereAnd(TableNameKey, FieldAndValue);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}
	
	/**
	 * 小于且等于 Or（或）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getLessEqOr(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String eq = RedisEache.eqlFymbol;
		String symbol = RedisEache.minFymbol + eq;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereOr(TableNameKey, FieldAndValue);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}
	
	/**
	 * 时间查询 Or(或)
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getDateOr(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.dateFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnDateValueByListKeyInWhereOr = RedisEache.getTnDateValueByListKeyInWhereOr(TableNameKey, FieldAndValue);
		return getTnDateValueByListKeyInWhereOr;
	}
	
	/**
	 * 时间查询  And（且）
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，奇数(表字段)，偶数(字段对应值）
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getDateAnd(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%2)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		String symbol = RedisEache.dateFymbol;
		this.replaceDate(FieldAndValue, symbol);
		List<Map> getTnDateValueByListKeyInWhereAnd = RedisEache.getTnDateValueByListKeyInWhereAnd(TableNameKey, FieldAndValue);
		return getTnDateValueByListKeyInWhereAnd;
	}
	
	
	/**
	 * 获取开始至结束(int and Date) 包含
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，第一个（字段名称） 第二个（开始值），第三个（结束值），第四个（字段名称）第五个（开始值），第六个（结束值）............以此类推
	 * 示范：RedisUtil.getBegin_End("key", "filed","begin","end","filed2","begin","end");
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getBeginEndEq(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%3)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		int length = FieldAndValue.length/3*2;
		int sum = 0;
		String eq = RedisEache.eqlFymbol;
		String groupF = RedisEache.groupFymbol;
		String minEq = RedisEache.minFymbol + eq;
		String maxEq = RedisEache.maxFymbol + eq;
		Object[] ret = new Object[length];
		for (int i = 0; i < FieldAndValue.length; i+=3) 
		{
			ret[sum] = FieldAndValue[i];
			sum++;
			if((i+2) < FieldAndValue.length)//防止数组越界
			{
				ret[sum] = maxEq +  FieldAndValue[i+1] + groupF + minEq + FieldAndValue[i+2];
				sum++;
			}
		}
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereAnd(TableNameKey, ret);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}
	/**
	 * 获取开始至结束(int and Date) 不包含
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，第一个（字段名称） 第二个（开始值），第三个（结束值），第四个（字段名称）第五个（开始值），第六个（结束值）............以此类推
	 * 示范：RedisUtil.getBegin_End("key", "filed","begin","end","filed2","begin","end");
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getBeginEndNotEq(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%3)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		int length = FieldAndValue.length/3*2;
		int sum = 0;
		String eq = RedisEache.eqlFymbol;
		String groupF = RedisEache.groupFymbol;
		String min = RedisEache.minFymbol ;
		String max = RedisEache.maxFymbol ;
		Object[] ret = new Object[length];
		for (int i = 0; i < FieldAndValue.length; i+=3) 
		{
			ret[sum] = FieldAndValue[i];
			sum++;
			if((i+2) < FieldAndValue.length)//防止数组越界
			{
				ret[sum] = max +  FieldAndValue[i+1] + groupF + min + FieldAndValue[i+2];
				sum++;
			}
		}
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnGreaterAndLessValueByListKeyInWhereAnd(TableNameKey, ret);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}
	
	
	/**
	 * 获取开始至结束(Date)
	 * @param listTypeKey	redis缓存中list类型的键
	 * @param FieldAndValue	 数组形式，第一个（字段名称） 第二个（开始值），第三个（结束值），第四个（字段名称）第五个（开始值），第六个（结束值）............以此类推
	 * 示范：RedisUtil.getBegin_End("key", "filed","begin","end","filed2","begin","end");
	 * @return 返回搜索到匹配的值
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getDateBeginEnd(String TableNameKey,Object...FieldAndValue)
	{
		if((FieldAndValue.length%3)!=0)
		{
			return null;//如果传入的数组格式不对
		}
		int length = FieldAndValue.length/3*2;
		int sum = 0;
		String groupF = "~";
		String symbol = RedisEache.dateFymbol;
		Object[] ret = new Object[length];
		for (int i = 0; i < FieldAndValue.length; i+=3) 
		{
			ret[sum] = FieldAndValue[i];
			sum++;
			if((i+2) < FieldAndValue.length)//防止数组越界
			{
				ret[sum] = symbol +  FieldAndValue[i+1] + groupF +  FieldAndValue[i+2];
				sum++;
			}
		}
		List<Map> getTnGreaterAndLessValueByListKeyInWhereAnd = RedisEache.getTnDateValueByListKeyInWhereAnd(TableNameKey, ret);
		return getTnGreaterAndLessValueByListKeyInWhereAnd;
	}
	
	
	
	
	
	
	/**
	 * 替换数据
	 * @param FieldAndValue	
	 * @param symbol
	 * @return
	 */
	private Object replaceDate(Object[] FieldAndValue,String symbol)
	{
		for (int i = 0; i < FieldAndValue.length; i+=2) 
		{
			if((i+1) < FieldAndValue.length)//防止数组越界
			{
				FieldAndValue[i+1] = symbol +  FieldAndValue[i+1];
			}
		}
		return FieldAndValue;
	}
}
