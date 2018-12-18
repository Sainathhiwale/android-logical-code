package com.bcil.broadcastreceiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if (intentAction!=null){
            String toastMessage = "inknown intent action";
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage ="Power connected";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage ="Power disconnected";
                    break;
                case Intent.ACTION_BATTERY_LOW:
                    toastMessage= "Battery low";
                    break;
                case Intent.ACTION_SCREEN_OFF:
                    toastMessage= "Screen off";
                    break;
                case Intent.ACTION_SCREEN_ON:
                    toastMessage ="Screen on";
                    break;
            }
            Toast.makeText(context,toastMessage,Toast.LENGTH_LONG).show();

        }



      /*MainActivity mainActivity = (MainActivity)context;

        TextView tv=(TextView)mainActivity.findViewById(R.id.tvBroadcast);

        if(intent.getAction()==Intent.ACTION_HEADSET_PLUG){

            tv.setText("Headset Plugin ...");

        }else if(intent.getAction()==Intent.ACTION_POWER_CONNECTED){


            tv.setText("Power Connected ...");

        }else if(intent.getAction()==Intent.ACTION_POWER_DISCONNECTED){


            tv.setText("Power Disconnected  ...");

        }else if(intent.getAction()==Intent.ACTION_SCREEN_ON){


            tv.setText("Screen ON  ...");

        }else if(intent.getAction()==Intent.ACTION_SCREEN_OFF){


            tv.setText("Screen  OFF ...");
        }
*/
    }
}
