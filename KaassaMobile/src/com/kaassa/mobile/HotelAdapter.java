package com.kaassa.mobile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HotelAdapter extends BaseAdapter {
	
	 // create an access to kaassa database
	private KaassaDatabaseHelper databaseHelper;
	
	private ArrayList<hotelRecord> hotels = new ArrayList<hotelRecord>();
	private ArrayList<hotelRecord> savehotels = new ArrayList<hotelRecord>();
	
	//initialise an Hotel Record
	private String HotelName=null;
	private String HotelLocationAddress=null;
	private String HotelLocationCityName=null;
	private String HotelLocationCityIs_top=null;
	private String HotelLocationCountryName_fr=null;
	private String HotelLocationCountryName_en=null;
	private String HotelLocationPresentation_fr=null;
	private String HotelLocationPresentation_en=null;
	private String HotelContactEmail=null;
	private String HotelContactPhone_one=null;
	private String HotelContactPhone_two=null;
	private String HotelContactWeb_site=null;
	private String HotelBillingPrice_min=null;
	private String HotelBillingFrequency=null;
	private String HotelStars=null;
	private ArrayList<String> HotelServicesName_fr = new ArrayList<String>();
	private ArrayList<String> HotelServicesName_en = new ArrayList<String>();
	
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
	        

			
			//Instantiate database helper
	    	databaseHelper = new KaassaDatabaseHelper(myContext);
	    	
	    	Log.i("numHotel: ",String.valueOf(reader.length()));
	        
	        for(int i=0;i<reader.length();i++)
	        {
 		        try {
					HotelName = reader.getJSONObject(i).optString("name");
					HotelLocationAddress = reader.getJSONObject(i).getJSONObject("location").optString("address");
					HotelLocationCityName = reader.getJSONObject(i).getJSONObject("location").getJSONObject("city").optString("name");
					HotelLocationCityIs_top = reader.getJSONObject(i).getJSONObject("location").getJSONObject("city").optString("is_top");
					HotelLocationCountryName_fr = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").optString("name_f_r");
					HotelLocationCountryName_en = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").optString("name_e_n");
					HotelLocationPresentation_fr = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").optString("presentation_f_r");
					HotelLocationPresentation_en = reader.getJSONObject(i).getJSONObject("location").getJSONObject("country").optString("presentation_e_n");
					HotelContactEmail = reader.getJSONObject(i).getJSONObject("contact").optString("email");
					HotelContactPhone_one = reader.getJSONObject(i).getJSONObject("contact").optString("phone_one");
					HotelContactPhone_two = reader.getJSONObject(i).getJSONObject("contact").optString("phone_two");
					HotelContactWeb_site = reader.getJSONObject(i).getJSONObject("contact").optString("web_site");
					
					JSONArray HotelServicesTb = reader.getJSONObject(i).getJSONArray("services");

       
			       for(int j=0;j<HotelServicesTb.length();j++)
			       {
						  HotelServicesName_fr.add(HotelServicesTb.getJSONObject(j).optString("name_f_r"));
						  HotelServicesName_en.add(HotelServicesTb.getJSONObject(j).optString("name_e_n"));
			
			       }
     
					HotelBillingPrice_min = reader.getJSONObject(i).getJSONObject("billing").optString("price_min");
					HotelBillingFrequency = reader.getJSONObject(i).getJSONObject("billing").optString("frequency");	     		        
					HotelStars = reader.getJSONObject(i).optString("stars");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     		        
     		        hotels.add(i, new hotelRecord(HotelName,HotelLocationAddress,HotelLocationCityName,HotelLocationCountryName_fr,HotelStars,HotelContactEmail,HotelContactPhone_one,HotelContactPhone_two,HotelContactWeb_site,HotelBillingPrice_min,HotelServicesName_en.toString()));
	     		    Log.i("Hotel record: ", HotelName);
	     		    Log.i("Hotel stars: ", String.valueOf(HotelStars));
     		        

	     		    
     		        // Save hotel to Kaassa Database
	     		    databaseHelper.saveHotelRecord(HotelName,HotelLocationAddress,HotelLocationCityName,HotelLocationCountryName_fr,HotelStars,HotelContactEmail,HotelContactPhone_one,HotelContactPhone_two,HotelContactWeb_site,HotelBillingPrice_min,HotelServicesName_en.toString());
	     		    backup();

	     		    //Réintialiser un record Hotel
	     		   initHotelrecod();
	 
	             }
	        Log.i("numHotel2: ",String.valueOf(hotels.size()));
		    
	    
		}
	
	//initialise an Hotel Record
	public void initHotelrecod(){
		
    	 HotelName=null;
    	 HotelLocationAddress=null;
    	 HotelLocationCityName=null;
    	 HotelLocationCityIs_top=null;
    	 HotelLocationCountryName_fr=null;
    	 HotelLocationCountryName_en=null;
    	 HotelLocationPresentation_fr=null;
    	 HotelLocationPresentation_en=null;
    	 HotelContactEmail=null;
    	 HotelContactPhone_one=null;
    	 HotelContactPhone_two=null;
    	 HotelContactWeb_site=null;
    	 HotelBillingPrice_min=null;
    	 HotelBillingFrequency=null;
    	 HotelStars=null;
    	 HotelServicesName_fr.clear();
    	 HotelServicesName_en.clear();
	}
	
	
	//Find the current kaassa database and copy it to a current directory with read permission (sdcard for example)
	public void backup() {
	    try {
	        Environment.getExternalStorageDirectory();
	        File outputFile = new File("/sdcard/Pictures",
	                "kaassa.bak");

	        if (!outputFile.exists()) 
	             outputFile.createNewFile(); 

	        File data = Environment.getDataDirectory();
	        File inputFile = new File(data, "data/com.kaassa.mobile/databases/kaassa.db");
	        InputStream input = new FileInputStream(inputFile);
	        OutputStream output = new FileOutputStream(outputFile);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = input.read(buffer)) > 0) {
	            output.write(buffer, 0, length);
	        }
	        output.flush();
	        output.close();
	        input.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new Error("Copying Failed");
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
		int numberOfStars = 0;
		if (hotel.HotelStars != null)
			numberOfStars=Integer.parseInt(hotel.HotelStars); 
		setImageStar(numberOfStars,view);
		return view;
		
		
	}
	
	// Filter Hotels By Name
		public void filter(final String charText, Context myContext) {
			final String charText2 = charText.toLowerCase(Locale.getDefault());
			
			
			//Get hotels list from database
			hotels.clear();
			Cursor cursor = databaseHelper.getAllHotelRecords();
			String HotelServicesName_en;
			
			if (cursor.moveToFirst()) 
			{
				do 
				{
					if(cursor.getString(1).toLowerCase(Locale.getDefault()).contains(charText2))
					{
						 HotelName = cursor.getString(1);
						 HotelLocationAddress = cursor.getString(2);
						 HotelLocationCityName = cursor.getString(3);
						 HotelLocationCountryName_fr = cursor.getString(4);
						 HotelStars = cursor.getString(5);
						 HotelContactEmail = cursor.getString(6);
						 HotelContactPhone_one = cursor.getString(7);
						 HotelContactPhone_two = cursor.getString(8);
						 HotelContactWeb_site = cursor.getString(9);
						 HotelServicesName_en = cursor.getString(10);
						
						 hotels.add(new hotelRecord(HotelName,HotelLocationAddress,HotelLocationCityName,HotelLocationCountryName_fr,HotelStars,HotelContactEmail,HotelContactPhone_one,HotelContactPhone_two,HotelContactWeb_site,HotelBillingPrice_min,HotelServicesName_en));
		
					
					}
				} while (cursor.moveToNext());
			}
			
			// Fermeture du curseur
			if (!cursor.isClosed()) {
			cursor.close();
			}

			
			notifyDataSetChanged();
		}
		
		// Function: Filter a hotels list by some parameters : country, city, stars, services
		
	    public void filterdialoghotel(String country, String  city, String services, String stars, Context context)
	    {
	        hotels.clear();
	        Cursor cursor = databaseHelper.getAllHotelRecords();
	        if (city.equals("") && country.equals("") && stars.equals("") && services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                CreateHotelListByCursor(cursor);
	            } while (cursor.moveToNext());
	        }
	        if (city.equals("") && country.equals("") && stars.equals("") && !services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(11).contains(services))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (city.equals("") && country.equals("") && !stars.equals("") && services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(5).contains(stars))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (city.equals("") && country.equals("") && !stars.equals("") && !services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(5).contains(stars) && cursor.getString(11).contains(services))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (city.equals("") && !country.equals("") && stars.equals("") && services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(4).contains(country))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (city.equals("") && !country.equals("") && stars.equals("") && !services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(4).contains(country) && cursor.getString(11).contains(services))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (city.equals("") && !country.equals("") && !stars.equals("") && services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(4).contains(country) && cursor.getString(5).contains(stars))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (city.equals("") && country.equals("") && stars.equals("") && services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(4).contains(country) && cursor.getString(5).contains(stars) && cursor.getString(11).contains(services))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (!city.equals("") && !country.equals("") && stars.equals("") && services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(3).contains(city) && cursor.getString(4).contains(country))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (!city.equals("") && !country.equals("") && stars.equals("") && !services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(3).contains(city) && cursor.getString(4).contains(country) && cursor.getString(11).contains(services))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (!city.equals("") && !country.equals("") && !stars.equals("") && services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(3).contains(city) && cursor.getString(4).contains(country) && cursor.getString(5).contains(stars))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (!city.equals("") && !country.equals("") && !stars.equals("") && !services.equals("") && cursor.moveToFirst())
	        {
	            do
	            {
	                if (cursor.getString(5).equals(stars) && cursor.getString(4).equals(country) && cursor.getString(11).contains(services))
	                {
	                    CreateHotelListByCursor(cursor);
	                }
	            } while (cursor.moveToNext());
	        }
	        if (!cursor.isClosed())
	        {
	            cursor.close();
	        }
	        notifyDataSetChanged();
	    }
		
	// Create Hotellist by cursor
	public void CreateHotelListByCursor (Cursor cursor)
	{
		String HotelName = cursor.getString(1);
		String	HotelLocationAddress = cursor.getString(2);
		String HotelLocationCityName = cursor.getString(3);
		String HotelLocationCountryName_fr = cursor.getString(4);
		String HotelStars = cursor.getString(5);
		String HotelContactEmail = cursor.getString(6);
		String HotelContactPhone_one = cursor.getString(7);
		String HotelContactPhone_two = cursor.getString(8);
		String HotelContactWeb_site = cursor.getString(9);
		String HotelBillingPrice_min = cursor.getString(10);
		String HotelServicesName_en = cursor.getString(11);
		
		 hotels.add(new hotelRecord(HotelName,HotelLocationAddress,HotelLocationCityName,HotelLocationCountryName_fr,HotelStars,HotelContactEmail,HotelContactPhone_one,HotelContactPhone_two,HotelContactWeb_site,HotelBillingPrice_min,HotelServicesName_en));
	}
}
