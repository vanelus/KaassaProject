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

        // Puis on récupère l'âge donné dans l'autre activité, ou 0 si cet extra n'est pas dans l'intent
        String hotelselectedName = i.getStringExtra("hotelselectedName");
        
        TextView hotel__detail_name = (TextView)findViewById(R.id.hotel__detail_name);
        hotel__detail_name.setText(hotelselectedName);
    }

    public void onStart(Bundle savedInstanceState)
    {
    	
    }
    
    public void onResume(Bundle savedInstanceState)
    {	

    	
    }
}
