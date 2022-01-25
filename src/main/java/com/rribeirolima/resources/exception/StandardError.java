package com.rribeirolima.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer statusErro;
	private String mgsErro;
	private Long timeStamp;
	
	public StandardError(Integer statusErro, String mgsErro, Long timeStamp) {
		super();
		this.statusErro = statusErro;
		this.mgsErro = mgsErro;
		this.timeStamp = timeStamp;
	}

	public Integer getStatusErro() {
		return statusErro;
	}

	public void setStatusErro(Integer statusErro) {
		this.statusErro = statusErro;
	}

	public String getMgsErro() {
		return mgsErro;
	}

	public void setMgsErro(String mgsErro) {
		this.mgsErro = mgsErro;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
