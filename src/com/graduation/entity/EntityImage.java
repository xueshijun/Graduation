package com.graduation.entity;

import android.util.Log;

public class EntityImage {
	private int intImageID;
    private int ItemID; 
	private String BigImg;
    private String SmallImg;
    private String Alt;

    private float Width;//2013.1.5
    private float Height;//2013.1.5
	public int getIntImageID() {
		return intImageID;
	}
	public void setIntImageID(String intImageID) {
		try{
			this.intImageID =intImageID==null||intImageID.equals("")? -1 : Integer.parseInt(intImageID);
		}catch (Exception e) {
			this.intImageID=-1;
		}
	}
	public int getItemID() {
		return ItemID;
	}
	public void setItemID(String itemID) {
		try{
		this.ItemID = itemID==null||itemID.equals("")? -1 : Integer.parseInt(itemID);
		}catch (Exception e) {
			this.ItemID=-1;
		}
	}
 
	public String getBigImg() {
		return this.BigImg;
	}
	public void setBigImg(String bigImg) {
		this.BigImg = bigImg;
	}
	public String getSmallImg() {
		return this.SmallImg;
	}
	public void setSmallImg(String smallImg) {
		this.SmallImg = smallImg;
	}
	public String getAlt() {
		return this.Alt;
	}
	public void setAlt(String alt) {
		this.Alt = alt;
	}
	public float getWidth() {
		return Width;
	}
	public void setWidth(float width) {
		Width = width;
	}
	public float getHeight() {
		return Height;
	}
	public void setHeight(float height) {
		Height = height;
	}

	
	public void printEntityImage(){
		Log.i("EntityImage intImageID", intImageID+"");
		Log.i("EntityImage ItemID", ItemID+""); 
		Log.i("EntityImage BigImg",BigImg+"");
		Log.i("EntityImage SmallImg",SmallImg+ "");
		Log.i("EntityImage Alt", Alt+"");
//		Log.i("EntityImage Width", Width+"");
//		Log.i("EntityImage Height", Height+""); 
	} 
}
