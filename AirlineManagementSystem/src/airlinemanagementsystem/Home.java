package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;
import java.util.*;

public class Home extends JFrame implements ActionListener{
    
    JMenuBar menubar;
    JMenu details, booking, ticket, admin, help;
    JMenuItem flightDetails, customerDetails, bookFlight, journeyDetails, ticketCancellation, 
              boardingPass, adminLogin, aboutUs, exit;
    
    public Home() {
        setLayout(null);
        
        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1600, 900, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1600, 900);
        add(image);
        
        // Welcome Heading with better styling
        JLabel heading = new JLabel("WELCOME TO AIRLINE MANAGEMENT SYSTEM");
        heading.setBounds(300, 50, 1000, 60);
        heading.setForeground(Color.WHITE);
        heading.setBackground(new Color(0, 0, 0, 150));
        heading.setOpaque(true);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 40));
        image.add(heading);
        
        // Subheading
        JLabel subheading = new JLabel("Your Journey Begins Here");
        subheading.setBounds(620, 120, 400, 30);
        subheading.setForeground(Color.YELLOW);
        subheading.setFont(new Font("Arial", Font.ITALIC, 22));
        image.add(subheading);
        
        // Create stylish menu bar
        menubar = new JMenuBar();
        menubar.setBackground(new Color(0, 51, 102));
        menubar.setForeground(Color.WHITE);
        menubar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setJMenuBar(menubar);
        
        // Details Menu
        details = new JMenu("DETAILS");
        details.setFont(new Font("Arial", Font.BOLD, 14));
        details.setForeground(Color.WHITE);
        menubar.add(details);
        
        flightDetails = new JMenuItem("Flight Details");
        flightDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        flightDetails.addActionListener(this);
        details.add(flightDetails);
        
        details.addSeparator();
        
        customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        customerDetails.addActionListener(this);
        details.add(customerDetails);
        
        // Booking Menu
        booking = new JMenu("BOOKING");
        booking.setFont(new Font("Arial", Font.BOLD, 14));
        booking.setForeground(Color.WHITE);
        menubar.add(booking);
        
        bookFlight = new JMenuItem("Book Flight");
        bookFlight.setFont(new Font("Arial", Font.PLAIN, 14));
        bookFlight.addActionListener(this);
        booking.add(bookFlight);
        
        booking.addSeparator();
        
        journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        journeyDetails.addActionListener(this);
        booking.add(journeyDetails);
        
        // Ticket Menu (Boarding Pass and Cancellation)
        ticket = new JMenu("TICKET");
        ticket.setFont(new Font("Arial", Font.BOLD, 14));
        ticket.setForeground(Color.WHITE);
        menubar.add(ticket);
        
        boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.setFont(new Font("Arial", Font.PLAIN, 14));
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);
        
        ticket.addSeparator();
        
        ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.setFont(new Font("Arial", Font.PLAIN, 14));
        ticketCancellation.addActionListener(this);
        ticket.add(ticketCancellation);
        
        // Admin Menu
        admin = new JMenu("ADMIN");
        admin.setFont(new Font("Arial", Font.BOLD, 14));
        admin.setForeground(Color.WHITE);
        menubar.add(admin);
        
        adminLogin = new JMenuItem("Admin Login");
        adminLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        adminLogin.addActionListener(this);
        admin.add(adminLogin);
        
        // Help Menu
        help = new JMenu("HELP");
        help.setFont(new Font("Arial", Font.BOLD, 14));
        help.setForeground(Color.WHITE);
        menubar.add(help);
        
        aboutUs = new JMenuItem("About Us");
        aboutUs.setFont(new Font("Arial", Font.PLAIN, 14));
        aboutUs.addActionListener(this);
        help.add(aboutUs);
        
        help.addSeparator();
        
        exit = new JMenuItem("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 14));
        exit.addActionListener(this);
        help.add(exit);
        
        // Quick Access Buttons Panel
        JPanel quickPanel = new JPanel();
        quickPanel.setLayout(new GridLayout(2, 3, 20, 20));
        quickPanel.setBounds(400, 200, 800, 150);
        quickPanel.setBackground(new Color(255, 255, 255, 200));
        quickPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 204), 3),
            "QUICK ACCESS",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 18),
            new Color(0, 51, 102)
        ));
        
        String[] buttons = {
            "Book Flight", 
            "Journey Details", 
            "Boarding Pass", 
            "Cancel Ticket", 
            "Flight Details", 
            "Add Customer"
        };
        
        Color btnColor = new Color(0, 102, 204);
        
        for (String btn : buttons) {
            JButton button = new JButton(btn);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(btnColor);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createRaisedBevelBorder());
            button.addActionListener(this);
            quickPanel.add(button);
        }
        
        image.add(quickPanel);
        
        // Footer
        JLabel footer = new JLabel("© 2024 Airline Management System. All rights reserved. | Version 1.0");
        footer.setBounds(500, 800, 600, 30);
        footer.setForeground(Color.WHITE);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));
        image.add(footer);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();
        
        switch(text) {
            case "Add Customer Details":
            case "Add Customer":
                new AddCustomer();
                break;
                
            case "Flight Details":
                new FlightInfo();
                break;
                
            case "Book Flight":
                new BookFlight();
                break;
                
            case "Journey Details":
                new JourneyDetails();
                break;
                
            case "Cancel Ticket":
                new Cancel();
                break;
                
            case "Boarding Pass":
                new BoardingPass();
                break;
                
            case "Admin Login":
                JOptionPane.showMessageDialog(this, 
                    "Admin Login feature coming soon!\nPlease use default login from Login page.", 
                    "Admin Login", 
                    JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "About Us":
                showAboutDialog();
                break;
                
            case "Exit":
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to exit?", 
                    "Confirm Exit", 
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
        }
    }
    
    private void showAboutDialog() {
        String message = "✈️ AIRLINE MANAGEMENT SYSTEM ✈️\n\n" +
                        "Version: 1.0\n" +
                        "Developed by: Your Name\n" +
                        "© 2024 All Rights Reserved\n\n" +
                        "FEATURES:\n" +
                        "• Add Customer Details\n" +
                        "• View Flight Information\n" +
                        "• Book Flights\n" +
                        "• Check Journey Details\n" +
                        "• Generate Boarding Pass\n" +
                        "• Cancel Tickets\n\n" +
                        "For support, contact: support@airline.com";
        
        JOptionPane.showMessageDialog(this, message, "About Us", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        new Home();
    }
}