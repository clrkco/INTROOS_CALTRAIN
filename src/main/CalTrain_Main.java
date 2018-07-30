package main;

import gui.Visual;
import introos.Station;
import introos.Train;

import java.util.concurrent.Semaphore;

public class CalTrain_Main
{
    static Thread[] trainThreads = new Thread[16];
    public static Station[] stations = new Station[8];
    public static int numOfTrains = 0;
    static Semaphore availableSeats;

    public static String[] stationNames = {"Station 1", "Station 2", "Station 3", "Station 4", "Station 5", "Station 6", "Station 7", "Station 8"};

    public CalTrain_Main()
    {
        for(int i = 0; i < 8; i++)
        {
            Station station = new Station(stationNames[i]);
            stations[i] = station;
        }
    }

    public static void dispatchTrain(String name, int seats, int id)
    {
        availableSeats = new Semaphore(seats, true);

        Train newTrain = new Train(availableSeats, seats, name, stations, id);

        Visual.trainName.get(numOfTrains).setText(newTrain.getTRAIN_NAME());
        Visual.trainSeats.get(numOfTrains).setText(newTrain.getTRAIN_NOOFPASSENGERS() + "/" + newTrain.getTRAIN_NOOFSEATS());
        Visual.trainStatusHead.get(numOfTrains).setText("<html><u>Status:</u></html>");
        Visual.trainStatus.get(numOfTrains).setText("");

        trainThreads[numOfTrains] = new Thread(newTrain);
        trainThreads[numOfTrains].start();
    }
}
