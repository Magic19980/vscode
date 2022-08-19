package com.example.demo.bean.modulestatus;
import java.util.List;
public class ModuleStatus
{
    private String ModuleName;

    private Status Status;

    private List<UnitInfo> UnitInfo;
    
    public ModuleStatus() {}
    
    public ModuleStatus(String moduleName, com.example.demo.bean.modulestatus.Status status,
			List<com.example.demo.bean.modulestatus.UnitInfo> unitInfo) {
		super();
		ModuleName = moduleName;
		Status = status;
		UnitInfo = unitInfo;
	}

	public void setModuleName(String ModuleName){
        this.ModuleName = ModuleName;
    }
    public String getModuleName(){
        return this.ModuleName;
    }
    public void setStatus(Status Status){
        this.Status = Status;
    }
    public Status getStatus(){
        return this.Status;
    }
    public void setUnitInfo(List<UnitInfo> UnitInfo){
        this.UnitInfo = UnitInfo;
    }
    public List<UnitInfo> getUnitInfo(){
        return this.UnitInfo;
    }

	@Override
	public String toString() {
		return "ModuleStatus [ModuleName=" + ModuleName + ", Status=" + Status + ", UnitInfo=" + UnitInfo + "]";
	}
}
