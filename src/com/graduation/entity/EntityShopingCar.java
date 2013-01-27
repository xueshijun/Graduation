package com.graduation.entity;

import java.util.Date;

import android.util.Log;

public class EntityShopingCar {

    private int CarID;
    private int ItemID;
    private int UserID;
    private boolean IsPay; 
    private boolean Visible;//2013.1.5
    private int ProductID;
    private int BrandID;
    private String Name;
    private double AgoraPrice;
    private double MemberPrice;
    private double VipPrice;
    private double SeckillPrice;
    private String Area;
    private String Details;
    private int ViewTimes;
    private int BuyTimes;
    private boolean IsSecondKill;
    private String LimitTime;

//    private Date AddDate;
    private String AddDate;
    private int Count;


	public int getCarID() {
		return this.CarID;
	}




	public void setCarID(String carID) {
		try{
			this.CarID= carID ==null||carID.equals("") ?-1:Integer.parseInt(carID);
		}catch (Exception ex) {
			CarID=-1;
		}
	}

	public int getItemID() {
		return this.ItemID;
	}




	public void setItemID(String itemID) {
		try{
			this.ItemID = itemID==null||itemID.equals("") ? -1 :Integer.parseInt(itemID);
		}catch (Exception e) {
			this.ItemID=-1;
		}
	}




	public int getUserID() {
		return UserID;
	}




	public void setUserID(String userID) {
		try{
			this.UserID=userID==null||userID.equals("") ? 0 : Integer.parseInt(userID);
		}catch (Exception e) {
			this.UserID=-1;
		}
	}

	public boolean isIsPay() {
		return IsPay;
	}


	public void setIsPay(String isPay) {
		try{
			IsPay = isPay==null||isPay.equals("")?false: Boolean.parseBoolean(isPay);
		}catch (Exception e) {
			this.IsPay=false;
		}
	} 
	
	public boolean isVisible() {
		return Visible;
	} 

	public void setVisible(String visible) {
		try{
			Visible =visible==null || visible.equals("") ? false : Boolean.parseBoolean(visible);
		}catch (Exception e) {
			this.Visible=false;
		}
	}




	public int getProductID() {
		return ProductID;
	}




	public void setProductID(String productID) {
		try{
		ProductID =productID==null||productID.equals("")?-1: Integer.parseInt(productID);
		}catch (Exception e) {
			this.ProductID=-1;
		}
	}




	public int getBrandID() {
		return BrandID;
	}




	public void setBrandID(String brandID) { 
		try{
			BrandID = brandID==null||brandID.equals("")?-1: Integer.parseInt(brandID);
		}catch (Exception e) {
			this.BrandID=-1;
		}
	}




	public String getName() {
		return Name;
	}




	public void setName(String name) {
		Name = name;
	}




	public double getAgoraPrice() {
		return AgoraPrice;
	}




	public void setAgoraPrice(String agoraPrice) { 
		try{ 
			AgoraPrice =Double.parseDouble(agoraPrice);
		}catch (Exception e) {
			this.AgoraPrice=0.0;
		}
	}




	public double getMemberPrice() {
		return MemberPrice;
	}




	public void setMemberPrice(String memberPrice) { 
		try{ 
			MemberPrice =Double.parseDouble(memberPrice);
		}catch (Exception e) {
			this.MemberPrice=0.0;
		} 	
	}




	public double getVipPrice() {
		return VipPrice;
	}




	public void setVipPrice(String vipPrice) {
		try{ 
			VipPrice =Double.parseDouble(vipPrice);
		}catch (Exception e) {
			this.VipPrice=0.0;
		}
	}




	public double getSeckillPrice() {
		return SeckillPrice;
	}




	public void setSeckillPrice(String seckillPrice) { 
		try{ 
			SeckillPrice =Double.parseDouble(seckillPrice);
		}catch (Exception e) {
			this.SeckillPrice=0.0;
		}
	}




	public String getArea() {
		return Area;
	}




	public void setArea(String area) {
		Area = area;
	}




	public String getDetails() {
		return Details;
	}




	public void setDetails(String details) {
		Details = details;
	}




	public int getViewTimes() {
		return ViewTimes;
	}




	public void setViewTimes(String viewTimes) { 
		try{
			ViewTimes = Integer.parseInt(viewTimes);
		}catch (Exception e) {
			ViewTimes=1;
		}
	}




	public int getBuyTimes() {
		return BuyTimes;
	}




	public void setBuyTimes(String buyTimes) { 
		try{			 
			BuyTimes = Integer.parseInt(buyTimes);
		}catch (Exception e) {
			BuyTimes=1;
		}
	}




	public boolean isIsSecondKill() {
		return IsSecondKill;
	}




	public void setIsSecondKill(String isSecondKill) {
		try{
			IsSecondKill =Boolean.parseBoolean(isSecondKill);
		}catch (Exception e) {
			IsSecondKill=false;
		}
	}




	public String getLimitTime() {
		return LimitTime;
	}




	public void setLimitTime(String limitTime) {
		LimitTime = limitTime;
	}




	public String getAddDate() {
		return AddDate;
	}




	public void setAddDate(String addDate) {
		AddDate = addDate;
	}




	public int getCount() {
		return Count;
	}


	public void setCount(String count) {
		try{
			Count = count==null||count.equals("") ? 0 :Integer.parseInt(count); 
		}catch (Exception e) {
			Count=0;
		}
	} 
	
	public void printEntityShaopCar() { 
		Log.i("EntityShopingCar CarID",this.CarID+"");
		Log.i("EntityShopingCar ItemID",this.ItemID+""); 
		Log.i("EntityShopingCar UserID",this.UserID+""); 
		Log.i("EntityShopingCar IsPay",this.IsPay+"");
		Log.i("EntityShopingCar Visible",this.Visible+""); 
		Log.i("EntityShopingCar ProductID", ProductID+"");
		Log.i("EntityShopingCar BrandID", BrandID+"");		
		Log.i("EntityShopingCar Name", Name+"");
		Log.i("EntityShopingCar AgoraPrice", AgoraPrice+"");
		Log.i("EntityShopingCar MemberPrice",MemberPrice +"");
		Log.i("EntityShopingCar VipPrice", VipPrice+"");
		Log.i("EntityShopingCar SeckillPrice",SeckillPrice +"");
		Log.i("EntityShopingCar Area", Area+"");
		Log.i("EntityShopingCar Details", Details+"");
		Log.i("EntityShopingCar ViewTimes", ViewTimes+"");
		Log.i("EntityShopingCar BuyTimes", BuyTimes+"");
		Log.i("EntityShopingCar IsSecondKill", IsSecondKill+"");
		Log.i("EntityShopingCar LimitTime",LimitTime +""); 
		Log.i("EntityShopingCar AddDate",this.AddDate.toString());
		Log.i("EntityShopingCar Count", Count+""); 
	} 
}
