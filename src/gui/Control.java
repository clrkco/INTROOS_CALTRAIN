package gui;

import main.CalTrain_Main;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control extends JPanel implements ActionListener
{
    CalTrain_Main CalTrain;

    JPanel  addTrainPanel,
            headerPanel,
            sidePanel;

    JLabel  label_header,
            label_train_num,
            label_train_count,
            label_train_name,
            label_train_seats;

    JTextField  textfield_train_name,
            textfield_train_seats;
    JSeparator separator = new JSeparator();
    JButton button_add_train,
            button_exit_simulation;

    Font headerFont = new Font("Georgia", Font.PLAIN, 20);
    Font trainNumFont = new Font("Georgia", Font.PLAIN, 18);
    Font labelFont = new Font("Tahoma", Font.PLAIN, 14);
    Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);
    Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);
    Font borderTitleFont = new Font("Segoe UI", Font.ITALIC + Font.BOLD, 16);

    public Control()
    {
        CalTrain = new CalTrain_Main();
        this.setLayout(new MigLayout("inset 20"));

        buildComponents();
        assembleComponents();
    }

    public void buildComponents()
    {
        headerPanel = new JPanel();
        headerPanel.setLayout(new MigLayout(""));

        addTrainPanel = new JPanel();
        addTrainPanel.setLayout(new MigLayout(""));
        addTrainPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Add a Train",
                TitledBorder.LEFT, TitledBorder.TOP, borderTitleFont, Color.decode("#006158")));

        sidePanel = new JPanel(new MigLayout());

        label_header = new JLabel("Train Manager");
        label_header.setFont(headerFont);

        label_train_num = new JLabel("Active Control");
        label_train_num.setFont(trainNumFont);

        label_train_count = new JLabel("0 out of 16");
        label_train_count.setFont(trainNumFont);
        label_train_count.setForeground(Color.decode("#e91e63"));

        label_train_name = new JLabel("Train Name: ");
        label_train_name.setFont(fieldFont);
        label_train_seats = new JLabel("Num of Seats");
        label_train_seats.setFont(fieldFont);

        textfield_train_name = new JTextField(10);
        textfield_train_name.setFont(fieldFont);

        textfield_train_seats = new JTextField(10);
        textfield_train_seats.setFont(fieldFont);

        button_add_train = new JButton("Add Train!");
        button_add_train.setFocusPainted(false);
        button_add_train.setFont(buttonFont);
        button_add_train.setForeground(Color.white);
        button_add_train.setBackground(Color.decode("#04bbaa"));
        button_add_train.addActionListener(this);

        button_exit_simulation = new JButton("Exit Simulation");
        button_exit_simulation.setFocusPainted(false);
        button_exit_simulation.setFont(buttonFont);
        button_exit_simulation.addActionListener(this);
    }

    public void assembleComponents()
    {
        headerPanel.add(label_header, "pushx, center, gaptop 20, wrap");
        headerPanel.add(separator, "growx, wrap 20, wrap");
        sidePanel.add(headerPanel, "pushx, growx, wrap");

        addTrainPanel.add(label_train_name, "");
        addTrainPanel.add(label_train_seats, "wrap");
        addTrainPanel.add(textfield_train_name, "growx, pushx");
        addTrainPanel.add(textfield_train_seats, "growx, pushx,wrap 10");
        addTrainPanel.add(button_add_train, "span, center");

        sidePanel.add(label_train_num, "pushx, center, wrap");
        sidePanel.add(label_train_count, "pushx, center, wrap 20");
        sidePanel.add(addTrainPanel, "pushx, growx");
        this.add(sidePanel, "dock west");
    }

    public boolean checkAddTrain(String name, String seats)
    {
        String trainName = name.trim();
        int Controleats = 0;

        if(trainName.isEmpty() || trainName == null)
        {
            JOptionPane.showMessageDialog(Control.this, "Invalid Train Name", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try
        {
            Controleats = Integer.parseInt(seats);
        }

        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(Control.this, "Invalid Train Number Seat", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(Controleats <= 0)
        {
            JOptionPane.showMessageDialog(Control.this, "Please Enter a Positive Seat Number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button_add_train)
        {
            String name = textfield_train_name.getText();
            String seats = textfield_train_seats.getText();

            if (checkAddTrain(name, seats))
            {
                if (CalTrain.numOfTrains == 16)
                {
                    JOptionPane.showMessageDialog(Control.this, "Maximum Train Limit", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                else
                {
                    int seatInt = Integer.parseInt(textfield_train_seats.getText());

                    CalTrain.dispatchTrain(name.trim(), seatInt, CalTrain.numOfTrains);

                    CalTrain.numOfTrains++;
                    JOptionPane.showMessageDialog(Control.this, "Created a New Train", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    textfield_train_name.setText("");
                    textfield_train_seats.setText("");
                    label_train_count.setText(CalTrain.numOfTrains + " out of 16");
                }
            }
        }
    }
}
