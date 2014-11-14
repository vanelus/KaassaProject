package com.kaassa.mobile;

public class hotelRecord {
	
	public String HotelName;
	public String HotelCity;
	public String HotelCountry;
	public String HotelStars;
	
	public hotelRecord(String HotelName, String HotelCity, String HotelCountry, String HotelStars ) {
		this.HotelName = HotelName;
		this.HotelCity = HotelCity;
		this.HotelCountry = HotelCountry;
		this.HotelStars = HotelStars;
	}
	
	public String getHotelName() { return HotelName; }
	public void setHotelName(String HotelName) { this.HotelName = HotelName; }
	
	public String getHotelCity() { return HotelCity; }
	public void setHotelCity(String HotelCity) { this.HotelCity = HotelCity; }
	
	public String getHotelCountry() { return HotelCountry; }
	public void setHotelCountry(String HotelCountry) { this.HotelCountry = HotelCountry; }
	
	public String getHotelStars() { return HotelStars; }
	public void setHotelStars(String HotelStars) { this.HotelStars = HotelStars; }

}
