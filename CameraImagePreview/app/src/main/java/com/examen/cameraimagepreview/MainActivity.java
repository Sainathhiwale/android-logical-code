package com.examen.cameraimagepreview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int ZBAR_CAMERA_PERMISSION = 180;

    Button btnOpen;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnCamera);
        imageView =findViewById(R.id.imgView1);
        initData();
    }

    private void initData() {
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},ZBAR_CAMERA_PERMISSION);
                }else {
                    dispatchTakePictureIntent();

                }
            }
        });
    }

    private void dispatchTakePictureIntent(){
        Intent takePictureintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureintent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureintent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extras.get("data");
            imageView.setImageBitmap(imageBitmap);

        }
        /*super.onActivityResult(requestCode, resultCode, data);*/

    }
}
