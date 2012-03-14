package com.stylogicalmaps.huechimes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.stylogicalmaps.huechimes.image.HueDraw;
import com.stylogicalmaps.huechimes.model.HueBar;

public class HueWidget extends View {
	private final String TAG;
	private final HueDraw hdrawable;
	private final HueBar chue;
	private int inputx;
	private int inputy;

	public HueWidget(Context context, String TAG, HueDraw hdrawable,
	        HueBar chue) {
		super(context);
		this.TAG = TAG;
		this.chue = chue;
		this.hdrawable = hdrawable;
		inputx = 0;
		inputy = 0;

		setMinimumWidth(15);
		setMinimumHeight(450);
	}
	
	public void inputX(double x) {
		inputx = 128 - (int)((x/9.8) * 128);
		
		Log.i(TAG,
		        Integer.toString(chue.getHue()) + " "
		                + Integer.toString(chue.getSat()) + " "
		                + Integer.toString(chue.getBri()));
		
		chue.setBri(inputx);
		this.invalidate();
	}

	public void inputY(double y) {
		final byte temp = (byte)(128 - (y/3));
		inputy =(0 < temp) ? temp: 0;

		Log.i(TAG,
		        Integer.toString(chue.getHue()) + " "
		                + Integer.toString(chue.getSat()) + " "
		                + Integer.toString(chue.getBri()));
		
		chue.setHue(inputy);
		this.invalidate();
	}
	
	public void restoreBar (int hue, int sat, int bri) {
		if (null != chue) {
			chue.setHue(hue);
			chue.setSat(sat);
			chue.setBri(bri);
		}
		this.invalidate();
	}
	
	public int getLastX() {
		return inputx;
	}
	
    public int getLastY() {
    	return inputy;
    }

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(getSuggestedMinimumWidth(),
		        getSuggestedMinimumHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);

		if (null != chue) {
			hdrawable.setParams(chue, this.getWidth());
			hdrawable.draw(canvas);
		}
	}
}