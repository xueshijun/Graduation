package com.graduation.test;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.graduation.R;
import com.graduation.entity.EntityAdmins;
import com.graduation.entity.EntityImage;
import com.graduation.entity.EntityShopingCar;
import com.graduation.entity.EntityUser;
import com.graduation.model.ModelAdmins;
import com.graduation.model.ModelImage;
import com.graduation.model.ModelShopingCar;
import com.graduation.model.ModelUser;

public class MainActivitys extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activitys);
 

		Log.i("MAINACTIVITY","================================管理员列表======================================="); 
		List<EntityAdmins> mList_EntityAdmins= ModelAdmins.getAllAdminsInfo();
		
		for(EntityAdmins admin:mList_EntityAdmins){
			admin.printAdmin();
		}
		Log.i("MAINACTIVITY","================================管理员编号为1=======================================");
		EntityAdmins mEntityAdmins=ModelAdmins.getAllAdminsInfoByID(1);
		mEntityAdmins.printAdmin();
		
		
		Log.i("MAINACTIVITY","================================登录=======================================");
		//登录
		EntityUser mEntityUser=ModelUser.getUserInfo("xueshijun","xueshijun");
		mEntityUser.printEntityUser(); 

		
		
		
		Log.i("MAINACTIVITY","==================================购物车=====================================");
		//购物车
		List<EntityShopingCar> list_EntityShopingCar=ModelShopingCar.getShopingCarInfo(1); 
		for(EntityShopingCar car:list_EntityShopingCar){
			car.printEntityShaopCar();
		}
		 
		
		Log.i("MAINACTIVITY","===================================商品图片===================================="); 
//		//商品列表
		List<EntityImage> list_EntityImage= ModelImage.getImages();
		for(EntityImage image:list_EntityImage){
			image.printEntityImage();
		} 
		Log.i("MAINACTIVITY","===================================商品编号为2的图片====================================");
		List<EntityImage> list_EntityImage2= ModelImage.getImageByItemID(2);
		for(EntityImage image:list_EntityImage2){
			image.printEntityImage();
		}		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_activitys, menu);
		return true;
	} 
}
