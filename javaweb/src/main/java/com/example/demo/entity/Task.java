package com.example.demo.entity;

public class Task {
	private Long id;
	private String title;
	private String detail;
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

	@Override // 親クラスのtuString()の動作を上書きする
	public String toString(){
		return "Staff [id = " + id + ", title = " + title + "detail = " + detail + ",done = " + done + "]";
	}

}
