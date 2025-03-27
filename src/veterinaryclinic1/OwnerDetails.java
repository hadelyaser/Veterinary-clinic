/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinaryclinic1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class OwnerDetails extends JFrame {

    private JLabel headerLabel, nameLabel, phoneLabel, addressLabel;
    private JTextField nameField, phoneField, addressField;
    private JButton backButton, submitButton;
    private ImageIcon backgroundIcon;

    public OwnerDetails() {
        setupUI();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Owner Details");
        setResizable(false);
        setVisible(true);
    }

    private void setupUI() {
        backgroundIcon = new ImageIcon("backg.jpg");

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        headerLabel = new JLabel("Owner Details", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.add(headerLabel);

        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        detailsPanel.setOpaque(false);

        // Components
        nameLabel = createLabel("Owner Name:");
        nameLabel.setOpaque(true);
        nameField = new JTextField(20);

        phoneLabel = createLabel("Phone Number:");
        phoneLabel.setOpaque(true);
        phoneField = new JTextField(20);

        addressLabel = createLabel("Address:");
        addressLabel.setOpaque(true);
        addressField = new JTextField(20);

        backButton = new JButton("Back");
        submitButton = new JButton("Submit");

        backButton.addActionListener(new ReturnToMainMenu());
        submitButton.addActionListener(new ProceedToRecordDetails());

        // Adding components to panels
        addComponentsToPanel(detailsPanel, new JComponent[]{nameLabel, nameField, phoneLabel, phoneField, addressLabel, addressField});

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        // Assembling the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        return label;
    }

    private void addComponentsToPanel(JPanel panel, JComponent[] components) {
        for (JComponent component : components) {
            panel.add(component);
        }
    }

    private class ReturnToMainMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new VeterinaryClinicApp();
        }
    }

    private class ProceedToRecordDetails implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (validateOwnerDetails()) {
                saveOwnerDetails();
                dispose();
                new ServiceSelection();
            }
        }
    }

    private boolean validateOwnerDetails() {
        //check if all fields are not empty
        if (nameField.getText().isEmpty() || phoneField.getText().isEmpty() || addressField.getText().isEmpty()) {
            try {
                throw new MyCustomException("All fields are required");
            } catch (MyCustomException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if (phoneField.getText().length() != 10 || !phoneField.getText().matches("[0-9]+") || !phoneField.getText().startsWith("05")) {
            try {
                throw new MyCustomException("Invalid phone number. Please try again.");
            } catch (MyCustomException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if (!nameField.getText().matches("[a-zA-Z ]+")) {
            try {
                throw new MyCustomException("Invalid name. Please try again.");
            } catch (MyCustomException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void saveOwnerDetails() {
        try {
            PrintWriter pw1= new PrintWriter(phoneField.getText()+".txt");
            pw1.println(nameField.getText()+","+phoneField.getText()+","+addressField.getText());
            pw1.close();
            PrintWriter pw = new PrintWriter("this_user.txt");
            pw.println(phoneField.getText());
            pw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
