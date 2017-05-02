package com.yanShu.pageDemo;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yanShu.htmlTools.JsoupHanlder;
import com.yanShu.htmlTools.RegexUtils;

public class ztc_tuiguangGoods extends JsoupHanlder{

	/**
	 * 首页
		ƒ
		标准推广
		ƒ
		推广计划： 1331 㗗
		ƒ
		宝贝推广
		ƒ
		关键词推广
	 * @param html
	 * @return
	 */
	/**
	 * 直通车宝贝推广解析
	 * @param html
	 * @return
	 */
	public static ArrayList<String> standardGeneralize(String filePath) {
		ArrayList<String> ztcDatas = new ArrayList<String>();
		try {
			String html = GetLoadFile(filePath);
			Document loadHtml = loadHtml(html);
			//店铺名称
			String sellerName = GetText(loadHtml.select("#J_common_header > div.header-right.fr > span").first()).replace("，退出", "");
			sellerName = sellerName.replace("，退出", "");
			int sellerIndex = sellerName.indexOf('(');
			if (sellerIndex!= -1) {
				sellerName = sellerName.substring(0,sellerIndex);
			}
			
			String adGroupId = RegexUtils.regexStr(html, "(?<=adGroupId:)\\d+(?=})");
			String campaignId = loadHtml.select("#magix_vf_main >div >div>ul>li:nth-child(5)>div>span>span> a").attr("value");
			String productUrl=  loadHtml.select("div.adgroup-details-text > h4:nth-child(1) > span > a").first().attr("href");
			String productId = RegexUtils.regexStr(productUrl, "(?<=id=)\\d+");
			
			
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
			
			
			Elements selects = loadHtml.select("div.table-container >table>tbody >tr");
			
			for (Element element : selects) {
				
				StringBuffer strBuff = new  StringBuffer();
				
				//除掉第一行的智能匹配
				String itm = element.attr("id");
				if ("".equals(itm)) {
					continue;
				}
				
				//sellerName
				strBuff.append(sellerName + "_|_");
				
				//adGroupId
				strBuff.append(adGroupId + "_|_");
				
				//campaignId
				strBuff.append(campaignId + "_|_");
				
				//productId
				strBuff.append(productId + "_|_");
				
				//keyWordId
				String keyWordId = element.attr("keywordid");
				strBuff.append(keyWordId + "_|_");
				
				//日期
				strBuff.append(GetDate(-1) + "_|_");
				
				//StartDate
				strBuff.append(startDate);
				strBuff.append("_|_");
				
				//EndDate
				strBuff.append(endDate);
				strBuff.append("_|_");
				
				//dateType
				strBuff.append(dataType);
				strBuff.append("_|_");
				
				// 状态
				strBuff.append(GetText(element.select("td:nth-child(2) > strong"))+"_|_");
				
				//关键词
				strBuff.append(GetText(element.select("td:nth-child(4) > div > span:nth-child(1) > span >a"))+"_|_");
				
				//计算机质量
				strBuff.append(strFromat(GetText(element.select("td:nth-child(5) > div > span"))+"_|_"));
				
				//移动质量分
				strBuff.append(strFromat(GetText(element.select("td:nth-child(6) > div > span"))+"_|_"));
				
				//计算机排名
				strBuff.append(strFromat(GetText(element.select("td:nth-child(7) > div > div > span"))+"_|_"));
				
				//移动排名
				strBuff.append(strFromat(GetText(element.select("td:nth-child(8) > div > div > span"))+"_|_"));
				
				//计算机出价
				strBuff.append(strFromat(GetText(element.select("td:nth-child(9) > div > span"))+"_|_"));
				
				//移动出价
				strBuff.append(strFromat(GetText(element.select("td:nth-child(10) > div > span"))+"_|_"));
				
				//展现量
				strBuff.append(strFromat(GetText(element.select("td:nth-child(11) > span:nth-child(1)"))+"_|_"));
				
				//点击量
				strBuff.append(strFromat(GetText(element.select("td:nth-child(12) > span:nth-child(1)"))+"_|_"));
				
				//点击率
				strBuff.append(strFromat(GetText(element.select("td:nth-child(13) > span:nth-child(1)"))+"_|_"));
				
				//花费
				strBuff.append(strFromat(GetText(element.select("td:nth-child(14) > span:nth-child(1)"))+"_|_"));
				
				//直接成交金额
				strBuff.append(strFromat(GetText(element.select("td:nth-child(15) > span:nth-child(1)"))+"_|_"));
				
				//平均点击花费
				strBuff.append(strFromat(GetText(element.select("td:nth-child(16) > span:nth-child(1)"))+"_|_"));
				
				//总成交笔数
				strBuff.append(strFromat(GetText(element.select("td:nth-child(17) > span:nth-child(1)"))+"_|_"));
				
				//平均展现排名
				strBuff.append(strFromat(GetText(element.select("td:nth-child(18) > span:nth-child(1)"))+"_|_"));
				
				//投入产出比
				strBuff.append(strFromat(GetText(element.select("td:nth-child(19) > span:nth-child(1)"))+"_|_"));
				
				//直接成交笔数
				strBuff.append(GetText(element.select("td:nth-child(20) > span:nth-child(1)"))+"_|_");
				
				//总成交金额
				strBuff.append(strFromat(GetText(element.select("td:nth-child(21) > span:nth-child(1)"))+"_|_"));
				
				//点击转化率
				strBuff.append(strFromat(GetText(element.select("td:nth-child(22) > span:nth-child(1)"))+"_|_"));
				
				//直接购物车数
				strBuff.append(strFromat(GetText(element.select("td:nth-child(23) > span:nth-child(1)"))+"_|_"));
				
				//宝贝收藏数
				strBuff.append(strFromat(GetText(element.select("td:nth-child(24) > span:nth-child(1)"))+"_|_"));
				
				//千次展现花费
				strBuff.append(strFromat(GetText(element.select("td:nth-child(25) > span:nth-child(1)"))+"_|_"));
				
				//间接成交金额
				strBuff.append(strFromat(GetText(element.select("td:nth-child(26) > span:nth-child(1)"))+"_|_"));
				
				//间接成交笔数
				strBuff.append(strFromat(GetText(element.select("td:nth-child(27) > span:nth-child(1)"))+"_|_"));
				
				//收藏店铺数
				strBuff.append(strFromat(GetText(element.select("td:nth-child(28) > span:nth-child(1)"))+"_|_"));
				
				//收藏总数
				strBuff.append(strFromat(GetText(element.select("td:nth-child(29) > span:nth-child(1)"))+"_|_"));
				
				//购物车数
				strBuff.append(strFromat(GetText(element.select("td:nth-child(30) > span:nth-child(1)"))+"_|_"));
				
				//间接购物车数
				strBuff.append(strFromat(GetText(element.select("td:nth-child(31) > span:nth-child(1)"))));
				
				String tgHua = strBuff.toString();
				if (!"".equals(tgHua.trim())) {
					ztcDatas.add(tgHua+"\n");
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
