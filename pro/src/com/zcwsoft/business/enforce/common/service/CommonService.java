package com.zcwsoft.business.enforce.common.service;

public interface CommonService {

	String getDept()throws Exception;

	String getCity()throws Exception;

	String treeList() throws Exception;

	String getCodeMenu()throws Exception;
	
	String getUserTable()throws Exception;

	String treeListToCodeMenu()throws Exception;
}
