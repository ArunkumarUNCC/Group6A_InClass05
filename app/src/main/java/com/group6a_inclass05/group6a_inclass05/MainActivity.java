package com.group6a_inclass05.group6a_inclass05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView fLocation, fMaxTemp, fMinTemp, fTemperature,fHumidity,
            fPressure, fWind, fClouds, fPercipitation;

    View fPrecipLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fLocation = (TextView) findViewById(R.id.textViewLocationAct);
        fMaxTemp = (TextView) findViewById(R.id.textViewMaxTempAct);
        fMinTemp = (TextView) findViewById(R.id.textViewMinTempAct);
        fTemperature = (TextView) findViewById(R.id.textViewTemp);
        fHumidity = (TextView) findViewById(R.id.textViewHumidity);
        fPressure = (TextView) findViewById(R.id.textViewPressure);
        fWind = (TextView) findViewById(R.id.textViewWind);
        fClouds = (TextView) findViewById(R.id.textViewClouds);
        fPercipitation = (TextView) findViewById(R.id.textViewPercipitation);

        fPrecipLayout = findViewById(R.id.precipitationLayout);
//        fPrecipLayout.setVisibility(View.INVISIBLE);

        resetText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void okOnClick(View aView){

    }

    public void switchOnClick(View aView){

    }

    public void nextOnClick(View aView){

    }

    public void previousOnClick (View aView){

    }

    public void resetText(){
        fLocation.setText("");
        fMaxTemp.setText("");
        fMinTemp.setText("");
        fTemperature.setText("");
        fHumidity.setText("");
        fPressure.setText("");
        fWind.setText("");
        fClouds.setText("");
        fPercipitation.setText("");
    }
}
