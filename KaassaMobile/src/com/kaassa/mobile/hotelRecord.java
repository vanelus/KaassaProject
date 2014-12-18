package com.kaassa.mobile;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class hotelRecord implements Parcelable{

	
	
	public String HotelName;
	public String HotelLocationAddress;
	public String HotelLocationCityName;
	public String HotelLocationCityIs_top;
	public String HotelLocationCountryName_fr;
	public String HotelLocationCountryName_en;
	public String HotelLocationPresentation_fr;
	public String HotelLocationPresentation_en;
	public String HotelContactPhone_one;
	public String HotelContactEmail;
	public String HotelContactPhone_two;
	public String HotelContactWeb_site;
	public String HotelServicesName_fr;
	public String HotelServicesName_en;
	public String HotelBillingPrice_min;
	public String HotelBillingFrequency;
	public String HotelStars;
	

	
	public hotelRecord(String HotelName, String HotelLocationAddress, String HotelLocationCityName, String HotelLocationCountryName_fr, String HotelStars,String HotelContactEmail,String HotelContactPhone_one, String HotelContactPhone_two, String HotelContactWeb_site, String HotelBillingPrice_min,String HotelServicesName_en ) {
		this.HotelName = HotelName;
		this.HotelLocationAddress = HotelLocationAddress;
		this.HotelLocationCityName = HotelLocationCityName;
		this.HotelLocationCountryName_fr = HotelLocationCountryName_fr;
		this.HotelStars = HotelStars;
		this.HotelContactEmail = HotelContactEmail;
		this.HotelContactPhone_one = HotelContactPhone_one;
		this.HotelContactPhone_two = HotelContactPhone_two;
		this.HotelContactWeb_site = HotelContactWeb_site;
		this.HotelBillingPrice_min = HotelBillingPrice_min;
		this.HotelServicesName_en = HotelServicesName_en;
		
	}
	
	public String getHotelName() { return HotelName; }
	public void setHotelName(String HotelName) { this.HotelName = HotelName; }
	
	public String getHotelLocationAddress() { return HotelLocationAddress; }
	public void setHotelLocationAddress(String HotelLocationAddress) { this.HotelLocationAddress = HotelLocationAddress; }
	
	public String getHotelLocationCityName() { return HotelLocationCityName; }
	public void setHotelLocationCityName(String HotelLocationCityName) { this.HotelLocationCityName = HotelLocationCityName; }
	
	public String getHotelLocationCountryName_fr() { return HotelLocationCountryName_fr; }
	public void setHotelLocationCountryName_fr(String HotelLocationCountryName_fr) { this.HotelLocationCountryName_fr = HotelLocationCountryName_fr; }
	
	public String getHotelStars() { return HotelStars; }
	public void setHotelStars(String HotelStars) { this.HotelStars = HotelStars; }
	
	public String getHotelContactEmail() { return HotelContactEmail; }
	public void setHotelContactEmail(String HotelContactEmail) { this.HotelContactEmail = HotelContactEmail; }
	
	public String getHotelContactPhone_one() { return HotelContactPhone_one; }
	public void setHotelContactPhone_one(String HotelContactPhone_one) { this.HotelContactPhone_one = HotelContactPhone_one; }
	
	public String getHotelContactPhone_two() { return HotelContactPhone_two; }
	public void setHotelContactPhone_two(String HotelContactPhone_two) { this.HotelContactPhone_two = HotelContactPhone_two; }
	
	public String getHotelContactWeb_site() { return HotelContactWeb_site; }
	public void setHotelContactWeb_site(String HotelContactWeb_site) { this.HotelContactWeb_site = HotelContactWeb_site; }
	
	public String getHotelBillingPrice_min() { return HotelBillingPrice_min; }
	public void setHotelBillingPrice_min(String HotelBillingPrice_min) { this.HotelBillingPrice_min = HotelBillingPrice_min; }
	
	public String getHotelServicesName_en() { return HotelServicesName_en; }
	public void setHotelServicesName_en(String HotelServicesName_en) { this.HotelServicesName_en = HotelServicesName_en; }
	
	  @Override
	  public int describeContents() {
	    //On renvoie 0, car notre classe ne contient pas de FileDescriptor
	    return 0;
	  }

	  @Override
	  public void writeToParcel(Parcel dest, int flags) {
	    // On ajoute les objets dans l'ordre dans lequel on les a déclarés
	    dest.writeString(HotelName);
	    dest.writeString(HotelLocationAddress);
	    dest.writeString(HotelLocationCityName);
	    dest.writeString(HotelLocationCountryName_fr);
	    dest.writeString(HotelStars);
	    dest.writeString(HotelContactEmail);
	    dest.writeString(HotelContactPhone_one);
	    dest.writeString(HotelContactPhone_two);
	    dest.writeString(HotelContactWeb_site);
	    dest.writeString(HotelBillingPrice_min);
	    dest.writeString(HotelServicesName_en);


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
		  Log.i("Read", "ParcelData(Parcel source): time to put back parcel data");
		  HotelName = in.readString();
		  HotelLocationAddress = in.readString();
		  HotelLocationCityName = in.readString();
		  HotelLocationCountryName_fr = in.readString();
		  HotelStars = in.readString();
		  HotelContactEmail = in.readString();
		  HotelContactPhone_one = in.readString(); 
		  HotelContactPhone_two = in.readString();
		  HotelContactWeb_site = in.readString();
		  HotelBillingPrice_min = in.readString();
		  HotelServicesName_en = in.readString();

		}
}

