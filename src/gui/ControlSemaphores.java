package gui;

import introos.StationSemaphores;
import main.CalTrain_Semaphores;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlSemaphores extends JPanel implements ActionListener
{
    CalTrain_Semaphores
            CalTrain;

    JLabel
            label_train_num,
            label_train_count,
            label_train_seats,
            label_passengers_station;

    JTextField
            textfield_train_seats,
            textfield_passengers_station;

    JButton
            button_exit_program,
            button_add_train,
            button_add_passengers;

    Font styleFont = new Font("Arial", Font.BOLD, 18);

    int i = 0;

    public ControlSemaphores()
    {
        CalTrain = new CalTrain_Semaphores();
        this.setLayout(new MigLayout("inset 20"));

        buildComponents();
        assembleComponents();
    }

    public void buildComponents()
    {
        label_train_num = new JLabel("Active Trains:");
        label_train_num.setFont(styleFont);
        label_train_num.setForeground(Color.black);

        label_train_count = new JLabel("0/16");
        label_train_count.setFont(styleFont);
        label_train_count.setForeground(Color.pink);

        label_train_seats = new JLabel("Number of Seats on the Train:");
        label_train_seats.setFont(styleFont);
        label_train_seats.setForeground(Color.black);

        textfield_train_seats = new JTextField(10);
        textfield_train_seats.setFont(styleFont);
        textfield_train_seats.setForeground(Color.pink);

        label_passengers_station = new JLabel("Number of Station of the Passengers:");
        label_passengers_station.setFont(styleFont);
        label_passengers_station.setForeground(Color.black);

        textfield_passengers_station = new JTextField(10);
        textfield_passengers_station.setFont(styleFont);
        textfield_passengers_station.setForeground(Color.pink);

        button_exit_program = new JButton("Exit Program");
        button_exit_program.setFocusPainted(false);
        button_exit_program.setFont(styleFont);
        button_exit_program.setForeground(Color.black);
        button_exit_program.setBackground(Color.pink);
        button_exit_program.addActionListener(this);

        button_add_train = new JButton("Add Train");
        button_add_train.setFocusPainted(false);
        button_add_train.setFont(styleFont);
        button_add_train.setForeground(Color.black);
        button_add_train.setBackground(Color.pink);
        button_add_train.addActionListener(this);

        button_add_passengers = new JButton("Add Passengers");
        button_add_passengers.setFocusPainted(false);
        button_add_passengers.setFont(styleFont);
        button_add_passengers.setForeground(Color.black);
        button_add_passengers.setBackground(Color.pink);
        button_add_passengers.addActionListener(this);
    }

    public void assembleComponents()
    {
        this.add(label_train_num, "center");
        this.add(label_train_seats, "center");
        this.add(label_passengers_station, "center, wrap");
        this.add(label_train_count, "center");
        this.add(textfield_train_seats, "center");
        this.add(textfield_passengers_station, "center, wrap 20");
        this.add(button_exit_program, "growx, pushx");
        this.add(button_add_train, "growx, pushx");
        this.add(button_add_passengers, "growx, pushx");
    }

    public boolean checkAddTrain(String seats)
    {
        int Controleats = 0;

        try
        {
            Controleats = Integer.parseInt(seats);
        }

        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(ControlSemaphores.this, "Invalid Train Number Seat", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(Controleats <= 0)
        {
            JOptionPane.showMessageDialog(ControlSemaphores.this, "Please Enter a Positive Seat Number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button_exit_program)
        {
            System.exit(0);
        }

        else if(e.getSource() == button_add_train)
        {
            String name = "Train" + (i+1);
            String seats = textfield_train_seats.getText();

            i++;

            if (checkAddTrain(seats))
            {
                if (CalTrain.numOfTrains == 16)
                {
                    JOptionPane.showMessageDialog(ControlSemaphores.this, "Maximum Train Limit", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                else
                {
                    int seatInt = Integer.parseInt(textfield_train_seats.getText());

                    CalTrain.dispatchTrain(name.trim(), seatInt, CalTrain.numOfTrains);

                    CalTrain.numOfTrains++;

                    label_train_count.setText(CalTrain.numOfTrains + "/16");
                }
            }
        }

        else if(e.getSource() == button_add_passengers)
        {
            if(textfield_passengers_station.getText().equals(""))
            {
                JOptionPane.showMessageDialog(ControlSemaphores.this, "Please enter passenger number!", "Error",
                                              JOptionPane.ERROR_MESSAGE);
            }
            else if(textfield_passengers_station.getText().matches("\\d+"))
            {
                int stationNumber = Integer.parseInt(textfield_passengers_station.getText());
                StringBuilder stringBuilder = new StringBuilder();
                Integer stationWaiting;
                if(stationNumber<1||stationNumber>8)
                {
                    JOptionPane.showMessageDialog(ControlSemaphores.this, "Station does not exist!", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    stringBuilder.append("Station").append(stationNumber);
                    for(int i=0; i<CalTrain_Semaphores.stations.length;i++)
                    {
                        if(CalTrain_Semaphores.stations[i].getStationName().equals(stringBuilder.toString()))
                        {
                            CalTrain_Semaphores.stations[i].Auto_Generate_Robot(CalTrain_Semaphores.stations);
                            stationWaiting = CalTrain_Semaphores.stations[i].getStationPassengersWaiting();
                            SystemSemaphores.setWaiting(stringBuilder.toString(),stationWaiting);
                        }
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(ControlSemaphores.this, "Integers only!", "Error",
                                              JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}