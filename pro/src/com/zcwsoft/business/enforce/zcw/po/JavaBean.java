package com.zcwsoft.business.enforce.zcw.po;

public class JavaBean {

	private String id;
	private String name;
	private String age;
	
	
	public JavaBean(String id, String name, String age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public JavaBean() {
		super();
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "JavaBean [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	
	
	
	
	
}
