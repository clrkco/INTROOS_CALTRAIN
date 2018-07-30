package main;

import gui.StatusSemaphores;
import introos.StationSemaphores;
import introos.TrainSemaphores;

import java.util.concurrent.Semaphore;

public class CalTrain_Semaphores
{
    static Thread[] trainThreads = new Thread[16];
    public static StationSemaphores[] stations = new StationSemaphores[16];
    public static int numOfTrains = 0;
    static Semaphore availableSeats;

    public static String[] stationNames = {"Station1", "TrackA", "Station2", "TrackB", "Station3", "TrackC", "Station4", "TrackD",
                                           "Station5", "TrackE", "Station6", "TrackF", "Station7", "TrackG", "Station8", "TrackH"};

    public CalTrain_Semaphores()
    {
        for(int i = 0; i < 16; i++)
        {
            StationSemaphores station = new StationSemaphores(stationNames[i]);
            stations[i] = station;
        }
    }

    public static void dispatchTrain(String name, int seats, int id)
    {
        availableSeats = new Semaphore(seats, true);

        TrainSemaphores newTrain = new TrainSemaphores(availableSeats, seats, name, stations, id);

        StatusSemaphores.trainName.get(numOfTrains).setText(newTrain.getTRAIN_NAME());
        StatusSemaphores.trainSeats.get(numOfTrains).setText(newTrain.getTRAIN_NOOFPASSENGERS() + "/" + newTrain.getTRAIN_NOOFSEATS());
        StatusSemaphores.trainStatusHead.get(numOfTrains).setText("<html><u>Status:</u></html>");
        StatusSemaphores.trainStatus.get(numOfTrains).setText("");

        trainThreads[numOfTrains] = new Thread(newTrain);
        trainThreads[numOfTrains].start();
    }
}