package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class BoardingPass extends JFrame implements ActionListener{
    
    JTextField tfpnr;
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate, lblgate, lblseat;
    JButton fetchButton, printButton, resetButton;
    
    public BoardingPass() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Header with better styling
        JLabel heading = new JLabel("AIRLINE MANAGEMENT SYSTEM");
        heading.setBounds(200, 10, 600, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setForeground(new Color(0, 51, 102));
        add(heading);
        
        JLabel subheading = new JLabel("BOARDING PASS GENERATOR");
        subheading.setBounds(320, 50, 350, 30);
        subheading.setFont(new Font("Arial", Font.BOLD, 20));
        subheading.setForeground(new Color(0, 102, 204));
        add(subheading);
        
        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 90, 500, 60);
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(inputPanel);
        
        JLabel lblaadhar = new JLabel("Enter PNR Number:");
        lblaadhar.setBounds(20, 15, 150, 30);
        lblaadhar.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(lblaadhar);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(180, 15, 150, 30);
        tfpnr.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(tfpnr);
        
        fetchButton = new JButton("GENERATE");
        fetchButton.setBackground(new Color(0, 153, 0));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setFont(new Font("Arial", Font.BOLD, 12));
        fetchButton.setBounds(340, 15, 100, 30);
        fetchButton.addActionListener(this);
        inputPanel.add(fetchButton);
        
        // Boarding Pass Panel
        JPanel passPanel = new JPanel();
        passPanel.setLayout(null);
        passPanel.setBounds(50, 160, 700, 280);
        passPanel.setBackground(Color.WHITE);
        passPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 51, 102), 3),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        add(passPanel);
        
        // Airline Logo/Name
        JLabel airlineName = new JLabel("✈️ AIRLINE NAME ✈️");
        airlineName.setBounds(20, 10, 300, 30);
        airlineName.setFont(new Font("Arial", Font.BOLD, 18));
        airlineName.setForeground(new Color(0, 51, 102));
        passPanel.add(airlineName);
        
        // Boarding Pass Title
        JLabel passTitle = new JLabel("BOARDING PASS");
        passTitle.setBounds(500, 10, 150, 30);
        passTitle.setFont(new Font("Arial", Font.BOLD, 16));
        passTitle.setForeground(Color.RED);
        passPanel.add(passTitle);
        
        // Line separator
        JSeparator separator = new JSeparator();
        separator.setBounds(20, 45, 650, 2);
        separator.setForeground(Color.GRAY);
        passPanel.add(separator);
        
        // Passenger Details Section
        JLabel passengerSection = new JLabel("PASSENGER DETAILS");
        passengerSection.setBounds(20, 50, 200, 20);
        passengerSection.setFont(new Font("Arial", Font.BOLD, 12));
        passengerSection.setForeground(new Color(0, 102, 204));
        passPanel.add(passengerSection);
        
        // Name
        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(20, 80, 80, 25);
        lblname.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(100, 80, 200, 25);
        tfname.setFont(new Font("Arial", Font.BOLD, 14));
        passPanel.add(tfname);
        
        // Nationality
        JLabel lblnationality = new JLabel("Nationality:");
        lblnationality.setBounds(20, 110, 80, 25);
        lblnationality.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lblnationality);
        
        tfnationality = new JLabel();
        tfnationality.setBounds(100, 110, 150, 25);
        tfnationality.setFont(new Font("Arial", Font.BOLD, 14));
        passPanel.add(tfnationality);
        
        // Flight Details Section
        JLabel flightSection = new JLabel("FLIGHT DETAILS");
        flightSection.setBounds(350, 50, 200, 20);
        flightSection.setFont(new Font("Arial", Font.BOLD, 12));
        flightSection.setForeground(new Color(0, 102, 204));
        passPanel.add(flightSection);
        
        // From - To
        JLabel lblsrc = new JLabel("From:");
        lblsrc.setBounds(350, 80, 50, 25);
        lblsrc.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lblsrc);
        
        this.lblsrc = new JLabel();
        this.lblsrc.setBounds(400, 80, 100, 25);
        this.lblsrc.setFont(new Font("Arial", Font.BOLD, 14));
        passPanel.add(this.lblsrc);
        
        JLabel lbldest = new JLabel("To:");
        lbldest.setBounds(350, 110, 50, 25);
        lbldest.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lbldest);
        
        this.lbldest = new JLabel();
        this.lbldest.setBounds(400, 110, 100, 25);
        this.lbldest.setFont(new Font("Arial", Font.BOLD, 14));
        passPanel.add(this.lbldest);
        
        // Flight Name and Code
        JLabel lblfname = new JLabel("Flight:");
        lblfname.setBounds(20, 150, 80, 25);
        lblfname.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lblfname);
        
        labelfname = new JLabel();
        labelfname.setBounds(100, 150, 200, 25);
        labelfname.setFont(new Font("Arial", Font.BOLD, 14));
        passPanel.add(labelfname);
        
        JLabel lblfcode = new JLabel("Code:");
        lblfcode.setBounds(350, 150, 50, 25);
        lblfcode.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lblfcode);
        
        labelfcode = new JLabel();
        labelfcode.setBounds(400, 150, 100, 25);
        labelfcode.setFont(new Font("Arial", Font.BOLD, 14));
        passPanel.add(labelfcode);
        
        // Date
        JLabel lbldate = new JLabel("Date:");
        lbldate.setBounds(20, 190, 80, 25);
        lbldate.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lbldate);
        
        labeldate = new JLabel();
        labeldate.setBounds(100, 190, 150, 25);
        labeldate.setFont(new Font("Arial", Font.BOLD, 14));
        passPanel.add(labeldate);
        
        // Gate and Seat (Generated randomly)
        JLabel lblgate = new JLabel("Gate:");
        lblgate.setBounds(350, 190, 50, 25);
        lblgate.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lblgate);
        
        this.lblgate = new JLabel();
        this.lblgate.setBounds(400, 190, 80, 25);
        this.lblgate.setFont(new Font("Arial", Font.BOLD, 14));
        this.lblgate.setForeground(new Color(204, 0, 0));
        passPanel.add(this.lblgate);
        
        JLabel lblseat = new JLabel("Seat:");
        lblseat.setBounds(480, 190, 50, 25);
        lblseat.setFont(new Font("Arial", Font.PLAIN, 12));
        passPanel.add(lblseat);
        
        this.lblseat = new JLabel();
        this.lblseat.setBounds(530, 190, 80, 25);
        this.lblseat.setFont(new Font("Arial", Font.BOLD, 14));
        this.lblseat.setForeground(new Color(204, 0, 0));
        passPanel.add(this.lblseat);
        
        // Barcode simulation
        JLabel barcode = new JLabel("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        barcode.setBounds(20, 230, 650, 30);
        barcode.setFont(new Font("Monospaced", Font.BOLD, 20));
        barcode.setForeground(Color.BLACK);
        passPanel.add(barcode);
        
        // PNR at bottom
        JLabel pnrLabel = new JLabel("PNR: ");
        pnrLabel.setBounds(20, 250, 40, 20);
        pnrLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        passPanel.add(pnrLabel);
        
        JLabel pnrValue = new JLabel();
        pnrValue.setBounds(60, 250, 150, 20);
        pnrValue.setFont(new Font("Arial", Font.BOLD, 10));
        passPanel.add(pnrValue);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds(250, 460, 300, 40);
        buttonPanel.setBackground(Color.WHITE);
        add(buttonPanel);
        
        printButton = new JButton("PRINT BOARDING PASS");
        printButton.setBackground(new Color(0, 51, 102));
        printButton.setForeground(Color.WHITE);
        printButton.setFont(new Font("Arial", Font.BOLD, 12));
        printButton.addActionListener(this);
        printButton.setEnabled(false);
        buttonPanel.add(printButton);
        
        resetButton = new JButton("RESET");
        resetButton.setBackground(Color.GRAY);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Arial", Font.BOLD, 12));
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);
        
        // Airplane image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/airplane.png"));
        Image i2 = i1.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(780, 150, 150, 100);
        add(lblimage);
        
        setSize(1000, 600);
        setLocation(250, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText().trim();
            
            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter PNR Number!");
                return;
            }
            
            try {
                Conn conn = new Conn();
                
                // Check if ticket exists in reservation
                String query = "select * from reservation where PNR = '" + pnr + "'";
                ResultSet rs = conn.s.executeQuery(query);
                
                if (rs.next()) {
                    tfname.setText(rs.getString("name")); 
                    tfnationality.setText(rs.getString("nationality")); 
                    lblsrc.setText(rs.getString("src")); 
                    lbldest.setText(rs.getString("des"));  
                    labelfname.setText(rs.getString("flightname"));  
                    labelfcode.setText(rs.getString("flightcode"));  
                    labeldate.setText(rs.getString("ddate"));
                    
                    // Generate random gate and seat
                    Random rand = new Random();
                    String gate = "G" + (rand.nextInt(20) + 1);
                    String seat = (char)(rand.nextInt(6) + 'A') + "" + (rand.nextInt(30) + 1);
                    lblgate.setText(gate);
                    lblseat.setText(seat);
                    
                    printButton.setEnabled(true);
                    
                    JOptionPane.showMessageDialog(null, 
                        "Boarding Pass Generated Successfully!\n\nGate: " + gate + "\nSeat: " + seat,
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    // Check if ticket was cancelled
                    String cancelQuery = "select * from cancel where pnr_number = '" + pnr + "'";
                    ResultSet rsCancel = conn.s.executeQuery(cancelQuery);
                    
                    if (rsCancel.next()) {
                        JOptionPane.showMessageDialog(null, 
                            "This ticket was CANCELLED on " + rsCancel.getString("date") + 
                            "\nCancellation No: " + rsCancel.getString("cancellation_no"),
                            "Ticket Cancelled", 
                            JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "No booking found with PNR: " + pnr, 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    
                    clearFields();
                    rsCancel.close();
                }
                
                rs.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
            
        } else if (ae.getSource() == printButton) {
            // Print functionality
            String pnr = tfpnr.getText();
            String name = tfname.getText();
            String flight = labelfname.getText();
            String from = lblsrc.getText();
            String to = lbldest.getText();
            String gate = lblgate.getText();
            String seat = lblseat.getText();
            
            String message = "🖨️ PRINTING BOARDING PASS 🖨️\n\n" +
                           "================================\n" +
                           "PNR: " + pnr + "\n" +
                           "Passenger: " + name + "\n" +
                           "Flight: " + flight + " (" + labelfcode.getText() + ")\n" +
                           "From: " + from + "  To: " + to + "\n" +
                           "Date: " + labeldate.getText() + "\n" +
                           "Gate: " + gate + "  Seat: " + seat + "\n" +
                           "================================\n\n" +
                           "Please present this boarding pass at the gate.\n" +
                           "Happy Journey! ✈️";
            
            JOptionPane.showMessageDialog(null, message, "Print Preview", JOptionPane.INFORMATION_MESSAGE);
            
        } else if (ae.getSource() == resetButton) {
            clearFields();
            tfpnr.setText("");
            printButton.setEnabled(false);
        }
    }
    
    private void clearFields() {
        tfname.setText("");
        tfnationality.setText("");
        lblsrc.setText("");
        lbldest.setText("");
        labelfname.setText("");
        labelfcode.setText("");
        labeldate.setText("");
        lblgate.setText("");
        lblseat.setText("");
    }
    
    public static void main(String[] args) {
        new BoardingPass();
    }
}