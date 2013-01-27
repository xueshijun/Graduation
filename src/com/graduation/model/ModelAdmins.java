package com.graduation.model;

import java.util.ArrayList;
import java.util.List;
 
import com.graduation.entity.EntityAdmins;
import com.graduation.util.database.sqlserver.ServiceSoap;

public class ModelAdmins { 
	

	public static List<EntityAdmins> getAllAdminsInfo(){
		
		ArrayList<String> param=new ArrayList<String>();
		ArrayList<String> value=new ArrayList<String>();
		
		ArrayList<String> list_String=new ArrayList<String>();
		ArrayList<EntityAdmins> list_EntityAdmins=new ArrayList<EntityAdmins>();
		list_String=ServiceSoap.GetWebServre("getAllAdminsInfo",param,value);
		if(list_String==null||list_String.size()<1){return null;}
		EntityAdmins admin=new EntityAdmins();
		for(int i=0;i<list_String.size();i++){
			admin.setAdminID(Integer.parseInt(list_String.get(i++)));
			admin.setName(list_String.get(i++));
			admin.setPass(list_String.get(i));
			list_EntityAdmins.add(admin);
		}
		return list_EntityAdmins;
	}
	
	public static EntityAdmins getAllAdminsInfoByID(int AdminID){ 
		ArrayList<String> param=new ArrayList<String>();
		ArrayList<String> value=new ArrayList<String>();
		param.add("AdminID");
		value.add(String.valueOf(AdminID));
		
		ArrayList<String> list=new ArrayList<String>();
		list=ServiceSoap.GetWebServre("getAllAdminsInfoByID",param,value);
		if(list==null||list.size()<1){return null;}
		EntityAdmins admin=new EntityAdmins();
		for(int i=0;i<list.size();i++){
			admin.setAdminID(Integer.parseInt(list.get(i++)));
			admin.setName(list.get(i++));
			admin.setPass(list.get(i));
		}
		return admin;
	}
}
