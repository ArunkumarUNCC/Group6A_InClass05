package com.group6a_inclass05.group6a_inclass05;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static String APIURL = "http://api.openweathermap.org/data/2.5/forecast?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


//        new WeatherAsyncTask().execute(APIURL + "q=" +  + "&mode=xml&cnt=8&units=imperial");
    }

    public void switchOnClick(View aView){

    }

    private class WeatherAsyncTask extends AsyncTask<String,Void,ArrayList<Weather>>{

        @Override
        protected ArrayList<Weather> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                connection.connect();

                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK){
                    InputStream in = connection.getInputStream();

                    return WeatherUtil.weatherSAXParser.parseWeather(in);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Weather> weathers) {
            super.onPostExecute(weathers);

            if (weathers!=null){
                Log.d("Weather SAX Parser",weathers.toString());
            }
        }
    }
}
