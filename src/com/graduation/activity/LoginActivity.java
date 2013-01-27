package com.graduation.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.graduation.R;
import com.graduation.entity.EntityGPS;
import com.graduation.entity.EntityUser;
import com.graduation.model.ModelUser;
import com.graduation.util.GPS;

public class LoginActivity extends Activity {
	
    private static final int DIALOG_LOGING_PROGRESS = 1; 
//
	private static final int MAX_PROGRESS = 100;
	private ProgressDialog mProgressDialog;
	private int mProgress;
	private Handler mProgressHandler;

	EntityUser entityUser; 
	EditText name;
	EditText pass;
	Button btnLogin;
	CheckBox cbRemember;
	
	final static String USERNAME="userName";
	final static String USERPASS="userPass";
	final static String USERREMEMBER="userRemember";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final SharedPreferences share=getSharedPreferences("Login_Info",0);

		name = (EditText) findViewById(R.id.login_edit_account);
		name.setText(share.getString(USERNAME, "").toString());
		
		pass = (EditText) findViewById(R.id.login_edit_pwd); 
		pass.setText(share.getString(USERPASS, "").toString());
		
		cbRemember=(CheckBox) findViewById(R.id.login_cb_savepwd);
		cbRemember.setChecked(share.getBoolean(USERREMEMBER, false));
		

        mProgressHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                
                if (mProgress >= MAX_PROGRESS) {
                	if(entityUser==null){ 
                        Log.i("LoginActivity mProgress >= MAX_PROGRESS entityUser=NULL FAILURE", "mProgress"+mProgress);
                        mProgress=0;
                	}else{ 
                        Log.i("LoginActivity mProgress >= MAX_PROGRESS entityUser!=NULL SUCCESS", "mProgress"+mProgress); 
						Intent intent=new Intent(LoginActivity.this,ItemListsActivity.class);
						startActivity(intent); 
                		mProgressDialog.dismiss();
                	}
                } else {             		
                    mProgress++;
                    mProgressDialog.incrementProgressBy(1);
                    mProgressHandler.sendEmptyMessageDelayed(0, 100);
                    Log.i("LoginActivity mProgress < MAX_PROGRESS entityUser=NULL FAILURE", "mProgress"+mProgress);
                    if((entityUser==null)&&(mProgress%30==0)){
    	                new Thread(){
    	                	public void run(){ 
    	                		entityUser=ModelUser.getUserInfo(name.getText().toString(), pass.getText().toString()); 
    	                	}
    	                }.start();
                    }
                }
            }
        };

		
		btnLogin = (Button) findViewById(R.id.login_btn_login); 
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String mName = name.getText().toString();
				String mPass = pass.getText().toString();
				
				if (mPass == null || mName == null || mName.trim().length() == 0 || mPass.trim().length() == 0) {
					Toast.makeText(getBaseContext(),R.string.login_null_account,Toast.LENGTH_SHORT);

				} else {
	                showDialog(DIALOG_LOGING_PROGRESS);
	                
	                mProgress = 0;
	                mProgressDialog.setProgress(0);
	                mProgressHandler.sendEmptyMessage(0); 

					if(cbRemember.isChecked()){
						share.edit()
						.putString(USERNAME, mName)
						.putString(USERPASS, mPass)
						.putBoolean(USERREMEMBER,cbRemember.isChecked())
						.commit();
					}else{
						share.edit()
						.putString(USERNAME, mName) 
						.putBoolean(USERREMEMBER,cbRemember.isChecked())
						.commit();
					}
				} 
			} 
		}); 
}
  
	
	
	@Override
	protected Dialog onCreateDialog(int id){
		switch (id) {
		case DIALOG_LOGING_PROGRESS:
			 mProgressDialog = new ProgressDialog(LoginActivity.this);
	         mProgressDialog.setIcon(android.R.drawable.alert_light_frame);
	         mProgressDialog.setTitle(R.string.login_dialog_title);
	         mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	         mProgressDialog.setMax(MAX_PROGRESS); 
	         
	         mProgressDialog.setButton(getResources().getString(R.string.login_dialog_hidden)
	        		 , new DialogInterface.OnClickListener() {
	             public void onClick(DialogInterface dialog, int whichButton) {
	            	 
	             }
	         });
	         mProgressDialog.setButton2(getResources().getString(R.string.login_dialog_cancel), new DialogInterface.OnClickListener() {
	             public void onClick(DialogInterface dialog, int whichButton) {
	            	 mProgressDialog.dismiss(); 	
	             }
	         });
	         return mProgressDialog;  
		default:
			break;
		}
		return null;
	} 
}

 