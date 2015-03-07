package com.kaassa.mobile;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;


public class KaassaMobile extends Activity
{
    HotelAdapter HotelAdapter;
    EditText editsearch;
    ImageView icon_star_1;
    boolean icon_star_1_etat;
    ImageView icon_star_2;
    boolean icon_star_2_etat;
    ImageView icon_star_3;
    boolean icon_star_3_etat;
    ImageView icon_star_4;
    boolean icon_star_4_etat;
    ImageView icon_star_5;
    boolean icon_star_5_etat;
    
    
    public String numofstar_text = "";
    public String countryvalue = "";
    public String cityvalue = "";
    public String checkedServices = "";
    
    public ArrayList<String> countriesValue = new ArrayList<String>();
    public ArrayList<String> citiesValue = new ArrayList<String>();
    public ArrayList<String> servicesValue = new ArrayList<String>();
    
    public Dialog dialog ;
    
    
    public Spinner country; 
    public Spinner city;
    public ListView filtermenu_hotelservices_listview;
	
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
    	    	    Log.i("SomeTag", "h.services: " + h.HotelServicesName_en.toString()); 
    	    	    
    	    	    Intent intent = new Intent(KaassaMobile.this,HotelDetailActivity.class);
    	    	      
    	    	    intent.putExtra("com.kaassa.mobile.KaassaMobile.hotelRecord",h);
    	    	    
