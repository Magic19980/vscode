package com.example.demo.bean.request;

public class APPHead {
    private String DvcNum;

    private String I18nInd;

    private String IttrStmInd;
    public APPHead() {}
    public APPHead(String dvcNum, String i18nInd, String ittrStmInd) {
		super();
		DvcNum = dvcNum;
		I18nInd = i18nInd;
		IttrStmInd = ittrStmInd;
	}
	public void setDvcNum(String DvcNum){
        this.DvcNum = DvcNum;
    }
    public String getDvcNum(){
        return this.DvcNum;
    }
	public void setI18nInd(String I18nInd){
        this.I18nInd = I18nInd;
    }
    public String getI18nInd(){
        return this.I18nInd;
    }
    public void setIttrStmInd(String IttrStmInd){
        this.IttrStmInd = IttrStmInd;
    }
    public String getIttrStmInd(){
        return this.IttrStmInd;
    }
	@Override
	public String toString() {
		return "APPHead [DvcNum=" + DvcNum + ", I18nInd=" + I18nInd + ", IttrStmInd=" + IttrStmInd + "]";
	}
}
