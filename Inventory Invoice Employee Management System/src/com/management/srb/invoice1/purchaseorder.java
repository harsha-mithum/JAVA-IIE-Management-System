/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.invoice1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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
public class purchaseorder extends javax.swing.JPanel {
 Connection con = null;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    Statement st=null;
    double oldpay = 0.0;
    double newpay = 0.0;
    
    /**
     * Creates new form purchaseorder
     */
    public purchaseorder() {
        initComponents();
       
    txttotalamount.setText("0.0");
    txtpay.setText("0.0");
    
         pu1_AddPane.setVisible(false);
        pu1_DeletePane.setVisible(false);
        pu1_UpdatePane.setVisible(false);
        pu1_ReportPane.setVisible(false);

        pu1.setVisible(true);
        
       
        
                   ordertable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
                   ordertable.getTableHeader().setOpaque(false);
                   ordertable.getTableHeader().setBackground(new Color(32,136,203));
                   ordertable.getTableHeader().setForeground(new Color(0,0,0));
                   ordertable.setRowHeight(25);
                    
        
        
        
        productdrop();
        dbconnect();
        autoidgen();
        currentdate();
        showtable();
        tablelord();
    
    
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
            rs = st.executeQuery("select Max(order_id) from purchase_orders");
             rs.next();
             rs.getString("Max(order_id)");
             if (rs.getString("Max(order_id)")== null)
             {
                 lblpurorid.setText("PUO0001");
             }
             else {
                 long id = Long.parseLong(rs.getString("Max(order_id)").substring(4,rs.getString("Max(order_id)").length()));
                 id++;
                 lblpurorid.setText("PUO" + String.format("%04d",id));
             }
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        
        }
            
        }
         public void currentdate(){
         
             SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
             Date date = new Date ();
             String dt = format.format(date);
             txtdate.setText(dt);
         } 
    
 public void balance (){
     double total = Double.parseDouble(txttotalamount.getText().trim());
        double pay = Double.parseDouble(txtpay.getText().trim());
        double bal = pay - total;
        String b=String.valueOf(bal);
        lblbala.setText(b);
         if (bal == 0){
         
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
                String qty =String.valueOf(rs.getInt("buyingprice"));
                unitpri.setText(qty);
                 }
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
 
   public void calqty1(){
         double qty = Double.parseDouble(txtqty.getText().trim());
      
        double price = Double.parseDouble(unitpri.getText().trim());
        
         double total = qty * price ;
         txtamount1.setText(String.valueOf(total));
         

       
    }
    public void calqty(){
         double qty = Double.parseDouble(txtqty.getText().trim());
        double avqty = Double.parseDouble(lblqty.getText().trim());
   

         
       double newqty = avqty + qty ;
       lblqty.setText(String.valueOf(newqty));
       
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
 
  public void showtable(){
             try {
            
              Statement st = con.createStatement();
              String sql ="select * from  purchase_orders";
               rs = st.executeQuery(sql);
              while (rs.next()){
                  String orderid = rs.getString("order_id");
                  String date = rs.getString("date");
                  String supplierid = rs.getString("supplier_id");
                  String supname = rs.getString("supplier_name");
                  double amu = rs.getDouble("total_amount");
                  String amount = Double.toString(amu);
                  String orderstu= rs.getString("order_status");
                  double py = rs.getDouble("pay");
                  String pay = Double.toString(py);
                  double balance = rs.getDouble("balance");
                  String bal= Double.toString(balance);
                  String custb[] ={orderid,date,supplierid,supname,amount,pay,bal,orderstu};
                    DefaultTableModel tbmodel = (DefaultTableModel)purchaseordertable.getModel();
                    tbmodel.addRow(custb);
                    
                    purchaseordertable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
                    purchaseordertable.getTableHeader().setOpaque(false);
                    purchaseordertable.getTableHeader().setBackground(new Color(32,136,203));
                    purchaseordertable.getTableHeader().setForeground(new Color(0,0,0));
                    purchaseordertable.setRowHeight(25);
                    
                     purchaseordertable.getColumnModel().getColumn(0).setPreferredWidth(130); 
                     purchaseordertable.getColumnModel().getColumn(1).setPreferredWidth(120);
                     purchaseordertable.getColumnModel().getColumn(2).setPreferredWidth(120);
                     purchaseordertable.getColumnModel().getColumn(3).setPreferredWidth(120);
                     purchaseordertable.getColumnModel().getColumn(4).setPreferredWidth(120);
                     purchaseordertable.getColumnModel().getColumn(5).setPreferredWidth(120);
                     purchaseordertable.getColumnModel().getColumn(6).setPreferredWidth(120);
                     purchaseordertable.getColumnModel().getColumn(7).setPreferredWidth(120);
                    
                   
                    
              }
              con.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
  
  
  
  
  
   private void tablelord()
                
               
                    
            { 
                try
                   {String sql="SELECT Product,Product_Id,Type,Category,Grade,Qty,Unit_Price,Amount FROM purchase_tempory";
                      con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=(PreparedStatement) con.prepareStatement(sql);
              
                    ResultSet rs= pst.executeQuery();
                    ordertable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
                   
                   
                    
                    
                    
                   }
                catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
                    
                    
                    
                   }
                    ordertable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
                    ordertable.getTableHeader().setOpaque(false);
                    ordertable.getTableHeader().setBackground(new Color(32,136,203));
                    ordertable.getTableHeader().setForeground(new Color(0,0,0));
                    ordertable.setRowHeight(25);
                    ordertable.getColumnModel().getColumn(0).setPreferredWidth(100); 
                    ordertable.getColumnModel().getColumn(1).setPreferredWidth(80); 
                    ordertable.getColumnModel().getColumn(2).setPreferredWidth(80); 
                    ordertable.getColumnModel().getColumn(3).setPreferredWidth(80); 
                    ordertable.getColumnModel().getColumn(4).setPreferredWidth(80); 
                    ordertable.getColumnModel().getColumn(5).setPreferredWidth(80); 
                    ordertable.getColumnModel().getColumn(6).setPreferredWidth(80); 
                    ordertable.getColumnModel().getColumn(7).setPreferredWidth(80); 
                    
                   
                   }  
  
  
  
  
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pu1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        addCustomer = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        CustomerReport = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        deleteCustomer = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        updateCustomer = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        pu1_AddPane = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        btnGoBack = new javax.swing.JButton();
        lblimagename = new javax.swing.JLabel();
        btnAddCus = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        lblpurorid = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        txtsupid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        unitpri = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        iblgrade = new javax.swing.JLabel();
        txtproid = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtsupname = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txttotalamount = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtpay = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        lblbala = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblorderstu = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        lblqty = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtamount1 = new javax.swing.JTextField();
        iblcategory = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        ibltype = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        comboproduct = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ordertable = new javax.swing.JTable();
        pu1_DeletePane = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        btnGoBack1 = new javax.swing.JButton();
        lblimagename1 = new javax.swing.JLabel();
        btnDeleteCus = new javax.swing.JButton();
        btnreset1 = new javax.swing.JButton();
        txtdelorderid = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        labldeldate = new javax.swing.JLabel();
        lbldelsupid = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lbldelsupname = new javax.swing.JLabel();
        lbldelamount = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtdelpay = new javax.swing.JTextField();
        lbldelbalance = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        lbldelorderstu = new javax.swing.JLabel();
        pu1_UpdatePane = new javax.swing.JPanel();
        header2 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        btnGoBack2 = new javax.swing.JButton();
        lblimagename2 = new javax.swing.JLabel();
        btnUpdateCus = new javax.swing.JButton();
        btnreset2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        txtuporderid = new javax.swing.JTextField();
        lablupdate = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblupsupid = new javax.swing.JLabel();
        lblupsupname = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblupamount = new javax.swing.JLabel();
        txtpay1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lblupbalance = new javax.swing.JLabel();
        lbluporderstu1 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        pu1_ReportPane = new javax.swing.JPanel();
        header3 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        btnGoBack3 = new javax.swing.JButton();
        lblimagename3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        purchaseordertable = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pu1.setBackground(new java.awt.Color(180, 170, 255));
        pu1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        pu1.setPreferredSize(new java.awt.Dimension(820, 700));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Purchase Order Section");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/information.png"))); // NOI18N
        jLabel15.setToolTipText("");

        addCustomer.setBackground(new java.awt.Color(255, 255, 255));
        addCustomer.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        addCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCustomerMouseClicked(evt);
            }
        });
        addCustomer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/p1.png"))); // NOI18N
        addCustomer.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 204, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Add Purchase Order");
        addCustomer.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        CustomerReport.setBackground(new java.awt.Color(255, 255, 255));
        CustomerReport.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        CustomerReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerReportMouseClicked(evt);
            }
        });
        CustomerReport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/p4.png"))); // NOI18N
        CustomerReport.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Purchase Order List");
        CustomerReport.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        deleteCustomer.setBackground(new java.awt.Color(255, 255, 255));
        deleteCustomer.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        deleteCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteCustomerMouseClicked(evt);
            }
        });
        deleteCustomer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/p2.png"))); // NOI18N
        deleteCustomer.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Delete Purchase Order");
        deleteCustomer.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        updateCustomer.setBackground(new java.awt.Color(255, 255, 255));
        updateCustomer.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        updateCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateCustomerMouseClicked(evt);
            }
        });
        updateCustomer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/p3.png"))); // NOI18N
        updateCustomer.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 0, 204));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Update Purchase Order");
        updateCustomer.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Welcome to Invoice System");

        javax.swing.GroupLayout pu1Layout = new javax.swing.GroupLayout(pu1);
        pu1.setLayout(pu1Layout);
        pu1Layout.setHorizontalGroup(
            pu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pu1Layout.createSequentialGroup()
                .addGroup(pu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pu1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pu1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel15))))
                    .addGroup(pu1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(addCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addGroup(pu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CustomerReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                        .addGap(56, 56, 56)
                        .addComponent(updateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        pu1Layout.setVerticalGroup(
            pu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pu1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(pu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(40, 40, 40)
                .addGroup(pu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(CustomerReport, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        pu1_AddPane.setBackground(new java.awt.Color(200, 210, 255));
        pu1_AddPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        pu1_AddPane.setToolTipText("");
        pu1_AddPane.setPreferredSize(new java.awt.Dimension(810, 712));
        pu1_AddPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 0, 104));
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Add Purchas Order :");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addContainerGap(670, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pu1_AddPane.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 0, 830, -1));

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
        pu1_AddPane.add(btnGoBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 646, 170, 30));

        lblimagename.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pu1_AddPane.add(lblimagename, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 200, 20));

        btnAddCus.setBackground(new java.awt.Color(0, 153, 204));
        btnAddCus.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnAddCus.setForeground(new java.awt.Color(255, 255, 255));
        btnAddCus.setText("Add Purchase");
        btnAddCus.setBorderPainted(false);
        btnAddCus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCusActionPerformed(evt);
            }
        });
        pu1_AddPane.add(btnAddCus, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 646, 170, 30));

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
        pu1_AddPane.add(btnreset, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 646, 170, 30));

        lblpurorid.setBackground(new java.awt.Color(255, 255, 255));
        lblpurorid.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblpurorid.setForeground(new java.awt.Color(51, 51, 51));
        lblpurorid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblpurorid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        pu1_AddPane.add(lblpurorid, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 95, 200, 37));

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Purchase Order ID :");
        pu1_AddPane.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 103, -1, -1));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Date :");
        pu1_AddPane.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 52, 50, -1));

        txtdate.setBackground(new java.awt.Color(200, 210, 255));
        txtdate.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtdate.setForeground(new java.awt.Color(51, 51, 51));
        txtdate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdateActionPerformed(evt);
            }
        });
        pu1_AddPane.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 52, 200, 32));

        txtsupid.setBackground(new java.awt.Color(200, 210, 255));
        txtsupid.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtsupid.setForeground(new java.awt.Color(51, 51, 51));
        txtsupid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtsupid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsupidKeyPressed(evt);
            }
        });
        pu1_AddPane.add(txtsupid, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 52, 200, 32));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Supplier ID :");
        pu1_AddPane.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 58, 100, -1));

        jLabel25.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Supplier Name :");
        pu1_AddPane.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 95, -1, 30));

        jLabel26.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Unit Price :");
        pu1_AddPane.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 90, 40));

        unitpri.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        unitpri.setForeground(new java.awt.Color(0, 0, 102));
        unitpri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unitpri.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        pu1_AddPane.add(unitpri, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 130, 30));

        jLabel27.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Quantity :");
        pu1_AddPane.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 80, 40));

        jLabel36.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Grade :");
        pu1_AddPane.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 150, -1, -1));

        iblgrade.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        iblgrade.setForeground(new java.awt.Color(0, 0, 102));
        iblgrade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iblgrade.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        pu1_AddPane.add(iblgrade, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 188, 140, 30));

        txtproid.setBackground(new java.awt.Color(200, 210, 255));
        txtproid.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtproid.setForeground(new java.awt.Color(0, 0, 102));
        txtproid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtproid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtproidKeyPressed(evt);
            }
        });
        pu1_AddPane.add(txtproid, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 188, 130, 30));

        jLabel28.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Product ID :");
        pu1_AddPane.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 139, 90, 40));

        jLabel29.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Product :");
        pu1_AddPane.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 150, 70, -1));

        txtsupname.setBackground(new java.awt.Color(255, 255, 255));
        txtsupname.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtsupname.setForeground(new java.awt.Color(51, 51, 51));
        txtsupname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        pu1_AddPane.add(txtsupname, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 95, 200, 37));

        jLabel31.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Total Amount :");
        pu1_AddPane.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 546, -1, 40));

        txttotalamount.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txttotalamount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txttotalamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttotalamountKeyPressed(evt);
            }
        });
        pu1_AddPane.add(txttotalamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 558, 150, 30));

        jLabel37.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Pay  :");
        pu1_AddPane.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 546, 60, 40));

        txtpay.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtpay.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtpay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpayKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpayKeyReleased(evt);
            }
        });
        pu1_AddPane.add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 558, 151, 30));

        jLabel38.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Balance  :");
        pu1_AddPane.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 588, 80, 40));

        lblbala.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblbala.setForeground(new java.awt.Color(0, 0, 102));
        lblbala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblbala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pu1_AddPane.add(lblbala, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 597, 148, 30));

        jLabel39.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Order Status  :");
        pu1_AddPane.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 597, 130, 30));

        lblorderstu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblorderstu.setForeground(new java.awt.Color(0, 0, 153));
        lblorderstu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblorderstu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pu1_AddPane.add(lblorderstu, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 597, 368, 30));

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pu1_AddPane.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 646, 170, 30));

        jLabel57.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 102));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Avalible Quantity :");
        pu1_AddPane.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 150, 40));

        lblqty.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblqty.setForeground(new java.awt.Color(0, 0, 102));
        lblqty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblqty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pu1_AddPane.add(lblqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 130, 30));

        jLabel58.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 102));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Amount :");
        pu1_AddPane.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 80, 40));

        txtamount1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtamount1.setForeground(new java.awt.Color(0, 0, 102));
        txtamount1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtamount1MouseClicked(evt);
            }
        });
        txtamount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtamount1KeyPressed(evt);
            }
        });
        pu1_AddPane.add(txtamount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 150, 30));

        iblcategory.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        iblcategory.setForeground(new java.awt.Color(0, 0, 102));
        iblcategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iblcategory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pu1_AddPane.add(iblcategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 140, 30));

        jLabel59.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 102));
        jLabel59.setText("Category :");
        pu1_AddPane.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, -1, -1));

        ibltype.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ibltype.setForeground(new java.awt.Color(0, 0, 102));
        ibltype.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ibltype.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pu1_AddPane.add(ibltype, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 140, 30));

        jLabel60.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 102));
        jLabel60.setText("Type :");
        pu1_AddPane.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, -1, -1));

        txtqty.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtqty.setForeground(new java.awt.Color(0, 0, 102));
        txtqty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtqtyMouseClicked(evt);
            }
        });
        txtqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtqtyKeyReleased(evt);
            }
        });
        pu1_AddPane.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 130, 30));

        comboproduct.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboproduct.setForeground(new java.awt.Color(0, 0, 102));
        comboproduct.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboproductPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        pu1_AddPane.add(comboproduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 130, 30));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 102));
        jButton3.setText("Update Stock");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pu1_AddPane.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 280, 150, 30));

        ordertable.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ordertable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(ordertable);

        pu1_AddPane.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 800, 220));

        add(pu1_AddPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 700));

        pu1_DeletePane.setBackground(new java.awt.Color(200, 210, 255));
        pu1_DeletePane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        pu1_DeletePane.setToolTipText("");
        pu1_DeletePane.setPreferredSize(new java.awt.Dimension(810, 712));

        header1.setBackground(new java.awt.Color(0, 0, 104));
        header1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Delete Purchase Order :");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addContainerGap(652, Short.MAX_VALUE))
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        lblimagename1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnDeleteCus.setBackground(new java.awt.Color(0, 0, 102));
        btnDeleteCus.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnDeleteCus.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteCus.setText("Delete Purchase");
        btnDeleteCus.setBorderPainted(false);
        btnDeleteCus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCusActionPerformed(evt);
            }
        });

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

        txtdelorderid.setBackground(new java.awt.Color(200, 210, 255));
        txtdelorderid.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtdelorderid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtdelorderid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdelorderidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdelorderidKeyReleased(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Purchase Order ID :");

        jLabel50.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("  Date");

        labldeldate.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        labldeldate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        lbldelsupid.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lbldelsupid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        jLabel51.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Supplier ID :");

        jLabel52.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Supplier Name :");

        lbldelsupname.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lbldelsupname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        lbldelamount.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lbldelamount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        jLabel53.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Total Amount :");

        jLabel54.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Pay :");

        txtdelpay.setBackground(new java.awt.Color(200, 210, 255));
        txtdelpay.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtdelpay.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtdelpay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdelpayKeyPressed(evt);
            }
        });

        lbldelbalance.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lbldelbalance.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        jLabel55.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Balance :");

        jLabel56.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Order Status  :");

        lbldelorderstu.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lbldelorderstu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        javax.swing.GroupLayout pu1_DeletePaneLayout = new javax.swing.GroupLayout(pu1_DeletePane);
        pu1_DeletePane.setLayout(pu1_DeletePaneLayout);
        pu1_DeletePaneLayout.setHorizontalGroup(
            pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnGoBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteCus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pu1_DeletePaneLayout.createSequentialGroup()
                                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel51))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pu1_DeletePaneLayout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addGap(6, 6, 6))
                            .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50))
                                .addGap(27, 27, 27)))
                        .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                                .addGap(281, 281, 281)
                                .addComponent(lblimagename1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbldelorderstu, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbldelbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdelpay, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbldelamount, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labldeldate, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbldelsupid, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdelorderid, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbldelsupname, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnreset1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pu1_DeletePaneLayout.setVerticalGroup(
            pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdelorderid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(30, 30, 30)
                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labldeldate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbldelsupid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbldelsupname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdelpay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbldelamount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                        .addComponent(lbldelbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lbldelorderstu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pu1_DeletePaneLayout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76)
                .addComponent(lblimagename1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pu1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnreset1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteCus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGoBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        add(pu1_DeletePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 700));

        pu1_UpdatePane.setBackground(new java.awt.Color(200, 210, 255));
        pu1_UpdatePane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        pu1_UpdatePane.setToolTipText("");
        pu1_UpdatePane.setPreferredSize(new java.awt.Dimension(810, 712));

        header2.setBackground(new java.awt.Color(0, 0, 104));
        header2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Update Purchase Order :");

        javax.swing.GroupLayout header2Layout = new javax.swing.GroupLayout(header2);
        header2.setLayout(header2Layout);
        header2Layout.setHorizontalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addContainerGap(646, Short.MAX_VALUE))
        );
        header2Layout.setVerticalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        lblimagename2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnUpdateCus.setBackground(new java.awt.Color(0, 0, 102));
        btnUpdateCus.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnUpdateCus.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateCus.setText("Update Purchase");
        btnUpdateCus.setBorderPainted(false);
        btnUpdateCus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCusActionPerformed(evt);
            }
        });

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

        jLabel40.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Purchase Order ID :");

        txtuporderid.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtuporderid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtuporderid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtuporderidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtuporderidKeyReleased(evt);
            }
        });

        lablupdate.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lablupdate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        jLabel42.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Date :");

        jLabel43.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Supplier ID :");

        lblupsupid.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblupsupid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        lblupsupname.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblupsupname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        jLabel44.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Supplier Name :");

        jLabel45.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Total Amount :");

        lblupamount.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblupamount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        txtpay1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtpay1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtpay1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpay1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpay1KeyReleased(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Pay :");

        jLabel47.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Balance :");

        lblupbalance.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblupbalance.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        lbluporderstu1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lbluporderstu1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        jLabel48.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Order Status  :");

        javax.swing.GroupLayout pu1_UpdatePaneLayout = new javax.swing.GroupLayout(pu1_UpdatePane);
        pu1_UpdatePane.setLayout(pu1_UpdatePaneLayout);
        pu1_UpdatePaneLayout.setHorizontalGroup(
            pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pu1_UpdatePaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblimagename2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pu1_UpdatePaneLayout.createSequentialGroup()
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pu1_UpdatePaneLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel43)
                            .addGroup(pu1_UpdatePaneLayout.createSequentialGroup()
                                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40))
                                .addGap(41, 41, 41)
                                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtuporderid, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lablupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblupsupid, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblupsupname, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblupamount, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpay1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblupbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbluporderstu1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pu1_UpdatePaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btnGoBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnreset2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnUpdateCus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pu1_UpdatePaneLayout.setVerticalGroup(
            pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pu1_UpdatePaneLayout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtuporderid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lablupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblupsupid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblupsupname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblupamount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpay1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pu1_UpdatePaneLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lblimagename2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pu1_UpdatePaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblupbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbluporderstu1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(pu1_UpdatePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGoBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreset2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateCus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        add(pu1_UpdatePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 700));

        pu1_ReportPane.setBackground(new java.awt.Color(200, 210, 255));
        pu1_ReportPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        pu1_ReportPane.setToolTipText("");
        pu1_ReportPane.setPreferredSize(new java.awt.Dimension(810, 712));
        pu1_ReportPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header3.setBackground(new java.awt.Color(0, 0, 104));
        header3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("List Of Perchase Order :");

        javax.swing.GroupLayout header3Layout = new javax.swing.GroupLayout(header3);
        header3.setLayout(header3Layout);
        header3Layout.setHorizontalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addContainerGap(655, Short.MAX_VALUE))
        );
        header3Layout.setVerticalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pu1_ReportPane.add(header3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, -1));

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
        pu1_ReportPane.add(btnGoBack3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 170, 30));

        lblimagename3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pu1_ReportPane.add(lblimagename3, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 470, 250, 20));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        purchaseordertable.setBackground(new java.awt.Color(122, 204, 255));
        purchaseordertable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        purchaseordertable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        purchaseordertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ORDER ID", "DATE", "SUPPLIER ID", "SUPPLIER", "TOTAL AMOUNT", "PAY", "BALANCE", "ORDER STATUS"
            }
        ));
        purchaseordertable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        purchaseordertable.setFocusable(false);
        purchaseordertable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        purchaseordertable.setShowVerticalLines(false);
        jScrollPane2.setViewportView(purchaseordertable);

        pu1_ReportPane.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 780, 420));

        add(pu1_ReportPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 700));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack3ActionPerformed
        pu1_AddPane.setVisible(false);
        pu1_DeletePane.setVisible(false);
        pu1_UpdatePane.setVisible(false);
        pu1_ReportPane.setVisible(false);

        pu1.setVisible(true);
    }//GEN-LAST:event_btnGoBack3ActionPerformed

    private void addCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCustomerMouseClicked
        // TODO add your handling code here:
        pu1_AddPane.setVisible(true);
        pu1_DeletePane.setVisible(false);
        pu1_UpdatePane.setVisible(false);
        pu1_ReportPane.setVisible(false);

        pu1.setVisible(false);
    }//GEN-LAST:event_addCustomerMouseClicked

    private void CustomerReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerReportMouseClicked
        pu1_AddPane.setVisible(false);
        pu1_DeletePane.setVisible(false);
        pu1_UpdatePane.setVisible(false);
        pu1_ReportPane.setVisible(true);

        pu1.setVisible(false);
    }//GEN-LAST:event_CustomerReportMouseClicked

    private void deleteCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteCustomerMouseClicked
        // TODO add your handling code here:
        pu1_AddPane.setVisible(false);
        pu1_DeletePane.setVisible(true);
        pu1_UpdatePane.setVisible(false);
        pu1_ReportPane.setVisible(false);

        pu1.setVisible(false);
    }//GEN-LAST:event_deleteCustomerMouseClicked

    private void updateCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateCustomerMouseClicked
        // TODO add your handling code here:
        pu1_AddPane.setVisible(false);
        pu1_DeletePane.setVisible(false);
        pu1_UpdatePane.setVisible(true);
        pu1_ReportPane.setVisible(false);

        pu1.setVisible(false);
    }//GEN-LAST:event_updateCustomerMouseClicked

    private void btnGoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBackActionPerformed
        // TODO add your handling code here:
        pu1_AddPane.setVisible(false);
        pu1_DeletePane.setVisible(false);
        pu1_UpdatePane.setVisible(false);
        pu1_ReportPane.setVisible(false);

        pu1.setVisible(true);
    }//GEN-LAST:event_btnGoBackActionPerformed

    private void btnAddCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCusActionPerformed

        if (lblpurorid.getText().isEmpty()){
        lblpurorid.setBackground(new Color(255,0,51));
        JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(txtdate.getText().isEmpty()){
            txtdate.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(txtsupid.getText().isEmpty()){
            txtsupid.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
    }
    else if(txtsupname.getText().isEmpty()){
            txtsupname.setBackground(new Color(255,0,51));
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
    else{
        
        
            try {
            String prodata = "SELECT Product_Id,Qty FROM purchase_tempory ";
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
                    Double totalstock = tqty+qty;
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
            String sql = "insert into purchase_orders "
            + "(order_id,date,supplier_id,supplier_name,total_amount,pay,balance,order_status)"
            + "values (?,?,?,?,?,?,?,?)";

            pst = con.prepareStatement(sql);
            pst.setString(1,lblpurorid.getText());
            
            pst.setString(2,txtdate.getText());
            pst.setString(3,txtsupid.getText());
            pst.setString(4,txtsupname.getText());
            double totamu = Double.parseDouble(txttotalamount.getText().toString());
            pst.setDouble(5,totamu);
             double pay = Double.parseDouble(txtpay.getText().toString());
            pst.setDouble(6,pay);
            double bal = Double.parseDouble(lblbala.getText().toString());
            pst.setDouble(7,bal);
            pst.setString(8,lblorderstu.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Add Successfull");
             autoidgen();
          
            txtsupid.setText("");
            txtsupname.setText("");
                  
        txtproid.setText("");
        ibltype.setText("");
        iblcategory.setText("");
        iblgrade.setText("");
        unitpri.setText("");
        txtamount1.setText("");
        txtqty.setText("");
        txttotalamount.setText("0.0");
        lblqty.setText("");
        txtpay.setText("0.0");
        lblorderstu.setText("");
        lblbala.setText("");
            
        
        
        
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
showtable();
tablelord();
    }//GEN-LAST:event_btnAddCusActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
 autoidgen();
       //   txtdate.setText("");
        txtsupid.setText("");
        txtsupname.setText("");
        txttotalamount.setText("0.0");
        txtpay.setText("");
        lblbala.setText("");
        lblorderstu.setText("");
        txtpay.setText("0.0");
          try {
         String le = "DELETE FROM`purchase_tempory`";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(le);
            pst.executeUpdate();

         
         }    
            catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        
        }  
     tablelord();     
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnGoBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack2ActionPerformed
        pu1_AddPane.setVisible(false);
        pu1_DeletePane.setVisible(false);
        pu1_UpdatePane.setVisible(false);
        pu1_ReportPane.setVisible(false);

        pu1.setVisible(true);
    }//GEN-LAST:event_btnGoBack2ActionPerformed

    private void btnUpdateCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCusActionPerformed
 
Double pa=null;
String ords=null;
        try {
            String sql ="select pay from purchase_orders where  order_id=?";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
            String ouid = txtuporderid.getText();
            pst.setString(1, ouid);
            rs= (ResultSet) pst.executeQuery();
     
            if (rs.next()){
              String p =rs.getString("pay");
              pa=Double.valueOf(p);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);

        }
        

String ord=lblupbalance.getText();
Double or=Double.valueOf(ord);
if(or==0.0)
{
    ords="Paid";
    lbluporderstu1.setText("Paid");
}
else
{
     ords="Incomplete order";
     lbluporderstu1.setText("Incomplete order");
}
        
        if(txtuporderid.getText().isEmpty()){
          txtuporderid.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lablupdate.getText().isEmpty()){
          lablupdate.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblupsupid.getText().isEmpty()){
          lblupsupid.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblupsupname.getText().isEmpty()){
          lblupsupname.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblupamount.getText().isEmpty()){
          lblupamount.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
       else if(txtpay1.getText().isEmpty()){
          txtpay1.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lblupbalance.getText().isEmpty()){
          lblupbalance.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else if(lbluporderstu1.getText().isEmpty()){
         lbluporderstu1.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Missing Information");
      }
      else {
          
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to update this record!!","update Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
            
        try {
            
             String le = "update purchase_orders set date=?,supplier_id =?,supplier_name=?,total_amount =? ,pay=?,balance=?,order_status=? where order_id=?";
           con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(le);
           
            pst.setString(1, lablupdate.getText());
            pst.setString(2, lblupsupid.getText());
            pst.setString(3, lblupsupname.getText());
            pst.setString(4,  lblupamount.getText());
            pst.setDouble(5, oldpay);
            pst.setString(6, lblupbalance.getText());
            pst.setString(7, ords);

           
            pst.setString(8, txtuporderid.getText());
           
           pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFULLY");
               txtuporderid.setText("");
               lablupdate.setText("");
               lblupsupid.setText("");
               lblupsupname.setText("");
               lblupamount.setText("");
               txtpay1.setText("");
               lblupbalance.setText("");
               lbluporderstu1.setText("");
           
        }  catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
      }
          }  
        showtable();      
    }//GEN-LAST:event_btnUpdateCusActionPerformed

    private void btnreset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreset2ActionPerformed

               txtuporderid.setText("");
                lablupdate.setText("");
                lblupsupid.setText("");
                lblupsupname.setText("");  
                lblupamount.setText("");
                txtpay1.setText("");
                lblupbalance.setText("");
                lbluporderstu1.setText("");        
    }//GEN-LAST:event_btnreset2ActionPerformed

    private void btnreset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreset1ActionPerformed
 txtdelorderid.setText("");
                labldeldate.setText("");
                 lbldelsupid.setText("");  
                 lbldelsupname.setText(""); 
                 lbldelamount.setText("");   
                  txtdelpay.setText("");
                  lbldelbalance.setText("");
                    lbldelorderstu.setText("");          
    }//GEN-LAST:event_btnreset1ActionPerformed

    private void btnDeleteCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCusActionPerformed
    if(txtdelorderid.getText().isEmpty()) {
            txtdelorderid.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Purchase Order ID field is empty !!");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Delete !!", "Delete", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    String query = "DELETE FROM sales_orders WHERE order_id=?";
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst = con.prepareStatement(query);
                    pst.setString(1, txtdelorderid.getText());
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Order Delete Successfully ");
                    txtdelorderid.setText("");
                    labldeldate.setText("");
                    lbldelsupid.setText("");
                    lbldelsupname.setText("");
                    lbldelamount.setText("");
                    txtdelpay.setText("");
                    lbldelbalance.setText("");
                    lbldelorderstu.setText("");
                  
                   

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
            }
        }      
    }//GEN-LAST:event_btnDeleteCusActionPerformed

    private void btnGoBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack1ActionPerformed
        pu1_AddPane.setVisible(false);
        pu1_DeletePane.setVisible(false);
        pu1_UpdatePane.setVisible(false);
        pu1_ReportPane.setVisible(false);

        pu1.setVisible(true);
    }//GEN-LAST:event_btnGoBack1ActionPerformed

    private void txtsupidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupidKeyPressed
  txtsupid.setBackground(new Color(255,255,255));
    

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
       
 try {
            String sql ="select * from suppliers where supplierid = ?";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
            String suid = txtsupid.getText();
            pst.setString(1, suid);
            rs= (ResultSet) pst.executeQuery();
     
            if (rs.next()== false){
               
               
                txtsupname.setText("");
                txtsupid.setText("");
            }
            else{
                String supname =rs.getString("supplier_name");
                txtsupname.setText(supname.trim());

            }

        } catch (SQLException ex) {
            

        }
    }//GEN-LAST:event_txtsupidKeyPressed

    private void txtpayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpayKeyPressed
