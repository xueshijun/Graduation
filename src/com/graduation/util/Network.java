package com.graduation.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {

//	www.jb51.net/article/32370.html
	//判断是否有网络连接
	public boolean isNetworkConnected(Context mContext) {
		if(mContext!=null){
			ConnectivityManager mConnectivityManager=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
			if(mNetworkInfo!=null){
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
}
