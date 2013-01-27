package com.graduation.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.graduation.entity.EntityItems;
import com.graduation.entity.EntityShopingCar;
import com.graduation.util.database.sqlserver.ServiceSoap;

public class ModelShopingCar { 
	
	public static List<EntityShopingCar> getShopingCarInfo(int userID){
		ArrayList<String> param=new ArrayList<String>();
		ArrayList<String> value=new ArrayList<String>();
		param.add("userID");
		value.add(String.valueOf(userID));
		
		ArrayList<String> list_String=new ArrayList<String>(); 
		list_String=ServiceSoap.GetWebServre("getShopingCarInfo",param,value); 
		ArrayList<EntityShopingCar> list_EntityShopingCar=new ArrayList<EntityShopingCar>(); 
		if(list_String==null||list_String.size()<1){return null;} 
		EntityShopingCar mShopCar=new EntityShopingCar();
		
		for(int i=0;i<list_String.size();){
			mShopCar.setCarID(list_String.get(i++));
			mShopCar.setItemID(list_String.get(i++));
			mShopCar.setUserID(list_String.get(i++));
			mShopCar.setIsPay(list_String.get(i++));  
			mShopCar.setVisible(list_String.get(i++));
			mShopCar.setAddDate(list_String.get(i++));
			mShopCar.setProductID(list_String.get(i++));
			mShopCar.setBrandID(list_String.get(i++));
			mShopCar.setName(list_String.get(i++));
			mShopCar.setAgoraPrice(list_String.get(i++));
			mShopCar.setMemberPrice(list_String.get(i++));
			mShopCar.setVipPrice(list_String.get(i++));
			mShopCar.setSeckillPrice(list_String.get(i++));
			mShopCar.setArea(list_String.get(i++));
			mShopCar.setDetails(list_String.get(i++));
			mShopCar.setViewTimes(list_String.get(i++));
			mShopCar.setBuyTimes(list_String.get(i++));
			mShopCar.setIsSecondKill(list_String.get(i++));
			mShopCar.setLimitTime(list_String.get(i++));
			mShopCar.setCount(list_String.get(i++));
			list_EntityShopingCar.add(mShopCar);
//			mShopCar.printEntityShaopCar();
		}
		return list_EntityShopingCar;
	}
}
