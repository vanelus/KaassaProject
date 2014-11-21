package com.kaassa.mobile;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
        final hotelRecord h1 = i.getParcelableExtra("com.kaassa.mobile.KaassaMobile.hotelRecord");
        
        TextView hotel__detail_name = (TextView)findViewById(R.id.hotel__detail_name);
        hotel__detail_name.setText(h1.HotelName);
        
        TextView hotel_detail_location = (TextView)findViewById(R.id.hotel_detail_location);
        //Define the location of an hotel
        String Location = h1.HotelLocationCityName+" - " +h1.getHotelLocationAddress()+ "("+ h1.HotelLocationCountryName_fr+")";
        hotel_detail_location.setText("Location : "+Location);

        
        TextView hotel_detail_phone = (TextView)findViewById(R.id.hotel_detail_phone);
        hotel_detail_phone.setText(h1.HotelContactPhone_one+" / "+h1.HotelContactPhone_two);
        
        // Get the "hoteldetaillist" services layout
        TextView hotel_detail_services = (TextView)findViewById(R.id.hotel_detail_services);
        String Services =  "";
//        for (int i1=0 ; i1< h1.HotelServicesName_en.size() ; i1++ )
//        	Services = Services + h1.HotelServicesName_en.+";";
        hotel_detail_services.setText(h1.HotelServicesName_en.toString());
        
        final TextView hotel_detail_website = (TextView)findViewById(R.id.hotel_detail_website);
        hotel_detail_website.setText(h1.HotelContactWeb_site);
        
      //Define the minprice in dollar of an hotel
        String minPrice = "From : $ "+h1.HotelBillingPrice_min;
        TextView hotel_detail_pricemin = (TextView)findViewById(R.id.hotel_detail_pricemin);
        hotel_detail_pricemin.setText(minPrice);
        
      // Get the "hoteldetaillist" layout
        View hoteletaillist = (View)findViewById(R.id.hoteletaillist);
        setImageStar (Integer.parseInt(h1.getHotelStars()), hoteletaillist);
        
        hotel_detail_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Uri contactUri = Uri.parse("content://com.android.contacts/contacts");
            	
              //Add the following lines
            	Intent i = new Intent(Intent.ACTION_VIEW);
            	i.setData(Uri.parse(hotel_detail_website.getText().toString()));
            	startActivity(i);
            }
        });
        
        hotel_detail_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Uri contactUri = Uri.parse("tel:"+h1.HotelContactPhone_one);
            	
              //Add the following lines
            	Intent i = new Intent(Intent.ACTION_DIAL,contactUri);
            	
            	startActivity(i);
            }
        });
        
        hotel_detail_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            	Uri contactUri = Uri.parse("tel:"+h1.HotelContactPhone_one);
//            	
//              //Add the following lines
//            	Intent i = new Intent(Intent.ACTION_DIAL,contactUri);
//            	
//            	startActivity(i);
            }
        });
        
    }

    //function to set stars images on "hoteldetaillist" layout
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
    
    public void onStart(Bundle savedInstanceState)
    {
    	
    }
    
    public void onResume(Bundle savedInstanceState)
    {	

    	
    }
}
