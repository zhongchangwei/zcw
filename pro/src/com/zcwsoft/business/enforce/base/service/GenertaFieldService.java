package com.zcwsoft.business.enforce.base.service;

import com.zcwsoft.common.newutils.basebean.BaseParamsBean;
import com.zcwsoft.common.newutils.basebean.ReceParam;

public interface GenertaFieldService {

	String list(ReceParam rcep) throws Exception;
	
	String Save(ReceParam aps)throws Exception;
	
	String getTableFieldByTableName(ReceParam rcep) throws Exception;
	
	String grid1ShowData(ReceParam rcep) throws Exception;

	String grid1AddShowData(ReceParam rcep) throws Exception;
	
	String formShowData(ReceParam rcep) throws Exception;
	
	String getMaxSortByTabName(ReceParam rcep) throws Exception;
	
	String generateLabel(ReceParam rcep)throws Exception;

	String remove(ReceParam aps)throws Exception;

	String getUserTable()throws Exception;
}
