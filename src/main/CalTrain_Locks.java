package main;

import gui.StatusLocks;
import introos.StationLocks;
import introos.TrainLocks;
import java.util.Random;

public class CalTrain_Locks
{
    static Thread[] trainThreads = new Thread[16];
    public static StationLocks[] stations = new StationLocks[16];
    public static int numOfTrains = 0;
    static int availableSeats;
    Random random = new Random();
    public static String[] stationNames = {"Station1", "TrackA", "Station2", "TrackB", "Station3", "TrackC", "Station4", "TrackD",
                                           "Station5", "TrackE", "Station6", "TrackF", "Station7", "TrackG", "Station8", "TrackH"};

    public CalTrain_Locks()
    {
        int stationCapacity;
        for(int i = 0; i < 16; i++)
        {
            stationCapacity = random.nextInt(41)+5;
            if(i%2==0)
            {
                stations[i] = new StationLocks(stationNames[i],stationCapacity,i);
            }
            else
            {
                stations[i] = new StationLocks(stationNames[i],0,i);
            }
            stations[i].station_init();
        }
    }

    public static void dispatchTrain(String name, int seats, int id)
    {
        availableSeats = seats;

        TrainLocks newTrain = new TrainLocks(seats,id,stations);

        StatusLocks.trainName.get(numOfTrains).setText(Integer.toString(newTrain.getTrainID()));
        StatusLocks.trainSeats.get(numOfTrains).setText(newTrain.getNumberOfPassengers() + "/" + newTrain.getNumberOfSeats());
        StatusLocks.trainStatusHead.get(numOfTrains).setText("<html><u>Status:</u></html>");
        StatusLocks.trainStatus.get(numOfTrains).setText("");

        trainThreads[numOfTrains] = new Thread(newTrain);
        trainThreads[numOfTrains].start();
    }
}