package com.stylogicalmaps.huechimes;

import java.util.ArrayList;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.stylogicalmaps.huechimes.model.HueBar;
import com.stylogicalmaps.huechimes.image.HueDraw;
import com.stylogicalmaps.huechimes.view.HueWidget;

public class HueChimesActivity extends Activity implements
        SensorEventListener {
	private final String TAG = this.getClass().getSimpleName();
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private int pauseint;

	private LinearLayout hueChimesLayout;
	private ArrayList<HueBar> hueList = new ArrayList<HueBar>();
	private HueDraw hd;

	/**
	 * Called when the activity is first
	 * created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, TAG + " is now running");
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager
		        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer,
		        SensorManager.SENSOR_DELAY_NORMAL);
		pauseint = 0;

		hd = new HueDraw(TAG);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
		        WindowManager.LayoutParams.FLAG_FULLSCREEN,
		        WindowManager.LayoutParams.FLAG_FULLSCREEN);

		hueChimesLayout = new LinearLayout(this);
		ViewGroup.LayoutParams hueChimesParams = new ViewGroup.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT);
		hueChimesLayout.setLayoutParams(hueChimesParams);
		hueChimesLayout.setBackgroundColor(Color.CYAN);
		setContentView(hueChimesLayout);

		for (int i = 0, t = 32; i < t; i++) {
			int so = this.getScreenOrientation();
			final HueBar hbar = new HueBar(i % 127, 127, 127);
			hueList.add(hbar);
			HueWidget hw = new HueWidget(this, TAG, hd, hbar, so);
			hw.setLayoutParams(new ViewGroup.LayoutParams(
			        ViewGroup.LayoutParams.FILL_PARENT,
			        ViewGroup.LayoutParams.FILL_PARENT));
			hueChimesLayout.addView(hw);
			if (2 == so) {
				hw.setMinimumWidth(800 / t);
				hw.setMinimumHeight(480);
			} else {
				hw.setMinimumWidth(480 / t);
				hw.setMinimumHeight(800);
			}
		}

		hueChimesLayout.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				HueWidget hw;
				int hi;
				if (MotionEvent.ACTION_CANCEL == event.getAction()) {
					Log.i(TAG, "TOUCH CANCLED");
					return false;
				} else if (MotionEvent.ACTION_UP == event.getAction()) {
					hi = (int) (event.getX() / (v.getWidth() / hueList
					        .size()));
					hw = (HueWidget) (hueChimesLayout.getChildAt(hi));
					if (null != hw) {
						hw.inputY(event.getY());
					}
					return false;
				} else if (MotionEvent.ACTION_MOVE == event.getAction()) {
					final int batchsize = event.getHistorySize();
					for (int i = 0; i < batchsize; i++) {
						// Log.i(TAG,"TOUCHY TOUCHY TOUCHY");
						// int hi =
						// (int)(event.getHistoricalX(i)/(v.getWidth()/hueList.size()));
						// hw =
						// (HueWidget)(hueChimesLayout.getChildAt(hi));
						// hw.input(event.getHistoricalX(i),
						// event.getHistoricalY(i));
					}
					hi = (int) (event.getX() / (v.getWidth() / hueList
					        .size()));
					hw = (HueWidget) (hueChimesLayout.getChildAt(hi));
					if (null != hw) {
						hw.inputY(event.getY());
					}
					return true;
				} else if (MotionEvent.ACTION_DOWN == event.getAction()) {
					Log.i(TAG, "TOUCHY TOUCHY TOUCHY");
					hi = (int) (event.getX() / (v.getWidth() / hueList
					        .size()));
					hw = (HueWidget) (hueChimesLayout.getChildAt(hi));
					if (null != hw) {
						hw.inputY(event.getY());
					}
					return true;
				}
				return false;
			}
		});
	}

	public int getScreenOrientation() {

		// Query what the orientation currently
		// really is.
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			// The following message is only
			// displayed once.
			return 1; // Portrait Mode

		} else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// The following message is only
			// displayed once.
			return 2; // Landscape mode
		}
		return 0;
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent event) {
		pauseint += 1;
		if (5 < pauseint) {
			/*
			 * HueWidget hw; for (int i = 0, z =
			 * hueList.size(); i < z; i++) { hw
			 * = (HueWidget)
			 * (hueChimesLayout.getChildAt(i));
			 * if (null != hw) {
			 * hw.inputX(event.values[2]);
			 * Log.i(TAG, "HueWidget input: " +
			 * event.values[2] + "@ hw" +
			 * Integer.toString(i)); } }
			 * Log.i(TAG, "Pause int is "+
			 * Integer.toString(pauseint));
			 */
			pauseint = 0;
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		// Notification that the activity will
		// be started
		String log = "onRestart";
		Log.i(TAG, log);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Notification that the activity is
		// starting
		String log = "onStart";
		Log.i(TAG, log);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Notification that the activity will
		// interact with the user
		String log = "onResume";
		Log.i(TAG, log);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Save instance-specific state
		if (null != hueList) {
			outState.putParcelableArrayList("hueList", hueList);
		} else {
			outState.putParcelableArrayList("hueList", null);
		}

		super.onSaveInstanceState(outState);
		String log = "onSaveInstanceState";
		Log.i(TAG, log);
	}

	@Override
	protected void onPostCreate(Bundle savedState) {
		super.onPostCreate(savedState);
		if (null != savedState) {
			if (null != savedState.getParcelableArrayList("hueList")) {
				ArrayList<HueBar> savedList = savedState
				        .getParcelableArrayList("hueList");
				int hi = 0;
				for (HueBar chue : savedList) {
					HueWidget hw;
					hw = (HueWidget) (hueChimesLayout.getChildAt(hi));
					if (null != hw) {
						hw.restoreBar(chue.getHue(), chue.getSat(),
						        chue.getBri());
					}
					hi += 1;
				}
				String log = "onPostCreate";
				Log.i(TAG, log);
			}
		}
	}

}
