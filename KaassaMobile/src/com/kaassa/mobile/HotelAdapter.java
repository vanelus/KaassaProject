package com.kaassa.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
	    	String HotelName=null;
	    	String HotelLocationAddress=null;
	    	String HotelLocationCityName=null;
	    	String HotelLocationCityIs_top=null;
	    	String HotelLocationCountryName_fr=null;
	    	String HotelLocationCountryName_en=null;
	    	String HotelLocationPresentation_fr=null;
	    	String HotelLocationPresentation_en=null;
	    	String HotelContactPhone_one=null;
	    	String HotelContactPhone_two=null;
	    	String HotelContactWeb_site=null;
	    	String HotelBillingPrice_min=null;
	    	String HotelBillingFrequency=null;
	    	String HotelStars=null;
	    	ArrayList<String> HotelServicesName_fr = new ArrayList<String> () ;
	    	ArrayList<String> HotelServicesName_en = new ArrayList<String> ();
	        
	        for(int i=0;i<reader.length();i++){
	     			try {
	     		        HotelName = reader.getJSONObject(i).getString("name");
	     		        HotelLocationAddress = reader.getJSONObject(i).getJSONObject("location").getString("address");
	     		        HotelLocationCityName = reader.getJSONObject(i).getJSONObject("location").getJSONObject("city").getString("name");
	     		        HotelLocationCityIs_top = reader.getJSONObject(i).getJSONObject("location").getJSONObject("city").getString("is_top");
	     		        HotelLocationCountryName_fr = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").getString("name_f_r");
	     		        HotelLocationCountryName_en = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").getString("name_e_n");
	     		        HotelLocationPresentation_fr = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").getString("presentation_f_r");
	     		        HotelLocationPresentation_en = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").getString("presentation_e_n");
	     		        HotelContactPhone_one = reader.getJSONObject(i).getJSONObject("contact").getString("phone_one");
	     		        HotelContactPhone_two = reader.getJSONObject(i).getJSONObject("contact").getString("phone_two");
	     		        HotelContactWeb_site = reader.getJSONObject(i).getJSONObject("contact").getString("web_site");
	     		        
	     		       JSONArray HotelServicesTb = reader.getJSONObject(i).getJSONArray("services");

	     		       
	     		       for(int j=0;j<HotelServicesTb.length();j++){
	     		    	 // HotelServicesName_fr [j] = HotelServicesTb.getJSONObject(j).getString("name_f_r");
	     		    	// HotelServicesName_en [j] = HotelServicesTb.getJSONObject(j).getString("name_e_n");
	     		    	  HotelServicesName_fr.add(HotelServicesTb.getJSONObject(j).getString("name_f_r"));
	     		    	  HotelServicesName_en.add(HotelServicesTb.getJSONObject(j).getString("name_e_n"));

	     		       }
	     		     
	     		        HotelBillingPrice_min = reader.getJSONObject(i).getJSONObject("billing").getString("price_min");
	     		     	HotelBillingFrequency = reader.getJSONObject(i).getJSONObject("billing").getString("frequency");	     		        
	     		        HotelStars = reader.getJSONObject(i).getString("stars");
	     		        
	     		        hotels.add(new hotelRecord(HotelName,HotelLocationAddress,HotelLocationCityName,HotelLocationCountryName_fr,HotelStars,HotelContactPhone_one,HotelContactPhone_two,HotelContactWeb_site,HotelBillingPrice_min,HotelServicesName_en));
	     		     //   Log.i("Hotel parameters: ",HotelServicesTb.toString());
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
	public hotelRecord getItem(int index) {
		// TODO Auto-generated method stub
		return hotels.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	public void setImageStar (int Nbstars, View view){

		ImageView icon_star_1 = (ImageView)view.findViewById(R.id.icon_star_1);
		ImageView icon_star_2 = (ImageView)view.findViewById(R.id.icon_star_2);
		ImageView icon_star_3 = (ImageView)view.findViewById(R.id.icon_star_3);
		ImageView icon_star_4 = (ImageView)view.findViewById(R.id.icon_star_4);
		ImageView icon_star_5 = (ImageView)view.findViewById(R.id.icon_star_5);
		
		//set stars image related to the number of stars.example: 2 stars ** ; 3 stars ***
		switch(Nbstars) {
	    case 1:
	    	icon_star_1.setImageResource(R.drawable.star_actif);
	    	icon_star_2.setImageResource(R.drawable.star_inactif);
	    	icon_star_3.setImageResource(R.drawable.star_inactif);
	    	icon_star_4.setImageResource(R.drawable.star_inactif);
	    	icon_star_5.setImageResource(R.drawable.star_inactif);
			
	        break;
	    case 2:
	    	icon_star_1.setImageResource(R.drawable.star_actif);
	    	icon_star_2.setImageResource(R.drawable.star_actif);
	    	icon_star_3.setImageResource(R.drawable.star_inactif);
	    	icon_star_4.setImageResource(R.drawable.star_inactif);
	    	icon_star_5.setImageResource(R.drawable.star_inactif);
	        break;
	    case 3:
	    	icon_star_1.setImageResource(R.drawable.star_actif);
	    	icon_star_2.setImageResource(R.drawable.star_actif);
	    	icon_star_3.setImageResource(R.drawable.star_actif);
	    	icon_star_4.setImageResource(R.drawable.star_inactif);
	    	icon_star_5.setImageResource(R.drawable.star_inactif);
	        break;
	    case 4:
	    	icon_star_1.setImageResource(R.drawable.star_actif);
	    	icon_star_2.setImageResource(R.drawable.star_actif);
	    	icon_star_3.setImageResource(R.drawable.star_actif);
	    	icon_star_4.setImageResource(R.drawable.star_actif);
	    	icon_star_5.setImageResource(R.drawable.star_inactif);
	        break;
	    case 5:
	    	icon_star_1.setImageResource(R.drawable.star_actif);
	    	icon_star_2.setImageResource(R.drawable.star_actif);
	    	icon_star_3.setImageResource(R.drawable.star_actif);
	    	icon_star_4.setImageResource(R.drawable.star_actif);
	    	icon_star_5.setImageResource(R.drawable.star_actif);
	        break;
	    default:
	    	icon_star_1.setImageResource(R.drawable.star_inactif);
	    	icon_star_2.setImageResource(R.drawable.star_inactif);
	    	icon_star_3.setImageResource(R.drawable.star_inactif);
	    	icon_star_4.setImageResource(R.drawable.star_inactif);
	    	icon_star_5.setImageResource(R.drawable.star_inactif);
	}
		
	}
	
	
	@Override
	public View getView(int index, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.hotellist3, parent, false);
		}

		hotelRecord hotel = hotels.get(index);
		
		TextView hotel_name = (TextView)view.findViewById(R.id.hotel_name);
		hotel_name.setText(hotel.getHotelName());
		TextView hotel_city = (TextView)view.findViewById(R.id.hotel_city);
		hotel_city.setText(hotel.getHotelLocationCityName());
		TextView hotel_country = (TextView)view.findViewById(R.id.hotel_country);
		hotel_country.setText(hotel.getHotelLocationCountryName_fr());

		//set stars image
		setImageStar(Integer.parseInt(hotel.getHotelStars()),view);
		return view;
		
		
	}

}
