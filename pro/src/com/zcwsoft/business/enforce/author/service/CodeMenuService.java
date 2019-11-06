package com.zcwsoft.business.enforce.author.service;

import com.zcwsoft.common.newutils.basebean.ReceParam;

public interface CodeMenuService {

	String list() throws Exception;

	String showData(ReceParam aps)throws Exception;
	String save(ReceParam aps)throws Exception;

}
