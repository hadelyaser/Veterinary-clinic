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
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServiceSelection extends JFrame {
    private JPanel mainPanel, titlePanel, selectionPanel, buttonPanel, radioPanel;
    private JComboBox<String> serviceComboBox;
    private JTextField CostField, serviceDetailsField;
    private JLabel titleLabel, serviceLabel, CostLabel, detailsLabel;
    private JButton backButton, confirmButton;
    private JRadioButton catRadio, DogRadio;
    private ButtonGroup radioGroup;
    private ImageIcon backgroundImage;

    public ServiceSelection() {
        initializeUI();
        setTitle("Select Service");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initializeUI() {
        backgroundImage = new ImageIcon("backg.jpg");

        mainPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLabel = new JLabel("Select Your Service", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel = new JPanel();
        titlePanel.add(titleLabel);

        selectionPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        radioPanel = new JPanel(new FlowLayout());

        serviceLabel = new JLabel("Service:");
        serviceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        serviceComboBox = new JComboBox<>(new String[]{"Shaving", "Nails cut", "Emergency", "Shower"});
        serviceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateServiceInfo(e);
            }
        });
        serviceComboBox.setFont(new Font("Arial", Font.PLAIN, 16));



        CostLabel = new JLabel("Total Cost:");
        CostLabel.setFont(new Font("Arial", Font.BOLD, 16));
        CostField = new JTextField(20);
        CostField.setFont(new Font("Arial", Font.PLAIN, 16));
        CostField.setEditable(false);

        detailsLabel = new JLabel("Service Details:");
        detailsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        serviceDetailsField = new JTextField(20);
        serviceDetailsField.setFont(new Font("Arial", Font.PLAIN, 16));
        serviceDetailsField.setEditable(false);

        catRadio = new JRadioButton("Cat");
        catRadio.setSelected(true);
        catRadio.setFont(new Font("Arial", Font.BOLD, 16));
        DogRadio = new JRadioButton("Dog");
        DogRadio.setFont(new Font("Arial", Font.BOLD, 16));
        radioGroup = new ButtonGroup();
        radioGroup.add(catRadio);
        radioGroup.add(DogRadio);

        selectionPanel.add(serviceLabel);
        selectionPanel.add(serviceComboBox);

        selectionPanel.add(CostLabel);
        selectionPanel.add(CostField);
        selectionPanel.add(detailsLabel);
        selectionPanel.add(serviceDetailsField);

        radioPanel.add(catRadio);
        radioPanel.add(DogRadio);

        buttonPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(new BackAction());
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.addActionListener(new ConfirmAction());
        selectionPanel.add(radioPanel);
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(selectionPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
        pack();
        updateServiceInfo(null);
    }

    private void updateServiceInfo(ActionEvent e) {
        String service = (String) serviceComboBox.getSelectedItem();

        double price = getPriceForService(service);
        String details = getDetailsForService(service);

        serviceDetailsField.setText(details);
        CostField.setText(price + " SAR");
    }

    private double getPriceForService(String service) {
        switch (service) {
            case "Shaving":
                return 50.0;
            case "Nails cut":
                return 30.0;
            case "Emergency":
                return 100.0;
            case "Shower":
                return 40.0;
            default:
                return 0.0;
        }
    }

    private String getDetailsForService(String service) {
        switch (service) {
            case "Shaving":
                return "Complete shaving for pets";
            case "Nails cut":
                return "Nail trimming and grooming";
            case "Emergency":
                return "Immediate medical attention";
            case "Shower":
                return "Bathing and cleaning service";
            default:
                return "";
        }
    }

    private class ConfirmAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateServiceInfo(null);
            saveServiceDetails();
            dispose();
            new InvoiceWindow();
        }
    }

    private class BackAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new OwnerDetails();
        }
    }

    private void saveServiceDetails() {
        try {
            Scanner in = new Scanner(new File("this_user.txt"));
            String name = in.nextLine();
            in.close();
            FileWriter fw = new FileWriter(name + ".txt", true);
            String isCat = "Cat";
            if (DogRadio.isSelected()) {
                isCat = "Dog";
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(new Date());
            fw.write(serviceComboBox.getSelectedItem() + "," + CostField.getText() + "," + isCat + ","+ date + "\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
