package com.zcwsoft.business.common.miniui.dao;

public interface LoadDataDao {

	String load(String data, Integer pageIndex, Integer pageSize, String sortField, String sortOrder, String sortFields) throws Exception;

	String loadArray(String sql) throws Exception;

}
