package com.mehmetbaykal.defaults;

import com.mehmetbaykal.cities.*;
import com.mehmetbaykal.city.City;
import com.mehmetbaykal.threads.TreadSleeper;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Moscow moscow = new Moscow("Moscow","MOW","GMT+3:00");
        London london = new London("London","LON","GMT");
        Berlin berlin = new Berlin("Berlin","BER","GMT+1:00");
        Newyork newyork = new Newyork("Newyork","NYC","GMT-5:00");
        NewDelhi newDelhi = new NewDelhi("New Delhi","DEL","GMT+5:00");

       Map<String, City> cities = new HashMap<>();
        cities.put("MOW",moscow);
        cities.put("LON",london);
        cities.put("BER",berlin);
        cities.put("NYC",newyork);
        cities.put("DEL",newDelhi);

        List<String> citiesList = new ArrayList<>();

        for (Map.Entry<String,City> entry : cities.entrySet())
            citiesList.add(entry.getKey() + " "
                    + entry.getValue().getCityName());

        for(String list:citiesList){
            System.out.println(list);
        }
        System.out.println("En az 3 ve en fazla 5 şehir kodu girebilirsiniz örn: DEL LON NYC ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> controlList = new ArrayList<String>(Arrays.asList(input.toUpperCase().split(" ")));
        List<String> showList = new ArrayList<>();
       boolean live =true;
        while (live){
           if (2<controlList.size() && controlList.size()<6){
                    for (int i=0;i<controlList.size();i++){
                        if (cities.containsKey(controlList.get(i)) ){
                            showList.add(controlList.get(i));
                        }else {
                            live=false;
                            System.out.println("Yanlış bir kode girdiniz..");
                            break;

                        }
                    }
                    for (String key:showList){
                        executor.execute(new TreadSleeper(cities.get(key)));
                    }
           }else{
               System.out.println("Eksik veya fazla şehir kodu girdiniz...");
               live=false;
           }



        }




    }




}
