package gui;

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
            label_train_seats;

    JTextField
            textfield_train_seats;

    JButton
            button_add_train,
            button_remove_train;

    Font trainNumFont = new Font("Georgia", Font.PLAIN, 18);
    Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);
    Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

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
        label_train_num.setFont(trainNumFont);

        label_train_count = new JLabel("0 out of 16");
        label_train_count.setFont(trainNumFont);
        label_train_count.setForeground(Color.decode("#e91e63"));

        label_train_seats = new JLabel("Number of Seats on the Train:");
        label_train_seats.setFont(fieldFont);

        textfield_train_seats = new JTextField(10);
        textfield_train_seats.setFont(fieldFont);

        button_add_train = new JButton("Add Train");
        button_add_train.setFocusPainted(false);
        button_add_train.setFont(buttonFont);
        button_add_train.setForeground(Color.white);
        button_add_train.setBackground(Color.decode("#04bbaa"));
        button_add_train.addActionListener(this);

        button_remove_train = new JButton("Remove Train");
        button_remove_train.setFocusPainted(false);
        button_remove_train.setFont(buttonFont);
        button_remove_train.setForeground(Color.white);
        button_remove_train.setBackground(Color.decode("#04bbaa"));
        button_remove_train.addActionListener(this);
    }

    public void assembleComponents()
    {
        this.add(label_train_num, "center");
        this.add(label_train_seats, "center, wrap");
        this.add(label_train_count, "center");
        this.add(textfield_train_seats, "center, wrap 20");
        this.add(button_add_train, "growx, pushx");
        this.add(button_remove_train, "growx, pushx");
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
        if(e.getSource() == button_add_train)
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

                    label_train_count.setText(CalTrain.numOfTrains + " out of 16");
                }
            }
        }

        else if(e.getSource() == button_remove_train)
        {
            String name = "Train" + (i+1);
            String seats = textfield_train_seats.getText();

            i++;

            if (checkAddTrain(seats))
            {
                if (CalTrain.numOfTrains == 0)
                {
                    JOptionPane.showMessageDialog(ControlSemaphores.this, "No Trains Left", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                else
                {
                    int seatInt = Integer.parseInt(textfield_train_seats.getText());

                    CalTrain.dispatchTrain(name.trim(), seatInt, CalTrain.numOfTrains);

                    CalTrain.numOfTrains++;

                    label_train_count.setText(CalTrain.numOfTrains + " out of 16");
                }
            }
        }
    }
}