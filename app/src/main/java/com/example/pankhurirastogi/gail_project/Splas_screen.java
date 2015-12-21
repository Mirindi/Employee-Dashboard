package com.example.pankhurirastogi.gail_project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Splas_screen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas_screen);
        Thread timerThread = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent i = new Intent(Splas_screen.this,MainActivity.class);
                    startActivity(i);
                }
            }

        };

        timerThread.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
