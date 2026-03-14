package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener{
    
    JTextField tfaadhar;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    JButton bookflight, fetchButton, flight;
    Choice source, destination;
    JDateChooser dcdate;
    
    public BookFlight() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 80, 150, 25);
        add(tfaadhar);
        
        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        tfaddress = new JLabel();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        labelgender = new JLabel();
        labelgender.setBounds(220, 280, 150, 25);
        add(labelgender);
        
        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 330, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsource);
        
        source = new Choice();
        source.setBounds(220, 330, 150, 25);       
        add(source);
        
        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 380, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);
        
        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);       
        add(destination);
        
        try {
            Conn c = new Conn();
            String query = "select * from flight";
            ResultSet rs = c.s.executeQuery(query);
            
            while(rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);
        
        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 430, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);
        
        labelfname = new JLabel();
        labelfname.setBounds(220, 430, 150, 25);
        add(labelfname);
        
        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 480, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);
        
        labelfcode = new JLabel();
        labelfcode.setBounds(220, 480, 150, 25);
        add(labelfcode);
        
        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 530, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550, 80, 500, 410);
        add(lblimage);
        
        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220, 580, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);
        
        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String aadhar = tfaadhar.getText();
            
            if (aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Aadhar number");
                return;
            }
            
            try {
                Conn conn = new Conn();
                String query = "select * from passenger where aadhar = '"+aadhar+"'";
                ResultSet rs = conn.s.executeQuery(query);
                
                if (rs.next()) {
                    tfname.setText(rs.getString("name")); 
                    tfnationality.setText(rs.getString("nationality")); 
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "No customer found with this Aadhar number. Please add customer details first.");                
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            try {
                Conn conn = new Conn();
                String query = "select * from flight where source = '"+src+"' and destination = '"+dest+"'";
                ResultSet rs = conn.s.executeQuery(query);
                
                if (rs.next()) {
                    labelfname.setText(rs.getString("f_name")); 
                    labelfcode.setText(rs.getString("f_code")); 
                } else {
                    JOptionPane.showMessageDialog(null, "No Flights Found for this route");                
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == bookflight) {
            // Validate all fields before booking
            if (tfaadhar.getText().isEmpty() || tfname.getText().isEmpty() || 
                labelfname.getText().isEmpty() || labelfcode.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fetch customer details and flight details first!");
                return;
            }
            
            Random random = new Random();
            
            String aadhar = tfaadhar.getText();
            String name = tfname.getText(); 
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText(); 
            String flightcode = labelfcode.getText();
            String src = source.getSelectedItem(); 
            String des = destination.getSelectedItem();
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            
            if (ddate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select date of travel");
                return;
            }
            
            // Generate PNR and Ticket Number
            String pnr = "PNR-" + String.format("%06d", random.nextInt(1000000));
            String ticketNo = "TIC-" + String.format("%04d", random.nextInt(10000));
            
            try {
                Conn conn = new Conn();
                
                // Insert reservation
                String query = "insert into reservation values('" + pnr + "', '" + ticketNo + "', '" 
                    + aadhar + "', '" + name + "', '" + nationality + "', '" + flightname + "', '" 
                    + flightcode + "', '" + src + "', '" + des + "', '" + ddate + "')";
                
                int rowsAffected = conn.s.executeUpdate(query);
                
                if (rowsAffected > 0) {
                    // Create a custom dialog to show PNR details
                    JDialog confirmationDialog = new JDialog(this, "Booking Confirmation", true);
                    confirmationDialog.setLayout(null);
                    confirmationDialog.setSize(400, 300);
                    confirmationDialog.setLocationRelativeTo(this);
                    
                    JLabel successLabel = new JLabel("✓ TICKET BOOKED SUCCESSFULLY!");
                    successLabel.setBounds(50, 20, 300, 30);
                    successLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
                    successLabel.setForeground(new Color(0, 150, 0));
                    confirmationDialog.add(successLabel);
                    
                    JLabel pnrLabel = new JLabel("PNR Number:");
                    pnrLabel.setBounds(50, 70, 100, 25);
                    pnrLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    confirmationDialog.add(pnrLabel);
                    
                    JLabel pnrValue = new JLabel(pnr);
                    pnrValue.setBounds(160, 70, 200, 25);
                    pnrValue.setFont(new Font("Tahoma", Font.BOLD, 16));
                    pnrValue.setForeground(Color.RED);
                    confirmationDialog.add(pnrValue);
                    
                    JLabel ticketLabel = new JLabel("Ticket No:");
                    ticketLabel.setBounds(50, 110, 100, 25);
                    ticketLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    confirmationDialog.add(ticketLabel);
                    
                    JLabel ticketValue = new JLabel(ticketNo);
                    ticketValue.setBounds(160, 110, 200, 25);
                    ticketValue.setFont(new Font("Tahoma", Font.BOLD, 14));
                    confirmationDialog.add(ticketValue);
                    
                    JLabel nameLabel = new JLabel("Passenger:");
                    nameLabel.setBounds(50, 150, 100, 25);
                    nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    confirmationDialog.add(nameLabel);
                    
                    JLabel nameValue = new JLabel(name);
                    nameValue.setBounds(160, 150, 200, 25);
                    nameValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    confirmationDialog.add(nameValue);
                    
                    JLabel flightLabel = new JLabel("Flight:");
                    flightLabel.setBounds(50, 190, 100, 25);
                    flightLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    confirmationDialog.add(flightLabel);
                    
                    JLabel flightValue = new JLabel(flightname + " (" + flightcode + ")");
                    flightValue.setBounds(160, 190, 200, 25);
                    flightValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    confirmationDialog.add(flightValue);
                    
                    JButton okButton = new JButton("OK");
                    okButton.setBounds(150, 230, 100, 30);
                    okButton.setBackground(Color.BLACK);
                    okButton.setForeground(Color.WHITE);
                    okButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            confirmationDialog.dispose();
                            setVisible(false);
                        }
                    });
                    confirmationDialog.add(okButton);
                    
                    confirmationDialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to book ticket!");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error booking ticket: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}