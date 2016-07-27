package de.fgl.tryout.android.training001;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayMessageActivityForResult extends Activity {
	public final static String EXTRA_MESSAGE = "de.fgl.tryout.android.training001.MainAcitvity.MESSAGE";
	private String sMessageCurrent;
	
	/**
	 * @param message
	 * 15.07.2016 08:26:09 Fritz Lindhauer
	 */
	private void setMessageCurrent(String message) {
		this.sMessageCurrent= message;
		Log.d("FGLSTATE", this.getClass().getSimpleName()+". setMessageCurrent() für '" + message + "'");
		
	}
	private String getMessageCurrent(){
		Log.d("FGLSTATE", this.getClass().getSimpleName()+". getMessageCurrent() für '" + this.sMessageCurrent + "'");
		return this.sMessageCurrent;		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message_activity_for_result);
		
		
		//###############################################################
		//++++++++++++++++++++++++++++++++++++++++++++++
				// Get the message from the intent
				Intent intent = getIntent();
				String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
				this.setMessageCurrent(message);
				//++++++++++++++++++++++++++++++++++++++++++++++
				int iColor;
				String alarmMessagePrefix = "Alarm";
				if(message.startsWith(alarmMessagePrefix)){
					iColor = Color.RED;
				}else{
					iColor = Color.GRAY;
				}
				
				//FGL: Check System Version at Runtime
				if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
					//FGL: Aktiviere den Home / UP Button 
//					android.support.v7.app.ActionBar actionBar = getSupportActionBar();  //funktioniert nur in einer ActionBarActivity
//					actionBar.setDisplayHomeAsUpEnabled(true);
//					
//					//Style den Hintergrund		
//					actionBar.setBackgroundDrawable(new ColorDrawable(iColor)); // set your desired color
				}else{
					// If your minSdkVersion is 11 or higher, instead use:
					android.app.ActionBar actionBar = getActionBar();
					actionBar.setDisplayHomeAsUpEnabled(true);//To allow Up navigation with the app icon in the action bar
					
					//Style den Hintergrund			
					actionBar.setBackgroundDrawable(new ColorDrawable(iColor)); // set your desired color		
				}
					
				
				// Create the text view
			    TextView textView = new TextView(this);
			    textView.setTextSize(40);
			    textView.setText(message);

			    // Set the text view as the activity layout
			    setContentView(textView);

			    //FGL: Wenn man die View im Layout-Editor erstellt, kann man eine ID vergeben, die hier benutzt werden kann. 
			    //Initialize member TextView so we can manipulate it later
			    //mTextView = (TextView) findViewById(R.id.text_message);
				//setContentView(R.layout.activity_display_message);

				if (savedInstanceState == null) {
//					getSupportFragmentManager().beginTransaction()
//							.add(R.id.container, new PlaceholderFragment()).commit();
				}
		
		
	}
	
	
	public void finish() {
//	    if (mParent == null) {
//	        int resultCode;
//	        Intent resultData;
//	        synchronized (this) {
//	            resultCode = mResultCode;
//	            resultData = mResultData;
//	        }
//	        if (Config.LOGV) Log.v(TAG, "Finishing self: token=" + mToken);
//	        try {
//	            if (ActivityManagerNative.getDefault()
//	                .finishActivity(mToken, resultCode, resultData)) {
//	                mFinished = true;
//	            }
//	        } catch (RemoteException e) {
//	            // Empty
//	        }
//	    } else {
//	        mParent.finishFromChild(this);
//	    }
		
		Log.d("FGLSTATE", this.getClass().getSimpleName()+". im finish()");
		super.finish();
		
		String message = this.getMessageCurrent() + " (als Result)";
		Intent data = new Intent();
		data.putExtra(EXTRA_MESSAGE, message);
		setResult(Activity.RESULT_OK, data);
	}
	
	
}
