package com.example.demo.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 纸币冠字号码信息文件上传工具类
 * 
 * @author
 *
 */
@Slf4j
public class FTPUtils {

	/** 本地字符编码 */
	private static String LOCAL_CHARSET = "GBK";

	// FTP协议里面，规定文件名编码为iso-8859-1
	private static String SERVER_CHARSET = "ISO-8859-1";

	// 标志执行FSN上传业务
	private static String STORE_FSN_FILE = "STORE_FSN_FILE";

	// 设置私有不能实例化
	public FTPUtils() {
	}

	// @PostConstruct
	public static void test() {
		List<String> list = new ArrayList<>();
		list.add("CNY15_20220811140222_00001200SR_87000722_CAQK_6231891100191001168.FSN");
		uploadFsnFile("32.114.72.164", null, "ftpuser", "ftpuser", "/data/fsnfiles",
				"/home/uos/CFESAP/FsnCollection/20220811", list);
	}

	/**
	 * 冠字号文件上传业务(五次上传失败，不再发送，端机自己保留)
	 * 
	 * @param hostname
	 * @param port
	 * @param username
	 * @param password
	 * @param workingPath
	 * @param loaclPath
	 * @param saveName
	 * @return
	 */
	public static boolean uploadFsnFile(String hostname, Integer port, String username, String password,
			String workingPath, String loaclPath, List<String> saveName) {
		boolean flag = true;
		List<String> list = uploadFile(hostname, port, username, password, workingPath, loaclPath, saveName,
				STORE_FSN_FILE);
		if (list.size() > 0) {
			log.error("---FSN文件上传失败！开始重新发送----");
			flag = false;
			List<String> paramList = new ArrayList<>();
			paramList.add(hostname);
			paramList.add(username);
			paramList.add(password);
			paramList.add(workingPath);
			paramList.add(loaclPath);
			// 启动线程调用上传方法，五次失败不再调用
			ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
			Runnable task = new Runnable() {
				public void run() {
					// 仅装载上传失败与未上传的数据
					List<String> unUploadList = new ArrayList<>(list);
					int j = 1;
					while ((unUploadList = uploadFile(paramList.get(0), port, paramList.get(1), paramList.get(2),
							paramList.get(3),
							paramList.get(4), unUploadList, STORE_FSN_FILE)).size() > 0) {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						j++;
						if (j > 4) {
							log.error("五次上传失败，不再发送，端机自己保留!");
							break;
						}
					}
				}
			};
			// 向线程池提交任务
			singleThreadExecutor.submit(task);
			// 执行此函数后线程池不再接收新任务，并等待所有任务执行完毕后销毁线程。此函数并不会等待线程销毁完毕，而是立即返回的
			singleThreadExecutor.shutdown();
		}
		return flag;
	}

