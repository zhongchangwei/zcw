package com.zcwsoft.business.enforce.author.service;

import com.zcwsoft.common.newutils.basebean.ReceParam;

public interface UserService {

	String list() throws Exception;
	String showData(ReceParam rcep)throws Exception;
	String save(ReceParam rcep)throws Exception;
	String delete(ReceParam aps)throws Exception;
}
