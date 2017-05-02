package com.yanShu.pageDemo;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yanShu.htmlTools.JsoupHanlder;
import com.yanShu.htmlTools.RegexUtils;

public class ztc_standard_promotion_Stats extends JsoupHanlder{
	
	public static ArrayList<String> standardGeneralize(String filePath) {
		ArrayList<String> ztcDatas = new ArrayList<String>();
			try {
				
				String html = GetLoadFile(filePath);
				Document doc = Jsoup.parse(html);
				
				String sellerName = doc.select("#J_common_header > div.header-right.fr > span").text();
				sellerName = sellerName.replace("，退出", "");
				int sellerIndex = sellerName.indexOf('(');
				if (sellerIndex!= -1) {
					sellerName = sellerName.substring(0,sellerIndex);
				}
				
				//dataType
				String comtext = doc.select("#J_table_time_filter span").first().text();
				Integer dataType = 0;
				if (!"".equals(comtext)) {
					comtext = comtext.trim();
					if (comtext.equals("昨天")) {
						dataType = 1;
					}else if (comtext.equals("过去7天")) {
						dataType = 7;
					}else if (comtext.equals("过去30天")) {
						dataType = 30;
					}
				}
				
				//StartDate
				String dateHtml =  doc.select("#J_table_time_filter").attr("bx-config");
				String dates = RegexUtils.regexStr(dateHtml, "(?<=dates:).*(?='],)");
				String startDate =dates.substring(dates.indexOf("[")+1,dates.indexOf(",")).replace("'", "");
				//EndDate 
				String endDate =dates.substring(dates.indexOf(",")+1,dates.length()).replace("'", "");
				
				
				Elements selects = doc.select("div.table-container > table:nth-child(1) > tbody >tr:last-child");
				for (Element element : selects) {

					StringBuffer comext = new StringBuffer();
					
					//sellerName 
					comext.append(sellerName + "_|_");
					
					//昨天日期 
					comext.append(GetDate(-1));
					comext.append("_|_");
					
					//StartDate
					comext.append(startDate);
					comext.append("_|_");
					
					//EndDate
					comext.append(endDate);
					comext.append("_|_");
					
					//dateType
					comext.append(dataType);
					comext.append("_|_");
					
					//日限额
					comext.append(strFromat(GetText(element.select("td:eq(1)"))+"_|_"));
//					//投放平台
//					comext.append(strFromat(GetText(element.select("td:eq(2)"))+"_|_"));
					//展现量
					comext.append(strFromat(GetText(element.select("td:eq(3)"))+"_|_"));
					//点击量 
					comext.append(strFromat(GetText(element.select("td:eq(4)"))+"_|_"));
					//花费 
					comext.append(strFromat(GetText(element.select("td:eq(5)"))+"_|_"));
					//自然流量转化金额
					comext.append(strFromat(GetText(element.select("td:eq(6)"))+"_|_"));
					//点击率 
					comext.append(strFromat(GetText(element.select("td:eq(7)"))+"_|_"));
					//自然流量曝光 
					comext.append(strFromat(GetText(element.select("td:eq(8)"))+"_|_"));
					//平均点击花费
					comext.append(strFromat(GetText(element.select("td:eq(9)"))+"_|_"));
					//千次展现花费
					comext.append(strFromat(GetText(element.select("td:eq(10)"))+"_|_"));
					//直接成交笔数
					comext.append(strFromat(GetText(element.select("td:eq(11)"))+"_|_"));
					//间接成交笔数 
					comext.append(strFromat(GetText(element.select("td:eq(12)"))+"_|_"));
					//直接成交金额
					comext.append(strFromat(GetText(element.select("td:eq(13)"))+"_|_"));
					//间接成交金额
					comext.append(strFromat(GetText(element.select("td:eq(14)"))+"_|_"));
					//收藏店铺数
					comext.append(strFromat(GetText(element.select("td:eq(15)"))+"_|_"));
					//总成交金额 
					comext.append(strFromat(GetText(element.select("td:eq(16)"))+"_|_"));
					//投入产出比
					comext.append(strFromat(GetText(element.select("td:eq(17)"))+"_|_"));
					//收藏宝贝数
					comext.append(strFromat(GetText(element.select("td:eq(18)"))+"_|_"));
					//总收藏数
					comext.append(strFromat(GetText(element.select("td:eq(19)"))+"_|_"));
					//直接购物车数
					comext.append(strFromat(GetText(element.select("td:eq(20)"))+"_|_"));
					//总成交笔数
					comext.append(strFromat(GetText(element.select("td:eq(21)"))+"_|_"));
					//点击转化率
					comext.append(strFromat(GetText(element.select("td:eq(22)"))+"_|_"));
					//总购物车数
					comext.append(strFromat(GetText(element.select("td:eq(23)"))+"_|_"));
					//间接购物车数
					comext.append(strFromat(GetText(element.select("td:eq(24)"))));
					
					String tgHua = comext.toString();
					if (!"".equals(tgHua.trim())) {
						ztcDatas.add(tgHua+"\n");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				
		
		return ztcDatas;
	}

}
