package gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Semaphores extends JFrame
{
    public Semaphores()
    {
        this.setTitle("CalTrainII Automation (Semaphores)");
        this.setSize(1200, 600);
        this.setMinimumSize(new Dimension(1200, 600));
        this.setLocation(10, 10);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new MigLayout());

        this.add(new SystemSemaphores(),"pushx, growx, wrap");
        this.add(new StatusSemaphores(),"pushx, growx, wrap");
        this.add(new ControlSemaphores(), "pushx, growx, wrap");
    }
}