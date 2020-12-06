package com.mehmetbaykal.threads;

import com.mehmetbaykal.city.City;

public class TreadSleeper implements Runnable{
    City city;
    public TreadSleeper(City city) {
        this.city = city;
    }
    @Override
    public void run() {
            try {

                synchronized (this){
                    city.showTime();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
