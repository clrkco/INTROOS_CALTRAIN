package introos;

import gui.StatusLocks;
import gui.SystemLocks;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationLocks
{
    private StationStatus stationStatus;
    private String stationName;
    private int stationPassengersWaiting;
    private TrainLocks trainOnStation;
    private boolean stationHasTrain;
    private ArrayList<Thread> stationRobots;
    private ArrayList<RobotLocks> stationRobotPOJO;
    private int stationCapacity;
    private int stationNumber;
    public int station_lock;

    public StationLocks(String stationName, int stationCapacity, int stationNumber) {
        this.stationName = stationName;
        this.stationCapacity = stationCapacity;
        this.stationNumber = stationNumber;
    }

    public void station_init()
    {
        this.stationStatus = StationStatus.IDLE;
        this.stationPassengersWaiting =0;
        this.trainOnStation = null;
        this.stationHasTrain = false;
        this.stationRobots = new ArrayList<>();
        this.stationRobotPOJO = new ArrayList<>();
        this.lock_init();
    }

    public void lock_init()
    {
        station_lock = 1;
    }

    public int lock_acquire()
    {
        if(station_lock == 0)
            return 0;
        else
        {
            station_lock = 0;
            return 1;
        }
    }

    public void lock_release()
    {
        station_lock = 1;
    }

    public void Auto_Generate_Robot(StationLocks[] stations)
    {
        if(stationPassengersWaiting == 0)
        {
            System.out.println("No passengers waiting.");
            int destinationStationNumber=stationNumber;
            Random random = new Random();
            Random stationRandom = new Random();
            Boolean sameStationNumber;

            for(int i =0; i < (random.nextInt(stationCapacity)+1); i++)
            {
                sameStationNumber=true;
                while(sameStationNumber)
                {
                    destinationStationNumber = stationRandom.nextInt(stations.length);
                    if(destinationStationNumber!=stationNumber&&(destinationStationNumber%2)==0)
                        sameStationNumber=false;
                }
                RobotLocks robot = new RobotLocks(this, stations[destinationStationNumber]);
                System.out.println("Destination of robot " + i + " = " + stations[destinationStationNumber].getStationName());
                stationRobots.add(new Thread(robot));
                stationRobots.get(stationRobots.size()-1).start();
                stationRobotPOJO.add(robot);
                stationPassengersWaiting++;
            }
            System.out.println("POJO SIZE = " + stationRobotPOJO.size());
        }
    }

    public void Station_On_Board(RobotLocks robot) {
        if(this.lock_acquire() == 1)
        {
            try {
                if (this.trainOnStation.getTrainLock() == 0) {
                    this.lock_release();
                    Station_Wait_For_Train(robot);
                } else {
//                    System.out.println("in here");
                    trainOnStation.lock_acquire();
                    this.stationPassengersWaiting--;
                    SystemLocks.setWaiting(this.stationName, stationPassengersWaiting);
                    robot.setRobotStatus(RobotStatus.ONBOARD);
                    trainOnStation.AddPassenger(robot);
                    stationRobotPOJO.remove(robot);
                }
            } catch (Exception e) {
            }
            this.lock_release();
        }
    }

    public synchronized void Station_Load_Train() throws InterruptedException
    {
        SystemLocks.setOccupied(stationName,Integer.toString(trainOnStation.getTrainID()));
        if(this.lock_acquire() == 1)
        {
            stationHasTrain = true;
            stationStatus = StationStatus.OCCUPIED;
            trainOnStation.SetDoorStatus(DoorStatus.OPEN);
            trainOnStation.DropPassenger();
            StatusLocks.trainSeats.get(trainOnStation.getTrainID()).setText(Integer.toString(trainOnStation.getNumberOfPassengers()) + "/" + trainOnStation.getNumberOfSeats());

            if(stationPassengersWaiting != 0){
                System.out.println("notified passengers");
                this.notifyAll(); //wake all waiting robot threads
            }
            Thread.sleep(1000);
            stationHasTrain = false;
            stationStatus = StationStatus.IDLE;
            trainOnStation.SetDoorStatus(DoorStatus.CLOSED);
            this.lock_release();
            SystemLocks.setFree(stationName);
        }
    }

    public synchronized void Station_Wait_For_Train(RobotLocks robot) throws InterruptedException
    {
        this.lock_acquire();
        this.lock_release();
        wait();
        this.Station_On_Board(robot);
    }

    public void setTrainOnStation(TrainLocks trainOnStation) {
        this.trainOnStation = trainOnStation;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public int getStationPassengersWaiting()
    {
        return stationPassengersWaiting;
    }

}