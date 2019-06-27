package com.examen.alertwithcalender;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
   public static EditText etSDate,etEDate;
   Button btnSumbit;
    private String getDeliveryDate;
    private int mYear;
    private int mMonth;
    private int mDay;
    public static int monthValue;
    public static StringBuilder  date;
    private String getStartDate;
    private String getEndDate;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return view;

    }

    private void initView() {
         AlertDialog alertDialog;
         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater li = LayoutInflater.from(getActivity());
        @SuppressLint("InflateParams")
        View promptsView = li.inflate(R.layout.user_details, null);
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setTitle("Enter your details...");
        alertDialogBuilder.setCancelable(false);

        alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // show it
        alertDialog.show();
        etSDate = (EditText)promptsView.findViewById(R.id.etSDate);
        etEDate = (EditText) promptsView.findViewById(R.id.etEDate);
        btnSumbit = (Button)promptsView.findViewById(R.id.btnSubmit);
        etSDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        (view1, year, monthOfYear, dayOfMonth) -> {

                            etSDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            getStartDate = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            etSDate.setError(null);

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        etEDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        (view1, year, monthOfYear, dayOfMonth) -> {

                            etEDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            getEndDate = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            etEDate.setError(null);

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etSDate.getText().toString().trim().equals("") && etEDate.getText().toString().trim().equals("") ){
                    Toast.makeText(getActivity(),"Please select the Date",Toast.LENGTH_LONG).show();
                }
                else if(etSDate.getText().toString().trim().equals("") || etEDate.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"Please enter the empty  details",Toast.LENGTH_LONG).show();
                }
                else{
                    alertDialog.dismiss();
                }
            }
        });

       /* etEDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(Objects.requireNonNull(getFragmentManager()), "datePicker");
                //showDate(year, month + 1, day);
            }

        });*/


    }
  public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

      @NonNull
      @Override
      public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
          Calendar calendar = Calendar.getInstance();
          int year = calendar.get(Calendar.YEAR);
          int month = calendar.get(Calendar.MONTH);
          int day = calendar.get(Calendar.DAY_OF_MONTH);

          return new DatePickerDialog(getActivity(), this, year, month, day);
      }

      @Override
      public void onDateSet(DatePicker view, int year, int month, int day) {
          monthValue = month + 1;
          date = (new StringBuilder().append(year).append("-").append(month + 1).append("-").append(day));
          etSDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
      }
  }
}
