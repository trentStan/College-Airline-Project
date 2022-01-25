/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javax.swing.*;

/**
 *
 * @author user-pc
 */
public class GUI extends JFrame implements ActionListener {

    int id;
    String path;
    ArrayList<BookedFlightModel> unpaidList;
    BookedFlightModel unpaidBook;
    String username = "a";
    String psw = "b";
    JLabel lblBook = new JLabel("Choose booking to pay for:");
    JComboBox bookings = new JComboBox();
    JLabel lbltotal = new JLabel("Total to pay:");
    JLabel total = new JLabel("0");
    JLabel lblBal = new JLabel("Balance:");
    JLabel bal = new JLabel("0");
    JLabel lblFileChooser = new JLabel("Choose where to save Receipt");
    JLabel selectedDir;
    JButton choDir;
    JButton pay;
    double balance;
    JFileChooser fileChoose = new JFileChooser();
    JLabel lblerr = new JLabel("Out of balance");
    JPanel panel;

    public GUI(String title,int id ) throws HeadlessException, SQLException {
        super(title);
        this.id = id;
        frameBuild();
    }
    

    public GUI(String title, Boolean firstRun) throws HeadlessException, SQLException, ClassNotFoundException {
        super(title);
        Database.DBConnection();
        if(firstRun == true){
        int errCount = 0;
        do{
            username = JOptionPane.showInputDialog("Enter username");
            
            psw = JOptionPane.showInputDialog("Enter Password");
        id = Database.getID(username, psw);
        
        if(id <= 0){
            
            JOptionPane.showMessageDialog(null, "Incorrect credentials, please try again, strike: " + errCount +"/3", "incorrect Credentials", JOptionPane.ERROR_MESSAGE);
            
        }
        if(errCount == 3){
            exit();
        }
        
        } while( id <= 0 );
        }
        frameBuild();
        
    }
    public void frameBuild() throws SQLException{
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        this.choDir = new JButton("Select Directory");
        this.pay = new JButton("Pay now");
        fileChoose.setCurrentDirectory(new java.io.File("."));
        fileChoose.setDialogTitle("Save Receipt to:");
        fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        balance = Database.getUserBal(id);
        bal.setText(String.valueOf(balance));
        fillCB();
        bookings.addActionListener(this);
        pay.addActionListener(this);
        this.selectedDir = new JLabel(fileChoose.getCurrentDirectory().getAbsolutePath());
        path = fileChoose.getCurrentDirectory().getAbsolutePath();
        panel.add(lblBook);
        panel.add(this.bookings);
        panel.add(this.lbltotal);
        panel.add(total);
        panel.add(this.lblBal);
        panel.add(bal);
        panel.add(this.lblFileChooser);
        panel.add(selectedDir);
        panel.add(this.choDir);
        panel.add(this.pay);

        this.add(panel);
        this.setSize(1000, 500);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.choDir.addActionListener(this);
    }
    public void fillCB() throws SQLException {
        unpaidList = Database.unpaidFlights(id);
        
        String[] items = new String[unpaidList.size()];
        
        for(int count = 0; count < items.length; count++){
            items[count] = "Destination: " + unpaidList.get(count).getDest()
                        + "\t DepartLoc: " + unpaidList.get(count).getDepartLoc()
                    + " \nTotal cost: " + unpaidList.get(count).getTotalCost()
                    + " \tDepart Time: " + unpaidList.get(count).getDepartTime().toString()
                    + " \nBooked seats: " + unpaidList.get(count).getBookedSeats();
            bookings.addItem(items[count]);
            
        }
        
        
    }

    public static void main(String[] args) throws HeadlessException, SQLException, ClassNotFoundException {
        new GUI("Payment", true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae.equals(this.choDir)) {
            fileChoose.showOpenDialog(null);
            this.selectedDir.setText(fileChoose.getSelectedFile().getAbsolutePath());
            path = fileChoose.getSelectedFile().getAbsolutePath();
            System.out.println();
        }
        
        if(ae.equals(this.bookings)){
           total.setText(String.valueOf( unpaidList.get(bookings.getSelectedIndex()).getTotalCost()));
           unpaidBook = unpaidList.get(bookings.getSelectedIndex());
        }
        
        if(ae.equals(this.pay)){
            unpaidBook = unpaidList.get(bookings.getSelectedIndex());
            try {
                Payment pay = new Payment(unpaidBook, this.id);
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Receipt rec = new Receipt(username, unpaidBook,path, String.valueOf(unpaidBook.getPaymentID()));
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
            
            try {
                new GUI("Payment", id);
            } catch (HeadlessException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
