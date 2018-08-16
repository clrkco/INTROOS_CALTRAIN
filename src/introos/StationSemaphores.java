package introos;

import gui.StatusSemaphores;
import gui.SystemSemaphores;
import main.CalTrain_Semaphores;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class StationSemaphores
{
    private ArrayList<Thread> stationRobots;
    private ArrayList<RobotSemaphores> stationRobotPOJO;
    private StationStatus stationStatus;
    private String stationName;
    private TrainSemaphores trainOnStation;
    private Semaphore stationMutex;
    private int stationCapacity;
    private int stationNumber;
    private int stationPassengersWaiting;
    private boolean stationHasTrain;

    public StationSemaphores(String stationName, int stationCapacity, int stationNumber)
    {
        this.stationName = stationName;
        this.stationCapacity = stationCapacity;
        this.stationNumber = stationNumber;
    }

    public void station_init()
    {
        this.stationStatus = StationStatus.IDLE;
        this.stationPassengersWaiting = 0;
        this.trainOnStation = null;
        this.stationHasTrain = false;
        this.stationRobots = new ArrayList<>();
        this.stationRobotPOJO = new ArrayList<>();
        this.stationMutex = new Semaphore(1);
    }

    public void Auto_Generate_Robot(StationSemaphores[] stations)
    {
        if (stationPassengersWaiting == 0)
        {
            System.out.println("No passengers waiting.");
            int destinationStationNumber = stationNumber;
            Random random = new Random();
            Random stationRandom = new Random();
            Boolean sameStationNumber;

            for (int i = 0; i < (random.nextInt(stationCapacity) + 1); i++)
            {
                sameStationNumber = true;
                while (sameStationNumber)
                {
                    destinationStationNumber = stationRandom.nextInt(stations.length);
                    if (destinationStationNumber != stationNumber && (destinationStationNumber % 2) == 0)
                    {
                        sameStationNumber = false;
                    }
                }
                RobotSemaphores robot = new RobotSemaphores(this, stations[destinationStationNumber]);
                System.out.println(
                        "Destination of robot " + i + " = " + stations[destinationStationNumber].getStationName());
                stationRobots.add(new Thread(robot));
                stationRobots.get(stationRobots.size() - 1).start();
                stationRobotPOJO.add(robot);
                stationPassengersWaiting++;
            }
            System.out.println("POJO SIZE = " + stationRobotPOJO.size());
        }
    }

    public void Station_On_Board(RobotSemaphores robot) throws InterruptedException
    {
        this.stationMutex.acquire();
        try
        {
            if (this.trainOnStation.getTrainMutex().availablePermits() == 0)
            {
                Station_Wait_For_Train(robot);
            }
            else
            {
                System.out.println("in here");
                trainOnStation.getTrainMutex().acquire();
                this.stationPassengersWaiting--;
                SystemSemaphores.setWaiting(this.stationName, stationPassengersWaiting);
                robot.setRobotStatus(RobotStatus.ONBOARD);
                trainOnStation.AddPassenger(robot);
                stationRobotPOJO.remove(robot);
            }
        }
        catch (Exception e)
        {
        }
        this.stationMutex.release();
    }

    public synchronized void Station_Load_Train() throws InterruptedException
    {
        SystemSemaphores.setOccupied(stationName, Integer.toString(trainOnStation.getTrainID()));
        this.stationMutex.acquire();
        stationStatus = StationStatus.OCCUPIED;
        trainOnStation.SetDoorStatus(DoorStatus.OPEN);
        trainOnStation.DropPassenger();
        StatusSemaphores.trainSeats.get(trainOnStation.getTrainID()).setText(
                Integer.toString(trainOnStation.getNumberOfPassengers()) + "/" + trainOnStation.getNumberOfSeats());

        if(stationPassengersWaiting != 0){
            this.notifyAll(); //wake all waiting robot threads
        }
        Thread.sleep(1000);
        stationStatus = StationStatus.IDLE;
        trainOnStation.SetDoorStatus(DoorStatus.CLOSED);
        this.stationMutex.release();
        SystemSemaphores.setFree(stationName);
    }

    public synchronized void Station_Wait_For_Train(RobotSemaphores robot) throws InterruptedException
    {
        this.stationMutex.acquire();
        this.stationMutex.release();
        wait();
        this.Station_On_Board(robot);
    }

    public void setTrainOnStation(TrainSemaphores trainOnStation)
    {
        this.trainOnStation = trainOnStation;
    }

    public int getStationNumber()
    {
        return stationNumber;
    }

    public String getStationName()
    {
        return stationName;
    }

    public int getStationPassengersWaiting()
    {
        return stationPassengersWaiting;
    }
}