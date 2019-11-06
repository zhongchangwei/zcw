package com.zcwsoft.business.enforce.author.service;

import com.zcwsoft.common.newutils.basebean.ReceParam;

public interface RoleService {
	
	String treeList(ReceParam rcep) throws Exception;

	String list(ReceParam rcep) throws Exception;
	
	String save(ReceParam rcep)throws Exception;
	
	String grid1ShowData(ReceParam rcep) throws Exception;
	
	String grid2ShowData(ReceParam rcep) throws Exception;
	
	String formShowData(ReceParam rcep) throws Exception;
	
	String remove(ReceParam rcep) throws Exception;
	
}