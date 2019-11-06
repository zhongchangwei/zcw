package com.zcwsoft.common.util;

import java.sql.Types;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StandardBasicTypes;
/**
* 类名称：Oracle10gDialectOverrider 
* 类描述： 继承数据库方言，在原有方言上添加NVARCHAR转STRING
* 创建人：cyc
* 创建时间：2016年12月5日17:26:10
* 修改人：cyc
* 修改时间：2016年12月5日17:26:13
* 修改备注：   
* @version V1.0
* </pre>
 */
public class Oracle10gDialectOverrider extends Oracle10gDialect{

	public Oracle10gDialectOverrider(){
		super();
		registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
	}
	
}
