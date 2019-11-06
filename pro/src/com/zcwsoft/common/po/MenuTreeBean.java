package com.zcwsoft.common.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuTreeBean implements Serializable {

	private static final long serialVersionUID = 8153945022449540327L;
	
	// 菜单id
    private String id;
    // 菜单名称
    private String name;
    // 父菜单id
    private String parentid;
    // 菜单lurl
    private String lurl;
    // 菜单图标
    private String icon = "0";
    // 菜单顺序
    private int order;
    // 子菜单
    private List<MenuTreeBean> children;
	public MenuTreeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuTreeBean(String id, String name, String parentid, String lurl,
			String icon, int order, List<MenuTreeBean> children) {
		super();
		this.id = id;
		this.name = name;
		this.parentid = parentid;
		this.lurl = lurl;
		this.icon = icon;
		this.order = order;
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getLurl() {
		return lurl;
	}
	public void setLurl(String lurl) {
		this.lurl = lurl;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public List<MenuTreeBean> getChildren() {
		/*if(this.children.isEmpty()||this.children==null){
			return new ArrayList<MenuTreeBean>();
		}*/
		return children;
	}
	public void setChildren(List<MenuTreeBean> children) {
		this.children = children;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MenuTreeBean [id=" + id + ", name=" + name + ", parentid="
				+ parentid + ", lurl=" + lurl + ", icon=" + icon + ", order="
				+ order + ", children=" + children + "]";
	}
	
}
