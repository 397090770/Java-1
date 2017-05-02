package com.yanShu.htmlTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class JsoupHanlder {

	/**
	 * 加载html
	 * 
	 * @param html文本
	 * @return Document
	 * @throws IOException
	 */
	public static Document loadHtml(String bodyHtml) throws IOException {
		Document document = Jsoup.parseBodyFragment(bodyHtml);
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyyMMddhhmm");
		// String startTime =simpleDateFormat.format(new Date());
		// System.out.println(startTime);
		return document;
	}

	/**
	 * 读取文件
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String GetLoadFile(String filePath) throws IOException {
		String encoding = "utf-8";
		StringBuffer html = new StringBuffer();
		File file = new File(filePath);
		if (file.isFile() && file.exists()) { // 判断文件是否存在
			InputStreamReader read;

			read = new InputStreamReader(new FileInputStream(file), encoding);
			// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				html.append(lineTxt);
			}
			read.close();
		}
		return html.toString();
	}

	/**
	 * 获取日期
	 * 
	 * @param day
	 * @return
	 */
	public static String GetDate(int day) {
		// 日期
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		Date time = cal.getTime();
		// String date = new SimpleDateFormat("yyyyMMddHHmm").format(time);
		String date = new SimpleDateFormat("yyyyMMdd").format(time);
		return date;
	}

	/**
	 * 获取文本
	 * 
	 * @param element
	 * @return
	 */
	public static String GetText(Elements element) {

		try {

			if (element != null) {
				return element.text();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "0";
		}
		return "0";
	}

	/**
	 * 获取文本
	 * 
	 * @param element
	 * @return
	 */
	public static String GetText(Element element) {

		if (element != null) {
			return element.text();
		}
		return "0";
	}

	/**
	 * 字符串截取
	 * 
	 * @param pms
	 * @return
	 */
	public static String strFromat(String pms) {
		Hashtable<String, String> hm = new Hashtable<String, String>();
		hm.put("%", "");
		hm.put("元", "");
		hm.put("分", "");
		hm.put("-", "0");
		hm.put("￥", "");
		hm.put(" ", "");
		hm.put("㑓", "");
		hm.put("Ŵ", "");
		Set<String> keys = hm.keySet();
		for (String key : keys) {
			pms = pms.replaceAll(key, hm.get(key));
			// System.out.println("Value of "+key+" is: "+hm.get(key));
		}
		return pms;
	}
}
