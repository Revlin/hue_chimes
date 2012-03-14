package com.stylogicalmaps.huechimes.image;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.stylogicalmaps.huechimes.model.HueBar;

public class HueDraw extends Drawable {
	// private ColorFilter filter;
	// private int opacity;
	private final String TAG;
	private HueBar chue;
	private int barwidth;

	public HueDraw(String tag) {
		this.TAG = tag;
	}
	
	public void setParams(HueBar chue, int barwidth) {
		this.chue = chue;
		this.barwidth = barwidth;	
	}

	@Override
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		float[] hsv = new float[3];

		//Log.i(TAG, "Barwidth: " + Integer.toString(barwidth));
		
		hsv[0] = 360.0F * (chue.getHue() / 128.0F);
		hsv[1] = chue.getSat() / 128.0F;
		hsv[2] = chue.getBri() / 128.0F;
		//Log.i(TAG, "HSV color is :" + Float.toString(hsv[0]) + " "
		//        + Float.toString(hsv[1]) + " " + Float.toString(hsv[2]));
		int color = Color.HSVToColor(hsv);
		Log.i(TAG, "RGB color is :" + Integer.toString(color));

		// paint.setColorFilter(filter);
		// paint.setAlpha(opacity);
		paint.setColor(color);
		paint.setStrokeWidth(barwidth);
		canvas.drawLine(barwidth / 2, 0, barwidth / 2,
		        canvas.getHeight(), paint);

	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return PixelFormat.OPAQUE;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub

	}
}