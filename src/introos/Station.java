package introos;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;
public class Station {
    private StationStatus stationStatus;            // Domain: IDLE, OCCUPIED
    private String stationName;              // Optional name for Station object
    private int stationPassengersWaiting;     // Number of Passengers waiting in the train station
    private Train trainOnStation;            // The current Train object in the station
    private boolean stationHasTrain;         // Does the station have a train?
    private Lock stationLock;
    private Condition stationCondition;
    private ArrayList<Thread> stationRobots;
    private ArrayList<Robot> stationRobotPOJO;
    private Semaphore stationMutex;
    private int stationCapacity;
    private int stationNumber;

    public Station(String stationName, int stationCapacity, int stationNumber) {
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
        this.stationLock = new ReentrantLock();
        this.stationCondition = new ReentrantLock().newCondition();
        this.stationRobots = new ArrayList<>();
        this.stationRobotPOJO = new ArrayList<>();
        this.stationMutex = new Semaphore(1);
    }

    public void Auto_Generate_Robot(Station[] stations)
    {
        if(stationPassengersWaiting == 0)
        {
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
                    if(destinationStationNumber!=stationNumber)
                        sameStationNumber=false;
                }
                Robot robot = new Robot(this, stations[destinationStationNumber]);
                stationRobots.add(new Thread(robot));
                stationRobots.get(stationRobots.size()-1).start();
                stationRobotPOJO.add(robot);
            }
        }
    }

    public void Station_On_Board(Robot robot) throws InterruptedException {
        this.stationMutex.acquire();
        try {
            if (this.trainOnStation.getTrainMutex().availablePermits() == 0) {
                this.stationMutex.release();
                Station_Wait_For_Train(robot);
            } else {
                trainOnStation.getTrainMutex().acquire();
                this.stationPassengersWaiting--;
                trainOnStation.addPassenger(robot);
                robot.setRobotStatus(RobotStatus.ONBOARD);
                trainOnStation.addPassenger(robot);
                stationRobotPOJO.remove(robot);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.stationMutex.release();
    }

    public synchronized void Station_Load_Train(Train train) throws InterruptedException
    {
        this.stationMutex.acquire();
        train.setDoorStatus(DoorStatus.OPEN);
        if(stationPassengersWaiting==0)
        {

        }
        else if(train.getTrainMutex().availablePermits() == 0)
        {
            //no seats on train available
        }
        else
        {
            this.notifyAll(); //wake all waiting robot threads
        }
        Thread.sleep(3000);
        train.setDoorStatus(DoorStatus.CLOSED);
        this.stationMutex.release();
    }

    public synchronized void Station_Wait_For_Train(Robot robot) throws InterruptedException
    {
        wait();
        this.Station_On_Board(robot);
    }

    public Train getTrainOnStation() {
        return trainOnStation;
    }

    public void setTrainOnStation(Train trainOnStation) {
        this.trainOnStation = trainOnStation;
    }
}
