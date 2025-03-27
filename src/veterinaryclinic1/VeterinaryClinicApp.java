/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package veterinaryclinic1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class VeterinaryClinicApp extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuClinic;
    private JMenuItem addPatientRecord, showPatientRecords, exitApp;
    private JPanel panelMain, panelHeader;
    private ImageIcon iconBackground;

    public VeterinaryClinicApp() {
        setupInterface();
        addEventHandlers();
        configureFrame();
    }

    private void setupInterface() {
        panelHeader = new JPanel();
        JLabel labelHeader = new JLabel("Welcome to pet care Veterinary Clinic");
        labelHeader.setFont(new Font("Arial", Font.BOLD, 20));
        labelHeader.setForeground(Color.DARK_GRAY);
        panelHeader.add(labelHeader);

        iconBackground = new ImageIcon("pets.jpg");
        panelMain = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(iconBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        menuBar = new JMenuBar();
        configureMenus();
    }

    private void configureMenus() {
        menuClinic = new JMenu("Clinic");

        menuClinic.setFont(new Font("Arial", Font.BOLD, 16));

        addPatientRecord = new JMenuItem("Add Patient Record");
        showPatientRecords = new JMenuItem("Show Patient Record");
        exitApp = new JMenuItem("Exit");

        addPatientRecord.setFont(new Font("Arial", Font.BOLD, 16));
        showPatientRecords.setFont(new Font("Arial", Font.BOLD, 16));
        exitApp.setFont(new Font("Arial", Font.BOLD, 16));

        menuClinic.add(addPatientRecord);
        menuClinic.add(showPatientRecords);
        menuClinic.add(exitApp);

        menuBar.add(menuClinic);
    }

    private void addEventHandlers() {
        addPatientRecord.addActionListener(new PatientRecordCreationWindow());
        showPatientRecords.addActionListener(new PatientRecordsDisplayWindow());
        exitApp.addActionListener(new ApplicationExitListener());
    }

    private void configureFrame() {
        panelMain.add(panelHeader, BorderLayout.NORTH);
        setJMenuBar(menuBar);
        setTitle("pet care Veterinary Clinic");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelMain);
        setResizable(false);
        setVisible(true);
    }

    private class PatientRecordCreationWindow implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new OwnerDetails();
        }
    }

    private class PatientRecordsDisplayWindow implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new SearchWindow();
        }
    }

    private class ApplicationExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new VeterinaryClinicApp();
    }
}
