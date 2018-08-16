package gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemLocks extends JPanel implements ActionListener
{
    static JLabel
            Station1Label,
            TrackALabel,
            Station2Label,
            TrackBLabel,
            Station3Label,
            TrackCLabel,
            Station4Label,
            TrackDLabel,
            Station5Label,
            TrackELabel,
            Station6Label,
            TrackFLabel,
            Station7Label,
            TrackGLabel,
            Station8Label,
            TrackHLabel,
            Waiting1Label,
            Waiting2Label,
            Waiting3Label,
            Waiting4Label,
            Waiting5Label,
            Waiting6Label,
            Waiting7Label,
            Waiting8Label;

    static JPanel
            Station1Panel,
            TrackAPanel,
            Station2Panel,
            TrackBPanel,
            Station3Panel,
            TrackCPanel,
            Station4Panel,
            TrackDPanel,
            Station5Panel,
            TrackEPanel,
            Station6Panel,
            TrackFPanel,
            Station7Panel,
            TrackGPanel,
            Station8Panel,
            TrackHPanel;

    Font styleFont = new Font("Arial", Font.BOLD, 18);

    public SystemLocks()
    {
        this.setLayout(new MigLayout("", "[150,fill, grow]", "[150,fill]"));
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "CalTrainII System",
                TitledBorder.CENTER, TitledBorder.TOP));
        
        buildComponents();
        assembleComponents();
    }

    public void buildComponents()
    {
        Station1Label = new JLabel("");
        Station1Label.setFont(styleFont);
        
        TrackALabel = new JLabel("");
        TrackALabel.setFont(styleFont);

        Station2Label = new JLabel("");
        Station2Label.setFont(styleFont);

        TrackBLabel = new JLabel("");
        TrackBLabel.setFont(styleFont);

        Station3Label = new JLabel("");
        Station3Label.setFont(styleFont);

        TrackCLabel = new JLabel("");
        TrackCLabel.setFont(styleFont);

        Station4Label = new JLabel("");
        Station4Label.setFont(styleFont);

        TrackDLabel = new JLabel("");
        TrackDLabel.setFont(styleFont);

        Station5Label = new JLabel("");
        Station5Label.setFont(styleFont);

        TrackELabel = new JLabel("");
        TrackELabel.setFont(styleFont);

        Station6Label = new JLabel("");
        Station6Label.setFont(styleFont);

        TrackFLabel = new JLabel("");
        TrackFLabel.setFont(styleFont);

        Station7Label = new JLabel("");
        Station7Label.setFont(styleFont);

        TrackGLabel = new JLabel("");
        TrackGLabel.setFont(styleFont);

        Station8Label = new JLabel("");
        Station8Label.setFont(styleFont);

        TrackHLabel = new JLabel("");
        TrackHLabel.setFont(styleFont);

        Waiting1Label = new JLabel("0");
        Waiting1Label.setFont(styleFont);

        Waiting2Label = new JLabel("0");
        Waiting2Label.setFont(styleFont);

        Waiting3Label = new JLabel("0");
        Waiting3Label.setFont(styleFont);

        Waiting4Label = new JLabel("0");
        Waiting4Label.setFont(styleFont);

        Waiting5Label = new JLabel("0");
        Waiting5Label.setFont(styleFont);

        Waiting6Label = new JLabel("0");
        Waiting6Label.setFont(styleFont);

        Waiting7Label = new JLabel("0");
        Waiting7Label.setFont(styleFont);

        Waiting8Label = new JLabel("0");
        Waiting8Label.setFont(styleFont);

        Station1Panel = new JPanel(new MigLayout());
        Station1Panel.setBackground(Color.pink);
        Station1Panel.add(new JLabel("Station 1"), "pushx, center, wrap 10");
        Station1Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station1Panel.add(Waiting1Label, "pushx, center, wrap 10, gaptop 10");
        Station1Panel.add(Station1Label, "pushx, center");

        TrackAPanel = new JPanel(new MigLayout());
        TrackAPanel.setBackground(Color.pink);
        TrackAPanel.add(new JLabel("Track A"), "pushx, center, wrap 10");
        TrackAPanel.add(TrackALabel, "pushx, center");

        Station2Panel = new JPanel(new MigLayout());
        Station2Panel.setBackground(Color.pink);
        Station2Panel.add(new JLabel("Station 2"), "pushx, center, wrap 10");
        Station2Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station2Panel.add(Waiting2Label, "pushx, center, wrap 10, gaptop 10");
        Station2Panel.add(Station2Label, "pushx, center");

        TrackBPanel = new JPanel(new MigLayout());
        TrackBPanel.setBackground(Color.pink);
        TrackBPanel.add(new JLabel("Track B"), "pushx, center, wrap 10");
        TrackBPanel.add(TrackBLabel, "pushx, center");

        Station3Panel = new JPanel(new MigLayout());
        Station3Panel.setBackground(Color.pink);
        Station3Panel.add(new JLabel("Station 3"), "pushx, center, wrap 10");
        Station3Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station3Panel.add(Waiting3Label, "pushx, center, wrap 10, gaptop 10");
        Station3Panel.add(Station3Label, "pushx, center");

        TrackCPanel = new JPanel(new MigLayout());
        TrackCPanel.setBackground(Color.pink);
        TrackCPanel.add(new JLabel("Track C"), "pushx, center, wrap 10");
        TrackCPanel.add(TrackCLabel, "pushx, center");

        Station4Panel = new JPanel(new MigLayout());
        Station4Panel.setBackground(Color.pink);
        Station4Panel.add(new JLabel("Station 4"), "pushx, center, wrap 10");
        Station4Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station4Panel.add(Waiting4Label, "pushx, center, wrap 10, gaptop 10");
        Station4Panel.add(Station4Label, "pushx, center");

        TrackDPanel = new JPanel(new MigLayout());
        TrackDPanel.setBackground(Color.pink);
        TrackDPanel.add(new JLabel("Track D"), "pushx, center, wrap 10");
        TrackDPanel.add(TrackDLabel, "pushx, center");

        Station5Panel = new JPanel(new MigLayout());
        Station5Panel.setBackground(Color.pink);
        Station5Panel.add(new JLabel("Station 5"), "pushx, center, wrap 10");
        Station5Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station5Panel.add(Waiting5Label, "pushx, center, wrap 10, gaptop 10");
        Station5Panel.add(Station5Label, "pushx, center");

        TrackEPanel = new JPanel(new MigLayout());
        TrackEPanel.setBackground(Color.pink);
        TrackEPanel.add(new JLabel("Track E"), "pushx, center, wrap 10");
        TrackEPanel.add(TrackELabel, "pushx, center");

        Station6Panel = new JPanel(new MigLayout());
        Station6Panel.setBackground(Color.pink);
        Station6Panel.add(new JLabel("Station 6"), "pushx, center, wrap 10");
        Station6Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station6Panel.add(Waiting6Label, "pushx, center, wrap 10, gaptop 10");
        Station6Panel.add(Station6Label, "pushx, center");

        TrackFPanel = new JPanel(new MigLayout());
        TrackFPanel.setBackground(Color.pink);
        TrackFPanel.add(new JLabel("Track F"), "pushx, center, wrap 10");
        TrackFPanel.add(TrackFLabel, "pushx, center");

        Station7Panel = new JPanel(new MigLayout());
        Station7Panel.setBackground(Color.pink);
        Station7Panel.add(new JLabel("Station 7"), "pushx, center, wrap 10");
        Station7Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station7Panel.add(Waiting7Label, "pushx, center, wrap 10, gaptop 10");
        Station7Panel.add(Station7Label, "pushx, center");

        TrackGPanel = new JPanel(new MigLayout());
        TrackGPanel.setBackground(Color.pink);
        TrackGPanel.add(new JLabel("Track G"), "pushx, center, wrap 10");
        TrackGPanel.add(TrackGLabel, "pushx, center");

        Station8Panel = new JPanel(new MigLayout());
        Station8Panel.setBackground(Color.pink);
        Station8Panel.add(new JLabel("Station 8"), "pushx, center, wrap 10");
        Station8Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station8Panel.add(Waiting8Label, "pushx, center, wrap 10, gaptop 10");
        Station8Panel.add(Station8Label, "pushx, center");

        TrackHPanel = new JPanel(new MigLayout());
        TrackHPanel.setBackground(Color.pink);
        TrackHPanel.add(new JLabel("Track H"), "pushx, center, wrap 10");
        TrackHPanel.add(TrackHLabel, "pushx, center");
    }

    public void assembleComponents()
    {
        this.add(Station1Panel, "cell 0 0");
        this.add(TrackAPanel, "cell 1 0");
        this.add(Station2Panel, "cell 2 0");
        this.add(TrackBPanel, "cell 3 0");
        this.add(Station3Panel, "cell 4 0");
        this.add(TrackCPanel, "cell 5 0");
        this.add(Station4Panel, "cell 6 0");
        this.add(TrackDPanel, "cell 7 0");
        this.add(Station5Panel, "cell 0 1");
        this.add(TrackEPanel, "cell 1 1");
        this.add(Station6Panel, "cell 2 1");
        this.add(TrackFPanel, "cell 3 1");
        this.add(Station7Panel, "cell 4 1");
        this.add(TrackGPanel, "cell 5 1");
        this.add(Station8Panel, "cell 6 1");
        this.add(TrackHPanel, "cell 7 1");
    }

    public static void setOccupied(String stationName, String trainName)
    {
        switch(stationName)
        {
            case "Station1":
                Station1Panel.setBackground(Color.green);
                Station1Label.setText(trainName);
                break;
            case "TrackA":
                TrackAPanel.setBackground(Color.green);
                TrackALabel.setText(trainName);
                break;
            case "Station2":
                Station2Panel.setBackground(Color.green);
                Station2Label.setText(trainName);
                break;
            case "TrackB":
                TrackBPanel.setBackground(Color.green);
                TrackBLabel.setText(trainName);
                break;
            case "Station3":
                Station3Panel.setBackground(Color.green);
                Station3Label.setText(trainName);
                break;
            case "TrackC":
                TrackCPanel.setBackground(Color.green);
                TrackCLabel.setText(trainName);
                break;
            case "Station4":
                Station4Panel.setBackground(Color.green);
                Station4Label.setText(trainName);
                break;
            case "TrackD":
                TrackDPanel.setBackground(Color.green);
                TrackDLabel.setText(trainName);
                break;
            case "Station5":
                Station5Panel.setBackground(Color.green);
                Station5Label.setText(trainName);
                break;
            case "TrackE":
                TrackEPanel.setBackground(Color.green);
                TrackELabel.setText(trainName);
                break;
            case "Station6":
                Station6Panel.setBackground(Color.green);
                Station6Label.setText(trainName);
                break;
            case "TrackF":
                TrackFPanel.setBackground(Color.green);
                TrackFLabel.setText(trainName);
                break;
            case "Station7":
                Station7Panel.setBackground(Color.green);
                Station7Label.setText(trainName);
                break;
            case "TrackG":
                TrackGPanel.setBackground(Color.green);
                TrackGLabel.setText(trainName);
                break;
            case "Station8":
                Station8Panel.setBackground(Color.green);
                Station8Label.setText(trainName);
                break;
            case "TrackH":
                TrackHPanel.setBackground(Color.green);
                TrackHLabel.setText(trainName);
                break;
        }
    }

    public static void setFree(String stationName)
    {
        switch(stationName)
        {
            case "Station1":
                Station1Panel.setBackground(Color.pink);
                Station1Label.setText("");
                break;
            case "TrackA":
                TrackAPanel.setBackground(Color.pink);
                TrackALabel.setText("");
                break;
            case "Station2":
                Station2Panel.setBackground(Color.pink);
                Station2Label.setText("");
                break;
            case "TrackB":
                TrackBPanel.setBackground(Color.pink);
                TrackBLabel.setText("");
                break;
            case "Station3":
                Station3Panel.setBackground(Color.pink);
                Station3Label.setText("");
                break;
            case "TrackC":
                TrackCPanel.setBackground(Color.pink);
                TrackCLabel.setText("");
                break;
            case "Station4":
                Station4Panel.setBackground(Color.pink);
                Station4Label.setText("");
                break;
            case "TrackD":
                TrackDPanel.setBackground(Color.pink);
                TrackDLabel.setText("");
                break;
            case "Station5":
                Station5Panel.setBackground(Color.pink);
                Station5Label.setText("");
                break;
            case "TrackE":
                TrackEPanel.setBackground(Color.pink);
                TrackELabel.setText("");
                break;
            case "Station6":
                Station6Panel.setBackground(Color.pink);
                Station6Label.setText("");
                break;
            case "TrackF":
                TrackFPanel.setBackground(Color.pink);
                TrackFLabel.setText("");
                break;
            case "Station7":
                Station7Panel.setBackground(Color.pink);
                Station7Label.setText("");
                break;
            case "TrackG":
                TrackGPanel.setBackground(Color.pink);
                TrackGLabel.setText("");
                break;
            case "Station8":
                Station8Panel.setBackground(Color.pink);
                Station8Label.setText("");
                break;
            case "TrackH":
                TrackHPanel.setBackground(Color.pink);
                TrackHLabel.setText("");
                break;
        }
    }

    public static void setWaiting(String stationName, int waiting)
    {
        switch(stationName)
        {
            case "Station1":
                Waiting1Label.setText(waiting + "");
                break;
            case "Station2":
                Waiting2Label.setText(waiting + "");
                break;
            case "Station3":
                Waiting3Label.setText(waiting + "");
                break;
            case "Station4":
                Waiting4Label.setText(waiting + "");
                break;
            case "Station5":
                Waiting5Label.setText(waiting + "");
                break;
            case "Station6":
                Waiting6Label.setText(waiting + "");
                break;
            case "Station7":
                Waiting7Label.setText(waiting + "");
                break;
            case "Station8":
                Waiting8Label.setText(waiting + "");
                break;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }
}