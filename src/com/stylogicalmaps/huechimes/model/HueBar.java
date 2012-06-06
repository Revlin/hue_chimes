package com.stylogicalmaps.huechimes.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HueBar implements Parcelable {
	private byte hue;
	private byte sat;
	private byte bri;
	
	public HueBar (int hue, int sat, int bri) {
		setHue(hue);
		setSat(sat);
		setBri(bri);
	}
	
	public int getHue() {
		return (int) hue;
	}
	
	public int getSat() {
		return (int) sat;
	}
	
	public int getBri() {
		return (int) bri;
	}
	
	public void setHue(int hue) {
		this.hue = (byte) (( 0 < (byte) hue) ? hue : -(hue));	
	}
	
	public void setSat(int sat) {
		this.sat = (byte) (( 0 < (byte) sat) ? sat : -(sat));	
	}
	
	public void setBri(int bri) {
		this.bri = (byte) (( 0 < (byte) bri) ? bri : -(bri));	
	}

	public static final Parcelable.Creator<HueBar> CREATOR
    = new Parcelable.Creator<HueBar>() {
		public HueBar createFromParcel(Parcel in) {
			final byte[] hsb = new byte[3];
			in.readByteArray(hsb);
		    return new HueBar(hsb[0], hsb[1], hsb[2]);
		}

		public HueBar[] newArray(int size) {
		    return new HueBar[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		final byte[] hsb = new byte[3];
		hsb[0] = hue;
		hsb[1] = sat;
		hsb[2] = bri;
	    dest.writeByteArray(hsb);
		
	}
}