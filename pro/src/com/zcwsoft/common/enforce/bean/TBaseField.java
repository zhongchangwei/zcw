package com.zcwsoft.common.enforce.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBaseField entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASE_FIELD", schema = "ZCW")
public class TBaseField implements java.io.Serializable {

	// Fields

	private String id;
	private String tablename;
	private String field;
	private String tname;
	private String width;
	private String dateformat;
	private String datatype;
	private String ishidden;
	private String sort;
	private Date createDate;
	private String createUserid;
	private String remark;
	private String state;
	private String islist;
	private String isblank;
	private String pkTablename;
	private String outputpath;
	private String packagename;
	private String beanname;
	private Date modifyDate;
	private String modifyUserid;

	// Constructors

	/** default constructor */
	public TBaseField() {
	}

	/** minimal constructor */
	public TBaseField(String id) {
		this.id = id;
	}

	/** full constructor */
	public TBaseField(String id, String tablename, String field, String tname,
			String width, String dateformat, String datatype, String ishidden,
			String sort, Date createDate, String createUserid, String remark,
			String state, String islist, String isblank, String pkTablename,
			String outputpath, String packagename, String beanname,
			Date modifyDate, String modifyUserid) {
		this.id = id;
		this.tablename = tablename;
		this.field = field;
		this.tname = tname;
		this.width = width;
		this.dateformat = dateformat;
		this.datatype = datatype;
		this.ishidden = ishidden;
		this.sort = sort;
		this.createDate = createDate;
		this.createUserid = createUserid;
		this.remark = remark;
		this.state = state;
		this.islist = islist;
		this.isblank = isblank;
		this.pkTablename = pkTablename;
		this.outputpath = outputpath;
		this.packagename = packagename;
		this.beanname = beanname;
		this.modifyDate = modifyDate;
		this.modifyUserid = modifyUserid;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TABLENAME", length = 30)
	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	@Column(name = "FIELD", length = 30)
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Column(name = "TNAME", length = 50)
	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Column(name = "WIDTH", length = 10)
	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Column(name = "DATEFORMAT", length = 20)
	public String getDateformat() {
		return this.dateformat;
	}

	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}

	@Column(name = "DATATYPE", length = 20)
	public String getDatatype() {
		return this.datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	@Column(name = "ISHIDDEN", length = 1)
	public String getIshidden() {
		return this.ishidden;
	}

	public void setIshidden(String ishidden) {
		this.ishidden = ishidden;
	}

	@Column(name = "SORT", length = 10)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_USERID", length = 32)
	public String getCreateUserid() {
		return this.createUserid;
	}

	public void setCreateUserid(String createUserid) {
		this.createUserid = createUserid;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATE", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ISLIST", length = 1)
	public String getIslist() {
		return this.islist;
	}

	public void setIslist(String islist) {
		this.islist = islist;
	}

	@Column(name = "ISBLANK", length = 10)
	public String getIsblank() {
		return this.isblank;
	}

	public void setIsblank(String isblank) {
		this.isblank = isblank;
	}

	@Column(name = "PK_TABLENAME", length = 200)
	public String getPkTablename() {
		return this.pkTablename;
	}

	public void setPkTablename(String pkTablename) {
		this.pkTablename = pkTablename;
	}

	@Column(name = "OUTPUTPATH", length = 200)
	public String getOutputpath() {
		return this.outputpath;
	}

	public void setOutputpath(String outputpath) {
		this.outputpath = outputpath;
	}

	@Column(name = "PACKAGENAME", length = 50)
	public String getPackagename() {
		return this.packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	@Column(name = "BEANNAME", length = 20)
	public String getBeanname() {
		return this.beanname;
	}

	public void setBeanname(String beanname) {
		this.beanname = beanname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE", length = 7)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "MODIFY_USERID", length = 32)
	public String getModifyUserid() {
		return this.modifyUserid;
	}

	public void setModifyUserid(String modifyUserid) {
		this.modifyUserid = modifyUserid;
	}

}