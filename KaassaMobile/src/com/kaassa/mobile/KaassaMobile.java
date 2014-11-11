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
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
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
    	
    
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listHotel);
//        liste.setAdapter(adapter);
        
//        JSONObject reader = null;
//		try {
//			reader = new JSONObject(result);
//		} catch (JSONException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        
//        String Hotel_Name = null;
//		try {
//			Hotel_Name = reader.getString("name");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String Hotel_Star = null;
//        try {
//			 Hotel_Star = reader.getString("stars");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        
//        //Get the hotel location
//        JSONObject Hotel_Location = null;
//        try {
//			 Hotel_Location  = reader.getJSONObject("location");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        //Get the hotel contacts
//        JSONObject Hotel_contacts = null;
//        try {
//			 Hotel_contacts  = reader.getJSONObject("contact");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        //Get the hotel services
//        JSONArray Hotel_services = null;
//        try {
//			 Hotel_services = reader.getJSONArray("services");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        //Get the hotel billing
//        JSONObject Hotel_billing = null;
//        try {
//			 Hotel_billing  = reader.getJSONObject("billing");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        
//        
//        
//      //display in long period of time
//        Toast.makeText(getApplicationContext(),"Hotel :"+Hotel_Name+" with "+Hotel_Star+"stars", Toast.LENGTH_LONG).show();
//               
//        
//        TextView HotelNameValue = (TextView)findViewById(R.id.HotelNameValue);
//        TextView HotelLocationValue = (TextView)findViewById(R.id.HotelLocationValue);
//        TextView HotelContactValue = (TextView)findViewById(R.id.HotelContactValue);
//        TextView HotelServicesValue = (TextView)findViewById(R.id.HotelServicesValue);
//        TextView HotelBillingValue = (TextView)findViewById(R.id.HotelBillingValue);
//        TextView HotelStarsValue = (TextView)findViewById(R.id.HotelStarsValue);
//        
//        HotelNameValue.setText(Hotel_Name);
//        try {
//			HotelLocationValue.setText(Hotel_Location.getString("address"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        try {
//			HotelContactValue.setText(Hotel_contacts.getString("phone_one"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        //construire la chaine de services d'un hotel
//        String TbServices = "";
//        for(int i=0;i<Hotel_services.length();i++){
//			try {
//				TbServices = TbServices + Hotel_services.getJSONObject(i).getString("name_f_r");
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//			HotelServicesValue.setText(TbServices);
//	
//        try {
//			HotelBillingValue.setText(Hotel_billing.getString("price_min"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        HotelStarsValue.setText(Hotel_Star);
//        
//        
//        setContentView(HotelNameValue);
//        setContentView(HotelStarsValue);
//        
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
