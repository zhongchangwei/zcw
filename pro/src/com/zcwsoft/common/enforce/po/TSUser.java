package com.zcwsoft.common.enforce.po;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;


/**
 * TSUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_S_USER"
    ,schema="ZCW"
)

public class TSUser  implements java.io.Serializable {


    // Fields    

     private String id;
     private String userId;
     private String password;
     private String deptId;
     private String roleId;
     private String realName;
     private String age;
     private String phone;
     private String msisdn;
     private String addresses;
     private String email;
     private String city;
     private String province;
     private String country;
     private String passwordHint;
     private String accountEnabled;
     private String accountExpired;
     private String accountLocked;
     private Integer passwordErrorTimes;
     private Date lastLoginTime;
     private String passwordErrorLock;
     private String rtxNum;
     private String remark;
     private String state;
     private Date createDate;
     private String createUserid;
     private String createUsername;
     private Date modifyDate;
     private String modifyUserid;


    // Constructors

    /** default constructor */
    public TSUser() {
    }

	/** minimal constructor */
    public TSUser(String id, String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }
    
    /** full constructor */
    public TSUser(String id, String userId, String password, String deptId, String roleId, String realName, String age, String phone, String msisdn, String addresses, String email, String city, String province, String country, String passwordHint, String accountEnabled, String accountExpired, String accountLocked, Integer passwordErrorTimes, Date lastLoginTime, String passwordErrorLock, String rtxNum, String remark, String state, Date createDate, String createUserid, String createUsername, Date modifyDate, String modifyUserid) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.deptId = deptId;
        this.roleId = roleId;
        this.realName = realName;
        this.age = age;
        this.phone = phone;
        this.msisdn = msisdn;
        this.addresses = addresses;
        this.email = email;
        this.city = city;
        this.province = province;
        this.country = country;
        this.passwordHint = passwordHint;
        this.accountEnabled = accountEnabled;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.passwordErrorTimes = passwordErrorTimes;
        this.lastLoginTime = lastLoginTime;
        this.passwordErrorLock = passwordErrorLock;
        this.rtxNum = rtxNum;
        this.remark = remark;
        this.state = state;
        this.createDate = createDate;
        this.createUserid = createUserid;
        this.createUsername = createUsername;
        this.modifyDate = modifyDate;
        this.modifyUserid = modifyUserid;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="ID", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
   
    
    @Column(name="USER_ID", unique=true, nullable=false, length=30)

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Column(name="PASSWORD", nullable=false, length=100)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="DEPT_ID", length=32)

    public String getDeptId() {
        return this.deptId;
    }
    
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    
    @Column(name="ROLE_ID", length=32)

    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    @Column(name="REAL_NAME", length=20)

    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    @Column(name="AGE", length=1)

    public String getAge() {
        return this.age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    @Column(name="PHONE", length=30)

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="MSISDN", length=50)

    public String getMsisdn() {
        return this.msisdn;
    }
    
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    
    @Column(name="ADDRESSES", length=200)

    public String getAddresses() {
        return this.addresses;
    }
    
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }
    
    @Column(name="EMAIL", length=50)

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="CITY", length=50)

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="PROVINCE", length=100)

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="COUNTRY", length=100)

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    @Column(name="PASSWORD_HINT", length=200)

    public String getPasswordHint() {
        return this.passwordHint;
    }
    
    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }
    
    @Column(name="ACCOUNT_ENABLED", length=1)

    public String getAccountEnabled() {
        return this.accountEnabled;
    }
    
    public void setAccountEnabled(String accountEnabled) {
        this.accountEnabled = accountEnabled;
    }
    
    @Column(name="ACCOUNT_EXPIRED", length=1)

    public String getAccountExpired() {
        return this.accountExpired;
    }
    
    public void setAccountExpired(String accountExpired) {
        this.accountExpired = accountExpired;
    }
    
    @Column(name="ACCOUNT_LOCKED", length=1)

    public String getAccountLocked() {
        return this.accountLocked;
    }
    
    public void setAccountLocked(String accountLocked) {
        this.accountLocked = accountLocked;
    }
    
    @Column(name="PASSWORD_ERROR_TIMES", precision=8, scale=0)

    public Integer getPasswordErrorTimes() {
        return this.passwordErrorTimes;
    }
    
    public void setPasswordErrorTimes(Integer passwordErrorTimes) {
        this.passwordErrorTimes = passwordErrorTimes;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_LOGIN_TIME", length=7)

    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }
    
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    @Column(name="PASSWORD_ERROR_LOCK", length=1)

    public String getPasswordErrorLock() {
        return this.passwordErrorLock;
    }
    
    public void setPasswordErrorLock(String passwordErrorLock) {
        this.passwordErrorLock = passwordErrorLock;
    }
    
    @Column(name="RTX_NUM", length=50)

    public String getRtxNum() {
        return this.rtxNum;
    }
    
    public void setRtxNum(String rtxNum) {
        this.rtxNum = rtxNum;
    }
    
    @Column(name="REMARK", length=500)

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="STATE", length=1)

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_DATE", length=7)

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    @Column(name="CREATE_USERID", length=32)

    public String getCreateUserid() {
        return this.createUserid;
    }
    
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }
    
    @Column(name="CREATE_USERNAME", length=20)

    public String getCreateUsername() {
        return this.createUsername;
    }
    
    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFY_DATE", length=7)

    public Date getModifyDate() {
        return this.modifyDate;
    }
    
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    @Column(name="MODIFY_USERID", length=32)

    public String getModifyUserid() {
        return this.modifyUserid;
    }
    
    public void setModifyUserid(String modifyUserid) {
        this.modifyUserid = modifyUserid;
    }

	@Override
	public String toString() {
		return "TSUser [id=" + id + ", userId=" + userId + ", password="
				+ password + ", deptId=" + deptId + ", roleId=" + roleId
				+ ", realName=" + realName + ", age=" + age + ", phone="
				+ phone + ", msisdn=" + msisdn + ", addresses=" + addresses
				+ ", email=" + email + ", city=" + city + ", province="
				+ province + ", country=" + country + ", passwordHint="
				+ passwordHint + ", accountEnabled=" + accountEnabled
				+ ", accountExpired=" + accountExpired + ", accountLocked="
				+ accountLocked + ", passwordErrorTimes=" + passwordErrorTimes
				+ ", lastLoginTime=" + lastLoginTime + ", passwordErrorLock="
				+ passwordErrorLock + ", rtxNum=" + rtxNum + ", remark="
				+ remark + ", state=" + state + ", createDate=" + createDate
				+ ", createUserid=" + createUserid + ", createUsername="
				+ createUsername + ", modifyDate=" + modifyDate
				+ ", modifyUserid=" + modifyUserid + "]";
	}
   








}