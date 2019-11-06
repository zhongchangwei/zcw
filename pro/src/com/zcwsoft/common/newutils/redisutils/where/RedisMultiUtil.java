package com.zcwsoft.common.newutils.redisutils.where;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.zcwsoft.common.newutils.BaseSysUtils;
import com.zcwsoft.common.newutils.pub.util.TableIndexUtils;
import com.zcwsoft.common.newutils.redisutils.core.RedisEache;

/**
 * 多条件筛选类
 * 使用样例：
 * 		RedisMultiUtil rs = new RedisMultiUtil();
   		rs.setTableName("t_s_menu");//这里也可以 rs.setListMap(List<Map>);
   		rs.setEq("Field","wheresValue");这里是传入条件筛选，可多次调用
   		......以此类推
   		List<Map> execute = rs.execute();//执行筛选，获取筛选后的结果集
 * @author Administrator
 *
 */
public class RedisMultiUtil {


	private String tableName;
	/**
	 * T=需要查询缓存获取结果集数据
	 * L=创建类的时候传入结果集
	 */
	private String state;
	private String symbol;
	private LinkedList eqLs;
	private String primary;
	private Map itMap = new HashMap();
	private List<Map> itLsM = new ArrayList<Map>();
	private List<Map> ResultLsM = new ArrayList<Map>();
	
	private String eqAnd = "eq_And";
	private String eqOr = "eqOr";
	private String notEq = "notEq";
	private String likeOr = "likeOr";
	private String likeAnd = "like_And";
	private String notLike = "notLike";
	private String greaterAnd = "greater_And";
	private String greaterOr = "greaterOr";
	private String greaterEqAnd = "greaterEq_And";
	private String greaterEqOr = "greaterEqOr";
	private String lessAnd = "less_And";
	private String lessOr = "lessOr";
	private String lessEqAnd = "lessEq_And";
	private String lessEqOr = "lessEqOr";
	private String dateAnd = "date_And";
	private String dateOr = "dateOr";
	private String beginEndEq = "beginEndEq";
	private String beginEndNotEq = "beginEndNotEq";
	private String dateBeginEnd = "dateBeginEnd";
	
