package gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatusSemaphores extends JPanel implements ActionListener
{
    public static ArrayList<JPanel> trainPanels = new ArrayList(16);
    public static ArrayList<JLabel> trainName = new ArrayList(16);
    public static ArrayList<JLabel> trainSeats = new ArrayList(16);
    public static ArrayList<JLabel> trainStatus = new ArrayList(16);
    public static ArrayList<JLabel> trainStatusHead = new ArrayList(16);

    Font styleFont = new Font("Arial", Font.BOLD, 18);

    public StatusSemaphores()
    {
        this.setLayout(new MigLayout("", "[150, fill, grow]", "[150, fill,  grow]"));
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Trains",
                TitledBorder.CENTER, TitledBorder.TOP));

        buildComponents();
        assembleComponents();
    }

    public void buildComponents()
    {
        for(int i = 0; i < 16; i++)
        {
            trainPanels.add(new JPanel(new MigLayout("")));
            trainPanels.get(i).setBackground(Color.pink);
            trainPanels.get(i).add(new JLabel("Train " + (i + 1)), "pushx, center, wrap 10");

            trainName.add(new JLabel(""));
            trainName.get(i).setFont(styleFont);
            trainName.get(i).setForeground(Color.black);

            trainSeats.add(new JLabel(""));
            trainSeats.get(i).setFont(styleFont);
            trainSeats.get(i).setForeground(Color.black);

            trainStatus.add(new JLabel(""));
            trainStatus.get(i).setFont(styleFont);
            trainStatus.get(i).setForeground(Color.black);

            trainStatusHead.add(new JLabel(""));
            trainStatusHead.get(i).setFont(styleFont);
            trainStatusHead.get(i).setForeground(Color.black);
        }
    }

    public void assembleComponents()
    {
        for(int i = 0 ; i < 16; i++)
        {
            trainPanels.get(i).add(trainName.get(i), "pushx, center, wrap 5");
            trainPanels.get(i).add(trainSeats.get(i), "pushx, center, wrap");
            trainPanels.get(i).add(trainStatusHead.get(i), "pushx, center, wrap");
            trainPanels.get(i).add(trainStatus.get(i), "pushx, center, wrap");

            this.add(trainPanels.get(i), "cell " + i%8 + " " + i/8);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}