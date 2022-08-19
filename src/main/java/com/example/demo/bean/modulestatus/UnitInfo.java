package com.example.demo.bean.modulestatus;
public class UnitInfo
{
    private int Count;

    private String CurrencyID;

    private int InitialCount;

    private int Number;

    private int RejectCount;

    private int Status;

    private int Type;

    private String UnitID;

    private int Values;
    
    private String CardName;

    private int RetainCount;
    
    public UnitInfo() {}
    
    public UnitInfo(int count, String currencyID, int initialCount, int number, int rejectCount, int status, int type,
			String unitID, int values, String cardName, int retainCount) {
		super();
		Count = count;
		CurrencyID = currencyID;
		InitialCount = initialCount;
		Number = number;
		RejectCount = rejectCount;
		Status = status;
		Type = type;
		UnitID = unitID;
		Values = values;
		CardName = cardName;
		RetainCount = retainCount;
	}

	@Override
	public String toString() {
		return "UnitInfo [Count=" + Count + ", CurrencyID=" + CurrencyID + ", InitialCount=" + InitialCount
				+ ", Number=" + Number + ", RejectCount=" + RejectCount + ", Status=" + Status + ", Type=" + Type
				+ ", UnitID=" + UnitID + ", Values=" + Values + ", CardName=" + CardName + ", RetainCount="
				+ RetainCount + "]";
	}

	public void setCardName(String CardName){
        this.CardName = CardName;
    }
    
    public String getCardName(){
        return this.CardName;
    }
    
    public void setRetainCount(int RetainCount){
        this.RetainCount = RetainCount;
    }
    
    public int getRetainCount(){
        return this.RetainCount;
    }

    public void setCount(int Count){
        this.Count = Count;
    }
    public int getCount(){
        return this.Count;
    }
    public void setCurrencyID(String CurrencyID){
        this.CurrencyID = CurrencyID;
    }
    public String getCurrencyID(){
        return this.CurrencyID;
    }
    public void setInitialCount(int InitialCount){
        this.InitialCount = InitialCount;
    }
    public int getInitialCount(){
        return this.InitialCount;
    }
    public void setNumber(int Number){
        this.Number = Number;
    }
    public int getNumber(){
        return this.Number;
    }
    public void setRejectCount(int RejectCount){
        this.RejectCount = RejectCount;
    }
    public int getRejectCount(){
        return this.RejectCount;
    }
    public void setStatus(int Status){
        this.Status = Status;
    }
    public int getStatus(){
        return this.Status;
    }
    public void setType(int Type){
        this.Type = Type;
    }
    public int getType(){
        return this.Type;
    }
    public void setUnitID(String UnitID){
        this.UnitID = UnitID;
    }
    public String getUnitID(){
        return this.UnitID;
    }
    public void setValues(int Values){
        this.Values = Values;
    }
    public int getValues(){
        return this.Values;
    }
}
