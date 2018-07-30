package introos;

public class NumberGenerator
{
    public static final int MAX_INCOMING_PASSENGER = 100;

    public static int GENERATE_PASSENGER_INFLUX()
    {
        return (int)Math.ceil(Math.random() * MAX_INCOMING_PASSENGER);
    }
    
}