package gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Locks extends JFrame
{
    public Locks()
    {
        this.setTitle("CalTrainII Automation (Locks)");
        this.setSize(1200, 600);
        this.setMinimumSize(new Dimension(1200, 600));
        this.setLocation(10, 10);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new MigLayout());

        this.add(new SystemLocks(),"pushx, growx, wrap");
        this.add(new StatusLocks(),"pushx, growx, wrap");
        this.add(new ControlLocks(), "pushx, growx, wrap");
    }
}