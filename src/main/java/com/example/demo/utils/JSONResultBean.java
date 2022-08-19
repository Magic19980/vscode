package com.example.demo.utils;

public class JSONResultBean {
	private int Code = 0;
	private Object ResMsg = "";
	private String ErrMsg = "";
	
	public int getCode() {
		return Code;
	}
	public void setCode(int code) {
		Code = code;
	}
	public Object getResMsg() {
		return ResMsg;
	}
	public void setResMsg(Object resMsg) {
		ResMsg = resMsg;
	}
	public String getErrMsg() {
		return ErrMsg;
	}
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}
	
}
