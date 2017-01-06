package com.dafen.xuejie.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * 对流的常用文本操作工具类
 * @author _Ms
 */
public class StreamUtils {
	
	/**
	 * 根据指定文件读取指定数量行的字符串
	 * 
	 * @param srcFile 源文件
	 * @param readLineCount 读取行的数量
	 * @return 读取行结果存储的数组，数组中元素按照读取顺序存储,例如:第一行的索引为0, 第二行为1 ...
	 * @throws IOException 文件无法读入情况
	 */
	public static String[] fileReadLine(String srcFile, int readLineCount) throws IOException {
		
		if (srcFile == null) {
			throw new NullPointerException("指定源文件路径为null");
		}
		
		return fileReadLine(new File(srcFile), readLineCount);
	}
	
	/**
	 * 根据指定文件读取指定数量行的字符串
	 * 
	 * @param srcFile 源文件
	 * @param readLineCount 读取行的数量
	 * @return 读取行结果存储的数组，数组中元素按照读取顺序存储,例如:第一行的索引为0, 第二行为1 ...
	 * @throws IOException 文件无法读入情况
	 */
	public static String[] fileReadLine(File srcFile, int readLineCount) throws IOException {
		
		/*
		 * 参数校验
		 */
		if (srcFile == null) {
			throw new NullPointerException("指定源文件路径为null");
		}
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("指定源文件不存在:" + srcFile);
		}
		if (srcFile.isDirectory()) {
			throw new IllegalArgumentException("指定源文件不能为目录:" + srcFile);
		}
		
		FileReader in = new FileReader(srcFile);
		String[] bufferedReadLine = bufferedReadLine(new BufferedReader(in), readLineCount);
		in.close();
		
		return bufferedReadLine;
	}
	
	/**
	 * 根据高效字符输入流读取指定数量行的字符串
	 * @param bufferedReader 高效字符输入流
	 * @param readLineCount 读取行的数量
	 * @return 读取行结果存储的数组，数组中元素按照读取顺序存储,例如:第一行的索引为0, 第二行为1 ...
	 * @throws IOException 输入流无法读取情况
	 */
	public static String[] bufferedReadLine(BufferedReader bufferedReader, int readLineCount) throws IOException {
		
		/*
		 * 参数校验
		 */
		if (bufferedReader == null){
			throw new NullPointerException("指定BufferedReader不能为null");
		}
		if (readLineCount < 0) {
			throw new IllegalArgumentException("指定读取行数量不能为负:" + readLineCount );
		}
		if (readLineCount == 0) {
			return null;
		}
		
		String[] strArray = new String[readLineCount];
		for (int x = 0; x < readLineCount; x++) {
			if ((strArray[x] = bufferedReader.readLine()) == null) {
				break;
			}
		}
		bufferedReader.close();
		
		return strArray;
	}
	
	/**
	 * 字节输入流转换为字符串
	 * @param inputStream 输入流
	 * @param charSet 字符集, 如制定null则默认UTF-8
	 * @return 转换后的字符串
	 */
	public static String inputStream2String(InputStream inputStream, String charSet) {
		
		/*
		 * 参数校验
		 */
		if (inputStream == null) {
			return null;
		}
		if (charSet == null) {
			charSet = "UTF-8";
		}
		
		String result = null;
		try {
			byte[] buffer = new byte[4096];
			int len;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			while ( (len = inputStream.read(buffer)) != -1 ) {
				baos.write(buffer, 0, len);
			}
			
			result = baos.toString(charSet);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	/**
	 * 关闭各流
	 * @param streams 流
     */
	public static void closeStream(Closeable... streams) {

		if (streams == null) {
			return;
		}

		for (Closeable stream : streams) {
			if (stream == null) {
				return;
			}
			try {
				stream.close();
			} catch (IOException e) {
				Debug.e(e);
			}
		}

	}

}
