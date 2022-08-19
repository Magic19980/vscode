package com.example.demo.ftp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZipUploadUtils {

	private static final int BUFFER_SIZE = 2 * 1024;

	/*
	 * public static void main(String[] args) {
	 * String file =
	 * sendEJFile("20220806","20220820","C:\\Users\\72745\\Desktop\\Test\\EJ");
	 * System.out.println(file);
	 * }
	 */

	/**
	 * 电子日志上传
	 * @param startTime
	 * @param endTime
	 * @param localPath
	 */
	public static String sendEJFile(String startTime, String endTime, String localPath) {
		if (startTime == null || endTime == null || localPath == null) {
			log.error("传入的时间或路径为空!");
			return null;
		}

		// 遍历本地路径下的文件
		File file = new File(localPath);
		File[] fs = file.listFiles();
		if (fs == null || fs.length == 0) {
			return null;
		}
		long beginMillisecond = StrToDate(startTime).getTime();
		long endMillisecond = StrToDate(endTime).getTime();
		List<File> files = new ArrayList<File>();
		for (File f : fs) {
			if (!f.isDirectory()) {
				String fileDate = f.getName().substring(0, f.getName().indexOf('.'));
				long fileMillisecond = StrToDate(fileDate).getTime();
				if (beginMillisecond <= fileMillisecond && fileMillisecond <= endMillisecond) {
					files.add(f);
				}
			}
		}

		return toBase64Zip(files);
	}

	/**
	 * 压缩 转 base64 网络传输
	 * 
	 * @param srcFiles 需要压缩的文件
	 * @return
	 * @throws RuntimeException
	 */
	public static String toBase64Zip(List<File> srcFiles) throws RuntimeException {
		log.info("开始压缩文件    [{}]", srcFiles);
		long start = System.currentTimeMillis();
		String base64toZip = "";
		ZipOutputStream zos = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			zos = new ZipOutputStream(baos);
			for (File srcFile : srcFiles) {
				byte[] buf = new byte[BUFFER_SIZE];
				log.info("压缩的文件名称    [{}]   ", srcFile.getName() + "压缩的文件大小： " + srcFile.length());
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				int len;
				FileInputStream in = new FileInputStream(srcFile);
				while ((len = in.read(buf)) != -1) {
					zos.write(buf, 0, len);
				}
				zos.closeEntry();
				in.close();
			}
			long end = System.currentTimeMillis();
			log.info("压缩完成，耗时：    [{}] ms", (end - start));
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipToBase64", e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		byte[] refereeFileBase64Bytes = Base64.getEncoder().encode(baos.toByteArray());
		try {
			base64toZip = new String(refereeFileBase64Bytes, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("压缩流出现异常", e);
		}
		return base64toZip;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
