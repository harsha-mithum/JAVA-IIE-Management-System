/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.maindashboad;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Avishka
 */
public final class usermenu extends javax.swing.JFrame {
  
    void userid(String user) {
        useridlbl.setText(user);
    }
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form usernmenu
     */
    public usermenu() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));

        userdplbl();
        empdetails();
       
      

        showDate();
        showTime();

    }

    

    void username(String name) {
        usernamelbl.setText(name);
    }

    void userdesignation(String designation) {
        userdesignationlbl.setText(designation);
    }

    void userdplbl() {
        try {
            String sql = "SELECT Profile_Pic FROM employee where EmpNo='" + useridlbl.getText() + "' ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(sql);
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                byte[] uimg = rs.getBytes("Profile_Pic");
                ImageIcon uproimage = new ImageIcon(uimg);
                Image uim = uproimage.getImage();
                Image umyImg = uim.getScaledInstance(90, 90, WIDTH);
                ImageIcon unewImage = new ImageIcon(umyImg);
                userdp.setIcon(unewImage);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    void empdetails() {
        try {
            String fname = null;
            String lname = null;
            String fulname = null;
            String sql = "SELECT EmpNo,NIC_No,First_Name,Last_Name,DOB,Address1,Address2,Contact_No,Email,Department,Designation,Educational_Level,Gender,Status,Basic_Salary FROM employee where EmpNo='" + useridlbl.getText() + "' ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(sql);
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                empno.setText(rs.getString("EmpNo"));
                nicno.setText(rs.getString("NIC_No"));
                fname = (rs.getString("First_Name"));
                lname = (rs.getString("Last_Name"));
                fulname = (fname + " " + lname);
                fullname.setText(fulname);
                dob.setText(rs.getString("DOB"));
                address1.setText(rs.getString("Address1"));
                address2.setText(rs.getString("Address2"));
                contactno.setText(rs.getString("Contact_No"));
                email.setText(rs.getString("Email"));
                department.setText(rs.getString("Department"));
                designation.setText(rs.getString("Designation"));
                educationallevel.setText(rs.getString("Educational_Level"));
                gender.setText(rs.getString("Gender"));
                status.setText(rs.getString("Status"));
                basicsalary.setText(rs.getString("Basic_Salary"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void salSummery() {
        try {
            String summ = "SELECT OT,Medical,Bonus,Other,Total_Deductions,Deduction_Details,NetPay,Payment_Status FROM `salary_payments` WHERE EmpNo='" + useridlbl.getText() + "' AND`Year`='" + year.getText() + "' AND`Month`='" + month.getSelectedItem() + "'  ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(summ);
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                otsalary.setText(rs.getString("OT"));
                bonus.setText(rs.getString("Bonus"));
                medical.setText(rs.getString("Medical"));
                other.setText(rs.getString("Other"));
                deduction.setText(rs.getString("Total_Deductions"));
                reson.setText(rs.getString("Deduction_Details"));
                netpay.setText(rs.getString("NetPay"));
                paymentstatus.setText(rs.getString("Payment_Status"));

            }
             else
            {
                otsalary.setText("0.00");
                bonus.setText("0.00");
                medical.setText("0.00");
                other.setText("0.00");
                deduction.setText("0.00");
                reson.setText("No Reson");
                netpay.setText("0.00");
                paymentstatus.setText("Payment Not Submitted");
                JOptionPane.showMessageDialog(this, "Payment Not Submited on "+month.getSelectedItem());
              
                }
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }
    }

    private void leaveSummery() {
        try {
            String summ = "SELECT Casual_Leaves,Annual_Leaves,Medical_Leaves,Additional_Leaves FROM `leaveinformation` WHERE EmpNo='" + useridlbl.getText() + "' AND`Year`='" + year.getText() + "'  ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(summ);
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                casualleaves.setText(rs.getString("Casual_Leaves"));
                anualleaves.setText(rs.getString("Annual_Leaves"));
                medicalleaves.setText(rs.getString("Medical_Leaves"));
                additionalleaves.setText(rs.getString("Additional_Leaves"));

            }
            else {
                casualleaves.setText("0");
                anualleaves.setText("0");
                medicalleaves.setText("0");
                additionalleaves.setText("0");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }
    }

    private void attendSummery() {
        try {
            String summ = "SELECT Working_Hours,OverTimes,Arrivals,Absents,Half_Days,Full_Days FROM `attendance_summery` WHERE EmpNo='" + useridlbl.getText() + "' AND`Year`='" + year.getText() + "' AND`Month`='" + month.getSelectedItem() + "'  ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(summ);
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                workhours.setText(rs.getString("Working_Hours"));
                ottime.setText(rs.getString("OverTimes"));
                arrivals.setText(rs.getString("Arrivals"));
                absent.setText(rs.getString("Absents"));
                halfday.setText(rs.getString("Half_Days"));
                fullday.setText(rs.getString("Full_Days"));

            }
            else {
                 workhours.setText("0");
                ottime.setText("0");
                arrivals.setText("0");
                absent.setText("0");
                halfday.setText("0");
                fullday.setText("0");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }
    }

    void showDate() {
        Date d = new Date();
        SimpleDateFormat s;
        s = new SimpleDateFormat("yyyy-MM-dd");
        datelable2.setText(s.format(d));

    }

    void showTime() {
        new Timer(0, (ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("H:mm:ss");
            timelable.setText(s.format(d));
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblminimize = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        basicsalary = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        paymentstatus = new javax.swing.JLabel();
        netpay = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bonus = new javax.swing.JLabel();
        other = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        otsalary = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        workhours = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        ottime = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        arrivals = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        absent = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        fullday = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        medical = new javax.swing.JLabel();
        halfday = new javax.swing.JLabel();
        additionalleaves = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        reson = new javax.swing.JLabel();
        deduction = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        empno = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        nicno = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        dob = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        address1 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        address2 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        contactno = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        department = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        anualleaves = new javax.swing.JLabel();
        casualleaves = new javax.swing.JLabel();
        medicalleaves = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        designation = new javax.swing.JLabel();
        educationallevel = new javax.swing.JLabel();
        gender = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        year = new javax.swing.JTextField();
        month = new javax.swing.JComboBox<>();
        userdp = new javax.swing.JLabel();
        useridlbl = new javax.swing.JLabel();
        usernamelbl = new javax.swing.JLabel();
        userdesignationlbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        userlogout = new javax.swing.JButton();
        timelable = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        datelable2 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.0F);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblminimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/minimize.png"))); // NOI18N
        lblminimize.setPreferredSize(new java.awt.Dimension(25, 25));
        lblminimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblminimizeMouseClicked(evt);
            }
        });
        getContentPane().add(lblminimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/cancel1.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, -1, -1));

        basicsalary.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        basicsalary.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(basicsalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 630, 160, 20));

        jLabel51.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Basic Salary");
        getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 630, -1, -1));

        jLabel23.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel23.setText("Net Pay");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, -1, -1));

        jLabel19.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel19.setText("Payment Status");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 480, -1, -1));

        paymentstatus.setBackground(new java.awt.Color(153, 255, 255));
        paymentstatus.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentstatus.setText("Payment Not Submitted");
        paymentstatus.setOpaque(true);
        getContentPane().add(paymentstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, 170, -1));

        netpay.setBackground(new java.awt.Color(153, 255, 255));
        netpay.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        netpay.setText("0.00");
        netpay.setOpaque(true);
        getContentPane().add(netpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 450, 80, -1));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setText("Month");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setText("Search Monthly Details");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 210, 20));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel6.setText("Year");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, -1, -1));

        bonus.setBackground(new java.awt.Color(153, 255, 255));
        bonus.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        bonus.setText("0.00");
        bonus.setOpaque(true);
        getContentPane().add(bonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, 80, -1));

        other.setBackground(new java.awt.Color(153, 255, 255));
        other.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        other.setText("0.00");
        other.setOpaque(true);
        getContentPane().add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 80, -1));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel10.setText("Budgetary");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, -1, -1));

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setText("OT Amount");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, -1, -1));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setText("Attendance");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, -1, -1));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setText("Other");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, -1, -1));

        otsalary.setBackground(new java.awt.Color(153, 255, 255));
        otsalary.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        otsalary.setText("0.00");
        otsalary.setOpaque(true);
        getContentPane().add(otsalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 80, -1));

        jLabel113.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel113.setText("Work Hours :");
        getContentPane().add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 550, -1, -1));

        workhours.setBackground(new java.awt.Color(153, 255, 255));
        workhours.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        workhours.setText("0");
        workhours.setOpaque(true);
        getContentPane().add(workhours, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 550, 50, -1));

        jLabel114.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel114.setText("Over Times :");
        getContentPane().add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 580, -1, -1));

        ottime.setBackground(new java.awt.Color(153, 255, 255));
        ottime.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        ottime.setText("0");
        ottime.setOpaque(true);
        getContentPane().add(ottime, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 580, 50, 20));

        jLabel115.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel115.setText("Arrivals :");
        getContentPane().add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 610, -1, -1));

        arrivals.setBackground(new java.awt.Color(153, 255, 255));
        arrivals.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        arrivals.setText("0");
        arrivals.setOpaque(true);
        getContentPane().add(arrivals, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 610, 50, -1));

        jLabel116.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel116.setText("Absents :");
        getContentPane().add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 640, -1, -1));

        absent.setBackground(new java.awt.Color(153, 255, 255));
        absent.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        absent.setText("0");
        absent.setOpaque(true);
        getContentPane().add(absent, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 640, 50, 20));

        jLabel117.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel117.setText("Full Days :");
        getContentPane().add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 670, -1, -1));

        fullday.setBackground(new java.awt.Color(153, 255, 255));
        fullday.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        fullday.setText("0");
        fullday.setOpaque(true);
        getContentPane().add(fullday, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 670, 50, 20));

        jLabel118.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel118.setText("Half Days :");
        getContentPane().add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 700, -1, -1));

        medical.setBackground(new java.awt.Color(153, 255, 255));
        medical.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        medical.setText("0.00");
        medical.setOpaque(true);
        getContentPane().add(medical, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 80, -1));

        halfday.setBackground(new java.awt.Color(153, 255, 255));
        halfday.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        halfday.setText("0");
        halfday.setOpaque(true);
        getContentPane().add(halfday, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 700, 50, -1));

        additionalleaves.setBackground(new java.awt.Color(153, 255, 255));
        additionalleaves.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        additionalleaves.setText("0");
        additionalleaves.setOpaque(true);
        getContentPane().add(additionalleaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 270, 40, -1));

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel17.setText("Additional Leaves");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, -1, -1));

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel18.setText("Reason");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, -1, -1));

        reson.setBackground(new java.awt.Color(153, 255, 255));
        reson.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        reson.setText("No Reason");
        reson.setOpaque(true);
        getContentPane().add(reson, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 420, 80, -1));

        deduction.setBackground(new java.awt.Color(153, 255, 255));
        deduction.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deduction.setText("0.00");
        deduction.setOpaque(true);
        getContentPane().add(deduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 80, -1));

        jLabel21.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel21.setText("Deduction Amount");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, -1, -1));

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Full Name");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        fullname.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        fullname.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 160, 20));

        empno.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        empno.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(empno, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 160, 20));

        jLabel25.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Emp No");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        jLabel26.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("NIC No");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));

        nicno.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nicno.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(nicno, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 160, 20));

        jLabel28.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("DOB");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        dob.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        dob.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 160, 20));

        jLabel30.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Address1");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, -1));

        address1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        address1.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(address1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 160, 20));

        jLabel32.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Address2");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, -1, -1));

        address2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        address2.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(address2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 160, 20));

        jLabel34.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Contact No");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        contactno.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        contactno.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(contactno, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 160, 20));

        jLabel36.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Email");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, -1, -1));

        email.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 160, 20));

        jLabel38.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Department");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, -1, -1));

        department.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        department.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(department, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 160, 20));

        jLabel40.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel40.setText("Medical Leaves");
        getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 360, -1, -1));

        jLabel41.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel41.setText("Casual Leaves");
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 330, -1, -1));

        jLabel42.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel42.setText("Annual Leaves");
        getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, -1, -1));

        anualleaves.setBackground(new java.awt.Color(153, 255, 255));
        anualleaves.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        anualleaves.setText("0");
        anualleaves.setOpaque(true);
        getContentPane().add(anualleaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 300, 40, -1));

        casualleaves.setBackground(new java.awt.Color(153, 255, 255));
        casualleaves.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        casualleaves.setText("0");
        casualleaves.setOpaque(true);
        getContentPane().add(casualleaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 330, 40, -1));

        medicalleaves.setBackground(new java.awt.Color(153, 255, 255));
        medicalleaves.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        medicalleaves.setText("0");
        medicalleaves.setOpaque(true);
        getContentPane().add(medicalleaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 360, 40, -1));

        jLabel47.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Status");
        getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 590, -1, -1));

        jLabel48.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("Gender");
        getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, -1, -1));

        jLabel49.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Educational Level");
        getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, -1, -1));

        jLabel50.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Designation");
        getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, -1, -1));

        designation.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        designation.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(designation, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 160, 20));

        educationallevel.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        educationallevel.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(educationallevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, 160, 20));

        gender.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        gender.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, 160, 20));

        status.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        status.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 590, 160, 20));

        jLabel46.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 340, 670));

        year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearKeyPressed(evt);
            }
        });
        getContentPane().add(year, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, 70, -1));

        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        month.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monthMouseClicked(evt);
            }
        });
        getContentPane().add(month, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 120, -1));

        userdp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(userdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 90, 90));

        useridlbl.setBackground(new java.awt.Color(255, 255, 255));
        useridlbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        useridlbl.setForeground(new java.awt.Color(255, 255, 255));
        useridlbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        useridlbl.setText("admin");
        useridlbl.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                useridlblComponentAdded(evt);
            }
        });
        useridlbl.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                useridlblAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        useridlbl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                useridlblFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                useridlblFocusLost(evt);
            }
        });
        useridlbl.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                useridlblInputMethodTextChanged(evt);
            }
        });
        getContentPane().add(useridlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 130, 20));

        usernamelbl.setBackground(new java.awt.Color(255, 255, 255));
        usernamelbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        usernamelbl.setForeground(new java.awt.Color(255, 255, 255));
        usernamelbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernamelbl.setText("name");
        getContentPane().add(usernamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 130, 20));

        userdesignationlbl.setBackground(new java.awt.Color(255, 255, 255));
        userdesignationlbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        userdesignationlbl.setForeground(new java.awt.Color(255, 255, 255));
        userdesignationlbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userdesignationlbl.setText("designation");
        getContentPane().add(userdesignationlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, 20));

        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 255, 255), 2, true));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 170, 190));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel5.setText("Log Out");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 660, -1, 20));

        userlogout.setBackground(new java.awt.Color(255, 153, 153));
        userlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/sign_out.png"))); // NOI18N
        userlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlogoutActionPerformed(evt);
            }
        });
        getContentPane().add(userlogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 180, 80));

        timelable.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        timelable.setForeground(new java.awt.Color(255, 255, 255));
        timelable.setText("Time :");
        getContentPane().add(timelable, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 140, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dashboard");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 200, 60));

        datelable2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        datelable2.setForeground(new java.awt.Color(255, 255, 255));
        datelable2.setText("Date :");
        getContentPane().add(datelable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 140, 30));

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 220, -1, -1));

        jLabel55.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 460, 670));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/sr boa food Main form design 2 1024 768.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 768));

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        double i;
        for (i = 0.0; i <= 1.0; i = i + 0.1) {
            String val = i + "";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try {
                Thread.sleep(50);

            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void useridlblFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_useridlblFocusGained
 empdetails();

        try {
            String sql = "SELECT Profile_Pic FROM employee where EmpNo='" + useridlbl.getText() + "' ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst = (PreparedStatement) con.prepareStatement(sql);
            rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                byte[] uimg = rs.getBytes("Profile_Pic");
                ImageIcon uproimage = new ImageIcon(uimg);
                Image uim = uproimage.getImage();
                Image umyImg = uim.getScaledInstance(userdp.getWidth(), userdp.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon unewImage = new ImageIcon(umyImg);
                userdp.setIcon(unewImage);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_useridlblFocusGained

    private void userlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlogoutActionPerformed
        if (userlogout.isSelected()) {
            userlogout.setBackground(new Color(160, 0, 51));

        }

        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Log Out !!", "Log Out", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            this.dispose();
            new userlogin().setVisible(true);
        }
    }//GEN-LAST:event_userlogoutActionPerformed

    private void monthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monthMouseClicked
        month.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_monthMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
 if(year.getText().isEmpty())
        {
            year.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please enter year !!");
        }
        else if(month.getSelectedItem().equals("Select Month"))
        {
            month.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please Select Month !!");
        }  
 else
        {
        salSummery();
        leaveSummery();
        attendSummery();
        }
    }//GEN-LAST:event_searchActionPerformed

    private void useridlblInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_useridlblInputMethodTextChanged

    }//GEN-LAST:event_useridlblInputMethodTextChanged

    private void useridlblFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_useridlblFocusLost

    }//GEN-LAST:event_useridlblFocusLost

    private void useridlblComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_useridlblComponentAdded
  // TODO add your handling code here:
    }//GEN-LAST:event_useridlblComponentAdded

    private void useridlblAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_useridlblAncestorAdded
