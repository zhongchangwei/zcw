package com.zcwsoft.business.enforce.author.bean;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *  entity. @author MyEclipse Persistence Tools
 */
/*存放系统对用户角色的定义*/
@Entity
@Table(name = "t_s_role", schema = "ZCW")
public class RoleBean implements java.io.Serializable {

private static final long serialVersionUID = 3259867707009629625L;


	private String id;//主键id
	private String rolename;//角色名
	private String description;//角色描述
	private Date createdate;//创建时间
	private String createuserid;//创建人id
	private String createuser;//创建人名称
	private Date modifydate;//修改时间
	private String modifyuserid;//修改人id
	private String state;//状态0：删除、1：正常

   public RoleBean() {
   }

   public RoleBean(String id) {
         this.id=id;
   }

   /*主键id get()方法*/
   @Id
   @Column(name="ID",unique = true, nullable=false, length=32)
   public String getId() {
         return this.id;
   }
   /*主键id set()方法*/
   public void setId(String id) {
         this.id=id;
   }
   /*角色名
   get()方法*/
   @Column(name="ROLE_NAME", nullable=false, length=50)
   public String getRolename() {
         return this.rolename;
   }
   /*角色名
   set()方法*/
   public void setRolename(String rolename) {
         this.rolename=rolename;
   }
   /*角色描述
   get()方法*/
   @Column(name="DESCRIPTION", length=200)
   public String getDescription() {
         return this.description;
   }
   /*角色描述
   set()方法*/
   public void setDescription(String description) {
         this.description=description;
   }
   /*创建时间
   get()方法*/
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="CREATE_DATE", nullable=false, length=7)
   public Date getCreatedate() {
         return this.createdate;
   }
   /*创建时间
   set()方法*/
   public void setCreatedate(Date createdate) {
         this.createdate=createdate;
   }
   /*创建人id
   get()方法*/
   @Column(name="CREATE_USERID", length=32)
   public String getCreateuserid() {
         return this.createuserid;
   }
   /*创建人id
   set()方法*/
   public void setCreateuserid(String createuserid) {
         this.createuserid=createuserid;
   }
   /*创建人名称
   get()方法*/
   @Column(name="CREATE_USER", length=20)
   public String getCreateuser() {
         return this.createuser;
   }
   /*创建人名称
   set()方法*/
   public void setCreateuser(String createuser) {
         this.createuser=createuser;
   }
   /*修改时间
   get()方法*/
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="MODIFY_DATE", length=7)
   public Date getModifydate() {
         return this.modifydate;
   }
   /*修改时间
   set()方法*/
   public void setModifydate(Date modifydate) {
         this.modifydate=modifydate;
   }
   /*修改人id
   get()方法*/
   @Column(name="MODIFY_USERID", length=32)
   public String getModifyuserid() {
         return this.modifyuserid;
   }
   /*修改人id
   set()方法*/
   public void setModifyuserid(String modifyuserid) {
         this.modifyuserid=modifyuserid;
   }
   /*状态0：删除、1：正常
   get()方法*/
   @Column(name="STATE", length=1)
   public String getState() {
         return this.state;
   }
   /*状态0：删除、1：正常
   set()方法*/
   public void setState(String state) {
         this.state=state;
   }
@Override
 public String toString(){return "RoleBean[id="+id+"rolename="+rolename+"description="+description+"createdate="+createdate+"createuserid="+createuserid+"createuser="+createuser+"modifydate="+modifydate+"modifyuserid="+modifyuserid+"state="+state+"]";
 }
	

}