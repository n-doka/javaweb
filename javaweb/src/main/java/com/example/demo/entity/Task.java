package com.example.demo.entity;

import javax.validation.constraints.Size;

public class Task {
	private long id;
	@Size(min = 1, max = 10, message = "input 1 to 10")
	private String title;
	@Size(min = 1, max = 100, message = "input 1 to 100")
	private String detail;

	@Size(min = 1, max = 100, message = "input 1 to 100")
	private String name;

	private boolean done;
	
	
	public Task() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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

	public boolean getDone() {
		return done;
	}


	public void setDone(boolean done) {
		this.done = done;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override // 親クラスのtuString()の動作を上書きする
	public String toString(){
		return "Staff [id = " + id + ", title = " + title + "detail = " + detail + ",done = " + done + "]";
	}

}
