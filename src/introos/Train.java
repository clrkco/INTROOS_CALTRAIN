package introos;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Train implements Runnable{
    private int numberOfSeats;
    private Semaphore trainMutex;
    private ArrayList<Robot> passengers;
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
        Semaphore trainMutex = new Semaphore(numberOfSeats);
        this.trainLocation = 0;
        this.passengers = new ArrayList<>();
    }

    @Override
    public void run()
    {
        while(trainRunning)
        {
            this.stations[this.trainLocation].setTrainOnStation(this);
            try {
                this.stations[this.trainLocation].Station_Load_Train(this);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Semaphore getTrainMutex() {
        return trainMutex;
    }

    public void setDoorStatus(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }

    public void addPassenger(Robot robot){
        passengers.add(robot);
    }
}
