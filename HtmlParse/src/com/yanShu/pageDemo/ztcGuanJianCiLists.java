package com.yanShu.pageDemo;


import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yanShu.htmlTools.JsoupHanlder;
import com.yanShu.htmlTools.RegexUtils;

/**
 * 关键词列表
 * @author yanshu
 *
 */
public class ztcGuanJianCiLists extends JsoupHanlder{

	/**
	 * 推广关键词列表d
	 * 
	 * @param htmlPath
	 * @return
	 */
	public static ArrayList<String> generalizeKeyword(String htmlPath) {
		ArrayList<String> keyWords = new ArrayList<String>();
		try {
			String html = GetLoadFile(htmlPath);
			Document docHtml = loadHtml(html);
			Elements select = docHtml.select("#J_J_std_keyword_list_table > tbody:nth-child(1) > tr");
			
			//店铺名称
			String sellerName = GetText(docHtml.select("#J_common_header > div.header-right.fr > span").first()).replace("，退出", "");
			sellerName = sellerName.replace("，退出", "");
			int sellerIndex = sellerName.indexOf('(');
			if (sellerIndex!= -1) {
				sellerName = sellerName.substring(0,sellerIndex);
			}
			
			for (Element element : select) {

				StringBuffer strBufer = new StringBuffer();
				if (element.className().equals("tbExpand-ctnr  none")
						|| element.className().equals("tbExpand-ctnr tbExpand-last none")) {
					continue;
				}
				//sellerName
				strBufer.append(sellerName);
				strBufer.append("_|_");
				
				
				String campOrAdGroup = element.select("td:nth-child(7)> div:nth-child(1) > p > a:nth-child(1) ").attr("href");
				//campaignId
				String campaignId = RegexUtils.regexStr(campOrAdGroup, "(?<=campaignId=)\\d+(?=&)");
				strBufer.append(campaignId);
				strBufer.append("_|_");
				
				//adGroupId
				String adGroupId = RegexUtils.regexStr(campOrAdGroup, "(?<=adGroupId=)\\d+");
				strBufer.append(adGroupId);
				strBufer.append("_|_");
				
				
				// 日期
				strBufer.append(GetDate(-1));
				strBufer.append("_|_");

				// 推广状态
				strBufer.append(GetText(element.select("td:nth-child(2) > strong:nth-child(1)")));
				strBufer.append("_|_");

				String shopName = GetText(element.select(" td:nth-child(4) > div:nth-child(1) > span:nth-child(1)"));
				shopName = shopName.substring(3, shopName.length());
				strBufer.append(shopName);
				strBufer.append("_|_");

				//计算机质量分
				strBufer.append(strFromat(GetText(element.select(" td:nth-child(5) > span:nth-child(1) > span:nth-child(1)"))));
				strBufer.append("_|_");
				
				//  移动质量分
				strBufer.append(strFromat(GetText(element.select("td:nth-child(6) > span:nth-child(1) > span:nth-child(1)"))));
				strBufer.append("_|_");

				// 推广单元名称
				strBufer.append(GetText(element.select("td:nth-child(7)> div:nth-child(1) ")));
				strBufer.append("_|_");
				
				//宝贝图片
				strBufer.append("http:" + element.select("td:nth-child(7)>  div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img ").attr("src"));
				strBufer.append("_|_");

				// 计划筛选
				strBufer.append(GetText(element.select("td:nth-child(8) ")));
				strBufer.append("_|_");

				// 计算机出价*[@id="J_325418741102_price"]
				strBufer.append(strFromat(GetText(element.select("td:nth-child(9)>div:nth-child(1)"))));
				strBufer.append("_|_");

				// 移动出价 //*[@id="J_325418741102_mobilePrice"]
				strBufer.append(strFromat(GetText(element.select("td:nth-child(10)>div:nth-child(1)>span:nth-child(2)"))));
				strBufer.append("_|_");

				// 展望量
				strBufer.append(strFromat(GetText(element.select("td:nth-child(11)"))));
				strBufer.append("_|_");

				// 点击量
				strBufer.append(strFromat(GetText(element.select("td:nth-child(12)"))));
				strBufer.append("_|_");

				// 点击率
				strBufer.append(strFromat(GetText(element.select("td:nth-child(13)"))));
				strBufer.append("_|_");

				// 花费
				strBufer.append(strFromat(GetText(element.select("td:nth-child(14)"))));
				strBufer.append("_|_");

				// 平均点击花费
				strBufer.append(strFromat(GetText(element.select("td:nth-child(15)"))));
				strBufer.append("_|_");

				// 千次展现花费
				strBufer.append(strFromat(GetText(element.select("td:nth-child(16)"))));
				strBufer.append("_|_");

				// 直接成交笔数
				strBufer.append(strFromat(GetText(element.select("td:nth-child(17)"))));
				strBufer.append("_|_");

				// 间接成交笔数
				strBufer.append(strFromat(GetText(element.select("td:nth-child(18)"))));
				strBufer.append("_|_");

				// 直接成交金额
				strBufer.append(strFromat(GetText(element.select("td:nth-child(19)"))));
				strBufer.append("_|_");

				// 间接成交金额
				strBufer.append(strFromat(GetText(element.select("td:nth-child(20)"))));
				strBufer.append("_|_");

				// 收藏宝贝数
				strBufer.append(strFromat(GetText(element.select("td:nth-child(21)"))));
				strBufer.append("_|_");

				// 收藏店铺数
				strBufer.append(strFromat(GetText(element.select("td:nth-child(22)"))));
				strBufer.append("_|_");

				// 总成交金额
				strBufer.append(strFromat(GetText(element.select("td:nth-child(23)"))));
				strBufer.append("_|_");

				// 投入产出比
				strBufer.append(strFromat(GetText(element.select("td:nth-child(24)"))));
				strBufer.append("_|_");

				// 总成交笔数
				strBufer.append(strFromat(GetText(element.select("td:nth-child(25)"))));
				strBufer.append("_|_");

				// 总成交额
				strBufer.append(strFromat(GetText(element.select("td:nth-child(26)"))));
				strBufer.append("_|_");

				strBufer.append(strFromat(GetText(element.select("td:nth-child(27)"))));
				strBufer.append("_|_");

				strBufer.append(strFromat(GetText(element.select("td:nth-child(28)"))));
				strBufer.append("_|_");

				strBufer.append(strFromat(GetText(element.select("td:nth-child(29)"))));
				strBufer.append("_|_");

				strBufer.append(strFromat(GetText(element.select("td:nth-child(30)"))));
				strBufer.append("_|_");

				strBufer.append(strFromat(GetText(element.select("td:nth-child(31)"))));
				
				//最后还有没有元素，如果是最后一行就不添加分隔符
				if (element.nextElementSibling() != null) {
//					System.out.println(element.nextElementSibling());
					strBufer.append("_||_\n");
				}
				keyWords.add(strBufer.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyWords;
	}
}

