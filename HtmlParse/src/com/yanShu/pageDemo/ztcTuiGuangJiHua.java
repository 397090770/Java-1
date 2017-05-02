package com.yanShu.pageDemo;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yanShu.htmlTools.JsoupHanlder;
import com.yanShu.htmlTools.RegexUtils;


/**
 * 推广计划
 * 
 * @author yanshu
 *
 */
public class ztcTuiGuangJiHua extends JsoupHanlder {

	/**
	 * 
	 * 推广计划
	 * 
	 * @return
	 */
	public static ArrayList<String> generalizePlan(String filePath) {
		ArrayList<String> planDatas = new ArrayList<String>();
		try {
			String html = GetLoadFile(filePath);
			Document docHtml = loadHtml(html);
			String sellerName = GetText(docHtml.select("#J_common_header > div.header-right.fr > span").first()).replace("，退出", "");
			
			Elements select = docHtml.select("div.table-container > table > tbody > tr");
			
			//dataType
			String comtext = docHtml.select("#J_table_time_filter span").first().text();
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
			String dateHtml =  docHtml.select("#J_table_time_filter").attr("bx-config");
			String dates = RegexUtils.regexStr(dateHtml, "(?<=dates:).*(?='],)");
			String startDate =dates.substring(dates.indexOf("[")+1,dates.indexOf(",")).replace("'", "");
			//EndDate 
			String endDate =dates.substring(dates.indexOf(",")+1,dates.length()).replace("'", "");
			
			
			for (Element element : select) {
				if (element.className().equals("tbExpand-ctnr  none")
						|| element.className().equals("tbExpand-ctnr tbExpand-last none")) {
					continue;
				}
				
				StringBuffer strBufs = new StringBuffer();
				
				int sellerIndex = sellerName.indexOf('(');
				if (sellerIndex!= -1) {
					sellerName = sellerName.substring(0,sellerIndex);
				}
				strBufs.append(sellerName);
				strBufs.append("_|_");
				
				String groupNode =element.select("p.title:nth-child(2) > a:nth-child(1)").attr("href");
				// campaignId
				String capaignId = RegexUtils.regexStr(groupNode, "(?<=campaignId=).*(?=&)");
				strBufs.append(capaignId);
				strBufs.append("_|_");
				
				
				String adGroupId = "adGroupId";
				adGroupId = RegexUtils.regexStr(groupNode, "(?<=adGroupId=).*");
				strBufs.append(adGroupId);
				strBufs.append("_|_");
				
				// 日期
				strBufs.append(GetDate(-1));
				strBufs.append("_|_");
				
				//StartDate
				strBufs.append(startDate);
				strBufs.append("_|_");
				
				//EndDate
				strBufs.append(endDate);
				strBufs.append("_|_");
				
				//dateType
				strBufs.append(dataType);
				strBufs.append("_|_");
				

				// 推广状态
				strBufs.append(GetText(element.select(".green")));
				strBufs.append("_|_");
				
				//ProductPicSrc
				strBufs.append("http:" +element.select("td:nth-child(3) > div > div > a > img").attr("src"));
				strBufs.append("_|_");

				// 推广单元
				strBufs.append(GetText(element.select("p.title:nth-child(2) > a:nth-child(1)")));
				strBufs.append("_|_");

				// 默认出价
				strBufs.append(strFromat(
						GetText(element.select("td.th-width2:nth-child(4) > div:nth-child(1) > span:nth-child(1)"))));
				strBufs.append("_|_");

				// 移动出价比例
				strBufs.append(strFromat(GetText(element.select(".mobile-discount-wrapper"))));
				strBufs.append("_|_");

				// 展现量
				strBufs.append(strFromat(GetText(element.select("td:nth-child(6)"))));
				strBufs.append("_|_");

				// 点击量
				strBufs.append(strFromat(GetText(element.select("td:nth-child(7)"))));
				strBufs.append("_|_");

				// 点击率
				strBufs.append(strFromat(GetText(element.select("td:nth-child(8)"))));
				strBufs.append("_|_");

				// 花费
				strBufs.append(strFromat(GetText(element.select("td:nth-child(9)"))));
				strBufs.append("_|_");

				// 平均点击花费
				strBufs.append(strFromat(GetText(element.select("td:nth-child(10)"))));
				strBufs.append("_|_");

				// 投入产出比
				strBufs.append(strFromat(GetText(element.select("td:nth-child(11)"))));
				strBufs.append("_|_");

				// 总成交笔数
				strBufs.append(strFromat(GetText(element.select("td:nth-child(12)"))));
				strBufs.append("_|_");

				// 直接成交金额
				strBufs.append(strFromat(GetText(element.select("td:nth-child(13)"))));
				strBufs.append("_|_");

				// 收藏宝贝数
				strBufs.append(strFromat(GetText(element.select("td:nth-child(14)"))));
				strBufs.append("_|_");

				// 点击转化率
				strBufs.append(strFromat(GetText(element.select("td:nth-child(15)"))));
				strBufs.append("_|_");

				// 收藏店铺数
				strBufs.append(strFromat(GetText(element.select("td:nth-child(16)"))));
				strBufs.append("_|_");

				// 直接购物车数
				strBufs.append(
						strFromat(GetText(element.select("td:nth-child(17)"))));
				strBufs.append("_|_");

				// 间接购物车数
				strBufs.append(
						strFromat(GetText(element.select("td:nth-child(18)"))));
				strBufs.append("_|_");

				// 自然流量转化金额
				strBufs.append(
						strFromat(GetText(element.select("td:nth-child(19)"))));
				strBufs.append("_|_");

				// 千次展现花费
				strBufs.append(strFromat(GetText(element.select("td:nth-child(20)"))));
				strBufs.append("_|_");

				// 直接成交笔数
				strBufs.append(strFromat(
						GetText(element.select("td:nth-child(21)"))));
				strBufs.append("_|_");

				// 间接成交笔数
				strBufs.append(strFromat(GetText(element.select("td:nth-child(22)"))));
				strBufs.append("_|_");

				// 间接成交金额
				strBufs.append(
						strFromat(GetText(element.select("td:nth-child(23)"))));
				strBufs.append("_|_");

				// 成交总金额
				strBufs.append(strFromat(GetText(element.select("td:nth-child(24)"))));
				strBufs.append("_|_");

				// 总收藏数
				strBufs.append(strFromat(GetText(element.select("td:nth-child(25)"))));
				strBufs.append("_|_");

				// 总购物车数
				strBufs.append(strFromat(GetText(element.select("td:nth-child(26)"))));
				strBufs.append("_|_");

				// 自然流量
				strBufs.append(
						strFromat(GetText(element.select("td:nth-child(27)"))));
				String tgHua = strBufs.toString();
				if (!"".equals(tgHua.trim())) {
					planDatas.add(strBufs.toString());
					planDatas.add("\n");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (planDatas.size() > 1) {
			planDatas.remove(planDatas.size() -1);
		}
		return planDatas;
	}
}
