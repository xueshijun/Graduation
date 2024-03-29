package com.graduation.util.maps;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.VersionInfo;
import com.graduation.R;

public class BMapApiDemoMain extends Activity {
    
	ListView mListView = null;
    String mStrDemos[] = {
            "CloudSearchDemo",
			"ItemizedOverlay",
			"PoiSearchDemo",
			"RoutePlanDemo",
			"Overlaytest",
			"MyLocationDemo",
			"GeoCoderDemo",
			"OfflineDemo",
			"BusLineSearchDemo",
			"MapViewDemo",
			"ReleaseEngine"
			
	};
    Class<?> mActivities[] = {
            CloudSearchDemo.class,
    		ItemizedOverlayDemo.class,
    		PoiSearch.class,
    		RoutePlan.class,
    		LocationOverlay.class,
    		MyLocation.class,
    		GeoCoder.class,
    		OfflineDemo.class,
    		BusLineSearch.class,
    		MapViewDemo.class,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String versionInof = VersionInfo.getApiVersion();
		mListView = (ListView)findViewById(R.id.listView); 
        // 添加ListItem，设置事件响应
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < mStrDemos.length; i++) {
			data.add(mStrDemos[i]);
		}
        mListView.setAdapter((ListAdapter) new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data));
        mListView.setOnItemClickListener(new OnItemClickListener() {  
            public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {  
            	onListItemClick(index);
            }  
        });  
    }

    void onListItemClick(int index) {
    	if (index < 0 || index >= mActivities.length+1)
    		return;

    	if( index == mActivities.length) {
    		BMapAPI app = (BMapAPI)this.getApplication();
    		if (app.mBMapMan != null) {
    			app.mBMapMan.destroy();
    			app.mBMapMan = null;
    		}
    		return;
    	}

		Intent intent = null;
		intent = new Intent(BMapApiDemoMain.this, mActivities[index]);
		this.startActivity(intent);
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		BMapAPI app = (BMapAPI)this.getApplication();
		if (!app.m_bKeyRight) {
			TextView text = (TextView)findViewById(R.id.text_Info);
			text.setText("请在BMapApiDemoApp.java文件输入正确的授权Key！\r\n" +
					"申请地址：http://dev.baidu.com/wiki/static/imap/key/");
			text.setTextColor(Color.RED);
		}
		super.onResume();
	}

	@Override
	// 建议在APP整体退出之前调用MapApi的destroy()函数，不要在每个activity的OnDestroy中调用，
	// 避免MapApi重复创建初始化，提高效率
	protected void onDestroy() {
		BMapAPI app = (BMapAPI)this.getApplication();
		if (app.mBMapMan != null) {
			app.mBMapMan.destroy();
			app.mBMapMan = null;
		}
		super.onDestroy();
		System.exit(0);
	}
}