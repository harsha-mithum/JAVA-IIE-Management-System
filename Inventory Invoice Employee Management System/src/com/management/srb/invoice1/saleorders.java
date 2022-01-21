/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.invoice1;

import static com.management.srb.invoice1.purchaseorder.ordertable;
import static com.management.srb.invoice1.purchaseorder.txttotalamount;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Avishka
 */
public class saleorders extends javax.swing.JPanel {
Connection con = null;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    Statement st=null;
    double oldpay = 0.0;
    /**
     * Creates new form saleorders
     */
    public saleorders() {
        initComponents();
          productdrop();
        dbconnect();
         autoidgen();
        currentdate();
        showtable();
          tablelord();
         sa1_AddPane.setVisible(false);
        sa1_DeletePane.setVisible(false);
        sa1_UpdatePane.setVisible(false);
        sa1_ReportPane.setVisible(false);
        sa1.setVisible(true);
        txttotalamount.setText("0.0");
        
        
          odertable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
                   odertable.getTableHeader().setOpaque(false);
                   odertable.getTableHeader().setBackground(new Color(32,136,203));
                  odertable.getTableHeader().setForeground(new Color(0,0,0));
                    odertable.setRowHeight(25);
        
        
        
    }

     public void dbconnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods","root","");  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
     
      public void autoidgen() 
        {
         try {
           
            Statement st = con.createStatement();
            rs = st.executeQuery("select Max(order_id) from sales_orders");
             rs.next();
             rs.getString("Max(order_id)");
             if (rs.getString("Max(order_id)")== null)
             {
                 lblsalsorid.setText("SO0001");
             }
             else {
                 long id = Long.parseLong(rs.getString("Max(order_id)").substring(4,rs.getString("Max(order_id)").length()));
                 id++;
                 lblsalsorid.setText("SO" + String.format("%04d",id));
             }
        } catch (SQLException ex) {
            Logger.getLogger(saleorders.class.getName()).log(Level.SEVERE, null, ex);
        
        }
            
        }
          public void currentdate(){
         
             SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
             Date date = new Date ();
             String dt = format.format(date);
             txtdate.setText(dt);
         }
          
         public void showtable(){
             try {
            
              Statement st = con.createStatement();
              String sql ="select * from  sales_orders";
               rs = st.executeQuery(sql);
              while (rs.next()){
                  String orderid = rs.getString("order_id");
                  String date = rs.getString("date");
                  String supplierid = rs.getString("customer_id");
                  String supname = rs.getString("customer_name");
                  double amu = rs.getDouble("total_amount");
                  String amount = Double.toString(amu);
                  double py = rs.getDouble("pay");
                  String pay = Double.toString(py);
                  double balance = rs.getDouble("balance");
                  String bal= Double.toString(balance);
                  String orderstu= rs.getString("order_status");
                  String ordertyp= rs.getString("order_type");
                  String custb[] ={orderid,date,supplierid,supname,amount,pay,bal,orderstu,ordertyp};
                    DefaultTableModel tbmodel = (DefaultTableModel)salesordertable.getModel();
                    
                    
                    
                    
                    salesordertable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
                   salesordertable.getTableHeader().setOpaque(false);
                   salesordertable.getTableHeader().setBackground(new Color(32,136,203));
                  salesordertable.getTableHeader().setForeground(new Color(0,0,0));
                   salesordertable.setRowHeight(25);
                    tbmodel.addRow(custb);
                    
                    
                  
                    
                    
                    
              }
              con.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(saleorders.class.getName()).log(Level.SEVERE, null, ex);
        }
         }  
         
          public void balance (){
       double total = Double.parseDouble(txttotalamount.getText().trim());
        double pay = Double.parseDouble(txtpay.getText().trim());
        double bal = pay - total;
        String b=String.valueOf(bal);
        lblbala.setText(String.valueOf(b));
         if (bal == 0.0){
         
             lblorderstu.setText("Complete order");
         }
         else{
             lblorderstu.setText("Incomplete order");
         }
    }
          
