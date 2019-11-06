package com.zcwsoft.common.enforce.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TSDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_S_DEPT", schema = "ZCW")
public class TSDept implements java.io.Serializable {

	// Fields

	private String id;
	private String pid;
	private String deptName;
	private String remark;
	private Date createDate;
	private String createUserid;
	private String createUsername;
	private Date modifyDate;
	private String modifyUserid;
	private String state;

	// Constructors

	/** default constructor */
	public TSDept() {
	}

	/** minimal constructor */
	public TSDept(String id) {
		this.id = id;
	}

	/** full constructor */
	public TSDept(String id, String pid, String deptName, String remark,
			Date createDate, String createUserid, String createUsername,
			Date modifyDate, String modifyUserid, String state) {
		this.id = id;
		this.pid = pid;
		this.deptName = deptName;
		this.remark = remark;
		this.createDate = createDate;
		this.createUserid = createUserid;
		this.createUsername = createUsername;
		this.modifyDate = modifyDate;
		this.modifyUserid = modifyUserid;
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

	@Column(name = "PID", length = 32)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "DEPT_NAME", length = 100)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Column(name = "CREATE_USERNAME", length = 20)
	public String getCreateUsername() {
		return this.createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
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

	@Column(name = "STATE", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}