package com.example.pankhurirastogi.gail_project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANKHURI RASTOGI on 6/23/2015.
 * this class is reponsible for fetching data from Sqlite Database and showing it
 * in form of bar Chart.
 */
public class FragmentA extends android.support.v4.app.Fragment {
     Context thisContext;
    ArrayList<BarEntry> entri=new ArrayList<>();
    ArrayList<String> labels=new ArrayList<>();
    BarChart chartt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragmenta, container,
                false);
        thisContext = container.getContext();
        chartt=(BarChart)contentView.findViewById(R.id.chart);
        Log.d("pankhuyoyoy",""+thisContext);

       // return super.onCreateView(inflater, container, savedInstanceState);
        return contentView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(thisContext);

        Log.d("Reading: ", "Reading all contacts..");
        List<employe> employee = db.getAllContacts();

       /** for (employe cn : employee) {
            String log = "Name: " + cn.name + "department" + cn.Department + "salary"+ cn.salary;
            // Writing Contacts to log
            Log.d("Name: ", log);
            Toast.makeText(thisContext, log, Toast.LENGTH_LONG).show();
        }**/
         int i=0;
        for(employe cn: employee)
        {
            String a =cn.salary;
            String name=cn.name;
            float f = Float.parseFloat(a);
            entri.add(new BarEntry(f,i));
            labels.add(name);
            i++;

        }

         BarDataSet dataSet=new BarDataSet(entri,"# salaries of employee");
         dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        // BarChart chartt=new BarChart(MainActivity.this);

         BarData data=new BarData(labels,dataSet);

         chartt.setData(data);
         chartt.setDescription("# of salry of employees");




    }
}
