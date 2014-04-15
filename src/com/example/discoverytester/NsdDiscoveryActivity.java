package com.example.discoverytester;

import android.app.Activity;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NsdDiscoveryActivity extends Activity {
	
	private final String TAG = "NsdDiscoveryActivity";
	private NsdHelper mNsdHelper;
	private TextView mStatusView;
	private final int port = 1324;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.nsd_discovery);
	    mStatusView = (TextView) findViewById(R.id.status);
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
            	String str = (String) msg.obj;
            	mStatusView.append(str);
            }
        };
	    mNsdHelper = new NsdHelper(this, handler);
	    mNsdHelper.initializeNsd();
	}
	
	public void clickAdvertise(View v) {
        mNsdHelper.registerService(port);
    }

    public void clickDiscover(View v) {
        mNsdHelper.discoverServices();
    }
    
    public void clickConnect(View v) {
        NsdServiceInfo service = mNsdHelper.getChosenServiceInfo();
        if (service != null) {
            Log.d(TAG, "Connecting.");
        } else {
            Log.d(TAG, "No service to connect to!");
        }
    }

    @Override
    protected void onPause() {
        if (mNsdHelper != null) {
            mNsdHelper.stopDiscovery();
        }
        super.onPause();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (mNsdHelper != null) {
            mNsdHelper.discoverServices();
        }
    }
    
    @Override
    protected void onDestroy() {
        mNsdHelper.tearDown();
        super.onDestroy();
    }
}
