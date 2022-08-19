package com.example.demo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.example.demo.bean.modulestatus.ModuleStatus;
import com.example.demo.bean.modulestatus.Status;
import com.example.demo.bean.modulestatus.UnitInfo;
import com.example.demo.bean.request.APPHead;
import com.example.demo.bean.request.HeartBeat;
import com.example.demo.bean.request.RequestBean;
import com.example.demo.bean.request.SYSHead;

public class PakageMessageUtils {
	
	
	/**
	 * 主动上送设备状态报文
	 * @param moduleStatusList
	 * @return
	 */
	public static String getModuleStatusRequest() {

		List <ModuleStatus> moduleStatusList = getModuleStatus();
		
		APPHead appHead = new APPHead();
		appHead.setDvcNum("6B2D34C04331EED4677915A26A146698");
		appHead.setI18nInd("CH");
		appHead.setIttrStmInd("SUTE");
		SYSHead sysHead = new SYSHead("ATM","07","07",
									"20220809191256","87000722","6B2D34C04331EED4677915A26A146698",
									"20220809","114718","http://32.114.122.78:9533/invokeTrade",
									"1","AT.AGENT.COLLECTION.018");
		Map<String,Object> map = new HashMap<>();
		map.put("Result", moduleStatusList);
		map.put("AppStatus", -1);
		//组装request
		return getRequest(appHead,map,sysHead);
	
	}
	/**
	 * 心跳请求报文
	 */
	public static String getHeartBeatRequest(HeartBeat heartBeat){
		APPHead appHead = new APPHead();
		appHead.setDvcNum("6B2D34C04331EED4677915A26A146698");
		appHead.setI18nInd("CH");
		appHead.setIttrStmInd("SUTE");
		SYSHead sysHead = new SYSHead("ATM","07","07",
									"20220808193944","87000722","6B2D34C04331EED4677915A26A146698",
									"20220809","114718","http://32.114.122.78:9533/invokeTrade",
									"1","AT.AGENT.COLLECTION.007");
//		sysHead.setAppNum("ATM");
//		sysHead.setChannelCode("07");
//		sysHead.setConsumerId(ConsumerId);
//		sysHead.setConsumerId(ConsumerId);
		return getRequest(appHead,heartBeat,sysHead);
	}
	
	/**
	 * Socket响应报文
	 */
	public static String getSocketResponse(int code,Object rspData,String errMsg){
		JSONResultBean jsonResultBean = new JSONResultBean();
		jsonResultBean.setCode(code);
		jsonResultBean.setResMsg(rspData);
		jsonResultBean.setErrMsg(errMsg);
		return JSONUtil.toJsonString(jsonResultBean)+"\n\n";
	}
	
