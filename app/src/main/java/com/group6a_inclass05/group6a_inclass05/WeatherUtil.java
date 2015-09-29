package com.group6a_inclass05.group6a_inclass05;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Arunkumar's on 9/28/2015.
 */
public class WeatherUtil {
    static public class weatherSAXParser extends DefaultHandler{
        ArrayList<Weather> weatherList;
        Weather weather;
        StringBuilder xmlInnerText;

        public ArrayList<Weather> getWeatherList() {
            return weatherList;
        }

        static public ArrayList<Weather> parseWeather(InputStream in) throws IOException, SAXException {
            weatherSAXParser parser = new weatherSAXParser();

            Xml.parse(in, Xml.Encoding.UTF_8, parser);

            return parser.getWeatherList();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();

            weatherList = new ArrayList<Weather>();
            xmlInnerText = new StringBuilder();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            /*<symbol number="500" name="light rain" var="10n"/>
<precipitation unit="3h" value="1.2" type="rain"/>
<windDirection deg="48.0013" code="NE" name="NorthEast"/>
<windSpeed mps="10.2" name="Fresh Breeze"/>
<temperature unit="imperial" value="72.86" min="69.88" max="72.86"/>
<pressure unit="hPa" value="1000.8"/>
<humidity value="98" unit="%"/>
<clouds value="overcast clouds" all="100" unit="%"/>*/



            if (localName.equals("time")){
                weather = new Weather();
            } else if (localName.equals("symbol")){
                weather.setSymbol(attributes.getValue("name"));
            } else if (localName.equals("precipitation")){
                weather.setPrecipitation(attributes.getValue("type"));
            } else if (localName.equals("windDirection")){
                weather.setWindDirection(attributes.getValue("name"));
            } else if (localName.equals("windSpeed")){

                weather.setWindSpeed(attributes.getValue("name"));
            } else if (localName.equals("temperature")){
                weather.setTemperature(attributes.getValue("value") + " " + attributes.getValue("unit"));
                weather.setMinTemperature(attributes.getValue("min") + " " + attributes.getValue("unit"));
                weather.setMaxTemperature(attributes.getValue("max") +  " " + attributes.getValue("unit"));
            } else if (localName.equals("pressure")){
                weather.setPressure(attributes.getValue("value") + " " + attributes.getValue("unit"));
            } else if (localName.equals("humidity")){
                weather.setHumidity(attributes.getValue("value") + " " + attributes.getValue("unit"));
            } else if (localName.equals("clouds")){
                weather.setClouds(attributes.getValue("value"));
            }


        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if (localName.equals("time")){
                weatherList.add(weather);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }
    }

    static public class weatherPullParser{

    }
}
