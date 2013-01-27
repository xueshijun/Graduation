package com.graduation.activity;
 
import android.app.Activity;
import android.os.Bundle;

import com.graduation.R;
import com.graduation.R.drawable;
import com.graduation.R.id;
import com.graduation.R.layout;
import com.graduation.view.LoadingView;

public class LoadingActivity extends Activity
{
	  
    private LoadingView main_imageview;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        main_imageview = (LoadingView)findViewById(R.id.main_imageview);
        

        int[] imageIds = new int[6];
        imageIds[0] = R.drawable.loader_frame_1;
        imageIds[1] = R.drawable.loader_frame_2;
        imageIds[2] = R.drawable.loader_frame_3;
        imageIds[3] = R.drawable.loader_frame_4;
        imageIds[4] = R.drawable.loader_frame_5;
        imageIds[5] = R.drawable.loader_frame_6; 
        main_imageview.setImageIds(imageIds);
        
       Thread thread= new Thread()
        {
            @Override
            public void run()
            {
                main_imageview.startAnim();
            }
        };
        thread.start();
         
    } 
}