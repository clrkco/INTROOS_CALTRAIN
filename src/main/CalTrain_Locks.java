package main;

import gui.StatusLocks;
import introos.StationLocks;
import introos.TrainLocks;

import java.util.concurrent.Semaphore;

public class CalTrain_Locks
{
    static Thread[] trainThreads = new Thread[16];
    public static StationLocks[] stations = new StationLocks[8];
    public static int numOfTrains = 0;
    static Semaphore availableSeats;

    public static String[] stationNames = {"Station 1", "Station 2", "Station 3", "Station 4", "Station 5", "Station 6", "Station 7", "Station 8"};

    public CalTrain_Locks()
    {
        for(int i = 0; i < 8; i++)
        {
            StationLocks station = new StationLocks(stationNames[i]);
            stations[i] = station;
        }
    }

    public static void dispatchTrain(String name, int seats, int id)
    {
        availableSeats = new Semaphore(seats, true);

        TrainLocks newTrain = new TrainLocks(availableSeats, seats, name, stations, id);

        StatusLocks.trainName.get(numOfTrains).setText(newTrain.getTRAIN_NAME());
        StatusLocks.trainSeats.get(numOfTrains).setText(newTrain.getTRAIN_NOOFPASSENGERS() + "/" + newTrain.getTRAIN_NOOFSEATS());
        StatusLocks.trainStatusHead.get(numOfTrains).setText("<html><u>Status:</u></html>");
        StatusLocks.trainStatus.get(numOfTrains).setText("");

        trainThreads[numOfTrains] = new Thread(newTrain);
        trainThreads[numOfTrains].start();
    }
}