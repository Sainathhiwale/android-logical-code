package com.examen.soapcalculator.network;

import android.content.Context;
import org.ksoap2.serialization.PropertyInfo;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class DataSelections {

    public static String checkSum(int num1, int num2, Context applicationContext) throws IOException, XmlPullParserException {
        PropertyInfo[] propertyInfo = new PropertyInfo[2];
        PropertyInfo piNum1 = new PropertyInfo();
        piNum1.setName("intA");
        piNum1.setValue(num1);
        piNum1.setType(Object.class);
        propertyInfo[0] = piNum1;
        PropertyInfo piNum2 = new PropertyInfo();
        piNum2.setName("intB");
        piNum2.setValue(num2);
        piNum2.setType(Object.class);
        propertyInfo[1] = piNum2;
        return WebServiceGrabber.invokeWebService("Add",propertyInfo,applicationContext).toString();

    }

    public static String checkDivide(int num1, int num2, Context applicationContext) throws IOException, XmlPullParserException {
        PropertyInfo[] propertyInfo = new PropertyInfo[2];
        PropertyInfo piNum1 = new PropertyInfo();
        piNum1.setName("intA");
        piNum1.setValue(num1);
        piNum1.setType(Object.class);
        propertyInfo[0] = piNum1;
        PropertyInfo piNum2 = new PropertyInfo();
        piNum2.setName("intB");
        piNum2.setValue(num2);
        piNum2.setType(Object.class);
        propertyInfo[1] = piNum2;
        return WebServiceGrabber.invokeWebService("Divide",propertyInfo,applicationContext).toString();
    }

    public static String checkMultiply(int num1, int num2, Context applicationContext) throws IOException, XmlPullParserException {
        PropertyInfo[] propertyInfo = new PropertyInfo[2];
        PropertyInfo piNum1 = new PropertyInfo();
        piNum1.setName("intA");
        piNum1.setValue(num1);
        piNum1.setType(Object.class);
        propertyInfo[0] = piNum1;
        PropertyInfo piNum2 = new PropertyInfo();
        piNum2.setName("intB");
        piNum2.setValue(num2);
        piNum2.setType(Object.class);
        propertyInfo[1] = piNum2;
        return WebServiceGrabber.invokeWebService("Multiply",propertyInfo,applicationContext).toString();
    }

    public static String checkSubtract(int num1, int num2, Context applicationContext) throws IOException, XmlPullParserException {
        PropertyInfo[] propertyInfo = new PropertyInfo[2];
        PropertyInfo piNum1 = new PropertyInfo();
        piNum1.setName("intA");
        piNum1.setValue(num1);
        piNum1.setType(Object.class);
        propertyInfo[0] = piNum1;
        PropertyInfo piNum2 = new PropertyInfo();
        piNum2.setName("intB");
        piNum2.setValue(num2);
        piNum2.setType(Object.class);
        propertyInfo[1] = piNum2;
        return WebServiceGrabber.invokeWebService("Subtract",propertyInfo,applicationContext).toString();

    }
  /*  public static String checkSum(int num1, int num2, Context applicationContext)throws IOException, XmlPullParserException {
        PropertyInfo[] propertyInfo = new PropertyInfo[2];
        PropertyInfo piNum1 = new PropertyInfo();
        piNum1.setName("intA");
        piNum1.setValue(num1);
        piNum1.setType(Object.class);
        propertyInfo[0] = piNum1;
        PropertyInfo piNum2 = new PropertyInfo();
        piNum2.setName("intB");
        piNum2.setValue(num2);
        piNum2.setType(Object.class);
        propertyInfo[1] = piNum2;
        return WebServiceGrabber.invokeWebService("Add",propertyInfo,applicationContext).toString();
    }*/
}
