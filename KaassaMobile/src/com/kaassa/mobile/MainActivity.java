package com.kaassa.mobile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends ActionBarActivity {
	
	//Search ON. Identify on which object to start the search? The default value is 'Enterprise'
	// possibles values are: Enterprise , School, Hotel, Executive
	String SearchOn = null;
	
	//Search button
	Button _loginBtn;
	
	 // create an access to kaassa database
	private KaassaDatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Instantiate database helper
		databaseHelper = new KaassaDatabaseHelper(this);
		backup();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		

		 
		 
		return true;
	}
	
	
	//Find the current kaassa database and copy it to a current directory with read permission (sdcard for example)
	public void backup() {
	    try {
	        File sdcard = Environment.getExternalStorageDirectory();
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

	 public void GetSearchResult(View view) {
	     // Kabloey
		 
    	 if ( SearchOn == "Hotel")
    	 {
    		 
  		    //Purge de la base de donnée
  		    databaseHelper.purgeHotelTable();
  		    
	    	//Add the following lines
	          Intent intent = new Intent(MainActivity.this, KaassaMobile.class);
	          startActivity(intent);
    	 }
	 }
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch(id) {
			case R.id.action_settings:
				
				
	    	    Intent intent = new Intent(this,AboutKaassa.class);
	    	    startActivity(intent);  
	    	    return true;
			case R.id.action_leaveusmessage:
	           	
              //Add the following lines
            	Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, "test@kassa.com");
            	
             // Verify that the intent will resolve to an activity
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(i, "Choose an Email client :"));
                }
				return true;
		    default:
		        return super.onOptionsItemSelected(item);
            
	    }
		
        	
//		if (id == R.id.action_settings) {
//			
//    	    Intent intent = new Intent(this,AboutKaassa.class);
//  	      	    
//    	    startActivity(intent);      
//
//			
//			return true;
//		}
	}
	
	

	
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radio_Hotel:
	            if (checked)
	            {
	                // Search will be on Hotels
	            	SearchOn = "Hotel";
	            }
	            break;
	        case R.id.radio_school:
	            if (checked)
	            {
	                // Search will be on Hotels
	            	SearchOn = "School";
	            }
	            break;
	            
	        case R.id.radio_enterprise:
	            if (checked)
	            {
	                // Search will be on Hotels
	            	SearchOn = "Enterprise";
	            }
	            break;
	            
	        case R.id.radio_executive:
	            if (checked)
	            {
	                // Search will be on Executives
	            	SearchOn = "Executive";
	            }
	            break;
	    }
	}
	

	
	
}
