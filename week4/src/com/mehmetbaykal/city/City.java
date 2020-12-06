package com.mehmetbaykal.city;

import com.mehmetbaykal.interfaces.Clock;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class City implements Clock, Comparable<City> {

   private String cityName;
   private String cityCode;
   private String gmt;

    public City(String cityName, String cityCode, String gmt) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.gmt = gmt;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getGmt() {
        return gmt;
    }

    public void setGmt(String gmt) {
        this.gmt = gmt;
    }



    @Override
    public void showTime() {
        boolean live = true;
        String timeString = "";
        while (live) {
            TimeZone.setDefault(TimeZone.getTimeZone(this.gmt));
            ZoneId zoneId = ZoneId.systemDefault();
            LocalTime time = LocalTime.now(zoneId);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timeString = time.format(dateTimeFormatter);
            System.out.printf("%s | %s | %-10S | %-10s | %s \n", Thread.currentThread().getName(), this.cityCode, this.cityName, this.gmt, timeString);
        }

    }
    @Override
    public int compareTo(City o) {

        return this.getCityCode().compareTo(o.getCityCode());

    }

    @Override
    public String toString() {
        return "com.mehmetbaykal.city.City{" +
                "cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", gmt='" + gmt + '\'' +
                '}';
    }
}
