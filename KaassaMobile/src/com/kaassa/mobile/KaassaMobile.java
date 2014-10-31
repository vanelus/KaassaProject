package com.kaassa.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kaassa.mobile.lib.gson-2.3.jar;

public class KaassaMobile extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //get the json text -in asset rep
        InputStream is = null;
		try {
			is = this.getAssets().open("apex-hilton.json");
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
          
        String result = str.toString();
        
        Log.i("JsonTest: ",result);
        
        Resources res = getResources();
        TextView vue = (TextView)findViewById(R.id.jsonDisplay);
        vue.setText(result);
        
        // parser le json
        
        InputStream inputStream = str ;
		if(inputStream != null) {
            // Lecture de l'inputStream dans un reader
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Return la liste désérialisé par le moteur gson
             gson.fromJson(reader, new TypeToken<List<Point>>(){}.getType());
        }

    } catch (Exception e) {
        Log.e("WebService", "Impossible de rapatrier les données");
             
    }
    
    public void onStart(Bundle savedInstanceState)
    {
    	
    }
    
    public void onResume(Bundle savedInstanceState)
    {	

    	
    }
    
    public class Cake  
    {  
        public int id;  
        public String name;  
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
