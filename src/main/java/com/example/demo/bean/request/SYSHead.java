package com.example.demo.bean.request;

public class SYSHead {
	    private String AppNum;

	    private String ChannelCode;

	    private String ConsumerId;

	    private String ConsumerSeqNo;

	    private String DeviceNum;

	    private String DvcNum;

	    private String RequestDate;

	    private String RequestTime;

	    private String ServerIP;

	    private String TranMode;

	    private String TransServiceCode;

	    public SYSHead() {}
	    
	    public SYSHead(String appNum, String channelCode, String consumerId, String consumerSeqNo, String deviceNum,
				String dvcNum, String requestDate, String requestTime, String serverIP, String tranMode,
				String transServiceCode) {
			super();
			AppNum = appNum;
			ChannelCode = channelCode;
			ConsumerId = consumerId;
			ConsumerSeqNo = consumerSeqNo;
			DeviceNum = deviceNum;
			DvcNum = dvcNum;
			RequestDate = requestDate;
			RequestTime = requestTime;
			ServerIP = serverIP;
			TranMode = tranMode;
			TransServiceCode = transServiceCode;
		}
		public void setAppNum(String AppNum){
	        this.AppNum = AppNum;
	    }
	    public String getAppNum(){
	        return this.AppNum;
	    }
	    public void setChannelCode(String ChannelCode){
	        this.ChannelCode = ChannelCode;
	    }
	    public String getChannelCode(){
	        return this.ChannelCode;
	    }
	    public void setConsumerId(String ConsumerId){
	        this.ConsumerId = ConsumerId;
	    }
	    public String getConsumerId(){
	        return this.ConsumerId;
	    }
	    public void setConsumerSeqNo(String ConsumerSeqNo){
	        this.ConsumerSeqNo = ConsumerSeqNo;
	    }
	    public String getConsumerSeqNo(){
	        return this.ConsumerSeqNo;
	    }
	    public void setDeviceNum(String DeviceNum){
	        this.DeviceNum = DeviceNum;
	    }
	    public String getDeviceNum(){
	        return this.DeviceNum;
	    }
	    public void setDvcNum(String DvcNum){
	        this.DvcNum = DvcNum;
	    }
	    public String getDvcNum(){
	        return this.DvcNum;
	    }
	    public void setRequestDate(String RequestDate){
	        this.RequestDate = RequestDate;
	    }
	    public String getRequestDate(){
	        return this.RequestDate;
	    }
	    public void setRequestTime(String RequestTime){
	        this.RequestTime = RequestTime;
	    }
	    public String getRequestTime(){
	        return this.RequestTime;
	    }
	    public void setServerIP(String ServerIP){
	        this.ServerIP = ServerIP;
	    }
	    public String getServerIP(){
	        return this.ServerIP;
	    }
	    public void setTranMode(String TranMode){
	        this.TranMode = TranMode;
	    }
	    public String getTranMode(){
	        return this.TranMode;
	    }
	    public void setTransServiceCode(String TransServiceCode){
	        this.TransServiceCode = TransServiceCode;
	    }
	    public String getTransServiceCode(){
	        return this.TransServiceCode;
	    }
		@Override
		public String toString() {
			return "SYSHead [AppNum=" + AppNum + ", ChannelCode=" + ChannelCode + ", ConsumerId=" + ConsumerId
					+ ", ConsumerSeqNo=" + ConsumerSeqNo + ", DeviceNum=" + DeviceNum + ", DvcNum=" + DvcNum
					+ ", RequestDate=" + RequestDate + ", RequestTime=" + RequestTime + ", ServerIP=" + ServerIP
					+ ", TranMode=" + TranMode + ", TransServiceCode=" + TransServiceCode + "]";
		}
	}
