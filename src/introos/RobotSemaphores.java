package introos;

public class RobotSemaphores implements Runnable
{
    private static int TOTAL_ROBOT_NUM = 0; //Tracks the number of spawned Robots
    private String ROBOT_NAME;
    private String ROBOT_STATUS; // Stasuses: 'WAITING' & 'ONBOARD'
    private int ROBOT_NOOFSTATION; // Max of 7 stations
    private final StationSemaphores ROBOT_STATION;

    public RobotSemaphores(StationSemaphores ROBOT_STATION, int ROBOT_NOOFSTATION)
    {
        TOTAL_ROBOT_NUM++;

        RobotNameGenerator nameGenerator = new RobotNameGenerator();

        this.ROBOT_NAME = "[" + TOTAL_ROBOT_NUM + "]" + nameGenerator.generateName();
        this.ROBOT_STATUS = "WAITING";
        this.ROBOT_NOOFSTATION = ROBOT_NOOFSTATION;
        this.ROBOT_STATION = ROBOT_STATION;
    }

    public static int getTOTAL_ROBOT_NUM()
    {
        return TOTAL_ROBOT_NUM;
    }

    public String getROBOT_NAME()
    {
        return ROBOT_NAME;
    }

    public String getROBOT_STATUS()
    {
        return ROBOT_STATUS;
    }

    public int getROBOT_NOOFSTATION()
    {
        return ROBOT_NOOFSTATION;
    }

    public static void setTOTAL_ROBOT_NUM(int aTOTAL_ROBOT_NUM)
    {
        TOTAL_ROBOT_NUM = aTOTAL_ROBOT_NUM;
    }

    public void setROBOT_NAME(String ROBOT_NAME)
    {
        this.ROBOT_NAME = ROBOT_NAME;
    }

    public void setROBOT_STATUS(String ROBOT_STATUS)
    {
        this.ROBOT_STATUS = ROBOT_STATUS;
    }

    public void setROBOT_NOOFSTATION(int ROBOT_NOOFSTATION)
    {
        this.ROBOT_NOOFSTATION = ROBOT_NOOFSTATION;
    }

    public void UpdateDestination()
    {
        this.ROBOT_NOOFSTATION--;
    }

    @Override
    public void run()
    {
        ROBOT_STATION.Station_Wait_For_Train(this);
    }
}