package com.graduation.personal;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.graduation.R;

public class PersonalCenterActivity extends Activity implements OnTouchListener  {
	public final static int TYPE_ROTATING = 0;
	public final static int TYPE_ENDROTATE = 1;
	public final static int TYPE_VELROTATE = 2;
	public  static float params = 1; 
	private ViewGroup layoutmain;
	private ViewGroup layoutnext;
	private ViewGroup layoutlast;
	
	private ViewGroup currentView;
	private ViewGroup nextView;
	private ViewGroup lastView;
	
	private Rotate3D rotate3d;
	private Rotate3D rotate3d2;
	private Rotate3D rotate3d3;
	private int mCenterX ;		
	private int mCenterY ;		
	private float degree = (float) 0.0;
	private int currentTab = 0;
	private float perDegree;
	private VelocityTracker mVelocityTracker;
	
	private boolean areButtonsShowing;
	private RelativeLayout composerButtonsWrapper;
	private ImageView composerButtonsShowHideButtonIcon;
	private RelativeLayout composerButtonsShowHideButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMain();
        MyAnimations.initOffset(this);
       

		// 加号的动�?
		composerButtonsShowHideButton.startAnimation(MyAnimations.getRotateAnimation(0, 360, 200));
        DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		mCenterX = dm.widthPixels / 2;
		mCenterY = dm.heightPixels / 2;
		if(dm.widthPixels > 320){
			params = 2.0f/3;
		}
		perDegree = (float) (90.0 / dm.widthPixels);
	}
	private void setListener() {
		// 给大按钮设置点击事件
		composerButtonsShowHideButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!areButtonsShowing) {
					// 图标的动�?
					MyAnimations.startAnimationsIn(composerButtonsWrapper, 300);
					// 加号的动�?
					composerButtonsShowHideButtonIcon.startAnimation(MyAnimations.getRotateAnimation(0, -225, 300));
				} else {
					// 图标的动�?
					MyAnimations.startAnimationsOut(composerButtonsWrapper, 300);
					// 加号的动�?
					composerButtonsShowHideButtonIcon.startAnimation(MyAnimations.getRotateAnimation(-225, 0, 300));
				}
				areButtonsShowing = !areButtonsShowing;
			}
		});

		// 给小图标设置点击事件
		for (int i = 0; i < composerButtonsWrapper.getChildCount(); i++) {
			final ImageView smallIcon = (ImageView) composerButtonsWrapper.getChildAt(i);
			final int position = i;
			smallIcon.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					// 这里写各个item的点击事�?
					// 1.加号按钮缩小后消�?缩小的animation
					// 2.其他按钮缩小后消�?缩小的animation
					// 3.被点击按钮放大后消失 透明度渐�?放大渐变的animation
					//composerButtonsShowHideButton.startAnimation(MyAnimations.getMiniAnimation(300));
					if(areButtonsShowing){
						composerButtonsShowHideButtonIcon.startAnimation(MyAnimations.getRotateAnimation(-225, 0, 300));
						smallIcon.startAnimation(MyAnimations.getMaxAnimation(400));
						for (int j = 0; j < composerButtonsWrapper.getChildCount(); j++) {
							if (j != position) {
								final ImageView smallIcon = (ImageView) composerButtonsWrapper.getChildAt(j);
								smallIcon.startAnimation(MyAnimations.getMiniAnimation(300));
								//MyAnimations.getMiniAnimation(300).setFillAfter(true);
							}
						}
						areButtonsShowing = !areButtonsShowing;
					}
					
					
				}
			});
		}
	}
	private void initMain(){
        setContentView(R.layout.activity_personal_center);
        
        composerButtonsWrapper = (RelativeLayout) findViewById(R.id.composer_buttons_wrapper);
		composerButtonsShowHideButton = (RelativeLayout) findViewById(R.id.composer_buttons_show_hide_button);
		composerButtonsShowHideButtonIcon = (ImageView) findViewById(R.id.composer_buttons_show_hide_button_icon);
		
        layoutnext = (ViewGroup) findViewById(R.id.layout_next);
        layoutnext.setOnTouchListener(this);
        
        layoutlast = (ViewGroup) findViewById(R.id.layout_last);
        layoutlast.setOnTouchListener(this);

		layoutmain = (ViewGroup)findViewById(R.id.layout_main);
		layoutmain.setOnTouchListener(this);
		
		
		
		
		 setListener();
		 
		 currentView = layoutmain;
		 nextView = layoutnext;
		lastView = layoutnext;
	}
	
	


	private int mLastMotionX;
	
	public boolean onTouch(View arg0, MotionEvent event) {
		int x = (int) event.getX();
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实�?
			}
			mVelocityTracker.addMovement(event);//将事件加入到VelocityTracker类实例中
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			break;
		case MotionEvent.ACTION_MOVE:
			mVelocityTracker.computeCurrentVelocity(1000, 1000); 
			int dx = x - mLastMotionX;
			if(dx != 0){
				doRotate(dx,TYPE_ROTATING);
				if(degree > 90){			
					degree = 0;		
					break;     
				}
			}else{
				return false;
			}
			mLastMotionX = x;
			break;
		case MotionEvent.ACTION_UP:
			//设置units的�?�?000，意思为�?��时间内运动了多少个像�?
			mVelocityTracker.computeCurrentVelocity(1000); 
			float VelocityX = mVelocityTracker.getXVelocity();
			if(VelocityX > 500 || VelocityX < -500 ){
				//endRotateByVelocity();
				doRotate(0,TYPE_VELROTATE);
			}else{
				//endRotate();
				doRotate(0,TYPE_ENDROTATE);
			}
			setView();
			setViewVisibile();
			degree = 0;
			
			   releaseVelocityTracker();  
               break;  
 
           case MotionEvent.ACTION_CANCEL:  
               releaseVelocityTracker();  
               break;  
		}
		return true;
	}
	private void setView(){
		if(currentTab == 0){
			currentView = layoutmain;
			nextView = layoutnext;
			lastView = layoutlast;
		}else if(currentTab == 1){
			currentView = layoutnext;
			nextView = layoutlast;
			lastView = layoutmain;
		}else if(currentTab == 2){
			currentView = layoutlast;
			nextView = layoutmain;
			lastView = layoutnext;
		}
	}
	private void releaseVelocityTracker() {
		if(null != mVelocityTracker) {  
            mVelocityTracker.clear();  
            mVelocityTracker.recycle();  
            mVelocityTracker = null;  
        }  
		
	}
	private void setViewVisibile() {
		if(currentTab == 0){
			layoutmain.setVisibility(View.VISIBLE);
			layoutnext.setVisibility(View.GONE);
			layoutlast.setVisibility(View.GONE);
		}else if(currentTab == 1){
			layoutmain.setVisibility(View.GONE);
			layoutnext.setVisibility(View.VISIBLE);
			layoutlast.setVisibility(View.GONE);
		}else if(currentTab == 2){
			layoutmain.setVisibility(View.GONE);
			layoutnext.setVisibility(View.GONE);
			layoutlast.setVisibility(View.VISIBLE);
		}
	}
	private void doRotate(int dx,int type) {
		if(type == TYPE_ROTATING){
			float xd = degree;
			degree += perDegree*dx;
			rotate3d = new Rotate3D(xd , degree , 0, mCenterX, mCenterY);
			rotate3d2 = new Rotate3D( 90 + xd,  90+degree,0, mCenterX, mCenterY);
			rotate3d3 = new Rotate3D(-90+xd, -90+degree,0, mCenterX, mCenterY);	
		}else if(type == TYPE_ENDROTATE){
			rotate3d2 = new Rotate3D( 90 + degree,0,0, mCenterX, mCenterY);
			rotate3d3 = new Rotate3D( - 90 + degree,0,0, mCenterX, mCenterY);
			if(degree > 45){
				rotate3d = new Rotate3D(degree , 90 , 0, mCenterX, mCenterY);
				currentTab =(currentTab - 1)%3;
				if(currentTab < 0){
					currentTab = 2;
				}
			}else if(degree < -45){
				rotate3d = new Rotate3D(degree , -90 , 0, mCenterX, mCenterY);
				currentTab = (currentTab + 1)%3;
			}else{
				rotate3d = new Rotate3D( degree , 0 , 0, mCenterX, mCenterY);
				rotate3d2 = new Rotate3D(  90 + degree,90,0, mCenterX, mCenterY);
				rotate3d3 = new Rotate3D(  - 90 + degree,- 90,0, mCenterX, mCenterY);
			}

		}else if(type == TYPE_VELROTATE){
			if(degree > 0){
				rotate3d = new Rotate3D(degree , 90 , 0, mCenterX, mCenterY);
				rotate3d3 = new Rotate3D( - 90 + degree,0,0, mCenterX, mCenterY);
				currentTab =(currentTab - 1)%3;
				if(currentTab < 0){
					currentTab = 2;
				}
			}else if(degree < 0){
				rotate3d = new Rotate3D(degree , -90 , 0, mCenterX, mCenterY);
				rotate3d2 = new Rotate3D( 90 + degree,0,0, mCenterX, mCenterY);
				currentTab = (currentTab + 1)%3;
			}
		
		}
		rotate3d.setDuration(300);
		rotate3d2.setDuration(300);
		rotate3d3.setDuration(300);
		
		layoutmain.setVisibility(View.GONE);
		layoutnext.setVisibility(View.GONE);
		layoutlast.setVisibility(View.GONE);
		
		currentView.setVisibility(View.VISIBLE);
		if(degree < 0){//由右向左�?
			nextView.setVisibility(View.VISIBLE);
			toDoAnimation(currentView, rotate3d, Rotate3D.BOUNDARY_RIGHT);
			toDoAnimation(nextView, rotate3d2, Rotate3D.BOUNDARY_LEFT);
		}else if(degree > 0) {
			lastView.setVisibility(View.VISIBLE);
			toDoAnimation(currentView, rotate3d, Rotate3D.BOUNDARY_LEFT);
			toDoAnimation(lastView, rotate3d3, Rotate3D.BOUNDARY_RIGHT);	
		}
		
	}
	public void toDoAnimation(ViewGroup viewGroup,Rotate3D rotate3d,int type){
		rotate3d.setType(type);
		viewGroup.startAnimation(rotate3d);
		
	}
}
