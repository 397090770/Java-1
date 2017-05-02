package com.yanShu.pageDemo;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yanShu.htmlTools.JsoupHanlder;

public class keyWordUp extends JsoupHanlder{

	/**
	 * 文档解析
	 * @param htmlPath
	 * @return
	 */
	public static ArrayList<String> generalizePlan(String htmlPath) {
		ArrayList<String> planDatas = new ArrayList<String>();
		try {
			String html = GetLoadFile(htmlPath);
			Document docHtml = loadHtml(html);
			
			Elements select = select = docHtml.select("li.content-row");
			for (Element element : select) {
				// 日期
				StringBuffer strBufs = new StringBuffer(GetDate(-1) + "_|_");

				//关键词
				String keyName = GetText(element.select("div.title a"));
				strBufs.append(keyName);
				strBufs.append("_|_");
				
				//关注指数
				String num = GetText(element.select("span.num"));
				strBufs.append(num);
				strBufs.append("_|_");
				
				//升降位次
				String col5 = GetText(element.select("div.col5"));
				strBufs.append(col5);
				strBufs.append("_|_");
				
				//升降位次
				String col6 = strFromat(GetText(element.select("div.col6")));
				strBufs.append(col6);
				strBufs.append("_|_");
				
				//一级类目
				String rankA = strFromat(GetText(docHtml.select("a.selected ")));
				strBufs.append(rankA);
				strBufs.append("_|_");
				
				//二级类目
				Element rankCNodes = docHtml.select("a.param-item-selected").parents().parents().first().firstElementSibling();
				String rankB = strFromat(GetText(rankCNodes.select("span.nav-title")));
				strBufs.append(rankB);
				strBufs.append("_|_");
				
				//三级类目
				String rankC = strFromat(GetText(docHtml.select("a.param-item-selected")));
				strBufs.append(rankC);
				strBufs.append("_||_");
				
				
				//leafId
				String leafId= docHtml.select("a.param-item-selected").outerHtml();
				if(leafId.lastIndexOf("=") != -1 && leafId.indexOf("#ToSwitch") != -1){
					leafId = leafId.substring(leafId.lastIndexOf("=")+1,leafId.indexOf("#ToSwitch"));
				}
				
				strBufs.append(leafId);
				strBufs.append("_||_");
				
				planDatas.add(strBufs.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return planDatas;
	}
}
