package com.bcil.gapscan;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bcil.gapscan.controller.AssetsController;
import com.bcil.gapscan.controller.StorageController;
import com.bcil.gapscan.model.Gapscan;
import com.bcil.gapscan.sqllite.DatabaseHandler;
import com.bcil.gapscan.utils.AppConstants;
import com.bcil.gapscan.utils.MessageBuilder;
import com.bcil.gapscan.utils.PreferenceManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText etEan;
    private TextView tvEan;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_FILE = 150;
    private DatabaseHandler databaseHandler;
    private String readData;
    private File[] dirFiles;
    private Button btSave;
    private Button btExit;
    private File fileDirectory;
    private File fileDirectory2;
    private PreferenceManager preferenceManager;
    private Date presentData;
    private Date futureDate;
    private String fileName;
    public String data;
    private String readValidData;
    private String dataInFile;
    private Button btResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(MainActivity.this);
        preferenceManager = new PreferenceManager(MainActivity.this);
        filePermission();
        initView();
        initData();

    }

    private void filePermission() {
        boolean result = checkpermission();
        if (result) {
            createFile();
        }
    }

    private void createFile() {
        try {
            copySettings("GapScan.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void copySettings(String settingsFile) {
        try {
            if (!StorageController.checkSettingFile(AppConstants.SETTING_FILE_PATH, settingsFile)) {
                //File settingFile = new File();
                AssetsController.copyAssets(MainActivity.this, settingsFile, AppConstants.SETTING_FILE_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkpermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
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

    private void initData() {
        etEan.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            finish();
                            return true;
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                            return true;
                        case KeyEvent.KEYCODE_ENTER:
                            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                    .hideSoftInputFromWindow(etEan.getWindowToken(), 0);
                           //databaseHandler.checkEanNoIfExist(etEan.getText().toString().trim());

                            if (!etEan.getText().toString().trim().equals(AppConstants.EMPTY)) {

                                if(databaseHandler.checkEanNoIfExist(etEan.getText().toString().trim())){
                                    showDialog("Already scaned barcode entry! please scan other new barcode",false);
                                    tvEan.setText(etEan.getText().toString().trim());
                                    etEan.setText("");
                                }else
                                    manipulateData(etEan.getText().toString().trim());
                            } else {
                                MessageBuilder.Alert(MainActivity.this, "Alert", "Scan Barcode. cannot be empty");
                            }


                            return true;
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                            return true;
                    }
                }
                return false;


            }

        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Gapscan> gapscanList = databaseHandler.getAllGapScan();
                if (gapscanList != null && gapscanList.size() > 0) {
                    fileDirectory = new File(Environment.getExternalStorageDirectory() + "/BCIL/GapScan/");
                    fileDirectory2 = new File(Environment.getExternalStorageDirectory() + "/BCIL/GapScan/");
                    File sourceLocation = new File(fileDirectory, "GapScan.txt");
                    int getIncrementFileNo = preferenceManager.getPreferenceIntValues(AppConstants.FILEINCREMENT);
                    int updateFileNo = getIncrementFileNo + 1;
                    File targetLocation = new File(fileDirectory2, "GapScan" + String.valueOf(updateFileNo) + ".txt");
                    preferenceManager.putPreferenceIntValues(AppConstants.FILEINCREMENT, updateFileNo);
                    Log.d(TAG, "sourceLocation: " + sourceLocation);
                    Log.d(TAG, "targetLocation: " + targetLocation);
                    try {

                        if (sourceLocation.exists()) {

                            InputStream in = new FileInputStream(sourceLocation);
                            OutputStream out = new FileOutputStream(targetLocation);

                            // Copy the bits from instream to outstream
                            byte[] buf = new byte[1024];
                            int len;

                            while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }

                            in.close();
                            out.close();

                            Log.v(TAG, "Copy file successful.");

                        } else {
                            Log.v(TAG, "Copy file failed. Source file missing.");
                        }

                        createNewFile(fileDirectory);

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                   // databaseHandler.deleteGapScantable();
                    etEan.setText(AppConstants.EMPTY);
                    tvEan.setText(AppConstants.EMPTY);
                    Toast.makeText(MainActivity.this, "Data save successfully", Toast.LENGTH_SHORT).show();
                } else {
                    etEan.setText(AppConstants.EMPTY);
                    tvEan.setText(AppConstants.EMPTY);
                    MessageBuilder.Alert(MainActivity.this, "Alert", "Data not found please scan ean for further operation.");

                }

            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish();
                clearData();
            }
        });
    }

    private void clearData() {
        tvEan.setText("");
    }

    private void createNewFile(File fileDirectory) {
        File file2 = new File(fileDirectory, "GapScan.txt");
        if (file2.exists() && file2.isFile()) {
            file2.delete();
        }
        try {
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void manipulateData(String eanno) {
        File fileDirectory = new File(Environment.getExternalStorageDirectory() + "/BCIL/GapScan/");
        Gapscan gapscan = new Gapscan();
        gapscan.setEanno(eanno);
        databaseHandler.addEanScan(gapscan);
        tvEan.setText(eanno);
        etEan.setText(AppConstants.EMPTY);
        List<Gapscan> gapscanList = databaseHandler.getAllGapScan();
        if (gapscanList != null && gapscanList.size() > 0) {
            writeFileOperation(fileDirectory, gapscanList);
        }

    }

    private void writeFileOperation(File fileDirectory, List<Gapscan> gapscanList) {
        dirFiles = fileDirectory.listFiles();
        String getfilename = dirFiles[0].getName();
        Log.d(TAG, "ForFileLengthOne:" + getfilename);
        File file = new File(fileDirectory, getfilename);
        try {
            String  currentDateTimeString = DateFormat.getDateTimeInstance()
                    .format(new Date());
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            for (int j = 0; j < gapscanList.size(); j++) {
                myOutWriter.append(gapscanList.get(j).getEanno());
                myOutWriter.append(" "+currentDateTimeString);
                myOutWriter.append("\r\n");
            }
            myOutWriter.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        etEan = (EditText) findViewById(R.id.etEan);
        tvEan = (TextView) findViewById(R.id.tvEan);
        btSave = (Button) findViewById(R.id.btSave);
        btExit = (Button) findViewById(R.id.btExit);
        btResult = (Button)findViewById(R.id.btResult);
        btResult.setOnClickListener(this);
        File fileDirectory = new File(Environment.getExternalStorageDirectory() + "/BCIL/GapScan/");
        File file2 = new File(fileDirectory, "GapScan.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file2));
            String line;
            while ((line = br.readLine()) != null) {
                readValidData = line;
            }
            if (readValidData != null) {
                Log.d(TAG, "Data is in file");
            } else {
                databaseHandler.deleteGapScantable();
            }
            etEan.setText(AppConstants.EMPTY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Date current = new Date();
        presentData = current;
        System.out.println("Present:" + presentData);
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + 6));
        current = cal.getTime();
        futureDate = current;
        System.out.println("Future:" + futureDate);
        if (presentData.equals(futureDate)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setTitle("Alert");
            alertDialogBuilder
                    .setMessage("Please contact application administrator for further operation.")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            //MainActivity.this.finish();
                            dialog.dismiss();
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_FILE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createFile();
            }
        }

    }

    private void readFile() {
        File fileDirectory = new File(Environment.getExternalStorageDirectory() + "/BCIL/GapScan/");
        File file = new File(fileDirectory, "GapScan.txt");
        try {
            String readData = null;
            BufferedReader br = new BufferedReader(new FileReader(file));
            readData = br.readLine();
            dataInFile = readData;
            br.close();
            Log.d(TAG, "CheckDataInFile:" + dataInFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDialog(String message, final boolean  isClosed) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Print");
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids){
            case R.id.btResult:
            openResult();
            break;
        }
    }

    private void openResult() {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        startActivity(intent);

    }
}