txtpay.setBackground(new Color(255,255,255));
        balance ();
    }//GEN-LAST:event_txtpayKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       print_purchase_order ppo = new print_purchase_order();
      
       print_purchase_order.lblorderid.setText(purchaseorder.lblpurorid.getText());
       print_purchase_order.lbldate.setText(purchaseorder.txtdate.getText());
       print_purchase_order.lblcusid.setText(purchaseorder.txtsupid.getText());
       print_purchase_order.lblcusname.setText(purchaseorder.txtsupname.getText());
       print_purchase_order.productable.setModel(ordertable.getModel());
       print_purchase_order.lbltotamu.setText(purchaseorder.txttotalamount.getText());
       print_purchase_order.lblpay.setText(purchaseorder.txtpay.getText());
       print_purchase_order.lblbala.setText(purchaseorder.lblbala.getText());
       print_purchase_order.lblordstu.setText(purchaseorder.lblorderstu.getText());
        ppo.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtpayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpayKeyReleased
       balance ();
    }//GEN-LAST:event_txtpayKeyReleased

    private void txtuporderidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtuporderidKeyPressed
txtuporderid.setBackground(new Color(255,255,255));
       
        
    }//GEN-LAST:event_txtuporderidKeyPressed

    private void txtuporderidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtuporderidKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        try {
             String sql ="select * from purchase_orders where order_id = ? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
             String ordid = txtuporderid.getText();
            pst.setString(1, ordid);
            rs= (ResultSet) pst.executeQuery();
            
           
           if (rs.next()== false){
               
               txtuporderid.setText("");
                lablupdate.setText("");
                lblupsupid.setText("");
                lblupsupname.setText("");  
                lblupamount.setText("");
                txtpay1.setText("");
                lblupbalance.setText("");
                lbluporderstu1.setText("");
           }
           else{
               String date =rs.getString("date");
                  lablupdate.setText(date.trim());
                  String supid =rs.getString("supplier_id");
                  lblupsupid.setText(supid.trim());
                  String supname =rs.getString("supplier_name");
                  lblupsupname.setText(supname.trim());
                 
                  lblupamount.setText(rs.getString("total_amount"));
                
                  txtpay1.setText(rs.getString("pay"));
                  this.oldpay = (rs.getDouble("pay"));
                  lblupbalance.setText(rs.getString("balance"));
                  String ordersty =rs.getString("order_status");
                  lbluporderstu1.setText(ordersty.trim());
                  
                }
       
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }//GEN-LAST:event_txtuporderidKeyReleased

    private void txtdelorderidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdelorderidKeyPressed
      txtdelorderid.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtdelorderidKeyPressed

    private void txtdelorderidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdelorderidKeyReleased
         if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        try {
             String sql ="select * from purchase_orders where order_id = ? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
             String ordid = txtdelorderid.getText();
            pst.setString(1, ordid);
            rs= (ResultSet) pst.executeQuery();
          
           if (rs.next()== false){
              
               txtdelorderid.setText("");
                labldeldate.setText("");
                 lbldelsupid.setText("");  
                 lbldelsupname.setText(""); 
                 lbldelamount.setText("");   
                  txtdelpay.setText("");
                  lbldelbalance.setText("");
                    lbldelorderstu.setText("");       
                          
           }
           else{
               String date =rs.getString("date");
                  labldeldate.setText(date.trim());
                  String supid =rs.getString("supplier_id");
                  lbldelsupid.setText(supid.trim());
                  String supname =rs.getString("supplier_name");
                  lbldelsupname.setText(supname.trim());
                 
                  lbldelamount.setText(rs.getString("total_amount"));
                 
                  txtdelpay.setText(rs.getString("pay"));
                
                  lbldelbalance.setText(rs.getString("balance"));
                  String ordersty =rs.getString("order_status");
                  lbldelorderstu.setText(ordersty.trim());
                  
                }
       
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }//GEN-LAST:event_txtdelorderidKeyReleased

    private void txtproidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproidKeyPressed
       txtproid.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtproidKeyPressed

    private void txttotalamountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalamountKeyPressed
       txttotalamount.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txttotalamountKeyPressed

    private void txtdelpayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdelpayKeyPressed
        txtdelpay.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtdelpayKeyPressed

    private void txtpay1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpay1KeyPressed
        
        
    }//GEN-LAST:event_txtpay1KeyPressed

    private void txtdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdateActionPerformed

    private void txtamount1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtamount1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtamount1MouseClicked

    private void txtamount1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtamount1KeyPressed
      
    }//GEN-LAST:event_txtamount1KeyPressed

    private void txtqtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtqtyMouseClicked
        

    }//GEN-LAST:event_txtqtyMouseClicked

    private void txtqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyReleased
