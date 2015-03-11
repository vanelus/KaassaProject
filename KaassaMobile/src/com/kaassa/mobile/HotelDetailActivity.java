package com.kaassa.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class HotelDetailActivity extends Activity {
	
	
	//initialise an Hotel Record
	String HotelName="";
	String HotelLocationAddress="";
	String HotelLocationCityName="";
	String HotelLocationCityIs_top="";
	String HotelLocationCountryName_fr="";
	String HotelLocationCountryName_en="";
	String HotelLocationPresentation_fr="";
	String HotelLocationPresentation_en="";
	String HotelContactEmail="";
	String HotelContactPhone_one="";
	String HotelContactPhone_two="";
	String HotelContactWeb_site="";
	String HotelBillingPrice_min="";
	String HotelBillingFrequency="";
	String HotelStars="";
	String HotelServicesName_en = "";
	
    @Override
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoteldetaillist);
        
        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Puis on récupère l'objet hotel selectionné dans l'autre activité, ou 0 si cet extra n'est pas dans l'intent
        final hotelRecord h1 = i.getParcelableExtra("com.kaassa.mobile.KaassaMobile.hotelRecord");

    	
    	if (h1.HotelName !=null){HotelName = h1.HotelName;}
    	if (h1.HotelLocationAddress !=null){HotelLocationAddress = h1.HotelLocationAddress;}
    	if (h1.HotelLocationCityName !=null){HotelLocationCityName = h1.HotelLocationCityName;}
    	if (h1.HotelLocationCityIs_top !=null){HotelLocationCityIs_top = h1.HotelLocationCityIs_top;}
    	if (h1.HotelLocationCountryName_fr !=null){HotelLocationCountryName_fr = h1.HotelLocationCountryName_fr;}
    	if (h1.HotelLocationCountryName_en !=null){HotelLocationCountryName_en = h1.HotelLocationCountryName_en;}
    	if (h1.HotelLocationPresentation_fr !=null){HotelLocationPresentation_fr = h1.HotelLocationPresentation_fr;}
    	if (h1.HotelLocationPresentation_en !=null){HotelLocationPresentation_en = h1.HotelLocationPresentation_en;}
    	if (h1.HotelContactEmail !=null){HotelContactEmail = h1.HotelContactEmail;}
    	if (h1.HotelContactPhone_one !=null){HotelContactPhone_one = h1.HotelContactPhone_one;}
    	if (h1.HotelContactPhone_two !=null){HotelContactPhone_two = h1.HotelContactPhone_two;}
    	if (h1.HotelContactWeb_site !=null){HotelContactWeb_site = h1.HotelContactWeb_site;}
    	if (h1.HotelBillingPrice_min !=null){HotelBillingPrice_min = h1.HotelBillingPrice_min;}
    	if (h1.HotelBillingFrequency !=null){HotelBillingFrequency = h1.HotelBillingFrequency;}
    	if (h1.HotelStars !=null){HotelStars = h1.HotelStars;}
    	if (h1.HotelServicesName_en !=null){HotelServicesName_en = h1.getHotelServicesName_en();;}
    	
    	
        TextView hotel__detail_name = (TextView)findViewById(R.id.hotel__detail_name);
        hotel__detail_name.setText(HotelName);
        
        TextView hotel_detail_location = (TextView)findViewById(R.id.hotel_detail_location);
        //Define the location of an hotel
        String Location = HotelLocationCityName+" - " +HotelLocationAddress+ "("+ HotelLocationCountryName_fr+")";
        hotel_detail_location.setText(getResources().getText(R.string.HotelDetail_Textview_locationLabel) + Location);

        
        TextView hotel_detail_phone = (TextView)findViewById(R.id.hotel_detail_phone);

        
        // Use the library’s functions
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phNumberProto1 = null;
        PhoneNumber phNumberProto2= null;
        
        // I set the default region to FR (France)
        // You can find your country code here http://www.iso.org/iso/country_names_and_code_elements
        try {
        	if (HotelContactPhone_one != null)
        		phNumberProto1 = phoneUtil.parse(HotelContactPhone_one,"FR");
        	if (HotelContactPhone_two != null)
        		phNumberProto2 = phoneUtil.parse(HotelContactPhone_two,"FR");
		} catch (NumberParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String internationalFormat1 = "";
        String internationalFormat2 = "";
        
	    // get the valid number’s international format
        if (phNumberProto1 != null && phNumberProto2 != null)
        {
    	    internationalFormat1 = phoneUtil.format( phNumberProto1, PhoneNumberFormat.INTERNATIONAL);
    	    internationalFormat2 = phoneUtil.format( phNumberProto2, PhoneNumberFormat.INTERNATIONAL);
        }

        hotel_detail_phone.setText(internationalFormat1+" / "+internationalFormat2);
        
        TextView hotel_detail_email = (TextView)findViewById(R.id.hotel_detail_email);
        hotel_detail_email.setText(HotelContactEmail);
        
        // Get the "hoteldetaillist" services layout
//        TextView hotel_detail_services = (TextView)findViewById(R.id.hotel_detail_services);
//        String Services =  "";
////        for (int i1=0 ; i1< HotelServicesName_en.size() ; i1++ )
////        	Services = Services + HotelServicesName_en.+";";
//        hotel_detail_services.setText(HotelServicesName_en.toString());
//        
        final TextView hotel_detail_website = (TextView)findViewById(R.id.hotel_detail_website);
        hotel_detail_website.setText(HotelContactWeb_site);
        
      //Define the minprice in dollar of an hotel
        String minPrice = getResources().getText(R.string.HotelDetail_Textview_priceminLabel)+"$ "+HotelBillingPrice_min;
        TextView hotel_detail_pricemin = (TextView)findViewById(R.id.hotel_detail_pricemin);
        hotel_detail_pricemin.setText(minPrice);
        
      // Get the "hoteldetaillist" layout
        View hoteletaillist = findViewById(R.id.hoteletaillist);
        int numberOfStars = 0;
        if (HotelStars != null)
        	numberOfStars=Integer.parseInt(HotelStars); 
        setImageStar (numberOfStars, hoteletaillist);
        
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
            	Uri contactUri = Uri.parse("tel:"+HotelContactPhone_one);
            	
              //Add the following lines
            	Intent i = new Intent(Intent.ACTION_DIAL,contactUri);
            	
            	startActivity(i);
            }
        });
        
        hotel_detail_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
              //Add the following lines
            	Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, HotelContactEmail);
            	
             // Verify that the intent will resolve to an activity
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(i, "Choose an Email client :"));
                }
            }
        });
        
        hotel_detail_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            	Uri contactUri = Uri.parse("tel:"+HotelContactPhone_one);
//            	
//              //Add the following lines
//            	Intent i = new Intent(Intent.ACTION_DIAL,contactUri);
//            	
//            	startActivity(i);
            }
        });
        
      //Get the services list layout  
        ListView services_list = (ListView) findViewById(R.id.services_list);
        

        int lastid= (HotelServicesName_en.length() - 2);
        String servicesList = HotelServicesName_en.substring(1, lastid );
        String[] servicesList1 = servicesList.split(",");
        		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.gridviewitemservice, servicesList1);
        services_list.setAdapter(adapter);
        

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
		
		
		// Start Display image activty
		//get the "Display Image" Button
		Button displayHotelPhoto = (Button)findViewById(R.id.displayHotelPhoto);
		displayHotelPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getBaseContext(), ImageGridFragment.class));
			}
		});
		
    }
    
    public void onStart(Bundle savedInstanceState)
    {
    	
    }
    
    public void onResume(Bundle savedInstanceState)
    {	

    	
    }
    

}
