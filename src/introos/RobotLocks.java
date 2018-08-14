package introos;

public class RobotLocks implements Runnable
{
    private RobotStatus robotStatus;
    private StationLocks stationDestination;
    private StationLocks stationOrigin;

    public RobotLocks(StationLocks stationOrigin, StationLocks stationDestination) {
        this.stationDestination = stationDestination;
        this.stationOrigin = stationOrigin;
        robotStatus = RobotStatus.WAITING;
    }

    public StationLocks getStationDestination() {
        return stationDestination;
    }
    @Override
    public void run(){
        try {
            stationOrigin.Station_Wait_For_Train(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public RobotStatus getRobotStatus() {
        return robotStatus;
    }

    public void setRobotStatus(RobotStatus robotStatus) {
        this.robotStatus = robotStatus;
    }
}