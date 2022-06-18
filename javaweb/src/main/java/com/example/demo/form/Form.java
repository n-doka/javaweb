package com.example.demo.form;

import javax.validation.constraints.Size;

public class Form {

	private long id;
	@Size(min = 1, max = 10, message = "input 1 to 10")
	private String title;
	@Size(min = 1, max = 100, message = "input 1 to 100")
	private String detail;

	@Size(min = 1, max = 100, message = "input 1 to 100")
	private String name;

	private boolean done;

	public Form() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
