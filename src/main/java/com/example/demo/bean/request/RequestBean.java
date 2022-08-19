package com.example.demo.bean.request;

public class RequestBean {
    private APPHead APP_HEAD;

    private Object REQ_BODY;
//
    private SYSHead SYS_HEAD;
    
    public RequestBean() {}
    
    public RequestBean(APPHead APP_HEAD, Object REQ_BODY, SYSHead SYS_HEAD) {
		super();
		this.APP_HEAD = APP_HEAD;
		this.REQ_BODY = REQ_BODY;
		this.SYS_HEAD = SYS_HEAD;
	}
	@Override
	public String toString() {
		return "RequestBean [APP_HEAD=" + APP_HEAD + ", REQ_BODY=" + REQ_BODY + ", SYS_HEAD=" + SYS_HEAD + "]";
	}
	public void setAPP_HEAD(APPHead APP_HEAD){
        this.APP_HEAD = APP_HEAD;
    }
    public APPHead getAPP_HEAD(){
        return this.APP_HEAD;
    }
    public void setREQ_BODY(Object REQ_BODY){
        this.REQ_BODY = REQ_BODY;
    }
    public Object getREQ_BODY(){
        return this.REQ_BODY;
    }
    public void setSYS_HEAD(SYSHead SYS_HEAD){
        this.SYS_HEAD = SYS_HEAD;
    }
    public SYSHead getSYS_HEAD(){
        return this.SYS_HEAD;
    }
}