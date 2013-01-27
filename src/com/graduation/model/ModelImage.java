package com.graduation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.graduation.entity.EntityImage;
import com.graduation.util.database.sqlserver.ServiceSoap;

public class  ModelImage { 

	public static List<EntityImage> getImages(){ 
		ArrayList<String> param=new ArrayList<String>();
		ArrayList<String> value=new ArrayList<String>();


		ArrayList<String> list_String=new ArrayList<String>();
		ArrayList<EntityImage> list_EntityImage=new ArrayList<EntityImage>();
		list_String=ServiceSoap.GetWebServre("getImages",param,value); 
		System.out.println("list_String "+list_String);
		if(list_String==null||list_String.size()<1){return null;}
		
		for(int i=0;i<list_String.size();){ 
			EntityImage item=new EntityImage();
			item.setItemID(list_String.get(i++));
			item.setIntImageID(list_String.get(i++));
			item.setBigImg(list_String.get(i++));
			item.setSmallImg(list_String.get(i++));
			item.setAlt(list_String.get(i++)); 
			list_EntityImage.add(item);
		}
		return list_EntityImage;
	}
	
	
	public static  List<EntityImage> getImageByItemID(int ItemID){
		ArrayList<String> param=new ArrayList<String>();
		ArrayList<String> value=new ArrayList<String>();
		param.add("ItemID");
		value.add(String.valueOf(ItemID));
		
		ArrayList<String> list_String=new ArrayList<String>();
		ArrayList<EntityImage> list_EntityImage=new ArrayList<EntityImage>();
		list_String=ServiceSoap.GetWebServre("getImageByItemID",param,value);
		System.out.println("list_String "+list_String);
		if(list_String==null||list_String.size()<1){return null;} 
		EntityImage item=new EntityImage();
		
		for(int i=0;i<list_String.size();){
			item.setItemID(list_String.get(i++));
			item.setIntImageID(list_String.get(i++));
			item.setBigImg(list_String.get(i++));
			item.setSmallImg(list_String.get(i++));
			item.setAlt(list_String.get(i++));

			list_EntityImage.add(item);
		} 
		
		return list_EntityImage;
	}
}
