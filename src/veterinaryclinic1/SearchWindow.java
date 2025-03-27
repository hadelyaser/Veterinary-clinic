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
import java.io.PrintWriter;

public class SearchWindow extends JFrame {
    private JPanel mainPanel, inputPanel, buttonPanel, instructionPanel;
    private JLabel titleLabel, phoneLabel, instructionLabel;
    private JTextField phoneField;
    private JButton backButton, searchButton;
    
    public SearchWindow() {
        setupUI();
        setTitle("Search for Pet Services");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setupUI() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        titleLabel = new JLabel("Search Pet Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(15);

        instructionPanel = new JPanel();
        instructionLabel = new JLabel("Enter owner's phone number to search for services.");
        instructionLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));

        backButton = new JButton("Back");
        searchButton = new JButton("Search");
        backButton.addActionListener(new BackAction());
        searchButton.addActionListener(new SearchAction());

        buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(searchButton);

        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        instructionPanel.add(instructionLabel);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(instructionPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private class SearchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            search();
        }
    }

    private class BackAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new VeterinaryClinicApp(); // يجب إنشاء هذه الفئة
        }
    }

    private void search() {
        if (phoneField.getText().length() != 10 || !phoneField.getText().matches("[0-9]+") || !phoneField.getText().startsWith("05")) {
            try {
                throw new MyCustomException("Invalid phone number. Please try again.");
            } catch (MyCustomException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            String phone = phoneField.getText();
            File file = new File( phone + ".txt");
            if (file.exists()){
                try {
                    PrintWriter pw = new PrintWriter("this_user.txt");
                    pw.println(phone);
                    pw.close();
                    dispose();
                    new InvoiceWindow();
                    
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
              
                
                
            }
            else{
                try {
                throw new MyCustomException("couldn't find your number ");
            } catch (MyCustomException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
        }

    }


}
