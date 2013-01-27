package com.graduation.entity;

import android.util.Log;

public class EntityAdmins {
    private int AdminID;
    private String Name;
    private String Pass;
	public int getAdminID() {
		return AdminID;
	}
	public void setAdminID(int adminID) {
		AdminID = adminID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	
	public void printAdmin(){
		Log.i("EntityAdmins AdminID", AdminID+"");
		Log.i("EntityAdmins Name", Name+"");
		Log.i("EntityAdmins Pass", Pass+"");
	}
    
}