       public void price(){
        String prprice = "SELECT * FROM product_price WHERE  product=?";
        String cobopro = comboproduct.getSelectedItem().toString();
         try {
            pst = con.prepareStatement(prprice);
            pst.setString(1,cobopro);
            rs = pst.executeQuery();
            if(rs.next()){
                String qty =String.valueOf(rs.getInt("sellprice"));
                lbluniprice.setText(qty);
                 }
        } catch (SQLException ex) {
            Logger.getLogger(saleorders.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    public void calqty(){
         double qty = Double.parseDouble(txtqty.getText().trim());
         double avqty = Double.parseDouble(lblqty.getText().trim());
        double price = Double.parseDouble(lbluniprice.getText().trim());
        
         if (qty < avqty || avqty == qty){
        double total = qty * price ;
        txtamount.setText(String.valueOf(total));
        
         }
         
         else {
            JOptionPane.showMessageDialog(this," Requsting Quntity is Not Avalible");
        }
        
       
       
    } 
    
    public void productdrop(){
             try{
                 con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");  
       String sql = "Select * from product";
       pst=(PreparedStatement) con.prepareStatement(sql);
       rs= (ResultSet) pst.executeQuery();
   
               while(rs.next()){
                 comboproduct.addItem(rs.getString("productName"));
                 }
             } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }
         } 
    
    
  private void tablelord()
                
               
                    
            { 
                try
                   {String sql="SELECT Product,Product_Id,Type,Category,Grade,Qty,Unit_Price,Amount FROM sales_tempory";
                      con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=(PreparedStatement) con.prepareStatement(sql);
              
                    ResultSet rs= pst.executeQuery();
                    odertable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
                   
                   
                    
                    
                    
                   }
                catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
                    
                    
                    
                   }
                    odertable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
                    odertable.getTableHeader().setOpaque(false);
                    odertable.getTableHeader().setBackground(new Color(32,136,203));
                    odertable.getTableHeader().setForeground(new Color(0,0,0));
                    odertable.setRowHeight(25);
                    odertable.getColumnModel().getColumn(0).setPreferredWidth(100); 
                    odertable.getColumnModel().getColumn(1).setPreferredWidth(80); 
                    odertable.getColumnModel().getColumn(2).setPreferredWidth(80); 
                    odertable.getColumnModel().getColumn(3).setPreferredWidth(80); 
                    odertable.getColumnModel().getColumn(4).setPreferredWidth(80); 
                    odertable.getColumnModel().getColumn(5).setPreferredWidth(80); 
                    odertable.getColumnModel().getColumn(6).setPreferredWidth(80); 
                    odertable.getColumnModel().getColumn(7).setPreferredWidth(80); 
                    
                   
                   }     
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sa1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        newSales = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        salesrReport = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        deletesales = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        updatesales = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        sa1_AddPane = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        btnGoBack = new javax.swing.JButton();
        lblimagename = new javax.swing.JLabel();
        btnAddCus = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        lblsalsorid = new javax.swing.JLabel();
        txtcusname = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        Lorderredio = new javax.swing.JRadioButton();
        exorderredio = new javax.swing.JRadioButton();
        lbluniprice = new javax.swing.JLabel();
        iblgrade = new javax.swing.JLabel();
        txtproid = new javax.swing.JTextField();
        comboproduct = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txttotalamount = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtpay = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        lblorderstu = new javax.swing.JLabel();
        lblbala = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblordtype = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        ibltyp = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        iblcategory = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        lblqty = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        odertable = new javax.swing.JTable();
        txtcusid = new javax.swing.JTextField();
        sa1_DeletePane = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        btnGoBack1 = new javax.swing.JButton();
        lblimagename1 = new javax.swing.JLabel();
        btnDeleteCus = new javax.swing.JButton();
        btnreset1 = new javax.swing.JButton();
        btnUpdateCus1 = new javax.swing.JButton();
        txtorderid1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        labldate1 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        lblsupid1 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblsupname1 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtpay2 = new javax.swing.JTextField();
        lblbalance1 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        lblorderstu2 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        lblordtyp1 = new javax.swing.JLabel();
        lblamount1 = new javax.swing.JLabel();
        sa1_UpdatePane = new javax.swing.JPanel();
        header2 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        btnGoBack2 = new javax.swing.JButton();
        lblimagename2 = new javax.swing.JLabel();
        btnUpdateCus = new javax.swing.JButton();
        btnreset2 = new javax.swing.JButton();
        lblsupid = new javax.swing.JLabel();
        labldate = new javax.swing.JLabel();
        lblsupname = new javax.swing.JLabel();
        lblamount = new javax.swing.JLabel();
        txtpay1 = new javax.swing.JTextField();
        lblbalance = new javax.swing.JLabel();
        lblorderstu1 = new javax.swing.JLabel();
        lblordtyp = new javax.swing.JLabel();
        txtorderid = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        sa1_ReportPane = new javax.swing.JPanel();
        header3 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        btnGoBack3 = new javax.swing.JButton();
        lblimagename3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        salesordertable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(820, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sa1.setBackground(new java.awt.Color(180, 170, 255));
        sa1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        sa1.setPreferredSize(new java.awt.Dimension(820, 700));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Sales Order Section");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/information.png"))); // NOI18N
        jLabel15.setToolTipText("");

        newSales.setBackground(new java.awt.Color(255, 255, 255));
        newSales.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        newSales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newSalesMouseClicked(evt);
            }
        });
        newSales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/s1.png"))); // NOI18N
        newSales.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 204, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("New Sales Order");
        newSales.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 130, -1, 20));

        salesrReport.setBackground(new java.awt.Color(255, 255, 255));
        salesrReport.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        salesrReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesrReportMouseClicked(evt);
            }
        });
        salesrReport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/s4.png"))); // NOI18N
        salesrReport.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("List Of Sales Orders");
        salesrReport.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 20));

        deletesales.setBackground(new java.awt.Color(255, 255, 255));
        deletesales.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        deletesales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletesalesMouseClicked(evt);
            }
        });
        deletesales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/s2.png"))); // NOI18N
        deletesales.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Delete Sales Order");
        deletesales.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 130, -1, 20));

        updatesales.setBackground(new java.awt.Color(255, 255, 255));
        updatesales.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        updatesales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatesalesMouseClicked(evt);
            }
        });
        updatesales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/s3.png"))); // NOI18N
        updatesales.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 0, 204));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Update Sales Order");
        updatesales.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 130, -1, 20));

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Welcome to Invoice System");

        javax.swing.GroupLayout sa1Layout = new javax.swing.GroupLayout(sa1);
        sa1.setLayout(sa1Layout);
        sa1Layout.setHorizontalGroup(
            sa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sa1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(sa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sa1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)))
                .addGap(177, 177, 177))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sa1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newSales, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(sa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(salesrReport, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(deletesales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addComponent(updatesales, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        sa1Layout.setVerticalGroup(
            sa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sa1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(sa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(42, 42, 42)
                .addGroup(sa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newSales, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletesales, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatesales, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(salesrReport, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(sa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        sa1_AddPane.setBackground(new java.awt.Color(200, 210, 255));
        sa1_AddPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        sa1_AddPane.setToolTipText("");
        sa1_AddPane.setPreferredSize(new java.awt.Dimension(810, 712));
        sa1_AddPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 0, 104));
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("New Orders :");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addContainerGap(713, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        sa1_AddPane.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, -1, -1));

        btnGoBack.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack.setText("Go Back");
        btnGoBack.setBorderPainted(false);
        btnGoBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBackActionPerformed(evt);
            }
        });
        sa1_AddPane.add(btnGoBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, 170, 30));

        lblimagename.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sa1_AddPane.add(lblimagename, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 234, 250, 20));

        btnAddCus.setBackground(new java.awt.Color(0, 153, 204));
        btnAddCus.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnAddCus.setForeground(new java.awt.Color(255, 255, 255));
        btnAddCus.setText("Add Order");
        btnAddCus.setBorderPainted(false);
        btnAddCus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCusActionPerformed(evt);
            }
        });
        sa1_AddPane.add(btnAddCus, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 650, 170, 30));

        btnreset.setBackground(new java.awt.Color(255, 153, 0));
        btnreset.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnreset.setForeground(new java.awt.Color(255, 255, 255));
        btnreset.setText("Reset");
        btnreset.setBorderPainted(false);
        btnreset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });
        sa1_AddPane.add(btnreset, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 650, 170, 30));

        lblsalsorid.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblsalsorid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(lblsalsorid, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 93, 200, 30));

        txtcusname.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtcusname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(txtcusname, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 92, 210, 30));

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Sales Order ID :");
        sa1_AddPane.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 93, -1, -1));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Date :");
        sa1_AddPane.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 59, 50, -1));

        txtdate.setBackground(new java.awt.Color(200, 210, 255));
        txtdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtdate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 52, 200, 30));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Customer ID :");
        sa1_AddPane.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 59, 129, -1));

        jLabel25.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Customer Name :");
        sa1_AddPane.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 92, -1, 30));

        jLabel26.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Order Type :");
        sa1_AddPane.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 120, -1));

        Lorderredio.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Lorderredio.setText("Local Order");
        sa1_AddPane.add(Lorderredio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 120, 30));

        exorderredio.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        exorderredio.setText("Export Order");
        sa1_AddPane.add(exorderredio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 130, 30));

        lbluniprice.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbluniprice.setForeground(new java.awt.Color(0, 0, 102));
        lbluniprice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbluniprice.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(lbluniprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 130, 30));

        iblgrade.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        iblgrade.setForeground(new java.awt.Color(0, 0, 102));
        iblgrade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iblgrade.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(iblgrade, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 140, 30));

        txtproid.setBackground(new java.awt.Color(200, 210, 255));
        txtproid.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtproid.setForeground(new java.awt.Color(0, 0, 102));
        txtproid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(txtproid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 130, 30));

        comboproduct.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboproduct.setForeground(new java.awt.Color(0, 0, 102));
        comboproduct.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        comboproduct.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboproductPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        sa1_AddPane.add(comboproduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 130, 30));

        jLabel27.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Product :");
        sa1_AddPane.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 70, -1));

        jLabel28.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Product ID :");
        sa1_AddPane.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 90, 20));

        jLabel36.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Grade :");
        sa1_AddPane.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, -1));

        jLabel29.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Quantity :");
        sa1_AddPane.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 80, 20));

        jLabel30.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Unit Price :");
        sa1_AddPane.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 90, 20));

        jLabel31.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Amount :");
        sa1_AddPane.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 80, 40));

        txtamount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtamount.setForeground(new java.awt.Color(0, 0, 102));
        txtamount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(txtamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 130, 30));

        jLabel37.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Total Amount :");
        sa1_AddPane.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 540, -1, 40));

        txttotalamount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txttotalamount.setForeground(new java.awt.Color(0, 0, 102));
        txttotalamount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(txttotalamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, 160, 30));

        jLabel38.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Pay  :");
        sa1_AddPane.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, 60, 40));

        txtpay.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtpay.setForeground(new java.awt.Color(0, 0, 102));
        txtpay.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtpay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpayKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpayKeyReleased(evt);
            }
        });
        sa1_AddPane.add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 550, 160, 30));

        jLabel39.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Order Status  :");
        sa1_AddPane.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 600, 102, 30));

        lblorderstu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblorderstu.setForeground(new java.awt.Color(0, 0, 153));
        lblorderstu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblorderstu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(lblorderstu, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 600, 400, 30));

        lblbala.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblbala.setForeground(new java.awt.Color(0, 0, 102));
        lblbala.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_AddPane.add(lblbala, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 600, 130, 30));

        jLabel40.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Balance  :");
        sa1_AddPane.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 80, 30));

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        sa1_AddPane.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 650, 170, 30));
        sa1_AddPane.add(lblordtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 146, 100, 30));

        jLabel60.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 102));
        jLabel60.setText("Type :");
        sa1_AddPane.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, -1, -1));

        ibltyp.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ibltyp.setForeground(new java.awt.Color(0, 0, 102));
        ibltyp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ibltyp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sa1_AddPane.add(ibltyp, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 140, 30));

        jLabel61.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 102));
        jLabel61.setText("Category :");
        sa1_AddPane.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        iblcategory.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        iblcategory.setForeground(new java.awt.Color(0, 0, 102));
        iblcategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iblcategory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sa1_AddPane.add(iblcategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 140, 30));

        jLabel62.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 102));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Avalible Quantity :");
        sa1_AddPane.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 150, 20));

        lblqty.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblqty.setForeground(new java.awt.Color(0, 0, 102));
        lblqty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblqty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sa1_AddPane.add(lblqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 130, 30));

        txtqty.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtqty.setForeground(new java.awt.Color(0, 0, 102));
        txtqty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtqtyMouseClicked(evt);
            }
        });
        txtqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtqtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtqtyKeyReleased(evt);
            }
        });
        sa1_AddPane.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 140, 30));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setText("Update Stock");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        sa1_AddPane.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 150, 30));

        odertable.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        odertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT", "PRODUCT ID", "TYPE", "CATEGORY", "GRADE", "QTY", "UNIT PRICE", "AMOUNT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(odertable);

        sa1_AddPane.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 800, 220));

        txtcusid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcusidKeyReleased(evt);
            }
        });
        sa1_AddPane.add(txtcusid, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 210, 30));

        add(sa1_AddPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 700));

        sa1_DeletePane.setBackground(new java.awt.Color(200, 210, 255));
        sa1_DeletePane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        sa1_DeletePane.setToolTipText("");
        sa1_DeletePane.setPreferredSize(new java.awt.Dimension(810, 712));
        sa1_DeletePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header1.setBackground(new java.awt.Color(0, 0, 104));
        header1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Delete Orders :");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addContainerGap(718, Short.MAX_VALUE))
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        sa1_DeletePane.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, -1));

        btnGoBack1.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack1.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack1.setText("Go Back");
        btnGoBack1.setBorderPainted(false);
        btnGoBack1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack1ActionPerformed(evt);
            }
        });
        sa1_DeletePane.add(btnGoBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 660, 170, 30));

        lblimagename1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sa1_DeletePane.add(lblimagename1, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 470, 250, 20));

        btnDeleteCus.setBackground(new java.awt.Color(0, 0, 102));
        btnDeleteCus.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnDeleteCus.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteCus.setText("Delete Order");
        btnDeleteCus.setBorderPainted(false);
        btnDeleteCus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCusActionPerformed(evt);
            }
        });
        sa1_DeletePane.add(btnDeleteCus, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 660, 170, 30));

        btnreset1.setBackground(new java.awt.Color(255, 153, 0));
        btnreset1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnreset1.setForeground(new java.awt.Color(255, 255, 255));
        btnreset1.setText("Reset");
        btnreset1.setBorderPainted(false);
        btnreset1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnreset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreset1ActionPerformed(evt);
            }
        });
        sa1_DeletePane.add(btnreset1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 660, 170, 30));

        btnUpdateCus1.setBackground(new java.awt.Color(0, 0, 102));
        btnUpdateCus1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnUpdateCus1.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateCus1.setText("Update Orders");
        btnUpdateCus1.setBorderPainted(false);
        btnUpdateCus1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateCus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCus1ActionPerformed(evt);
            }
        });
        sa1_DeletePane.add(btnUpdateCus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 660, 170, 30));

        txtorderid1.setBackground(new java.awt.Color(200, 210, 255));
        txtorderid1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtorderid1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtorderid1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtorderid1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtorderid1KeyReleased(evt);
            }
        });
        sa1_DeletePane.add(txtorderid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 260, 30));

        jLabel51.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Sales Order ID :");
        sa1_DeletePane.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        jLabel52.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Date :");
        sa1_DeletePane.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 100, 40));

        labldate1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labldate1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_DeletePane.add(labldate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 260, 30));

        jLabel53.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Customer ID :");
        sa1_DeletePane.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 120, 50));

        lblsupid1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblsupid1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_DeletePane.add(lblsupid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 260, 30));

        jLabel54.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Customer Name :");
        sa1_DeletePane.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, 40));

        lblsupname1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblsupname1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_DeletePane.add(lblsupname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 260, 30));

        jLabel55.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Total Amount :");
        sa1_DeletePane.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, -1, 40));

        jLabel56.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Pay :");
        sa1_DeletePane.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 70, 20));

        txtpay2.setBackground(new java.awt.Color(200, 210, 255));
        txtpay2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtpay2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_DeletePane.add(txtpay2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 260, 30));

        lblbalance1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblbalance1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_DeletePane.add(lblbalance1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 260, 30));

        jLabel57.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Balance :");
        sa1_DeletePane.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 80, 30));

        jLabel58.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 51));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Order Status  :");
        sa1_DeletePane.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 130, 30));

        lblorderstu2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblorderstu2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_DeletePane.add(lblorderstu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, 260, 30));

        jLabel59.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText("Order Type :");
        sa1_DeletePane.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 110, 20));

        lblordtyp1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblordtyp1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_DeletePane.add(lblordtyp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 540, 260, 30));

        lblamount1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblamount1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_DeletePane.add(lblamount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 260, 30));

        add(sa1_DeletePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 700));

        sa1_UpdatePane.setBackground(new java.awt.Color(200, 210, 255));
        sa1_UpdatePane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        sa1_UpdatePane.setToolTipText("");
        sa1_UpdatePane.setPreferredSize(new java.awt.Dimension(810, 712));
        sa1_UpdatePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header2.setBackground(new java.awt.Color(0, 0, 104));
        header2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Update Orders :");

        javax.swing.GroupLayout header2Layout = new javax.swing.GroupLayout(header2);
        header2.setLayout(header2Layout);
        header2Layout.setHorizontalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addContainerGap(712, Short.MAX_VALUE))
        );
        header2Layout.setVerticalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        sa1_UpdatePane.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, -1));

        btnGoBack2.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack2.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack2.setText("Go Back");
        btnGoBack2.setBorderPainted(false);
        btnGoBack2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack2ActionPerformed(evt);
            }
        });
        sa1_UpdatePane.add(btnGoBack2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 170, 30));

        lblimagename2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sa1_UpdatePane.add(lblimagename2, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 470, 250, 20));

        btnUpdateCus.setBackground(new java.awt.Color(0, 0, 102));
        btnUpdateCus.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnUpdateCus.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateCus.setText("Update Orders");
        btnUpdateCus.setBorderPainted(false);
        btnUpdateCus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCusActionPerformed(evt);
            }
        });
        sa1_UpdatePane.add(btnUpdateCus, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 660, 170, 30));

        btnreset2.setBackground(new java.awt.Color(255, 153, 0));
        btnreset2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnreset2.setForeground(new java.awt.Color(255, 255, 255));
        btnreset2.setText("Reset");
        btnreset2.setBorderPainted(false);
        btnreset2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnreset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreset2ActionPerformed(evt);
            }
        });
        sa1_UpdatePane.add(btnreset2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 660, 170, 30));

        lblsupid.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblsupid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_UpdatePane.add(lblsupid, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 190, 30));

        labldate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labldate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_UpdatePane.add(labldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 190, 30));

        lblsupname.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblsupname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_UpdatePane.add(lblsupname, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 190, 30));

        lblamount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblamount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_UpdatePane.add(lblamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 190, 30));

        txtpay1.setBackground(new java.awt.Color(200, 210, 255));
        txtpay1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtpay1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtpay1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpay1KeyReleased(evt);
            }
        });
        sa1_UpdatePane.add(txtpay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 190, 30));

        lblbalance.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblbalance.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_UpdatePane.add(lblbalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 190, 30));

        lblorderstu1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblorderstu1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_UpdatePane.add(lblorderstu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, 190, 30));

        lblordtyp.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblordtyp.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        sa1_UpdatePane.add(lblordtyp, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 530, 190, 30));

        txtorderid.setBackground(new java.awt.Color(200, 210, 255));
        txtorderid.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtorderid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtorderid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtorderidKeyPressed(evt);
            }
        });
        sa1_UpdatePane.add(txtorderid, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 190, 30));

        jLabel42.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Sales Order ID :");
        sa1_UpdatePane.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        jLabel43.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Date :");
        sa1_UpdatePane.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 100, 40));

        jLabel44.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Customer ID :");
        sa1_UpdatePane.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 120, 50));

        jLabel45.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Customer Name :");
        sa1_UpdatePane.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, -1, 40));

        jLabel46.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Total Amount :");
        sa1_UpdatePane.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, -1, 40));

        jLabel47.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Pay :");
        sa1_UpdatePane.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 70, 20));

        jLabel48.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Balance :");
        sa1_UpdatePane.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 80, 30));

        jLabel49.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Order Status  :");
        sa1_UpdatePane.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 130, 30));

        jLabel50.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Order Type :");
        sa1_UpdatePane.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 110, 20));

        add(sa1_UpdatePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 700));

        sa1_ReportPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        sa1_ReportPane.setToolTipText("");
        sa1_ReportPane.setPreferredSize(new java.awt.Dimension(820, 700));
        sa1_ReportPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header3.setBackground(new java.awt.Color(0, 0, 104));
        header3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("List Of Sales Orders :");

        javax.swing.GroupLayout header3Layout = new javax.swing.GroupLayout(header3);
        header3.setLayout(header3Layout);
        header3Layout.setHorizontalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addContainerGap(677, Short.MAX_VALUE))
        );
        header3Layout.setVerticalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        sa1_ReportPane.add(header3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, -1));

        btnGoBack3.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack3.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack3.setText("Go Back");
        btnGoBack3.setBorderPainted(false);
        btnGoBack3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack3ActionPerformed(evt);
            }
        });
        sa1_ReportPane.add(btnGoBack3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 170, 30));

        lblimagename3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sa1_ReportPane.add(lblimagename3, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 470, 250, 20));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        salesordertable.setBackground(new java.awt.Color(122, 204, 255));
        salesordertable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        salesordertable.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        salesordertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ORDER ID", "DATE", "CUSTOMER NAME", "CUSTOMER ID", "TOTAL AMOUNT ", "PAY", "BALANCE", "ORDER STATUS", "ORDER TYPE"
            }
        ));
        salesordertable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        jScrollPane2.setViewportView(salesordertable);

        sa1_ReportPane.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 770, 340));

        add(sa1_ReportPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 700));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack3ActionPerformed
        sa1_AddPane.setVisible(false);
        sa1_DeletePane.setVisible(false);
        sa1_UpdatePane.setVisible(false);
        sa1_ReportPane.setVisible(false);

        sa1.setVisible(true);
    }//GEN-LAST:event_btnGoBack3ActionPerformed

    private void newSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newSalesMouseClicked
        // TODO add your handling code here:
        sa1_AddPane.setVisible(true);
        sa1_DeletePane.setVisible(false);
        sa1_UpdatePane.setVisible(false);
        sa1_ReportPane.setVisible(false);

        sa1.setVisible(false);
    }//GEN-LAST:event_newSalesMouseClicked

    private void salesrReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesrReportMouseClicked
        sa1_AddPane.setVisible(false);
        sa1_DeletePane.setVisible(false);
        sa1_UpdatePane.setVisible(false);
        sa1_ReportPane.setVisible(true);

        sa1.setVisible(false);
    }//GEN-LAST:event_salesrReportMouseClicked

    private void deletesalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletesalesMouseClicked
        // TODO add your handling code here:
        sa1_AddPane.setVisible(false);
        sa1_DeletePane.setVisible(true);
        sa1_UpdatePane.setVisible(false);
        sa1_ReportPane.setVisible(false);

        sa1.setVisible(false);
    }//GEN-LAST:event_deletesalesMouseClicked

    private void updatesalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatesalesMouseClicked
        // TODO add your handling code here:
        sa1_AddPane.setVisible(false);
        sa1_DeletePane.setVisible(false);
        sa1_UpdatePane.setVisible(true);
        sa1_ReportPane.setVisible(false);

        sa1.setVisible(false);
    }//GEN-LAST:event_updatesalesMouseClicked

    private void btnGoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBackActionPerformed
        // TODO add your handling code here:
        sa1_AddPane.setVisible(false);
        sa1_DeletePane.setVisible(false);
        sa1_UpdatePane.setVisible(false);
        sa1_ReportPane.setVisible(false);

        sa1.setVisible(true);
    }//GEN-LAST:event_btnGoBackActionPerformed

    private void btnAddCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCusActionPerformed
 String ordtyp = null;
            if (Lorderredio.isSelected())
            {
                ordtyp = Lorderredio.getText();
            }
            if (exorderredio.isSelected())
            {
                ordtyp = exorderredio.getText();
            } 
