/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.inventory;

import com.barcodelib.barcode.Linear;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Avishka
 */
public class product extends javax.swing.JPanel {

    String dburl = "jdbc:mysql://localhost:3306/sr_bio_foods";
    String dbuname = "root";
    String dbpass = "";

    Connection Con = null;
    Statement St = null;
    ResultSet Rs = null;

        String upimageFilePath;
    String upimageFileName;
    String imageFilePath;
    String imageFileName;
    InputStream inputstream;
        InputStream upinputstream;
    InputStream barcodeinputstream;
    String barcodeFilePath;
    Connection con = null;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st = null;

    /**
     * Creates new form product
     */
    public product() {
        initComponents();
        SelectProd();
        fillComboBox();
        setView();
    }

    public void SelectProd() {
        try {
            Con = DriverManager.getConnection(dburl, dbuname, dbpass);
            St = Con.createStatement();
            Rs = St.executeQuery("SELECT * FROM product");
            ProductTable.setModel(DbUtils.resultSetToTableModel(Rs));

            ProductTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
            ProductTable.getTableHeader().setOpaque(false);
            ProductTable.getTableHeader().setBackground(new Color(32, 136, 203));
            ProductTable.getTableHeader().setForeground(new Color(0, 0, 0));
            ProductTable.setRowHeight(25);

            ProductTable.getColumnModel().getColumn(0).setPreferredWidth(130);
            ProductTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            ProductTable.getColumnModel().getColumn(2).setPreferredWidth(120);
            ProductTable.getColumnModel().getColumn(3).setPreferredWidth(120);
            ProductTable.getColumnModel().getColumn(4).setPreferredWidth(120);
            ProductTable.getColumnModel().getColumn(5).setPreferredWidth(120);
            ProductTable.getColumnModel().getColumn(6).setPreferredWidth(120);
            ProductTable.getColumnModel().getColumn(7).setPreferredWidth(120);
            ProductTable.getColumnModel().getColumn(8).setPreferredWidth(120);
            ProductTable.getColumnModel().getColumn(9).setPreferredWidth(120);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillComboBox() {
        try {
            String query = "select distinct catName from category order by catName";
            PreparedStatement pst = Con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //shows topic data in combobox
                txtProductCategory.addItem(rs.getString("catName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String query = "select distinct name from type order by name";
            PreparedStatement pst = Con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //shows topic data in combobox
                txtProductType.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setView() {
        
        try {
            PreparedStatement ps = Con.prepareStatement("SELECT MAX(Substring(productID,4,LENGTH(productID))) FROM product");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                this.txtProductID.setText(Integer.toString(rs.getInt(1) + 1));
                int number = Integer.valueOf(this.txtProductID.getText());
                String padded = String.format("%03d", number);
                this.txtProductID.setText("PRD" + padded);
            }
        } catch (SQLException e) {

        }
        txtProductName.setText("");
        txtMinimumQuantity.setText("");
        txtStock.setText("");
        lbl_image.setText("");
        txtProductCategory.setSelectedIndex(0);
        txtProductType.setSelectedIndex(0);
        radioBtnOne.setSelected(true);
        r_Active.setSelected(true);
        this.chkGradeFirst.setSelected(false);
        this.chkGradeSecond.setSelected(false);
        this.chkGradeThird.setSelected(false);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioBtnOne);
        bg.add(radioBtnTwo);
        bg.add(radioBtnThree);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(r_Active);
        bg2.add(r_Inactive);
        
        this.barcode();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CenterPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        AddBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        UpBtn = new javax.swing.JButton();
        txtProductID = new javax.swing.JTextField();
        txtProductName = new javax.swing.JTextField();
        txtMinimumQuantity = new javax.swing.JTextField();
        txtProductCategory = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        AddImageBtn = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtProductType = new javax.swing.JComboBox<>();
        lblgender = new javax.swing.JLabel();
        radioBtnOne = new javax.swing.JRadioButton();
        radioBtnThree = new javax.swing.JRadioButton();
        lblstatus = new javax.swing.JLabel();
        r_Active = new javax.swing.JRadioButton();
        r_Inactive = new javax.swing.JRadioButton();
        radioBtnTwo = new javax.swing.JRadioButton();
        chkGradeThird = new javax.swing.JCheckBox();
        chkGradeFirst = new javax.swing.JCheckBox();
        chkGradeSecond = new javax.swing.JCheckBox();
        lblstatus1 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        header3 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        lblbarcodepreview = new javax.swing.JLabel();
        lblbarcode = new javax.swing.JLabel();
        btngeneratebarcode = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblimagename2 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CenterPanel1.setBackground(new java.awt.Color(200, 210, 255));
        CenterPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        CenterPanel1.setPreferredSize(new java.awt.Dimension(810, 712));
        CenterPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Product ID");
        CenterPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 61, -1, -1));

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Product Name");
        CenterPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 99, -1, -1));

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Minim QTY (kg)");
        CenterPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 212, -1, -1));

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Category");
        CenterPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 139, -1, -1));

        AddBtn.setBackground(new java.awt.Color(0, 0, 102));
        AddBtn.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        AddBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddBtn.setText("Add");
        AddBtn.setBorder(null);
        AddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBtnMouseClicked(evt);
            }
        });
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });
        CenterPanel1.add(AddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 330, 175, 31));

        DeleteBtn.setBackground(new java.awt.Color(255, 0, 51));
        DeleteBtn.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        DeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBtn.setText("Delete");
        DeleteBtn.setBorder(null);
        DeleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteBtnMouseClicked(evt);
            }
        });
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });
        CenterPanel1.add(DeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 281, 175, 31));

        UpBtn.setBackground(new java.awt.Color(0, 153, 204));
        UpBtn.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        UpBtn.setForeground(new java.awt.Color(255, 255, 255));
        UpBtn.setText("Update");
        UpBtn.setBorder(null);
        UpBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpBtnMouseClicked(evt);
            }
        });
        UpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpBtnActionPerformed(evt);
            }
        });
        CenterPanel1.add(UpBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 387, 175, 31));

        txtProductID.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        txtProductID.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtProductID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductIDActionPerformed(evt);
            }
        });
        txtProductID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductIDKeyPressed(evt);
            }
        });
        CenterPanel1.add(txtProductID, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 60, 140, -1));

        txtProductName.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        txtProductName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameActionPerformed(evt);
            }
        });
        txtProductName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductNameKeyPressed(evt);
            }
        });
        CenterPanel1.add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 95, 140, -1));

        txtMinimumQuantity.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        txtMinimumQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtMinimumQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinimumQuantityActionPerformed(evt);
            }
        });
        txtMinimumQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMinimumQuantityKeyPressed(evt);
            }
        });
        CenterPanel1.add(txtMinimumQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 211, 140, -1));

        txtProductCategory.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        txtProductCategory.setForeground(new java.awt.Color(255, 0, 51));
        txtProductCategory.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtProductCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductCategoryActionPerformed(evt);
            }
        });
        CenterPanel1.add(txtProductCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 131, 140, 20));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        ProductTable.setBackground(new java.awt.Color(122, 204, 255));
        ProductTable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        ProductTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProdID", "Name", "Quantity", "Description", "Category"
            }
        ));
        ProductTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        ProductTable.setFocusable(false);
        ProductTable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        ProductTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ProductTable);

        CenterPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 505, 800, 189));

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Product List");
        CenterPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 463, -1, -1));

        lbl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbl_image.setMaximumSize(new java.awt.Dimension(200, 200));
        CenterPanel1.add(lbl_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(623, 92, 170, 157));

        AddImageBtn.setBackground(new java.awt.Color(0, 153, 204));
        AddImageBtn.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        AddImageBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddImageBtn.setText("Add Image");
        AddImageBtn.setBorder(null);
        AddImageBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddImageBtnMouseClicked(evt);
            }
        });
        AddImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddImageBtnActionPerformed(evt);
            }
        });
        CenterPanel1.add(AddImageBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(623, 288, 170, 25));

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Type");
        CenterPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 172, -1, -1));

        txtProductType.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        txtProductType.setForeground(new java.awt.Color(255, 0, 51));
        txtProductType.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductTypeActionPerformed(evt);
            }
        });
        CenterPanel1.add(txtProductType, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 172, 140, 20));

        lblgender.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblgender.setForeground(new java.awt.Color(51, 51, 51));
        lblgender.setText("Preservation :");
        CenterPanel1.add(lblgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 172, -1, -1));

        radioBtnOne.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        radioBtnOne.setText("1-2");
        radioBtnOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioBtnOneMouseClicked(evt);
            }
        });
        radioBtnOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnOneActionPerformed(evt);
            }
        });
        CenterPanel1.add(radioBtnOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 211, -1, -1));

        radioBtnThree.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        radioBtnThree.setText("5-7");
        radioBtnThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioBtnThreeMouseClicked(evt);
            }
        });
        radioBtnThree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnThreeActionPerformed(evt);
            }
        });
        CenterPanel1.add(radioBtnThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(537, 211, -1, -1));

        lblstatus.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblstatus.setForeground(new java.awt.Color(51, 51, 51));
        lblstatus.setText("Grade :");
        CenterPanel1.add(lblstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 99, -1, -1));

        r_Active.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        r_Active.setText("Active");
        r_Active.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_ActiveMouseClicked(evt);
            }
        });
        r_Active.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_ActiveActionPerformed(evt);
            }
        });
        CenterPanel1.add(r_Active, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 268, 100, -1));

        r_Inactive.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        r_Inactive.setText("In-Active");
        r_Inactive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_InactiveMouseClicked(evt);
            }
        });
        r_Inactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_InactiveActionPerformed(evt);
            }
        });
        CenterPanel1.add(r_Inactive, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 268, 100, -1));

        radioBtnTwo.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        radioBtnTwo.setText("3-5");
        radioBtnTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioBtnTwoMouseClicked(evt);
            }
        });
        radioBtnTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnTwoActionPerformed(evt);
            }
        });
        CenterPanel1.add(radioBtnTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 211, -1, -1));

        chkGradeThird.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkGradeThird.setText("Grade C");
        chkGradeThird.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkGradeThirdMouseClicked(evt);
            }
        });
        chkGradeThird.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGradeThirdActionPerformed(evt);
            }
        });
        CenterPanel1.add(chkGradeThird, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 135, -1, -1));

        chkGradeFirst.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkGradeFirst.setText("Grade A");
        chkGradeFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkGradeFirstMouseClicked(evt);
            }
        });
        chkGradeFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGradeFirstActionPerformed(evt);
            }
        });
        CenterPanel1.add(chkGradeFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 135, -1, -1));

        chkGradeSecond.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkGradeSecond.setText("Grade B");
        chkGradeSecond.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkGradeSecondMouseClicked(evt);
            }
        });
        chkGradeSecond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGradeSecondActionPerformed(evt);
            }
        });
        chkGradeSecond.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkGradeSecondKeyPressed(evt);
            }
        });
        CenterPanel1.add(chkGradeSecond, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 135, -1, -1));

        lblstatus1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblstatus1.setForeground(new java.awt.Color(51, 51, 51));
        lblstatus1.setText("Status :");
        CenterPanel1.add(lblstatus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 243, -1, -1));

        txtStock.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        txtStock.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtStock.setEnabled(false);
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockKeyPressed(evt);
            }
        });
        CenterPanel1.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 140, -1));

        jLabel19.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Stock (kg)");
        CenterPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 61, -1, -1));

        header3.setBackground(new java.awt.Color(0, 0, 104));
        header3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Product");

        javax.swing.GroupLayout header3Layout = new javax.swing.GroupLayout(header3);
        header3.setLayout(header3Layout);
        header3Layout.setHorizontalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(771, Short.MAX_VALUE))
        );
        header3Layout.setVerticalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        CenterPanel1.add(header3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 840, -1));

        lblbarcodepreview.setBorder(new javax.swing.border.MatteBorder(null));
        CenterPanel1.add(lblbarcodepreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 379, 220, 54));

        lblbarcode.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblbarcode.setForeground(new java.awt.Color(100, 100, 100));
        lblbarcode.setText("Barcode :");
        CenterPanel1.add(lblbarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 298, -1, -1));

        btngeneratebarcode.setBackground(new java.awt.Color(255, 102, 0));
        btngeneratebarcode.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btngeneratebarcode.setText("Generate Barcode");
        btngeneratebarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngeneratebarcodeActionPerformed(evt);
            }
        });
        CenterPanel1.add(btngeneratebarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(623, 379, 170, -1));

        jButton1.setBackground(new java.awt.Color(204, 102, 0));
        jButton1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        CenterPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 436, 175, 31));

        lblimagename2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CenterPanel1.add(lblimagename2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 230, 20));

        add(CenterPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 710));
    }// </editor-fold>//GEN-END:initComponents

    private void AddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddBtnMouseClicked
        String p = null;
        if (radioBtnOne.isSelected()) {
            p = radioBtnOne.getText();
        } else if (radioBtnTwo.isSelected()) {
            p = radioBtnTwo.getText();
        } else if (radioBtnThree.isSelected()) {
            p = radioBtnThree.getText();
        }

        String status = null;
        if (r_Active.isSelected()) {
            status = r_Active.getText();
        } else if (r_Inactive.isSelected()) {
            status = r_Inactive.getText();
        }
        String grade = null;
        if (chkGradeFirst.isSelected()) {
            grade = chkGradeFirst.getText();
        } else if (chkGradeSecond.isSelected()) {
            grade = chkGradeSecond.getText();
        } else if (chkGradeThird.isSelected()) {
            grade = chkGradeThird.getText();
        }

        if (txtProductID.getText().isEmpty()) {
            txtProductID.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "ProductID field is empty !!");
        } else if (txtProductName.getText().isEmpty()) {
            txtProductName.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "ProductName field is empty !!");
        } else if (txtMinimumQuantity.getText().isEmpty()) {
            txtMinimumQuantity.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "MinimumQuantity field is empty !!");
        
        } else if (grade == null) {
            chkGradeFirst.setForeground(new java.awt.Color(255, 0, 51));
            chkGradeSecond.setForeground(new java.awt.Color(255, 0, 51));
            chkGradeThird.setForeground(new java.awt.Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "please select grade!!");
        } else if (p == null) {
            radioBtnOne.setForeground(new java.awt.Color(255, 0, 51));
            radioBtnTwo.setForeground(new java.awt.Color(255, 0, 51));
            radioBtnThree.setForeground(new java.awt.Color(255, 0, 51));

            JOptionPane.showMessageDialog(this, "Please select Preservation  !!");
        } else if (status == null) {
            r_Active.setForeground(new java.awt.Color(255, 0, 51));
            r_Inactive.setForeground(new java.awt.Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Select the Status  !!");
        } else if (lblbarcodepreview.getIcon() == null) {
            btngeneratebarcode.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Genarate Barcode !!");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure to add this record!!", "Add Record", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {

                try {
                    inputstream = new FileInputStream(new File(imageFilePath));
                    barcodeinputstream = new FileInputStream(new File(barcodeFilePath));

                    try {

                        Con = DriverManager.getConnection(dburl, dbuname, dbpass);
                        PreparedStatement add = Con.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                        add.setString(1, txtProductID.getText());
                        add.setString(2, txtProductName.getText());
                        add.setString(3, txtProductCategory.getSelectedItem().toString());
                        add.setString(4, txtProductType.getSelectedItem().toString());

                        if (this.chkGradeFirst.isSelected() && this.chkGradeSecond.isSelected() && this.chkGradeThird.isSelected()) {
                            add.setString(5, "A,B,C");
                        } else if (this.chkGradeFirst.isSelected() && this.chkGradeSecond.isSelected()) {
                            add.setString(5, "A,B");
                        } else if (this.chkGradeFirst.isSelected() && this.chkGradeThird.isSelected()) {
                            add.setString(5, "A,C");
                        } else if (this.chkGradeThird.isSelected() && this.chkGradeSecond.isSelected()) {
                            add.setString(5, "B,C");
                        } else if (this.chkGradeFirst.isSelected()) {
                            add.setString(5, "A");
                        } else if (this.chkGradeSecond.isSelected()) {
                            add.setString(5, "B");
                        } else if (this.chkGradeThird.isSelected()) {
                            add.setString(5, "C");
                        }

                        if (this.radioBtnOne.isSelected()) {
                            add.setString(6, "TwoDays");
                        } else if (this.radioBtnTwo.isSelected()) {
                            add.setString(6, "FiveDays");
                        } else if (this.radioBtnThree.isSelected()) {
                            add.setString(6, "SevenDays");
                        }
                        add.setInt(7, NULL);
                        add.setInt(8, Integer.valueOf(txtMinimumQuantity.getText()));
if (this.r_Active.isSelected()) {
                            add.setString(9, "Available");
                        } else if (this.r_Inactive.isSelected()) {
                            add.setString(9, "Unavailable");
                        }
                        add.setBlob(10, inputstream);

                        
                        add.setBlob(11, barcodeinputstream);
                        add.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Product Successfully Added");
                        txtProductName.setText("");
                        txtMinimumQuantity.setText("");
                        txtStock.setText("");
                        chkGradeFirst.setSelected(false);
                        chkGradeSecond.setSelected(false);
                        chkGradeThird.setSelected(false);
                        radioBtnOne.setSelected(false);
                        radioBtnTwo.setSelected(false);
                        radioBtnThree.setSelected(false);
                        r_Active.setSelected(false);
                        r_Inactive.setSelected(false);
                        lbl_image.setIcon(null);
                        lblbarcodepreview.setIcon(null);

                        Con.close();
                        SelectProd();
                        setView();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_AddBtnMouseClicked

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        
    }//GEN-LAST:event_AddBtnActionPerformed

    private void DeleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteBtnMouseClicked
        if (txtProductID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Product ID is Empty. Please Enter a Product ID to be DELETED");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Delete !!", "Delete", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    Con = DriverManager.getConnection(dburl, dbuname, dbpass);
                    String ID = txtProductID.getText();
                    String Query = "DELETE FROM  product WHERE productID = '" + this.txtProductID.getText() + "'";
                    Statement Add = Con.createStatement();
                    Add.executeUpdate(Query);
                    SelectProd();
                    JOptionPane.showMessageDialog(this, "Product DELETED Successfully");
                    txtProductName.setText("");
                    txtMinimumQuantity.setText("");
                    txtStock.setText("");
                    chkGradeFirst.setSelected(false);
                    chkGradeSecond.setSelected(false);
                    chkGradeThird.setSelected(false);
                    radioBtnOne.setSelected(false);
                    radioBtnTwo.setSelected(false);
                    radioBtnThree.setSelected(false);
                    r_Active.setSelected(false);
                    r_Inactive.setSelected(false);
                    lbl_image.setIcon(null);
                    lblbarcodepreview.setIcon(null);
                    setView();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }//GEN-LAST:event_DeleteBtnMouseClicked

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed

    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void UpBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpBtnMouseClicked
        String p = null;
        if (radioBtnOne.isSelected()) {
            p = radioBtnOne.getText();
        } else if (radioBtnTwo.isSelected()) {
            p = radioBtnTwo.getText();
        } else if (radioBtnThree.isSelected()) {
            p = radioBtnThree.getText();
        }

        String status = null;
        if (r_Active.isSelected()) {
            status = r_Active.getText();
        } else if (r_Inactive.isSelected()) {
            status = r_Inactive.getText();
        }
        String grade = null;
        if (chkGradeFirst.isSelected()) {
            grade = chkGradeFirst.getText();
        } else if (chkGradeSecond.isSelected()) {
            grade = chkGradeSecond.getText();
        } else if (chkGradeThird.isSelected()) {
            grade = chkGradeThird.getText();
        }

        if (txtProductID.getText().isEmpty()) {
            txtProductID.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "ProductID field is empty !!");
        } else if (txtProductName.getText().isEmpty()) {
            txtProductName.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "ProductName field is empty !!");
        } else if (txtMinimumQuantity.getText().isEmpty()) {
            txtMinimumQuantity.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "MinimumQuantity field is empty !!");
        } else if (txtStock.getText().isEmpty()) {
            txtStock.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "txtStock field is empty !!");
        } else if (grade == null) {
            chkGradeFirst.setForeground(new java.awt.Color(255, 0, 51));
            chkGradeSecond.setForeground(new java.awt.Color(255, 0, 51));
            chkGradeThird.setForeground(new java.awt.Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "please select grade!!");
        } else if (p == null) {
            radioBtnOne.setForeground(new java.awt.Color(255, 0, 51));
            radioBtnTwo.setForeground(new java.awt.Color(255, 0, 51));
            radioBtnThree.setForeground(new java.awt.Color(255, 0, 51));

            JOptionPane.showMessageDialog(this, "Please select Preservation  !!");
        } else if (status == null) {
            r_Active.setForeground(new java.awt.Color(255, 0, 51));
            r_Inactive.setForeground(new java.awt.Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Select the Status  !!");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure to update this record!!", "update Record", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {

                if (txtProductID.getText().isEmpty()
                        || txtProductName.getText().isEmpty()
                        || txtMinimumQuantity.getText().isEmpty()
                        || txtStock.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Missing Information");
                } else {
                    try {
                        Con = DriverManager.getConnection(dburl, dbuname, dbpass);
                        PreparedStatement add = Con.prepareStatement("UPDATE product SET productID = ?, productName = ?,categoryID = ?, typeID = ?, productGrade = ?, preservation = ?, stock = ?, minimumStock = ?, status = ? WHERE productID = '" + this.txtProductID.getText() + "'");
                        add.setString(1, txtProductID.getText());
                        add.setString(2, txtProductName.getText());
                        add.setString(3, txtProductCategory.getSelectedItem().toString());
                        add.setString(4, txtProductType.getSelectedItem().toString());
                        
                        if (this.chkGradeFirst.isSelected() && this.chkGradeSecond.isSelected() && this.chkGradeThird.isSelected()) {
                            add.setString(5, "A,B,C");
                        } else if (this.chkGradeFirst.isSelected() && this.chkGradeSecond.isSelected()) {
                            add.setString(5, "A,B");
                        } else if (this.chkGradeFirst.isSelected() && this.chkGradeThird.isSelected()) {
                            add.setString(5, "A,C");
                        } else if (this.chkGradeThird.isSelected() && this.chkGradeSecond.isSelected()) {
                            add.setString(5, "B,C");
                        } else if (this.chkGradeFirst.isSelected()) {
                            add.setString(5, "A");
                        } else if (this.chkGradeSecond.isSelected()) {
                            add.setString(5, "B");
                        } else if (this.chkGradeThird.isSelected()) {
                            add.setString(5, "C");
                        }
                        
                        if (this.radioBtnOne.isSelected()) {
                            add.setString(6, "TwoDays");
                        } else if (this.radioBtnTwo.isSelected()) {
                            add.setString(6, "FiveDays");
                        } else if (this.radioBtnThree.isSelected()) {
                            add.setString(6, "SevenDays");
                        }
                        add.setDouble(7, Double.valueOf(txtStock.getText()));
                        add.setDouble(8, Double.valueOf(txtMinimumQuantity.getText()));

                        if (this.r_Active.isSelected()) {
                            add.setString(9, "Available");
                        } else if (this.r_Inactive.isSelected()) {
                            add.setString(9, "Unavailable");
                        }
                    //    add.setBlob(11, barcodeinputstream);
                        add.executeUpdate();
                        
                        JOptionPane.showMessageDialog(this, "Product Updated Successfully");
                        txtProductName.setText("");
                        txtMinimumQuantity.setText("");
                        txtStock.setText("");
                        chkGradeFirst.setSelected(false);
                        chkGradeSecond.setSelected(false);
                        chkGradeThird.setSelected(false);
                        radioBtnOne.setSelected(false);
                        radioBtnTwo.setSelected(false);
                        radioBtnThree.setSelected(false);
                        r_Active.setSelected(false);
                        r_Inactive.setSelected(false);
                        lbl_image.setIcon(null);
                        lblbarcodepreview.setIcon(null);
                        SelectProd();
                        setView();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }//GEN-LAST:event_UpBtnMouseClicked

    private void UpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpBtnActionPerformed
        
    }//GEN-LAST:event_UpBtnActionPerformed

    private void txtProductIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductIDActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    private void txtMinimumQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinimumQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinimumQuantityActionPerformed

    private void txtProductCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductCategoryActionPerformed

    private void ProductTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductTableMouseClicked
        setView();
     
        DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();
        int Myindex = ProductTable.getSelectedRow();
        txtProductID.setText(model.getValueAt(Myindex, 0).toString());
        txtProductName.setText(model.getValueAt(Myindex, 1).toString());
        txtProductCategory.setSelectedItem(model.getValueAt(Myindex, 2));
        txtProductType.setSelectedItem(model.getValueAt(Myindex, 3));
        txtMinimumQuantity.setText(model.getValueAt(Myindex, 7).toString());
        txtStock.setText(model.getValueAt(Myindex, 6).toString());
        
        try {
            String query = "select photo,Barcode,productGrade,preservation,status from product where productID='" + txtProductID.getText() + "' ";
            PreparedStatement pst = Con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                byte[] img = rs.getBytes("photo");
                ImageIcon proimage = new ImageIcon(img);
                Image im = proimage.getImage();
                Image myImg = im.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                lbl_image.setIcon(newImage);

                byte[] brimg = rs.getBytes("Barcode");
                ImageIcon brimage = new ImageIcon(brimg);
                Image brim = brimage.getImage();
                Image brmyImg = brim.getScaledInstance(lblbarcodepreview.getWidth(), lblbarcodepreview.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon brImage = new ImageIcon(brmyImg);
                lblbarcodepreview.setIcon(brImage);
                
                if (rs.getString("productGrade").contains("A")) {
                    this.chkGradeFirst.setSelected(true);
                }

                if (rs.getString("productGrade").contains("B")) {
                    this.chkGradeSecond.setSelected(true);
                }

                if (rs.getString("productGrade").contains("C")) {
                    this.chkGradeThird.setSelected(true);
                }
                if(rs.getString("preservation").contains("TwoDays")){
                    this.radioBtnOne.setSelected(true);
                }
                if(rs.getString("preservation").contains("FiveDays")){
                    this.radioBtnTwo.setSelected(true);
                }
                if(rs.getString("preservation").contains("SevenDays")){
                    this.radioBtnThree.setSelected(true);
                }
                if(rs.getString("status").contains("Available")){
                    this.r_Active.setSelected(true);
                }
                if(rs.getString("status").contains("Unavailable")){
                    this.r_Inactive.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_ProductTableMouseClicked

    private void AddImageBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddImageBtnMouseClicked
         String currentDirectoryPath = "C:/srbio/images/product";
        JFileChooser imageFileChooser = new JFileChooser(currentDirectoryPath);
        int imageChooser = imageFileChooser.showOpenDialog(null);
        imageFileChooser.setDialogTitle("Choose Image");

        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg");
        imageFileChooser.setFileFilter(fnef);

        if (imageChooser == JFileChooser.APPROVE_OPTION) {
            File imageFile = imageFileChooser.getSelectedFile();
            imageFilePath = imageFile.getAbsolutePath();
            imageFileName = imageFile.getName();

            ImageIcon imageIcon = new ImageIcon(imageFilePath);

            Image image = imageIcon.getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon resizedImageIcon = new ImageIcon(image);

            lbl_image.setIcon(resizedImageIcon);
        }
        try {
            File file = new File(imageFilePath);
            inputstream = new FileInputStream(file);
            byte[] image = new byte[(int) file.length()];
            inputstream.read(image);

            String sql = "UPDATE product SET photo=? where productID=?";
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);

            pst.setBytes(1, image);
            pst.setString(2, txtProductID.getText());
            pst.executeUpdate();
            pst.close();
            System.out.println("uploaded");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
      //  UpBtn.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_AddImageBtnMouseClicked

    private void AddImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddImageBtnActionPerformed
      /*  String currentDirectoryPath = "C:/srbio/images/product";
        JFileChooser imageFileChooser = new JFileChooser(currentDirectoryPath);
        int imageChooser = imageFileChooser.showOpenDialog(null);
        imageFileChooser.setDialogTitle("Choose Image");

        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg");
        imageFileChooser.setFileFilter(fnef);

        if (imageChooser == JFileChooser.APPROVE_OPTION) {
            File imageFile = imageFileChooser.getSelectedFile();
            imageFilePath = imageFile.getAbsolutePath();
            imageFileName = imageFile.getName();

            ImageIcon imageIcon = new ImageIcon(imageFilePath);

            Image image = imageIcon.getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon resizedImageIcon = new ImageIcon(image);

            lbl_image.setIcon(resizedImageIcon);
        }*/
    }//GEN-LAST:event_AddImageBtnActionPerformed

    private void txtProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductTypeActionPerformed

    private void radioBtnOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnOneActionPerformed
        // Gender Male:
    }//GEN-LAST:event_radioBtnOneActionPerformed

    private void radioBtnThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnThreeActionPerformed
        // Gender Female:
    }//GEN-LAST:event_radioBtnThreeActionPerformed

    private void r_ActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_ActiveActionPerformed
        // status active
    }//GEN-LAST:event_r_ActiveActionPerformed

    private void r_InactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_InactiveActionPerformed
        // status Inactive:
    }//GEN-LAST:event_r_InactiveActionPerformed

    private void radioBtnTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnTwoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBtnTwoActionPerformed

    private void chkGradeThirdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGradeThirdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkGradeThirdActionPerformed

    private void chkGradeFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGradeFirstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkGradeFirstActionPerformed

    private void chkGradeSecondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGradeSecondActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkGradeSecondActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void txtProductIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductIDKeyPressed
        txtProductID.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtProductIDKeyPressed

    private void txtProductNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductNameKeyPressed
        txtProductName.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtProductNameKeyPressed

    private void txtMinimumQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimumQuantityKeyPressed
        txtMinimumQuantity.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtMinimumQuantityKeyPressed

    private void txtStockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyPressed
        txtStock.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtStockKeyPressed

    private void chkGradeFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkGradeFirstMouseClicked
        chkGradeFirst.setForeground(new Color(0, 0, 0));
        chkGradeSecond.setForeground(new Color(0, 0, 0));
        chkGradeThird.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_chkGradeFirstMouseClicked

    private void chkGradeSecondKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkGradeSecondKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkGradeSecondKeyPressed

    private void chkGradeSecondMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkGradeSecondMouseClicked
        chkGradeSecond.setForeground(new Color(0, 0, 0));
        chkGradeFirst.setForeground(new Color(0, 0, 0));
        chkGradeThird.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_chkGradeSecondMouseClicked

    private void chkGradeThirdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkGradeThirdMouseClicked
        chkGradeThird.setForeground(new Color(0, 0, 0));
        chkGradeFirst.setForeground(new Color(0, 0, 0));
        chkGradeSecond.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_chkGradeThirdMouseClicked

    private void radioBtnOneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioBtnOneMouseClicked
        radioBtnOne.setForeground(new Color(0, 0, 0));
        radioBtnTwo.setForeground(new Color(0, 0, 0));
        radioBtnThree.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_radioBtnOneMouseClicked

    private void radioBtnTwoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioBtnTwoMouseClicked
        radioBtnTwo.setForeground(new Color(0, 0, 0));
        radioBtnOne.setBackground(new Color(0, 0, 0));
        radioBtnThree.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_radioBtnTwoMouseClicked

    private void radioBtnThreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioBtnThreeMouseClicked
        radioBtnThree.setBackground(new Color(0, 0, 0));
        radioBtnOne.setForeground(new Color(0, 0, 0));
        radioBtnTwo.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_radioBtnThreeMouseClicked

    private void r_ActiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_ActiveMouseClicked
        r_Active.setForeground(new Color(0, 0, 0));
        r_Inactive.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_r_ActiveMouseClicked

    private void r_InactiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_InactiveMouseClicked
        r_Inactive.setForeground(new Color(0, 0, 0));
        r_Active.setForeground(new Color(0, 0, 0));

    }//GEN-LAST:event_r_InactiveMouseClicked

    private void btngeneratebarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngeneratebarcodeActionPerformed
        // Generate Barcode:
        this.barcode();
    }//GEN-LAST:event_btngeneratebarcodeActionPerformed

    private void barcode(){
        // Generate Barcode:
        try {

            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(txtProductID.getText());
            barcode.setI(11.0f);

            String fname = txtProductID.getText();

            barcode.renderBarcode("C:\\srbio\\barcode\\product/" + fname + ".png");
            ImageIcon imageIcon = new ImageIcon("C:\\srbio\\barcode\\product/" + fname + ".png");
            barcodeFilePath = ("C:\\srbio\\barcode\\product/" + fname + ".png");
            Image image = imageIcon.getImage().getScaledInstance(lblbarcodepreview.getWidth(), lblbarcodepreview.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon resizedImageIcon = new ImageIcon(image);
            lblbarcodepreview.setIcon(resizedImageIcon);

        } catch (Exception e) {

        }
        btngeneratebarcode.setBackground(new Color(255, 255, 255));
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setView();
        txtProductName.setText("");
        txtMinimumQuantity.setText("");
        txtStock.setText("");
        chkGradeFirst.setSelected(false);
        chkGradeSecond.setSelected(false);
        chkGradeThird.setSelected(false);
        radioBtnOne.setSelected(false);
        radioBtnTwo.setSelected(false);
        radioBtnThree.setSelected(false);
        r_Active.setSelected(false);
        r_Inactive.setSelected(false);
        lbl_image.setIcon(null);
        lblbarcodepreview.setIcon(null);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JButton AddImageBtn;
    private javax.swing.JPanel CenterPanel1;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JTable ProductTable;
    private javax.swing.JButton UpBtn;
    private javax.swing.JButton btngeneratebarcode;
    private javax.swing.JCheckBox chkGradeFirst;
    private javax.swing.JCheckBox chkGradeSecond;
    private javax.swing.JCheckBox chkGradeThird;
    private javax.swing.JPanel header3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lblbarcode;
    private javax.swing.JLabel lblbarcodepreview;
    private javax.swing.JLabel lblgender;
    private javax.swing.JLabel lblimagename2;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JLabel lblstatus1;
    private javax.swing.JRadioButton r_Active;
    private javax.swing.JRadioButton r_Inactive;
    private javax.swing.JRadioButton radioBtnOne;
    private javax.swing.JRadioButton radioBtnThree;
    private javax.swing.JRadioButton radioBtnTwo;
    private javax.swing.JTextField txtMinimumQuantity;
    private javax.swing.JComboBox<String> txtProductCategory;
    private javax.swing.JTextField txtProductID;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JComboBox<String> txtProductType;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
