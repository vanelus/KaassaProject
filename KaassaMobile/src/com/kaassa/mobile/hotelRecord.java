package com.kaassa.mobile;

import android.os.Parcel;
import android.os.Parcelable;

public class hotelRecord implements Parcelable{
	
	public String HotelName;
	public String HotelCity;
	public String HotelCountry;
	public String HotelStars;
	public String HotelPhone_one;
	public String HotelPhone_two;
	public String HotelWebsite;
	public String HotelPrice_min;
	
	public hotelRecord(String HotelName, String HotelCity, String HotelCountry, String HotelStars,String HotelPhone_one, String HotelPhone_two, String HotelWebsite, String HotelPrice_min ) {
		this.HotelName = HotelName;
		this.HotelCity = HotelCity;
		this.HotelCountry = HotelCountry;
		this.HotelStars = HotelStars;
		this.HotelPhone_one = HotelPhone_one;
		this.HotelPhone_two = HotelPhone_two;
		this.HotelWebsite = HotelWebsite;
		this.HotelPrice_min = HotelPrice_min;
	}
	
	public String getHotelName() { return HotelName; }
	public void setHotelName(String HotelName) { this.HotelName = HotelName; }
	
	public String getHotelCity() { return HotelCity; }
	public void setHotelCity(String HotelCity) { this.HotelCity = HotelCity; }
	
	public String getHotelCountry() { return HotelCountry; }
	public void setHotelCountry(String HotelCountry) { this.HotelCountry = HotelCountry; }
	
	public String getHotelStars() { return HotelStars; }
	public void setHotelStars(String HotelStars) { this.HotelStars = HotelStars; }
	
	public String getHotelPhone_one() { return HotelPhone_one; }
	public void setHotelPhone_one(String HotelPhone_one) { this.HotelPhone_one = HotelPhone_one; }
	
	public String getHotelPhone_two() { return HotelPhone_two; }
	public void setHotelPhone_two(String HotelPhone_two) { this.HotelPhone_two = HotelPhone_two; }
	
	public String getHotelWebsite() { return HotelWebsite; }
	public void setHotelWebsite(String HotelWebsite) { this.HotelWebsite = HotelWebsite; }
	
	public String getHotelPrice_min() { return HotelPrice_min; }
	public void setHotelPrice_min(String HotelPrice_min) { this.HotelPrice_min = HotelPrice_min; }
	
	  @Override
	  public int describeContents() {
	    //On renvoie 0, car notre classe ne contient pas de FileDescriptor
	    return 0;
	  }

	  @Override
	  public void writeToParcel(Parcel dest, int flags) {
	    // On ajoute les objets dans l'ordre dans lequel on les a déclarés
	    dest.writeString(HotelName);
	    dest.writeString(HotelCity);
	    dest.writeString(HotelCountry);
	    dest.writeString(HotelStars);
	    dest.writeString(HotelPhone_one);
	    dest.writeString(HotelPhone_two);
	    dest.writeString(HotelWebsite);
	    dest.writeString(HotelPrice_min);

	  }
	  
	  public static final Parcelable.Creator<hotelRecord> CREATOR = new Parcelable.Creator<hotelRecord>() {
		  @Override
		  public hotelRecord createFromParcel(Parcel source) {
		    return new hotelRecord(source);
		  }

		  @Override
		  public hotelRecord[] newArray(int size) {
		    return new hotelRecord[size];
		  }
		};

		public hotelRecord(Parcel in) {
		  HotelName = in.readString();
		  HotelCity = in.readString();
		  HotelCountry = in.readString();
		  HotelStars = in.readString();
		  HotelPhone_one = in.readString();
		  HotelPhone_two = in.readString();
		  HotelWebsite = in.readString();
		  HotelPrice_min = in.readString();
		  
		}
}

