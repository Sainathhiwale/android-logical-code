package com.examen.soapcalculator.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.examen.soapcalculator.R;
import com.examen.soapcalculator.network.DataSelections;
import com.examen.soapcalculator.utils.AppConstants;
import com.examen.soapcalculator.utils.CommonUtils;
import com.examen.soapcalculator.utils.NetworkUtils;
import com.examen.soapcalculator.utils.PreferenceManager;
import com.examen.soapcalculator.utils.Validation;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_FILE = 101;
    @Bind(R.id.etNum1)
    EditText etNum1;
    @Bind(R.id.etNum2)
    EditText etNum2;
    @Bind(R.id.tvResult)
    TextView tvResult;
    @Bind(R.id.btnSum)
    Button btnSum;
    @Bind(R.id.btnDivide)
    Button btnDivide;
    @Bind(R.id.btnMultiply)
    Button btnMultiply;
    @Bind(R.id.btnSubtract)
    Button btnSubtract;
    private ProgressDialog progressDialog;
    private PreferenceManager preferenceManager;
    private int num ;
    private int num2 ;
    private String numner1;
    private String number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        preferenceManager = new PreferenceManager(MainActivity.this);
        initData();

    }



    private void initData() {
        filePermission();
    }

    private void filePermission() {
          boolean result = checkpremission();
          if (result){
            createFile();
          }
    }


    private void createFile() {
        try{
            new File(AppConstants.SERVER_FILE_PATH);
            copySettings("Service.txt");
            String[] credentials = new CommonUtils().readSettingFile(AppConstants.SERVER_FILE_PATH, "Service.txt");
            AppConstants.URL = credentials[0];
            preferenceManager.putPreferenceValues(AppConstants.SERVERURL, credentials[0]);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void copySettings(String settingsFile) {
        try {
            if (!new CommonUtils().checkSettingFile(AppConstants.SERVER_FILE_PATH, settingsFile)) {
                new CommonUtils().copyAssets(MainActivity.this, settingsFile, AppConstants.SERVER_FILE_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkpremission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Write file permission is necessary to read info!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_FILE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_FILE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }



    @OnClick({R.id.btnSum, R.id.btnDivide, R.id.btnMultiply, R.id.btnSubtract})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSum:

                if (checkValidation()) {
                    if (new NetworkUtils().isNetworkAvailable(this)) {
                        AsyncSumTask  asyncSumTask = new AsyncSumTask(Integer.parseInt(etNum1.getText().toString().trim()),Integer.parseInt(etNum2.getText().toString().trim()));
                        asyncSumTask.execute("");
                    } else {
                        Toast.makeText(MainActivity.this, "Please check internet connection and try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter the required fields", Toast.LENGTH_SHORT).show();

                }
                //TODO: add click handling
                break;
            case R.id.btnDivide:
                if (checkValidation()) {
                    if (new NetworkUtils().isNetworkAvailable(this)) {
                        AsyncDivideTask asyncDivideTask = new AsyncDivideTask (Integer.parseInt(etNum1.getText().toString().trim()),Integer.parseInt(etNum2.getText().toString().trim()));
                        asyncDivideTask.execute("");
                    } else {
                        Toast.makeText(MainActivity.this, "Please check internet connection and try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter the required fields", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btnMultiply:
                if (checkValidation()) {
                    if (new NetworkUtils().isNetworkAvailable(this)) {
                        AsyncMultiplyTask asyncMultiplyTask = new AsyncMultiplyTask(Integer.parseInt(etNum1.getText().toString().trim()),Integer.parseInt(etNum2.getText().toString().trim()));
                        asyncMultiplyTask.execute("");
                    } else {
                        Toast.makeText(MainActivity.this, "Please check internet connection and try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter the required fields", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btnSubtract:
                if (checkValidation()) {
                    if (new NetworkUtils().isNetworkAvailable(this)) {
                        AsyncSubtractTask asyncSubtractTask = new AsyncSubtractTask(Integer.parseInt(etNum1.getText().toString().trim()),Integer.parseInt(etNum2.getText().toString().trim()));
                        asyncSubtractTask.execute("");
                    } else {
                        Toast.makeText(MainActivity.this, "Please check internet connection and try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter the required fields", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(etNum1)) ret = false;
        if (!Validation.hasText(etNum2)) ret = false;
        return ret;
    }
   //addtion operation
    @SuppressLint("StaticFieldLeak")
    private class AsyncSumTask  extends AsyncTask<Object,Object,String> {
        private int num1;
        private int num2;

        public AsyncSumTask(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }



        @Override
        protected void onPreExecute() {
            progressDialog = new CommonUtils().startProgressBarDialog(MainActivity.this,"Please wait sum total adding..");

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Object... objects) {
            try {
                return  DataSelections.checkSum(num1,num2,getApplicationContext());
            } catch (Exception ex) {
                try {
                    throw ex;
                } catch (final IOException | XmlPullParserException e1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (e1.toString().startsWith("java.net.ConnectException: failed to connect")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            } else if (e1.toString().startsWith("java.net.SocketTimeoutException: failed to connect")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            } else if (e1.toString().contains("Connection timed out")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
            return null;

        }

        @Override
        protected void onPostExecute(final String result) {
            progressDialog.dismiss();
            if (result!=null){
                tvResult.setText(result);
                etNum1.setText("");
                etNum2.setText("");
                Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(result);
        }
    }


    //divide operation

    @SuppressLint("StaticFieldLeak")
    public class AsyncDivideTask extends AsyncTask<Object,Object,String>{
        private  int num1;
        private int num2;

        public AsyncDivideTask(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new CommonUtils().startProgressBarDialog(MainActivity.this,"Please wait divideing...");
        }

        @Override
        protected String doInBackground(Object... objects) {
            try {
                return  DataSelections.checkDivide(num1,num2,getApplicationContext());
            } catch (Exception ex) {
                try {
                    throw ex;
                } catch (final IOException | XmlPullParserException e1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (e1.toString().startsWith("java.net.ConnectException: failed to connect")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            } else if (e1.toString().startsWith("java.net.SocketTimeoutException: failed to connect")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            } else if (e1.toString().contains("Connection timed out")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
            return null;

        }

        @Override
        protected void onPostExecute(final String result) {
            progressDialog.dismiss();
            if (result!=null){
                tvResult.setText(result);
                etNum1.setText("");
                etNum2.setText("");

            }else {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(result);
        }
    }


    //Multiply operation
    @SuppressLint("StaticFieldLeak")
    public class AsyncMultiplyTask extends AsyncTask<Object,Object,String>{
        private int num1;
        private  int num2;

        public AsyncMultiplyTask(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new CommonUtils().startProgressBarDialog(MainActivity.this,"Please wait Multiply...");

        }

        @Override
        protected String doInBackground(Object... objects) {
            try {
                return  DataSelections.checkMultiply(num1,num2,getApplicationContext());
            } catch (Exception ex) {
                try {
                    throw ex;
                } catch (final IOException | XmlPullParserException e1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (e1.toString().startsWith("java.net.ConnectException: failed to connect")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            } else if (e1.toString().startsWith("java.net.SocketTimeoutException: failed to connect")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            } else if (e1.toString().contains("Connection timed out")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(final String result) {
            progressDialog.dismiss();
            if (result!=null){
                 tvResult.setText(result);
                etNum1.setText("");
                etNum2.setText("");
            }else {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(result);
        }
    }

    //asyncSubtractTask operation
    @SuppressLint("StaticFieldLeak")
    private class AsyncSubtractTask extends AsyncTask<Object,Object,String> {
        private int num1;
        private int num2;

        public AsyncSubtractTask(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new CommonUtils().startProgressBarDialog(MainActivity.this,"Please wait...Subtracting");
        }

        @Override
        protected String doInBackground(Object... objects) {
            try {
                return  DataSelections.checkSubtract(num1,num2,getApplicationContext());
            } catch (Exception ex) {
                try {
                    throw ex;
                } catch (final IOException | XmlPullParserException e1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (e1.toString().startsWith("java.net.ConnectException: failed to connect")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            } else if (e1.toString().startsWith("java.net.SocketTimeoutException: failed to connect")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            } else if (e1.toString().contains("Connection timed out")) {
                                Toast.makeText(MainActivity.this, "Failed to connect,Please try again.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(final String result) {
            progressDialog.dismiss();
            if (result!=null){
                tvResult.setText(result);
                etNum1.setText("");
                etNum2.setText("");
            }else {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        }
    }




}
