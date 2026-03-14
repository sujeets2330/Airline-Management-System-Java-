package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener{
    JTable table;
    JTextField pnr;
    JButton show;
    
    public JourneyDetails() {
        this("");
    }
    
    public JourneyDetails(String pnrNumber) {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 50, 100, 25);
        add(lblpnr);
        
        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);
        if (!pnrNumber.isEmpty()) {
            pnr.setText(pnrNumber);
        }
        add(pnr);
        
        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        add(show);
        
        table = new JTable();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);
        jsp.setBackground(Color.WHITE);
        add(jsp);
        
        // Auto-show details if PNR is provided
        if (!pnrNumber.isEmpty()) {
            showDetails(pnrNumber);
        }
        
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        showDetails(pnr.getText());
    }
    
    private void showDetails(String pnrNumber) {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from reservation where PNR = '" + pnrNumber + "'");
            
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found for PNR: " + pnrNumber);
                return;
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}