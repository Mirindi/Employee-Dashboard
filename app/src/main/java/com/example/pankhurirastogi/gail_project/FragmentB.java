package com.example.pankhurirastogi.gail_project;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PANKHURI RASTOGI on 6/23/2015.This class is responsible for fetching data from database and showing
 * in the form of table.
 */
public class FragmentB extends android.support.v4.app.Fragment {
    Context thisContext;
    TableLayout table;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_b, container, false);
       // return super.onCreateView(inflater, container, savedInstanceState);
        thisContext=container.getContext();
        table=(TableLayout)content.findViewById(R.id.table1);
        return content;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(thisContext);
        Log.d("Reading: ", "Reading all contacts..");
        List<employe> employee = db.getAllContacts();

        int i=0;

        for(employe cn:employee){

            TableRow row;
            LayoutInflater inflater = LayoutInflater.from(thisContext);
            row = (TableRow) inflater.inflate(R.layout.my_row,null,false);


            TextView text = (TextView) row.findViewById(R.id.myText);
            TextView department=(TextView)row.findViewById(R.id.department);
            TextView salary=(TextView) row.findViewById(R.id.salary);







            text.setText(cn.name);
            department.setText(cn.Department);
            salary.setText(cn.salary);



            table.addView(row);
        }
    }
}
