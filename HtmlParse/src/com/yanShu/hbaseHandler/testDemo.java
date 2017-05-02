package com.yanShu.hbaseHandler;

public class testDemo {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String name ="0.25213213210012元 㑓";
		System.out.println(name.length());
		
		String a= "";
		if(name.indexOf('元') != -1)
			a  = name.substring(0, name.indexOf('元'));
		else{
			a  = "0";
		}
		
		System.out.println(a);
	}

}