    	    	    startActivity(intent);      

    	    	}
    		});

		// Locate the EditText in listview_main.xml
		editsearch = (EditText) findViewById(R.id.search);
 
		// Capture Text in EditText
		editsearch.addTextChangedListener(new TextWatcher() {
 
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
				HotelAdapter.filter(text,getBaseContext());
								
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
 
		});
    	
		
    }

        
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotellist_menu, menu);
		 
		 
		return true;
	} 
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch(id) 
		{
			case R.id.action_filter:
				//Display a dialog to filter the hotellist	
				dialog = new Dialog(this);

                // Include dialog.xml file
                dialog.setContentView(R.layout.dialog_filter);
                // Set dialog title
                dialog.setTitle("Filter Form");
 
                country = (Spinner)dialog.findViewById(R.id.filtermenu_country_spinner);
                city = (Spinner)dialog.findViewById(R.id.filtermenu_city_spinner);       
                filtermenu_hotelservices_listview = (ListView) dialog.findViewById(R.id.filtermenu_hotelservices_listview);
                		
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.checkitemservice, servicesValue);
                filtermenu_hotelservices_listview.setAdapter(adapter);
                filtermenu_hotelservices_listview.setItemsCanFocus(false);
                // we want multiple clicks 
                filtermenu_hotelservices_listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                      
                // Get filter values
                getFilterValue();
                          
                Button cancelButton = (Button) dialog.findViewById(R.id.filtermenu_cancel_button);
                Button submitButton = (Button) dialog.findViewById(R.id.filtermenu_submit_button);
                
                // Get the stars icon View
                icon_star_1 = (ImageView)dialog.findViewById(R.id.filtermenu_iconstar1_imageview);
                icon_star_2 = (ImageView)dialog.findViewById(R.id.filtermenu_iconstar2_imageview);
                icon_star_3 = (ImageView)dialog.findViewById(R.id.filtermenu_iconstar3_imageview);
                icon_star_4 = (ImageView)dialog.findViewById(R.id.filtermenu_iconstar4_imageview);
                icon_star_5 = (ImageView)dialog.findViewById(R.id.filtermenu_iconstar5_imageview);

                // Set Countries Spinner Values
                ArrayAdapter<String> adapterCountries = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,countriesValue);
                country.setAdapter(adapterCountries);
                adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Define a listener on the Country Spinner
                country.setOnItemSelectedListener(new setCitySpinner());
                
                
                RestoreFilterValue(countryvalue, cityvalue, numofstar_text, checkedServices);

                // Display the dialog filter
                dialog.show();
                

                
                // Set OnclickListener on Stars Icon => Call the function "shineStarsIcon" as the callback method
                icon_star_1.setOnClickListener(new shineStarsIcon());
                icon_star_2.setOnClickListener(new shineStarsIcon());
                icon_star_3.setOnClickListener(new shineStarsIcon());
                icon_star_4.setOnClickListener(new shineStarsIcon());
                icon_star_5.setOnClickListener(new shineStarsIcon());
  
                          
                // if decline button is clicked, close the custom dialog
                cancelButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });
                
                submitButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {                        
                        // Close dialog
                        dialog.dismiss();
                    	
                        countryvalue = "";
                        cityvalue = "";
                        checkedServices = "";
                        
                    	// Get selected filter values
                        if (country.getSelectedItem() != null)
                        	countryvalue = country.getSelectedItem().toString();
                        

                        if (city.getSelectedItem() != null)
                        	cityvalue = city.getSelectedItem().toString();
                        
                        //Get check services items
   
                        int cntChoice = filtermenu_hotelservices_listview.getCount();


                        SparseBooleanArray sparseBooleanArray = filtermenu_hotelservices_listview.getCheckedItemPositions();

                        for(int i = 0; i < cntChoice; i++)
                        {

                             if(sparseBooleanArray.get(i) == true) 
                             {
                            	 checkedServices += filtermenu_hotelservices_listview.getItemAtPosition(i).toString() + ",";
                             }
                         }
                                            
                        
                       
                        // Call the function filter by Number of stars, Country, city
                        		
                    	HotelAdapter.filterdialoghotel(countryvalue, cityvalue , checkedServices , numofstar_text, getBaseContext());
                    	//HotelAdapter.filterByStars("3",getBaseContext());
                    	
                    	// reset the dialog filter
                    	//numofstar_text = "";

                    }
                });
                
		    default:
		        return super.onOptionsItemSelected(item);
		}
	 }
		
	public void RestoreFilterValue(String countryValue,String cityValue, String starsValue, String serviceValue)
	{
		
		//Set Country Spinner value
		if (countryValue != "")
		{
			for(int i=0; i < country.getAdapter().getCount(); i++) 
			{
			  if(countryValue.trim().equals(country.getAdapter().getItem(i).toString())){
				  country.setSelection(i);
			    break;
			  }
			}
		}
		//Set city Spinner value
		
//		if (cityValue != "")
//		{
//			for(int i=0; i < city.getAdapter().getCount(); i++) 
//			{
//			  if(cityValue.trim().equals(city.getAdapter().getItem(i).toString())){
//				  city.setSelection(i);
//			    break;
//			  }
//			}
//		}
		
		//Set stars value
		if(starsValue != "")
		{
	      switch(Integer.parseInt(starsValue)) 
	      {
	        case 1:
	          // it was the first star

	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_inactif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "1";
      
	          break;
	          
	        case 2:
	          // it was the second star
	        		icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_actif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "2";

	          break;
	          
	        case 3:
		          // it was the third star

	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_actif);
	                icon_star_3.setImageResource(R.drawable.star_actif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "3";

		      break;
		          
	        case 4:
	          // it was the fourth star

	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_actif);
	                icon_star_3.setImageResource(R.drawable.star_actif);
	                icon_star_4.setImageResource(R.drawable.star_actif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "4";

	          break;
	          
	        case 5:
		          // it was the fifth star
	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_actif);
	                icon_star_3.setImageResource(R.drawable.star_actif);
	                icon_star_4.setImageResource(R.drawable.star_actif);
	                icon_star_5.setImageResource(R.drawable.star_actif);
	                numofstar_text = "5";

		       break;
	      }
			
		}
		
		//Set services value
		if(serviceValue != "")
		{
			String[] serviceValueList = serviceValue.split(",");
			for (int i=0; i < serviceValueList.length ; i++)
			{
				
				
				for(int j=0; j < filtermenu_hotelservices_listview.getAdapter().getCount(); j++) 
				{
					String test = filtermenu_hotelservices_listview.getAdapter().getItem(j).toString();
				  if(serviceValueList[i].equals(filtermenu_hotelservices_listview.getAdapter().getItem(j).toString()))
					  filtermenu_hotelservices_listview.setItemChecked(j, true);
				  
				}
				
			}
		}
			

			
	}
	
	
	public void getFilterValue()
	{
		// Go through all the displayed hotels
		for (int i=0; i < HotelAdapter.getCount() ; i++)
		{
			countriesValue.clear();
			countriesValue.add("");
			//Get Countries Values
			if (!(countriesValue.toString().contains(HotelAdapter.getItem(i).HotelLocationCountryName_fr)))
				countriesValue.add(HotelAdapter.getItem(i).HotelLocationCountryName_fr);
		
			//Get Services Values
			String [] serviceHotel = HotelAdapter.getItem(i).HotelServicesName_en.split(",") ;
			for (int j =0 ; j < serviceHotel.length ; j++)
			{
				if (!(servicesValue.toString().contains(serviceHotel[j])))
					servicesValue.add(serviceHotel[j]);
			
			}
		}
	}
    

	
	public class setCitySpinner implements OnItemSelectedListener 
	{

        @Override
       public void onItemSelected(AdapterView<?> parent, View v, int pos,
               long id) 
        {

           //Get Selected Country
           if (parent.getSelectedItem() != null)
           {		
        	   
      			citiesValue.clear();
      			citiesValue.add("");
      			
	       		// Get city values
	       		for (int i=0; i < HotelAdapter.getCount() ; i++)
	       		{    

	       			//Get Cities Values
	       			if (!(citiesValue.toString().contains(HotelAdapter.getItem(i).HotelLocationCityName)) && parent.getSelectedItem().toString().contains(HotelAdapter.getItem(i).HotelLocationCountryName_fr))
	       				citiesValue.add(HotelAdapter.getItem(i).HotelLocationCityName);
	       		
	           }
        	   
	       	   if (citiesValue != null)
	       	   {
	       		   
	               // Set Cities Spinner Values
		           city = (Spinner)dialog.findViewById(R.id.filtermenu_city_spinner);
	               ArrayAdapter<String> adapterCities = new ArrayAdapter<String>(KaassaMobile.this,android.R.layout.simple_spinner_item, citiesValue);
	               city.setAdapter(adapterCities);
	               adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	       	   }

           }

       }
       
       public void onNothingSelected(AdapterView<?> arg0) {
           // TODO Auto-generated method stub

       }
   }
	
	public class shineStarsIcon implements OnClickListener 
	{
		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub
			
	      switch(v.getId()) 
	      {
	        case R.id.filtermenu_iconstar1_imageview:
	          // it was the first image
	            if (!icon_star_1_etat)
	            {
	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_inactif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "1";
	                icon_star_1_etat = true;
	            } else
	            {
	                icon_star_1.setImageResource(R.drawable.star_inactif);
	                icon_star_2.setImageResource(R.drawable.star_inactif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "";
	                icon_star_1_etat = false;
	            }
	            
	          break;
	          
	        case R.id.filtermenu_iconstar2_imageview:
	          // it was the second image
	            if (!icon_star_2_etat)
	            {
	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_actif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "2";
	                icon_star_2_etat = true;
	            } else
	            {
	                icon_star_1.setImageResource(R.drawable.star_inactif);
	                icon_star_2.setImageResource(R.drawable.star_inactif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "";
	                icon_star_2_etat = false;
	            }
	          break;
	          
	        case R.id.filtermenu_iconstar3_imageview:
		          // it was the third image
	        	
	            if (!icon_star_3_etat)
	            {
	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_actif);
	                icon_star_3.setImageResource(R.drawable.star_actif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "3";
	                icon_star_3_etat = true;
	            } else
	            {
	                icon_star_1.setImageResource(R.drawable.star_inactif);
	                icon_star_2.setImageResource(R.drawable.star_inactif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "";
	                icon_star_3_etat = false;
	            }
	            
		          break;
		          
	        case R.id.filtermenu_iconstar4_imageview:
		          // it was the fourth image

	            if (!icon_star_4_etat)
	            {
	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_actif);
	                icon_star_3.setImageResource(R.drawable.star_actif);
	                icon_star_4.setImageResource(R.drawable.star_actif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "4";
	                icon_star_4_etat = true;
	            } else
	            {
	                icon_star_1.setImageResource(R.drawable.star_inactif);
	                icon_star_2.setImageResource(R.drawable.star_inactif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "";
	                icon_star_4_etat = false;
	            }
	            
		          break;
		          
	        case R.id.filtermenu_iconstar5_imageview:
		          // it was the fifth image
	            if (!icon_star_5_etat)
	            {
	                icon_star_1.setImageResource(R.drawable.star_actif);
	                icon_star_2.setImageResource(R.drawable.star_actif);
	                icon_star_3.setImageResource(R.drawable.star_actif);
	                icon_star_4.setImageResource(R.drawable.star_actif);
	                icon_star_5.setImageResource(R.drawable.star_actif);
	                numofstar_text = "5";
	                icon_star_5_etat = true;
	            } else
	            {
	                icon_star_1.setImageResource(R.drawable.star_inactif);
	                icon_star_2.setImageResource(R.drawable.star_inactif);
	                icon_star_3.setImageResource(R.drawable.star_inactif);
	                icon_star_4.setImageResource(R.drawable.star_inactif);
	                icon_star_5.setImageResource(R.drawable.star_inactif);
	                numofstar_text = "";
	                icon_star_5_etat = false;
	            }
		          break;
	      }
		      

		}

   }
	
    public void onStart(Bundle savedInstanceState)
    {
    	
    }
    
    public void onResume(Bundle savedInstanceState)
    {	

    	
    }
   
}
