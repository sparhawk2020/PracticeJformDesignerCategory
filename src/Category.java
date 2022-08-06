import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Jul 29 16:22:19 PDT 2022
 */



/**
 * @author unknown
 */
public class Category extends JFrame {


    Connection123 con  = new Connection123();
    Connection conobj = con.connect();






    public Category() throws SQLException, ClassNotFoundException {
        initComponents();



    }


    public void Retrieveitems() throws SQLException {


     String   catcode = textField1.getText();


        String quer1 = "Select * from items where catcode=?";
        PreparedStatement query = conobj.prepareStatement(quer1);
        query.setString(1, catcode);

        ResultSet rs = query.executeQuery();




        if(rs.isBeforeFirst()==false) {          //res.isBeforeFirst() is true if the cursor

            JOptionPane.showMessageDialog(null, "There are no items for this category ");



            return;



        }


       // String quer1 = "Select * from category";
     //   PreparedStatement query = conobj.prepareStatement(quer1);
      //  ResultSet rs = query.executeQuery();
        ResultSetMetaData Res = rs.getMetaData();



        int c = Res.getColumnCount();
        DefaultTableModel df = (DefaultTableModel) table2.getModel();
        df.setRowCount(0);
        rs.last();
        int z = rs.getRow();

        rs.beforeFirst();

        String[][] array = new String[0][];
        if(z>0) {
            array= new String[z][4];
        }

        int j=0;
        while(rs.next()) {
            array[j][0] = rs.getString("icode");
            array[j][1] = rs.getString("descp");
          //  array[j][2] = rs.getString("catcode");
            array[j][2] = rs.getString("price");
            array[j][3] = rs.getString("qty");
            ++j;

        }

        String[] cols = {"Item Code", "Item Description", "Price", "Quantity"};

        DefaultTableModel model = new DefaultTableModel(array,cols);
        table2.setModel(model);

        table2.setDefaultEditor(Object.class, null);








    }

    private void table2MouseClicked(MouseEvent e) {








        // TODO add your code here
    }

    private void table1MouseClicked(MouseEvent e) throws SQLException {



        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        int index1 = table1.getSelectedRow();

        textField1.setText(df.getValueAt(index1,0).toString());
        textField2.setText(df.getValueAt(index1,1).toString());


        Retrieveitems();
        // TODO add your code here
    }

    private void button1(ActionEvent e) throws SQLException, ClassNotFoundException {
        String catcode, catdesc;



        catcode = textField1.getText();
        catdesc = textField2.getText();

        String quer1 = "Select * from category where catcode=?";
        PreparedStatement query = conobj.prepareStatement(quer1);
        query.setString(1, catcode);

        ResultSet rs = query.executeQuery();




        if(rs.isBeforeFirst()) {          //res.isBeforeFirst() is true if the cursor

            JOptionPane.showMessageDialog(null, "The catcode you are trying to enter already exists ");

       textField1.setText("");
            textField2.setText("");
            textField1.requestFocus();

            return;



        }


        String quer2 = "INSERT INTO category VALUES ( ?, ? )";
        query = conobj.prepareStatement(quer2);


        query.setString(1, catcode);
        query.setString(2, catdesc);

        query.executeUpdate();

        JOptionPane.showMessageDialog(null, "One record added ");



        Updatetable();





    }

    private void button3(ActionEvent e) throws SQLException, ClassNotFoundException {
        String catcode, catdesc;



        catcode = textField1.getText();
        catdesc = textField2.getText();



        PreparedStatement query;
        query = conobj.prepareStatement("Delete from category where catcode = ?");
        query.setString(1, catcode);

        query.executeUpdate();

        JOptionPane.showMessageDialog(null, "One record deleted ");

        Updatetable();




    }

    private void button2(ActionEvent e) throws SQLException, ClassNotFoundException {
        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        int index1 = table1.getSelectedRow();

        String catcode, catdesc;



        catcode = textField1.getText();
        catdesc = textField2.getText();

        String oldvalue=df.getValueAt(index1,0).toString();

        PreparedStatement query;
        query = conobj.prepareStatement("Update category set catcode=?, catdesc=? where catcode = ?");
        query.setString(1, catcode);
        query.setString(2, catdesc);
        query.setString(3, oldvalue);

        query.executeUpdate();

        JOptionPane.showMessageDialog(null, "One record edited ");

        Updatetable();


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setAutoRequestFocus(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter Category Code");
        label1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
        contentPane.add(label1, "cell 0 0");
        contentPane.add(textField1, "cell 3 0,width 190:200:200");

        //---- label2 ----
        label2.setText("Enter Category Description");
        label2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
        contentPane.add(label2, "cell 0 1");
        contentPane.add(textField2, "cell 3 1,width 190:200:200");

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        table1MouseClicked(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 2");

        //======== scrollPane2 ========
        {

            //---- table2 ----
            table2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table2MouseClicked(e);
                }
            });
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2, "cell 3 2");

        //---- button1 ----
        button1.setText("Add");
        button1.addActionListener(e -> {
            try {
                button1(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(button1, "cell 0 3");

        //---- button2 ----
        button2.setText("Edit");
        button2.addActionListener(e -> {
            try {
                button2(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(button2, "cell 0 3");

        //---- button3 ----
        button3.setText("Delete");
        button3.addActionListener(e -> {
            try {
                button3(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(button3, "cell 0 3");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables



    public void Updatetable() throws SQLException, ClassNotFoundException {

        String quer1 = "Select * from category";
        PreparedStatement query = conobj.prepareStatement(quer1);
        ResultSet rs = query.executeQuery();
        ResultSetMetaData Res = rs.getMetaData();



        int c = Res.getColumnCount();
        DefaultTableModel df = (DefaultTableModel) table1.getModel();
        df.setRowCount(0);
        rs.last();
        int z = rs.getRow();

        rs.beforeFirst();

        String[][] array = new String[0][];
        if(z>0) {
            array= new String[z][2];
        }

        int j=0;
        while(rs.next()) {
            array[j][0] = rs.getString("catcode");
            array[j][1] = rs.getString("catdesc");
            ++j;

        }

        String[] cols = {"Category Code", "Category Description"};

        DefaultTableModel model = new DefaultTableModel(array,cols);
        table1.setModel(model);

        table1.setDefaultEditor(Object.class, null);





    }

}


