package com.albertcbraun.cms50fw.alert;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.albertcbraun.cms50fw.alert.custom.RadarMarkerView;
import com.albertcbraun.cms50fw.alert.notimportant.DemoBase;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

public class RadarChartActivitry extends DemoBase {

    private RadarChart mChart;
    private String Tag;
    private float Mild = 0f,
            Moderate = 0f,
            Severe = 0f,
            AHI = 0f,
            NAHI = 0f;
    private int max = 0,
                min = 0;



    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radar_chart_activitry);

        tv = (TextView) findViewById(R.id.textView);
        tv.setTypeface(mTfLight);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(Color.rgb(60, 65, 82));


        mChart = (RadarChart) findViewById(R.id.chart1);
        mChart.setBackgroundColor(Color.rgb(60, 65, 82));

        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.LTGRAY);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        Bundle bundle =new Bundle();
        ArrayList<String> entry = bundle.getStringArrayList("entry");

        Log.wtf(Tag,"++++++++++++++++++++++++++++++++++  : " + String.valueOf(entry));

        if (entry != null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Entry is Enable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //TODO setdata
        setData(entry);

        tv.setText("The Mild : " + (int) Mild
                + "\t\tModurate : " + (int) Moderate
                + "\t\tSevere : " + (int)Severe
                + "\t\tAHI : " + (int)AHI
                + "\t\tNAHI : " +(int)NAHI
                + "\nMax : " + max
                + "\t\tMin : " + min);

        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"Mild",
                    "Moderate",
                    "Severe",
                    "AHI",
                    "NAHI"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setTypeface(mTfLight);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTypeface(mTfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.radar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                for (IDataSet<?> set : mChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if (mChart.getData() != null) {
                    mChart.getData().setHighlightEnabled(!mChart.getData().isHighlightEnabled());
                    mChart.invalidate();
                }
                break;
            }
            case R.id.actionToggleRotate: {
                if (mChart.isRotationEnabled())
                    mChart.setRotationEnabled(false);
                else
                    mChart.setRotationEnabled(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleFilled: {

                ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) mChart.getData()
                        .getDataSets();

                for (IRadarDataSet set : sets) {
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlightCircle: {

                ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) mChart.getData()
                        .getDataSets();

                for (IRadarDataSet set : sets) {
                    set.setDrawHighlightCircleEnabled(!set.isDrawHighlightCircleEnabled());
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionSave: {
                if (mChart.saveToPath("title" + System.currentTimeMillis(), "")) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();
                break;
            }
            case R.id.actionToggleXLabels: {
                mChart.getXAxis().setEnabled(!mChart.getXAxis().isEnabled());
                mChart.notifyDataSetChanged();
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleYLabels: {

                mChart.getYAxis().setEnabled(!mChart.getYAxis().isEnabled());
                mChart.invalidate();
                break;
            }
            case R.id.animateX: {
                mChart.animateX(1400);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(1400);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(1400, 1400);
                break;
            }
            case R.id.actionToggleSpin: {
                mChart.spin(2000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption
                        .EaseInCubic);
                break;
            }
        }
        return true;
    }

    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                //System.out.println("Could not parse " + nfe);
                Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }

    public void setData(ArrayList<String> entry) {

        float mult = 80;
//        float min = 20;
        int cnt = 5;
        int AHIcount = 0;
        int NAHIcount = 0;
        int allWDNAHIcount = 0;
        ArrayList<Float> counter = new ArrayList<Float>();

        //
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();
        if (!contacts.isEmpty()) {

            for (Contact cn : contacts) {
                if(cn.getName() < 70){
                    Severe++;
                }else if(cn.getName() < 80){
                    Moderate++;
                }else if(cn.getName() < 85){
                    Mild++;
                }
                if (cn.getName() > max){
                    max = cn.getName();
                }
                if (cn.getName() < min){
                    min = cn.getName();
                }
                counter.add((float) cn.getName());



            }


            Toast toast = Toast.makeText(this, "Data Ready", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT);
            toast.show();
        }

        for (int i = 0;i<counter.size();i++){
            if(counter.get(i) < 90 ){
                AHIcount++;
            }
            if (counter.get(i) > 90 && AHIcount > 5){
                AHI++;
                AHIcount = 0;
            }
        }

        //TODO Windows Sliding

        for (int ii = 11;ii<counter.size() ; ii++) {
            for (int jj = 11 ; jj >= 0 ; jj++){
                if (counter.get(ii-jj) < 90) {
                    allWDNAHIcount++;
                }
                NAHIcount++;
            }
            if ((float)allWDNAHIcount/(float)NAHIcount >= 0.8) {
                NAHI++;
            }
        }


        ArrayList<Integer> entries = new ArrayList<Integer>();
        if (entry != null) {

            entries = getIntegerArray(entry);

            Toast toast = Toast.makeText(getApplicationContext(), "Entry is Enable",Toast.LENGTH_SHORT);
            toast.show();


        }else {

            ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
            ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

            // NOTE: The order of the entries when being added to the entries array determines their position around the center of
            // the chart.
//            for (int i = 0; i < cnt; i++) {
//                float val1 = (float) (Math.random() * mult) + min;
//                entries1.add(new RadarEntry(val1));
//
//                float val2 = (float) (Math.random() * mult) + min;
//                entries2.add(new RadarEntry(val2));
//            }


            entries1.add(new RadarEntry(Mild));
            entries1.add(new RadarEntry(Moderate));
            entries1.add(new RadarEntry(Severe));
            entries1.add(new RadarEntry(AHI));
            entries1.add(new RadarEntry(NAHI));

            RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
            set1.setColor(Color.rgb(103, 110, 129));
            set1.setFillColor(Color.rgb(103, 110, 129));
            set1.setDrawFilled(true);
            set1.setFillAlpha(180);
            set1.setLineWidth(2f);
            set1.setDrawHighlightCircleEnabled(true);
            set1.setDrawHighlightIndicators(false);

            RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
            set2.setColor(Color.rgb(121, 162, 175));
            set2.setFillColor(Color.rgb(121, 162, 175));
            set2.setDrawFilled(true);
            set2.setFillAlpha(180);
            set2.setLineWidth(2f);
            set2.setDrawHighlightCircleEnabled(true);
            set2.setDrawHighlightIndicators(false);

            ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
            sets.add(set1);
//            sets.add(set2);

            RadarData data = new RadarData(sets);
            data.setValueTypeface(mTfLight);
            data.setValueTextSize(8f);
            data.setDrawValues(false);
            data.setValueTextColor(Color.WHITE);

            mChart.setData(data);
            mChart.invalidate();
        }
    }
}
