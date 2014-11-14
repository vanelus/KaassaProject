package com.kaassa.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class KaassaMobile extends Activity
{
	HotelAdapter HotelAdapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView listView = (ListView)findViewById(R.id.hotels_list);
    	HotelAdapter = new HotelAdapter(getBaseContext());
    	listView.setAdapter(HotelAdapter);
    	
        //Que se passe-t-il dès qu'on clique sur le bouton ?

    	
    	listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
    		{
    			@Override
    			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

    	    	    System.out.println("row Number Clicked "+ position);
    	    	    
    	    	 // Get Hotel "behind" the clicked item
    	    	    hotelRecord h =  (hotelRecord) arg0.getAdapter().getItem(position);
    	    	    
    	    	    Log.i("SomeTag", "position: " + position);    	    	    
    	    	    
    	    	    Intent intent = new Intent(KaassaMobile.this,HotelDetailActivity.class);
    	    	      
    	    	    intent.putExtra("com.kaassa.mobile.KaassaMobile.hotelRecord",h);
    	    	    
    	    	    startActivity(intent);      

    	    	}
    		});

    	
    }

        
        
    
    public void onStart(Bundle savedInstanceState)
    {
    	
    }
    
    public void onResume(Bundle savedInstanceState)
    {	

    	
    }
   
    // check network connection
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo2 = connMgr.getActiveNetworkInfo();
            if (networkInfo2 != null && networkInfo2.isConnected()) 
                return true;
            else
                return false;   
    }
}
