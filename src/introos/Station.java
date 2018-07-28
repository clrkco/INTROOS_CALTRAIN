package introos;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;
public class Station {
    private String stationStatus;            // Domain: IDLE, OCCUPIED
    private String stationName;              // Optional name for Station object
    private int stationPassengersWaiting;     // Number of Passengers waiting in the train station
    private Train trainOnStation;            // The current Train object in the station
    private boolean stationHasTrain;         // Does the station have a train?
    private Lock stationLock;
    private Condition stationCondition;
    private ArrayList<Thread> stationRobots;
    private ArrayList<Robot> ROBOT_OBJECT;
    private Semaphore STATION_MUTEX;

    public Station(String STATION_NAME) {
        this.STATION_NAME = STATION_NAME;
    }

    public void station_init()
    {

    }

    public void station_on_board(Station station)
    {

    }
    public synchronized void station_load_train(Station station, int seats)
    {

    }
    public void station_wait_for_train(Station station)
    {

    }

}
