package main;

import gui.StatusSemaphores;
import introos.StationSemaphores;
import introos.TrainSemaphores;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CalTrain_Semaphores
{
    static Thread[] trainThreads = new Thread[16];
    public static StationSemaphores[] stations = new StationSemaphores[16];
    public static int numOfTrains = 0;
    static Semaphore availableSeats;
    Random random = new Random();
    public static String[] stationNames = {"Station1", "TrackA", "Station2", "TrackB", "Station3", "TrackC", "Station4", "TrackD",
                                           "Station5", "TrackE", "Station6", "TrackF", "Station7", "TrackG", "Station8", "TrackH"};

    public CalTrain_Semaphores()
    {
        int stationCapacity;
        for(int i = 0; i < 16; i++)
        {
            stationCapacity = random.nextInt(41)+5;
            if(i%2==0)
            {
                stations[i] = new StationSemaphores(stationNames[i],stationCapacity,i);
            }
            else
            {
                stations[i] = new StationSemaphores(stationNames[i],0,i);
            }
            stations[i].station_init();
        }
    }

    public static void dispatchTrain(String name, int seats, int id)
    {
        availableSeats = new Semaphore(seats, true);

        TrainSemaphores newTrain = new TrainSemaphores(seats,id,stations);

        StatusSemaphores.trainName.get(numOfTrains).setText(Integer.toString(newTrain.getTrainID()));
        StatusSemaphores.trainSeats.get(numOfTrains)
                                   .setText(newTrain.getNumberOfPassengers() + "/" + newTrain.getNumberOfSeats());
        StatusSemaphores.trainStatusHead.get(numOfTrains).setText("<html><u>Status:</u></html>");
        StatusSemaphores.trainStatus.get(numOfTrains).setText("");

        trainThreads[numOfTrains] = new Thread(newTrain);
        trainThreads[numOfTrains].start();
    }
}