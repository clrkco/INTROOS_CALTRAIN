package introos;

import gui.StatusLocks;
import gui.SystemLocks;
import main.CalTrain_Semaphores;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationLocks
{
    private String STATION_STATUS;            // Domain: IDLE, OCCUPIED
    private String STATION_NAME;              // Optional name for Station object
    private int STATION_PASSENGERSWAITING;     // Number of Passengers waiting in the train station
    private TrainLocks TRAIN_ONSTATION;            // The current Train object in the station
    private boolean STATION_HASTRAIN;         // Does the station have a train?
    private Lock STATION_LOCK;
    private Condition STATION_CONDITION;
    private ArrayList<Thread> STATION_ROBOTS;
    private ArrayList<RobotLocks> ROBOT_OBJECT;
    private Semaphore STATION_MUTEX;

    public StationLocks(String STATION_NAME)
    {
        this.Station_Init(STATION_NAME);

    }

    public void GeneratePassengers(int STATION_PASSENGERSWAITING, int station_destination)
    {
        this.STATION_PASSENGERSWAITING += STATION_PASSENGERSWAITING;
        SystemLocks.setWaiting(this.STATION_NAME, this.STATION_PASSENGERSWAITING);

        for(int i = 0; i < STATION_PASSENGERSWAITING; i++)
        {
            RobotLocks passenger = new RobotLocks(this, station_destination);
            ROBOT_OBJECT.add(passenger);
            STATION_ROBOTS.add(new Thread(passenger));
            STATION_ROBOTS.get(STATION_ROBOTS.size() - 1).start();
        }
    }

    private void Station_Init(String STATION_NAME)
    {
        this.STATION_STATUS = "IDLE";
        this.TRAIN_ONSTATION = null;
        this.STATION_PASSENGERSWAITING = 0;
        this.STATION_HASTRAIN = false;
        this.STATION_NAME = STATION_NAME;
        this.ROBOT_OBJECT = new ArrayList();
        this.STATION_ROBOTS = new ArrayList();

        this.lock_init();
        this.cond_init();
        this.mutex_init();
    }

    public synchronized void Station_Load_Train(int TRAIN_AVAILABLESEATS)
    {
        this.mutex_acquire();
        SystemLocks.setOccupied(this.STATION_NAME, this.getTRAIN_ONSTATION().getTRAIN_NAME());

        if(STATION_PASSENGERSWAITING == 0)
        {
            SystemLocks.setWaiting(this.STATION_NAME, STATION_PASSENGERSWAITING);
        }

        else
        {
            this.notifyAll();
        }

        try
        {
            StatusLocks.trainStatus.get(TRAIN_ONSTATION.getTrainID()).setText("Arrived at " + CalTrain_Semaphores.stationNames[TRAIN_ONSTATION.getTRAIN_WHERE()]);
            Thread.sleep(3000);
        }

        catch (InterruptedException ex)
        {
            Logger.getLogger(StationLocks.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mutex_release();
        SystemLocks.setFree(this.STATION_NAME);
    }

    public synchronized void Station_Wait_For_Train(RobotLocks passenger)
    {
        this.mutex_acquire();
        this.mutex_release();

        try
        {
            wait();
        }

        catch (InterruptedException ex)
        {
            Logger.getLogger(StationLocks.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.Station_On_Board(passenger);
    }

    public void Station_On_Board(RobotLocks passenger)
    {
        this.mutex_acquire();

        try
        {
            if(TRAIN_ONSTATION.getTRAIN_AVAILABLESEATS() == 0)
            {
                this.mutex_release();

                Station_Wait_For_Train(passenger);
            }

            else
            {
                int shit = this.TRAIN_ONSTATION.getTRAIN_NOOFPASSENGERS();

                try
                {
                    TRAIN_ONSTATION.getSemaphore().acquire();

                }

                catch (InterruptedException ex)
                {
                    Logger.getLogger(StationLocks.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.STATION_PASSENGERSWAITING--;
                SystemLocks.setWaiting(STATION_NAME, STATION_PASSENGERSWAITING);
                int currNoOfPassengers = TRAIN_ONSTATION.getTRAIN_NOOFPASSENGERS() + 1;
                TRAIN_ONSTATION.setTRAIN_NOOFPASSENGERS(shit + 1);

                TRAIN_ONSTATION.AddPassenger(passenger);
                ROBOT_OBJECT.remove(passenger);

                System.out.println(passenger.getROBOT_NAME() + " boarded the train");
                System.out.println("Number of available seats of train: " + TRAIN_ONSTATION.getTRAIN_AVAILABLESEATS() + " Train: " + TRAIN_ONSTATION.getTRAIN_NAME());
                System.out.println("Number of Passengers = " + TRAIN_ONSTATION.getTRAIN_NOOFPASSENGERS());
            }
        }

        finally
        {

        }

        this.mutex_release();
    }

    public void mutex_init()
    {
        this.STATION_MUTEX = new Semaphore(1);
    }

    public void mutex_acquire()
    {
        try
        {
            this.STATION_MUTEX.acquire();
        }

        catch (InterruptedException ex)
        {
            Logger.getLogger(StationLocks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mutex_release()
    {
        this.STATION_MUTEX.release();
    }

    public void lock_init()
    {
        STATION_LOCK = new ReentrantLock();
    }

    public void lock_acquire()
    {
        STATION_LOCK.lock();
    }

    public void lock_release()
    {
        STATION_LOCK.unlock();
    }

    public  void cond_init()
    {
        STATION_CONDITION = STATION_LOCK.newCondition();
    }

    public void cond_wait()
    {
        try
        {
            STATION_CONDITION.await();
        }

        catch (InterruptedException ex)
        {
            Logger.getLogger(StationLocks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public  void cond_signal()
    {
        STATION_CONDITION.signal();
    }

    public void cond_broadcast()
    {
        STATION_CONDITION.signalAll();
    }

    public String getSTATION_STATUS()
    {
        return STATION_STATUS;
    }

    public void setSTATION_STATUS(String STATION_STATUS)
    {
        this.STATION_STATUS = STATION_STATUS;
    }

    public String getSTATION_NAME()
    {
        return STATION_NAME;
    }

    public void setSTATION_NAME(String STATION_NAME)
    {
        this.STATION_NAME = STATION_NAME;
    }

    public int getSTATION_PASSNGERSWAITING()
    {
        return STATION_PASSENGERSWAITING;
    }

    public void setSTATION_PASSNGERSWAITING(int STATION_PASSNGERSWAITING)
    {
        this.STATION_PASSENGERSWAITING = STATION_PASSNGERSWAITING;
    }

    public TrainLocks getTRAIN_ONSTATION()
    {
        return TRAIN_ONSTATION;
    }

    public void setTRAIN_ONSTATION(TrainLocks TRAIN_ONSTATION)
    {
        this.TRAIN_ONSTATION = TRAIN_ONSTATION;
    }

    public void Station_Add_Passengers()
    {
        this.STATION_PASSENGERSWAITING += NumberGenerator.GENERATE_PASSENGER_INFLUX();
    }

    public boolean isSTATION_HASTRAIN()
    {
        return STATION_HASTRAIN;
    }

    public void setSTATION_HASTRAIN(boolean STATION_HASTRAIN)
    {
        this.STATION_HASTRAIN = STATION_HASTRAIN;
    }
}