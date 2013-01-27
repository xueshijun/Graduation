package com.graduation.model;

import java.util.ArrayList;

import com.graduation.entity.EntityUser;
import com.graduation.util.database.sqlserver.ServiceSoap;

public class ModelUser {

	
	public static EntityUser getUserInfo(String name,String pass){
		ArrayList<String> param=new ArrayList<String>();
		ArrayList<String> value=new ArrayList<String>();
		param.add("strUser");param.add("strPass");
		value.add(name.trim());value.add(pass.trim()); 
		
		ArrayList<String> list=new ArrayList<String>(); 
		list=ServiceSoap.GetWebServre("getLoginInfo",param,value);
		if(list==null||list.size()<1){return null;}
		EntityUser user=new EntityUser();
		for(int i=0;i<list.size();){
			user.setMyUserID(list.get(i++));
			user.setMyName(list.get(i++));
			user.setMyPass(list.get(i++));
			user.setMyEmail(list.get(i++)); 
			user.setMyPhone(list.get(i++));
			user.setMyTel(list.get(i++));
			user.setMyAddress(list.get(i++));
			user.setMyIP(list.get(i++));
			user.setMyVipName(list.get(i++));
			user.setMyVipImg(list.get(i++));
		}
		return user;
	}
}
