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
        private StationStatus stationStatus;            // Domain: IDLE, OCCUPIED
        private String stationName;              // Optional name for Station object
        private int stationPassengersWaiting;     // Number of Passengers waiting in the train station
        private TrainSemaphores trainOnStation;            // The current Train object in the station
        private boolean stationHasTrain;         // Does the station have a train?
        private Lock stationLock;
        private Condition stationCondition;
        private ArrayList<Thread> stationRobots;
        private ArrayList<RobotSemaphores> stationRobotPOJO;
        private Semaphore stationMutex;
        private int stationCapacity;
        private int stationNumber;

        public StationSemaphores(String stationName, int stationCapacity, int stationNumber) {
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

        public void Auto_Generate_Robot(StationSemaphores[] stations)
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
                        if(destinationStationNumber!=stationNumber)
                            sameStationNumber=false;
                    }
                    RobotSemaphores robot = new RobotSemaphores(this, stations[destinationStationNumber]);
                    System.out.println("Destination of robot " + i + " = " + stations[destinationStationNumber].getStationName());
                    stationRobots.add(new Thread(robot));
                    stationRobots.get(stationRobots.size()-1).start();
                    stationRobotPOJO.add(robot);
                    stationPassengersWaiting++;
                }
                System.out.println("POJO SIZE = " + stationRobotPOJO.size());
            }
        }

        public void Station_On_Board(RobotSemaphores robot) throws InterruptedException {
            //        System.out.println("Train on Station id: " + trainOnStation);
            this.stationMutex.acquire();
            try {
                if (this.trainOnStation.getTrainMutex().availablePermits() == 0) {
                    this.stationMutex.release();
                    Station_Wait_For_Train(robot);
                } else {
                    System.out.println("in here");
                    trainOnStation.getTrainMutex().acquire();
                    this.stationPassengersWaiting--;
                    robot.setRobotStatus(RobotStatus.ONBOARD);
                    trainOnStation.AddPassenger(robot);
                    stationRobotPOJO.remove(robot);
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            this.stationMutex.release();
        }

        public synchronized void Station_Load_Train() throws InterruptedException
        {
            this.stationMutex.acquire();
            trainOnStation.SetDoorStatus(DoorStatus.OPEN);
            trainOnStation.DropPassenger();
            //        System.out.println(stationRobotPOJO);
            //        System.out.println(stationPassengersWaiting);
            if(stationPassengersWaiting==0)
            {

            }
            else if(trainOnStation.getTrainMutex().availablePermits() == 0)
            {
                //no seats on train available
            }
            else
            {
                System.out.println("notified passengers");
                this.notifyAll(); //wake all waiting robot threads
            }
            Thread.sleep(3000);
            trainOnStation.SetDoorStatus(DoorStatus.CLOSED);
            this.stationMutex.release();
        }

        public synchronized void Station_Wait_For_Train(RobotSemaphores robot) throws InterruptedException
        {
            this.stationMutex.acquire();
            this.stationMutex.release();
            wait();
            this.Station_On_Board(robot);
        }

        public TrainSemaphores getTrainOnStation() {
            return trainOnStation;
        }

        public void setTrainOnStation(TrainSemaphores trainOnStation) {
            this.trainOnStation = trainOnStation;
        }

        public int getStationNumber() {
            return stationNumber;
        }

        public String getStationName() {
            return stationName;
        }
    }