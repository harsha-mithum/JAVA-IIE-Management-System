/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.employee1;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Avishka
 */
public class Attendance extends javax.swing.JPanel implements Runnable, ThreadFactory{
    
      Connection con = null;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st=null;
    
    
     private final Dimension bs =new Dimension (296,177);
    private final Dimension es =WebcamResolution.QVGA.getSize();
    private final Webcam barWebCam=Webcam.getDefault();
    private final WebcamPanel barWebCamPanel=new WebcamPanel(barWebCam,bs,false);
    
    
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    /**
     * Creates new form Attendance
     */
     String status;
    String imageFilePath ;
    String imageFileName ;
    InputStream inputstream ;
    InputStream upinputstream ;
    InputStream barcodeinputstream ;
    String barcodeFilePath ;
    String captureFilePath ;
    String gender ;
    String upimageFilePath;
    String upimageFileName;
    String x;
    String casual ;
    String annual ;
    String medical ;
    String year ;
    String emp;
    String NIC;
    int days;
    int casual1 ;
    int annual1;
    int medical1;
    String leave;
    
    
    
    public Attendance() {
        initComponents();
       
       p5_Attendance.setVisible(false);
       p5_AttendanceSummery.setVisible(false);
     
       p5.setVisible(true); 
        
        barWebCam.setViewSize(es);
       barWebCamPanel.setFPSDisplayed(true);
       barWebCamPanel.setPreferredSize(es);
        cam.add(barWebCamPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        barWebCamPanel.setFillArea(true);
         
        cam.setLayout(new FlowLayout());
        cam.add(barWebCamPanel); 
       
        
         showDate();
        showTime();
    }
    
     void showDate() {
       Date d = new Date();
       SimpleDateFormat s;
       s = new SimpleDateFormat("yyyy-MM-dd");
     
       attenddate.setText(s.format(d));
       
   }
       void showTime() {
        new Timer(0, (ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("H:mm:ss");
           
            attendandleavetime.setText(s.format(d));
         }).start();
            
       }
    
    
    
    
     public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (barWebCam.isOpen()) {
                if ((image = barWebCam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }

            if (result != null) {
                empid.setText(result.getText());
                
              String fname1,lname1;   
                try {
            String sql ="SELECT First_Name,Last_Name,Profile_Pic FROM employee where EmpNo='"+empid.getText()+"'";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);           
            rs= (ResultSet) pst.executeQuery();
           
            if(rs.next()) {
                  fname1=(rs.getString("First_Name"));
                lname1=(rs.getString("Last_Name"));
                name.setText(fname1+" "+lname1);
                byte[] img = rs.getBytes("Profile_Pic");
                ImageIcon proimage = new ImageIcon(img);
                Image im = proimage.getImage();
                Image myImg = im.getScaledInstance(dp.getWidth(), dp.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                dp.setIcon(newImage);
                  }
            else{
                JOptionPane.showMessageDialog(null, "No Data");
                
                 empid.setText("");
                name.setText("");
                dp.setIcon(null);
            }
       
                 }
        
                 
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
          
            
            }
        } while (true);
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    } 
    
  private void attendanceSummerytable(){
  try {
            String summ ="SELECT * FROM `attendance_summery` WHERE `Year`=? AND`Month`=?  ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(summ);
            
            pst.setString(1, AttendanceSummeryPaneYear.getText());
            pst.setString(2, String.valueOf(AttendanceSummeryPaneMonth.getSelectedItem()));
            rs= (ResultSet) pst.executeQuery();
            AttendanceSummeryTable.setModel(DbUtils.resultSetToTableModel(rs));
            
     }
         
          catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
                     AttendanceSummeryTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
                     AttendanceSummeryTable.getTableHeader().setOpaque(false);
                     AttendanceSummeryTable.getTableHeader().setBackground(new Color(32,136,203));
                     AttendanceSummeryTable.getTableHeader().setForeground(new Color(0,0,0));
                     AttendanceSummeryTable.setRowHeight(25);
      AttendanceSummeryTable.getColumnModel().getColumn(1).setPreferredWidth(100);
      AttendanceSummeryTable.getColumnModel().getColumn(2).setPreferredWidth(140);
      AttendanceSummeryTable.getColumnModel().getColumn(3).setPreferredWidth(140);
      AttendanceSummeryTable.getColumnModel().getColumn(4).setPreferredWidth(140);
      AttendanceSummeryTable.getColumnModel().getColumn(5).setPreferredWidth(140);
      AttendanceSummeryTable.getColumnModel().getColumn(6).setPreferredWidth(70);
      AttendanceSummeryTable.getColumnModel().getColumn(7).setPreferredWidth(100);
      AttendanceSummeryTable.getColumnModel().getColumn(8).setPreferredWidth(70);
      AttendanceSummeryTable.getColumnModel().getColumn(9).setPreferredWidth(70);
      AttendanceSummeryTable.getColumnModel().getColumn(10).setPreferredWidth(70);
      AttendanceSummeryTable.getColumnModel().getColumn(11).setPreferredWidth(70);
      AttendanceSummeryTable.getColumnModel().getColumn(12).setPreferredWidth(100);
      AttendanceSummeryTable.getColumnModel().getColumn(13).setPreferredWidth(100);
  
  
  }
          
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p5 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        attendanceMark = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        attendanceSummery = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        p5_Attendance = new javax.swing.JPanel();
        cam = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        getatten = new javax.swing.JButton();
        dp = new javax.swing.JLabel();
        addlist = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        camon1 = new javax.swing.JButton();
        camoff1 = new javax.swing.JButton();
        attenddate = new javax.swing.JLabel();
        attendandleavetime = new javax.swing.JLabel();
        getleave = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchAttend = new javax.swing.JButton();
        attendDateChooser1 = new com.toedter.calendar.JDateChooser();
        attendDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        searchAttendId = new javax.swing.JTextField();
        attendprint = new javax.swing.JButton();
        attotlbl = new javax.swing.JLabel();
        attarivallbl = new javax.swing.JLabel();
        attabsentlbl = new javax.swing.JLabel();
        attfulldaylbl = new javax.swing.JLabel();
        atthulfdaylbl = new javax.swing.JLabel();
        atthourslbl = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        attAdd = new javax.swing.JButton();
        jLabel119 = new javax.swing.JLabel();
        btnGoBack14 = new javax.swing.JButton();
        empid = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        p5_AttendanceSummery = new javax.swing.JPanel();
        header12 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        btnGoBack13 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        AttendanceSummeryTable = new javax.swing.JTable();
        AttendanceSearchbtn = new javax.swing.JButton();
        AttendanceSummeryPaneYear = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        AttendanceSummeryPaneMonth = new javax.swing.JComboBox<>();
        jLabel122 = new javax.swing.JLabel();
        AttendanceSummeryPrintbtn = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p5.setBackground(new java.awt.Color(180, 170, 255));
        p5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p5.setPreferredSize(new java.awt.Dimension(810, 712));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel61.setText("Attendance Section");

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/information.png"))); // NOI18N
        jLabel62.setToolTipText("");

        attendanceMark.setBackground(new java.awt.Color(255, 255, 255));
        attendanceMark.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        attendanceMark.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        attendanceMark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attendanceMarkMouseClicked(evt);
            }
        });
        attendanceMark.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/attendance mark.png"))); // NOI18N
        attendanceMark.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 130, 130));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(204, 0, 204));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Attendance Mark");
        attendanceMark.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 20));

        attendanceSummery.setBackground(new java.awt.Color(255, 255, 255));
        attendanceSummery.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        attendanceSummery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attendanceSummeryMouseClicked(evt);
            }
        });
        attendanceSummery.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/attendance3.png"))); // NOI18N
        attendanceSummery.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 120, 120));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 204, 204));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Attendance Summery");
        attendanceSummery.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Welcome to EMP System");

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 0, 1, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout p5Layout = new javax.swing.GroupLayout(p5);
        p5.setLayout(p5Layout);
        p5Layout.setHorizontalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p5Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(attendanceMark, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(attendanceSummery, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(p5Layout.createSequentialGroup()
                            .addComponent(jLabel61)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(164, 164, 164))
        );
        p5Layout.setVerticalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62))
                .addGap(101, 101, 101)
                .addGroup(p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(attendanceMark, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attendanceSummery, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(p5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        p5_Attendance.setBackground(new java.awt.Color(200, 210, 255));
        p5_Attendance.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p5_Attendance.setPreferredSize(new java.awt.Dimension(810, 712));

        cam.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel109.setText("Emp_Id :-");

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel110.setText("Name    :-");

        getatten.setBackground(new java.awt.Color(51, 255, 51));
        getatten.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getatten.setText("Get Attendance");
        getatten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getattenActionPerformed(evt);
            }
        });

        dp.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        addlist.setBackground(new java.awt.Color(0, 0, 102));
        addlist.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addlist.setForeground(new java.awt.Color(255, 255, 255));
        addlist.setText("AddList");
        addlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addlistActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 153, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        camon1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        camon1.setText("Camera ON");
        camon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camon1ActionPerformed(evt);
            }
        });

        camoff1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        camoff1.setText("Camera Off");
        camoff1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camoff1ActionPerformed(evt);
            }
        });

        attenddate.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        attenddate.setText("Date");

        attendandleavetime.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        attendandleavetime.setText("Time");

        getleave.setBackground(new java.awt.Color(204, 102, 0));
        getleave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getleave.setText("Get Leave");
        getleave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getleaveActionPerformed(evt);
            }
        });

        jScrollPane7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jTable1.setBackground(new java.awt.Color(122, 204, 255));
        jTable1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 204)));
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setFocusable(false);
        jTable1.setSelectionBackground(new java.awt.Color(102, 40, 204));
        jTable1.setShowVerticalLines(false);
        jScrollPane7.setViewportView(jTable1);

        searchAttend.setBackground(new java.awt.Color(0, 153, 204));
        searchAttend.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchAttend.setText("Search");
        searchAttend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAttendActionPerformed(evt);
            }
        });

        attendDateChooser1.setBackground(new java.awt.Color(51, 51, 51));
        attendDateChooser1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 51)));
        attendDateChooser1.setDateFormatString("yyyy-MM-dd");
        attendDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        attendDateChooser2.setBackground(new java.awt.Color(51, 51, 51));
        attendDateChooser2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 51)));
        attendDateChooser2.setDateFormatString("yyyy-MM-dd");
        attendDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setText("From");

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setText("To");

        searchAttendId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchAttendId.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        searchAttendId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAttendIdActionPerformed(evt);
            }
        });

        attendprint.setBackground(new java.awt.Color(0, 0, 102));
        attendprint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        attendprint.setForeground(new java.awt.Color(255, 255, 255));
        attendprint.setText("Print");
        attendprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendprintActionPerformed(evt);
            }
        });

        attotlbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        attotlbl.setText("ot");

        attarivallbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        attarivallbl.setText("arrival");

        attabsentlbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        attabsentlbl.setText("absent");

        attfulldaylbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        attfulldaylbl.setText("fullday");

        atthulfdaylbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        atthulfdaylbl.setText("halfday");

        atthourslbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        atthourslbl.setText("hours");

        jLabel113.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel113.setText("Work Hours :");

        jLabel114.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel114.setText("Over Times :");

        jLabel115.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel115.setText("Arrivals :");

        jLabel116.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel116.setText("Absents :");

        jLabel117.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel117.setText("Full Days :");

        jLabel118.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel118.setText("Half Days :");

        attAdd.setBackground(new java.awt.Color(0, 0, 102));
        attAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        attAdd.setForeground(new java.awt.Color(255, 255, 255));
        attAdd.setText("Add");
        attAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attAddActionPerformed(evt);
            }
        });

        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel119.setText("EmpNo :");

        btnGoBack14.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack14.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack14.setText("Go Back");
        btnGoBack14.setBorderPainted(false);
        btnGoBack14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack14ActionPerformed(evt);
            }
        });

        empid.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        empid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        name.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        name.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        javax.swing.GroupLayout p5_AttendanceLayout = new javax.swing.GroupLayout(p5_Attendance);
        p5_Attendance.setLayout(p5_AttendanceLayout);
        p5_AttendanceLayout.setHorizontalGroup(
            p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                .addComponent(getatten, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(getleave, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                .addComponent(btnGoBack14, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(attAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(attendprint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))))
                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(camon1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(camoff1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel110)
                                        .addGap(8, 8, 8)
                                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(dp, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel109)
                                        .addGap(6, 6, 6)
                                        .addComponent(empid, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(addlist, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                        .addComponent(cam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(attenddate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(attendandleavetime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel119)
                                            .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                                    .addComponent(searchAttendId)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(searchAttend, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                                    .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(attendDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(40, 40, 40)
                                                    .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(attendDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p5_AttendanceLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addComponent(jLabel113)
                                        .addGap(14, 14, 14)
                                        .addComponent(atthourslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addComponent(jLabel114)
                                        .addGap(20, 20, 20)
                                        .addComponent(attotlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addComponent(jLabel115)
                                        .addGap(38, 38, 38)
                                        .addComponent(attarivallbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addComponent(jLabel116)
                                        .addGap(40, 40, 40)
                                        .addComponent(attabsentlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addComponent(jLabel117)
                                        .addGap(33, 33, 33)
                                        .addComponent(attfulldaylbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                        .addComponent(jLabel118)
                                        .addGap(32, 32, 32)
                                        .addComponent(atthulfdaylbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(29, 29, 29)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p5_AttendanceLayout.setVerticalGroup(
            p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(cam, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(camon1)
                            .addComponent(camoff1))
                        .addGap(7, 7, 7)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(empid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(10, 10, 10)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p5_AttendanceLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel110))
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(dp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(attenddate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(attendandleavetime, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getatten, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(getleave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(btnGoBack14)
                        .addGap(19, 19, 19))
                    .addGroup(p5_AttendanceLayout.createSequentialGroup()
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel111)
                            .addComponent(addlist)
                            .addComponent(jLabel112))
                        .addGap(1, 1, 1)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(attendDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(attendDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel119)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchAttendId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchAttend))
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel113)
                            .addComponent(atthourslbl))
                        .addGap(15, 15, 15)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel114)
                            .addComponent(attotlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel115)
                            .addComponent(attarivallbl))
                        .addGap(15, 15, 15)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel116)
                            .addComponent(attabsentlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel117)
                            .addComponent(attfulldaylbl, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel118)
                            .addComponent(atthulfdaylbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(p5_AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(attAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(attendprint, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );

        add(p5_Attendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 700));

        p5_AttendanceSummery.setBackground(new java.awt.Color(200, 210, 255));
        p5_AttendanceSummery.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p5_AttendanceSummery.setMinimumSize(new java.awt.Dimension(810, 712));
        p5_AttendanceSummery.setPreferredSize(new java.awt.Dimension(810, 712));

        header12.setBackground(new java.awt.Color(0, 0, 104));
        header12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        header12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 255, 255));
        jLabel120.setText("Attendance Summery :");
        header12.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        btnGoBack13.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack13.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack13.setText("Go Back");
        btnGoBack13.setBorderPainted(false);
        btnGoBack13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack13ActionPerformed(evt);
            }
        });

        jScrollPane8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        AttendanceSummeryTable.setBackground(new java.awt.Color(122, 204, 255));
        AttendanceSummeryTable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        AttendanceSummeryTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AttendanceSummeryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14"
            }
        ));
        AttendanceSummeryTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        AttendanceSummeryTable.setFocusable(false);
        AttendanceSummeryTable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        AttendanceSummeryTable.setShowVerticalLines(false);
        AttendanceSummeryTable.getTableHeader().setResizingAllowed(false);
        AttendanceSummeryTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(AttendanceSummeryTable);

        AttendanceSearchbtn.setBackground(new java.awt.Color(0, 153, 204));
        AttendanceSearchbtn.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        AttendanceSearchbtn.setText("Search");
        AttendanceSearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttendanceSearchbtnActionPerformed(evt);
            }
        });

        AttendanceSummeryPaneYear.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        AttendanceSummeryPaneYear.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        AttendanceSummeryPaneYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AttendanceSummeryPaneYearKeyPressed(evt);
            }
        });

        jLabel121.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(51, 51, 51));
        jLabel121.setText("Year : ");

        AttendanceSummeryPaneMonth.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        AttendanceSummeryPaneMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        AttendanceSummeryPaneMonth.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        AttendanceSummeryPaneMonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AttendanceSummeryPaneMonthMouseClicked(evt);
            }
        });
        AttendanceSummeryPaneMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttendanceSummeryPaneMonthActionPerformed(evt);
            }
        });

        jLabel122.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(51, 51, 51));
        jLabel122.setText("Month : ");

        AttendanceSummeryPrintbtn.setBackground(new java.awt.Color(0, 0, 102));
        AttendanceSummeryPrintbtn.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        AttendanceSummeryPrintbtn.setForeground(new java.awt.Color(255, 255, 255));
        AttendanceSummeryPrintbtn.setText("Print");
        AttendanceSummeryPrintbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttendanceSummeryPrintbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p5_AttendanceSummeryLayout = new javax.swing.GroupLayout(p5_AttendanceSummery);
        p5_AttendanceSummery.setLayout(p5_AttendanceSummeryLayout);
        p5_AttendanceSummeryLayout.setHorizontalGroup(
            p5_AttendanceSummeryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5_AttendanceSummeryLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnGoBack13, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AttendanceSummeryPrintbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(p5_AttendanceSummeryLayout.createSequentialGroup()
                .addGroup(p5_AttendanceSummeryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header12, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(p5_AttendanceSummeryLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(p5_AttendanceSummeryLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel121)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AttendanceSummeryPaneYear, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel122)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AttendanceSummeryPaneMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(AttendanceSearchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p5_AttendanceSummeryLayout.setVerticalGroup(
            p5_AttendanceSummeryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5_AttendanceSummeryLayout.createSequentialGroup()
                .addComponent(header12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(p5_AttendanceSummeryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel121)
                    .addComponent(jLabel122)
                    .addComponent(AttendanceSummeryPaneYear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AttendanceSearchbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AttendanceSummeryPaneMonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addGroup(p5_AttendanceSummeryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AttendanceSummeryPrintbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGoBack13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(p5_AttendanceSummery, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 700));
    }// </editor-fold>//GEN-END:initComponents

    private void getattenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getattenActionPerformed
        String lname=null,depart=null,design=null,fname = null,att="Arrival";

        String at3=attendandleavetime.getText();
        int at3pos1= at3.indexOf(":");
        int at3pos2= at3.indexOf(":",at3pos1+1);
        String a3h=at3.substring(0,at3pos1);
        String a3m=at3.substring(at3pos1+1,at3pos2);
        String a3s=at3.substring(at3pos2+1);

        int ae3=Integer.parseInt(a3h+a3m+a3s);

        if(ae3>83100)
        {
            try{

                String query = "UPDATE attend SET AttendTime=?,Attend=?,Day=? where EmpNo=? AND AttendDate=? ";
                con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=con.prepareStatement(query);
                String d="Half Day";

                pst.setString(1, attendandleavetime.getText());
                pst.setString(2, att);
                pst.setString(3, d);
                pst.setString(4, empid.getText());
                pst.setString(5, attenddate.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Get Half day");

                empid.setText("");
                name.setText("");
                dp.setIcon(null);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());

            }  }

            else{

                try{

                    String query = "UPDATE attend SET AttendTime=?,Attend=? where EmpNo=? AND AttendDate=? ";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst=con.prepareStatement(query);

                    pst.setString(1, attendandleavetime.getText());
                    pst.setString(2, att);
                    pst.setString(3, empid.getText());
                    pst.setString(4, attenddate.getText());

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Attend Get Successfully");
                    dp.setIcon(null);
                    empid.setText("");
                    name.setText("");

                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }
            }
    }//GEN-LAST:event_getattenActionPerformed

    private void addlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addlistActionPerformed
          try {
            String sqle ="SELECT MAX(AttendDate) FROM attend" ;
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sqle);
            String da=attenddate.getText();

            rs= (ResultSet) pst.executeQuery();
            while(rs.next())
            {
                String da1=(rs.getString("MAX(AttendDate)"));

                if(da.equals(da1))
                {

                    JOptionPane.showMessageDialog(this, "Today Data Also Add");
                }

                else
                {

                    try {
                        String sqlg ="SELECT EmpNo,First_Name,Last_Name,Department,Designation FROM employee" ;
                        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                        pst=(PreparedStatement) con.prepareStatement(sqlg);

                        rs= (ResultSet) pst.executeQuery();
                        while(rs.next()) {
                            String empno=(rs.getString("EmpNo"));
                            String fname=(rs.getString("First_Name"));
                            String lname=(rs.getString("Last_Name"));
                            String dep=(rs.getString("Department"));
                            String des=(rs.getString("Designation"));

                            try{

                                String query = "INSERT INTO attend(EmpNo,First_Name,Last_Name,Department,Designation ,AttendDate) VALUES (?,?,?,?,?,?)";
                                con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                                pst=con.prepareStatement(query);

                                pst.setString(1, empno);
                                pst.setString(2, fname);
                                pst.setString(3, lname);
                                pst.setString(4, dep);
                                pst.setString(5, des);
                                pst.setString(6, attenddate.getText());

                                pst.executeUpdate();

                            }
                            catch(Exception ex){
                                JOptionPane.showMessageDialog(this, ex.getMessage());

                            }

                        }
                        JOptionPane.showMessageDialog(this, "Get Successfully");

                    }

                    catch(Exception ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage());

                    }
                }

            } }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());

            }
    }//GEN-LAST:event_addlistActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String fname,lname;
        try {
            String sql ="SELECT First_Name,Last_Name,Profile_Pic FROM employee where EmpNo='"+empid.getText()+"'";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
            rs= (ResultSet) pst.executeQuery();

            if(rs.next()) {
                fname=(rs.getString("First_Name"));
                lname=(rs.getString("Last_Name"));
                name.setText(fname+" "+lname);
                byte[] img = rs.getBytes("Profile_Pic");
                ImageIcon proimage = new ImageIcon(img);
                Image im = proimage.getImage();
                Image myImg = im.getScaledInstance(dp.getWidth(), dp.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                dp.setIcon(newImage);
            }
            else{
                JOptionPane.showMessageDialog(null, "No Data");

                empid.setText("");
                name.setText("");
                dp.setIcon(null);
            }

        }

        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void camon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camon1ActionPerformed

        Thread t = new Thread(){
            @Override
            public void run(){
                barWebCamPanel.start();
            }
        };
        t.setDaemon(true);
        t.start();
        barWebCam.setViewSize(es);
        barWebCamPanel.setFPSDisplayed(true);
        barWebCamPanel.setPreferredSize(es);
        cam.add(barWebCamPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

        barWebCamPanel.setFillArea(true);

        cam.setLayout(new FlowLayout());
        cam.add(barWebCamPanel);
        executor.execute(this);
    }//GEN-LAST:event_camon1ActionPerformed

    private void camoff1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camoff1ActionPerformed
        Thread t = new Thread(){
            @Override
            public void run(){
                barWebCamPanel.stop();
            }
        };
        t.setDaemon(false);
        t.start();
        barWebCamPanel.setFillArea(false);
    }//GEN-LAST:event_camoff1ActionPerformed

    private void getleaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getleaveActionPerformed
    try{
    
            String quer = "UPDATE attend SET LeaveTime=? where EmpNo=? AND AttendDate=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(quer);
 
        
            pst.setString(1, attendandleavetime.getText());
            pst.setString(2, empid.getText());
            pst.setString(3, attenddate.getText());

            pst.executeUpdate();
           
          
  }
              catch(Exception ex){
                 
            JOptionPane.showMessageDialog(this, ex.getMessage());
              }
        
        
        try {
            String sql ="SELECT LeaveTime,Day FROM attend where EmpNo=? AND AttendDate=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sql);
          
            pst.setString(1, empid.getText());
            pst.setString(2, attenddate.getText());
            rs= (ResultSet) pst.executeQuery();
            if(rs.next()) {
               String lt3=(rs.getString("LeaveTime"));
               String da=(rs.getString("Day"));
              
               int lt3pos1= lt3.indexOf(":");
              int lt3pos2= lt3.indexOf(":",lt3pos1+1);
              String l3h=lt3.substring(0,lt3pos1);
              String l3m=lt3.substring(lt3pos1+1,lt3pos2);
              String l3s=lt3.substring(lt3pos2+1);
              
              int le3=Integer.parseInt(l3h+l3m+l3s);
              
  if(da.equals("Half Day"))
    {
           String d="Half Day"; 
           
            try{
 
            String quer = "UPDATE attend SET LeaveTime=?, Day=? where EmpNo=? AND AttendDate=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(quer);
 
            pst.setString(1, attendandleavetime.getText());
            pst.setString(2, d); 
            pst.setString(3, empid.getText());
            pst.setString(4, attenddate.getText());

            
            
            pst.executeUpdate();
           
                JOptionPane.showMessageDialog(this, "Leave Get Successfully");
                
                
            }  
           
           
            catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        } 
           
  
        
         try {
            String sq ="SELECT AttendTime,LeaveTime FROM attend where EmpNo=? AND AttendDate=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sq);
          
           pst.setString(1, empid.getText());
            pst.setString(2, attenddate.getText());
            rs= (ResultSet) pst.executeQuery();
            if(rs.next()) {
                String attime=(rs.getString("AttendTime"));
               String letime=(rs.getString("LeaveTime"));
          
 
              int letpos1= letime.indexOf(":");
              int letpos2= letime.indexOf(":",letpos1+1);
              String lh=letime.substring(0,letpos1);
              String lm=letime.substring(letpos1+1,letpos2);
              String ls=letime.substring(letpos2+1);
              System.out.println("hour:"+lh);
              System.out.println("min:"+lm);
              System.out.println("sec:"+ls);
                 
               int atpos1= attime.indexOf(":");  
               int atpos2= attime.indexOf(":",atpos1+1);  
               String ah=attime.substring(0,atpos1);
               String am=attime.substring(atpos1+1,atpos2);
               String as=attime.substring(atpos2+1);
              System.out.println("ahour:"+ah);
              System.out.println("amin:"+am);
              System.out.println("asec:"+as); 
                
              int lhou=Integer.parseInt(lh+lm+ls);

              int ahou=Integer.parseInt(ah+am+as);
              int w=lhou-ahou; 
             int worktime=0;
              int ot=0;
             if(w>=00000 && w<=99999)
             {
                String w3=String.valueOf(w);
              String w4=w3.substring(0,1); 
              worktime=Integer.parseInt(w4);
              
             if(worktime<=8){
                 ot=0;
             }
             
              ot= worktime - 8;
              
             }
             else{
                 
              String w1=String.valueOf(w);
              String w2=w1.substring(0,2);
             worktime=Integer.parseInt(w2); 
             
               ot= worktime - 8;
             }
            
              String wh =(String.valueOf(worktime));
             String ovet=(String.valueOf(ot));
              
              try{
    
            String qu = "UPDATE attend SET Working_Hours=?,OverTime=? where EmpNo=? AND AttendDate=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(qu);
 
        
            pst.setString(1, wh);
            pst.setString(2, ovet);
            pst.setString(3, empid.getText());
            pst.setString(4, attenddate.getText());

            
            
            pst.executeUpdate();
          
  }
             
     catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }       
        
        
        
    }}
     catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }   
    
    } 
 
  
  
   
