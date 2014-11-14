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
        
        // On r�cup�re l'intent qui a lanc� cette activit�
        Intent i = getIntent();

        // Puis on r�cup�re l'�ge donn� dans l'autre activit�, ou 0 si cet extra n'est pas dans l'intent
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
