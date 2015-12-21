package com.example.pankhurirastogi.gail_project;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;

/**
 * Created by PANKHURI RASTOGI on 6/23/2015. This class is reponsible for fetching data from
 * database and showing it in the form of PieChart.
 */
public class FragmentC extends android.support.v4.app.Fragment {
    Context thisContext;
    RelativeLayout mainlayout;
    PieChart mchart;
    float y_data[]={10,20,30,15,45};
    String x_data[]={"marketting","it","safety","hr","finance"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x=inflater.inflate(R.layout.fragment_c, container, false);
        mainlayout=(RelativeLayout)x.findViewById(R.id.mainlayout);
        thisContext=container.getContext();

        return x;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mchart=new PieChart(thisContext);
        mainlayout.addView(mchart);
        mainlayout.setBackgroundColor(Color.LTGRAY);
        mchart.setUsePercentValues(true);
        mchart.setDescription("constiturnt of differenet departments");
        mchart.setDrawHoleEnabled(true);
        mchart.setHoleColorTransparent(true);
        mchart.setHoleRadius(7);
        mchart.setTransparentCircleRadius(10);
        mchart.setRotationAngle(0);
        mchart.setRotationEnabled(true);
        mchart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if (entry == null)
                    return;
                Toast.makeText(thisContext, x_data[entry.getXIndex()] + "=" + entry.getVal() + "%", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData();
        Legend l=mchart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }
    public void addData()
    {
        ArrayList<Entry> y_vals=new ArrayList<>();
        for(int i=0;i<y_data.length;i++)
            y_vals.add(new Entry(y_data[i],i));
        ArrayList<String> x_vals=new ArrayList<>();
        for (int i=0;i<x_data.length;i++)
            x_vals.add(x_data[i]);
        PieDataSet dataSet=new PieDataSet(y_vals,"departments");
        dataSet.setSliceSpace(5);
        dataSet.setSelectionShift(3);
        ArrayList<Integer> colors= new ArrayList<>();
        for(int c: ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData pieData=new PieData(x_vals,dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.GRAY);
        mchart.setData(pieData);
        mchart.highlightValues(null);
        mchart.invalidate();





    }
}