lblordtype.setText(ordtyp);
        
        if (lblsalsorid.getText().isEmpty()){
           lblsalsorid.setBackground(new Color(255,0,51));
           JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(txtdate.getText().isEmpty()){
            txtdate.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(txtcusid.getText().isEmpty()){
           txtcusid.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(txtcusname.getText().isEmpty()){
            txtcusname.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(txttotalamount.getText().isEmpty()){
            txttotalamount.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(txtpay.getText().isEmpty()){
            txtpay.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(lblbala.getText().isEmpty()){
            lblbala.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(lblorderstu.getText().isEmpty()){
            lblorderstu.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(ordtyp==null){
        Lorderredio.setForeground(new Color(255,0,51));
         exorderredio.setForeground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Please Select Order Type");}
        else{
        
        
        
          try {
            String prodata = "SELECT Product_Id,Qty FROM sales_tempory ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(prodata);
            rs = pst.executeQuery();
              while (rs.next()){
                  String tpid=(rs.getString("Product_Id"));
                  String tqt=(rs.getString("Qty"));
    
      
            String produ = "SELECT ProductID,stock FROM product ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst1=(PreparedStatement) con.prepareStatement(produ);
            rs1 = pst1.executeQuery();
             while (rs1.next()){
                  String pid=(rs1.getString("ProductID"));
                  String qt=(rs1.getString("stock"));
           
          
                if(tpid.equals(pid))
                  {
                    Double tqty = Double.valueOf(tqt);
                    Double qty = Double.valueOf(qt);
                    Double totalstock = qty-tqty;
                    String totsto=String.valueOf(totalstock);
                     try {
            
             String le = "update product set stock=? where ProductID=?";
           con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(le);
           
            pst.setString(1, totsto);
            pst.setString(2, pid);

           pst.executeUpdate();

        }  catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage()); }
                    
                  } 
                  
             }
           
                 
                 } 
 }
        catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                } 
        
        
        
        
        
        try {
            String sql = "insert into sales_orders"
            + "(order_id,date,customer_id,customer_name,total_amount,pay,balance,order_status,order_type)"
            + "values (?,?,?,?,?,?,?,?,?)";
 
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst=con.prepareStatement(sql);
            

            pst.setString(1,lblsalsorid.getText().toString());
            pst.setString(2,txtdate.getText().toString());
            pst.setString(3,txtcusid.getText().toString());
            pst.setString(4,txtcusname.getText().toString());
            double totamu = Double.parseDouble(txttotalamount.getText().toString());
            pst.setDouble(5,totamu);
             double pay = Double.parseDouble(txtpay.getText().toString());
            pst.setDouble(6,pay);
            double bal = Double.parseDouble(lblbala.getText().toString());
            pst.setDouble(7,bal);
            pst.setString(8,lblorderstu.getText().toString());
            pst.setString(9,lblordtype.getText().toString());
            pst.executeUpdate();
           JOptionPane.showMessageDialog(null, "Add Successfull");
           
            
        
            
        } catch (SQLException ex) {
            
            Logger.getLogger(saleorders.class.getName()).log(Level.SEVERE, null, ex);
        }
                }  
        showtable();
        tablelord();
    }//GEN-LAST:event_btnAddCusActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
 try {
         String le = "DELETE FROM`sales_tempory`";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(le);
            pst.executeUpdate();

         
         }    
            catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        
        }  
        
        
        autoidgen();
        //    txtdate.setText("");
            txtcusid.setText("");
            txtcusname.setText("");
            txttotalamount.setText("");
            txtpay.setText("");
            lblbala.setText("") ;       
            lblorderstu.setText("");       
tablelord();
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnGoBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack1ActionPerformed
        sa1_AddPane.setVisible(false);
        sa1_DeletePane.setVisible(false);
        sa1_UpdatePane.setVisible(false);
        sa1_ReportPane.setVisible(false);

        sa1.setVisible(true);
    }//GEN-LAST:event_btnGoBack1ActionPerformed

    private void btnDeleteCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCusActionPerformed
         if(txtorderid1.getText().isEmpty()) {
            txtorderid1.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Sales Order ID field is empty !!");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Delete !!", "Delete", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    String query = "DELETE FROM sales_orders WHERE order_id=?";
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst = con.prepareStatement(query);
                    pst.setString(1, txtorderid1.getText());
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Employee Delete Successfully ");
                    txtorderid1.setText("");
                    labldate1.setText("");
                    lblsupid1.setText("");
                    lblsupname1.setText("");
                    lblamount1.setText("");
                    txtpay2.setText("");
                    lblbalance1.setText("");
                    lblorderstu2.setText("");
                    lblordtyp1.setText("");
                   

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
            }
        }
        
    }//GEN-LAST:event_btnDeleteCusActionPerformed

    private void btnreset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreset1ActionPerformed
 txtorderid1.setText("");
               labldate1.setText("");
                lblsupid1.setText(""); 
               lblsupname1.setText("");
               lblamount1.setText("");
               txtpay2.setText("");  
               lblbalance1.setText(""); 
                lblorderstu2.setText(""); 
                 lblordtyp1.setText("");        
    }//GEN-LAST:event_btnreset1ActionPerformed

    private void btnGoBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack2ActionPerformed
        sa1_AddPane.setVisible(false);
        sa1_DeletePane.setVisible(false);
        sa1_UpdatePane.setVisible(false);
        sa1_ReportPane.setVisible(false);

        sa1.setVisible(true);
    }//GEN-LAST:event_btnGoBack2ActionPerformed

    private void btnUpdateCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCusActionPerformed

        
