package com.example.discoverytester;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class WifiP2pDiscoveryActivity extends Activity {

	private final String TAG = "WifiP2PDiscoveryActivity";
	private NsdHelper mNsdHelper;
	private TextView mStatusView;
	private final int port = 1324;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi_p2p_discovery);
		mStatusView = (TextView) findViewById(R.id.status);
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				String str = (String) msg.obj;
				mStatusView.append(str);
			}
		};

	}
}
