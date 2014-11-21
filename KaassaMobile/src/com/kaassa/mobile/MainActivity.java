package com.kaassa.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends ActionBarActivity {
	
	//Search ON. Identify on which object to start the search? The default value is 'Enterprise'
	// possibles values are: Enterprise , School, Hotel
	String SearchOn = null;
	
	//Search button
	Button _loginBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		
		
		//Get Button view and set onClickListener
		_loginBtn = ( Button ) findViewById(R.id.btn_login);
		
		_loginBtn.setOnClickListener(new View.OnClickListener() {
		      @Override
		public void onClick(View v) {
		        //This is a comment which does no good to your code. Feel free to remove it after you copy paste.
		        //When the button is clicked, the control will come to this method.
		        //To demonstrate this, let us try changing the label of the Button from 'Login' to 'I am clicked'
		        
		    	 if ( SearchOn == "Hotel")
		    	 {
			    	//Add the following lines
			          Intent intent = new Intent(MainActivity.this, KaassaMobile.class);
			          startActivity(intent);
		    	 }
		      } 
		  });
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
	    }
	}
	

	
	
}