Double pa=null;
String ords=null;
        try {
            String sql ="select pay from sales_orders where  order_id=?";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
            String ouid = txtorderid.getText();
            pst.setString(1, ouid);
            rs= (ResultSet) pst.executeQuery();
     
            if (rs.next()){
              String p =rs.getString("pay");
              pa=Double.valueOf(p);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);

        }
        

String ord=lblbalance.getText();
Double or=Double.valueOf(ord);
if(or==0.0)
{
    ords="Paid";
    lblorderstu1.setText("Paid");
}
else
{
     ords="Incomplete order";
     lblorderstu1.setText("Incomplete order");
}
        
        if(txtorderid.getText().isEmpty()){
          txtorderid.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(labldate.getText().isEmpty()){
          labldate.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblsupid.getText().isEmpty()){
          lblsupid.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblsupname.getText().isEmpty()){
          lblsupname.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblamount.getText().isEmpty()){
          lblamount.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
       else if(txtpay1.getText().isEmpty()){
          txtpay1.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblbalance.getText().isEmpty()){
          lblbalance.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblorderstu1.getText().isEmpty()){
         lblorderstu1.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else {
          
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to update this record!!","update Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
            
        try {
            
             String le = "update sales_orders set date=?,customer_id =?,customer_name=?,total_amount =? ,pay=?,balance=?,order_status=? where order_id=?";
           con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(le);
           
            pst.setString(1, labldate.getText());
            pst.setString(2, lblsupid.getText());
            pst.setString(3, lblsupname.getText());
            pst.setString(4,  lblamount.getText());
            pst.setDouble(5, oldpay);
            pst.setString(6, lblbalance.getText());
            pst.setString(7, ords);

           
            pst.setString(8, txtorderid.getText());
           
           pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFULLY");
               txtorderid.setText("");
               labldate.setText("");
               lblsupid.setText("");
               lblsupname.setText("");
               lblamount.setText("");
               txtpay1.setText("");
               lblbalance.setText("");
               lblorderstu1.setText("");
           
        }  catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
      }
          }  
        showtable();    
    }//GEN-LAST:event_btnUpdateCusActionPerformed

    private void btnreset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreset2ActionPerformed
txtorderid.setText("");
                labldate.setText("");
                 lblsupid.setText("");
                 lblsupname.setText("");
                 lblamount.setText(""); 
                 txtpay1.setText("");  
                lblbalance.setText("");
                 lblorderstu1.setText("");
                   lblordtyp.setText("");         
    }//GEN-LAST:event_btnreset2ActionPerformed

    private void txtpayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpayKeyPressed
       
    }//GEN-LAST:event_txtpayKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 String ordty = null;
            if (Lorderredio.isSelected())
            {
                ordty = Lorderredio.getText();
            }
            if (exorderredio.isSelected())
            {
                ordty = exorderredio.getText();
            } 
