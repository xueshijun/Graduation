package com.graduation.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import com.graduation.R;

public class AsynTask extends Activity {
    
    private ImageView mImage;
    private ProgressDialog mDialog;

    String url = "http://img1.3lian.com/img2011/07/20/05.jpg";
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asyn_task);
        
        mDialog = new ProgressDialog(this);
        mDialog.setTitle("请稍等");
        mDialog.setMessage("正在加载...");
        
        mImage = (ImageView) findViewById(R.id.img);
        new ImageAsynTask(url,mImage).execute();
   
    
    
    }
    
    private class ImageAsynTask extends AsyncTask<Void, Void, Drawable> {
    	private String url;
    	private ImageView imageView;
    	public ImageAsynTask(String url,ImageView imageView){
    		this.url=url; 
    		this.imageView=imageView;
    	}
    	
    	
        @Override
        protected Drawable doInBackground (Void... params) {
            return loadImages(url);
        }
        
        @Override
        protected void onPostExecute (Drawable result) {
            super.onPostExecute(result);
//            mDialog.dismiss();
            imageView.setImageDrawable(result);
        }
        
        @Override
        protected void onPreExecute () {
            super.onPreExecute();
//            mDialog.show();
        }
    }
    
    @Override
    protected void onDestroy () {
        super.onDestroy();
//        mDialog.dismiss();
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