	public RedisMultiUtil()
	{
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* 执行方法，获取最终结果集
	* @return
	*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> execute()
	{
		if("T".equals(state))
		{
			List<Map> valueByListkey =new ArrayList();
			//查缓存
			if(symbol.contains("#"+eqAnd+"#"))
			{
				valueByListkey = RedisEache.getTnEqValueByListKeyInWhereOr(this.tableName, this.replaceDate(eqAnd));
			}else{
				
				valueByListkey = RedisEache.getValueByListkey(this.tableName);
			}
			return this.run(valueByListkey);
		}
		else if("L".equals(state))
		{
			//筛选LIst
			return this.run(itLsM);
		}
		return new ArrayList<Map>();
		//return  RedisEacheNew.getValueByListKeyInWhere(this.tableName, this.ListToArray(linkLs));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	private List<Map> run(List<Map> lsm)
	{
		List<Map> rlsm = new ArrayList();
		String syb = "";
		if(lsm.size()==0 ||lsm.isEmpty())
		{
			return new ArrayList<Map>();
		}
//////////////////////////////OR////////////////////////////////////////////
		if(!symbol.contains("And#"))
		{
			syb = eqOr;
			if(itMap.containsKey(syb))
			{
				rlsm = this.distinct(ScreenDataUtils.getEqValueByListKeyInWhereOr(lsm, this.replaceDate(syb)) ,rlsm);
			}
			syb = greaterOr;
			if(itMap.containsKey(syb))
			{
				rlsm = this.distinct(ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereOr(lsm, this.replaceDate(syb)) ,rlsm);
			}
			syb = greaterEqOr;
			if(itMap.containsKey(syb))
			{
				rlsm = this.distinct(ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereOr(lsm, this.replaceDate(syb)) ,rlsm);
			}
			syb = lessOr;
			if(itMap.containsKey(syb))
			{
				rlsm = this.distinct(ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereOr(lsm, this.replaceDate(syb)) ,rlsm);
			}
			syb = lessEqOr;
			if(itMap.containsKey(syb))
			{
				rlsm = this.distinct(ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereOr(lsm, this.replaceDate(syb)) ,rlsm);
			}
			syb = dateOr;
			if(itMap.containsKey(syb))
			{
				rlsm = this.distinct(ScreenDataUtils.getDateValueByListKeyInWhereOr(lsm, this.replaceDate(syb)) ,rlsm);
			}
			syb = likeOr;
			if(itMap.containsKey(syb))
			{
				rlsm = this.distinct(ScreenDataUtils.getLikeOrValueByListKeyInWhere(lsm, this.replaceDate(syb)) ,rlsm);
			}
			lsm = rlsm;
//////////////////////////////OR////////////////////////////////////////////
		}else{//如果是And的表达式，就没必要判断上面or的了
//////////////////////////////AND////////////////////////////////////////////
			syb = eqAnd;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getEqValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
			
			syb = notEq;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getNotEqValueByListKeyInWhere(lsm, this.replaceDate(syb));
			}
			syb = notLike;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getNotLikeValueByListKeyInWhere(lsm, this.replaceDate(syb));
			}
			syb = likeAnd;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getLikeAndValueByListKeyInWhere(lsm, this.replaceDate(syb));
			}
			syb = greaterAnd;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
		
			syb = greaterEqAnd;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
			
			syb = lessAnd;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
			
			syb = lessEqAnd;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
			
			syb = dateAnd;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getDateValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
			
			syb = beginEndEq;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
			syb = beginEndNotEq;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getGreaterAndLessValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
			syb = dateBeginEnd;
			if(itMap.containsKey(syb))
			{
				lsm = ScreenDataUtils.getDateValueByListKeyInWhereAnd(lsm, this.replaceDate(syb));
			}
		}
//////////////////////////////AND////////////////////////////////////////////	
		return lsm;
	}
	
	/////////////////////////////////////////////////////////////////////Set方法设置条件，带下划线_的方法需要3个一组其余2个一组//////////////////////////	
	/**
	* 传入表名（redis缓存List类型键）,
	* @param tableName	redis缓存中的表名键
	*/
	public void setTableName(String tableName)
	{
		this.state = "T";
		this.tableName = tableName;
		TableIndexUtils tbn = new TableIndexUtils(tableName);
		this.primary = tbn.getUniquePrimaryField();
		if(BaseSysUtils.isNotBlank(this.primary))
		{
			this.primary =this.primary.toUpperCase();
		}
	}
	
	/**
	* 筛选list
	* @param tableName	redis缓存中的表名键
	*/
	@SuppressWarnings("rawtypes")
	public void setListMap(List<Map> screenLsM)
	{

		this.state = "L";
		this.itLsM = screenLsM;
	}
	

	/**
	* 唯一性字段
	* @param primary	唯一性字段，用于去掉重复数据
	*/
	public void setPrimaryFieldKey(String primary)
	{
		this.primary = primary;
	}
	/**
	 * 等于 或
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 
	 */
	public void setEqualOr(String whereField,String whereValue)
	{
		this.appenWhere("",eqOr,whereField,whereValue);
	}
	/**
	 * 等于 且
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	public void setEqualAnd(String whereField,String whereValue)
	{
		this.appenWhere("",eqAnd,whereField,whereValue);
	}

	/**
	 * 不等于
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 
	 */
	public void setNotEqual(String whereField,String whereValue)
	{
		String symbol = RedisEache.notEqlFymbol;
		this.appenWhere(symbol,notEq,whereField,whereValue);
	}

	/**
	 * 等于
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	
	public void setInAnd(String whereField,String whereValue)
	{
		this.setEqualAnd(whereField,whereValue);
	}
	/**
	 * 等于
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	
	public void setInOr(String whereField,String whereValue)
	{
		this.setEqualOr(whereField,whereValue);
	}

	/**
	 * 不等于
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	
	public void setNotIn(String whereField,String whereValue)
	{

		this.setNotEqual(whereField, whereValue);
	}
	

	/**
	 * 模糊查询（只支持包含，不存在左、右模糊）
	 * @param whereField     条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	   条件匹配的值
	 */
	
	public void setLikeOr(String whereField,String whereValue)
	{
		String symbol = RedisEache.likeFymbol;
		this.appenWhere(symbol,likeOr,whereField,whereValue);
	}
	/**
	 * 模糊查询（只支持包含，不存在左、右模糊）
	 * @param whereField     条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	  条件匹配的值
	 */
	
	public void setLikeAnd(String whereField,String whereValue)
	{
		String symbol = RedisEache.likeFymbol;
		this.appenWhere(symbol,likeAnd,whereField,whereValue);
	}
	
	/**
	 * 模糊查询（只支持包含，不存在左、右模糊）
	 * @param whereField     条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	  条件匹配的值
	 */
	
	public void setNotLike(String whereField,String whereValue)
	{
		String symbol = RedisEache.likeFymbol;
		this.appenWhere(symbol,notLike,whereField,whereValue);
	}
	
	/**
	 * 大于 And（且）
	 * @param whereField     条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	   条件匹配的值
	 */
	
	public void setGreaterAnd(String whereField,String whereValue)
	{
		String symbol = RedisEache.maxFymbol;
		this.appenWhere(symbol,greaterAnd,whereField,whereValue);
	}

	/**
	 * 大于 Or（或）
	 * @param whereField     条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	   条件匹配的值
	 */
	
	public void setGreaterOr(String whereField,String whereValue)
	{
		String symbol = RedisEache.maxFymbol;
		this.appenWhere(symbol,greaterOr,whereField,whereValue);
	}
	
	/**
	 * 大于且等于  And(且)
	 * @param whereField     条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	   条件匹配的值
	 */
	
	public void setGreaterEqAnd(String whereField,String whereValue)
	{
		String eq = RedisEache.eqlFymbol;
		String symbol = RedisEache.maxFymbol + eq;
		this.appenWhere(symbol,greaterEqAnd,whereField,whereValue);
	}
	/**
	 * 大于且等于  And(且)
	 * @param whereField     条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	   条件匹配的值
	 */
	
	public void setGreaterEqOr(String whereField,String whereValue)
	{
		String eq = RedisEache.eqlFymbol;
		String symbol = RedisEache.maxFymbol + eq;
		this.appenWhere(symbol,greaterEqOr,whereField,whereValue);
	}

	/**
	 * 小于 and（且）
	 * @param whereField     条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	   条件匹配的值
	 */
	
	public void setLessAnd(String whereField,String whereValue)
	{
		String symbol = RedisEache.minFymbol;
		this.appenWhere(symbol,lessAnd,whereField,whereValue);
	}
	
	/**
	 * 小于 Oe（或）
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	
	public void setLessOr(String whereField,String whereValue)
	{
		String symbol = RedisEache.minFymbol;
		this.appenWhere(symbol,lessOr,whereField,whereValue);
	}

	/**
	 * 小于且等于 And（且）
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	
	public void setLessEqAnd(String whereField,String whereValue)
	{
		String eq = RedisEache.eqlFymbol;
		String symbol = RedisEache.minFymbol + eq;
		this.appenWhere(symbol,lessEqAnd,whereField,whereValue);
	}
	
	/**
	 * 小于且等于 Or（或）
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	
	public void setLessEqOr(String whereField,String whereValue)
	{
		String eq = RedisEache.eqlFymbol;
		String symbol = RedisEache.minFymbol + eq;
		this.appenWhere(symbol,lessEqOr,whereField,whereValue);
	}
	
	/**
	 * 时间查询 Or(或)
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	public void setDateOr(String whereField,String whereValue)
	{
		String symbol = RedisEache.dateFymbol;
		this.appenWhere(symbol,dateOr,whereField,whereValue);
	}
	
	/**
	 * 时间查询  And（且）
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 条件匹配的值
	 */
	
	public void setDateAnd(String whereField,String whereValue)
	{
		String symbol = RedisEache.dateFymbol;
		this.appenWhere(symbol,dateAnd,whereField,whereValue);
	}
	
	
	/**
	 * 获取开始至结束(int and Date) 包含
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 数组形式，第一个（字段名称） 第二个（开始值），第三个（结束值），第四个（字段名称）第五个（开始值），第六个（结束值）............以此类推
	 * 示例：RedisUtil.getBegin_End("key", "filed","begin","end","filed2","begin","end");
	 */
	
	public void setBeginEndEq(String whereField,String whereBeginValue,String whereEndValue)
	{
		String eq = RedisEache.eqlFymbol;
		String groupF = RedisEache.groupFymbol;
		String minEq = RedisEache.minFymbol + eq;
		String maxEq = RedisEache.maxFymbol + eq;
		
		String ret = maxEq +  whereBeginValue + groupF + minEq + whereEndValue;

		this.appenWhere("",beginEndEq,whereField,ret);
	}
	/**
	 * 获取开始至结束(int and Date) 不包含
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 数组形式，第一个（字段名称） 第二个（开始值），第三个（结束值），第四个（字段名称）第五个（开始值），第六个（结束值）............以此类推
	 * 示例：RedisUtil.getBegin_End("key", "filed","begin","end","filed2","begin","end");
	 */
	
	public void setBeginEndNotEq(String whereField,String whereBeginValue,String whereEndValue)
	{
		String groupF = RedisEache.groupFymbol;
		String min = RedisEache.minFymbol ;
		String max = RedisEache.maxFymbol ;
		String ret = max +  whereBeginValue + groupF + min + whereEndValue;

		this.appenWhere("",beginEndNotEq,whereField,ret);
	}
	
	
	/**
	 * 获取开始至结束(Date)
	 * @param whereField    条件字段名称，不论大小写，因后面会自动转换大写
	 * @param FieldAndValue	 数组形式，第一个（字段名称） 第二个（开始值），第三个（结束值），第四个（字段名称）第五个（开始值），第六个（结束值）............以此类推
	 * 示例：RedisUtil.getBegin_End("key", "filed","begin","end","filed2","begin","end");
	 
	 */
	
	public void setDateBeginEnd(String whereField,String whereBeginValue,String whereEndValue)
	{
		String groupF = "~";
		String symbol = RedisEache.dateFymbol;
		String ret = symbol +  whereBeginValue + groupF +  whereEndValue;

		this.appenWhere("",dateBeginEnd,whereField,ret);
	}
	
	
	
	
	
	
	/**
	 * 替换数据
	 * @param FieldAndValue	
	 * @param symbol
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] replaceDate(String symbol)
	{
		Object obj = itMap.get(symbol);
		if(obj != null && obj instanceof Map)
		{
			Map itemMap  = (Map) obj;
			LinkedList ls = new LinkedList();
			for(Object key:itemMap.keySet())
			{
				ls.add(key);
				ls.add(itemMap.get(key));
			}
			return ListToArray(ls);
		}else{
			return null;
		}
		
	}
	/**
	 * list转数组
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Object[] ListToArray(List ls)
	{
		Object[] obj = new Object[ls.size()];
		for (int i = 0; i < ls.size(); i++) 
		{
			obj[i] = ls.get(i);
		}
		return obj;
	}
	/**
	 * 多次调用相同set方法（参数不一样），吧数据收集放到Map<String,Map>中
	 * @param flag			条件类型符号	
	 * @param syb			调用了那类型的set方法，调用一次就插入一条数据仅Map，下次再调用相同set就取出追加插入
	 * @param whereField	条件匹配字段
	 * @param whereValue	条件匹配值	
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void appenWhere(String flag ,String syb,String whereField,String whereValue)
	{
		symbol += "#"+ syb +"# ";
		Object obj = itMap.get(syb);
		if(obj != null && obj instanceof Map)
		{
			//重复调用，追加参数
			Map itemMap  = (Map) obj;
			this.putFieldAndValue(flag,syb,itemMap,whereField,whereValue);
			itMap.put(syb, itemMap);
		}else{
			//第一次调用
			Map itemMap = new HashMap();
			this.putFieldAndValue(flag,syb,itemMap,whereField,whereValue);
			itMap.put(syb, itemMap);
		}
	}
	/**
	 * 处理Map参数
	 * @param flag			条件类型符号	
	 * @param m				参数Map
	 * @param whereField	条件匹配字段
	 * @param whereValue	条件匹配值	
	 */
	@SuppressWarnings("unchecked")
	private void putFieldAndValue(String flag,String syb,Map m,String whereField,String whereValue)
	{
		if(m != null)
		{
			if(m.containsKey(whereField) &&! syb.contains("And"))//如果调用了带And的方法则不追加，执行替换
			{
				m.put(whereField, m.get(whereField) + WhereOperation.FYMOBL_SPRIT + whereValue);//这里就多次调用相同set方法，把新值追加存放
			}else{
				m.put(whereField, flag+whereValue);//第一次调用，把符号插入进去
			}
		}
	}
	/**
	 * 去掉重复数据，一般调用OR方法才会执行
	 * @param source
	 * @param lsm
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Map> distinct(List<Map> source,List<Map> lsm)
	{
		List<Map> rslm = lsm;//new ArrayList<Map>();
		if(lsm.isEmpty()){
			for (int j = 0; j < source.size(); j++) 
			{
				rslm.add(source.get(j));
			}
		}else{
			for (int i = 0; i < source.size(); i++) 
			{
				boolean flag = true;
				for (int j = 0; j < lsm.size(); j++) 
				{
					if(lsm.get(j).get(primary).equals(source.get(i).get(primary)))
					{
						flag = false;
						break;
					}
				}
				if(flag)
				{
					rslm.add(source.get(i));
				}
			}
		}
		return rslm;
		 
	}
	public static void main(String[] args) {
		RedisMultiUtil a = new RedisMultiUtil();
//		a.appenWhere("##", "One", "field", "Value");
		a.setEqualAnd("field", "Value2");
		a.setEqualAnd("field", "Value3");
		a.setEqualOr("field", "Value4");
		a.setEqualOr("field", "Value5");
		/*a.setNotEqual("field", "Value2");
		a.setNotEqual("field", "Value3");*/
		a.setBeginEndEq("field", "5", "7");
		a.setDateAnd("field", "2018-01-02");
		a.setDateAnd("field", "2018-01-03");
		
		String[] ss = (String[])a.replaceDate("beginEndEq");
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}
		//System.out.println(a.replace("beginEndEq").hashCode().toString());//notEq  dateAnd
	}
}