lblordtype.setText(ordty);
        
        
        
        print_sales_order pso = new print_sales_order();
       
       print_sales_order.lblorderid.setText(lblsalsorid.getText());
       print_sales_order.lbldate.setText(txtdate.getText());
       print_sales_order.lblordtyp.setText(lblordtype.getText());
       print_sales_order.lblcusid.setText(txtcusid.getText());
       print_sales_order.lblcusname.setText(txtcusname.getText());
        print_sales_order.productable.setModel(odertable.getModel());
       print_sales_order.lbltotamu.setText(txttotalamount.getText());
       print_sales_order.lblpay.setText(txtpay.getText());
       print_sales_order.lblbala.setText(lblbala.getText());
       print_sales_order.lblordstu.setText(lblorderstu.getText());
        pso.setVisible(true);
      
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtorderidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtorderidKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        try {
String sql ="select * from sales_orders where order_id = ? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
           String ordid = txtorderid.getText();
            pst.setString(1, ordid);
            rs= (ResultSet) pst.executeQuery();
           
         
            if (rs.next()== false){
                
                txtorderid.setText("");
                labldate.setText("");
                 lblsupid.setText("");
                 lblsupname.setText("");
                 lblamount.setText(""); 
                 txtpay1.setText("");  
                lblbalance.setText("");
                 lblorderstu1.setText("");
                   lblordtyp.setText("");       
            }
            else{
                String date =rs.getString("date");
                labldate .setText(date.trim());
                String supid =rs.getString("customer_id");
                lblsupid.setText(supid.trim());
                String supname =rs.getString("customer_name");
                lblsupname.setText(supname.trim());
                String totamu =(rs.getString("total_amount"));
                lblamount.setText(totamu);
                String pay =(rs.getString("pay"));
                this.oldpay = (rs.getDouble("pay"));
                txtpay1.setText(pay);
                String bal =(rs.getString("balance"));
                lblbalance.setText(bal);
                String ordersty =rs.getString("order_status");
                lblorderstu1.setText(ordersty.trim());
                String orderty =rs.getString("order_type");
                lblordtyp.setText(orderty.trim());

            }

        } catch (SQLException ex) {
            Logger.getLogger(saleorders.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_txtorderidKeyPressed

    private void btnUpdateCus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateCus1ActionPerformed

    private void txtpayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpayKeyReleased
        balance ();
    }//GEN-LAST:event_txtpayKeyReleased

    private void txtorderid1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtorderid1KeyPressed

     
    }//GEN-LAST:event_txtorderid1KeyPressed

    private void txtorderid1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtorderid1KeyReleased
         if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        try {
            String sql ="select * from sales_orders where order_id = ?";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
          String ordid = txtorderid1.getText();
            pst.setString(1, ordid);
            rs= (ResultSet) pst.executeQuery();
           
           if (rs.next()== false){
            
               txtorderid1.setText("");
               labldate1.setText("");
                lblsupid1.setText(""); 
               lblsupname1.setText("");
               lblamount1.setText("");
               txtpay2.setText("");  
               lblbalance1.setText(""); 
                lblorderstu2.setText(""); 
                 lblordtyp1.setText("");       
           }
           else{
               String date =rs.getString("date");
                  labldate1.setText(date.trim());
                  String supid =rs.getString("customer_id");
                  lblsupid1.setText(supid.trim());
                  String supname =rs.getString("customer_name");
                  lblsupname1.setText(supname.trim());
                  String totamu =rs.getString("total_amount");
                  lblamount1.setText(totamu);
                  String pay =rs.getString("pay");
                  this.oldpay = (rs.getDouble("pay"));
                  txtpay2.setText(pay);
                  String bal =rs.getString("balance");
                  lblbalance1.setText(bal);
                  String ordersty =rs.getString("order_status");
                  lblorderstu2.setText(ordersty.trim());
                   String orderty =rs.getString("order_type");
                  lblordtyp1.setText(orderty.trim());
                }
       
        } catch (SQLException ex) {
            Logger.getLogger(saleorders.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }//GEN-LAST:event_txtorderid1KeyReleased

    private void txtqtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtqtyMouseClicked
      
    }//GEN-LAST:event_txtqtyMouseClicked

    private void txtqtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyPressed

    }//GEN-LAST:event_txtqtyKeyPressed

    private void txtqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyReleased
         calqty();
       
    }//GEN-LAST:event_txtqtyKeyReleased

    private void comboproductPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboproductPopupMenuWillBecomeInvisible
    
      String cobopro = comboproduct.getSelectedItem().toString();
        try {
            String prodata = "SELECT * FROM product WHERE  productName=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(prodata);
           pst.setString(1,cobopro);
            rs= (ResultSet) pst.executeQuery();
          
            rs = pst.executeQuery();
            if(rs.next()){
                txtproid.setText(rs.getString("productID"));
                iblgrade.setText(rs.getString("productGrade"));
                ibltyp.setText(rs.getString("typeID"));
                iblcategory.setText(rs.getString("categoryID"));
              
                lblqty.setText(rs.getString("stock"));
                price();
            }
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboproductPopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  try {
            String prodata = "SELECT product_Id FROM sales_tempory WHERE  Product_Id=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(prodata);
            pst.setString(1, txtproid.getText());
            rs= (ResultSet) pst.executeQuery();
          
            rs = pst.executeQuery();
            if(rs.next()){
                
                  DefaultTableModel model = (DefaultTableModel)odertable.getModel();
        int selectedRowIndex = odertable.getSelectedRow();
        String amount=(model.getValueAt(selectedRowIndex, 7).toString());
        Double amount1 = Double.valueOf(amount);
        
           Double tamount = Double.valueOf(txttotalamount.getText());
           
           Double minamount = tamount - amount1;
                
              Double namount = Double.valueOf(txtamount.getText());
         Double ntotalamount= minamount + namount;
         txttotalamount.setText(String.valueOf(ntotalamount));
                 try {
            
             String le = "update sales_tempory SET Qty=?,Amount =?,Total_Amount=? where Product_Id=?";
           con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(le);
           
            pst.setString(1, txtqty.getText());
            pst.setString(2, txtamount.getText());
            pst.setString(3, txttotalamount.getText());
            pst.setString(4, txtproid.getText());

           
           pst.executeUpdate();
       
           
        txtproid.setText("");
        ibltyp.setText("");
        iblcategory.setText("");
        iblgrade.setText("");
        lbluniprice.setText("");
        txtamount.setText("");
        txtqty.setText("");
        lblqty.setText("");
           
        }  catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
           tablelord();    
               
            }
       
            else{
                
   double qty = Double.parseDouble(txtqty.getText().trim());
        double avqty = Double.parseDouble(lblqty.getText().trim());
        double newqty = avqty - qty ;
        lblqty.setText(String.valueOf(newqty));          

try {
            String sql = "insert into sales_tempory "
            + "(Product,Product_Id,Type,Category,Grade,Avalible_Quantity,Qty,Unit_Price,Amount,Total_Amount)"
            + "values (?,?,?,?,?,?,?,?,?,?)";

            pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(comboproduct.getSelectedItem()));
            pst.setString(2,txtproid.getText());
            pst.setString(3,ibltyp.getText());
            pst.setString(4,iblcategory.getText());
            pst.setString(5,iblgrade.getText());
            pst.setString(6,lblqty.getText());
            pst.setString(7,txtqty.getText());
            pst.setString(8,lbluniprice.getText());
            pst.setString(9,txtamount.getText());
            pst.setString(10,txttotalamount.getText());
            pst.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }


       tablelord();
       
        double sum =0;
        for (int k =0; k <odertable.getRowCount();k++)
        {
            sum = sum + Double.parseDouble(odertable.getValueAt(k, 7).toString());
        }
        txttotalamount.setText(Double.toString(sum));
      
        txtproid.setText("");
        ibltyp.setText("");
        iblcategory.setText("");
        iblgrade.setText("");
        lbluniprice.setText("");
        txtamount.setText("");
        txtqty.setText("");
        lblqty.setText("");
        
      } 
  }catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtcusidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusidKeyReleased
      txtcusid.setBackground(new Color(255,255,255));
    

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
       
 try {
            String sql ="select * from customers where cusid = ?";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
            String suid = txtcusid.getText();
            pst.setString(1, suid);
            rs= (ResultSet) pst.executeQuery();
     
            if (rs.next()== false){
               
               
                txtcusname.setText("");
                txtcusid.setText("");
            }
            else{
                String supname =rs.getString("cusname");
                txtcusname.setText(supname.trim());

            }

        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_txtcusidKeyReleased

    private void odertableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_odertableMouseClicked
    
        DefaultTableModel model = (DefaultTableModel)odertable.getModel();
        int selectedRowIndex = odertable.getSelectedRow();

        comboproduct.setSelectedItem(model.getValueAt(selectedRowIndex, 0).toString());
        txtproid.setText(model.getValueAt(selectedRowIndex, 1).toString());
        ibltyp.setText(model.getValueAt(selectedRowIndex, 2).toString());
        iblcategory.setText(model.getValueAt(selectedRowIndex, 3).toString());
        iblgrade.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txtqty.setText(model.getValueAt(selectedRowIndex, 5).toString());
        lbluniprice.setText(model.getValueAt(selectedRowIndex, 6).toString());
        txtamount.setText(model.getValueAt(selectedRowIndex, 7).toString());
        
        
      try {
            String sql ="select stock from product where  productID='"+txtproid.getText()+"'";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
            rs= (ResultSet) pst.executeQuery();
     
            if (rs.next()){
             lblqty.setText(rs.getString("stock"));
   
            }
           

        }  catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }   
          
    }//GEN-LAST:event_odertableMouseClicked

    private void comboproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboproductActionPerformed
        
    }//GEN-LAST:event_comboproductActionPerformed

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtqtyActionPerformed

    private void txtpay1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpay1KeyReleased
        txtpay1.setBackground(new Color(255,255,255));
        double tot = Double.parseDouble(lblamount.getText().trim());
        double pay = Double.parseDouble(txtpay1.getText());
   //     double bal = Double.parseDouble(lblupbalance.getText().trim());
        
         double total = tot - pay ;
         oldpay =+ pay;

         lblbalance.setText(String.valueOf(total));
    }//GEN-LAST:event_txtpay1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JRadioButton Lorderredio;
    private javax.swing.JButton btnAddCus;
    private javax.swing.JButton btnDeleteCus;
    private javax.swing.JButton btnGoBack;
    private javax.swing.JButton btnGoBack1;
    private javax.swing.JButton btnGoBack2;
    private javax.swing.JButton btnGoBack3;
    private javax.swing.JButton btnUpdateCus;
    private javax.swing.JButton btnUpdateCus1;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnreset1;
    private javax.swing.JButton btnreset2;
    private javax.swing.JComboBox<String> comboproduct;
    private javax.swing.JPanel deletesales;
    public static javax.swing.JRadioButton exorderredio;
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JPanel header2;
    private javax.swing.JPanel header3;
    private javax.swing.JLabel iblcategory;
    private javax.swing.JLabel iblgrade;
    private javax.swing.JLabel ibltyp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labldate;
    private javax.swing.JLabel labldate1;
    private javax.swing.JLabel lblamount;
    private javax.swing.JLabel lblamount1;
    public static javax.swing.JLabel lblbala;
    private javax.swing.JLabel lblbalance;
    private javax.swing.JLabel lblbalance1;
    private javax.swing.JLabel lblimagename;
    private javax.swing.JLabel lblimagename1;
    private javax.swing.JLabel lblimagename2;
    private javax.swing.JLabel lblimagename3;
    public static javax.swing.JLabel lblorderstu;
    private javax.swing.JLabel lblorderstu1;
    private javax.swing.JLabel lblorderstu2;
    private javax.swing.JLabel lblordtyp;
    private javax.swing.JLabel lblordtyp1;
    private javax.swing.JLabel lblordtype;
    private javax.swing.JLabel lblqty;
    public static javax.swing.JLabel lblsalsorid;
    private javax.swing.JLabel lblsupid;
    private javax.swing.JLabel lblsupid1;
    private javax.swing.JLabel lblsupname;
    private javax.swing.JLabel lblsupname1;
    private javax.swing.JLabel lbluniprice;
    private javax.swing.JPanel newSales;
    public static javax.swing.JTable odertable;
    private javax.swing.JPanel sa1;
    private javax.swing.JPanel sa1_AddPane;
    private javax.swing.JPanel sa1_DeletePane;
    private javax.swing.JPanel sa1_ReportPane;
    private javax.swing.JPanel sa1_UpdatePane;
    private javax.swing.JTable salesordertable;
    private javax.swing.JPanel salesrReport;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtcusid;
    public static javax.swing.JLabel txtcusname;
    public static javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtorderid;
    private javax.swing.JTextField txtorderid1;
    public static javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtpay1;
    private javax.swing.JTextField txtpay2;
    private javax.swing.JTextField txtproid;
    private javax.swing.JTextField txtqty;
    public static javax.swing.JTextField txttotalamount;
    private javax.swing.JPanel updatesales;
    // End of variables declaration//GEN-END:variables
}
