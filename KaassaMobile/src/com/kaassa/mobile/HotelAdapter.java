package com.kaassa.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HotelAdapter extends BaseAdapter {


	
	private ArrayList<hotelRecord> hotels = new ArrayList<hotelRecord>();
	
	public HotelAdapter(Context myContext) {
			AssetManager mngr = myContext.getAssets();
			
		    //get the json text -in asset rep
			InputStream is = null;
		    try {
				 is = mngr.open("hotels.json");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		     
	        // check the value
	        int ch;  
	        StringBuffer str = new StringBuffer();  
	          
				try {
					while ((ch = is.read()) != -1)  
					    str.append((char) ch);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

	        String result = str.toString();
	        
	    //    Log.i("JsonTest: ",result);
	        

	        
	        // parser le json pour récupérer la liste des hotels
	        JSONArray reader = null;      
	        try {
				reader = new JSONArray(result);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        // déclaration d'une liste d'hotel bis 
	        String HotelName = null;
	        String HotelCity = null;
	        String HotelCountry = null;
	        String HotelStars = null;
	        
	        for(int i=0;i<reader.length();i++){
	     			try {
	     		        HotelName = reader.getJSONObject(i).getString("name");
	     		        
	     		        HotelCity = reader.getJSONObject(i).getJSONObject("location").getJSONObject("city").getString("name");
	     		        HotelCountry = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").getString("name_e_n");
	     		        HotelStars = reader.getJSONObject(i).getString("stars");
	     		        hotels.add(new hotelRecord(HotelName, HotelCity,HotelCountry,HotelStars));
	     		       Log.i("Hotel name: ",hotels.get(i).getHotelName()+hotels.get(i).getHotelCity()+hotels.get(i).getHotelCountry()+hotels.get(i).getHotelStars());
	     				
	     			} catch (JSONException e) {
	     				// TODO Auto-generated catch block
	     				e.printStackTrace();
	     			}
	             }
		    
	    
		}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return hotels.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return getItem(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.hotellist, parent, false);
		}

		hotelRecord hotel = hotels.get(index);
		
		TextView hotel_name = (TextView)view.findViewById(R.id.hotel_name);
		hotel_name.setText(hotel.getHotelName());
		TextView hotel_city = (TextView)view.findViewById(R.id.hotel_city);
		hotel_city.setText(hotel.getHotelCity());
		TextView hotel_country = (TextView)view.findViewById(R.id.hotel_country);
		hotel_country.setText(hotel.getHotelCountry());
		TextView hotel_stars = (TextView)view.findViewById(R.id.hotel_stars);
		hotel_stars.setText(hotel.getHotelStars());

		return view;
		
		
	}

}
