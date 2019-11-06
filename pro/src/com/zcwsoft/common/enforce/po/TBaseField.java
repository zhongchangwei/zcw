package com.zcwsoft.common.enforce.po;

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
			String state) {
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

}