	/**
	 * 普通上传业务
	 * 
	 * @param hostname
	 * @param port
	 * @param username
	 * @param password
	 * @param workingPath
	 * @param loaclPath
	 * @param saveName
	 * @return
	 */
	public static boolean uploadOtherFile(String hostname, Integer port, String username, String password,
			String workingPath, String loaclPath, List<String> saveName) {
		boolean flag = true;
		List<String> list = uploadFile(hostname, port, username, password, workingPath, loaclPath, saveName,
				STORE_FSN_FILE);
		if (list.size() != 0) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 连接上传
	 * 
	 * @param hostname
	 *                    IP或域名地址
	 * @param port
	 *                    端口号
	 * @param username
	 *                    FTP服务器登录用户名
	 * @param password
	 *                    FTP服务器登录密码
	 * @param workingPath
	 *                    FTP服务器保存目录
	 * @param loaclPath
	 *                    本地路径
	 * @param saveName
	 *                    此次操作需保存的文件
	 * @param operation
	 *                    上传操作
	 * @return
	 */
	public static List<String> uploadFile(String hostname, Integer port, String username, String password,
			String workingPath, String loaclPath, List<String> saveName, String operation) {
		FTPClient ftpClient = new FTPClient();

		// 记录未上传的数据
		List<String> list = new ArrayList<>();
		// 传入数据校验
		if (workingPath == null || workingPath.trim() == "") {
			log.info("工作路径为空！");
			return list;
		}
		if (hostname == null || hostname.trim() == "") {
			log.info("服务器IP为空！");
			return list;
		}
		if (username == null || username.trim() == "") {
			log.info("FTP用户为空！");
			return list;
		}
		if (password == null || password.trim() == "") {
			log.info("FTP密码为空！");
			return list;
		}
		if (loaclPath == null || loaclPath.trim() == "") {
			log.info("本地路径为空！");
			return list;
		}
		if (saveName == null || saveName.size() == 0) {
			log.info("传输文件为空！");
			return list;
		}

		// 测试连接
		if (connect(ftpClient, hostname, port, username, password)) {

			switch (operation) {
				case "STORE_FSN_FILE":
					// 冠字号文件上传
					list = storeFsnFile(ftpClient, saveName, loaclPath, workingPath);
					break;
				default:
					// 文件上传
					list = storeOtherFile(ftpClient, saveName, loaclPath, workingPath);
					break;
			}
			if (list.size() == 0) {
				disconnect(ftpClient);
			}
		}
		return list;
	}

	/**
	 * 断开连接
	 *
	 * @param ftpClient
	 * @throws Exception
	 */
	public static void disconnect(FTPClient ftpClient) {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
				log.info("已关闭连接");
			} catch (IOException e) {
				log.error("没有关闭连接");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试是否能连接
	 *
	 * @param ftpClient
	 * @param hostname
	 *                  ip或域名地址
	 * @param port
	 *                  端口
	 * @param username
	 *                  用户名
	 * @param password
	 *                  密码
	 * @return 返回真则能连接
	 */
	public static boolean connect(FTPClient ftpClient, String hostname, Integer port, String username,
			String password) {
		boolean flag = false;

		try {
			// 读取文件配置判断是否使用主动
			ftpClient.enterLocalPassiveMode();// 被动
			// ftpClient.enterLocalActiveMode();//主动

			// ftp连接
			if (port == null) {
				ftpClient.connect(hostname);
			} else {
				ftpClient.connect(hostname, port);
			}

			// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
			if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
				LOCAL_CHARSET = "UTF-8";
			}
			ftpClient.setControlEncoding(LOCAL_CHARSET);

			if (ftpClient.login(username, password)) {
				log.info("连接ftp成功");
				// 设置FTP以2进制传输设置类型要放在登录后，否则不生效
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

				// 设置以字节流传输模式
				ftpClient.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
				flag = true;
			} else {
				log.error("连接ftp失败，可能用户名或密码错误");
				try {
					disconnect(ftpClient);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			log.error("连接失败，可能ip或端口错误");
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 上传.FSN文件
	 *
	 * @param ftpClient
	 * @param saveName
	 * @param fileInputStream
	 * @return
	 */
	public static List<String> storeFsnFile(FTPClient ftpClient, List<String> saveName, String localPath,
			String workingPath) {
		log.info("进入storeFsnFile方法体-------");
		List<String> copyList = new ArrayList<>(saveName);
		try {

			for (String fileName : saveName) {

				String firstLevel = null;
				String secondLevel = null;
				String savePath1 = null;
				try {
					// 一级目录以上传日期（yyyymmdd）为文件名
					DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
					Date date = new Date();
					firstLevel = dateFormat.format(date);

					// 二级目录以机型编号为文件名
					String[] arr = fileName.split("_");
					secondLevel = arr[3];
				} catch (Exception es) {
					es.printStackTrace();
					log.error("FSN文件名格式不正确！");
					ftpClient.disconnect();
					return copyList;
				}

				// 构建存放目录
				if (workingPath.endsWith(File.separator)) {
					savePath1 = workingPath + firstLevel;
				} else {
					savePath1 = workingPath + File.separator + firstLevel;
				}

				// 检查存放目录是否存在，不存在则创建（ 因为ftp只支持一级级创建，固拆分后一级级创建）
				if (!ftpClient.changeWorkingDirectory(savePath1)) {
					if (!ftpClient.makeDirectory(savePath1)) {
						log.error(savePath1 + "目录创建失败！");
						return copyList;
					}

					String savePath = savePath1 + File.separator + secondLevel;

					if (!ftpClient.makeDirectory(savePath)) {
						log.error(savePath + "目录创建失败！");
						return copyList;
					}

					// 创建目录成功，转到创建的目录
					ftpClient.changeWorkingDirectory(savePath);
				}

				if (!localPath.endsWith(File.separator)) {
					localPath = localPath + File.separator;
				}

				File file2 = new File(localPath);
				if (file2 == null || !file2.exists()) {
					log.error(localPath + " 不存在！");
				}

				// 将文件转为.temp结尾的临时文件
				File file1 = new File(localPath + fileName);
				if (file1 == null || !file1.exists()) {
					log.error(fileName + "本地文件不存在！");
					continue;
				} else {
					if (!file1.renameTo(new File(localPath + fileName + ".temp"))) {
						log.error(localPath + fileName + "重命名失败！");
					}
				}

				// 上传文件
				File file = new File(localPath + fileName + ".temp");
				FileInputStream fileInputStream = new FileInputStream(file);
				ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
				// 将缓存区变大，提升上传效果
				ftpClient.setBufferSize(1024 * 1024 * 10);
				// 上传文件时，文件名称需要做编码转换(避免中文乱码)
				fileName = new String(fileName.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
				if (!ftpClient.storeFile(fileName, fileInputStream)) {
					log.error(fileName + "---------上传失败!----------");
					fileInputStream.close();
					return copyList;
				} else {
					copyList.remove(fileName);

					// 上传完必须关闭资源，否则资源占用影响后续改名操作
					if (fileInputStream != null) {
						fileInputStream.close();
					}
					log.info("上传成功");

					// 上传成功，去掉.temp后缀
					File files = new File(localPath + fileName + ".temp");
					if (files.exists()) {
						if (!files.renameTo(new File(localPath + fileName))) {
							log.error(fileName + ".temp" + "本地临时文件更改为3.3的文件名失败！");
						}
					} else {
						log.error(fileName + "本地临时文件不存在！");
					}
				}
			}
		} catch (IOException e) {
			log.error("上传失败");
			e.printStackTrace();
		} finally {
			// 关闭ftp资源
			disconnect(ftpClient);
		}
		return copyList;
	}

	/**
	 * 上传文件(普通文件上传)
	 *
	 * @param ftpClient
	 * @param saveName
	 * @param fileInputStream
	 * @return
	 */
	public static List<String> storeOtherFile(FTPClient ftpClient, List<String> saveName, String localPath,
			String workingPath) {
		FileInputStream fileInputStream = null;
		log.info("------进入storeOtherFile方法体-------");
		List<String> copyList = new ArrayList<>(saveName);
		try {

			for (String fileName : saveName) {
				// 检查工作目录是否存在
				if (!ftpClient.changeWorkingDirectory(workingPath)) {
					if (!ftpClient.makeDirectory(workingPath)) {
						log.error(workingPath + "目录创建失败！");
						return copyList;
					}
				}

				if (!localPath.endsWith(File.separator)) {
					localPath = localPath + File.separator;
				}

				File file = new File(localPath + fileName);
				if (file == null || !file.exists()) {
					log.error(fileName + "本地文件不存在！");
					continue;
				}
				fileInputStream = new FileInputStream(file);

				// 上传文件
				ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
				// 将缓存区变大，提升上传效果
				ftpClient.setBufferSize(1024 * 1024 * 10);
				if (!ftpClient.storeFile(fileName, fileInputStream)) {
					log.error(fileName + "---------上传失败!");
					fileInputStream.close();
					return copyList;
				} else {
					copyList.remove(fileName);
				}
			}

			if (fileInputStream != null) {
				fileInputStream.close();
			}

		} catch (IOException e) {
			log.error("上传失败");
			e.printStackTrace();
		} finally {
			// 关闭ftp资源
			disconnect(ftpClient);
		}
		return copyList;
	}
}
