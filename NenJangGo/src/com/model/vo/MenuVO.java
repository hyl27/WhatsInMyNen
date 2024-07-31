package com.model.vo;

public class MenuVO {
	private int menu_id;
	private String menu_name;
	private String all_ingredient;
	
	public void MenuVO(int id, String name, String all) {
		this.menu_id = id;
		this.menu_name = name;
		this.all_ingredient = all;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getAll_ingredient() {
		return all_ingredient;
	}

	public void setAll_ingredient(String all_ingredient) {
		this.all_ingredient = all_ingredient;
	}
	
	
	

}
