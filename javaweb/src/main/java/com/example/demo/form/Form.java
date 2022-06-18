package com.example.demo.form;

import javax.validation.constraints.Size;

public class Form {

	@Size(min=1, max=10, message="input 1 to 10")
	private String name;
	
	public Form() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