else if(le3<163000)
      {
            String d="Half Day";
               try{
    
            String query = "UPDATE attend SET Day=?,LeaveTime=? where EmpNo=? AND AttendDate=? ";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(query);
          
           
            pst.setString(1, d);
            pst.setString(2, attendandleavetime.getText());
            pst.setString(3, empid.getText());
            pst.setString(4, attenddate.getText());
            
             pst.executeUpdate();
            
                 JOptionPane.showMessageDialog(this, "Get Half day ");
            }
              catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

                         }
               
       try {
            String sq ="SELECT AttendTime,LeaveTime FROM attend where EmpNo=? AND AttendDate=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sq);
          
           pst.setString(1, empid.getText());
            pst.setString(2, attenddate.getText());
            rs= (ResultSet) pst.executeQuery();
            if(rs.next()) {
                String attime=(rs.getString("AttendTime"));
               String letime=(rs.getString("LeaveTime"));
          
 
              int letpos1= letime.indexOf(":");
              int letpos2= letime.indexOf(":",letpos1+1);
              String lh=letime.substring(0,letpos1);
              String lm=letime.substring(letpos1+1,letpos2);
              String ls=letime.substring(letpos2+1);
              System.out.println("hour:"+lh);
              System.out.println("min:"+lm);
              System.out.println("sec:"+ls);
                 
               int atpos1= attime.indexOf(":");  
               int atpos2= attime.indexOf(":",atpos1+1);  
               String ah=attime.substring(0,atpos1);
               String am=attime.substring(atpos1+1,atpos2);
               String as=attime.substring(atpos2+1);
              System.out.println("ahour:"+ah);
              System.out.println("amin:"+am);
              System.out.println("asec:"+as); 
                
              int lhou=Integer.parseInt(lh+lm+ls);

              int ahou=Integer.parseInt(ah+am+as);
              int w=lhou-ahou; 
              String w1=String.valueOf(w);
              String w2=w1.substring(0,1);
             int worktime=Integer.parseInt(w2);
              
              int ot=0;
             
              String wh =(String.valueOf(worktime));
             String ovet=(String.valueOf(ot));
              
              try{
    
            String qu = "UPDATE attend SET Working_Hours=?,OverTime=? where EmpNo=? AND AttendDate=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(qu);
 
        
            pst.setString(1, w2);
            pst.setString(2, ovet);
            pst.setString(3, empid.getText());
            pst.setString(4, attenddate.getText());

            
            
            pst.executeUpdate();
           
               
               
            empid.setText("");
            name.setText("");
            dp.setIcon(null);
  }
             
     catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }       
        
        
        
        
        
    }}
     catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }             
               
               
     }
       






              
 else
 {
               
             
           try{
    String day="Full Day";
            String quer = "UPDATE attend SET LeaveTime=?, Day=? where EmpNo=? AND AttendDate=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(quer);
 
            pst.setString(1, attendandleavetime.getText());
            pst.setString(2, day); 
            pst.setString(3, empid.getText());
            pst.setString(4, attenddate.getText());

            
            
            pst.executeUpdate();
           
                JOptionPane.showMessageDialog(this, "Leave Get Successfully");
               
                
               
           
         
          try {
            String sq ="SELECT AttendTime,LeaveTime FROM attend where EmpNo=? AND AttendDate=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sq);
          
           pst.setString(1, empid.getText());
            pst.setString(2, attenddate.getText());
            rs= (ResultSet) pst.executeQuery();
            if(rs.next()) {
                String attime=(rs.getString("AttendTime"));
               String letime=(rs.getString("LeaveTime"));
          
 
              int letpos1= letime.indexOf(":");
              int letpos2= letime.indexOf(":",letpos1+1);
              String lh=letime.substring(0,letpos1);
              String lm=letime.substring(letpos1+1,letpos2);
              String ls=letime.substring(letpos2+1);
              System.out.println("hour:"+lh);
              System.out.println("min:"+lm);
              System.out.println("sec:"+ls);
                 
               int atpos1= attime.indexOf(":");  
               int atpos2= attime.indexOf(":",atpos1+1);  
               String ah=attime.substring(0,atpos1);
               String am=attime.substring(atpos1+1,atpos2);
               String as=attime.substring(atpos2+1);
              System.out.println("ahour:"+ah);
              System.out.println("amin:"+am);
              System.out.println("asec:"+as); 
                
              int lhou=Integer.parseInt(lh+lm+ls);

              int ahou=Integer.parseInt(ah+am+as);
              int w=lhou-ahou; 
              
              
             int fworktime=0;
              int ot=0;
             if(w>=00000 && w<=99999)
             {
                String w1=String.valueOf(w);
              String w2=w1.substring(0,1); 
              fworktime=Integer.parseInt(w2);
              
             if(fworktime<=8){
                 ot=0;
             }
             
              ot= fworktime - 8;
             
             }
             else{
                 
              String fw1=String.valueOf(w);
              String fw2=fw1.substring(0,2);
             fworktime=Integer.parseInt(fw2); 
             
               ot= fworktime - 8;
             }
              String wh =(String.valueOf(fworktime));
             String ovet=(String.valueOf(ot));
              
              try{
    
            String qu = "UPDATE attend SET Working_Hours=?,OverTime=? where EmpNo=? AND AttendDate=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(qu);
 
        
            pst.setString(1, wh);
            pst.setString(2, ovet);
            pst.setString(3, empid.getText());
            pst.setString(4, attenddate.getText());

            
            
            pst.executeUpdate();
           
               
               
            empid.setText("");
            name.setText("");
          
  }
              catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }   
              
              
              
              
            }}
               
                catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }   
               
               
               
               
               }
              catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }
              
              
              
              }
              
            }}
               
                catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }        
                                           

    }//GEN-LAST:event_getleaveActionPerformed

    private void searchAttendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAttendActionPerformed
        String month = null;
        String month1 = null;
        String year = null;
        String monthname =null ;

        String selectdate = ((JTextField)attendDateChooser1.getDateEditor().getUiComponent()).getText();
        month =(selectdate);
        System.out.println(month);

        String selectdate1 = ((JTextField)attendDateChooser2.getDateEditor().getUiComponent()).getText();
        month1 =(selectdate1);
        System.out.println(month);

        int at3pos1= month.indexOf("-");
        int at3pos2= month.indexOf("-",at3pos1+1);

        String a3y=month.substring(0,at3pos1);
        String a3m=month.substring(at3pos1+1,at3pos2);
        String a3d=month.substring(at3pos2+1);

        int m=Integer.parseInt(a3m);
        int mo=0;
        if(m>=00 && m<10)
        {
            String m3=String.valueOf(m);
            String m4=m3.substring(0,1);
            mo =Integer.parseInt(m4);
            String mo1 =String.valueOf(mo);

            if(mo1.equals("1"))
            {
                monthname="January";
            }
            else if(mo1.equals("2"))
            {
                monthname="February";
            }
            else if(mo1.equals("3"))
            {
                monthname="March";
            }
            else if(mo1.equals("4"))
            {
                monthname="April";
            }
            else if(mo1.equals("5"))
            {
                monthname="May";
            }
            else if(mo1.equals("6"))
            {
                monthname="June";

            }
            else if(mo1.equals("7"))
            {

                monthname="July";
            }
            else if(mo1.equals("8"))
            {
                monthname="August";
            }
            else if(mo1.equals("9"))
            {
                monthname="September";
            }
        }
        if(m>=10 && m<13)
        {
            String m3=String.valueOf(m);
            String m4=m3.substring(0,2);
            mo =Integer.parseInt(m4);
            String mo1 =String.valueOf(mo);

            if(mo1.equals("10"))
            {
                monthname="October";
            }
            else if(mo1.equals("11"))
            {
                monthname="November";
            }
            else if(mo1.equals("12"))
            {
                monthname="December";
            }
        }

        try {
            String sqlg ="SELECT EmpNo,First_Name,Last_Name,Department,Designation,AttendDate,AttendTime,LeaveTime,Working_Hours,OverTime,Attend,Day FROM attend  WHERE EmpNo='"+searchAttendId.getText()+"' AND AttendDate BETWEEN '"+month+"' AND '"+month1+"' " ;
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(sqlg);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        }

        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

        try {
            int c=0;
            String sqlg ="SELECT Attend FROM attend  WHERE Attend='Arrival' AND EmpNo='"+searchAttendId.getText()+"' AND  AttendDate BETWEEN '"+month+"' AND '"+month1+"' " ;
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(sqlg);
            rs=pst.executeQuery();
            while(rs.next())
            {
                String at=(rs.getString("Attend"));
                c=c+1;

            }
            attarivallbl.setText(String.valueOf(c));
        }

        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

        try {
            int c1=0;
            String sqlg ="SELECT Attend FROM attend  WHERE Attend='Absent' AND EmpNo='"+searchAttendId.getText()+"' AND  AttendDate BETWEEN '"+month+"' AND '"+month1+"' " ;
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(sqlg);
            rs=pst.executeQuery();
            while(rs.next())
            {
                String at1=(rs.getString("Attend"));
                c1=c1+1;

            }
            attabsentlbl.setText(String.valueOf(c1));
        }

        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

        try {
            int c2=0;
            String sqlg ="SELECT Day FROM attend  WHERE Day='Full Day' AND EmpNo='"+searchAttendId.getText()+"' AND  AttendDate BETWEEN '"+month+"' AND '"+month1+"' " ;
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(sqlg);
            rs=pst.executeQuery();
            while(rs.next())
            {
                String at1=(rs.getString("Day"));
                c2=c2+1;

            }
            attfulldaylbl.setText(String.valueOf(c2));
        }

        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

        try {
            int c3=0;
            String sqlg ="SELECT Day FROM attend  WHERE Day='Half Day' AND EmpNo='"+searchAttendId.getText()+"' AND  AttendDate BETWEEN '"+month+"' AND '"+month1+"' " ;
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(sqlg);
            rs=pst.executeQuery();
            while(rs.next())
            {
                String at1=(rs.getString("Day"));
                c3=c3+1;

            }
            atthulfdaylbl.setText(String.valueOf(c3));
        }

        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }
        
         jTable1.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
         jTable1.getTableHeader().setOpaque(false);
         jTable1.getTableHeader().setBackground(new Color(32,136,203));
         jTable1.getTableHeader().setForeground(new Color(0,0,0));
         jTable1.setRowHeight(25);
         
         jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
         jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(8).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(9).setPreferredWidth(120);
         jTable1.getColumnModel().getColumn(10).setPreferredWidth(120);
         
        
     

        int numrow = jTable1.getRowCount();
        int arri=0;
        int hotot= 0;
        int ottot= 0;
        for(int i = 0;i< numrow;i++){
            int val1 = Integer.parseInt(jTable1.getValueAt(i, 8).toString());
            hotot += val1;
            int val = Integer.parseInt(jTable1.getValueAt(i, 9).toString());
            ottot += val;

        }
        attotlbl.setText(String.valueOf(ottot));
        atthourslbl.setText(String.valueOf(hotot));
    }//GEN-LAST:event_searchAttendActionPerformed

    private void searchAttendIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAttendIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchAttendIdActionPerformed

    private void attendprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendprintActionPerformed
        String month = null;
        String month1 = null;
        String year = null;
        String monthname =null ;

        String selectdate = ((JTextField)attendDateChooser1.getDateEditor().getUiComponent()).getText();
        month =(selectdate);
        System.out.println(month);

        int at3pos1= month.indexOf("-");
        int at3pos2= month.indexOf("-",at3pos1+1);

        String a3y=month.substring(0,at3pos1);
        String a3m=month.substring(at3pos1+1,at3pos2);
        String a3d=month.substring(at3pos2+1);

        int m=Integer.parseInt(a3m);
        int mo=0;
        if(m>=00 && m<10)
        {
            String m3=String.valueOf(m);
            String m4=m3.substring(0,1);
            mo =Integer.parseInt(m4);
            String mo1 =String.valueOf(mo);

            if(mo1.equals("1"))
            {
                monthname="January";
            }
            else if(mo1.equals("2"))
            {
                monthname="February";
            }
            else if(mo1.equals("3"))
            {
                monthname="March";
            }
            else if(mo1.equals("4"))
            {
                monthname="April";
            }
            else if(mo1.equals("5"))
            {
                monthname="May";
            }
            else if(mo1.equals("6"))
            {
                monthname="June";

            }
            else if(mo1.equals("7"))
            {

                monthname="July";
            }
            else if(mo1.equals("8"))
            {
                monthname="August";
            }
            else if(mo1.equals("9"))
            {
                monthname="September";
            }
        }
        if(m>=10 && m<13)
        {
            String m3=String.valueOf(m);
            String m4=m3.substring(0,2);
            mo =Integer.parseInt(m4);
            String mo1 =String.valueOf(mo);

            if(mo1.equals("10"))
            {
                monthname="October";
            }
            else if(mo1.equals("11"))
            {
                monthname="November";
            }
            else if(mo1.equals("12"))
            {
                monthname="December";
            }
        }

        try {

            java.text.MessageFormat header = new java.text.MessageFormat(a3y+" "+monthname+" "+searchAttendId.getText()+" "+"Attendance report");

            jTable1.print(JTable.PrintMode.FIT_WIDTH,header, header);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_attendprintActionPerformed

    private void attAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attAddActionPerformed
        String attemp=null;
        String attfname=null;
        String attlname=null;
        String attdep=null;
        String attdes=null;

        String month = null;
        String month1 = null;
        String year = null;
        String monthname =null ;

        String selectdate = ((JTextField)attendDateChooser1.getDateEditor().getUiComponent()).getText();
        month =(selectdate);
        System.out.println(month);

        int at3pos1= month.indexOf("-");
        int at3pos2= month.indexOf("-",at3pos1+1);

        String a3y=month.substring(0,at3pos1);
        String a3m=month.substring(at3pos1+1,at3pos2);
        String a3d=month.substring(at3pos2+1);

        int m=Integer.parseInt(a3m);
        int mo=0;
        if(m>=00 && m<10)
        {
            String m3=String.valueOf(m);
            String m4=m3.substring(0,1);
            mo =Integer.parseInt(m4);
            String mo1 =String.valueOf(mo);

            if(mo1.equals("1"))
            {
                monthname="January";
            }
            else if(mo1.equals("2"))
            {
                monthname="February";
            }
            else if(mo1.equals("3"))
            {
                monthname="March";
            }
            else if(mo1.equals("4"))
            {
                monthname="April";
            }
            else if(mo1.equals("5"))
            {
                monthname="May";
            }
            else if(mo1.equals("6"))
            {
                monthname="June";

            }
            else if(mo1.equals("7"))
            {

                monthname="July";
            }
            else if(mo1.equals("8"))
            {
                monthname="August";
            }
            else if(mo1.equals("9"))
            {
                monthname="September";
            }
        }
        if(m>=10 && m<13)
        {
            String m3=String.valueOf(m);
            String m4=m3.substring(0,2);
            mo =Integer.parseInt(m4);
            String mo1 =String.valueOf(mo);

            if(mo1.equals("10"))
            {
                monthname="October";
            }
            else if(mo1.equals("11"))
            {
                monthname="November";
            }
            else if(mo1.equals("12"))
            {
                monthname="December";
            }
        }

        try {
            String sq ="SELECT EmpNo,First_Name,Last_Name,Department,Designation FROM employee where EmpNo=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(sq);

            pst.setString(1, searchAttendId.getText());
            rs= (ResultSet) pst.executeQuery();
            if(rs.next()) {
                attemp=(rs.getString("EmpNo"));
                attfname=(rs.getString("First_Name"));
                attlname=(rs.getString("Last_Name"));
                attdep=(rs.getString("Department"));
                attdes=(rs.getString("Designation"));

            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

        int opt =JOptionPane.showConfirmDialog(null,"Are you sure to Add !!","Add Employee",JOptionPane.YES_NO_OPTION);
        if (opt==0)
        {
            try{

                String query = "INSERT INTO attendance_summery(EmpNo,First_Name,Last_Name,Department,Designation,Year,Month,Working_Hours,OverTimes,Arrivals,Absents,Half_Days,Full_Days) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=con.prepareStatement(query);

                pst.setString(1, attemp);
                pst.setString(2,attfname );
                pst.setString(3, attlname);
                pst.setString(4, attdep);
                pst.setString(5, attdes);
                pst.setString(6, a3y);
                pst.setString(7, monthname);
                pst.setString(8, atthourslbl.getText());
                pst.setString(9, attotlbl.getText());
                pst.setString(10, attarivallbl.getText());
                pst.setString(11, attabsentlbl.getText());
                pst.setString(12, atthulfdaylbl.getText());
                pst.setString(13, attfulldaylbl.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Inserted Successfully");

            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());

            }

        }
    }//GEN-LAST:event_attAddActionPerformed

    private void btnGoBack13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack13ActionPerformed

        p5.setVisible(true);

        p5_Attendance.setVisible(false);
        p5_AttendanceSummery.setVisible(false);
    }//GEN-LAST:event_btnGoBack13ActionPerformed

    private void AttendanceSearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttendanceSearchbtnActionPerformed
        if(AttendanceSummeryPaneYear.getText().isEmpty())
        {
            AttendanceSummeryPaneYear.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please enter year !!");
        }
        else if(AttendanceSummeryPaneMonth.getSelectedItem().equals("Select Month"))
        {
            AttendanceSummeryPaneMonth.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please Select Month !!");
        }

        else
        {
            attendanceSummerytable();
        }
    }//GEN-LAST:event_AttendanceSearchbtnActionPerformed

    private void AttendanceSummeryPaneYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AttendanceSummeryPaneYearKeyPressed
        AttendanceSummeryPaneYear.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_AttendanceSummeryPaneYearKeyPressed

    private void AttendanceSummeryPaneMonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AttendanceSummeryPaneMonthMouseClicked
        AttendanceSummeryPaneMonth.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_AttendanceSummeryPaneMonthMouseClicked

    private void AttendanceSummeryPrintbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttendanceSummeryPrintbtnActionPerformed
        String atsy=AttendanceSummeryPaneYear.getText();
        String atsm=String.valueOf(AttendanceSummeryPaneMonth.getSelectedItem());

        try {

            java.text.MessageFormat header = new java.text.MessageFormat(atsy+" "+atsm+" "+"Attendance Summery");

            AttendanceSummeryTable.print(JTable.PrintMode.FIT_WIDTH,header, header);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_AttendanceSummeryPrintbtnActionPerformed

    private void btnGoBack14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack14ActionPerformed
        p5.setVisible(true);

        p5_Attendance.setVisible(false);
        p5_AttendanceSummery.setVisible(false);
    }//GEN-LAST:event_btnGoBack14ActionPerformed

    private void attendanceSummeryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attendanceSummeryMouseClicked
        p5_AttendanceSummery.setVisible(true);
        p5_Attendance.setVisible(false);
        p5.setVisible(false);
    }//GEN-LAST:event_attendanceSummeryMouseClicked

    private void attendanceMarkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attendanceMarkMouseClicked
        p5_Attendance.setVisible(true);
        p5_AttendanceSummery.setVisible(false);
        p5.setVisible(false);
    }//GEN-LAST:event_attendanceMarkMouseClicked

    private void AttendanceSummeryPaneMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttendanceSummeryPaneMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AttendanceSummeryPaneMonthActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AttendanceSearchbtn;
    private javax.swing.JComboBox<String> AttendanceSummeryPaneMonth;
    private javax.swing.JTextField AttendanceSummeryPaneYear;
    private javax.swing.JButton AttendanceSummeryPrintbtn;
    private javax.swing.JTable AttendanceSummeryTable;
    private javax.swing.JButton addlist;
    private javax.swing.JButton attAdd;
    private javax.swing.JLabel attabsentlbl;
    private javax.swing.JLabel attarivallbl;
    private com.toedter.calendar.JDateChooser attendDateChooser1;
    private com.toedter.calendar.JDateChooser attendDateChooser2;
    private javax.swing.JPanel attendanceMark;
    private javax.swing.JPanel attendanceSummery;
    private javax.swing.JLabel attendandleavetime;
    private javax.swing.JLabel attenddate;
    private javax.swing.JButton attendprint;
    private javax.swing.JLabel attfulldaylbl;
    private javax.swing.JLabel atthourslbl;
    private javax.swing.JLabel atthulfdaylbl;
    private javax.swing.JLabel attotlbl;
    private javax.swing.JButton btnGoBack13;
    private javax.swing.JButton btnGoBack14;
    private javax.swing.JLabel cam;
    private javax.swing.JButton camoff1;
    private javax.swing.JButton camon1;
    private javax.swing.JLabel dp;
    private javax.swing.JTextField empid;
    private javax.swing.JButton getatten;
    private javax.swing.JButton getleave;
    private javax.swing.JPanel header12;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p5_Attendance;
    private javax.swing.JPanel p5_AttendanceSummery;
    private javax.swing.JButton searchAttend;
    private javax.swing.JTextField searchAttendId;
    // End of variables declaration//GEN-END:variables
}
