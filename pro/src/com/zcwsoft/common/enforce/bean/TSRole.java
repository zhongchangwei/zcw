package com.zcwsoft.common.enforce.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TSRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_S_ROLE", schema = "ZCW")
public class TSRole implements java.io.Serializable {

	// Fields

	private String id;
	private String roleName;
	private String remark;
	private Date createDate;
	private String createUserid;
	private String createUser;
	private Date updateDate;
	private String updateUserid;
	private String state;

	// Constructors

	/** default constructor */
	public TSRole() {
	}

	/** minimal constructor */
	public TSRole(String id, String roleName, Date createDate,
			String createUserid, String createUser) {
		this.id = id;
		this.roleName = roleName;
		this.createDate = createDate;
		this.createUserid = createUserid;
		this.createUser = createUser;
	}

	/** full constructor */
	public TSRole(String id, String roleName, String remark, Date createDate,
			String createUserid, String createUser, Date updateDate,
			String updateUserid, String state) {
		this.id = id;
		this.roleName = roleName;
		this.remark = remark;
		this.createDate = createDate;
		this.createUserid = createUserid;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUserid = updateUserid;
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

	@Column(name = "ROLE_NAME", nullable = false, length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", nullable = false, length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_USERID", nullable = false, length = 32)
	public String getCreateUserid() {
		return this.createUserid;
	}

	public void setCreateUserid(String createUserid) {
		this.createUserid = createUserid;
	}

	@Column(name = "CREATE_USER", nullable = false, length = 20)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 7)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_USERID", length = 32)
	public String getUpdateUserid() {
		return this.updateUserid;
	}

	public void setUpdateUserid(String updateUserid) {
		this.updateUserid = updateUserid;
	}

	@Column(name = "STATE", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}