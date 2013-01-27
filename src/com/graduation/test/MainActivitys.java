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
 

		Log.i("MAINACTIVITY","================================����Ա�б�======================================="); 
		List<EntityAdmins> mList_EntityAdmins= ModelAdmins.getAllAdminsInfo();
		
		for(EntityAdmins admin:mList_EntityAdmins){
			admin.printAdmin();
		}
		Log.i("MAINACTIVITY","================================����Ա���Ϊ1=======================================");
		EntityAdmins mEntityAdmins=ModelAdmins.getAllAdminsInfoByID(1);
		mEntityAdmins.printAdmin();
		
		
		Log.i("MAINACTIVITY","================================��¼=======================================");
		//��¼
		EntityUser mEntityUser=ModelUser.getUserInfo("xueshijun","xueshijun");
		mEntityUser.printEntityUser(); 

		
		
		
		Log.i("MAINACTIVITY","==================================���ﳵ=====================================");
		//���ﳵ
		List<EntityShopingCar> list_EntityShopingCar=ModelShopingCar.getShopingCarInfo(1); 
		for(EntityShopingCar car:list_EntityShopingCar){
			car.printEntityShaopCar();
		}
		 
		
		Log.i("MAINACTIVITY","===================================��ƷͼƬ===================================="); 
//		//��Ʒ�б�
		List<EntityImage> list_EntityImage= ModelImage.getImages();
		for(EntityImage image:list_EntityImage){
			image.printEntityImage();
		} 
		Log.i("MAINACTIVITY","===================================��Ʒ���Ϊ2��ͼƬ====================================");
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
