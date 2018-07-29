package introos;

public class Robot implements Runnable{
    private RobotStatus robotStatus;
    private Station stationDestination;
    private Station stationOrigin;

    public Robot(Station stationOrigin, Station stationDestination) {
        this.stationDestination = stationDestination;
        this.stationOrigin = stationOrigin;
        robotStatus = RobotStatus.WAITING;
    }

    public Station getStationDestination() {
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
