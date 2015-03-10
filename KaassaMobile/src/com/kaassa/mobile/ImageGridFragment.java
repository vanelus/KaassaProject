package com.kaassa.mobile;
 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class ImageGridFragment extends Activity
{    
    //---the images to display--- 
    String[] urls = {
    		"http://www.metropolehotel.com/files/gallery/pics/hotel_metropole_pdv_04042012_070.jpg",
    		"http://www.metropolehotel.com/files/gallery/pics/003.jpg",
    		"http://www.metropolehotel.com/files/gallery/pics/0001.jpg",
    		"http://www.metropolehotel.com/files/gallery/pics/metropole_rubinstein_banquet-125.jpg"	
    		
    };
    

    @Override    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayview);
        
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
 
        gallery.setAdapter(new ImageAdapter(this));        
        gallery.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView parent, 
            View v, int position, long id) 
            {                
                Toast.makeText(getBaseContext(), 
                        "pic" + (position + 1) + " selected", 
                        Toast.LENGTH_SHORT).show();
                
                //---display the images selected---
                ImageView imageView = (ImageView) findViewById(R.id.image1);                
                //imageView.setImageResource(imageIDs[position]);
                BitmapWorkerTask task = new BitmapWorkerTask(imageView);
                task.execute(urls[position]);
            }
        });
    }
 
    public class ImageAdapter extends BaseAdapter 
    {
        private Context context;
        private int itemBackground;
 
        public ImageAdapter(Context c) 
        {
            context = c;
            //---setting the style---
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
            itemBackground = a.getResourceId(
                R.styleable.Gallery1_android_galleryItemBackground, 0);
            a.recycle();                    
        }
 
        //---returns the number of images---
        public int getCount() {
            return urls.length;
        }
 
        //---returns the ID of an item--- 
        public Object getItem(int position) {
            return position;
        }            
 
        public long getItemId(int position) {
            return position;
        }
 
        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
          //  imageView.setImageResource(imageIDs[position]);
          //  imageView.setImageBitmap(images[position]);
            BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            task.execute(urls[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(300, 230));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }    
}