package com.group6a_inclass05.group6a_inclass05;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

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


            if (localName.equals("time")){
                weather = new Weather();
            } else if (localName.equals("symbol")){
                weather.setSymbol(attributes.getValue("var"));
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

    static public class weatherPullParser extends DefaultHandler{


        static ArrayList<Weather> parseWeather(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");

            Weather weather = null;
            ArrayList<Weather> weatherList = new ArrayList<Weather>();

            int event = parser.getEventType();

            while(event != XmlPullParser.END_DOCUMENT) {

                switch (event) {
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("time")){
                            weather = new Weather();
                        } else if (parser.getName().equals("symbol")){
                            weather.setSymbol(parser.getAttributeValue(null, "var"));
                        } else if (parser.getName().equals("precipitation")){
                            weather.setPrecipitation(parser.getAttributeValue(null, "type"));
                        } else if (parser.getName().equals("windDirection")){
                            weather.setWindDirection(parser.getAttributeValue(null, "name"));
                        } else if (parser.getName().equals("windSpeed")){

                            weather.setWindSpeed(parser.getAttributeValue(null, "name"));
                        } else if (parser.getName().equals("temperature")){
                            weather.setTemperature(parser.getAttributeValue(null, "value") + " " + parser.getAttributeValue(null, "unit"));
                            weather.setMinTemperature(parser.getAttributeValue(null, "min") + " " + parser.getAttributeValue(null,"unit"));
                            weather.setMaxTemperature(parser.getAttributeValue(null, "max") + " " + parser.getAttributeValue(null, "unit"));
                        } else if (parser.getName().equals("pressure")){
                            weather.setPressure(parser.getAttributeValue(null, "value") + " " + parser.getAttributeValue(null,"unit"));
                        } else if (parser.getName().equals("humidity")){
                            weather.setHumidity(parser.getAttributeValue(null, "value") + " " + parser.getAttributeValue(null, "unit"));
                        } else if (parser.getName().equals("clouds")){
                            weather.setClouds(parser.getAttributeValue(null,"value"));
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("time")){
                            weatherList.add(weather);
                            weather = null;
                        }
                        break;
                    default:break;
                }

                event = parser.next();
            }

            return weatherList;
        }
    }
}
