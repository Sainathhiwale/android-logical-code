package com.durgasoft.soaptest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    EditText et1;

    TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText);


        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);





    }

    public void go(View v){

        MyTask task=new MyTask(et1.getText().toString());
        task.execute();

    }


     class MyTask extends AsyncTask<Void,Void,Void>{

         String response;

         String et1;

         public  MyTask(String et1){
             this.et1=et1;
         }

         @Override
         protected Void doInBackground(Void... params) {

             SOAPRequest request=new SOAPRequest();
             response=request.requestWeatherByZip(et1);


             return null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
             super.onPostExecute(aVoid);

            System.out.print(response);

             Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

            // parseXML(response,"Temperature");

           tv1.setText("Temparature is :"+parseXML(response,"Temperature").get(0));

             tv2.setText("Humidity is :"+parseXML(response, "RelativeHumidity").get(0));

             tv3.setText("Visibility is :"+parseXML(response, "Visibility").get(0));

         }
     }

    public ArrayList<String> parseXML(String msg, final String tag_name){

        final ArrayList<String> list=new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse(IOUtils.toInputStream(msg), new DefaultHandler() {
                 boolean found=false;
                String tag_value="";

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);

                    System.out.println("Start Tag :"+qName);

                    if(tag_name.equalsIgnoreCase(qName)){
                        found=true;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if(found){
                        tag_value=new String(ch,start,length);

                        System.out.print("Tag Value : "+tag_value);
                    }

                    super.characters(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    super.endElement(uri, localName, qName);
                    if(tag_name.equalsIgnoreCase(qName)){
                        found=false;
                        list.add(tag_value);

                        System.out.println("End Tag :" + qName);

                    }

                }
            });

        }catch (Exception e){

        }

        return  list;

    }

}
