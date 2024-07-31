package com.model.vo;

public class IngredientVO {
	private int ing_id;
	private String ing_name;
	private int nen_id;
	
	public IngredientVO (int ing_id, String ing_name, int nen_id) {
		this.ing_id = ing_id;
		this.ing_name = ing_name;
		this.nen_id = nen_id;
	}
	public IngredientVO (String ing_name) {
		this.ing_name = ing_name;
	}
	
	public int getIng_id() {
		return ing_id;
	}
	public void setIng_id(int ing_id) {
		this.ing_id = ing_id;
	}
	public String getIng_name() {
		return ing_name;
	}
	public void setIng_name(String ing_name) {
		this.ing_name = ing_name;
	}
	public int getNen_id() {
		return nen_id;
	}
	public void setNen_id(int nen_id) {
		this.nen_id = nen_id;
	}
	
	

}
