package com.yanShu.pageDemo;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yanShu.htmlTools.JsoupHanlder;

public class jzdsDemo extends JsoupHanlder{
	/**
	 * 竞争对手
	 * @param filePath
	 * @return
	 */
	public static ArrayList<String> GetJXDSHtml(String filePath) {
		ArrayList<String> shopMsg = new ArrayList<String>();
		try {

			String html = GetLoadFile(filePath);
			Document loadHtml = loadHtml(html);
			Element shopMessage = loadHtml.getElementById("J_DetailMeta");
			
			// 价格 - 促销价
			Elements select = shopMessage.select("span.tm-price");
			for (Element element : select) {
				shopMsg.add(element.text() + "\t");
			}
			
			
			//月销量 - 累计评价 - 送天猫积分
			Elements select2 = shopMessage.select("span.tm-count");
			for (Element element : select2) {
				shopMsg.add(element.text() + "\t");
			}
			
			// 快递出发地
			String J_deliveryAdd = loadHtml.getElementById("J_deliveryAdd").text();
			shopMsg.add(J_deliveryAdd + "\t");
			
			// 用户所在地
			Element first = shopMessage.select("span.mui_addr_tri_1").first();
			if (null != first) {
				String mui_addr_tri_1 = first.ownText();
				shopMsg.add(mui_addr_tri_1+"\t");
			}
			
			//评论 --tm-col-master
			Elements tmColMacter = loadHtml.select("td.tm-col-master");
			for (Element element : tmColMacter) {
				//评论内容
				String tmContent = element.select("div.tm-rate-fulltxt").text();
				//日期
				String tmDate = element.select("div.tm-rate-date").first().ownText();
				//评论用户
				String tmUser = element.select("div.rate-user-info").text();
				
				if (null != tmColMacter) shopMsg.add(tmContent+"\t");
				if (null != tmDate) shopMsg.add(tmDate+"\t");
				if (null != tmUser) shopMsg.add(tmUser+"\t");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return shopMsg;
	}
}