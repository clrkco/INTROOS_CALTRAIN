package gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class System extends JPanel implements ActionListener
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

    Font stationFont = new Font("Georgia", Font.PLAIN, 20);
    
    public System()
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
        Station1Label.setFont(stationFont);
        
        TrackALabel = new JLabel("");
        TrackALabel.setFont(stationFont);

        Station2Label = new JLabel("");
        Station2Label.setFont(stationFont);

        TrackBLabel = new JLabel("");
        TrackBLabel.setFont(stationFont);

        Station3Label = new JLabel("");
        Station3Label.setFont(stationFont);

        TrackCLabel = new JLabel("");
        TrackCLabel.setFont(stationFont);

        Station4Label = new JLabel("");
        Station4Label.setFont(stationFont);

        TrackDLabel = new JLabel("");
        TrackDLabel.setFont(stationFont);

        Station5Label = new JLabel("");
        Station5Label.setFont(stationFont);

        TrackELabel = new JLabel("");
        TrackELabel.setFont(stationFont);

        Station6Label = new JLabel("");
        Station6Label.setFont(stationFont);

        TrackFLabel = new JLabel("");
        TrackFLabel.setFont(stationFont);

        Station7Label = new JLabel("");
        Station7Label.setFont(stationFont);

        TrackGLabel = new JLabel("");
        TrackGLabel.setFont(stationFont);

        Station8Label = new JLabel("");
        Station8Label.setFont(stationFont);

        TrackHLabel = new JLabel("");
        TrackHLabel.setFont(stationFont);

        Waiting1Label = new JLabel("0");
        Waiting1Label.setFont(stationFont);

        Waiting2Label = new JLabel("0");
        Waiting2Label.setFont(stationFont);

        Waiting3Label = new JLabel("0");
        Waiting3Label.setFont(stationFont);

        Waiting4Label = new JLabel("0");
        Waiting4Label.setFont(stationFont);

        Waiting5Label = new JLabel("0");
        Waiting5Label.setFont(stationFont);

        Waiting6Label = new JLabel("0");
        Waiting6Label.setFont(stationFont);

        Waiting7Label = new JLabel("0");
        Waiting7Label.setFont(stationFont);

        Waiting8Label = new JLabel("0");
        Waiting8Label.setFont(stationFont);

        Station1Panel = new JPanel(new MigLayout());
        Station1Panel.setBackground(Color.white);
        Station1Panel.add(new JLabel("Station 1"), "pushx, center, wrap 10");
        Station1Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station1Panel.add(Waiting1Label, "pushx, center, wrap 10, gaptop 10");
        Station1Panel.add(Station1Label, "pushx, center");

        TrackAPanel = new JPanel(new MigLayout());
        TrackAPanel.setBackground(Color.white);
        TrackAPanel.add(new JLabel("Track A"), "pushx, center, wrap 10");
        TrackAPanel.add(TrackALabel, "pushx, center");

        Station2Panel = new JPanel(new MigLayout());
        Station2Panel.setBackground(Color.white);
        Station2Panel.add(new JLabel("Station 2"), "pushx, center, wrap 10");
        Station2Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station2Panel.add(Waiting2Label, "pushx, center, wrap 10, gaptop 10");
        Station2Panel.add(Station2Label, "pushx, center");

        TrackBPanel = new JPanel(new MigLayout());
        TrackBPanel.setBackground(Color.white);
        TrackBPanel.add(new JLabel("Track B"), "pushx, center, wrap 10");
        TrackBPanel.add(TrackBLabel, "pushx, center");

        Station3Panel = new JPanel(new MigLayout());
        Station3Panel.setBackground(Color.white);
        Station3Panel.add(new JLabel("Station 3"), "pushx, center, wrap 10");
        Station3Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station3Panel.add(Waiting3Label, "pushx, center, wrap 10, gaptop 10");
        Station3Panel.add(Station3Label, "pushx, center");

        TrackCPanel = new JPanel(new MigLayout());
        TrackCPanel.setBackground(Color.white);
        TrackCPanel.add(new JLabel("Track C"), "pushx, center, wrap 10");
        TrackCPanel.add(TrackCLabel, "pushx, center");

        Station4Panel = new JPanel(new MigLayout());
        Station4Panel.setBackground(Color.white);
        Station4Panel.add(new JLabel("Station 4"), "pushx, center, wrap 10");
        Station4Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station4Panel.add(Waiting4Label, "pushx, center, wrap 10, gaptop 10");
        Station4Panel.add(Station4Label, "pushx, center");

        TrackDPanel = new JPanel(new MigLayout());
        TrackDPanel.setBackground(Color.white);
        TrackDPanel.add(new JLabel("Track D"), "pushx, center, wrap 10");
        TrackDPanel.add(TrackDLabel, "pushx, center");

        Station5Panel = new JPanel(new MigLayout());
        Station5Panel.setBackground(Color.white);
        Station5Panel.add(new JLabel("Station 5"), "pushx, center, wrap 10");
        Station5Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station5Panel.add(Waiting5Label, "pushx, center, wrap 10, gaptop 10");
        Station5Panel.add(Station5Label, "pushx, center");

        TrackEPanel = new JPanel(new MigLayout());
        TrackEPanel.setBackground(Color.white);
        TrackEPanel.add(new JLabel("Track E"), "pushx, center, wrap 10");
        TrackEPanel.add(TrackELabel, "pushx, center");

        Station6Panel = new JPanel(new MigLayout());
        Station6Panel.setBackground(Color.white);
        Station6Panel.add(new JLabel("Station 6"), "pushx, center, wrap 10");
        Station6Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station6Panel.add(Waiting6Label, "pushx, center, wrap 10, gaptop 10");
        Station6Panel.add(Station6Label, "pushx, center");

        TrackFPanel = new JPanel(new MigLayout());
        TrackFPanel.setBackground(Color.white);
        TrackFPanel.add(new JLabel("Track F"), "pushx, center, wrap 10");
        TrackFPanel.add(TrackFLabel, "pushx, center");

        Station7Panel = new JPanel(new MigLayout());
        Station7Panel.setBackground(Color.white);
        Station7Panel.add(new JLabel("Station 7"), "pushx, center, wrap 10");
        Station7Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station7Panel.add(Waiting7Label, "pushx, center, wrap 10, gaptop 10");
        Station7Panel.add(Station7Label, "pushx, center");

        TrackGPanel = new JPanel(new MigLayout());
        TrackGPanel.setBackground(Color.white);
        TrackGPanel.add(new JLabel("Track G"), "pushx, center, wrap 10");
        TrackGPanel.add(TrackGLabel, "pushx, center");

        Station8Panel = new JPanel(new MigLayout());
        Station8Panel.setBackground(Color.white);
        Station8Panel.add(new JLabel("Station 8"), "pushx, center, wrap 10");
        Station8Panel.add(new JLabel("Passengers Waiting:"), "pushx, center");
        Station8Panel.add(Waiting8Label, "pushx, center, wrap 10, gaptop 10");
        Station8Panel.add(Station8Label, "pushx, center");

        TrackHPanel = new JPanel(new MigLayout());
        TrackHPanel.setBackground(Color.white);
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
            case "Station 1":
                Station1Panel.setBackground(Color.white);
                Station1Label.setText(trainName);
                break;
            case "Track A":
                TrackAPanel.setBackground(Color.white);
                TrackALabel.setText(trainName);
                break;
            case "Station 2":
                Station2Panel.setBackground(Color.white);
                Station2Label.setText(trainName);
                break;
            case "Track B":
                TrackBPanel.setBackground(Color.white);
                TrackBLabel.setText(trainName);
                break;
            case "Station 3":
                Station3Panel.setBackground(Color.white);
                Station3Label.setText(trainName);
                break;
            case "Track C":
                TrackCPanel.setBackground(Color.white);
                TrackCLabel.setText(trainName);
                break;
            case "Station 4":
                Station4Panel.setBackground(Color.white);
                Station4Label.setText(trainName);
                break;
            case "Track D":
                TrackDPanel.setBackground(Color.white);
                TrackDLabel.setText(trainName);
                break;
            case "Station 5":
                Station5Panel.setBackground(Color.white);
                Station5Label.setText(trainName);
                break;
            case "Track E":
                TrackEPanel.setBackground(Color.white);
                TrackELabel.setText(trainName);
                break;
            case "Station 6":
                Station6Panel.setBackground(Color.white);
                Station6Label.setText(trainName);
                break;
            case "Track F":
                TrackFPanel.setBackground(Color.white);
                TrackFLabel.setText(trainName);
                break;
            case "Station 7":
                Station7Panel.setBackground(Color.white);
                Station7Label.setText(trainName);
                break;
            case "Track G":
                TrackGPanel.setBackground(Color.white);
                TrackGLabel.setText(trainName);
                break;
            case "Station 8":
                Station8Panel.setBackground(Color.white);
                Station8Label.setText(trainName);
                break;
            case "Track H":
                TrackHPanel.setBackground(Color.white);
                TrackHLabel.setText(trainName);
                break;
        }
    }

    public static void setFree(String stationName)
    {
        switch(stationName)
        {
            case "Station 1":
                Station1Panel.setBackground(Color.white);
                Station1Label.setText(stationName);
                break;
            case "Track A":
                TrackAPanel.setBackground(Color.white);
                TrackALabel.setText(stationName);
                break;
            case "Station 2":
                Station2Panel.setBackground(Color.white);
                Station2Label.setText(stationName);
                break;
            case "Track B":
                TrackBPanel.setBackground(Color.white);
                TrackBLabel.setText(stationName);
                break;
            case "Station 3":
                Station3Panel.setBackground(Color.white);
                Station3Label.setText(stationName);
                break;
            case "Track C":
                TrackCPanel.setBackground(Color.white);
                TrackCLabel.setText(stationName);
                break;
            case "Station 4":
                Station4Panel.setBackground(Color.white);
                Station4Label.setText(stationName);
                break;
            case "Track D":
                TrackDPanel.setBackground(Color.white);
                TrackDLabel.setText(stationName);
                break;
            case "Station 5":
                Station5Panel.setBackground(Color.white);
                Station5Label.setText(stationName);
                break;
            case "Track E":
                TrackEPanel.setBackground(Color.white);
                TrackELabel.setText(stationName);
                break;
            case "Station 6":
                Station6Panel.setBackground(Color.white);
                Station6Label.setText(stationName);
                break;
            case "Track F":
                TrackFPanel.setBackground(Color.white);
                TrackFLabel.setText(stationName);
                break;
            case "Station 7":
                Station7Panel.setBackground(Color.white);
                Station7Label.setText(stationName);
                break;
            case "Track G":
                TrackGPanel.setBackground(Color.white);
                TrackGLabel.setText(stationName);
                break;
            case "Station 8":
                Station8Panel.setBackground(Color.white);
                Station8Label.setText(stationName);
                break;
            case "Track H":
                TrackHPanel.setBackground(Color.white);
                TrackHLabel.setText(stationName);
                break;
        }
    }

    public static void setWaiting(String stationName, int waiting)
    {
        switch(stationName)
        {
            case "Station 1":
                Waiting1Label.setText(waiting + "");
                break;
            case "Station 2":
                Waiting2Label.setText(waiting + "");
                break;
            case "Station 3":
                Waiting3Label.setText(waiting + "");
                break;
            case "Station 4":
                Waiting4Label.setText(waiting + "");
                break;
            case "Station 5":
                Waiting5Label.setText(waiting + "");
                break;
            case "Station 6":
                Waiting6Label.setText(waiting + "");
                break;
            case "Station 7":
                Waiting7Label.setText(waiting + "");
                break;
            case "Station 8":
                Waiting8Label.setText(waiting + "");
                break;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }
}