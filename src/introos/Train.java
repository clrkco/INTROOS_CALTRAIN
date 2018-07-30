package introos;

import gui.Visual;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Train implements Runnable
{
    private String TRAIN_STATUS;      // Domain: IDLE, TRAVELING, LOADING
    private int TRAIN_NOOFSEATS;      // Maximum number of seats in the train
    private int TRAIN_NOOFPASSENGERS; // Number of Passengers in the train
    private Semaphore TRAIN_AVAILABLESEATS; // Computer by subtracting TRAIN_NOOFSEATS and TRAIN_AVAILABLESEATS
    private String TRAIN_NAME;        // Optional name of Train object
    private String TRAIN_DOORSTATUS;  // Domain: OPEN, CLOSED
    private boolean TRAIN_ISRUNNING; // Is it running?
    private final Station[] TRAIN_STATIONS;
    private ArrayList<Robot> TRAIN_PASSENGERS;
    private ArrayList<Robot> TRAIN_DROPOFFS;
    private int TRAIN_WHERE;
    private int trainID;

    public Train(Semaphore TRAIN_AVAILABLESEATS, int TRAIN_NOOFSEATS, String TRAIN_NAME, Station[] TRAIN_STATIONS, int id)
    {
        this.TRAIN_STATUS = "IDLE";
        this.TRAIN_NOOFSEATS = TRAIN_NOOFSEATS;
        this.TRAIN_NAME = TRAIN_NAME;
        this.TRAIN_NOOFPASSENGERS = 0;
        this.TRAIN_AVAILABLESEATS = TRAIN_AVAILABLESEATS;
        this.TRAIN_DOORSTATUS = "CLOSED";
        this.TRAIN_ISRUNNING = true;
        this.TRAIN_STATIONS = TRAIN_STATIONS;
        this.TRAIN_WHERE = 0;
        this.TRAIN_PASSENGERS = new ArrayList();
        this.TRAIN_DROPOFFS = new ArrayList();
        this.trainID = id;
    }

    public void AddPassenger(Robot passenger)
    {
        TRAIN_PASSENGERS.add(passenger);
    }

    public String getTRAIN_STATUS()
    {
        return TRAIN_STATUS;
    }

    public void setTRAIN_STATUS(String TRAIN_STATUS)
    {
        this.TRAIN_STATUS = TRAIN_STATUS;
    }

    public int getTRAIN_NOOFSEATS()
    {
        return TRAIN_NOOFSEATS;
    }

    public void setTRAIN_NOOFSEATS(int TRAIN_NOOFSEATS)
    {
        this.TRAIN_NOOFSEATS = TRAIN_NOOFSEATS;
    }

    public int getTRAIN_NOOFPASSENGERS()
    {
        return TRAIN_NOOFSEATS - this.getTRAIN_AVAILABLESEATS();
    }

    public void setTRAIN_NOOFPASSENGERS(int TRAIN_NOOFPASSENGERS)
    {
        this.TRAIN_NOOFPASSENGERS = TRAIN_NOOFPASSENGERS;
        Visual.trainSeats.get(this.getTrainID()).setText(this.getTRAIN_NOOFPASSENGERS() + "/" + this.getTRAIN_NOOFSEATS());
    }

    public int getTRAIN_AVAILABLESEATS()
    {
        return TRAIN_AVAILABLESEATS.availablePermits();
    }

    public Semaphore getSemaphore()
    {
        return TRAIN_AVAILABLESEATS;
    }

    public void setTRAIN_AVAILABLESEATS(Semaphore TRAIN_AVAILABLESEATS)
    {
        this.TRAIN_AVAILABLESEATS = TRAIN_AVAILABLESEATS;
    }

    public String getTRAIN_NAME()
    {
        return TRAIN_NAME;
    }

    public void setTRAIN_NAME(String TRAIN_NAME)
    {
        this.TRAIN_NAME = TRAIN_NAME;
    }

    public String getTRAIN_DOORSTATUS()
    {
        return TRAIN_DOORSTATUS;
    }

    public void setTRAIN_DOORSTATUS(String TRAIN_DOORSTATUS)
    {
        this.TRAIN_DOORSTATUS = TRAIN_DOORSTATUS;
    }

    public boolean isTRAIN_ISRUNNING()
    {
        return TRAIN_ISRUNNING;
    }

    public void setTRAIN_ISRUNNING(boolean TRAIN_ISRUNNING)
    {
        this.TRAIN_ISRUNNING = TRAIN_ISRUNNING;
    }

    public int getTrainID()
    {
        return trainID;
    }

    public int getTRAIN_WHERE()
    {
        return TRAIN_WHERE;
    }

    @Override
    public void run()
    {

    }
}