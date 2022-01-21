/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.employee1;

import com.barcodelib.barcode.Linear;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.management.srb.util.db;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Avishka
 */
public class Employee extends javax.swing.JPanel {

    db d = new db();
    Connection conn = d.mycon();

    Connection con = null;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st = null;

    private final Dimension ds = new Dimension(199, 129);
    private final Dimension cs = WebcamResolution.VGA.getSize();
    private final Webcam wCam = Webcam.getDefault();
    private final WebcamPanel wCamPanel = new WebcamPanel(wCam, ds, false);

    /**
     * Creates new form Employee
     */
    String status;
    String imageFilePath;
    String imageFileName;
    InputStream inputstream;
    InputStream upinputstream;
    InputStream barcodeinputstream;
    String barcodeFilePath;
    String captureFilePath;
    String gender;
    String upimageFilePath;
    String upimageFileName;
    String x;
    String casual;
    String annual;
    String medical;
    String year;
    String emp;
    String NIC;
    int days;
    int casual1;
    int annual1;
    int medical1;
    String leave;

    public Employee() {
        initComponents();
        empid();
        tablelord();
        p1_AddPane.setVisible(false);
        p1_DeletePane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(false);
        p1.setVisible(true);

    }

    public void empid() {
        try {

            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            String sql = "SELECT MAX(EmpNo) FROM employee";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                x = rs.getString("MAX(EmpNo)");

                if (x == (null)) {
                    txtempno.setText("E0001");
                } else {
                    long id = Long.parseLong(rs.getString("MAX(EmpNo)").substring(1, rs.getString("MAX(EmpNo)").length()));
                    id++;
                    txtempno.setText("E" + String.format("%04d", id));
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void tablelord() {
        try {
            String sql = "SELECT `EmpNo`, `NIC_No`, `First_Name`, `Last_Name`, `DOB`, `Address1`, `Address2`, `Contact_No`, `Email`, `Department`, `Designation`, `Educational_Level`, `Gender`, `Status`,Basic_Salary FROM `employee`";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            EMPtable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        EMPtable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        EMPtable.getTableHeader().setOpaque(false);
        EMPtable.getTableHeader().setBackground(new Color(32, 136, 203));
        EMPtable.getTableHeader().setForeground(new Color(0, 0, 0));
        EMPtable.setRowHeight(25);
        EMPtable.getColumnModel().getColumn(1).setPreferredWidth(130);
        EMPtable.getColumnModel().getColumn(2).setPreferredWidth(120);
        EMPtable.getColumnModel().getColumn(3).setPreferredWidth(120);
        EMPtable.getColumnModel().getColumn(4).setPreferredWidth(120);
        EMPtable.getColumnModel().getColumn(5).setPreferredWidth(160);
        EMPtable.getColumnModel().getColumn(6).setPreferredWidth(160);
        EMPtable.getColumnModel().getColumn(7).setPreferredWidth(100);
        EMPtable.getColumnModel().getColumn(8).setPreferredWidth(170);
        EMPtable.getColumnModel().getColumn(9).setPreferredWidth(120);
        EMPtable.getColumnModel().getColumn(10).setPreferredWidth(150);
        EMPtable.getColumnModel().getColumn(11).setPreferredWidth(150);
        EMPtable.getColumnModel().getColumn(12).setPreferredWidth(70);
        EMPtable.getColumnModel().getColumn(13).setPreferredWidth(70);
        EMPtable.getColumnModel().getColumn(14).setPreferredWidth(120);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        addEmployee = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        searchEmployee = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        deleteEmployee = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        reportEmployee = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        updateEmployee = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        p1_AddPane = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        txtLname = new javax.swing.JTextField();
        lblemono = new javax.swing.JLabel();
        btnGoBack = new javax.swing.JButton();
        txtDOB = new javax.swing.JTextField();
        lblimagename = new javax.swing.JLabel();
        lblfname = new javax.swing.JLabel();
        txtempno = new javax.swing.JTextField();
        btnUpload = new javax.swing.JButton();
        lbllname = new javax.swing.JLabel();
        lblbarcodepreview = new javax.swing.JLabel();
        lblcapturephoto = new javax.swing.JLabel();
        lblcaptureimage = new javax.swing.JLabel();
        lblimage = new javax.swing.JLabel();
        lbldesignation = new javax.swing.JLabel();
        btngeneratebarcode = new javax.swing.JButton();
        txtDepartment = new javax.swing.JComboBox<>();
        lblbarcode = new javax.swing.JLabel();
        lbleducation = new javax.swing.JLabel();
        lblgender = new javax.swing.JLabel();
        lblstatus = new javax.swing.JLabel();
        txtDesignation = new javax.swing.JComboBox<>();
        txtNIC = new javax.swing.JTextField();
        lblNic = new javax.swing.JLabel();
        txtContactNo = new javax.swing.JTextField();
        lblContactno = new javax.swing.JLabel();
        txtAdress = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtFname = new javax.swing.JTextField();
        lblAdress = new javax.swing.JLabel();
        btnAddEmp = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        lblDepartment = new javax.swing.JLabel();
        btncamON = new javax.swing.JButton();
        btncamOFF = new javax.swing.JButton();
        btncapture = new javax.swing.JButton();
        txtAdressline = new javax.swing.JTextField();
        lblAdressline = new javax.swing.JLabel();
        lblpropic = new javax.swing.JLabel();
        txteducationallevel = new javax.swing.JComboBox<>();
        r_male = new javax.swing.JRadioButton();
        r_female = new javax.swing.JRadioButton();
        r_Active = new javax.swing.JRadioButton();
        r_Inactive = new javax.swing.JRadioButton();
        btnreset = new javax.swing.JButton();
        txtBasicSalary = new javax.swing.JTextField();
        lblBasicSalary = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        p1_DeletePane = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        lblDOB3 = new javax.swing.JLabel();
        txtLname3 = new javax.swing.JTextField();
        lblemono3 = new javax.swing.JLabel();
        btnGoBack3 = new javax.swing.JButton();
        txtDOB3 = new javax.swing.JTextField();
        lblfname3 = new javax.swing.JLabel();
        txtempno3 = new javax.swing.JTextField();
        lbllname3 = new javax.swing.JLabel();
        lblbarcodepreview3 = new javax.swing.JLabel();
        lblimage3 = new javax.swing.JLabel();
        lbldesignation3 = new javax.swing.JLabel();
        lblbarcode3 = new javax.swing.JLabel();
        lbleducation3 = new javax.swing.JLabel();
        lblgender3 = new javax.swing.JLabel();
        lblstatus3 = new javax.swing.JLabel();
        txtNIC3 = new javax.swing.JTextField();
        lblNic3 = new javax.swing.JLabel();
        txtContactNo3 = new javax.swing.JTextField();
        lblContactno3 = new javax.swing.JLabel();
        txtAdress3 = new javax.swing.JTextField();
        lblEmail3 = new javax.swing.JLabel();
        txtFname3 = new javax.swing.JTextField();
        lblAdress3 = new javax.swing.JLabel();
        btnAddEmp3 = new javax.swing.JButton();
        txtEmail3 = new javax.swing.JTextField();
        lblDepartment3 = new javax.swing.JLabel();
        txtAdressline3 = new javax.swing.JTextField();
        lblAdressline3 = new javax.swing.JLabel();
        txtdepartment3 = new javax.swing.JTextField();
        txteducationallevel3 = new javax.swing.JTextField();
        txtdesignation3 = new javax.swing.JTextField();
        txtgender3 = new javax.swing.JTextField();
        txtstatus3 = new javax.swing.JTextField();
        lblpropic3 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        p1_UpdatePane = new javax.swing.JPanel();
        header3 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        lblDOB2 = new javax.swing.JLabel();
        txtLname2 = new javax.swing.JTextField();
        lblemono2 = new javax.swing.JLabel();
        btnGoBack2 = new javax.swing.JButton();
        txtDOB2 = new javax.swing.JTextField();
        lblimagename2 = new javax.swing.JLabel();
        lblfname2 = new javax.swing.JLabel();
        txtempno2 = new javax.swing.JTextField();
        btnUpload2 = new javax.swing.JButton();
        lbllname2 = new javax.swing.JLabel();
        lblbarcodepreview2 = new javax.swing.JLabel();
        lblcapturephoto2 = new javax.swing.JLabel();
        lblcaptureimage2 = new javax.swing.JLabel();
        lblimage2 = new javax.swing.JLabel();
        lbldesignation2 = new javax.swing.JLabel();
        txtDepartment2 = new javax.swing.JComboBox<>();
        lblbarcode2 = new javax.swing.JLabel();
        lbleducation2 = new javax.swing.JLabel();
        lblgender2 = new javax.swing.JLabel();
        lblstatus2 = new javax.swing.JLabel();
        txtDesignation2 = new javax.swing.JComboBox<>();
        txtNIC2 = new javax.swing.JTextField();
        lblNic2 = new javax.swing.JLabel();
        txtContactNo2 = new javax.swing.JTextField();
        lblContactno2 = new javax.swing.JLabel();
        txtAdress2 = new javax.swing.JTextField();
        lblEmail2 = new javax.swing.JLabel();
        txtFname2 = new javax.swing.JTextField();
        lblAdress2 = new javax.swing.JLabel();
        btnAddEmp2 = new javax.swing.JButton();
        txtEmail2 = new javax.swing.JTextField();
        lblDepartment2 = new javax.swing.JLabel();
        btncamON2 = new javax.swing.JButton();
        btncamOFF2 = new javax.swing.JButton();
        btncapture2 = new javax.swing.JButton();
        txtAdressline2 = new javax.swing.JTextField();
        lblAdressline2 = new javax.swing.JLabel();
        lblpropic2 = new javax.swing.JLabel();
        txteducationallevel2 = new javax.swing.JComboBox<>();
        r_male2 = new javax.swing.JRadioButton();
        r_female2 = new javax.swing.JRadioButton();
        r_Active2 = new javax.swing.JRadioButton();
        r_Inactive2 = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        btnreset2 = new javax.swing.JButton();
        p1_EMP_ReportPane = new javax.swing.JPanel();
        header2 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        EMPtable = new javax.swing.JTable();
        btnGoBack4 = new javax.swing.JButton();
        btnPrintempdetails = new javax.swing.JButton();
        p1_SearchPane = new javax.swing.JPanel();
        btnGoBack5 = new javax.swing.JButton();
        lblimagename3 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        searchEmp = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        header4 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();

        p1.setBackground(new java.awt.Color(180, 170, 255));
        p1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p1.setForeground(new java.awt.Color(51, 51, 51));
        p1.setPreferredSize(new java.awt.Dimension(810, 712));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Welcome to EMP System");

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Employee Section");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/information.png"))); // NOI18N
        jLabel15.setToolTipText("");

        addEmployee.setBackground(new java.awt.Color(255, 255, 255));
        addEmployee.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        addEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEmployeeMouseClicked(evt);
            }
        });
        addEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/add_1.png"))); // NOI18N
        addEmployee.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 204, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Add Employee");
        addEmployee.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 130, -1, 20));

        searchEmployee.setBackground(new java.awt.Color(255, 255, 255));
        searchEmployee.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        searchEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchEmployeeMouseClicked(evt);
            }
        });
        searchEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/search.png"))); // NOI18N
        searchEmployee.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Search Employee");
        searchEmployee.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 20));

        deleteEmployee.setBackground(new java.awt.Color(255, 255, 255));
        deleteEmployee.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        deleteEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteEmployeeMouseClicked(evt);
            }
        });
        deleteEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/delete-male-user.png"))); // NOI18N
        deleteEmployee.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Delete Employee");
        deleteEmployee.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 130, -1, 20));

        reportEmployee.setBackground(new java.awt.Color(255, 255, 255));
        reportEmployee.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        reportEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportEmployeeMouseClicked(evt);
            }
        });
        reportEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/report.png"))); // NOI18N
        reportEmployee.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 100));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 153, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Employee Report");
        reportEmployee.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 130, -1, 20));

        updateEmployee.setBackground(new java.awt.Color(255, 255, 255));
        updateEmployee.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        updateEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateEmployeeMouseClicked(evt);
            }
        });
        updateEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/update.png"))); // NOI18N
        updateEmployee.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 0, 204));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Update Employee");
        updateEmployee.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 130, -1, 20));

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(p1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15))
                            .addGroup(p1Layout.createSequentialGroup()
                                .addComponent(addEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(deleteEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(updateEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(searchEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(reportEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        p1_AddPane.setBackground(new java.awt.Color(200, 210, 255));
        p1_AddPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 204, 204)));
        p1_AddPane.setToolTipText("");
        p1_AddPane.setPreferredSize(new java.awt.Dimension(810, 712));
        p1_AddPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 0, 104));
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Add Employee :");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p1_AddPane.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, -1));

        lblDOB.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDOB.setForeground(new java.awt.Color(51, 51, 51));
        lblDOB.setText("Date of Birth :");
        p1_AddPane.add(lblDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        txtLname.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtLname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtLname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLnameActionPerformed(evt);
            }
        });
        txtLname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLnameKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtLname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 240, 30));

        lblemono.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblemono.setForeground(new java.awt.Color(51, 51, 51));
        lblemono.setText("Emp No :");
        p1_AddPane.add(lblemono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

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
        p1_AddPane.add(btnGoBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 170, 30));

        txtDOB.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtDOB.setForeground(new java.awt.Color(153, 153, 153));
        txtDOB.setText("DD/MM/YYYY");
        txtDOB.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtDOB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDOBFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDOBFocusLost(evt);
            }
        });
        txtDOB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDOBMouseClicked(evt);
            }
        });
        txtDOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDOBActionPerformed(evt);
            }
        });
        txtDOB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDOBKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 240, 30));

        lblimagename.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1_AddPane.add(lblimagename, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 470, 250, 20));

        lblfname.setBackground(new java.awt.Color(51, 0, 51));
        lblfname.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblfname.setForeground(new java.awt.Color(51, 51, 51));
        lblfname.setText("First Name :");
        p1_AddPane.add(lblfname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtempno.setBackground(new java.awt.Color(235, 235, 235));
        txtempno.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtempno.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtempno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtempnoActionPerformed(evt);
            }
        });
        txtempno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtempnoKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtempno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 240, 30));

        btnUpload.setBackground(new java.awt.Color(0, 153, 204));
        btnUpload.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });
        p1_AddPane.add(btnUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 110, -1));

        lbllname.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbllname.setForeground(new java.awt.Color(51, 51, 51));
        lbllname.setText("Last Name :");
        p1_AddPane.add(lbllname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        lblbarcodepreview.setBorder(new javax.swing.border.MatteBorder(null));
        p1_AddPane.add(lblbarcodepreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 540, 220, 54));

        lblcapturephoto.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblcapturephoto.setForeground(new java.awt.Color(51, 51, 51));
        lblcapturephoto.setText("Capture Photo : ");
        p1_AddPane.add(lblcapturephoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, -1, -1));

        lblcaptureimage.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        p1_AddPane.add(lblcaptureimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 210, 140));

        lblimage.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        p1_AddPane.add(lblimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 210, 140));

        lbldesignation.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbldesignation.setForeground(new java.awt.Color(51, 51, 51));
        lbldesignation.setText("Designation :");
        p1_AddPane.add(lbldesignation, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        btngeneratebarcode.setBackground(new java.awt.Color(255, 102, 0));
        btngeneratebarcode.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btngeneratebarcode.setText("Generate Barcode");
        btngeneratebarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngeneratebarcodeActionPerformed(evt);
            }
        });
        p1_AddPane.add(btngeneratebarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 600, 160, -1));

        txtDepartment.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        txtDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Department", "Accounts", "Manufacturing", "Packing", "Sales" }));
        txtDepartment.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtDepartmentItemStateChanged(evt);
            }
        });
        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });
        txtDepartment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDepartmentKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 240, 30));

        lblbarcode.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblbarcode.setForeground(new java.awt.Color(51, 51, 51));
        lblbarcode.setText("Barcode :");
        p1_AddPane.add(lblbarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 520, -1, -1));

        lbleducation.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbleducation.setForeground(new java.awt.Color(51, 51, 51));
        lbleducation.setText("Educational Level :");
        p1_AddPane.add(lbleducation, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, -1));

        lblgender.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblgender.setForeground(new java.awt.Color(51, 51, 51));
        lblgender.setText("Gender :");
        p1_AddPane.add(lblgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, -1, -1));

        lblstatus.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblstatus.setForeground(new java.awt.Color(51, 51, 51));
        lblstatus.setText("Status :");
        p1_AddPane.add(lblstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, -1));

        txtDesignation.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        txtDesignation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Designation" }));
        txtDesignation.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtDesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDesignationActionPerformed(evt);
            }
        });
        p1_AddPane.add(txtDesignation, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 241, 30));

        txtNIC.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtNIC.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNICActionPerformed(evt);
            }
        });
        txtNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNICKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNICKeyReleased(evt);
            }
        });
        p1_AddPane.add(txtNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

        lblNic.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblNic.setForeground(new java.awt.Color(51, 51, 51));
        lblNic.setText("NIC No :");
        p1_AddPane.add(lblNic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        txtContactNo.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtContactNo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtContactNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtContactNoMouseClicked(evt);
            }
        });
        txtContactNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactNoActionPerformed(evt);
            }
        });
        txtContactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactNoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContactNoKeyReleased(evt);
            }
        });
        p1_AddPane.add(txtContactNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 240, 30));

        lblContactno.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblContactno.setForeground(new java.awt.Color(51, 51, 51));
        lblContactno.setText("Contact No :");
        p1_AddPane.add(lblContactno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        txtAdress.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtAdress.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdressActionPerformed(evt);
            }
        });
        txtAdress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdressKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtAdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 240, 30));

        lblEmail.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email :");
        p1_AddPane.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));

        txtFname.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtFname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFnameActionPerformed(evt);
            }
        });
        txtFname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFnameKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtFname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 240, 30));

        lblAdress.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblAdress.setForeground(new java.awt.Color(51, 51, 51));
        lblAdress.setText("Adress 1 :");
        p1_AddPane.add(lblAdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        btnAddEmp.setBackground(new java.awt.Color(0, 0, 102));
        btnAddEmp.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnAddEmp.setForeground(new java.awt.Color(255, 255, 255));
        btnAddEmp.setText("Add Employee");
        btnAddEmp.setBorderPainted(false);
        btnAddEmp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmpActionPerformed(evt);
            }
        });
        p1_AddPane.add(btnAddEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 660, 170, 30));

        txtEmail.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 240, 30));

        lblDepartment.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDepartment.setForeground(new java.awt.Color(51, 51, 51));
        lblDepartment.setText("Department :");
        p1_AddPane.add(lblDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        btncamON.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btncamON.setText("Camera ON");
        btncamON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncamONActionPerformed(evt);
            }
        });
        p1_AddPane.add(btncamON, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 100, -1));

        btncamOFF.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btncamOFF.setText("Camera OFF");
        btncamOFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncamOFFActionPerformed(evt);
            }
        });
        p1_AddPane.add(btncamOFF, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 100, -1));

        btncapture.setBackground(new java.awt.Color(51, 255, 51));
        btncapture.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btncapture.setText("Capture");
        btncapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaptureActionPerformed(evt);
            }
        });
        p1_AddPane.add(btncapture, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, -1));

        txtAdressline.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtAdressline.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtAdressline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdresslineActionPerformed(evt);
            }
        });
        txtAdressline.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdresslineKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtAdressline, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 240, 30));

        lblAdressline.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblAdressline.setForeground(new java.awt.Color(51, 51, 51));
        lblAdressline.setText("Adress 2 :");
        p1_AddPane.add(lblAdressline, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        lblpropic.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblpropic.setForeground(new java.awt.Color(51, 51, 51));
        lblpropic.setText("Profile Picture :");
        p1_AddPane.add(lblpropic, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, -1, -1));

        txteducationallevel.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        txteducationallevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Educational Level", "Upto G.C.E O/Level", "Passed G.C.E O/Level", "Passed G.C.E A/Level", "Graduate" }));
        txteducationallevel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txteducationallevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txteducationallevelActionPerformed(evt);
            }
        });
        p1_AddPane.add(txteducationallevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 240, 30));

        r_male.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        r_male.setText("Male");
        r_male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_maleActionPerformed(evt);
            }
        });
        p1_AddPane.add(r_male, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, -1, -1));

        r_female.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        r_female.setText("Female");
        r_female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_femaleActionPerformed(evt);
            }
        });
        p1_AddPane.add(r_female, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, -1, -1));

        r_Active.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        r_Active.setText("Active");
        r_Active.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_ActiveActionPerformed(evt);
            }
        });
        p1_AddPane.add(r_Active, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 100, -1));

        r_Inactive.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        r_Inactive.setText("In-Active");
        r_Inactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_InactiveActionPerformed(evt);
            }
        });
        p1_AddPane.add(r_Inactive, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, 100, -1));

        btnreset.setBackground(new java.awt.Color(204, 102, 0));
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
        p1_AddPane.add(btnreset, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 660, 170, 30));

        txtBasicSalary.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtBasicSalary.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtBasicSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBasicSalaryActionPerformed(evt);
            }
        });
        txtBasicSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBasicSalaryKeyPressed(evt);
            }
        });
        p1_AddPane.add(txtBasicSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 240, 30));

        lblBasicSalary.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblBasicSalary.setForeground(new java.awt.Color(51, 51, 51));
        lblBasicSalary.setText("Basic Salary :");
        p1_AddPane.add(lblBasicSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, -1, -1));

        jLabel36.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        p1_AddPane.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 541, 600));

        jLabel35.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        p1_AddPane.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 270, 600));

        p1_DeletePane.setBackground(new java.awt.Color(200, 210, 255));
        p1_DeletePane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 204, 204)));
        p1_DeletePane.setToolTipText("");
        p1_DeletePane.setPreferredSize(new java.awt.Dimension(810, 712));

        header1.setBackground(new java.awt.Color(0, 0, 104));
        header1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Delete Employee :");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblDOB3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDOB3.setForeground(new java.awt.Color(51, 51, 51));
        lblDOB3.setText("Date of Birth :");

        txtLname3.setEditable(false);
        txtLname3.setBackground(new java.awt.Color(255, 255, 255));
        txtLname3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtLname3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtLname3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLname3ActionPerformed(evt);
            }
        });

        lblemono3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblemono3.setForeground(new java.awt.Color(51, 51, 51));
        lblemono3.setText("Emp No :");

        btnGoBack3.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack3.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack3.setText("Go Back");
        btnGoBack3.setBorderPainted(false);
        btnGoBack3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGoBack3MouseClicked(evt);
            }
        });
        btnGoBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack3ActionPerformed(evt);
            }
        });
        btnGoBack3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGoBack3KeyPressed(evt);
            }
        });

        txtDOB3.setEditable(false);
        txtDOB3.setBackground(new java.awt.Color(255, 255, 255));
        txtDOB3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtDOB3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtDOB3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDOB3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDOB3FocusLost(evt);
            }
        });
        txtDOB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDOB3ActionPerformed(evt);
            }
        });

        lblfname3.setBackground(new java.awt.Color(51, 0, 51));
        lblfname3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblfname3.setForeground(new java.awt.Color(51, 51, 51));
        lblfname3.setText("First Name :");

        txtempno3.setBackground(new java.awt.Color(235, 235, 235));
        txtempno3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtempno3.setForeground(new java.awt.Color(153, 153, 153));
        txtempno3.setText("              Enter Employee ID to Search");
        txtempno3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtempno3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtempno3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtempno3FocusLost(evt);
            }
        });
        txtempno3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtempno3ActionPerformed(evt);
            }
        });
        txtempno3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtempno3KeyPressed(evt);
            }
        });

        lbllname3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbllname3.setForeground(new java.awt.Color(51, 51, 51));
        lbllname3.setText("Last Name :");

        lblbarcodepreview3.setBorder(new javax.swing.border.MatteBorder(null));

        lblimage3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lbldesignation3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbldesignation3.setForeground(new java.awt.Color(51, 51, 51));
        lbldesignation3.setText("Designation :");

        lblbarcode3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblbarcode3.setForeground(new java.awt.Color(51, 51, 51));
        lblbarcode3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblbarcode3.setText("Barcode :");

        lbleducation3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbleducation3.setForeground(new java.awt.Color(51, 51, 51));
        lbleducation3.setText("Educational Level :");

        lblgender3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblgender3.setForeground(new java.awt.Color(51, 51, 51));
        lblgender3.setText("Gender :");

        lblstatus3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblstatus3.setForeground(new java.awt.Color(51, 51, 51));
        lblstatus3.setText("Status :");

        txtNIC3.setEditable(false);
        txtNIC3.setBackground(new java.awt.Color(255, 255, 255));
        txtNIC3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtNIC3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtNIC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIC3ActionPerformed(evt);
            }
        });

        lblNic3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblNic3.setForeground(new java.awt.Color(51, 51, 51));
        lblNic3.setText("NIC No :");

        txtContactNo3.setEditable(false);
        txtContactNo3.setBackground(new java.awt.Color(255, 255, 255));
        txtContactNo3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtContactNo3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtContactNo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactNo3ActionPerformed(evt);
            }
        });

        lblContactno3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblContactno3.setForeground(new java.awt.Color(51, 51, 51));
        lblContactno3.setText("Contact No :");

        txtAdress3.setEditable(false);
        txtAdress3.setBackground(new java.awt.Color(255, 255, 255));
        txtAdress3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtAdress3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtAdress3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdress3ActionPerformed(evt);
            }
        });

        lblEmail3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblEmail3.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail3.setText("Email :");

        txtFname3.setEditable(false);
        txtFname3.setBackground(new java.awt.Color(255, 255, 255));
        txtFname3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtFname3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtFname3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFname3ActionPerformed(evt);
            }
        });

        lblAdress3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblAdress3.setForeground(new java.awt.Color(51, 51, 51));
        lblAdress3.setText("Adress 1 :");

        btnAddEmp3.setBackground(new java.awt.Color(0, 0, 102));
        btnAddEmp3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnAddEmp3.setForeground(new java.awt.Color(255, 255, 255));
        btnAddEmp3.setText("Delete Employee");
        btnAddEmp3.setBorderPainted(false);
        btnAddEmp3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddEmp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmp3ActionPerformed(evt);
            }
        });

        txtEmail3.setEditable(false);
        txtEmail3.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtEmail3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtEmail3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmail3ActionPerformed(evt);
            }
        });

        lblDepartment3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDepartment3.setForeground(new java.awt.Color(51, 51, 51));
        lblDepartment3.setText("Department :");

        txtAdressline3.setEditable(false);
        txtAdressline3.setBackground(new java.awt.Color(255, 255, 255));
        txtAdressline3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtAdressline3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtAdressline3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdressline3ActionPerformed(evt);
            }
        });

        lblAdressline3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblAdressline3.setForeground(new java.awt.Color(51, 51, 51));
        lblAdressline3.setText("Adress 2 :");

        txtdepartment3.setEditable(false);
        txtdepartment3.setBackground(new java.awt.Color(255, 255, 255));
        txtdepartment3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtdepartment3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtdepartment3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdepartment3ActionPerformed(evt);
            }
        });

        txteducationallevel3.setEditable(false);
        txteducationallevel3.setBackground(new java.awt.Color(255, 255, 255));
        txteducationallevel3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txteducationallevel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txteducationallevel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txteducationallevel3ActionPerformed(evt);
            }
        });

        txtdesignation3.setEditable(false);
        txtdesignation3.setBackground(new java.awt.Color(255, 255, 255));
        txtdesignation3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtdesignation3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtdesignation3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdesignation3ActionPerformed(evt);
            }
        });

        txtgender3.setEditable(false);
        txtgender3.setBackground(new java.awt.Color(255, 255, 255));
        txtgender3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtgender3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtgender3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgender3ActionPerformed(evt);
            }
        });

        txtstatus3.setEditable(false);
        txtstatus3.setBackground(new java.awt.Color(255, 255, 255));
        txtstatus3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtstatus3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtstatus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstatus3ActionPerformed(evt);
            }
        });

        lblpropic3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblpropic3.setForeground(new java.awt.Color(51, 51, 51));
        lblpropic3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblpropic3.setText("Profile Picture :");

        jLabel41.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.lightGray));

        jLabel40.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.lightGray));

        javax.swing.GroupLayout p1_DeletePaneLayout = new javax.swing.GroupLayout(p1_DeletePane);
        p1_DeletePane.setLayout(p1_DeletePaneLayout);
        p1_DeletePaneLayout.setHorizontalGroup(
            p1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                .addGroup(p1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbllname3)
                        .addGap(186, 186, 186)
                        .addGroup(p1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail3)
                            .addComponent(lblDepartment3)
                            .addComponent(txteducationallevel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblstatus3))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(txtgender3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(570, 570, 570)
                        .addComponent(lblimage3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(569, 569, 569)
                        .addComponent(lblpropic3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(570, 570, 570)
                        .addComponent(lblbarcodepreview3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblAdressline3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblfname3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblDOB3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtAdressline3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtLname3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtDOB3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblemono3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblNic3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtempno3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtNIC3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(lblContactno3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtAdress3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(txtdepartment3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(lblgender3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(txtdesignation3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblAdress3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(lbldesignation3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtFname3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(txtstatus3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(570, 570, 570)
                        .addComponent(lblbarcode3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(lbleducation3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(txtContactNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnGoBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(390, 390, 390)
                        .addComponent(btnAddEmp3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        p1_DeletePaneLayout.setVerticalGroup(
            p1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                .addGroup(p1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(lbllname3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(lblEmail3)
                        .addGap(62, 62, 62)
                        .addComponent(lblDepartment3)
                        .addGap(162, 162, 162)
                        .addComponent(txteducationallevel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(lblstatus3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(txtgender3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lblimage3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(lblpropic3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(lblbarcodepreview3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(560, 560, 560)
                        .addComponent(lblAdressline3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(lblfname3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(lblDOB3))
                    .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(580, 580, 580)
                        .addComponent(txtAdressline3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(txtLname3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(txtDOB3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lblemono3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(lblNic3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(txtempno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(txtNIC3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lblContactno3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(txtAdress3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(txtdepartment3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(480, 480, 480)
                        .addComponent(lblgender3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(txtdesignation3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(480, 480, 480)
                        .addComponent(lblAdress3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(lbldesignation3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(txtFname3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(580, 580, 580)
                        .addComponent(txtstatus3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(lblbarcode3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(lbleducation3))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(txtContactNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_DeletePaneLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(p1_DeletePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddEmp3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGoBack3))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        p1_UpdatePane.setBackground(new java.awt.Color(200, 210, 255));
        p1_UpdatePane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 204, 204)));
        p1_UpdatePane.setToolTipText("");
        p1_UpdatePane.setPreferredSize(new java.awt.Dimension(810, 712));
        p1_UpdatePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header3.setBackground(new java.awt.Color(0, 0, 104));
        header3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Update Employee :");

        javax.swing.GroupLayout header3Layout = new javax.swing.GroupLayout(header3);
        header3.setLayout(header3Layout);
        header3Layout.setHorizontalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(656, Short.MAX_VALUE))
        );
        header3Layout.setVerticalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p1_UpdatePane.add(header3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, -1));

        lblDOB2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDOB2.setForeground(new java.awt.Color(51, 51, 51));
        lblDOB2.setText("Date of Birth :");
        p1_UpdatePane.add(lblDOB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        txtLname2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtLname2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtLname2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLname2ActionPerformed(evt);
            }
        });
        txtLname2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLname2KeyPressed(evt);
            }
        });
        p1_UpdatePane.add(txtLname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 240, 30));

        lblemono2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblemono2.setForeground(new java.awt.Color(51, 51, 51));
        lblemono2.setText("Emp No :");
        p1_UpdatePane.add(lblemono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        btnGoBack2.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack2.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack2.setText("Go Back");
        btnGoBack2.setBorderPainted(false);
        btnGoBack2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGoBack2MouseClicked(evt);
            }
        });
        btnGoBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(btnGoBack2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 170, 30));

        txtDOB2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtDOB2.setForeground(new java.awt.Color(153, 153, 153));
        txtDOB2.setText("DD/MM/YYYY");
        txtDOB2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtDOB2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDOB2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDOB2FocusLost(evt);
            }
        });
        txtDOB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDOB2ActionPerformed(evt);
            }
        });
        txtDOB2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDOB2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDOB2KeyReleased(evt);
            }
        });
        p1_UpdatePane.add(txtDOB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 240, 30));

        lblimagename2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1_UpdatePane.add(lblimagename2, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 470, 250, 20));

        lblfname2.setBackground(new java.awt.Color(51, 0, 51));
        lblfname2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblfname2.setForeground(new java.awt.Color(51, 51, 51));
        lblfname2.setText("First Name :");
        p1_UpdatePane.add(lblfname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        txtempno2.setBackground(new java.awt.Color(235, 235, 235));
        txtempno2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtempno2.setForeground(new java.awt.Color(153, 153, 153));
        txtempno2.setText("              Enter Employee ID to Search");
        txtempno2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtempno2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtempno2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtempno2FocusLost(evt);
            }
        });
        txtempno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtempno2ActionPerformed(evt);
            }
        });
        txtempno2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtempno2KeyPressed(evt);
            }
        });
        p1_UpdatePane.add(txtempno2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 240, 30));

        btnUpload2.setBackground(new java.awt.Color(0, 153, 204));
        btnUpload2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btnUpload2.setText("Upload");
        btnUpload2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpload2MouseClicked(evt);
            }
        });
        btnUpload2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpload2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(btnUpload2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 110, -1));

        lbllname2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbllname2.setForeground(new java.awt.Color(51, 51, 51));
        lbllname2.setText("Last Name :");
        p1_UpdatePane.add(lbllname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        lblbarcodepreview2.setBorder(new javax.swing.border.MatteBorder(null));
        p1_UpdatePane.add(lblbarcodepreview2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 540, 220, 54));

        lblcapturephoto2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblcapturephoto2.setForeground(new java.awt.Color(51, 51, 51));
        lblcapturephoto2.setText("Capture Photo : ");
        p1_UpdatePane.add(lblcapturephoto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, -1, -1));

        lblcaptureimage2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        p1_UpdatePane.add(lblcaptureimage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 210, 140));

        lblimage2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        p1_UpdatePane.add(lblimage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 210, 140));

        lbldesignation2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbldesignation2.setForeground(new java.awt.Color(51, 51, 51));
        lbldesignation2.setText("Designation :");
        p1_UpdatePane.add(lbldesignation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, -1));

        txtDepartment2.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        txtDepartment2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Department", "Accounts", "Manufacturing", "Packing", "Sales" }));
        txtDepartment2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtDepartment2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtDepartment2ItemStateChanged(evt);
            }
        });
        txtDepartment2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartment2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(txtDepartment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 240, 30));

        lblbarcode2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblbarcode2.setForeground(new java.awt.Color(51, 51, 51));
        lblbarcode2.setText("Barcode :");
        p1_UpdatePane.add(lblbarcode2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 520, -1, -1));

        lbleducation2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbleducation2.setForeground(new java.awt.Color(51, 51, 51));
        lbleducation2.setText("Educational Level :");
        p1_UpdatePane.add(lbleducation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, -1, -1));

        lblgender2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblgender2.setForeground(new java.awt.Color(51, 51, 51));
        lblgender2.setText("Gender :");
        p1_UpdatePane.add(lblgender2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 480, -1, -1));

        lblstatus2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblstatus2.setForeground(new java.awt.Color(51, 51, 51));
        lblstatus2.setText("Status :");
        p1_UpdatePane.add(lblstatus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, -1, -1));

        txtDesignation2.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Designation" }));
        txtDesignation2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtDesignation2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDesignation2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(txtDesignation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 241, 30));

        txtNIC2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtNIC2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtNIC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIC2ActionPerformed(evt);
            }
        });
        txtNIC2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNIC2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNIC2KeyReleased(evt);
            }
        });
        p1_UpdatePane.add(txtNIC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 240, 30));

        lblNic2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblNic2.setForeground(new java.awt.Color(51, 51, 51));
        lblNic2.setText("NIC No :");
        p1_UpdatePane.add(lblNic2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        txtContactNo2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtContactNo2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtContactNo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactNo2ActionPerformed(evt);
            }
        });
        txtContactNo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactNo2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContactNo2KeyReleased(evt);
            }
        });
        p1_UpdatePane.add(txtContactNo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 240, 30));

        lblContactno2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblContactno2.setForeground(new java.awt.Color(51, 51, 51));
        lblContactno2.setText("Contact No :");
        p1_UpdatePane.add(lblContactno2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        txtAdress2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtAdress2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtAdress2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdress2ActionPerformed(evt);
            }
        });
        txtAdress2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdress2KeyPressed(evt);
            }
        });
        p1_UpdatePane.add(txtAdress2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 240, 30));

        lblEmail2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblEmail2.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail2.setText("Email :");
        p1_UpdatePane.add(lblEmail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        txtFname2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtFname2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtFname2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFname2ActionPerformed(evt);
            }
        });
        txtFname2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFname2KeyPressed(evt);
            }
        });
        p1_UpdatePane.add(txtFname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 240, 30));

        lblAdress2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblAdress2.setForeground(new java.awt.Color(51, 51, 51));
        lblAdress2.setText("Adress 1 :");
        p1_UpdatePane.add(lblAdress2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        btnAddEmp2.setBackground(new java.awt.Color(0, 0, 102));
        btnAddEmp2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnAddEmp2.setForeground(new java.awt.Color(255, 255, 255));
        btnAddEmp2.setText("Update Employee");
        btnAddEmp2.setBorderPainted(false);
        btnAddEmp2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddEmp2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddEmp2MouseClicked(evt);
            }
        });
        btnAddEmp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmp2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(btnAddEmp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 660, 170, 30));

        txtEmail2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtEmail2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtEmail2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmail2ActionPerformed(evt);
            }
        });
        txtEmail2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmail2KeyPressed(evt);
            }
        });
        p1_UpdatePane.add(txtEmail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 240, 30));

        lblDepartment2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDepartment2.setForeground(new java.awt.Color(51, 51, 51));
        lblDepartment2.setText("Department :");
        p1_UpdatePane.add(lblDepartment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));

        btncamON2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btncamON2.setText("Camera ON");
        btncamON2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncamON2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(btncamON2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 100, -1));

        btncamOFF2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btncamOFF2.setText("Camera OFF");
        btncamOFF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncamOFF2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(btncamOFF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 100, -1));

        btncapture2.setBackground(new java.awt.Color(51, 255, 51));
        btncapture2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        btncapture2.setText("Capture");
        btncapture2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapture2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(btncapture2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, -1));

        txtAdressline2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtAdressline2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtAdressline2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdressline2ActionPerformed(evt);
            }
        });
        txtAdressline2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdressline2KeyPressed(evt);
            }
        });
        p1_UpdatePane.add(txtAdressline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 240, 30));

        lblAdressline2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblAdressline2.setForeground(new java.awt.Color(51, 51, 51));
        lblAdressline2.setText("Adress 2 :");
        p1_UpdatePane.add(lblAdressline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        lblpropic2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblpropic2.setForeground(new java.awt.Color(51, 51, 51));
        lblpropic2.setText("Profile Picture :");
        p1_UpdatePane.add(lblpropic2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, -1, -1));

        txteducationallevel2.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        txteducationallevel2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Educational Level", "Upto G.C.E O/Level", "Passed G.C.E O/Level", "Passed G.C.E A/Level", "Graduate" }));
        txteducationallevel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txteducationallevel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txteducationallevel2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(txteducationallevel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 240, 30));

        r_male2.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        r_male2.setText("Male");
        r_male2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_male2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(r_male2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, -1, -1));

        r_female2.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        r_female2.setText("Female");
        r_female2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_female2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(r_female2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, -1, -1));

        r_Active2.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        r_Active2.setText("Active");
        r_Active2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_Active2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(r_Active2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, 100, -1));

        r_Inactive2.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        r_Inactive2.setText("In-Active");
        r_Inactive2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_Inactive2ActionPerformed(evt);
            }
        });
        p1_UpdatePane.add(r_Inactive2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, 100, -1));

        jLabel38.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        p1_UpdatePane.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 541, 600));

        jLabel39.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        p1_UpdatePane.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 270, 600));

        btnreset2.setBackground(new java.awt.Color(204, 102, 0));
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
        p1_UpdatePane.add(btnreset2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 660, 170, 30));

        p1_EMP_ReportPane.setBackground(new java.awt.Color(200, 210, 255));
        p1_EMP_ReportPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 204, 204)));
        p1_EMP_ReportPane.setPreferredSize(new java.awt.Dimension(810, 712));

        header2.setBackground(new java.awt.Color(0, 0, 104));
        header2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Employee Details :");

        javax.swing.GroupLayout header2Layout = new javax.swing.GroupLayout(header2);
        header2.setLayout(header2Layout);
        header2Layout.setHorizontalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header2Layout.setVerticalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, header2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);

        EMPtable.setBackground(new java.awt.Color(122, 204, 255));
        EMPtable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        EMPtable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        EMPtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        EMPtable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        EMPtable.setFocusable(false);
        EMPtable.setGridColor(new java.awt.Color(51, 51, 51));
        EMPtable.setMinimumSize(new java.awt.Dimension(500, 2500));
        EMPtable.setRowHeight(25);
        EMPtable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        EMPtable.setShowVerticalLines(false);
        EMPtable.getTableHeader().setResizingAllowed(false);
        EMPtable.getTableHeader().setReorderingAllowed(false);
        EMPtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EMPtableMouseClicked(evt);
            }
        });
        EMPtable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                EMPtablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(EMPtable);

        btnGoBack4.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack4.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack4.setText("Go Back");
        btnGoBack4.setBorderPainted(false);
        btnGoBack4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGoBack4MouseClicked(evt);
            }
        });
        btnGoBack4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack4ActionPerformed(evt);
            }
        });
        btnGoBack4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGoBack4KeyPressed(evt);
            }
        });

        btnPrintempdetails.setBackground(new java.awt.Color(0, 0, 102));
        btnPrintempdetails.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnPrintempdetails.setForeground(new java.awt.Color(255, 255, 255));
        btnPrintempdetails.setText("Print");
        btnPrintempdetails.setBorderPainted(false);
        btnPrintempdetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrintempdetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintempdetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p1_EMP_ReportPaneLayout = new javax.swing.GroupLayout(p1_EMP_ReportPane);
        p1_EMP_ReportPane.setLayout(p1_EMP_ReportPaneLayout);
        p1_EMP_ReportPaneLayout.setHorizontalGroup(
            p1_EMP_ReportPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1_EMP_ReportPaneLayout.createSequentialGroup()
                .addGroup(p1_EMP_ReportPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1_EMP_ReportPaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1_EMP_ReportPaneLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnGoBack4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(379, 379, 379)
                        .addComponent(btnPrintempdetails, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(header2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p1_EMP_ReportPaneLayout.setVerticalGroup(
            p1_EMP_ReportPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1_EMP_ReportPaneLayout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(p1_EMP_ReportPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1_EMP_ReportPaneLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnGoBack4))
                    .addComponent(btnPrintempdetails, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        p1_SearchPane.setBackground(new java.awt.Color(200, 210, 255));
        p1_SearchPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 204, 204)));
        p1_SearchPane.setToolTipText("");
        p1_SearchPane.setPreferredSize(new java.awt.Dimension(810, 712));
        p1_SearchPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1_SearchPaneMouseClicked(evt);
            }
        });
        p1_SearchPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGoBack5.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack5.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack5.setText("Go Back");
        btnGoBack5.setBorderPainted(false);
        btnGoBack5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack5ActionPerformed(evt);
            }
        });
        p1_SearchPane.add(btnGoBack5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 170, -1));

        lblimagename3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1_SearchPane.add(lblimagename3, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 470, 250, 20));

        jLabel44.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel44.setText("EmpNo");
        p1_SearchPane.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 100, 20));

        searchEmp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        searchEmp.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p1_SearchPane.add(searchEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 220, 30));

        search.setBackground(new java.awt.Color(0, 153, 204));
        search.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        p1_SearchPane.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 140, 30));

        jPanel1.setBackground(new java.awt.Color(227, 226, 226));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );

        p1_SearchPane.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 810, 520));

        header4.setBackground(new java.awt.Color(0, 0, 104));
        header4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        header4.setPreferredSize(new java.awt.Dimension(159, 46));
        header4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Search Employee :");
        header4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, -1, 30));

        p1_SearchPane.add(header4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(p1_SearchPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p1_DeletePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p1_UpdatePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p1_AddPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p1_EMP_ReportPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(p1_SearchPane, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 142, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p1_DeletePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p1_UpdatePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p1_AddPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p1_EMP_ReportPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEmployeeMouseClicked
        // TODO add your handling code here:
        p1_AddPane.setVisible(true);
        p1_DeletePane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(false);

        p1.setVisible(false);

    }//GEN-LAST:event_addEmployeeMouseClicked

    private void deleteEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteEmployeeMouseClicked
        // TODO add your handling code here:
        p1_DeletePane.setVisible(true);
        p1_AddPane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(false);
        p1.setVisible(false);


    }//GEN-LAST:event_deleteEmployeeMouseClicked

    private void reportEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportEmployeeMouseClicked
        // TODO add your handling code here:

        p1_EMP_ReportPane.setVisible(true);
        p1_DeletePane.setVisible(false);
        p1_AddPane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_SearchPane.setVisible(false);
        p1.setVisible(false);

    }//GEN-LAST:event_reportEmployeeMouseClicked

    private void updateEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateEmployeeMouseClicked
        // TODO add your handling code here:
        p1.setVisible(false);
        p1_AddPane.setVisible(false);
        p1_UpdatePane.setVisible(true);
        p1_DeletePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(false);
    }//GEN-LAST:event_updateEmployeeMouseClicked

    private void txtLname3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLname3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLname3ActionPerformed

    private void btnGoBack3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGoBack3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnGoBack3MouseClicked

    private void btnGoBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack3ActionPerformed
        // TODO add your handling code here:
        p1.setVisible(true);
        p1_AddPane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_DeletePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(false);

        txtempno3.setText("              Enter Employee ID to Search");
        txtContactNo3.setText(null);
        txtNIC3.setText(null);
        txtEmail3.setText(null);
        txtFname3.setText(null);
        txtdepartment3.setText(null);
        txtLname3.setText(null);
        txtdesignation3.setText(null);
        txteducationallevel3.setText(null);
        txtAdress3.setText(null);
        txtgender3.setText(null);
        txtAdressline3.setText(null);
        txtstatus3.setText(null);
        lblimage3.setIcon(null);
        lblbarcodepreview3.setIcon(null);
        txtDOB3.setText(null);
    }//GEN-LAST:event_btnGoBack3ActionPerformed

    private void btnGoBack3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGoBack3KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnGoBack3KeyPressed

    private void txtDOB3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOB3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB3FocusGained

    private void txtDOB3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOB3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB3FocusLost

    private void txtDOB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDOB3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB3ActionPerformed

    private void txtempno3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtempno3FocusGained
        // TODO add your handling code here:
        if (txtempno3.getText().trim().equals("Enter Employee ID to Search")) {
            txtempno3.setText("");
            txtempno3.setForeground(new Color(153, 153, 153));
        }
        txtempno3.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtempno3FocusGained

    private void txtempno3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtempno3FocusLost
        // TODO add your handling code here:
        if (txtempno3.getText().trim().equals("")) {
            txtempno3.setText("             Enter Employee ID to Search");
        }
    }//GEN-LAST:event_txtempno3FocusLost

    private void txtempno3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtempno3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempno3ActionPerformed

    private void txtempno3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtempno3KeyPressed
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM employee where EmpNo=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(sql);

            pst.setString(1, txtempno3.getText());
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                txtempno3.setText(rs.getString("EmpNo"));
                txtNIC3.setText(rs.getString("NIC_No"));
                txtFname3.setText(rs.getString("First_Name"));
                txtLname3.setText(rs.getString("Last_Name"));
                txtDOB3.setText(rs.getString("DOB"));
                txtAdress3.setText(rs.getString("Address1"));
                txtAdressline3.setText(rs.getString("Address2"));
                txtContactNo3.setText(rs.getString("Contact_No"));
                txtEmail3.setText(rs.getString("Email"));
                txtdepartment3.setText(rs.getString("Department"));
                txteducationallevel3.setText(rs.getString("Educational_Level"));
                txtdesignation3.setText(rs.getString("Designation"));
                txtgender3.setText(rs.getString("Gender"));
                txtstatus3.setText(rs.getString("Status"));

                byte[] img = rs.getBytes("Profile_Pic");
                ImageIcon proimage = new ImageIcon(img);
                Image im = proimage.getImage();
                Image myImg = im.getScaledInstance(lblimage3.getWidth(), lblimage3.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                lblimage3.setIcon(newImage);

                byte[] brimg = rs.getBytes("Barcode");
                ImageIcon brimage = new ImageIcon(brimg);
                Image brim = brimage.getImage();
                Image brmyImg = brim.getScaledInstance(lblbarcodepreview3.getWidth(), lblbarcodepreview3.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon brImage = new ImageIcon(brmyImg);
                lblbarcodepreview3.setIcon(brImage);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_txtempno3KeyPressed

    private void txtNIC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNIC3ActionPerformed

    private void txtContactNo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactNo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactNo3ActionPerformed

    private void txtAdress3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdress3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdress3ActionPerformed

    private void txtFname3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFname3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFname3ActionPerformed

    private void btnAddEmp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmp3ActionPerformed
        // TODO add your handling code here:
        if (txtempno3.getText().trim().equals("Enter Employee ID to Search")) {
            txtempno3.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The EmpNo field is empty !!");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Delete !!", "Delete", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {

                try {
                    String le = "DELETE FROM`leaveinformation`WHERE EmpNo='" + txtempno3.getText() + "'";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst = con.prepareStatement(le);
                    pst.executeUpdate();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }

                try {
                    String query = "DELETE FROM employee WHERE EmpNo=?";
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst = con.prepareStatement(query);
                    pst.setString(1, txtempno3.getText());
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Employee Delete Successfully ");
                    txtempno3.setText("              Enter Employee ID to Search");
                    txtNIC3.setText("");
                    txtFname3.setText("");
                    txtLname3.setText("");
                    txtDOB3.setText("");
                    txtAdress3.setText("");
                    txtAdressline3.setText("");
                    txtContactNo3.setText("");
                    txtEmail3.setText("");
                    txtdepartment3.setText("");
                    txtdesignation3.setText("");
                    txteducationallevel3.setText("");
                    txtgender3.setText("");
                    txtstatus3.setText("");
                    lblimage3.setIcon(null);
                    lblbarcodepreview3.setIcon(null);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
            }
        }
        tablelord();
    }//GEN-LAST:event_btnAddEmp3ActionPerformed

    private void txtEmail3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmail3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmail3ActionPerformed

    private void txtAdressline3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdressline3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdressline3ActionPerformed

    private void txtdepartment3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdepartment3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdepartment3ActionPerformed

    private void txteducationallevel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txteducationallevel3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txteducationallevel3ActionPerformed

    private void txtdesignation3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdesignation3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdesignation3ActionPerformed

    private void txtgender3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgender3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgender3ActionPerformed

    private void txtstatus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstatus3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstatus3ActionPerformed

    private void txtLname2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLname2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLname2ActionPerformed

    private void txtLname2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLname2KeyPressed
        // TODO add your handling code here:
        txtLname2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtLname2KeyPressed

    private void btnGoBack2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGoBack2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnGoBack2MouseClicked

    private void btnGoBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack2ActionPerformed
        // TODO add your handling code here:
        p1.setVisible(true);
        p1_AddPane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_DeletePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(false);

    }//GEN-LAST:event_btnGoBack2ActionPerformed

    private void txtDOB2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOB2FocusGained
        // TODO add your handling code here:
        if (txtDOB2.getText().trim().equals("DD/MM/YYYY")) {
            txtDOB2.setText("");
            txtDOB2.setForeground(new Color(153, 153, 153));
        }
        txtDOB2.setForeground(Color.BLACK);

    }//GEN-LAST:event_txtDOB2FocusGained

    private void txtDOB2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOB2FocusLost
        // TODO add your handling code here:
        if (txtDOB2.getText().trim().equals("")) {
            txtDOB2.setText("DD/MM/YYYY");
        }
    }//GEN-LAST:event_txtDOB2FocusLost

    private void txtDOB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDOB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB2ActionPerformed

    private void txtDOB2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOB2KeyPressed
        // TODO add your handling code here:
        txtDOB2.setBackground(new Color(255, 255, 255));

    }//GEN-LAST:event_txtDOB2KeyPressed

    private void txtDOB2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOB2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB2KeyReleased

    private void txtempno2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtempno2FocusGained
        // TODO add your handling code here:
        if (txtempno2.getText().trim().equals("Enter Employee ID to Search")) {
            txtempno2.setText("");
            txtempno2.setForeground(new Color(153, 153, 153));
        }
        txtempno2.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtempno2FocusGained

    private void txtempno2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtempno2FocusLost
        // TODO add your handling code here:
        if (txtempno2.getText().trim().equals("")) {
            txtempno2.setText("             Enter Employee ID to Search");
        }
    }//GEN-LAST:event_txtempno2FocusLost

    private void txtempno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtempno2ActionPerformed
        // TODO add your handling code here:
        txtDOB2.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtempno2ActionPerformed

    private void txtempno2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtempno2KeyPressed
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM employee where EmpNo=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(sql);

            pst.setString(1, txtempno2.getText());
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                txtempno2.setText(rs.getString("EmpNo"));
                txtNIC2.setText(rs.getString("NIC_No"));
                txtFname2.setText(rs.getString("First_Name"));
                txtLname2.setText(rs.getString("Last_Name"));
                txtDOB2.setText(rs.getString("DOB"));
                txtAdress2.setText(rs.getString("Address1"));
                txtAdressline2.setText(rs.getString("Address2"));
                txtContactNo2.setText(rs.getString("Contact_No"));
                txtEmail2.setText(rs.getString("Email"));

                String department;
                department = (rs.getString("Department"));
                String designation;
                designation = (rs.getString("Designation"));
                String educationallevel;
                educationallevel = (rs.getString("Educational_Level"));

                if (department.matches("Accounts")) {
                    txtDepartment2.setSelectedIndex(1);
                    txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Charted Accountant", "Head Accountant", "Senior Accountant", "Junior Accountant"}));

                    if (designation.matches("Charted Accountant")) {
                        txtDesignation2.setSelectedIndex(0);
                    } else if (designation.matches("Head Accountant")) {
                        txtDesignation2.setSelectedIndex(1);
                    } else if (designation.matches("Senior Accountant")) {
                        txtDesignation2.setSelectedIndex(2);
                    } else if (designation.matches("Junior Accountant")) {
                        txtDesignation2.setSelectedIndex(3);
                    }

                } else if (department.matches("Manufacturing")) {
                    txtDepartment2.setSelectedIndex(2);
                    txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Head Officer", "Checker", "Superviser", "Helper"}));

                    if (designation.matches("Head Officer")) {
                        txtDesignation2.setSelectedIndex(0);
                    } else if (designation.matches("Checker")) {
                        txtDesignation2.setSelectedIndex(1);
                    } else if (designation.matches("Superviser")) {
                        txtDesignation2.setSelectedIndex(2);
                    } else if (designation.matches("Helper")) {
                        txtDesignation2.setSelectedIndex(3);
                    }
                } else if (department.matches("Packing")) {
                    txtDepartment2.setSelectedIndex(3);
                    txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Head Packing Officer", "Assembler", "Superviser", "Helper"}));

                    if (designation.matches("Head Packing Officer")) {
                        txtDesignation2.setSelectedIndex(0);
                    } else if (designation.matches("Assembler")) {
                        txtDesignation2.setSelectedIndex(1);
                    } else if (designation.matches("Superviser")) {
                        txtDesignation2.setSelectedIndex(2);
                    } else if (designation.matches("Helper")) {
                        txtDesignation2.setSelectedIndex(3);
                    }
                } else if (department.matches("Sales")) {
                    txtDepartment2.setSelectedIndex(4);
                    txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Head Sale Officer", "Quality Manager", "Dispatcher", "Helper"}));

                    if (designation.matches("Head Sale Officer")) {
                        txtDesignation2.setSelectedIndex(0);
                    } else if (designation.matches("Quality Man")) {
                        txtDesignation2.setSelectedIndex(1);
                    } else if (designation.matches("Dispatcher")) {
                        txtDesignation2.setSelectedIndex(2);
                    } else if (designation.matches("Helper")) {
                        txtDesignation2.setSelectedIndex(3);
                    }
                }

                if (educationallevel.matches("Upto G.C.E O/Level")) {
                    txteducationallevel2.setSelectedIndex(1);
                } else if (educationallevel.matches("Passed G.C.E O/Level")) {
                    txteducationallevel2.setSelectedIndex(2);
                } else if (educationallevel.matches("Passed G.C.E A/Level")) {
                    txteducationallevel2.setSelectedIndex(3);
                } else if (educationallevel.matches("Graduate")) {
                    txteducationallevel2.setSelectedIndex(4);
                }

                String gender;
                gender = (rs.getString("Gender"));
                if (gender.matches("Female")) {
                    r_female2.setSelected(true);
                    r_male2.setSelected(false);
                } else if (gender.matches("Male")) {
                    r_male2.setSelected(true);
                    r_female2.setSelected(false);
                }

                String status;
                status = (rs.getString("Status"));
                if (status.matches("Active")) {
                    r_Active2.setSelected(true);
                    r_Inactive2.setSelected(false);
                } else if (status.matches("In-Active")) {
                    r_Inactive2.setSelected(true);
                    r_Active2.setSelected(false);
                }

                byte[] img = rs.getBytes("Profile_Pic");
                ImageIcon proimage = new ImageIcon(img);
                Image im = proimage.getImage();
                Image myImg = im.getScaledInstance(lblimage2.getWidth(), lblimage2.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                lblimage2.setIcon(newImage);

                byte[] brimg = rs.getBytes("Barcode");
                ImageIcon brimage = new ImageIcon(brimg);
                Image brim = brimage.getImage();
                Image brmyImg = brim.getScaledInstance(lblbarcodepreview2.getWidth(), lblbarcodepreview2.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon brImage = new ImageIcon(brmyImg);
                lblbarcodepreview2.setIcon(brImage);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_txtempno2KeyPressed

    private void btnUpload2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpload2MouseClicked
        // TODO add your handling code here:
        String currentDirectoryPath = "C:\\srbio\\images\\employee/";
        JFileChooser imageFileChooser = new JFileChooser(currentDirectoryPath);
        int imageChooser = imageFileChooser.showOpenDialog(null);
        imageFileChooser.setDialogTitle("Choose Image");

        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg");
        imageFileChooser.setFileFilter(fnef);

        if (imageChooser == JFileChooser.APPROVE_OPTION) {
            File imageFile = imageFileChooser.getSelectedFile();
            upimageFilePath = imageFile.getAbsolutePath();
            upimageFileName = imageFile.getName();

            lblimagename2.setText(upimageFileName);

            ImageIcon upimageIcon = new ImageIcon(upimageFilePath);

            Image upimage = upimageIcon.getImage().getScaledInstance(lblimage2.getWidth(), lblimage2.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon resizedImageIcon = new ImageIcon(upimage);
            lblimage2.setIcon(resizedImageIcon);
        }
        try {
            File file = new File(upimageFilePath);
            upinputstream = new FileInputStream(file);
            byte[] upimage = new byte[(int) file.length()];
            upinputstream.read(upimage);

            String sql = "UPDATE employee SET Profile_Pic=? where EmpNo=?";
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);

            pst.setBytes(1, upimage);
            pst.setString(2, txtempno2.getText());
            pst.executeUpdate();
            pst.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        btnUpload2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnUpload2MouseClicked

    private void btnUpload2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpload2ActionPerformed

    }//GEN-LAST:event_btnUpload2ActionPerformed

    private void txtDepartment2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtDepartment2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartment2ItemStateChanged

    private void txtDepartment2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartment2ActionPerformed
        // TODO add your handling code here:
        txtDepartment2.setBackground(new Color(255, 255, 255));
        if (txtDepartment2.getSelectedItem().equals("Accounts")) {

            txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation", "Charted Accountant", "Head Accountant", "Senior Accountant", "Junior Accountant"}));
        } else if (txtDepartment2.getSelectedItem().equals("Manufacturing")) {

            txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation", "Head Officer", "Checker", "Superviser", "Helper"}));
        } else if (txtDepartment2.getSelectedItem().equals("Packing")) {

            txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation", "Head Packing Officer", "Assembler", "Superviser", "Helper"}));
        } else if (txtDepartment2.getSelectedItem().equals("Sales")) {

            txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation", "Head Sale Officer", "Quality Man", "Dispatcher", "Helper"}));
        } else {

            txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation"}));
        }
    }//GEN-LAST:event_txtDepartment2ActionPerformed

    private void txtDesignation2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDesignation2ActionPerformed
        // TODO add your handling code here
        txtDesignation2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtDesignation2ActionPerformed

    private void txtNIC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNIC2ActionPerformed

    private void txtNIC2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNIC2KeyPressed
        // TODO add your handling code here:
        txtNIC2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtNIC2KeyPressed

    private void txtContactNo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactNo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactNo2ActionPerformed

    private void txtContactNo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactNo2KeyPressed
        // TODO add your handling code here:
        String phoneNumber = txtContactNo2.getText();

        int length = phoneNumber.length();
        char c = evt.getKeyChar();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            if (length < 10) {
                txtContactNo2.setEditable(true);

            } else {
                txtContactNo2.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                txtContactNo2.setEditable(true);

            } else {
                txtContactNo2.setEditable(false);
            }

        }
    }//GEN-LAST:event_txtContactNo2KeyPressed

    private void txtContactNo2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactNo2KeyReleased
        // TODO add your handling code here:
        String mobileNo = txtContactNo2.getText();

        if (mobileNo.length() >= 1 && mobileNo.length() < 10) {
            txtContactNo2.setBackground(new Color(255, 0, 51));
        } else {
            txtContactNo2.setBackground(new Color(255, 255, 255));

        }
    }//GEN-LAST:event_txtContactNo2KeyReleased

    private void txtAdress2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdress2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdress2ActionPerformed

    private void txtAdress2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdress2KeyPressed
        // TODO add your handling code here:
        txtAdress2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtAdress2KeyPressed

    private void txtFname2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFname2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFname2ActionPerformed

    private void txtFname2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFname2KeyPressed
        // TODO add your handling code here:
        txtFname2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtFname2KeyPressed

    private void btnAddEmp2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddEmp2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddEmp2MouseClicked

    private void btnAddEmp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmp2ActionPerformed
        // TODO add your handling code here:

        String gender = null;
        if (r_male2.isSelected()) {
            gender = r_male2.getText();
        } else if (r_female2.isSelected()) {
            gender = r_female2.getText();
        }
        String status = null;
        if (r_Active2.isSelected()) {
            status = r_Active2.getText();
        } else if (r_Inactive2.isSelected()) {
            status = r_Inactive2.getText();
        }

        if (txtNIC2.getText().isEmpty()) {
            txtNIC2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The NIC NO field is empty !!");
        } else if (txtFname2.getText().isEmpty()) {
            txtFname2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The First Name field is empty !!");
        } else if (txtLname2.getText().isEmpty()) {
            txtLname2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Last Name field is empty !!");
        } else if (txtDOB2.getText().equals("DD/MM/YYYY")) {
            txtDOB2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The  Date of Birth field is empty !!");
        } else if (txtAdress2.getText().isEmpty()) {
            txtAdress2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Address1 field is empty !!");
        } else if (txtAdressline2.getText().isEmpty()) {
            txtAdressline2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Address2 field is empty !!");
        } else if (txtContactNo2.getText().isEmpty()) {
            txtContactNo2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Contact NO field is empty !!");
        } else if (txtEmail2.getText().isEmpty()) {
            txtEmail2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Email field is empty !!");
        } else if (txtDepartment2.getSelectedItem().equals("Select Department")) {
            txtDepartment2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please select the Department !!");
        } else if (txtDesignation2.getSelectedItem().equals("Select Designation")) {
            txtDesignation2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please select the Designation !!");
        } else if (txteducationallevel2.getSelectedItem().equals("Select Educational Level")) {
            txteducationallevel2.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please select Educational Level !!");
        } else if (gender == null) {
            r_male2.setForeground(new java.awt.Color(255, 0, 51));
            r_female2.setForeground(new java.awt.Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Select the Gender !!");
        } else if (status == null) {
            r_Active2.setForeground(new java.awt.Color(255, 0, 51));
            r_Inactive2.setForeground(new java.awt.Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Select the Status  !!");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Update this record!!", "Update Record", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {

                    String sql = "UPDATE employee SET NIC_No=?,First_Name=?,Last_Name=?,DOB=?,Address1=?,Address2=?,Contact_No=?,Email=?,Department=?,Designation=?,Educational_Level=?,Gender=?,Status=? where EmpNo=? ";
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                    pst.setString(1, txtNIC2.getText());
                    pst.setString(2, txtFname2.getText());
                    pst.setString(3, txtLname2.getText());
                    pst.setString(4, txtDOB2.getText());
                    pst.setString(5, txtAdress2.getText());
                    pst.setString(6, txtAdressline2.getText());
                    pst.setString(7, txtContactNo2.getText());
                    pst.setString(8, txtEmail2.getText());
                    pst.setString(9, String.valueOf(txtDepartment2.getSelectedItem()));
                    pst.setString(10, String.valueOf(txtDesignation2.getSelectedItem()));
                    pst.setString(11, String.valueOf(txteducationallevel2.getSelectedItem()));
                    pst.setString(12, gender);
                    pst.setString(13, status);

                    pst.setString(14, txtempno2.getText());

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    txtempno2.setText("              Enter Employee ID to Search");
                    txtNIC2.setText("");
                    txtFname2.setText("");
                    txtLname2.setText("");
                    if (txtDOB.getText().trim().equals("")) {
                        txtDOB.setText("DD/MM/YYYY");
                        txtDOB.setForeground(new Color(153, 153, 153));
                    }
                    txtDOB2.setText(null);
                    txtAdress2.setText("");
                    txtAdressline2.setText("");
                    txtContactNo2.setText("");
                    txtEmail2.setText("");
                    txtDepartment2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Department", "Accounts", "Manufacturing", "Packing", "Sales"}));
                    txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation"}));
                    txteducationallevel2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Educational Level", "Upto G.C.E O/Level", "Passed G.C.E O/Level", "Passed G.C.E A/Level", "Graduate"}));
                    r_male2.setSelected(false);
                    r_female2.setSelected(false);
                    r_Active2.setSelected(false);
                    r_Inactive2.setSelected(false);
                    r_female2.setSelected(false);
                    lblimage2.setIcon(null);
                    lblbarcodepreview2.setIcon(null);
                    lblimagename2.setText(null);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
            }
        }
        tablelord();
    }//GEN-LAST:event_btnAddEmp2ActionPerformed

    private void txtEmail2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmail2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmail2ActionPerformed

    private void txtEmail2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmail2KeyPressed
        // TODO add your handling code here:
        

 txtEmail2.setBackground(new Color(255,255,255));
        
          txtEmail2.setBackground(new Color(255,255,255));
        
     if(!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", txtEmail2.getText()))) 
     {
         txtEmail2.setBackground(new Color(255,0,51));  
     }
     else
     {
       txtEmail2.setBackground(new Color(255,255,255));  
     }

    }//GEN-LAST:event_txtEmail2KeyPressed

    private void btncamON2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncamON2ActionPerformed
        // TODO add your handling code here:

        btncamON2.setEnabled(false);
        btncamOFF2.setEnabled(true);
        btncapture2.setEnabled(true);

        Thread t = new Thread() {
            @Override
            public void run() {
                wCamPanel.start();
            }
        };
        t.setDaemon(true);
        t.start();
        wCam.setViewSize(cs);
        wCamPanel.setFillArea(true);

        lblcaptureimage2.setLayout(new FlowLayout());
        lblcaptureimage2.add(wCamPanel);
    }//GEN-LAST:event_btncamON2ActionPerformed

    private void btncamOFF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncamOFF2ActionPerformed
        // TODO add your handling code here:
        btncamON2.setEnabled(true);
        btncamOFF2.setEnabled(false);
        btncapture2.setEnabled(false);

        Thread t = new Thread() {
            @Override
            public void run() {
                wCamPanel.stop();
            }
        };
        t.setDaemon(false);
        t.start();
        wCamPanel.setFillArea(false);
    }//GEN-LAST:event_btncamOFF2ActionPerformed

    private void btncapture2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapture2ActionPerformed
        // TODO add your handling code here:
        try {

            File file = new File(String.format("capture-%d.jpg", System.currentTimeMillis()));
            ImageIO.write(wCam.getImage(), "JPG", file);
            JOptionPane.showMessageDialog(this, "image saved on :\n" + file.getAbsolutePath(), "CamCap", 1);
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(this, "Image Not Saved :/n" + e.getMessage(), "Cam", 0);

        }
    }//GEN-LAST:event_btncapture2ActionPerformed

    private void txtAdressline2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdressline2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdressline2ActionPerformed

    private void txtAdressline2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdressline2KeyPressed
        // TODO add your handling code here:
        txtAdressline2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtAdressline2KeyPressed

    private void txteducationallevel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txteducationallevel2ActionPerformed
        // TODO add your handling code here:
        txteducationallevel2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txteducationallevel2ActionPerformed

    private void r_male2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_male2ActionPerformed
        // Male:
        status = "Male";
        r_male2.setSelected(true);
        r_female2.setSelected(false);

        if (r_male2.isSelected()) {
            r_male2.setForeground(new java.awt.Color(0, 0, 0));
            r_female2.setForeground(new java.awt.Color(0, 0, 0));
            r_female2.setSelected(false);
        }
    }//GEN-LAST:event_r_male2ActionPerformed

    private void r_female2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_female2ActionPerformed
        // Female:
        status = "Female";
        r_male2.setSelected(false);
        r_female2.setSelected(true);

        if (r_female2.isSelected()) {
            r_female2.setForeground(new java.awt.Color(0, 0, 0));
            r_male2.setForeground(new java.awt.Color(0, 0, 0));
            r_male2.setSelected(false);
        }
    }//GEN-LAST:event_r_female2ActionPerformed

    private void r_Active2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_Active2ActionPerformed
        // Active:
        status = "Active";
        r_Active2.setSelected(true);
        r_Inactive2.setSelected(false);

        if (r_Active2.isSelected()) {
            r_Active2.setForeground(new java.awt.Color(0, 0, 0));
            r_Inactive2.setForeground(new java.awt.Color(0, 0, 0));
            r_Inactive2.setSelected(false);
        }
    }//GEN-LAST:event_r_Active2ActionPerformed

    private void r_Inactive2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_Inactive2ActionPerformed
        // Inactive:
        status = "Inactive";
        r_Active2.setSelected(false);
        r_Inactive2.setSelected(true);

        if (r_Inactive2.isSelected()) {
            r_Inactive2.setForeground(new java.awt.Color(0, 0, 0));
            r_Active2.setForeground(new java.awt.Color(0, 0, 0));
            r_Active2.setSelected(false);
        }
    }//GEN-LAST:event_r_Inactive2ActionPerformed

    private void btnreset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreset2ActionPerformed
        // TODO add your handling code here:
        txtempno2.setText("              Enter Employee ID to Search");
        txtContactNo2.setText(null);
        txtNIC2.setText(null);
        txtEmail2.setText(null);
        txtFname2.setText(null);
        txtDepartment2.setSelectedIndex(0);
        txtLname2.setText(null);
        txtDesignation2.setSelectedIndex(0);
        txteducationallevel2.setSelectedIndex(0);
        txtAdress2.setText(null);
        r_male2.setSelected(false);
        r_female2.setSelected(false);
        txtAdressline2.setText(null);
        r_Active2.setSelected(false);
        r_Inactive2.setSelected(false);
        lblbarcodepreview2.setIcon(null);
        txtDOB2.setText(null);
        if (txtDOB2.getText().trim().equals("")) {
            txtDOB2.setText("DD/MM/YYYY");
            txtDOB2.setForeground(new Color(153, 153, 153));

        }
        txtDOB2.setForeground(new Color(153, 153, 153));

        r_Active2.setSelected(false);
        r_Inactive2.setSelected(false);
        r_Active2.setForeground(new java.awt.Color(0, 0, 0));
        r_Inactive2.setForeground(new java.awt.Color(0, 0, 0));

        r_female2.setSelected(false);
        r_male2.setSelected(false);
        r_male2.setForeground(new java.awt.Color(0, 0, 0));
        r_female2.setForeground(new java.awt.Color(0, 0, 0));

        txtDepartment2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Department", "Accounts", "Manufacturing", "Packing", "Sales"}));
        txtDesignation2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation"}));
        txteducationallevel2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Educational Level", "Upto G.C.E O/Level", "Passed G.C.E O/Level", "Passed G.C.E A/Level", "Graduate"}));

        lblimage2.setIcon(null);
        lblbarcodepreview2.setIcon(null);
        lblimagename2.setText(null);

    }//GEN-LAST:event_btnreset2ActionPerformed

    private void txtLnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLnameActionPerformed

    private void txtLnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLnameKeyPressed
        // TODO add your handling code here:
        txtLname.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtLnameKeyPressed

    private void btnGoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBackActionPerformed
        // TODO add your handling code here:
        p1.setVisible(true);
        p1_AddPane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_DeletePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(false);

    }//GEN-LAST:event_btnGoBackActionPerformed

    private void txtDOBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOBFocusGained
        // TODO add your handling code here:
        if (txtDOB.getText().trim().equals("DD/MM/YYYY")) {
            txtDOB.setText("");
            txtDOB.setForeground(new Color(153, 153, 153));
        }
        txtDOB.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtDOBFocusGained

    private void txtDOBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOBFocusLost
        // TODO add your handling code here:
        if (txtDOB.getText().trim().equals("")) {
            txtDOB.setText("DD/MM/YYYY");
        }
    }//GEN-LAST:event_txtDOBFocusLost

    private void txtDOBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDOBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOBMouseClicked

    private void txtDOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDOBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOBActionPerformed

    private void txtDOBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOBKeyPressed
        // TODO add your handling code here:
        txtDOB.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtDOBKeyPressed

    private void txtempnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtempnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempnoActionPerformed

    private void txtempnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtempnoKeyPressed
        // TODO add your handling code here:
        txtempno.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtempnoKeyPressed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        // TODO add your handling code here:
        String currentDirectoryPath = "C:\\Users\\Public\\Pictures\\sample";
        JFileChooser imageFileChooser = new JFileChooser(currentDirectoryPath);
        int imageChooser = imageFileChooser.showOpenDialog(null);
        imageFileChooser.setDialogTitle("Choose Image");

        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg");
        imageFileChooser.setFileFilter(fnef);

        if (imageChooser == JFileChooser.APPROVE_OPTION) {
            File imageFile = imageFileChooser.getSelectedFile();
            imageFilePath = imageFile.getAbsolutePath();
            imageFileName = imageFile.getName();

            lblimagename.setText(imageFileName);

            ImageIcon imageIcon = new ImageIcon(imageFilePath);

            Image image = imageIcon.getImage().getScaledInstance(lblimage.getWidth(), lblimage.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon resizedImageIcon = new ImageIcon(image);
            lblimage.setIcon(resizedImageIcon);

        }
        btnUpload.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btngeneratebarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngeneratebarcodeActionPerformed
        // Generate Barcode:
        try {

            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(txtempno.getText());
            barcode.setI(11.0f);

            String fname = txtempno.getText();

            barcode.renderBarcode("C:\\srbio\\barcode\\employee/" + fname + ".png");
            ImageIcon imageIcon = new ImageIcon("C:\\srbio\\barcode\\employee/" + fname + ".png");
            barcodeFilePath = ("C:\\srbio\\barcode\\employee/" + fname + ".png");
            Image image = imageIcon.getImage().getScaledInstance(lblbarcodepreview.getWidth(), lblbarcodepreview.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon resizedImageIcon = new ImageIcon(image);
            lblbarcodepreview.setIcon(resizedImageIcon);

        } catch (Exception e) {

        }
        btngeneratebarcode.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btngeneratebarcodeActionPerformed

    private void txtDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtDepartmentItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentItemStateChanged

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
        txtDepartment.setBackground(new Color(255, 255, 255));
        if (txtDepartment.getSelectedItem().equals("Accounts")) {

            txtDesignation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation", "Charted Accountant", "Head Accountant", "Senior Accountant", "Junior Accountant"}));
        } else if (txtDepartment.getSelectedItem().equals("Manufacturing")) {

            txtDesignation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation", "Head Officer", "Checker", "Superviser", "Helper"}));
        } else if (txtDepartment.getSelectedItem().equals("Packing")) {

            txtDesignation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation", "Head Packing Officer", "Assembler", "Superviser", "Helper"}));
        } else if (txtDepartment.getSelectedItem().equals("Sales")) {

            txtDesignation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation", "Head Sale Officer", "Quality Man", "Dispatcher", "Helper"}));
        } else {

            txtDesignation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation"}));
        }
    }//GEN-LAST:event_txtDepartmentActionPerformed

    private void txtDepartmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepartmentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentKeyPressed

    private void txtDesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDesignationActionPerformed
        // TODO add your handling code here:
        txtDesignation.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtDesignationActionPerformed

    private void txtNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNICActionPerformed

    private void txtNICKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNICKeyPressed
        // TODO add your handling code here:
        txtNIC.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtNICKeyPressed

    private void txtContactNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtContactNoMouseClicked
        txtContactNo.setEditable(true);
    }//GEN-LAST:event_txtContactNoMouseClicked

    private void txtContactNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactNoActionPerformed

    private void txtContactNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactNoKeyPressed
        // TODO add your handling code here:
        String phoneNumber = txtContactNo.getText();

        int length = phoneNumber.length();
        char c = evt.getKeyChar();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            if (length < 10) {
                txtContactNo.setEditable(true);

            } else {
                txtContactNo.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                txtContactNo.setEditable(true);

            } else {
                txtContactNo.setEditable(false);
            }

        }
    }//GEN-LAST:event_txtContactNoKeyPressed

    private void txtContactNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactNoKeyReleased
        // TODO add your handling code here:
        String mobileNo = txtContactNo.getText();

        if (mobileNo.length() >= 1 && mobileNo.length() < 10) {
            txtContactNo.setBackground(new Color(255, 0, 51));
        } else {
            txtContactNo.setBackground(new Color(255, 255, 255));

        }
    }//GEN-LAST:event_txtContactNoKeyReleased

    private void txtAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdressActionPerformed

    private void txtAdressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdressKeyPressed
        // TODO add your handling code here:
        txtAdress.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtAdressKeyPressed

    private void txtFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFnameActionPerformed

    private void txtFnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFnameKeyPressed
        // TODO add your handling code here:
        txtFname.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtFnameKeyPressed

    private void btnAddEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmpActionPerformed
        String year = null;
        String casual = null;
        String annual = null;
        String medi = null;

        String gender = null;
        if (r_male.isSelected()) {
            gender = r_male.getText();
        } else if (r_female.isSelected()) {
            gender = r_female.getText();
        }
        String status = null;
        if (r_Active.isSelected()) {
            status = r_Active.getText();
        } else if (r_Inactive.isSelected()) {
            status = r_Inactive.getText();
        }

        if (txtNIC.getText().isEmpty()) {
            txtNIC.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The NIC NO field is empty !!");
        } else if (txtFname.getText().isEmpty()) {
            txtFname.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The First Name field is empty !!");
        } else if (txtLname.getText().isEmpty()) {
            txtLname.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Last Name field is empty !!");
        } else if (txtDOB.getText().equals("DD/MM/YYYY")) {
            txtDOB.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The  Date of Birth field is empty !!");
        } else if (txtAdress.getText().isEmpty()) {
            txtAdress.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Address1 field is empty !!");
        } else if (txtAdressline.getText().isEmpty()) {
            txtAdressline.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Address2 field is empty !!");
        } else if (txtContactNo.getText().isEmpty()) {
            txtContactNo.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Contact NO field is empty !!");
        } else if (txtEmail.getText().isEmpty()) {
            txtEmail.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Email field is empty !!");
        } else if (txtDepartment.getSelectedItem().equals("Select Department")) {
            txtDepartment.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please select the Department !!");
        } else if (txtDesignation.getSelectedItem().equals("Select Designation")) {
            txtDesignation.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please select the Designation !!");
        } else if (txteducationallevel.getSelectedItem().equals("Select Educational Level")) {
            txteducationallevel.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please select Educational Level !!");
        } else if (txtBasicSalary.getText().isEmpty()) {
            txtBasicSalary.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Basic Salary field is empty !!");
        } else if (gender == null) {
            r_male.setForeground(new java.awt.Color(255, 0, 51));
            r_female.setForeground(new java.awt.Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Select the Gender !!");
        } else if (status == null) {
            r_Active.setForeground(new java.awt.Color(255, 0, 51));
            r_Inactive.setForeground(new java.awt.Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Select the Status  !!");
        } else if (lblimage.getIcon() == null) {
            btnUpload.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Upload Profile Picture !!");
        } else if (lblbarcodepreview.getIcon() == null) {
            btngeneratebarcode.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "Please Genarate Barcode !!");
        } else {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure to add this record!!", "Add Record", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    String sql1 = "SELECT MAX(Casual_Leaves), MAX(Annual_Leaves), MAX(Medical_Leaves), Year FROM `leaveinformation`";
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst = (PreparedStatement) con.prepareStatement(sql1);
                    rs = (ResultSet) pst.executeQuery();
                    while (rs.next()) {
                        casual = rs.getString("MAX(Casual_Leaves)");
                        annual = rs.getString("MAX(Annual_Leaves)");
                        medi = rs.getString("MAX(Medical_Leaves)");
                        year = rs.getString("Year");

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }

                try {
                    String le = "INSERT INTO `leaveinformation` (EmpNo,NIC_No,Casual_Leaves,Annual_Leaves,Medical_Leaves,Year) VALUES (?,?,?,?,?,?)";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst = con.prepareStatement(le);

                    pst.setString(1, txtempno.getText());
                    pst.setString(2, txtNIC.getText());
                    pst.setString(3, casual);
                    pst.setString(4, annual);
                    pst.setString(5, medi);
                    pst.setString(6, year);

                    pst.executeUpdate();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }

                try {
                    inputstream = new FileInputStream(new File(imageFilePath));
                    barcodeinputstream = new FileInputStream(new File(barcodeFilePath));

                    String query = "INSERT INTO employee(EmpNo,NIC_No,First_Name,Last_Name,DOB,Address1,Address2,Contact_No,Email,Department,Designation,Educational_Level,Gender,Status,Basic_Salary,Profile_Pic,Barcode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst = con.prepareStatement(query);

                    pst.setString(1, txtempno.getText());
                    pst.setString(2, txtNIC.getText());
                    pst.setString(3, txtFname.getText());
                    pst.setString(4, txtLname.getText());
                    pst.setString(5, txtDOB.getText());
                    pst.setString(6, txtAdress.getText());
                    pst.setString(7, txtAdressline.getText());
                    pst.setString(8, txtContactNo.getText());
                    pst.setString(9, txtEmail.getText());
                    pst.setString(10, String.valueOf(txtDepartment.getSelectedItem()));
                    pst.setString(11, String.valueOf(txtDesignation.getSelectedItem()));
                    pst.setString(12, String.valueOf(txteducationallevel.getSelectedItem()));
                    pst.setString(13, gender);
                    pst.setString(14, status);
                    pst.setString(15, txtBasicSalary.getText());
                    pst.setBlob(16, inputstream);
                    pst.setBlob(17, barcodeinputstream);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Inserted Successfully ");

                    try {

                        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                        String sql = "SELECT MAX(EmpNo) FROM employee";
                        st = con.createStatement();
                        rs = st.executeQuery(sql);

                        if (rs.next()) {
                            x = rs.getString("MAX(EmpNo)");

                            if (x == (null)) {
                                txtempno.setText("E0001");
                            } else {
                                long id = Long.parseLong(rs.getString("MAX(EmpNo)").substring(1, rs.getString("MAX(EmpNo)").length()));
                                id++;
                                txtempno.setText("E" + String.format("%04d", id));
                            }

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    txtNIC.setText("");
                    txtFname.setText("");
                    txtLname.setText("");
                    txtDOB.setText(null);
                    if (txtDOB.getText().trim().equals("")) {
                        txtDOB.setText("DD/MM/YYYY");
                        txtDOB.setForeground(new Color(153, 153, 153));

                    }
                    txtDOB.setForeground(new Color(153, 153, 153));
                    txtAdress.setText("");
                    txtAdressline.setText("");
                    txtContactNo.setText("");
                    txtEmail.setText("");
                    txtDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Department", "Accounts", "Manufacturing", "Packing", "Sales"}));
                    txtDesignation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Designation"}));
                    txteducationallevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Educational Level", "Upto G.C.E O/Level", "Passed G.C.E O/Level", "Passed G.C.E A/Level", "Graduate"}));
                    r_male.setSelected(false);
                    r_female.setSelected(false);
                    r_Active.setSelected(false);
                    r_Inactive.setSelected(false);
                    r_female.setSelected(false);
                    lblimage.setIcon(null);
                    lblbarcodepreview.setIcon(null);
                    lblimagename.setText("");
                    txtBasicSalary.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
            }
        }
        tablelord();
    }//GEN-LAST:event_btnAddEmpActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
        txtEmail.setBackground(new Color(255, 255, 255));
         txtEmail.setBackground(new Color(255,255,255));
        
          txtEmail.setBackground(new Color(255,255,255));
        
     if(!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", txtEmail.getText()))) 
     {
         txtEmail.setBackground(new Color(255,0,51));  
     }
     else
     {
       txtEmail.setBackground(new Color(255,255,255));  
     }

    }//GEN-LAST:event_txtEmailKeyPressed

    private void btncamONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncamONActionPerformed
        // web camera on:

        btncamON.setEnabled(false);
        btncamOFF.setEnabled(true);
        btncapture.setEnabled(true);

        Thread t = new Thread() {
            @Override
            public void run() {
                wCamPanel.start();
            }
        };
        t.setDaemon(true);
        t.start();
        wCam.setViewSize(cs);
        wCamPanel.setFillArea(true);

        lblcaptureimage.setLayout(new FlowLayout());
        lblcaptureimage.add(wCamPanel);
    }//GEN-LAST:event_btncamONActionPerformed

    private void btncamOFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncamOFFActionPerformed
        // web cam off:
        btncamON.setEnabled(true);
        btncamOFF.setEnabled(false);
        btncapture.setEnabled(false);

        Thread t = new Thread() {
            @Override
            public void run() {
                wCamPanel.stop();
            }
        };
        t.setDaemon(false);
        t.start();
        wCamPanel.setFillArea(false);
    }//GEN-LAST:event_btncamOFFActionPerformed

    private void btncaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaptureActionPerformed
        // capture image:
        try {

            File file = new File(String.format("capture-%d.jpg", System.currentTimeMillis()));
            ImageIO.write(wCam.getImage(), "JPG", file);
            JOptionPane.showMessageDialog(this, "image saved on :\n" + file.getAbsolutePath(), "CamCap", 1);
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(this, "Image Not Saved :/n" + e.getMessage(), "Cam", 0);

        }
    }//GEN-LAST:event_btncaptureActionPerformed

    private void txtAdresslineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdresslineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdresslineActionPerformed

    private void txtAdresslineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdresslineKeyPressed
        // TODO add your handling code here:
        txtAdressline.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtAdresslineKeyPressed

    private void txteducationallevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txteducationallevelActionPerformed
        // TODO add your handling code here:
        txteducationallevel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txteducationallevelActionPerformed

    private void r_maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_maleActionPerformed
        // Gender Male:
        status = "Male";
        r_male.setSelected(true);
        r_female.setSelected(false);

        if (r_male.isSelected()) {
            r_male.setForeground(new java.awt.Color(0, 0, 0));
            r_female.setForeground(new java.awt.Color(0, 0, 0));
            r_female.setSelected(false);
        }
    }//GEN-LAST:event_r_maleActionPerformed

    private void r_femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_femaleActionPerformed
        // Gender Female:
        status = "Female";
        r_male.setSelected(false);
        r_female.setSelected(true);

        if (r_female.isSelected()) {
            r_female.setForeground(new java.awt.Color(0, 0, 0));
            r_male.setForeground(new java.awt.Color(0, 0, 0));
            r_male.setSelected(false);
        }
    }//GEN-LAST:event_r_femaleActionPerformed

    private void r_ActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_ActiveActionPerformed
        // status active:
        status = "Active";
        r_Active.setSelected(true);
        r_Inactive.setSelected(false);

        if (r_Active.isSelected()) {
            r_Active.setForeground(new java.awt.Color(0, 0, 0));
            r_Inactive.setForeground(new java.awt.Color(0, 0, 0));
            r_Inactive.setSelected(false);
        }
    }//GEN-LAST:event_r_ActiveActionPerformed

    private void r_InactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_InactiveActionPerformed
        // status Inactive:
        status = "Inactive";
        r_Active.setSelected(false);
        r_Inactive.setSelected(true);

        if (r_Inactive.isSelected()) {
            r_Inactive.setForeground(new java.awt.Color(0, 0, 0));
            r_Active.setForeground(new java.awt.Color(0, 0, 0));
            r_Active.setSelected(false);
        }
    }//GEN-LAST:event_r_InactiveActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // TODO add your handling code here:

        txtContactNo.setText(null);
        txtNIC.setText(null);
        txtEmail.setText(null);
        txtFname.setText(null);
        txtDepartment.setSelectedIndex(0);
        txtLname.setText(null);
        txtDesignation.setSelectedIndex(0);
        txteducationallevel.setSelectedIndex(0);
        txtAdress.setText(null);
        r_male.setSelected(false);
        r_female.setSelected(false);
        txtAdressline.setText(null);
        r_Active.setSelected(false);
        r_Inactive.setSelected(false);
        txtBasicSalary.setText("");
        lblbarcodepreview.setIcon(null);
        txtDOB.setText(null);
        if (txtDOB.getText().trim().equals("")) {
            txtDOB.setText("DD/MM/YYYY");
            txtDOB.setForeground(new Color(153, 153, 153));

        }
        txtDOB.setForeground(new Color(153, 153, 153));

        r_Active.setSelected(false);
        r_Inactive.setSelected(false);
        r_Active.setForeground(new java.awt.Color(0, 0, 0));
        r_Inactive.setForeground(new java.awt.Color(0, 0, 0));

        r_female.setSelected(false);
        r_male.setSelected(false);
        r_male.setForeground(new java.awt.Color(0, 0, 0));
        r_female.setForeground(new java.awt.Color(0, 0, 0));

        lblimage.setIcon(null);
        lblimagename.setText("");
    }//GEN-LAST:event_btnresetActionPerformed

    private void txtBasicSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBasicSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBasicSalaryActionPerformed

    private void txtBasicSalaryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBasicSalaryKeyPressed
        txtBasicSalary.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtBasicSalaryKeyPressed

    private void EMPtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EMPtableMouseClicked
        // TODO add your handling code here:
        boolean a = EMPtable.isEditing();
        if (a == false) {

        }

    }//GEN-LAST:event_EMPtableMouseClicked

    private void EMPtablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_EMPtablePropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_EMPtablePropertyChange

    private void btnGoBack4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGoBack4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGoBack4MouseClicked

    private void btnGoBack4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack4ActionPerformed
        // TODO add your handling code here:

        p1.setVisible(true);
        p1_AddPane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_DeletePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(false);

    }//GEN-LAST:event_btnGoBack4ActionPerformed

    private void btnGoBack4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGoBack4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGoBack4KeyPressed

    private void btnPrintempdetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintempdetailsActionPerformed
        // TODO add your handling code here:

        try {
            boolean print = EMPtable.print();
            if (!print) {
                JOptionPane.showMessageDialog(null, "Unable To Print !!..");
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_btnPrintempdetailsActionPerformed

    private void searchEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchEmployeeMouseClicked
        p1_AddPane.setVisible(false);
        p1_DeletePane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
        p1_SearchPane.setVisible(true);

        p1.setVisible(false);
    }//GEN-LAST:event_searchEmployeeMouseClicked

    private void p1_SearchPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1_SearchPaneMouseClicked

    }//GEN-LAST:event_p1_SearchPaneMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed

        try {
            HashMap a = new HashMap();
            a.put("EmpNo", searchEmp.getText());
            jPanel1.removeAll();
            jPanel1.repaint();
            jPanel1.revalidate();
            String jrxmlFile = "src\\com\\management\\srb\\reports\\emp_report.jrxml";

            JasperReport jreport = JasperCompileManager.compileReport(jrxmlFile);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, a, conn);
            JRViewer v = new JRViewer(jprint);
            jPanel1.setLayout(new BorderLayout());
            jPanel1.add(v);
        } catch (JRException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchActionPerformed

    private void btnGoBack5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack5ActionPerformed
        p1.setVisible(true);
        p1_AddPane.setVisible(false);
        p1_UpdatePane.setVisible(false);
        p1_DeletePane.setVisible(false);
        p1_SearchPane.setVisible(false);
        p1_EMP_ReportPane.setVisible(false);
    }//GEN-LAST:event_btnGoBack5ActionPerformed

    private void txtNIC2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNIC2KeyReleased
        // TODO add your handling code here:
        if(!(txtNIC2.getText().trim().matches("^[0-9]{9}[vV]$")))
{
txtNIC2.setBackground(new Color(255,0,51));
}else
    {
     txtNIC2.setBackground(new Color(255,255,255));   
    }   



    }//GEN-LAST:event_txtNIC2KeyReleased

    private void txtNICKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNICKeyReleased
        // TODO add your handling code here:
         txtNIC.setBackground(new Color(255,255,255));
    if(!(txtNIC.getText().trim().matches("^[0-9]{9}[vV]$")))
{
txtNIC.setBackground(new Color(255,0,51));
}else
    {
     txtNIC.setBackground(new Color(255,255,255));   
    } 
    }//GEN-LAST:event_txtNICKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable EMPtable;
    private javax.swing.JPanel addEmployee;
    private javax.swing.JButton btnAddEmp;
    private javax.swing.JButton btnAddEmp2;
    private javax.swing.JButton btnAddEmp3;
    private javax.swing.JButton btnGoBack;
    private javax.swing.JButton btnGoBack2;
    private javax.swing.JButton btnGoBack3;
    private javax.swing.JButton btnGoBack4;
    private javax.swing.JButton btnGoBack5;
    private javax.swing.JButton btnPrintempdetails;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton btnUpload2;
    private javax.swing.JButton btncamOFF;
    private javax.swing.JButton btncamOFF2;
    private javax.swing.JButton btncamON;
    private javax.swing.JButton btncamON2;
    private javax.swing.JButton btncapture;
    private javax.swing.JButton btncapture2;
    private javax.swing.JButton btngeneratebarcode;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnreset2;
    private javax.swing.JPanel deleteEmployee;
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JPanel header2;
    private javax.swing.JPanel header3;
    private javax.swing.JPanel header4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAdress2;
    private javax.swing.JLabel lblAdress3;
    private javax.swing.JLabel lblAdressline;
    private javax.swing.JLabel lblAdressline2;
    private javax.swing.JLabel lblAdressline3;
    private javax.swing.JLabel lblBasicSalary;
    private javax.swing.JLabel lblContactno;
    private javax.swing.JLabel lblContactno2;
    private javax.swing.JLabel lblContactno3;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDOB2;
    private javax.swing.JLabel lblDOB3;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblDepartment2;
    private javax.swing.JLabel lblDepartment3;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail2;
    private javax.swing.JLabel lblEmail3;
    private javax.swing.JLabel lblNic;
    private javax.swing.JLabel lblNic2;
    private javax.swing.JLabel lblNic3;
    private javax.swing.JLabel lblbarcode;
    private javax.swing.JLabel lblbarcode2;
    private javax.swing.JLabel lblbarcode3;
    private javax.swing.JLabel lblbarcodepreview;
    private javax.swing.JLabel lblbarcodepreview2;
    private javax.swing.JLabel lblbarcodepreview3;
    private javax.swing.JLabel lblcaptureimage;
    private javax.swing.JLabel lblcaptureimage2;
    private javax.swing.JLabel lblcapturephoto;
    private javax.swing.JLabel lblcapturephoto2;
    private javax.swing.JLabel lbldesignation;
    private javax.swing.JLabel lbldesignation2;
    private javax.swing.JLabel lbldesignation3;
    private javax.swing.JLabel lbleducation;
    private javax.swing.JLabel lbleducation2;
    private javax.swing.JLabel lbleducation3;
    private javax.swing.JLabel lblemono;
    private javax.swing.JLabel lblemono2;
    private javax.swing.JLabel lblemono3;
    private javax.swing.JLabel lblfname;
    private javax.swing.JLabel lblfname2;
    private javax.swing.JLabel lblfname3;
    private javax.swing.JLabel lblgender;
    private javax.swing.JLabel lblgender2;
    private javax.swing.JLabel lblgender3;
    private javax.swing.JLabel lblimage;
    private javax.swing.JLabel lblimage2;
    private javax.swing.JLabel lblimage3;
    private javax.swing.JLabel lblimagename;
    private javax.swing.JLabel lblimagename2;
    private javax.swing.JLabel lblimagename3;
    private javax.swing.JLabel lbllname;
    private javax.swing.JLabel lbllname2;
    private javax.swing.JLabel lbllname3;
    private javax.swing.JLabel lblpropic;
    private javax.swing.JLabel lblpropic2;
    private javax.swing.JLabel lblpropic3;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JLabel lblstatus2;
    private javax.swing.JLabel lblstatus3;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p1_AddPane;
    private javax.swing.JPanel p1_DeletePane;
    private javax.swing.JPanel p1_EMP_ReportPane;
    private javax.swing.JPanel p1_SearchPane;
    private javax.swing.JPanel p1_UpdatePane;
    private javax.swing.JRadioButton r_Active;
    private javax.swing.JRadioButton r_Active2;
    private javax.swing.JRadioButton r_Inactive;
    private javax.swing.JRadioButton r_Inactive2;
    private javax.swing.JRadioButton r_female;
    private javax.swing.JRadioButton r_female2;
    private javax.swing.JRadioButton r_male;
    private javax.swing.JRadioButton r_male2;
    private javax.swing.JPanel reportEmployee;
    private javax.swing.JButton search;
    private javax.swing.JTextField searchEmp;
    private javax.swing.JPanel searchEmployee;
    private javax.swing.JTextField txtAdress;
    private javax.swing.JTextField txtAdress2;
    private javax.swing.JTextField txtAdress3;
    private javax.swing.JTextField txtAdressline;
    private javax.swing.JTextField txtAdressline2;
    private javax.swing.JTextField txtAdressline3;
    private javax.swing.JTextField txtBasicSalary;
    private javax.swing.JTextField txtContactNo;
    private javax.swing.JTextField txtContactNo2;
    private javax.swing.JTextField txtContactNo3;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtDOB2;
    private javax.swing.JTextField txtDOB3;
    private javax.swing.JComboBox<String> txtDepartment;
    private javax.swing.JComboBox<String> txtDepartment2;
    private javax.swing.JComboBox<String> txtDesignation;
    private javax.swing.JComboBox<String> txtDesignation2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEmail3;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtFname2;
    private javax.swing.JTextField txtFname3;
    private javax.swing.JTextField txtLname;
    private javax.swing.JTextField txtLname2;
    private javax.swing.JTextField txtLname3;
    private javax.swing.JTextField txtNIC;
    private javax.swing.JTextField txtNIC2;
    private javax.swing.JTextField txtNIC3;
    private javax.swing.JTextField txtdepartment3;
    private javax.swing.JTextField txtdesignation3;
    private javax.swing.JComboBox<String> txteducationallevel;
    private javax.swing.JComboBox<String> txteducationallevel2;
    private javax.swing.JTextField txteducationallevel3;
    private javax.swing.JTextField txtempno;
    private javax.swing.JTextField txtempno2;
    private javax.swing.JTextField txtempno3;
    private javax.swing.JTextField txtgender3;
    private javax.swing.JTextField txtstatus3;
    private javax.swing.JPanel updateEmployee;
    // End of variables declaration//GEN-END:variables
}
