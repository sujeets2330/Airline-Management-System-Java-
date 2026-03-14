package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Cancel extends JFrame implements ActionListener{
    
    JTextField tfpnr;
    JLabel tfname, cancellationno, lblfcode, lbldateoftravel;
    JButton fetchButton, cancelButton;
    
    public Cancel() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        Random random = new Random();
        
        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.RED);
        add(heading);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);
        
        JLabel lblaadhar = new JLabel("PNR Number");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);
        
        fetchButton = new JButton("Show Details");
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
        tfname.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(tfname);
        
        JLabel lblcancelno = new JLabel("Cancellation No");
        lblcancelno.setBounds(60, 180, 150, 25);
        lblcancelno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcancelno);
        
        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        cancellationno.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancellationno.setForeground(Color.BLUE);
        add(cancellationno);
        
        JLabel lblflightcode = new JLabel("Flight Code");
        lblflightcode.setBounds(60, 230, 150, 25);
        lblflightcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblflightcode);
        
        lblfcode = new JLabel();
        lblfcode.setBounds(220, 230, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblfcode);
        
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 280, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        lbldateoftravel = new JLabel();
        lbldateoftravel.setBounds(220, 280, 150, 25);
        lbldateoftravel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lbldateoftravel);
        
        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(220, 330, 150, 30);
        cancelButton.addActionListener(this);
        cancelButton.setEnabled(false);
        add(cancelButton);
        
        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText().trim();
            
            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter PNR Number");
                return;
            }
            
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rsCancel = null;
            
            try {
                Conn dbConn = new Conn();
                conn = dbConn.c;
                stmt = dbConn.s;
                
                // First check if ticket exists in reservation
                String query = "select * from reservation where PNR = '" + pnr + "'";
                rs = stmt.executeQuery(query);
                
                if (rs.next()) {
                    String name = rs.getString("name");
                    String flightcode = rs.getString("flightcode");
                    String date = rs.getString("ddate");
                    
                    // Close first ResultSet before creating new one
                    rs.close();
                    
                    // Check if already cancelled by looking in cancel table
                    String checkCancelQuery = "select * from cancel where pnr_number = '" + pnr + "'";
                    rsCancel = stmt.executeQuery(checkCancelQuery);
                    
                    if (rsCancel.next()) {
                        String cancelNo = rsCancel.getString("cancellation_no");
                        JOptionPane.showMessageDialog(null, "This ticket has already been cancelled!\nCancellation No: " + cancelNo);
                        tfpnr.setText("");
                        tfname.setText("");
                        lblfcode.setText("");
                        lbldateoftravel.setText("");
                        cancelButton.setEnabled(false);
                    } else {
                        tfname.setText(name); 
                        lblfcode.setText(flightcode); 
                        lbldateoftravel.setText(date);
                        cancelButton.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "Details fetched successfully. You can now cancel the ticket.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No booking found with PNR: " + pnr); 
                    tfname.setText("");
                    lblfcode.setText("");
                    lbldateoftravel.setText("");
                    cancelButton.setEnabled(false);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {
                // Close ResultSets in finally block
                try {
                    if (rs != null) rs.close();
                    if (rsCancel != null) rsCancel.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
        } else if (ae.getSource() == cancelButton) {
            
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to cancel this ticket?", 
                "Confirm Cancellation", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            String name = tfname.getText();
            String pnr = tfpnr.getText().trim();
            String cancelno = cancellationno.getText();
            String fcode = lblfcode.getText();
            String date = lbldateoftravel.getText();
            
            if (name.isEmpty() || pnr.isEmpty() || fcode.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fetch ticket details first!");
                return;
            }
            
            Connection conn = null;
            Statement stmt = null;
            
            try {
                Conn dbConn = new Conn();
                conn = dbConn.c;
                stmt = dbConn.s;
                
                // Start transaction
                conn.setAutoCommit(false);
                
                try {
                    // First insert into cancel table
                    String insertQuery = "insert into cancel (pnr_number, name, cancellation_no, fcode, date) values ('" 
                        + pnr + "', '" + name + "', '" + cancelno + "', '" + fcode + "', '" + date + "')";
                    
                    int insertResult = stmt.executeUpdate(insertQuery);
                    
                    if (insertResult > 0) {
                        // Then delete from reservation table
                        String deleteQuery = "delete from reservation where PNR = '" + pnr + "'";
                        int deleteResult = stmt.executeUpdate(deleteQuery);
                        
                        if (deleteResult > 0) {
                            // Commit transaction
                            conn.commit();
                            JOptionPane.showMessageDialog(null, 
                                "Ticket Cancelled Successfully!\n\n" +
                                "PNR: " + pnr + "\n" +
                                "Cancellation Number: " + cancelno + "\n" +
                                "Please save this for future reference.");
                            setVisible(false);
                        } else {
                            // Rollback if delete fails
                            conn.rollback();
                            JOptionPane.showMessageDialog(null, "Failed to cancel ticket. Please try again.");
                        }
                    } else {
                        // Rollback if insert fails
                        conn.rollback();
                        JOptionPane.showMessageDialog(null, "Failed to cancel ticket. Please try again.");
                    }
                } catch (SQLException e) {
                    // Rollback on error
                    conn.rollback();
                    throw e;
                } finally {
                    // Reset auto-commit
                    conn.setAutoCommit(true);
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                if (e.getMessage().contains("foreign key constraint")) {
                    JOptionPane.showMessageDialog(null, "Database constraint error. Please check if the ticket exists.");
                } else {
                    JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } 
    }

    public static void main(String[] args) {
        new Cancel();
    }
}