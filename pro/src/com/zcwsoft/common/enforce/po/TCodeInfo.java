package com.zcwsoft.common.enforce.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TCodeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CODE_INFO", schema = "ZCW")
public class TCodeInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String parentid;
	private String name;
	private String property;
	private String type;
	private BigDecimal sort;
	private String url;
	private String sysFlag;
	private Date createDate;
	private String createUser;
	private String createUserName;
	private Date modifyDate;
	private String modifyUser;
	private String modifyUserName;
	private String modifyType;
	private String state;
	private String remark;

	// Constructors

	/** default constructor */
	public TCodeInfo() {
	}

	/** minimal constructor */
	public TCodeInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public TCodeInfo(String id, String parentid, String name, String property,
			String type, BigDecimal sort, String url, String sysFlag,
			Date createDate, String createUser, String createUserName,
			Date modifyDate, String modifyUser, String modifyUserName,
			String modifyType, String state, String remark) {
		this.id = id;
		this.parentid = parentid;
		this.name = name;
		this.property = property;
		this.type = type;
		this.sort = sort;
		this.url = url;
		this.sysFlag = sysFlag;
		this.createDate = createDate;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.modifyDate = modifyDate;
		this.modifyUser = modifyUser;
		this.modifyUserName = modifyUserName;
		this.modifyType = modifyType;
		this.state = state;
		this.remark = remark;
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

	@Column(name = "PARENTID", length = 32)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(name = "NAME_", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PROPERTY", length = 50)
	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Column(name = "TYPE", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "SORT", precision = 22, scale = 0)
	public BigDecimal getSort() {
		return this.sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	@Column(name = "URL_", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "SYS_FLAG", length = 5)
	public String getSysFlag() {
		return this.sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_USER", length = 100)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "CREATE_USER_NAME", length = 100)
	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE", length = 7)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "MODIFY_USER", length = 100)
	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Column(name = "MODIFY_USER_NAME", length = 100)
	public String getModifyUserName() {
		return this.modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	@Column(name = "MODIFY_TYPE", length = 20)
	public String getModifyType() {
		return this.modifyType;
	}

	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}

	@Column(name = "STATE", length = 5)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}