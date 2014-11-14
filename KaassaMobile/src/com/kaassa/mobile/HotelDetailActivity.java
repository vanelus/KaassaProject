package com.kaassa.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class HotelDetailActivity extends Activity {
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoteldetaillist);
        
        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Puis on récupère l'objet hotel selectionné dans l'autre activité, ou 0 si cet extra n'est pas dans l'intent
        hotelRecord h1 = i.getParcelableExtra("com.kaassa.mobile.KaassaMobile.hotelRecord");
        
        TextView hotel__detail_name = (TextView)findViewById(R.id.hotel__detail_name);
        hotel__detail_name.setText(h1.HotelName);
        TextView hotel_detail_city = (TextView)findViewById(R.id.hotel_detail_city);
        hotel_detail_city.setText(h1.HotelCity);
        TextView hotel_detail_country = (TextView)findViewById(R.id.hotel_detail_country);
        hotel_detail_country.setText(h1.HotelCountry);
        TextView hotel_detail_stars = (TextView)findViewById(R.id.hotel_detail_stars);
        hotel_detail_stars.setText(h1.HotelStars);
        
        TextView hotel_detail_phoneone = (TextView)findViewById(R.id.hotel_detail_phoneone);
        hotel_detail_phoneone.setText(h1.HotelPhone_one);
        TextView hotel_detail_phonetwo = (TextView)findViewById(R.id.hotel_detail_phonetwo);
        hotel_detail_phonetwo.setText(h1.HotelPhone_two);
        TextView hotel_detail_website = (TextView)findViewById(R.id.hotel_detail_website);
        hotel_detail_website.setText(h1.HotelWebsite);
        TextView hotel_detail_pricemin = (TextView)findViewById(R.id.hotel_detail_pricemin);
        hotel_detail_pricemin.setText(h1.HotelPrice_min);
    }

    public void onStart(Bundle savedInstanceState)
    {
    	
    }
    
    public void onResume(Bundle savedInstanceState)
    {	

    	
    }
}
