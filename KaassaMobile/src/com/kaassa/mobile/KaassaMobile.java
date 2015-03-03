package com.kaassa.mobile;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


public class KaassaMobile extends Activity
{
	HotelAdapter HotelAdapter;
	EditText editsearch;
	
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
//				//Display a dialog to filter the hotellist	
//
//                final Dialog dialog = new Dialog(KaassaMobile.this);
//                // Include dialog.xml file
//                dialog.setContentView(R.layout.dialog_filter);
//                // Set dialog title
//                dialog.setTitle("Filter Form");
// 
//                        
//                final ListView dialog_services_list = (ListView) dialog.findViewById(R.id.dialog_services_list);
//
//                String[] servicesList1 = {"Air-conditioning","Beauty Parlor/Hairdressing","Car rental","Casino","Fitness/Gym/Spa","Internet/Wi-Fi","Laundry/Dry cleaning","Meeting room","Nightclub","Parking","Pets allowed","Restaurant","Room service","Safe","Satelitte TV","Secretarial/Business Center","Snack-Bar","Store / Gift shop","Swimming pool","Tennis"};
//                		
//                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.checkitemservice, servicesList1);
//                dialog_services_list.setAdapter(adapter);
//                dialog_services_list.setItemsCanFocus(false);
//                // we want multiple clicks 
//                dialog_services_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//                
//                // Display the dialog filter
//                dialog.show();
//                 
//                Button cancelButton = (Button) dialog.findViewById(R.id.filtermenu_cancel_button);
//                Button submitButton = (Button) dialog.findViewById(R.id.filtermenu_submit_button);
//                // if decline button is clicked, close the custom dialog
//                cancelButton.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Close dialog
//                        dialog.dismiss();
//                    }
//                });
//                
//                submitButton.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Filter HotelList
//                        
//                        // Close dialog
//                        dialog.dismiss();
//                    	
//                    	// Get Filer attributes values
//                    	//EditText numofstar = (EditText) findViewById(R.id.dialog_filter_numofstars_edittext);
////                        EditText numofstar = (EditText)dialog.findViewById(R.id.dialog_filter_numofstars_edittext);
////                        EditText country = (EditText)dialog.findViewById(R.id.dialog_filter_country_edittext);
////                        EditText city = (EditText)dialog.findViewById(R.id.dialog_filter_city_edittext);
////                    	
//                        //Get check services items
//    
//                        int cntChoice = dialog_services_list.getCount();
//
//                        String checkedServices = "";
//
//                        SparseBooleanArray sparseBooleanArray = dialog_services_list.getCheckedItemPositions();
//
//                        for(int i = 0; i < cntChoice; i++)
//                        {
//
//                             if(sparseBooleanArray.get(i) == true) 
//                             {
//                            	 checkedServices += dialog_services_list.getItemAtPosition(i).toString() + ",";
//                             }
//                         }
//                                            
//                        
//                        
////                        String numofstar_text = numofstar.getText().toString();
////                        String country_text = country.getText().toString();
////                        String city_text = city.getText().toString();
//                    	
//                        		
//                        // Call the function filter by Number of stars, Country, city
//                        		
//                    //	HotelAdapter.filterByStars(country_text, numofstar_text,checkedServices, getBaseContext());
//                    	//HotelAdapter.filterByStars("3",getBaseContext());
//                    }
//                });
		    default:
		        return super.onOptionsItemSelected(item);
		}
	 }
		

	
    
    public void onStart(Bundle savedInstanceState)
    {
    	
    }
    
    public void onResume(Bundle savedInstanceState)
    {	

    	
    }
   
}
