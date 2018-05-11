package com.dorvis.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
     Spinner spinner;
   Spinner spinner2;
    String india[]={"Maharashtra","Bihar","Uttar Pradesh"};
    String m[]={"PUNE","Mumbai","Solapur"};
    String b[]={"PATNA","MUZAFFARPUR","GAYA"};
    String u[]={"LUCKNOW","KANPUR","FAIZABAD"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner =(Spinner)findViewById(R.id.spinner1);
        spinner2 =(Spinner)findViewById(R.id.spinner2);

        final ArrayAdapter<String>listCountry = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,india);

        final ArrayAdapter<String> mDis = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, m);

        final ArrayAdapter<String> bDis = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,  b);

        final ArrayAdapter<String> uDis = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, u);

        listCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(listCountry);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()){
                    case R.id.spinner1:
                        if (india[position].equals("Maharashtra")){
                            mDis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner2.setAdapter(mDis);
                        }
                        if(india[position].equals("Bihar")){

                            bDis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner2.setAdapter(bDis);

                        }
                        if(india[position].equals("Uttar Pradesh")){

                            uDis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner2.setAdapter(uDis);

                        }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
/*

        spinner sample code
        String conntry []={"india", "america","japan","china","paris","london","england"};
        spinner =(Spinner)findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this
                ,android.R.layout.simple_dropdown_item_1line,conntry);

        spinner.setAdapter(adapter);

 */