

package com.albertcbraun.cms50fw.alert;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        new SimpleEula(this).show(savedInstanceState);


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialog);
        dialog.setCancelable(true);

        Button button1 = (Button)dialog.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext()
                        , "Close dialog", Toast.LENGTH_SHORT);
                dialog.cancel();
            }
        });

        TextView textView1 = (TextView)dialog.findViewById(R.id.textView1);
        textView1.setText("Custom Dialog");
        TextView textView2 = (TextView)dialog.findViewById(R.id.textView2);
        textView2.setText("Try it yourself");

        dialog.show();


    }

    MainUIFragment findMyFragment() {
        return (MainUIFragment) getSupportFragmentManager().findFragmentByTag(MainUIFragment.TAG);
    }

    public void connect(View v) {
        MainUIFragment f = findMyFragment();
        if (f != null) {
            f.connect(v);
        }
    }

    public void resetState(View v) {
        MainUIFragment f = findMyFragment();
        if (f != null) {
            f.resetState(v);
        }
    }

    public void startData(View v) {
        MainUIFragment f = findMyFragment();
        if (f != null) {
            f.startData(v);
        }
    }

    public void stopData(View v) {
        MainUIFragment f = findMyFragment();
        if (f != null) {
            f.stopData(v);
        }
    }

    public void clearWindow(View v) {
        MainUIFragment f = findMyFragment();
        if (f != null) {
            f.clearWindow(v);
        }
    }

    void stopAlertSound() {
        MainUIFragment f = findMyFragment();
        if (f != null) {
            f.stopAlertSound();
        }
    }

    void disableAlertSound() {
        MainUIFragment f = findMyFragment();
        if (f != null) {
            f.disableAlertSound();
        }
    }

}