	/**
	 * 组装ModuleStatus
	 * 1.socket返回设备状态(直接返回List)
	 * 2.http主动上送(需要组装成Request并转json字符串返回)
	 * @return
	 */
	public static List <ModuleStatus> getModuleStatus(){
		List <ModuleStatus> moduleStatusList = new ArrayList<>();
		
		//====================
		Status status1 = new Status();
//		UnitInfo unitInfo = new UnitInfo();
		status1.setDevSt(6);
		status1.setIdcBinSt(1);
		status1.setIdcCardNum(4);
		status1.setIdcChipPowerSt(5);
		status1.setMediaSt(2);
		//===========================
		Status status2 = new Status();
//		UnitInfo unitInfo = new UnitInfo();
		status2.setDevSt(6);
		status2.setPinEncSt(0);
		//===========================
		Status status3 = new Status();
		status3.setDevSt(6);
		status3.setDispenserSt(0);
		status3.setShutterSt(0);
		status3.setTransportSt(0);
		status3.setMediaSt(2);
		List<UnitInfo> unitInfoList3 = new ArrayList<>();
		UnitInfo unitInfo = new UnitInfo();
		unitInfo.setCardName("CardA");
		unitInfo.setCount(18);
		unitInfo.setInitialCount(0);
		unitInfo.setNumber(1);
		unitInfo.setRetainCount(0);
		unitInfo.setStatus(2);
		unitInfo.setType(1);
		
		UnitInfo unitInfo1 = new UnitInfo();
		unitInfo1.setCardName("CardB");
		unitInfo1.setCount(0);
		unitInfo1.setInitialCount(0);
		unitInfo1.setNumber(2);
		unitInfo1.setRetainCount(0);
		unitInfo1.setStatus(1);
		unitInfo1.setType(1);
		
		UnitInfo unitInfo2 = new UnitInfo();
		unitInfo2.setCardName("CardC");
		unitInfo2.setCount(17);
		unitInfo2.setInitialCount(0);
		unitInfo2.setNumber(3);
		unitInfo2.setRetainCount(0);
		unitInfo2.setStatus(0);
		unitInfo2.setType(2);
		
		unitInfoList3.add(unitInfo);
		unitInfoList3.add(unitInfo1);
		unitInfoList3.add(unitInfo2);
		//===========================
		Status status4 = new Status();
		status4.setDevSt(0);
		status4.setDispenserSt(0);
		status4.setSafeDoorSt(0);
		
		List<UnitInfo> unitInfoList4 = new ArrayList<>();
		UnitInfo unitInfo3 = new UnitInfo();
		unitInfo3.setCount(0);
		unitInfo3.setCurrencyID("CNY");
		unitInfo3.setInitialCount(0);
		unitInfo3.setNumber(1);
		unitInfo3.setRejectCount(0);
		unitInfo3.setStatus(4);
		unitInfo3.setType(12);
		unitInfo3.setUnitID("RB1");
		unitInfo3.setValues(100);
		
		UnitInfo unitInfo4 = new UnitInfo();
		unitInfo4.setCount(0);
		unitInfo4.setCurrencyID("CNY");
		unitInfo4.setInitialCount(0);
		unitInfo4.setNumber(2);
		unitInfo4.setRejectCount(24);
		unitInfo4.setStatus(4);
		unitInfo4.setType(12);
		unitInfo4.setUnitID("RB2");
		unitInfo4.setValues(100);
		
		UnitInfo unitInfo5 = new UnitInfo();
		unitInfo5.setCount(2000);
		unitInfo5.setCurrencyID("CNY");
		unitInfo5.setInitialCount(0);
		unitInfo5.setNumber(3);
		unitInfo5.setRejectCount(118);
		unitInfo5.setStatus(0);
		unitInfo5.setType(12);
		unitInfo5.setUnitID("RB3");
		unitInfo5.setValues(100);
		
		UnitInfo unitInfo6 = new UnitInfo();
		unitInfo6.setCount(0);
		unitInfo6.setCurrencyID("CNY");
		unitInfo6.setInitialCount(0);
		unitInfo6.setNumber(4);
		unitInfo6.setRejectCount(0);
		unitInfo6.setStatus(0);
		unitInfo6.setType(6);
		unitInfo6.setUnitID("AB1");
		unitInfo6.setValues(0);
		
		UnitInfo unitInfo7 = new UnitInfo();
		unitInfo7.setCount(0);
		unitInfo7.setCurrencyID("CNY");
		unitInfo7.setInitialCount(0);
		unitInfo7.setNumber(5);
		unitInfo7.setRejectCount(0);
		unitInfo7.setStatus(3);
		unitInfo7.setType(12);
		unitInfo7.setUnitID("RB4");
		unitInfo7.setValues(10);
		
		unitInfoList4.add(unitInfo3);
		unitInfoList4.add(unitInfo4);
		unitInfoList4.add(unitInfo5);
		unitInfoList4.add(unitInfo6);
		unitInfoList4.add(unitInfo7);
		//===========================
		Status status5 = new Status();
		status5.setDevSt(0);
		status5.setBanknoteReaderSt(0);
		status5.setSafeDoorSt(0);
		status5.setStackerItemsSt(0);
		status5.setIntermediateStackerSt(0);
		status5.setAcceptorSt(0);
		
		List<UnitInfo> unitInfoList5 = new ArrayList<>();
		UnitInfo unitInfoA = new UnitInfo();
		unitInfoA.setCount(8);
		unitInfoA.setCurrencyID("CNY");
		unitInfoA.setInitialCount(0);
		unitInfoA.setNumber(1);
		unitInfoA.setRejectCount(0);
		unitInfoA.setStatus(0);
		unitInfoA.setType(1);
		unitInfoA.setUnitID("CAS_A");
		unitInfoA.setValues(100);
		
		UnitInfo unitInfoB = new UnitInfo();
		unitInfoB.setCount(585);
		unitInfoB.setCurrencyID("CNY");
		unitInfoB.setInitialCount(0);
		unitInfoB.setNumber(2);
		unitInfoB.setRejectCount(0);
		unitInfoB.setStatus(0);
		unitInfoB.setType(1);
		unitInfoB.setUnitID("CAS_B");
		unitInfoB.setValues(100);
		
		UnitInfo unitInfoC = new UnitInfo();
		unitInfoC.setCount(0);
		unitInfoC.setCurrencyID("CNY");
		unitInfoC.setInitialCount(0);
		unitInfoC.setNumber(3);
		unitInfoC.setRejectCount(0);
		unitInfoC.setStatus(0);
		unitInfoC.setType(2);
		unitInfoC.setUnitID("CAS_C");
		unitInfoC.setValues(100);
		
		UnitInfo unitInfoD = new UnitInfo();
		unitInfoD.setCount(1);
		unitInfoD.setCurrencyID("CNY");
		unitInfoD.setInitialCount(0);
		unitInfoD.setNumber(4);
		unitInfoD.setRejectCount(0);
		unitInfoD.setStatus(0);
		unitInfoD.setType(2);
		unitInfoD.setUnitID("CAS_E");
		unitInfoD.setValues(0);
		
		UnitInfo unitInfoE = new UnitInfo();
		unitInfoE.setCount(10);
		unitInfoE.setCurrencyID(" ");
		unitInfoE.setInitialCount(0);
		unitInfoE.setNumber(5);
		unitInfoE.setRejectCount(0);
		unitInfoE.setStatus(0);
		unitInfoE.setType(4);
		unitInfoE.setUnitID("CAS_R");
		unitInfoE.setValues(0);
		
		unitInfoList5.add(unitInfoA);
		unitInfoList5.add(unitInfoB);
		unitInfoList5.add(unitInfoC);
		unitInfoList5.add(unitInfoD);
		unitInfoList5.add(unitInfoE);
		//===========================
		moduleStatusList.add(new ModuleStatus("CardReader",status1,null));
		moduleStatusList.add(new ModuleStatus("PINKeypad",status2,null));
		moduleStatusList.add(new ModuleStatus("CardDispenser",status3,unitInfoList3));
		moduleStatusList.add(new ModuleStatus("CashDispenser",status4,unitInfoList4));
		moduleStatusList.add(new ModuleStatus("CashAcceptor",status5,unitInfoList5));
		return moduleStatusList;
	}

	/**
	 * 组装request
	 * @return
	 */
	private static String getRequest(APPHead appHead,Object reqBody,SYSHead sysHead){
		RequestBean requestBean = new RequestBean(appHead,reqBody,sysHead);
//		System.out.println("组装的requestBean为: "+requestBean);
		
		System.out.println("组装的requestBeanJSON为: "+JSONUtil.toJsonString(requestBean));
		
		return JSONUtil.toJsonString(requestBean);
	}
}
