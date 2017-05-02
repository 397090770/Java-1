package com.yanShu.main;

import java.util.ArrayList;

import com.yanShu.pageDemo.jzdsDemo;
import com.yanShu.pageDemo.keyWordUp;
import com.yanShu.pageDemo.ztcBiaoZhunTuiGuang;
import com.yanShu.pageDemo.ztcTuiGuangJiHua;
import com.yanShu.pageDemo.ztcGuanJianCiLists;
import com.yanShu.pageDemo.ztc_standard_promotion_Stats;
import com.yanShu.pageDemo.ztc_tuiguangGoods;

public class _Main_Html {

	private static String filePath = "D:/workspace4.4/HtmlParse/HtmlContent/";

	/**
	 * 解析html
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		filePath = filePath + "ztc_biaoZhunTuiGuang2017_0413100328.html";
		
		// TODO Auto-generated method stub

		/*
		
		System.out.println(
				"------------------------------------------------推广计划宝贝信息---------------------------------------------------");
		String bztgPath = filePath + "ztc_tuiguangGoods2017_0410092338.html";
		ArrayList<String> ztcGoods = ztc_tuiguangGoods.standardGeneralize(bztgPath);
		for (String godItem : ztcGoods) {
			System.out.println(godItem);
		}

		*/
	
		System.out.println("-------------------------------------------------标准推广-------------------------------------------------");
		ArrayList<String> html = ztcBiaoZhunTuiGuang.standardGeneralize(filePath);
		for (String next_Item : html) {
			System.out.println(next_Item);
		}
		
		
		/*
		System.out.println("-------------------------------------------------标准推广合计信息-------------------------------------------------");
		String bztgPath = filePath + "ztc_biaoZhunTuiGuang.html";
		ArrayList<String> html = ztc_standard_promotion_Stats.standardGeneralize(bztgPath);
		for (String next_Item : html) {
			System.out.println(next_Item);
		}
		
		
		System.out.println(
				"------------------------------------------------推广计划名称下---------------------------------------------------");
		String tgJhPath = filePath + "ztc_tuiGuangJiHua.html";

		ArrayList<String> tgjhs = ztcTuiGuangJiHua.generalizePlan(tgJhPath);
		for (String tgjbItme : tgjhs) {
			System.out.println(tgjbItme);
		}
		
		**/
		
		/*
		System.out.println(
				"------------------------------------------------推广关键词列表-------------------------------------------------");
		String tgJhPath = filePath + "ztc_guanJianCiList.html";

		ArrayList<String> tgjhs = ztcGuanJianCiLists.generalizeKeyword(tgJhPath);
		for (String tgjbItme : tgjhs) {
			System.out.println(tgjbItme);
		}
		*/
	}
}