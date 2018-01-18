package com.albertcbraun.cms50fw.alert;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();
        final ListView listview = (ListView) findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<String>();


        if (!contacts.isEmpty()) {
            for (Contact cn : contacts) {
                String log = "Id: " + cn.getID() + " ,Name: " + cn.getName();
                list.add(log);
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);
            }
                Toast toast = Toast.makeText(this, "Data Ready", Toast.LENGTH_SHORT);
                toast.show();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
