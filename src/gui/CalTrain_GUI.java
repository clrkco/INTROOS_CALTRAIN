package gui;

public class CalTrain_GUI
{
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Locks();
                new Semaphores();
            }
        });
    }
}