package com.kaassa.mobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KaassaLocalBase extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "kaassa.db";
    private static final int DATABASE_VERSION = 1;
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

    public KaassaLocalBase(Context context)
    {
        super(context, "kaassa.db", null, 1);
    }

    @Override
	public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("create table hotelrecords(id integer primary key,  HotelName text,  HotelLocationAddress text,  HotelLocationCityName text,  HotelLocationCountryName_fr text,HotelStars text, HotelContactEmail text, HotelContactPhone_one text,  HotelContactPhone_two text,  HotelContactWeb_site text,  HotelBillingPrice_min text, HotelServicesName_en text)");
    }

    @Override
	public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS hotelrecords");
        onCreate(sqlitedatabase);
    }
}
