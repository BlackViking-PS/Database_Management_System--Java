import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Customer {
    private JPanel main;
    private JTextField txtAcntN;
    private JTextField txtName;
    private JTextField txtNID;
    private JTextField txtMobile;
    private JTextField txtType;
    private JTextField txtBalance;
    private JTable table1;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField txtAcntS;
    private JButton searchAccountButton;
    private JButton EXITButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Customer Database Management System of a Bank by MD WALID SHAHRIAR");
        frame.setContentPane(new Customer().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(200, 20, 1500, 1000);
    }

    Connection con;
    PreparedStatement pst;

    public void connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/walidbank", "user name", "password");
            System.out.println("Successfully connected to 'walidbank' database");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            /* ignore */
        }
    }

    public void table_load()
    {
        try
        {
            pst = con.prepareStatement("select * from customerdb");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Customer() {
        connect();
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String acntN, name, nid, mobile, type, balance;

                acntN = txtAcntN.getText();
                name = txtName.getText();
                nid = txtNID.getText();
                mobile = txtMobile.getText();
                type = txtType.getText();
                balance = txtBalance.getText();

                try
                {
                    pst = con.prepareStatement("insert into customerdb(AccountNumber, CustomerName, NationalID, Mobile, AccountType, Balance)values(?,?,?,?,?,?)");
                    pst.setString(1, acntN);
                    pst.setString(2, name);
                    pst.setString(3, nid);
                    pst.setString(4, mobile);
                    pst.setString(5, type);
                    pst.setString(6, balance);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added Successfully!");

                    txtAcntN.setText("");
                    txtName.setText("");
                    txtNID.setText("");
                    txtMobile.setText("");
                    txtType.setText("");
                    txtBalance.setText("");
                    table_load();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }


            }
        });

        searchAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String cusAcN = txtAcntS.getText();

                    pst = con.prepareStatement("select AccountNumber, CustomerName, NationalID, Mobile, AccountType, Balance from customerdb where AccountNumber = ?");
                    pst.setString(1, cusAcN);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String AccountNumber = rs.getString(1);
                        String CustomerName = rs.getString(2);
                        String NationalID = rs.getString(3);
                        String Mobile = rs.getString(4);
                        String AccountType = rs.getString(5);
                        String Balance = rs.getString(6);

                        txtAcntN.setText(AccountNumber);
                        txtName.setText(CustomerName);
                        txtNID.setText(NationalID);
                        txtMobile.setText(Mobile);
                        txtType.setText(AccountType);
                        txtBalance.setText(Balance);
                    }
                    else
                    {
                        txtAcntN.setText("");
                        txtName.setText("");
                        txtNID.setText("");
                        txtMobile.setText("");
                        txtType.setText("");
                        txtBalance.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Account Number Entered!");
                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String acntN, name, nid, mobile, type, balance;

                acntN = txtAcntN.getText();
                name = txtName.getText();
                nid = txtNID.getText();
                mobile = txtMobile.getText();
                type = txtType.getText();
                balance = txtBalance.getText();

                try
                {
                    pst = con.prepareStatement("update customerdb set AccountNumber = ?, CustomerName = ?, NationalID = ?, Mobile = ?, AccountType = ?,  Balance = ? where AccountNumber = ?");
                    pst.setString(1, acntN);
                    pst.setString(2, name);
                    pst.setString(3, nid);
                    pst.setString(4, mobile);
                    pst.setString(5, type);
                    pst.setString(6, balance);
                    pst.setString(7, acntN);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated!");

                    txtAcntN.setText("");
                    txtName.setText("");
                    txtNID.setText("");
                    txtMobile.setText("");
                    txtType.setText("");
                    txtBalance.setText("");
                    txtAcntS.setText("");
                    table_load();
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String acntN;
                acntN = txtAcntN.getText();

                try {
                    pst = con.prepareStatement("delete from customerdb  where AccountNumber = ?");

                    pst.setString(1, acntN);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted!");
                    txtAcntN.setText("");
                    txtName.setText("");
                    txtNID.setText("");
                    txtMobile.setText("");
                    txtType.setText("");
                    txtBalance.setText("");
                    txtAcntS.setText("");
                    table_load();
                }
                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });

        EXITButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Program", JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
