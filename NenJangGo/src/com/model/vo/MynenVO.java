package com.model.vo;

public class MynenVO {
	private int id;
	private String m_id;
	
	
	public MynenVO() {
	
	}
	
	public MynenVO(String m_id, int id) {
		this.m_id = m_id;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	
}