calqty1();     
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
                ibltype.setText(rs.getString("typeID"));
                iblcategory.setText(rs.getString("categoryID"));
                String qty =String.valueOf(rs.getDouble("stock"));
                lblqty.setText(qty);
                price();
            }
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboproductPopupMenuWillBecomeInvisible

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed


  try {
            String prodata = "SELECT product_Id FROM purchase_tempory WHERE  Product_Id=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(prodata);
            pst.setString(1, txtproid.getText());
            rs= (ResultSet) pst.executeQuery();
          
            rs = pst.executeQuery();
            if(rs.next()){
                
                  DefaultTableModel model = (DefaultTableModel)ordertable.getModel();
        int selectedRowIndex = ordertable.getSelectedRow();
        String amount=(model.getValueAt(selectedRowIndex, 7).toString());
        Double amount1 = Double.valueOf(amount);
        
           Double tamount = Double.valueOf(txttotalamount.getText());
           
           Double minamount = tamount - amount1;
                
              Double namount = Double.valueOf(txtamount1.getText());
         Double ntotalamount= minamount + namount;
         txttotalamount.setText(String.valueOf(ntotalamount));
                 try {
            
             String le = "update purchase_tempory SET Qty=?,Amount =?,Total_Amount=? where Product_Id=?";
           con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(le);
           
            pst.setString(1, txtqty.getText());
            pst.setString(2, txtamount1.getText());
            pst.setString(3, txttotalamount.getText());
            pst.setString(4, txtproid.getText());

           
           pst.executeUpdate();
       
           
        txtproid.setText("");
        ibltype.setText("");
        iblcategory.setText("");
        iblgrade.setText("");
        unitpri.setText("");
        txtamount1.setText("");
        txtqty.setText("");
        lblqty.setText("");
           
        }  catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
           tablelord();    
               
            }
       
            else{
    calqty();            

try {
            String sql = "insert into purchase_tempory "
            + "(Product,Product_Id,Type,Category,Grade,Avalible_Quantity,Qty,Unit_Price,Amount,Total_Amount)"
            + "values (?,?,?,?,?,?,?,?,?,?)";

            pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(comboproduct.getSelectedItem()));
            pst.setString(2,txtproid.getText());
            pst.setString(3,ibltype.getText());
            pst.setString(4,iblcategory.getText());
            pst.setString(5,iblgrade.getText());
            pst.setString(6,lblqty.getText());
            pst.setString(7,txtqty.getText());
            pst.setString(8,unitpri.getText());
            pst.setString(9,txtamount1.getText());
            pst.setString(10,txttotalamount.getText());
            pst.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        }


       tablelord();
       
        double sum =0;
        for (int k =0; k <ordertable.getRowCount();k++)
        {
            sum = sum + Double.parseDouble(ordertable.getValueAt(k, 7).toString());
        }
        txttotalamount.setText(Double.toString(sum));
      
        txtproid.setText("");
        ibltype.setText("");
        iblcategory.setText("");
        iblgrade.setText("");
        unitpri.setText("");
        txtamount1.setText("");
        txtqty.setText("");
        lblqty.setText("");
        
      } 
  }catch (SQLException ex) {
            Logger.getLogger(purchaseorder.class.getName()).log(Level.SEVERE, null, ex);
        } 
  
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ordertableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordertableMouseClicked
     DefaultTableModel model = (DefaultTableModel)ordertable.getModel();
        int selectedRowIndex = ordertable.getSelectedRow();

        comboproduct.setSelectedItem(model.getValueAt(selectedRowIndex, 0).toString());
        txtproid.setText(model.getValueAt(selectedRowIndex, 1).toString());
        ibltype.setText(model.getValueAt(selectedRowIndex, 2).toString());
        iblcategory.setText(model.getValueAt(selectedRowIndex, 3).toString());
        iblgrade.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txtqty.setText(model.getValueAt(selectedRowIndex, 5).toString());
        unitpri.setText(model.getValueAt(selectedRowIndex, 6).toString());
        txtamount1.setText(model.getValueAt(selectedRowIndex, 7).toString());

 
    }//GEN-LAST:event_ordertableMouseClicked

    private void txtpay1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpay1KeyReleased
        txtpay1.setBackground(new Color(255,255,255));
        double tot = Double.parseDouble(lblupamount.getText().trim());
        double pay = Double.parseDouble(txtpay1.getText());
   //     double bal = Double.parseDouble(lblupbalance.getText().trim());
        
         double total = tot - pay ;

         lblupbalance.setText(String.valueOf(total));
         oldpay =+ pay;
    }//GEN-LAST:event_txtpay1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CustomerReport;
    private javax.swing.JPanel addCustomer;
    private javax.swing.JButton btnAddCus;
    private javax.swing.JButton btnDeleteCus;
    private javax.swing.JButton btnGoBack;
    private javax.swing.JButton btnGoBack1;
    private javax.swing.JButton btnGoBack2;
    private javax.swing.JButton btnGoBack3;
    private javax.swing.JButton btnUpdateCus;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnreset1;
    private javax.swing.JButton btnreset2;
    private javax.swing.JComboBox<String> comboproduct;
    private javax.swing.JPanel deleteCustomer;
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JPanel header2;
    private javax.swing.JPanel header3;
    private javax.swing.JLabel iblcategory;
    private javax.swing.JLabel iblgrade;
    private javax.swing.JLabel ibltype;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labldeldate;
    private javax.swing.JLabel lablupdate;
    public static javax.swing.JLabel lblbala;
    private javax.swing.JLabel lbldelamount;
    private javax.swing.JLabel lbldelbalance;
    private javax.swing.JLabel lbldelorderstu;
    private javax.swing.JLabel lbldelsupid;
    private javax.swing.JLabel lbldelsupname;
    private javax.swing.JLabel lblimagename;
    private javax.swing.JLabel lblimagename1;
    private javax.swing.JLabel lblimagename2;
    private javax.swing.JLabel lblimagename3;
    public static javax.swing.JLabel lblorderstu;
    public static javax.swing.JLabel lblpurorid;
    private javax.swing.JLabel lblqty;
    private javax.swing.JLabel lblupamount;
    private javax.swing.JLabel lblupbalance;
    private javax.swing.JLabel lbluporderstu1;
    private javax.swing.JLabel lblupsupid;
    private javax.swing.JLabel lblupsupname;
    public static javax.swing.JTable ordertable;
    private javax.swing.JPanel pu1;
    private javax.swing.JPanel pu1_AddPane;
    private javax.swing.JPanel pu1_DeletePane;
    private javax.swing.JPanel pu1_ReportPane;
    private javax.swing.JPanel pu1_UpdatePane;
    private javax.swing.JTable purchaseordertable;
    private javax.swing.JTextField txtamount1;
    public static javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtdelorderid;
    private javax.swing.JTextField txtdelpay;
    public static javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtpay1;
    private javax.swing.JTextField txtproid;
    private javax.swing.JTextField txtqty;
    public static javax.swing.JTextField txtsupid;
    public static javax.swing.JLabel txtsupname;
    public static javax.swing.JTextField txttotalamount;
    private javax.swing.JTextField txtuporderid;
    private javax.swing.JLabel unitpri;
    private javax.swing.JPanel updateCustomer;
    // End of variables declaration//GEN-END:variables
}
