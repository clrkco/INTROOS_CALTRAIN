package gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame
{
    public Frame()
    {
        this.setTitle("CalTrainII Automation");
        this.setSize(1200, 600);
        this.setMinimumSize(new Dimension(1200, 600));
        this.setLocation(10, 10);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new MigLayout());

        this.add(new System(),"pushx, growx, wrap");
        this.add(new Visual(),"pushx, growx, wrap");
        this.add(new Control(), "pushx, growx, wrap");
    }
}