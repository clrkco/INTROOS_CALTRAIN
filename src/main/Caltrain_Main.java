package main;

import introos.*;

import java.util.Random;

public class Caltrain_Main {
    public static void main(String[] args){
        Station[] stations = new Station[8];
        String[] stationNames = {"Taft", "Magallanes", "Ayala", "Buendia", "Guadalupe", "Boni", "Shaw", "Ortigas"};
        Thread[] trains = new Thread[16];
        Random random = new Random();
        int stationCapacity;
        for(int i =0; i < 8; i++){
            stationCapacity = random.nextInt(41)+5;
//            System.out.println("station capacity" + stationCapacity);
            stations[i] = new Station(stationNames[i],stationCapacity,i);
            stations[i].station_init();
        }
        trains[0] = new Thread(new Train(10,1,stations)) ;
        trains[0].start();
        stations[1].Auto_Generate_Robot(stations);
    }
}
