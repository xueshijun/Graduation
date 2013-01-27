package com.graduation.util.maps;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
import com.baidu.mapapi.RouteOverlay;
import com.graduation.R;


public class BusLineSearch extends MapActivity {
	Button mBtnSearch = null;	// ������ť
	
	MapView mMapView = null;	// ��ͼView
	MKSearch mSearch = null;	// ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	String  mCityName = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.buslinesearch);
        
		BMapAPI app = (BMapAPI)this.getApplication();
		if (app.mBMapMan == null) {
			app.mBMapMan = new BMapManager(getApplication());
			app.mBMapMan.init(app.mStrKey, new BMapAPI.MyGeneralListener());
		}
		app.mBMapMan.start();
        // ���ʹ�õ�ͼSDK�����ʼ����ͼActivity
        super.initMapActivity(app.mBMapMan);
        
        mMapView = (MapView)findViewById(R.id.bmapView);
        mMapView.setBuiltInZoomControls(true);
        //���������Ŷ���������Ҳ��ʾoverlay,Ĭ��Ϊ������
        mMapView.setDrawOverlayWhenZooming(true);
        
        // ��ʼ������ģ�飬ע���¼�����
        mSearch = new MKSearch();
        mSearch.init(app.mBMapMan, new MKSearchListener(){

            @Override
            public void onGetPoiDetailSearchResult(int type, int error) {
            }
            
			public void onGetPoiResult(MKPoiResult res, int type, int error) {
				// ����ſɲο�MKEvent�еĶ���
				if (error != 0 || res == null) {
					Toast.makeText(BusLineSearch.this, "��Ǹ��δ�ҵ����", Toast.LENGTH_LONG).show();
					return;
		        }
				
				// �ҵ�����·��poi node
                MKPoiInfo curPoi = null;
                int totalPoiNum  = res.getNumPois();
				for( int idx = 0; idx < totalPoiNum; idx++ ) {
				    curPoi = res.getPoi(idx);
                    if ( 2 == curPoi.ePoiType ) {
                        // poi���ͣ�0����ͨ�㣬1������վ��2��������·��3������վ��4��������·
                        mSearch.busLineSearch(mCityName, curPoi.uid);
                    	break;
                    }
				}
				
				// û���ҵ�������Ϣ
                if (curPoi == null) {
                    Toast.makeText(BusLineSearch.this, "��Ǹ��δ�ҵ����", Toast.LENGTH_LONG).show();
                    return;
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
				if (iError != 0 || result == null) {
					Toast.makeText(BusLineSearch.this, "��Ǹ��δ�ҵ����", Toast.LENGTH_LONG).show();
					return;
		        }

				RouteOverlay routeOverlay = new RouteOverlay(BusLineSearch.this, mMapView);
			    // �˴���չʾһ��������Ϊʾ��
			    routeOverlay.setData(result.getBusRoute());
			    mMapView.getOverlays().clear();
			    mMapView.getOverlays().add(routeOverlay);
			    mMapView.invalidate();
			    
			    mMapView.getController().animateTo(result.getBusRoute().getStart());
			}
			@Override
			public void onGetSuggestionResult(MKSuggestionResult res, int arg1) {
				// TODO Auto-generated method stub
				
			}
            @Override
            public void onGetRGCShareUrlResult(String arg0, int arg1) {
                // TODO Auto-generated method stub
                
            }
			

        });
        
        // �趨������ť����Ӧ
        mBtnSearch = (Button)findViewById(R.id.search);
        
        OnClickListener clickListener = new OnClickListener(){
			public void onClick(View v) {
					SearchButtonProcess(v);
			}
        };
        
        mBtnSearch.setOnClickListener(clickListener); 
	}
	void SearchButtonProcess(View v) {
		if (mBtnSearch.equals(v)) {
			EditText editCity = (EditText)findViewById(R.id.city);
			EditText editSearchKey = (EditText)findViewById(R.id.searchkey);
			mCityName = editCity.getText().toString(); 
			mSearch.poiSearchInCity(mCityName, editSearchKey.getText().toString());
		}
	}

	@Override
	protected void onPause() {
		BMapAPI app = (BMapAPI)this.getApplication();
		app.mBMapMan.stop();
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
		// TODO Auto-generated method stub
		return false;
	}

}
