package com.example.pankhurirastogi.gail_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is responsible for connecting with server. JSON fethed from server is passed and stored in
 * local database.
 */

public class MainActivity extends Activity {
    JSONArray data;
    TextView tv;
    JSONObject ob;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c=this;
        setContentView(R.layout.activity_main);
        ConnectivityManager check = (ConnectivityManager) this.c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(check!=null)
        {
            NetworkInfo[] info = check.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i <info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        Toast.makeText(c, "Internet is connected",Toast.LENGTH_SHORT).show();
                        //Toast.makeText(c, "Internet is connected",Toast.LENGTH_SHORT).show();
                    }
        }


        new A().execute();
    }

    private class A extends AsyncTask<Void, Void, Void> {
        String content="",alldata="";

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {

            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://10.0.2.2/a/index.php/welcome/display_employee");


            try {
                HttpResponse rs = client.execute(get);
                InputStream is = rs.getEntity().getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String s;
                while ((s = br.readLine()) != null) {
                    content += s;


                }


            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }
            @Override
        protected void onPostExecute(Void result) {
            if(content=="")
                content="no content";
                ArrayList<BarEntry> entri=new ArrayList<>();
                ArrayList<String> labels=new ArrayList<>();
                ArrayList<employe> emps=new ArrayList<>();
                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
            //Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG).show();
            try {
            data=new JSONArray(content);

            for (int i=0;i<data.length();i++){
            ob=data.getJSONObject(i);

                     String name=ob.getString("name");
                     String department=ob.getString("department");

                     String salary=ob.getString("salary");

                     employe emp=new employe(name,department,salary);
                     db.addContact(emp);






            }
            } catch (JSONException e) {

            e.printStackTrace();
            }






                Intent i=new Intent(MainActivity.this,MainActivity2Activity.class);
                startActivity(i);

            super.onPostExecute(result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
