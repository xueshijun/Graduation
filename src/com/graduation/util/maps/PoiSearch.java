package com.graduation.util.maps;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiInfo;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.PoiOverlay;
import com.graduation.R;


public class PoiSearch extends MapActivity {
	Button mBtnSearch = null;	// ������ť
	Button mBtnDetailSearch = null;  // ��ϸ���Ѱ�ť
	Button mSuggestionSearch = null;  //suggestion����
	ListView mSuggestionList = null;
	public static String mStrSuggestions[] = {};
	
	MapView mMapView = null;	// ��ͼView
	MKSearch mSearch = null;	// ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	
	MKPoiResult mRes = null;    // poi�������
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.poisearch);
        
		BMapAPI app = (BMapAPI)this.getApplication();
		if (app.mBMapMan == null) {
			app.mBMapMan = new BMapManager(getApplication());
			app.mBMapMan.init(app.mStrKey, new BMapAPI.MyGeneralListener());
		}
		app.mBMapMan.start();
        // ���ʹ�õ�ͼSDK�����ʼ����ͼActivity
        super.initMapActivity(app.mBMapMan);
        
        mMapView = (MapView)findViewById(R.id.bmapView);
        mMapView.setTraffic(true);
        //���������Ŷ���������Ҳ��ʾoverlay,Ĭ��Ϊ������
        mMapView.setDrawOverlayWhenZooming(true);
        
        // ��ʼ������ģ�飬ע���¼�����
        mSearch = new MKSearch();
        mSearch.init(app.mBMapMan, new MKSearchListener(){

            @Override
            public void onGetPoiDetailSearchResult(int type, int error) {
                if (error != 0) {
                    Toast.makeText(PoiSearch.this, "��Ǹ��δ�ҵ����", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PoiSearch.this, "�ɹ����鿴����ҳ��", Toast.LENGTH_SHORT).show();
                }
            }
            
			public void onGetPoiResult(MKPoiResult res, int type, int error) {
				// ����ſɲο�MKEvent�еĶ���
				if (error != 0 || res == null) {
					Toast.makeText(PoiSearch.this, "��Ǹ��δ�ҵ����", Toast.LENGTH_LONG).show();
					return;
				}

				mRes = res;
			    // ����ͼ�ƶ�����һ��POI���ĵ�
			    if (res.getCurrentNumPois() > 0) {
				    // ��poi�����ʾ����ͼ��
					PoiOverlay poiOverlay = new PoiOverlay(PoiSearch.this, mMapView, mSearch);
					poiOverlay.setData(res.getAllPoi());
				    mMapView.getOverlays().clear();
				    mMapView.getOverlays().add(poiOverlay);
				    mMapView.invalidate();
			    	mMapView.getController().animateTo(res.getPoi(0).pt);
			    } else if (res.getCityListNum() > 0) {
			    	String strInfo = "��";
			    	for (int i = 0; i < res.getCityListNum(); i++) {
			    		strInfo += res.getCityListInfo(i).city;
			    		strInfo += ",";
			    	}
			    	strInfo += "�ҵ����";
					Toast.makeText(PoiSearch.this, strInfo, Toast.LENGTH_LONG).show();
			    }
			}
			public void onGetDrivingRouteResult(MKDrivingRouteResult res,
					int error) {
			}
			public void onGetTransitRouteResult(MKTransitRouteResult res,
					int error) {
			}
			public void onGetWalkingRouteResult(MKWalkingRouteResult res,
					int error) {
			}
			public void onGetAddrResult(MKAddrInfo res, int error) {
			}
			public void onGetBusDetailResult(MKBusLineResult result, int iError) {
			}
			@Override
			public void onGetSuggestionResult(MKSuggestionResult res, int arg1) {
				if (arg1 != 0 || res == null) {
					Toast.makeText(PoiSearch.this, "��Ǹ��δ�ҵ����", Toast.LENGTH_LONG).show();
					return;
				}
				int nSize = res.getSuggestionNum();
				mStrSuggestions = new String[nSize];

				for (int i = 0; i < nSize; i++) {
					mStrSuggestions[i] = res.getSuggestion(i).city + res.getSuggestion(i).key;
				}
				ArrayAdapter<String> suggestionString = new ArrayAdapter<String>(PoiSearch.this, android.R.layout.simple_list_item_1,mStrSuggestions);
				mSuggestionList.setAdapter(suggestionString);
				Toast.makeText(PoiSearch.this, "suggestion callback", Toast.LENGTH_LONG).show();

			}
            @Override
            public void onGetRGCShareUrlResult(String arg0, int arg1) {
            }
			
        });
        mSuggestionList = (ListView) findViewById(R.id.listView1);
        // �趨������ť����Ӧ
        mBtnSearch = (Button)findViewById(R.id.search);
        
        OnClickListener clickListener = new OnClickListener(){
			public void onClick(View v) {
				SearchButtonProcess(v);
			}
        };
        mBtnSearch.setOnClickListener(clickListener); 
        
        mBtnDetailSearch = (Button)findViewById(R.id.detail_search_a);
        mBtnDetailSearch.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                SearchButtonProcess(v);
            }
        });
        
        // �趨suggestion��Ӧ
        mSuggestionSearch = (Button)findViewById(R.id.suggestionsearch);

		OnClickListener clickListener1 = new OnClickListener(){
			public void onClick(View v) {
				SuggestionSearchButtonProcess(v);
			}
		};
		mSuggestionSearch.setOnClickListener(clickListener1); 
	}
	void SearchButtonProcess(View v) {
		if (mBtnSearch.equals(v)) {
//			Intent intent = null;
//			intent = new Intent(PoiSearch.this, MapViewDemo.class);
//			this.startActivity(intent);
//		    JSONObject log = new JSONObject();
//		    log.put(name, value)
		    
			EditText editCity = (EditText)findViewById(R.id.city);
			EditText editSearchKey = (EditText)findViewById(R.id.searchkey);
			mSearch.poiSearchInCity(editCity.getText().toString(), 
					editSearchKey.getText().toString());
		}
		else if (mBtnDetailSearch.equals(v)) {
		    if (mRes != null) {
		        ArrayList<MKPoiInfo> allPoi = mRes.getAllPoi();
	            if (allPoi == null || allPoi.size() <= 0) {
	                Toast.makeText(PoiSearch.this, "����������ʳ��poi", Toast.LENGTH_LONG).show();
	            }
	            else {
	                if (allPoi.get(0).hasCaterDetails) {
	                    mSearch.poiDetailSearch(allPoi.get(0).uid);
	                }
	            }
		    }
		    else {
		        Toast.makeText(PoiSearch.this, "����������ʳ��poi", Toast.LENGTH_LONG).show();
		    }
		}
	}

	void SuggestionSearchButtonProcess(View v) {
		EditText editSearchKey = (EditText)findViewById(R.id.suggestionkey);
		mSearch.suggestionSearch(editSearchKey.getText().toString());
	}
	
	@Override
	protected void onPause() {
//		BMapApiDemoApp app = (BMapApiDemoApp)this.getApplication();
//		app.mBMapMan.stop();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		BMapAPI app = (BMapAPI)this.getApplication();
		app.mBMapMan.start();
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
