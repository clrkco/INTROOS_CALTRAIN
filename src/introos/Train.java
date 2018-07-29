package introos;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Train implements Runnable{
    private int numberOfSeats;
    private Semaphore trainMutex;
    private ArrayList<Robot> passengers;
    private ArrayList<Robot> dropoff;
    private final Station[] stations;
    private int trainID;
    private int trainLocation;
    private DoorStatus doorStatus;
    private boolean trainRunning;

    public Train(int numberOfSeats, int trainID, Station[] stations) {
        this.numberOfSeats = numberOfSeats;
        this.stations = stations;
        this.trainID = trainID;
        this.doorStatus = DoorStatus.CLOSED;
        trainRunning = true;
        trainMutex = new Semaphore(numberOfSeats);
        this.trainLocation = 0;
        this.passengers = new ArrayList<>();
        this.dropoff = new ArrayList<>();
    }

    @Override
    public void run()
    {
        while(trainRunning)
        {
            this.stations[this.trainLocation].setTrainOnStation(this);
            try {
                System.out.println("permits available: " + trainMutex.availablePermits());
                System.out.println(stations[trainLocation].getStationName());
//                this.DropPassenger();
                this.stations[this.trainLocation].Station_Load_Train();
                trainLocation = (trainLocation+1)%(stations.length-1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Semaphore getTrainMutex()
    {
        return trainMutex;
    }

    public void SetDoorStatus(DoorStatus doorStatus)
    {
        this.doorStatus = doorStatus;
    }

    public void AddPassenger(Robot robot)
    {
        passengers.add(robot);
    }

    public void DropPassenger()
    {
//        for(Robot robot : passengers){
//            if(robot.getStationDestination().getStationNumber() == trainLocation){
//                passengers.remove(robot);
//                trainMutex.release();
//            }
//        }
        for (Robot robot : passengers){
            System.out.println(robot);
        }
        dropoff.clear();
        for (Robot robot : passengers) {
            if(robot.getStationDestination().getStationNumber() == trainLocation) {
//                TextFrame.textarea.append("It's " + passenger.getROBOT_NAME()+ "[" + this.TRAIN_NOOFPASSENGERS +"]" + "'s destination, dropping off from " + TRAIN_STATIONS[getTRAIN_WHERE()].getSTATION_NAME() + "\n");
                System.out.println("Train Location: " + trainLocation);
                System.out.println("Destination Station Number : " + robot.getStationDestination().getStationNumber());
                dropoff.add(robot);
            }
        }
        passengers.removeAll(dropoff);
        trainMutex.release(dropoff.size());
    }
}
