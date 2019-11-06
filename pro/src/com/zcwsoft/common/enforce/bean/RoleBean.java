package com.zcwsoft.common.enforce.bean;

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
/*@Entity
@Table(name = "t_s_role", schema = "ZCW")*/
public class RoleBean implements java.io.Serializable {

////////////////////////////////////////////

	private String id;//主键id
	private String rolename;//角色
	private String remark;//备注
	private Date createdate;//创建时间
	private String createuserid;//创建人id
	private String createuser;//创建人名称
	private Date updatedate;//修改时间
	private String updateuserid;//修改人id
	private String state;//状态0：删除、1：正常
	
	
///////////////////////////////////////////	
	
	
	
	
	
	public String getId(){
		return null;
	}

}