package com.example.demo.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.example.demo.bean.request.HeartBeat;
import com.example.demo.ftp.FTPUtils;
import com.example.demo.utils.PakageMessageUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 发送 http 请求(心跳,上送设备状态)
 */
@Component
@Slf4j
public class HttpRequestUtils {
//	private static final String IP = "127.0.0.1";//服务器IP
//	private static final int PORT = 8888;//服务器端口
	private static final String IP = "32.114.122.78";//服务器IP
	
	private static final int PORT = 9533;//服务器端口
	
	private static int time = 180;//心跳间隔时间 s
	
	private static HeartBeat heartBeatBean = new HeartBeat();//默认为状态正常0,当收不到心跳响应时状态设置为-1
	
	/**
	 * 心跳  冠字号 上送测试
	 */
	//@PostConstruct
	public void test() {
		//测试冠字号上传
		FTPUtils.test();
		
		//测试主动上送设备状态信息
		String txt = PakageMessageUtils.getModuleStatusRequest();
//		uploadStatusMessage(IP,PORT,body);
		uploadStatusMessage(IP,PORT,txt);
		
		try{
			//上送心跳报文
			heartBeat();
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("连接服务器失败");
		}

		
	}
	
	/**
	 * 	http主动上送设备状态信息
	 * @param ip
	 * @param port
	 * @param context
	 */
	public static void uploadStatusMessage(String ip,int port,String context) {
		log.info("主动上送设备状态报文为: " + context);
		log.info("http主动上送设备状态信息....");
		//TODO 修改过
		httpRequest(ip,port,context);
	};
	
	/**
	 * 发送心跳电文
	 */
	public void heartBeat() {
		
		//读取配置文件设置time
		//TODO
		heartBeatBean.setInterval(time);
		
		//心跳报文
		String context = PakageMessageUtils.getHeartBeatRequest(heartBeatBean);
		
		//定时发送心跳报文
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		    @Override
		    public void run() {
		        // 业务代码
		    	log.info("心跳报文为: " + context);
		    	log.info("发送心跳请求...");
		    	httpRequest(IP, PORT, context);
		    }
		},0, time*1000);  // 0s后调度周期为180s的定时心跳任务
		

	};

	/**
	 * 
	 * @param ip
	 * @param port
	 * @param context
	 */
	public static void httpRequest(String ip,int port,String context){
	    HttpURLConnection con = null;
	    
	    BufferedReader buffer = null;
	    StringBuffer resultBuffer = null;

	    try {
	        URL url = new URL("http://"+ip+":"+port);
	        //得到连接对象
	        con = (HttpURLConnection) url.openConnection();
//	        //设置请求类型
	        con.setRequestMethod("POST");
	        //设置Content-Type，此处根据实际情况确定
	        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        //允许写出
	        con.setDoOutput(true);
	        //允许读入
	        con.setDoInput(true);
	        //不使用缓存
	        con.setUseCaches(false);
	        OutputStream os = con.getOutputStream();

	        os.write((context.getBytes()));
	        //得到响应码
	        int responseCode = con.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            //收到服务端响应heartBeatBean状态设为正常,否则不正常
	            heartBeatBean.setAppStatus(0);
	            //得到响应流
	            InputStream inputStream = con.getInputStream();
	            //将响应流转换成字符串
	            resultBuffer = new StringBuffer();
	            String line;
//	            buffer = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
	            buffer = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	            while ((line = buffer.readLine()) != null) {
	                resultBuffer.append(line);
	            }
	            log.info("http响应结果:" + resultBuffer.toString());

	        }else {
	        	heartBeatBean.setAppStatus(-1);
	        	log.info("http连接出错");
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