empdetails();
userdplbl();
    }//GEN-LAST:event_useridlblAncestorAdded

    private void yearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearKeyPressed
       year.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_yearKeyPressed

    private void lblminimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblminimizeMouseClicked
        // TODO add your handling code here:
        this.setExtendedState(usermenu.ICONIFIED);
    }//GEN-LAST:event_lblminimizeMouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(usermenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usermenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usermenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usermenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usermenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel absent;
    private javax.swing.JLabel additionalleaves;
    private javax.swing.JLabel address1;
    private javax.swing.JLabel address2;
    private javax.swing.JLabel anualleaves;
    private javax.swing.JLabel arrivals;
    private javax.swing.JLabel basicsalary;
    private javax.swing.JLabel bonus;
    private javax.swing.JLabel casualleaves;
    private javax.swing.JLabel contactno;
    private javax.swing.JLabel datelable2;
    private javax.swing.JLabel deduction;
    private javax.swing.JLabel department;
    private javax.swing.JLabel designation;
    private javax.swing.JLabel dob;
    private javax.swing.JLabel educationallevel;
    private javax.swing.JLabel email;
    private javax.swing.JLabel empno;
    private javax.swing.JLabel fullday;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel gender;
    private javax.swing.JLabel halfday;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblminimize;
    private javax.swing.JLabel medical;
    private javax.swing.JLabel medicalleaves;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JLabel netpay;
    private javax.swing.JLabel nicno;
    private javax.swing.JLabel other;
    private javax.swing.JLabel otsalary;
    private javax.swing.JLabel ottime;
    private javax.swing.JLabel paymentstatus;
    private javax.swing.JLabel reson;
    private javax.swing.JButton search;
    private javax.swing.JLabel status;
    private javax.swing.JLabel timelable;
    private javax.swing.JLabel userdesignationlbl;
    private javax.swing.JLabel userdp;
    private javax.swing.JLabel useridlbl;
    private javax.swing.JButton userlogout;
    private javax.swing.JLabel usernamelbl;
    private javax.swing.JLabel workhours;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables
}
