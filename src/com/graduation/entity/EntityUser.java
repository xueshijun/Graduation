package com.graduation.entity;

import android.util.Log;

public class EntityUser {

	 private int myUserID;
     private String myName ;
     private String myPass ;
     private String myEmail ;
     private String myPhone ;
     private String myTel ;
     private String myAddress ;
     private String myIP ;
     private String myVipName ;
     private String myVipImg ;
     
     public String getMyVipImg() {
		return myVipImg;
	}
	public void setMyVipImg(String myVipImg) {
		this.myVipImg = myVipImg;
	}
	private boolean myState = false;//用户状态（激活/销毁）
     
	public int getMyUserID() {
		return myUserID;
	}
	public void setMyUserID(String myUserID) {
		try{
			this.myUserID = Integer.parseInt(myUserID);
		}catch(Exception ex){
			this.myUserID=-1;
		}
	}
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public String getMyPass() {
		return myPass;
	}
	public void setMyPass(String myPass) {
		this.myPass = myPass;
	}
	public String getMyEmail() {
		return myEmail;
	}
	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}
	public String getMyPhone() {
		return myPhone;
	}
	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}
	public String getMyTel() {
		return myTel;
	}
	public void setMyTel(String myTel) {
		this.myTel = myTel;
	}
	public String getMyAddress() {
		return myAddress;
	}
	public void setMyAddress(String myAddress) {
		this.myAddress = myAddress;
	}
	public String getMyIP() {
		return myIP;
	}
	public void setMyIP(String myIP) {
		this.myIP = myIP;
	}
	public String getMyVipName() {
		return myVipName;
	}
	public void setMyVipName(String myVipName) {
		this.myVipName = myVipName;
	}
	public boolean isMyState() {
		return myState;
	}
	public void setMyState(String myState) {
		try{
			this.myState =Boolean.parseBoolean(myState);
		}catch(Exception ex){
			this.myState=false;
		}
	} 
	
	public void printEntityUser() {
		Log.i("EntityUser myUserID",this.myUserID+"");
		Log.i("EntityUser myName",this.myName);
		Log.i("EntityUser myPass",this.myPass); 
		Log.i("EntityUser myEmail",this.myEmail);
		Log.i("EntityUser myPhone",this.myPhone);
		Log.i("EntityUser myTel",this.myTel);
		Log.i("EntityUser myAddress",this.myAddress);
		Log.i("EntityUser myIP",this.myIP);
		Log.i("EntityUser myVipName",this.myVipName);
		Log.i("EntityUser myVipImg",this.myVipImg);
	} 
}
