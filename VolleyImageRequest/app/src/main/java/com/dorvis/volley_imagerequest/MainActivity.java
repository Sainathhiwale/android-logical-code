package com.dorvis.volley_imagerequest;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
   public Activity mActivity;

    private LinearLayout mCLayout;
    private Button mButtonDo;
    private ImageView mImageView;
    private ImageView mImageViewInternal;
    //change the url it's not working
    private String mImageURLString = "https://static.pexels.com/photos/134596/pexels-photo-134596.jpeg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from XML layout
        mCLayout = (LinearLayout) findViewById(R.id.linear_layout);
        mButtonDo = (Button) findViewById(R.id.bt_request);
        mImageView = (ImageView) findViewById(R.id.iv);
        mImageViewInternal = (ImageView) findViewById(R.id.iv_internal);

        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                // Initialize a new ImageRequest
                ImageRequest imageRequest = new ImageRequest(
                        mImageURLString, // Image URL
                        new Response.Listener<Bitmap>() { // Bitmap listener
                            @Override
                            public void onResponse(Bitmap response) {
                                // Do something with response
                                mImageView.setImageBitmap(response);

                                // Save this downloaded bitmap to internal storage
                                Uri uri = saveImageToInternalStorage(response);

                                // Display the internal storage saved image to image view
                                mImageViewInternal.setImageURI(uri);
                            }
                        },
                        0, // Image width
                        0, // Image height
                        ImageView.ScaleType.CENTER_CROP, // Image scale type
                        Bitmap.Config.RGB_565, //Image decode configuration
                        new Response.ErrorListener() { // Error listener
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Do something with error response
                                error.printStackTrace();
                                Snackbar.make(mCLayout,"Error",Snackbar.LENGTH_LONG).show();
                            }
                        }
                );

                // Add ImageRequest to the RequestQueue
                requestQueue.add(imageRequest);
            }
        });
    }

    // Custom method to save a bitmap into internal storage
    protected Uri saveImageToInternalStorage(Bitmap bitmap){
        // Initialize ContextWrapper
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

        // Initializing a new file
        // The bellow line return a directory in internal storage
        File file = wrapper.getDir("Images",MODE_PRIVATE);

        // Create a file to save the image
        file = new File(file, "UniqueFileName"+".jpg");

        try{
            // Initialize a new OutputStream
            OutputStream stream = null;

            // If the output file exists, it can be replaced or appended to it
            stream = new FileOutputStream(file);

            // Compress the bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

            // Flushes the stream
            stream.flush();

            // Closes the stream
            stream.close();

        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());

        // Return the saved image Uri
        return savedImageURI;
    }
}
