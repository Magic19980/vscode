package com.example.demo.bean.request;

public class HeartBeat {
	private int Interval = 180000;//心跳间隔时间
	private int AppStatus;//APP状态 0：正常  -1：异常
	
	public int getInterval() {
		return Interval;
	}
	public void setInterval(int interval) {
		Interval = interval;
	}
	public int getAppStatus() {
		return AppStatus;
	}
	public void setAppStatus(int appStatus) {
		AppStatus = appStatus;
	}
	@Override
	public String toString() {
		return "HeartBeat [Interval=" + Interval + ", AppStatus=" + AppStatus + "]";
	}
}
