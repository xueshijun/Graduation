package com.graduation.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.LocationListener;
import com.graduation.R;
import com.graduation.entity.EntityGPS;
import com.graduation.entity.EntityImage;
import com.graduation.model.ModelImage;
import com.graduation.util.GPS;
import com.graduation.util.database.sqlserver.ServiceSoap;
import com.graduation.util.maps.BMapAPI;

public class ItemListsActivity extends Activity {
		
		LocationListener mLocationListener = null;//create时注册此listener，Destroy时需要Remove

	 	private final static int DIALOG_LOADING=0; 
		
	    private final static int LOADING_STATE_FINISH=1;
		private GridView gv;  
	    private MyAdapter adapter;
	    private ArrayList<HashMap<Object,Object>> list;
	  
	    private Handler mHandler;
	 
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);   
			setContentView(R.layout.activity_item_lists);

	        showDialog(DIALOG_LOADING);
	        
	        
	        
	        gv = (GridView)findViewById(R.id.mItemListGridView); 
	        

	        DisplayMetrics dm = new DisplayMetrics();  
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
	        if(dm.heightPixels == 1280 && dm.widthPixels == 800){
	        	gv.setNumColumns(4);
	        }
	        
		 	Thread thread=  new Thread(){
		    	  @Override
		    	  public void run() {  
		    		  list=new ArrayList<HashMap<Object,Object>>();
		    		  List<EntityImage> list_EntityImage=new ArrayList<EntityImage>();
		    		  list_EntityImage= ModelImage.getImages();
		    		  while(list_EntityImage==null){
		    			  Log.i("Loading Data", "Times:");
		    			  list_EntityImage= ModelImage.getImages();
		    		  }

		    		  for(int i=0;i<list_EntityImage.size();i++){
		    				HashMap<Object, Object> map=new HashMap<Object, Object>(); 
		    				map.put("ImageID",list_EntityImage.get(i).getIntImageID());
		    				map.put("ItemID",list_EntityImage.get(i).getItemID());
		    				map.put("SmallImg",ServiceSoap.DOMAIN+list_EntityImage.get(i).getSmallImg());
		    				map.put("BigImg",ServiceSoap.DOMAIN+ list_EntityImage.get(i).getBigImg()); 
		    				map.put("isSelected", false);  
		    				list.add(map);
		    				Log.i("Loading Data","ID"+i);
		    		  }  
		    		  
		    		Message msg = new Message();
		    		Bundle b = new Bundle();// 存放数据
		    		b.putInt("LOADING_STATE_FINISH",LOADING_STATE_FINISH);
		    		msg.setData(b);
		    	  	mHandler.sendMessage(msg); //发送消息 
		  	}};
		 	thread.start();
		 	
	        mHandler=new Handler(){
	        	@Override
	            public void handleMessage(Message msg){
	                super.handleMessage(msg); 

	                Bundle b = msg.getData();
	                switch (b.getInt("LOADING_STATE_FINISH")) {
					case LOADING_STATE_FINISH:
	                    dismissDialog(DIALOG_LOADING); 
	                    

					
						adapter=new MyAdapter(list);
	                    gv.setAdapter(adapter);  
						break; 
					default:
						break;
					} 
	            }
	        };

		 	
	        gv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {
					 HashMap<Object,Object> map=list.get(position);
					 if(Boolean.parseBoolean(map.get("isSelected").toString())){
						 map.put("isSelected", false);
					 }else{ 
						 map.put("isSelected", true);
					 }
					adapter.notifyDataSetInvalidated();
					
				}
			});
	    
	        
	        
	        //
	      //GPS LOCATION
			GPS gps=GPS.getInstance((LocationManager) getSystemService(Context.LOCATION_SERVICE));
			final EntityGPS mEntityGPS= gps.getEntityGPS();
			  
			BMapAPI app = (BMapAPI)this.getApplication();
			if (app.mBMapMan == null) {
				app.mBMapMan = new BMapManager(getApplication());
				app.mBMapMan.init(app.mStrKey, new BMapAPI.MyGeneralListener());
			}
			app.mBMapMan.start(); 
	        // 注册定位事件
	        mLocationListener = new LocationListener(){
	        	
				@Override
				public void onLocationChanged(Location location) {
					if(location != null){
						mEntityGPS.setLatitude(location.getLatitude());
						mEntityGPS.setLongitude(location.getLongitude());
						mEntityGPS.setAltitude(location.getAltitude());
						mEntityGPS.setBearing(location.getBearing());
						mEntityGPS.setSpeed(location.getSpeed()); 
					}
				}
	        };
	        
	      setTitle(  "纬度"+mEntityGPS.getLatitude()+""
					+"经度"+mEntityGPS.getLongitude()+""
					+"海拔"+mEntityGPS.getAltitude()+"");
	    }  
	      
	    class MyAdapter extends BaseAdapter{
	    	class ViewHolder{
	    		TextView titleTextView;
	    		ImageView imageView;
	    		ImageView checkBoxImg;
	    		ProgressBar itemProgressBar;
	    	}  
	        private ArrayList<HashMap<Object,Object>> list;
	        private ImageAsynTask mImageAsynTask;
	        
	        MyAdapter(ArrayList<HashMap<Object,Object>> list){   
	            this.list=list;
	        	
	        }   

			public void setmSelectedID(int mSelectedID) {
	        	 HashMap<Object, Object> hashMap=list.get(mSelectedID); 
	        	 hashMap.put("isSelected",true); 
			} 
			
	        public int getCount() {  
	            return list.size();  
	        }  
	 
	 		public Object getItem(int item) {  
	            return list.get(item);  
	        }  
	 
	        public long getItemId(int id) {  
	            return id;  
	        }  
	        
	        public View getView(int position, View convertView, ViewGroup parent) {  
	        	 ViewHolder holder=new ViewHolder();
	        	 convertView = getLayoutInflater().inflate(R.layout.activity_item_lists_item, null);
	        	 holder.imageView=(ImageView)convertView.findViewById(R.id.imageView);
	        	 holder.checkBoxImg=(ImageView)convertView.findViewById(R.id.checkboxImage);
	        	 holder.titleTextView=(TextView)convertView.findViewById(R.id.titleTextView);
	        	 holder.itemProgressBar=(ProgressBar)convertView.findViewById(R.id.itemprogressBar);
	        	   
	        	 HashMap<Object, Object> hashMap=list.get(position);
	        	 EntityImage mEntityImage=new EntityImage(); 
	        	 mEntityImage.setIntImageID(hashMap.get("ImageID").toString());
	        	 mEntityImage.setItemID(hashMap.get("ItemID").toString()); 
	        	 mEntityImage.setBigImg(hashMap.get("BigImg").toString());
	        	 mEntityImage.setSmallImg(hashMap.get("SmallImg").toString()); 
	        	 boolean isSelected=(Boolean)hashMap.get("isSelected");
	        	 
	        	 holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);    
	        	 mImageAsynTask= new ImageAsynTask(mEntityImage.getSmallImg(),holder);
	        	 mImageAsynTask.execute();
	        	 holder.imageView.setAdjustViewBounds(false); 
	        	 
	        	 holder.titleTextView.setText(mEntityImage.getItemID()+"");
	        	 
	        	 Resources res=getResources(); 
	        	 if(isSelected==true){
	        		holder.imageView.setBackgroundColor(Color.parseColor("#87CEFA"));
//	        		Bitmap bmp_select_after=BitmapFactory.decodeResource(res, R.drawable.select_after);
//	        		holder.checkBoxImg.setImageBitmap(bmpwater_sel);
	        		holder.checkBoxImg.setImageResource(R.drawable.item_list_select_after);
	        	 }else{
	        		holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//	       			Bitmap bmp_select_before=BitmapFactory.decodeResource(res, R.drawable.select_before);
//	        		holder.checkBoxImg.setImageBitmap(bmpwater_sel);
	        		holder.checkBoxImg.setImageResource(R.drawable.item_list_select_before);
	        	 }  
	          	return convertView; 
	        }  
	        
	        
			
		    private class ImageAsynTask extends AsyncTask<Void, Void, Drawable> {
		    	private String url;
		    	private ViewHolder holder;
		    	public ImageAsynTask(String url,ViewHolder holder){
		    		this.url=url; 
		    		this.holder =holder;
		    	}
		    	
		    	
		        @Override
		        protected Drawable doInBackground (Void... params) {
		            return loadImages(url);
		        }
		        
		        @Override
		        protected void onPostExecute (Drawable result) {
		            super.onPostExecute(result); 
		            this.holder.itemProgressBar.setVisibility(ProgressBar.INVISIBLE);
		            this.holder.checkBoxImg.setVisibility(CheckBox.VISIBLE);
		            this.holder.imageView.setImageDrawable(result);
		        }
		        
		        @Override
		        protected void onPreExecute () {
		            super.onPreExecute(); 
//		            dis
		        }
		    }
		     
		    
		    public Drawable loadImages(String url) { 
		        try {
		            return Drawable.createFromStream((InputStream)(new URL(url)).openStream(), "test");
		        }
		        catch (IOException e) {
		            e.printStackTrace();
		        }
		        return null;
		    }
	        
	    }

		@SuppressLint("NewApi")
		@Override
		protected Dialog onCreateDialog(int id, Bundle bundle) {
			switch(id){
			case DIALOG_LOADING:
				View view=getLayoutInflater().inflate(R.layout.activity_item_lists_item_loading,null);
				return new AlertDialog
						.Builder(ItemListsActivity.this)
						.setView(view).create();
			}
			return super.onCreateDialog(id, bundle);
		}  

		
		@Override
		protected void onPause() {
			BMapAPI app = (BMapAPI)this.getApplication();
			// 移除listener
			app.mBMapMan.getLocationManager().removeUpdates(mLocationListener);
			app.mBMapMan.stop();
			super.onPause();
		}
		@Override
		protected void onResume() {
			BMapAPI app = (BMapAPI)this.getApplication();
			// 注册Listener
	        app.mBMapMan.getLocationManager().requestLocationUpdates(mLocationListener);
			app.mBMapMan.start();
			super.onResume();
		} 
}  