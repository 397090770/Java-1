package com.yanShu.pageDemo;

import java.io.IOException;
import java.io.Serializable;
import java.io.SerializablePermission;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yanShu.htmlTools.JsoupHanlder;
import com.yanShu.htmlTools.RegexUtils;

public class ztcBiaoZhunTuiGuang extends JsoupHanlder {

	/**
	 * 标准推广
	 * 
	 * @param html
	 * @return
	 */
	public static ArrayList<String> standardGeneralize(String filePath) {
		ArrayList<String> ztcDatas = new ArrayList<String>();
		try {
			String html = GetLoadFile(filePath);
			Document loadHtml = loadHtml(html);
			//selllerId 
			String sellerId = GetText(loadHtml.select("#J_common_header > div.header-right.fr > span"));
			sellerId = sellerId.replace("，退出", "");
			int sellerIndex = sellerId.indexOf('(');
			if (sellerIndex!= -1) {
				sellerId = sellerId.substring(0,sellerIndex);
			}
			
			//dataType
			String comtext = loadHtml.select("#J_table_time_filter span").first().text();
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
			String dateHtml =  loadHtml.select("#J_table_time_filter").attr("bx-config");
			String dates = RegexUtils.regexStr(dateHtml, "(?<=dates:).*(?='],)");
			String startDate =dates.substring(dates.indexOf("[")+1,dates.indexOf(",")).replace("'", "");
			//EndDate 
			String endDate =dates.substring(dates.indexOf(",")+1,dates.length()).replace("'", "");
			
			
			Elements selects = loadHtml.select("div.table-container > table:nth-child(1) > tbody >tr");
			for (Element element : selects) {

				StringBuffer strBuf = new StringBuffer();
				String itm = GetText(element.select("td:nth-child(1)"));
				if ("(合计)".equals(itm)) {
					continue;
				}
				
				//selllerId 
				strBuf.append(sellerId);
				strBuf.append("_|_");
				
				// campaignId
				String capaignId = element.select("td:nth-child(3) > div > span > a").attr("href");
				capaignId = RegexUtils.regexStr(capaignId, "(?<=campaignId=).*");
				strBuf.append(capaignId);
				strBuf.append("_|_");
				
				//昨天日期 
				strBuf.append(GetDate(-1));
				strBuf.append("_|_");
				
				//StartDate
				strBuf.append(startDate);
				strBuf.append("_|_");
				
				//EndDate
				strBuf.append(endDate);
				strBuf.append("_|_");
				
				//dateType
				strBuf.append(dataType);
				strBuf.append("_|_");
				
				
				// 状态
				strBuf.append(GetText(element.select("td:nth-child(2) > strong")));
				strBuf.append("_|_");
				// 推广计划名称
				strBuf.append(GetText(element.select("td:nth-child(3) > div > span > a")));
				strBuf.append("_|_");
				// 计划类型
				strBuf.append(GetText(element.select("td:nth-child(4)")));
				strBuf.append("_|_");
				// 分时折扣
				strBuf.append(strFromat(GetText(element.select("td:nth-child(5) > a"))));
				strBuf.append("_|_");
				// 日限额
				strBuf.append(strFromat(GetText(element.select("td.bid-inplace > a"))));
				strBuf.append("_|_");
				// 投放平台
				strBuf.append(strFromat(GetText(element.select("td:nth-child(7) > a"))));
				strBuf.append("_|_");
				// 展现量
				strBuf.append(strFromat(GetText(element.select("td:nth-child(8)"))));
				strBuf.append("_|_");
				// 点击量
				strBuf.append(strFromat(GetText(element.select("td:nth-child(9)"))));
				strBuf.append("_|_");
				// 花费
				strBuf.append(strFromat(GetText(element.select("td:nth-child(10)"))));
				strBuf.append("_|_");
				// 自然流量转化金额
				strBuf.append(strFromat(GetText(element.select("td:nth-child(11)"))));
				strBuf.append("_|_");
				// 点击率
				strBuf.append(strFromat(GetText(element.select("td:nth-child(12)"))));
				strBuf.append("_|_");
				// 自然流量曝光
				strBuf.append(strFromat(GetText(element.select("td:nth-child(13)"))));
				strBuf.append("_|_");

				// 平均点击花费
				strBuf.append(strFromat(GetText(element.select("td:nth-child(14)"))));
				strBuf.append("_|_");
				// 千次展现
				strBuf.append(strFromat(GetText(element.select("td:nth-child(15)"))));
				strBuf.append("_|_");
				// 直接成交笔数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(16)"))));
				strBuf.append("_|_");

				// 间接成交笔数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(17)"))));
				strBuf.append("_|_");
				
				// 直接成交金额
				strBuf.append(strFromat(GetText(element.select("td:nth-child(18)"))));
				strBuf.append("_|_");

				// 间接成交金额//*[@id="brix_brick_3300"]/tbody/tr[1]
				strBuf.append(strFromat(GetText(element.select("td:nth-child(19)"))));
				strBuf.append("_|_");

				// 收藏店铺数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(20)"))));
				strBuf.append("_|_");

				// 总成交金额
				strBuf.append(strFromat(GetText(element.select("td:nth-child(21)"))));
				strBuf.append("_|_");

				// 投入产出比
				strBuf.append(strFromat(GetText(element.select("td:nth-child(22)"))));
				strBuf.append("_|_");

				// 收藏宝贝数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(23)"))));
				strBuf.append("_|_");

				// 总收藏数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(24)"))));
				strBuf.append("_|_");

				// 直接购物车数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(25)"))));
				strBuf.append("_|_");

				// 总成交笔数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(26)"))));
				strBuf.append("_|_");

				// 点击转化率
				strBuf.append(strFromat(GetText(element.select("td:nth-child(27)"))));
				strBuf.append("_|_");

				// 总购物车数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(28)"))));
				strBuf.append("_|_");

				// 间接购物车数
				strBuf.append(strFromat(GetText(element.select("td:nth-child(29)"))));
				ztcDatas.add(strBuf.toString());
				
				String nextItem = GetText(element.nextElementSibling().select("td:nth-child(1)"));
				if ("(合计)".equals(nextItem) == false) {
					ztcDatas.add("\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return ztcDatas;
	}

}
