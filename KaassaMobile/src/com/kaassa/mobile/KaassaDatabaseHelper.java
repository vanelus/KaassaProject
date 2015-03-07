package com.kaassa.mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class KaassaDatabaseHelper {

	    private static final String DATABASE_NAME = "kaassa.db";
	    private static final int DATABASE_VERSION = 2;
	    public static final String KAASSAHOTEL_COLUMN_HOTECONTACTWEBSITE = "HotelContactWeb_site";
	    public static final String KAASSAHOTEL_COLUMN_HOTELBILLINGPRICEMIN = "HotelBillingPrice_min";
	    public static final String KAASSAHOTEL_COLUMN_HOTELCONTACTEMAIL = "HotelContactEmail";
	    public static final String KAASSAHOTEL_COLUMN_HOTELCONTACTPHONEONE = "HotelContactPhone_one";
	    public static final String KAASSAHOTEL_COLUMN_HOTELCONTACTPHONETWO = "HotelContactPhone_two";
	    public static final String KAASSAHOTEL_COLUMN_HOTELLOCATIONADDRESS = "HotelLocationAddress";
	    public static final String KAASSAHOTEL_COLUMN_HOTELLOCATIONCITYNAME = "HotelLocationCityName";
	    public static final String KAASSAHOTEL_COLUMN_HOTELLOCATIONCOUNTNAMEFR = "HotelLocationCountryName_fr";
	    public static final String KAASSAHOTEL_COLUMN_HOTELNAME = "HotelName";
	    public static final String KAASSAHOTEL_COLUMN_HOTELSERVICESNAMEEN = "HotelServicesName_en";
	    public static final String KAASSAHOTEL_COLUMN_HOTELSTARS = "HotelStars";
	    public static final String KAASSAHOTEL_COLUMN_ID = "id";
	    private static final String TABLE_NAME = "hotelrecords";
	    private SQLiteDatabase database;
	    private KaassaLocalBase openHelper;

	    public KaassaDatabaseHelper(Context context)
	    {
	        openHelper = new KaassaLocalBase(context);
	        database = openHelper.getWritableDatabase();
	    }

	    public Cursor getAllHotelRecords()
	    {
	        return database.rawQuery("select * from hotelrecords", null);
	    }

	    public void purgeHotelTable()
	    {
	        database.execSQL("delete from hotelrecords");
	        database.execSQL("vacuum");
	    }

	    public void saveHotelRecord(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
	            String s7, String s8, String s9, String s10)
	    {
	        ContentValues contentvalues = new ContentValues();
	        contentvalues.put("HotelName", s);
	        contentvalues.put("HotelLocationAddress", s1);
	        contentvalues.put("HotelLocationCityName", s2);
	        contentvalues.put("HotelLocationCountryName_fr", s3);
	        contentvalues.put("HotelStars", s4);
	        contentvalues.put("HotelContactEmail", s5);
	        contentvalues.put("HotelContactPhone_one", s6);
	        contentvalues.put("HotelContactPhone_two", s7);
	        contentvalues.put("HotelContactWeb_site", s8);
	        contentvalues.put("HotelBillingPrice_min", s9);
	        contentvalues.put("HotelServicesName_en", s10);
	        database.insert("hotelrecords", null, contentvalues);
	    }
}
