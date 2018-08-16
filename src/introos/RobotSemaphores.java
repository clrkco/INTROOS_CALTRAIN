package introos;

public class RobotSemaphores implements Runnable{
    private RobotStatus robotStatus;
    private StationSemaphores stationDestination;
    private StationSemaphores stationOrigin;

    public RobotSemaphores(StationSemaphores stationOrigin, StationSemaphores stationDestination) {
        this.stationDestination = stationDestination;
        this.stationOrigin = stationOrigin;
        robotStatus = RobotStatus.WAITING;
    }

    public StationSemaphores getStationDestination() {
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

    public void setRobotStatus(RobotStatus robotStatus) {
        this.robotStatus = robotStatus;
    }
}