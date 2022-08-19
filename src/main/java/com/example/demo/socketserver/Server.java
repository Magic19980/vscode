package com.example.demo.socketserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.demo.ftp.ZipUploadUtils;
import com.example.demo.utils.JSONResultBean;
import com.example.demo.utils.JSONUtil;
import com.example.demo.utils.PakageMessageUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Server {
	// 客户端
	Socket client;
	// 端口号
	private int port = 20202;
	// 处理接收消息线程池(只有一个客户端)
	ExecutorService executorService = Executors.newFixedThreadPool(20);

	//@PostConstruct
	public void initSocketConnect() throws IOException {
		ServerSocket server = new ServerSocket(port);
		log.info("Socket服务端启动-监听" + port + "端口.......");

		// 处理连接请求的线程
		new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						log.info("Socket服务端等待连接.......");
						// 监听端口，等待客户端的连接
						client = server.accept();
						log.info("Socket服务端收到客户端请求,客户端为: " + client);

						// 启动接收消息线程
						executorService.execute(new Read(client));
					}
				} catch (Exception e) {
					log.info("服务端处理连接事件异常:" + e.getMessage());
				}
			}
		}.start();

	}

	/**
	 *
	 * 服务端发送消息线程
	 */
	public void sendMsg(String msg) {
		// 调用客户端的发送消息线程
		new Thread() {
			@Override
			public void run() {

				try {
					log.info("发送消息线程启动....");

					// 获取客户端的输出流,向客户端输出内容
					PrintStream ps = new PrintStream(client.getOutputStream());
					ps.println(msg);

					log.info("发送消息线程结束....");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 *
	 * 服务端读取消息线程
	 */
	public class Read implements Runnable {

		private Socket socket;

		public Read(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				log.info("读消息线程启动了.......");

				String receiveMsg = null;

				Scanner scanner = new Scanner(socket.getInputStream());

				Map rs;

				while (scanner.hasNext()) {
					rs = null;
					log.info("获取客户端报文信息.......");
					// receiveMsg = scanner.next();
					receiveMsg = scanner.nextLine();
					log.info("得到的客户端报文信息: " + receiveMsg);

					// 解析报文
					rs = analyse(receiveMsg);

					if (null == rs) {// 解析不出
						JSONResultBean jsonResultBean = new JSONResultBean();
						jsonResultBean.setCode(-1);
						jsonResultBean.setErrMsg("请求报文格式错误!");
						// 调用发送消息线程发送响应报文
						String rsMsg = JSONUtil.toJsonString(jsonResultBean) + "\n\n";
						sendMsg(rsMsg);
					} else {
						// 根据解析得到的信息进行相应处理
						handle(rs);
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 解析报文
	 */
	private Map analyse(String receiveMsg) {

		// 解析报文,进行相应处理(返回数据或者调接口拿数据)
		try {
			return JSON.parseObject(receiveMsg, Map.class);
		} catch (Exception e) {
			// handle exception
			log.error("传来的报文信息格式不正确!");
			return null;
			// continue;
		}
	}

	/**
	 * 处理报文数据
	 */
	private void handle(Map map) {
		String rspMsg = null;
		switch ((String) map.get("Method")) {
			case "ModuleStatus":
				// 获取设备信息
				log.info("获取设备信息.....");
				// 组装响应报文
				rspMsg = PakageMessageUtils.getSocketResponse(0, PakageMessageUtils.getModuleStatus(), null);
				break;
			case "ManualUploadJournal":
				// 提取电子日志
				log.info("提取电子日志....");
				// 提取电子日志
				log.info("提取电子日志....");
				// 调文件上传方法
				String zipString = ZipUploadUtils.sendEJFile((String) map.get("StartDate"), (String) map.get("EndDate"),"/home/uos/CFESAgent/EJ/data");
				if (zipString != null) {
					rspMsg = PakageMessageUtils.getSocketResponse(0,zipString,null);
				}
				else {
					rspMsg = PakageMessageUtils.getSocketResponse(-1,null,"上传失败！");
				}
				break;
			default:
				rspMsg = PakageMessageUtils.getSocketResponse(-1, null, "请求方法不存在！");
				break;
		}
		// 调用发送消息线程发送响应报文
		log.info("响应报文: " + rspMsg);
		sendMsg(rspMsg);
	}
}
