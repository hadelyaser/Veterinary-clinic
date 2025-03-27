/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinaryclinic1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class InvoiceWindow extends JFrame {
    private JPanel mainPanel, invoicePanel, controlPanel;
    private JLabel titleLabel, ownerNameLabel, contactLabel, animalTypeLabel, serviceLabel, PriceLabel, serviceDateLabel;
    private JTextField ownerNameField, contactField, animalTypeField, serviceField, PriceField, serviceDateField;
    private JButton closeButton;
    private ImageIcon backgroundIcon;

    public InvoiceWindow() {
        setupUI();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setTitle("Invoice");
    }

    private void setupUI() {
        backgroundIcon = new ImageIcon("backg.jpg");

        mainPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLabel = new JLabel("Service Invoice", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        invoicePanel = new JPanel(new GridLayout(0, 2, 10, 10));
        invoicePanel.setOpaque(false);


        ownerNameLabel = new JLabel("Owner Name:");
        ownerNameLabel.setOpaque(true);
        ownerNameField = new JTextField();
        ownerNameField.setEditable(false);

        contactLabel = new JLabel("Contact:");
        contactLabel.setOpaque(true);
        contactField = new JTextField();
        contactField.setEditable(false);

        animalTypeLabel = new JLabel("Animal Type:");
        animalTypeLabel.setOpaque(true);
        animalTypeField = new JTextField();
        animalTypeField.setEditable(false);

        serviceLabel = new JLabel("Service:");
        serviceLabel.setOpaque(true);
        serviceField = new JTextField();
        serviceField.setEditable(false);

        PriceLabel = new JLabel(" Price:");
        PriceLabel.setOpaque(true);
        PriceField = new JTextField();
        PriceField.setEditable(false);

        serviceDateLabel = new JLabel("Service Date:");
        serviceDateLabel.setOpaque(true);
        serviceDateField = new JTextField();
        serviceDateField.setEditable(false);

        getDataFromDatabase();

        controlPanel = new JPanel();
        closeButton = new JButton("Close");
        closeButton.addActionListener(new CloseAction());

        addComponentsToPanel(invoicePanel, new JComponent[]{
                ownerNameLabel, ownerNameField,
                contactLabel, contactField,
                animalTypeLabel, animalTypeField,
                serviceLabel, serviceField,
                PriceLabel, PriceField,
                serviceDateLabel, serviceDateField
        });

        controlPanel.add(closeButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(invoicePanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        setVisible(true);
        setContentPane(mainPanel);
        pack();
    }

    private class CloseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new VeterinaryClinicApp();
        }
    }

    private void getDataFromDatabase() {
        try {
            Scanner in = new Scanner(new File("this_user.txt"));
            String phone = in.nextLine();
            in.close();
            Scanner sc = new Scanner(new File(phone + ".txt"));
            String line = sc.nextLine();
            String[] data = line.split(",");
            ownerNameField.setText(data[0]);
            contactField.setText(data[1]);
            line = sc.nextLine();
            data = line.split(",");
            serviceField.setText(data[0]);
            PriceField.setText(data[1]);
            animalTypeField.setText(data[2]);
            serviceDateField.setText(data[3]);
            sc.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addComponentsToPanel(JPanel panel, JComponent[] components) {
        for (JComponent component : components) {
            panel.add(component);
        }
    }

}
