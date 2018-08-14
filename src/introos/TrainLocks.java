package introos;


import java.util.ArrayList;

public class TrainLocks implements Runnable
{
    private int numberOfSeats;
    private int lock_count;
    private ArrayList<RobotLocks> passengers;
    private ArrayList<RobotLocks> dropoff;
    private final StationLocks[] stations;
    private int trainID;
    private int trainLocation;
    private DoorStatus doorStatus;
    private boolean trainRunning;

    public TrainLocks(int numberOfSeats, int trainID, StationLocks[] stations) {
        this.numberOfSeats = numberOfSeats;
        this.stations = stations;
        this.trainID = trainID;
        this.doorStatus = DoorStatus.CLOSED;
        trainRunning = true;
        this.trainLocation = 0;
        this.passengers = new ArrayList<>();
        this.dropoff = new ArrayList<>();
        this.lock_init(numberOfSeats);
    }

    @Override
    public void run()
    {
        while(trainRunning)
        {
            this.stations[this.trainLocation].setTrainOnStation(this);
            try {
                System.out.println(trainID + " = permits available: " + lock_count);
                System.out.println(stations[trainLocation].getStationName());
                //                this.DropPassenger();
                this.stations[this.trainLocation].Station_Load_Train();
                trainLocation = (trainLocation+1)%(stations.length);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void lock_init(int n)
    {
        lock_count = n;
    }

    public void lock_acquire()
    {
        if(lock_count == 0){}
        else
            lock_count -= 1;

    }

    public void lock_release(int n)
    {
        lock_count += n;
    }

    public int getTrainID()
    {
        return trainID;
    }

    public int getTrainLock()
    {
        return lock_count;
    }

    public void SetDoorStatus(DoorStatus doorStatus)
    {
        this.doorStatus = doorStatus;
    }

    public void AddPassenger(RobotLocks robot)
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
        for (RobotLocks robot : passengers){
            System.out.println(robot);
        }
        dropoff.clear();
        for (RobotLocks robot : passengers) {
            if(robot.getStationDestination().getStationNumber() == trainLocation) {
                //TextFrame.textarea.append("It's " + passenger.getROBOT_NAME()+ "[" + this.TRAIN_NOOFPASSENGERS +"]" + "'s destination, dropping off from " + TRAIN_STATIONS[getTRAIN_WHERE()].getSTATION_NAME() + "\n");
                System.out.println("Train Location: " + trainLocation);
                System.out.println("Destination Station Number : " + robot.getStationDestination().getStationNumber());
                dropoff.add(robot);
            }
        }
        passengers.removeAll(dropoff);
        this.lock_release(dropoff.size());
    }

    public int getNumberOfPassengers()
    {
        return numberOfSeats - lock_count;
    }

    public int getNumberOfSeats()
    {
        return numberOfSeats;
    }
}