/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.employee1;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Avishka
 */
public class Payroll extends javax.swing.JPanel {

 Connection con = null;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st=null;
    
    
    
    /**
     * Creates new form Payroll
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
    
    
    public Payroll() {
        initComponents();
        
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
       
        p3.setVisible(true);
           
        allowancetable();
        deductiontable();
        paymentSummerytable();
        
        allowancePanebudgetaryltxt.setText("1000");
        allowancePaneattentxt.setText("2000");
    
        showDate();
    }
    
  private void allowancetable() {
    try{
        String allowsql ="select * from allowance";
         con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(allowsql);
             rs=pst.executeQuery();
        allowanceTable.setModel(DbUtils.resultSetToTableModel(rs));
        
        
         allowanceTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
         allowanceTable.getTableHeader().setOpaque(false);
         allowanceTable.getTableHeader().setBackground(new Color(32,136,203));
         allowanceTable.getTableHeader().setForeground(new Color(0,0,0));
         allowanceTable.setRowHeight(25);
    }
    catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }     
                  allowanceTable.getColumnModel().getColumn(0).setPreferredWidth(100); 
                  allowanceTable.getColumnModel().getColumn(1).setPreferredWidth(130); 
                  allowanceTable.getColumnModel().getColumn(2).setPreferredWidth(130); 
                  allowanceTable.getColumnModel().getColumn(3).setPreferredWidth(140); 
                  allowanceTable.getColumnModel().getColumn(4).setPreferredWidth(140); 
                  allowanceTable.getColumnModel().getColumn(5).setPreferredWidth(120); 
                  allowanceTable.getColumnModel().getColumn(6).setPreferredWidth(140); 
                  allowanceTable.getColumnModel().getColumn(7).setPreferredWidth(120); 
                  allowanceTable.getColumnModel().getColumn(8).setPreferredWidth(100); 
                  allowanceTable.getColumnModel().getColumn(9).setPreferredWidth(100); 
                  allowanceTable.getColumnModel().getColumn(10).setPreferredWidth(120); 
                   allowanceTable.getColumnModel().getColumn(11).setPreferredWidth(140); 
                 
    
    
    
  } 
        
        
        private void deductiontable() {
    try{
        String allowsql ="select * from deduction";
         con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=con.prepareStatement(allowsql);
             rs=pst.executeQuery();
        deductionTable.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        
         deductionTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
         deductionTable.getTableHeader().setOpaque(false);
         deductionTable.getTableHeader().setBackground(new Color(32,136,203));
         deductionTable.getTableHeader().setForeground(new Color(0,0,0));
         deductionTable.setRowHeight(25);
    }
    catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }     
     
    deductionTable.getColumnModel().getColumn(0).setPreferredWidth(100); 
    deductionTable.getColumnModel().getColumn(1).setPreferredWidth(120);
    deductionTable.getColumnModel().getColumn(2).setPreferredWidth(120);
    deductionTable.getColumnModel().getColumn(3).setPreferredWidth(120);
    deductionTable.getColumnModel().getColumn(4).setPreferredWidth(140);
    deductionTable.getColumnModel().getColumn(5).setPreferredWidth(120);
    deductionTable.getColumnModel().getColumn(6).setPreferredWidth(140);
    deductionTable.getColumnModel().getColumn(7).setPreferredWidth(150);
    deductionTable.getColumnModel().getColumn(8).setPreferredWidth(180);
    
    
    
  } 
 

        
        private void paymentSummerytable(){
  try {
            String summ ="SELECT * FROM `salary_payments` WHERE `Year`=? AND`Month`=?  ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(summ);
            
            pst.setString(1, paymentSummeryPaneYear.getText());
            pst.setString(2, String.valueOf(paymentSummeryPaneMonth.getSelectedItem()));
            rs= (ResultSet) pst.executeQuery();
            paymentsummeryTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
            
         paymentsummeryTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
         paymentsummeryTable.getTableHeader().setOpaque(false);
         paymentsummeryTable.getTableHeader().setBackground(new Color(32,136,203));
         paymentsummeryTable.getTableHeader().setForeground(new Color(0,0,0));
         paymentsummeryTable.setRowHeight(25);
            
     }
         
          catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  
  
           paymentsummeryTable.getColumnModel().getColumn(0).setPreferredWidth(100);
           paymentsummeryTable.getColumnModel().getColumn(1).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(2).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(3).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(4).setPreferredWidth(140);
           paymentsummeryTable.getColumnModel().getColumn(5).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(6).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(7).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(8).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(9).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(10).setPreferredWidth(140);
           paymentsummeryTable.getColumnModel().getColumn(11).setPreferredWidth(140);
           paymentsummeryTable.getColumnModel().getColumn(12).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(13).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(14).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(15).setPreferredWidth(120);
           paymentsummeryTable.getColumnModel().getColumn(16).setPreferredWidth(140);
  
  
  
  
  
  
  
  
  
  
  }   
    
    
    
 void showDate() {
        Date d = new Date();
        SimpleDateFormat s;
        s = new SimpleDateFormat("yyyy-MM-dd");
       

        
        String month = null;
        String year = null;
        String monthname =null ;

        String selectdate = (s.format(d));
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
        
        monthlbl.setText(monthname);
        yearlbl.setText(a3y);
    }
 
 

  
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p3 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        allowanceSalary = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        paymentSalary = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        updateSalary = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        deductionSalary = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        paymentSummery = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        p3_UpdatesalryPane = new javax.swing.JPanel();
        header8 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        btnGoBack9 = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        salaryUpdateSearch = new javax.swing.JButton();
        updateSalarySearchtxt = new javax.swing.JTextField();
        updatePercentage = new javax.swing.JTextField();
        updateAmount = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        updatepercentageRadio = new javax.swing.JRadioButton();
        updateAmountRadio = new javax.swing.JRadioButton();
        basicSalaryUpdate = new javax.swing.JButton();
        lblemono5 = new javax.swing.JLabel();
        txtempno4 = new javax.swing.JTextField();
        lblNic4 = new javax.swing.JLabel();
        txtNIC4 = new javax.swing.JTextField();
        lblfname4 = new javax.swing.JLabel();
        txtFname4 = new javax.swing.JTextField();
        lbllname4 = new javax.swing.JLabel();
        txtLname4 = new javax.swing.JTextField();
        lblDOB4 = new javax.swing.JLabel();
        txtDOB4 = new javax.swing.JTextField();
        lblAdress4 = new javax.swing.JLabel();
        txtAdress4 = new javax.swing.JTextField();
        lblAdressline4 = new javax.swing.JLabel();
        txtAdressline4 = new javax.swing.JTextField();
        lblContactno4 = new javax.swing.JLabel();
        txtContactNo4 = new javax.swing.JTextField();
        lblEmail4 = new javax.swing.JLabel();
        txtEmail4 = new javax.swing.JTextField();
        lblDepartment5 = new javax.swing.JLabel();
        txtSdepartment = new javax.swing.JTextField();
        lbldesignation5 = new javax.swing.JLabel();
        txtSdesignation5 = new javax.swing.JTextField();
        lbleducation4 = new javax.swing.JLabel();
        txteducationallevel4 = new javax.swing.JTextField();
        lblBasicSalary4 = new javax.swing.JLabel();
        txtBasicSalary4 = new javax.swing.JTextField();
        lblgender4 = new javax.swing.JLabel();
        txtgender4 = new javax.swing.JTextField();
        lblstatus4 = new javax.swing.JLabel();
        txtstatus4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        p3_PaymentPane = new javax.swing.JPanel();
        header10 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        btnGoBack11 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        paymentPaneSearchtxt = new javax.swing.JTextField();
        paymentPaneSearch = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        paymentPaneEmpNo = new javax.swing.JTextField();
        paymentPaneLastName = new javax.swing.JTextField();
        paymentPaneDeparment = new javax.swing.JTextField();
        paymentPaneDesignation = new javax.swing.JTextField();
        paymentPaneBasicSalary = new javax.swing.JTextField();
        paymentPaneOverTime = new javax.swing.JTextField();
        paymentPaneMedical = new javax.swing.JTextField();
        paymentPaneBonus = new javax.swing.JTextField();
        paymentPaneOther = new javax.swing.JTextField();
        paymentPaneDeductionDetails = new javax.swing.JTextField();
        paymentPaneTotalDeduction = new javax.swing.JTextField();
        GenarateSalary = new javax.swing.JButton();
        paymentPaneTotalEarning = new javax.swing.JLabel();
        paymentPaneNetPay = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        paymentPaneFirstName = new javax.swing.JTextField();
        paymentPaneClear = new javax.swing.JButton();
        paymentPanePayment = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        slipArea = new javax.swing.JTextArea();
        paymentPanePrint = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        paymentPaneStatus = new javax.swing.JLabel();
        yearlbl = new javax.swing.JLabel();
        monthlbl = new javax.swing.JLabel();
        p3_AllowancePane = new javax.swing.JPanel();
        header4 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        btnGoBack8 = new javax.swing.JButton();
        allowancePaneSearchEmpidlb = new javax.swing.JLabel();
        allowancePaneSearchEmpidtxt = new javax.swing.JTextField();
        allowanceSearchbtn = new javax.swing.JButton();
        allowancePaneEmpnolbl = new javax.swing.JLabel();
        allowancePaneCalculatebtn = new javax.swing.JButton();
        allowancePaneFnamelbl = new javax.swing.JLabel();
        allowancePaneLnamelbl = new javax.swing.JLabel();
        allowancePaneBasicsalarylbl = new javax.swing.JLabel();
        allowancePaneDeparmentlbl = new javax.swing.JLabel();
        allowancePaneOvertimelbl = new javax.swing.JLabel();
        allowancePanebudetarylbl = new javax.swing.JLabel();
        allowancePaneBonuslbl = new javax.swing.JLabel();
        allowancePaneOtherlbl = new javax.swing.JLabel();
        allowancePaneRateOfPerHour = new javax.swing.JLabel();
        allowancePaneTotalAmount = new javax.swing.JLabel();
        allowancePaneSavebtn = new javax.swing.JButton();
        allowancePaneClearbtn = new javax.swing.JButton();
        allowancePaneEmpNotxt = new javax.swing.JTextField();
        allowancePaneFnametxt = new javax.swing.JTextField();
        allowancePaneLnametxt = new javax.swing.JTextField();
        allowancePaneBasicSalarytxt = new javax.swing.JTextField();
        allowancePaneDepartmenttxt = new javax.swing.JTextField();
        allowancePaneOvertimetxt = new javax.swing.JTextField();
        allowancePanebudgetaryltxt = new javax.swing.JTextField();
        allowancePaneattentxt = new javax.swing.JTextField();
        allowancePaneOthertxt = new javax.swing.JTextField();
        allowancePaneRateOfPerHourtxt = new javax.swing.JTextField();
        allowancePaneUpdatebtn = new javax.swing.JButton();
        allowancePaneTotalAmountDislbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        allowanceTable = new javax.swing.JTable();
        jLabel55 = new javax.swing.JLabel();
        allowancePaneOT = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        p3_DeductionPane = new javax.swing.JPanel();
        header9 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        btnGoBack10 = new javax.swing.JButton();
        deductionPaneSearchEmpidtxt = new javax.swing.JTextField();
        deductionPaneSearchEmpidlb = new javax.swing.JLabel();
        deductionSearchbtn = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        deductionPaneEmpNotxt = new javax.swing.JTextField();
        deductionPaneEmpnolbl = new javax.swing.JLabel();
        deductionPaneFnamelbl = new javax.swing.JLabel();
        deductionPaneLnamelbl = new javax.swing.JLabel();
        deductionPaneBasicsalarylbl = new javax.swing.JLabel();
        deductionPaneDeparmentlbl = new javax.swing.JLabel();
        deductionPaneDepartmenttxt = new javax.swing.JTextField();
        deductionPaneBasicSalarytxt = new javax.swing.JTextField();
        deductionPaneLnametxt = new javax.swing.JTextField();
        deductionPaneFnametxt = new javax.swing.JTextField();
        deductionPaneDesignationtxt = new javax.swing.JTextField();
        deductionPaneDesignationlbl = new javax.swing.JLabel();
        deductionPaneUplbl = new javax.swing.JLabel();
        deductionPanePercentageRadio = new javax.swing.JRadioButton();
        deductionPaneAmountRadio = new javax.swing.JRadioButton();
        deductionPanePercentagelbl = new javax.swing.JLabel();
        deductionPaneResonlbl = new javax.swing.JLabel();
        deductionPanePercentagetxt = new javax.swing.JTextField();
        deductionPaneAmountlbl = new javax.swing.JLabel();
        deductionPaneAmounttxt = new javax.swing.JTextField();
        deductionPaneResontxt = new javax.swing.JTextField();
        additionalLeaves = new javax.swing.JTextField();
        deductionPaneTotalDeductionlbl = new javax.swing.JLabel();
        deductionPaneTotalDeduction = new javax.swing.JLabel();
        deductionPaneSadlbl = new javax.swing.JLabel();
        deductionPaneSad = new javax.swing.JLabel();
        deductionPaneSave = new javax.swing.JButton();
        deductionPaneCalculate = new javax.swing.JButton();
        deductionPaneClear = new javax.swing.JButton();
        deductionPaneUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        additionalLeavesamount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        deductionTable = new javax.swing.JTable();
        p3_PaymentSummeryPane = new javax.swing.JPanel();
        header11 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        btnGoBack12 = new javax.swing.JButton();
        salaryUpdateSearch1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        paymentsummeryTable = new javax.swing.JTable();
        paymentSummeryPaneYear = new javax.swing.JTextField();
        paymentSummeryPaneMonth = new javax.swing.JComboBox<>();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        paymentPaneSummeryTot = new javax.swing.JLabel();
        paymentSummeryPrint = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p3.setBackground(new java.awt.Color(180, 170, 255));
        p3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p3.setPreferredSize(new java.awt.Dimension(1024, 768));
        p3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel61.setText("PayRoll Section");
        p3.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 170, -1, -1));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/information.png"))); // NOI18N
        jLabel62.setToolTipText("");
        p3.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 167, -1, -1));

        allowanceSalary.setBackground(new java.awt.Color(255, 255, 255));
        allowanceSalary.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        allowanceSalary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        allowanceSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allowanceSalaryMouseClicked(evt);
            }
        });
        allowanceSalary.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/salary1.png"))); // NOI18N
        allowanceSalary.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 120, 120));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 102, 0));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Allowance Salary");
        allowanceSalary.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 20));

        p3.add(allowanceSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 235, 210, 170));

        paymentSalary.setBackground(new java.awt.Color(255, 255, 255));
        paymentSalary.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        paymentSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentSalaryMouseClicked(evt);
            }
        });
        paymentSalary.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/salary2.png"))); // NOI18N
        paymentSalary.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 120, 120));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 153, 0));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Payments");
        paymentSalary.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, 20));

        p3.add(paymentSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 464, 210, 178));

        updateSalary.setBackground(new java.awt.Color(255, 255, 255));
        updateSalary.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        updateSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateSalaryMouseClicked(evt);
            }
        });
        updateSalary.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/salary5.png"))); // NOI18N
        updateSalary.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 120, 120));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 0, 204));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Update Salary");
        updateSalary.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, 20));

        p3.add(updateSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 235, 210, 170));

        deductionSalary.setBackground(new java.awt.Color(255, 255, 255));
        deductionSalary.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        deductionSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deductionSalaryMouseClicked(evt);
            }
        });
        deductionSalary.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/salary4.png"))); // NOI18N
        deductionSalary.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 130, 120));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 153, 255));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Deduction Salary");
        deductionSalary.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 20));

        p3.add(deductionSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 235, 210, 170));

        paymentSummery.setBackground(new java.awt.Color(255, 255, 255));
        paymentSummery.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 51)));
        paymentSummery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentSummeryMouseClicked(evt);
            }
        });
        paymentSummery.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/management/srb/icons/salary3.png"))); // NOI18N
        paymentSummery.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 130, 120));

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 0, 0));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("Payments Summery");
        paymentSummery.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        p3.add(paymentSummery, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 464, 210, 178));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Welcome to EMP System");
        p3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 44, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 0, 1, new java.awt.Color(0, 0, 0)));
        p3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 126, 561, -1));

        add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 700));

        p3_UpdatesalryPane.setBackground(new java.awt.Color(200, 210, 255));
        p3_UpdatesalryPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p3_UpdatesalryPane.setForeground(new java.awt.Color(255, 255, 255));
        p3_UpdatesalryPane.setToolTipText("");
        p3_UpdatesalryPane.setPreferredSize(new java.awt.Dimension(810, 712));

        header8.setBackground(new java.awt.Color(0, 0, 104));
        header8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Update Salary :");

        javax.swing.GroupLayout header8Layout = new javax.swing.GroupLayout(header8);
        header8.setLayout(header8Layout);
        header8Layout.setHorizontalGroup(
            header8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel72)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header8Layout.setVerticalGroup(
            header8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnGoBack9.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack9.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack9.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack9.setText("Go Back");
        btnGoBack9.setBorderPainted(false);
        btnGoBack9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack9ActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel73.setText("EmpNo :");

        salaryUpdateSearch.setBackground(new java.awt.Color(0, 153, 204));
        salaryUpdateSearch.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        salaryUpdateSearch.setText("Search");
        salaryUpdateSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryUpdateSearchActionPerformed(evt);
            }
        });

        updateSalarySearchtxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        updateSalarySearchtxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        updateSalarySearchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updateSalarySearchtxtKeyPressed(evt);
            }
        });

        updatePercentage.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        updatePercentage.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        updatePercentage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePercentageActionPerformed(evt);
            }
        });

        updateAmount.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        updateAmount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        updateAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAmountActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Update Salary By :");

        jLabel75.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Percentage :");

        jLabel76.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
        jLabel76.setText("Amount :");

        updatepercentageRadio.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        updatepercentageRadio.setText("Percentage (%)");
        updatepercentageRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatepercentageRadioActionPerformed(evt);
            }
        });

        updateAmountRadio.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        updateAmountRadio.setText("Amount");
        updateAmountRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAmountRadioActionPerformed(evt);
            }
        });

        basicSalaryUpdate.setBackground(new java.awt.Color(0, 0, 102));
        basicSalaryUpdate.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        basicSalaryUpdate.setForeground(new java.awt.Color(255, 255, 255));
        basicSalaryUpdate.setText("Update");
        basicSalaryUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basicSalaryUpdateActionPerformed(evt);
            }
        });

        lblemono5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblemono5.setForeground(new java.awt.Color(51, 51, 51));
        lblemono5.setText("Emp No :");

        txtempno4.setEditable(false);
        txtempno4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtempno4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtempno4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtempno4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtempno4FocusLost(evt);
            }
        });
        txtempno4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtempno4ActionPerformed(evt);
            }
        });
        txtempno4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtempno4KeyPressed(evt);
            }
        });

        lblNic4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblNic4.setForeground(new java.awt.Color(51, 51, 51));
        lblNic4.setText("NIC No :");

        txtNIC4.setEditable(false);
        txtNIC4.setBackground(new java.awt.Color(255, 255, 255));
        txtNIC4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtNIC4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtNIC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIC4ActionPerformed(evt);
            }
        });

        lblfname4.setBackground(new java.awt.Color(51, 0, 51));
        lblfname4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblfname4.setForeground(new java.awt.Color(51, 51, 51));
        lblfname4.setText("First Name :");

        txtFname4.setEditable(false);
        txtFname4.setBackground(new java.awt.Color(255, 255, 255));
        txtFname4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtFname4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtFname4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFname4ActionPerformed(evt);
            }
        });

        lbllname4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbllname4.setForeground(new java.awt.Color(51, 51, 51));
        lbllname4.setText("Last Name :");

        txtLname4.setEditable(false);
        txtLname4.setBackground(new java.awt.Color(255, 255, 255));
        txtLname4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtLname4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtLname4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLname4ActionPerformed(evt);
            }
        });

        lblDOB4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDOB4.setForeground(new java.awt.Color(51, 51, 51));
        lblDOB4.setText("Date of Birth :");

        txtDOB4.setEditable(false);
        txtDOB4.setBackground(new java.awt.Color(255, 255, 255));
        txtDOB4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtDOB4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtDOB4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDOB4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDOB4FocusLost(evt);
            }
        });
        txtDOB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDOB4ActionPerformed(evt);
            }
        });

        lblAdress4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblAdress4.setForeground(new java.awt.Color(51, 51, 51));
        lblAdress4.setText("Adress 1 :");

        txtAdress4.setEditable(false);
        txtAdress4.setBackground(new java.awt.Color(255, 255, 255));
        txtAdress4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtAdress4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtAdress4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdress4ActionPerformed(evt);
            }
        });

        lblAdressline4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblAdressline4.setForeground(new java.awt.Color(51, 51, 51));
        lblAdressline4.setText("Adress 2 :");

        txtAdressline4.setEditable(false);
        txtAdressline4.setBackground(new java.awt.Color(255, 255, 255));
        txtAdressline4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtAdressline4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtAdressline4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdressline4ActionPerformed(evt);
            }
        });

        lblContactno4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblContactno4.setForeground(new java.awt.Color(51, 51, 51));
        lblContactno4.setText("Contact No :");

        txtContactNo4.setEditable(false);
        txtContactNo4.setBackground(new java.awt.Color(255, 255, 255));
        txtContactNo4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtContactNo4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtContactNo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactNo4ActionPerformed(evt);
            }
        });

        lblEmail4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblEmail4.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail4.setText("Email :");

        txtEmail4.setEditable(false);
        txtEmail4.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtEmail4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtEmail4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmail4ActionPerformed(evt);
            }
        });

        lblDepartment5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDepartment5.setForeground(new java.awt.Color(51, 51, 51));
        lblDepartment5.setText("Department :");

        txtSdepartment.setEditable(false);
        txtSdepartment.setBackground(new java.awt.Color(255, 255, 255));
        txtSdepartment.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtSdepartment.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtSdepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdepartmentActionPerformed(evt);
            }
        });

        lbldesignation5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbldesignation5.setForeground(new java.awt.Color(51, 51, 51));
        lbldesignation5.setText("Designation :");

        txtSdesignation5.setEditable(false);
        txtSdesignation5.setBackground(new java.awt.Color(255, 255, 255));
        txtSdesignation5.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtSdesignation5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtSdesignation5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdesignation5ActionPerformed(evt);
            }
        });

        lbleducation4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lbleducation4.setForeground(new java.awt.Color(51, 51, 51));
        lbleducation4.setText("Educational Level :");

        txteducationallevel4.setEditable(false);
        txteducationallevel4.setBackground(new java.awt.Color(255, 255, 255));
        txteducationallevel4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txteducationallevel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txteducationallevel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txteducationallevel4ActionPerformed(evt);
            }
        });

        lblBasicSalary4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblBasicSalary4.setForeground(new java.awt.Color(51, 51, 51));
        lblBasicSalary4.setText("Basic Salary :");

        txtBasicSalary4.setEditable(false);
        txtBasicSalary4.setBackground(new java.awt.Color(255, 255, 255));
        txtBasicSalary4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtBasicSalary4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtBasicSalary4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBasicSalary4ActionPerformed(evt);
            }
        });
        txtBasicSalary4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBasicSalary4KeyPressed(evt);
            }
        });

        lblgender4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblgender4.setForeground(new java.awt.Color(51, 51, 51));
        lblgender4.setText("Gender :");

        txtgender4.setEditable(false);
        txtgender4.setBackground(new java.awt.Color(255, 255, 255));
        txtgender4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtgender4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtgender4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgender4ActionPerformed(evt);
            }
        });

        lblstatus4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblstatus4.setForeground(new java.awt.Color(51, 51, 51));
        lblstatus4.setText("Status :");

        txtstatus4.setEditable(false);
        txtstatus4.setBackground(new java.awt.Color(255, 255, 255));
        txtstatus4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtstatus4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        txtstatus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstatus4ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 102, 0));
        jButton3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p3_UpdatesalryPaneLayout = new javax.swing.GroupLayout(p3_UpdatesalryPane);
        p3_UpdatesalryPane.setLayout(p3_UpdatesalryPaneLayout);
        p3_UpdatesalryPaneLayout.setHorizontalGroup(
            p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel73)
                        .addGap(47, 47, 47)
                        .addComponent(updateSalarySearchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(salaryUpdateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblemono5)
                        .addGap(44, 44, 44)
                        .addComponent(txtempno4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(lblEmail4)
                        .addGap(87, 87, 87)
                        .addComponent(txtEmail4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblNic4)
                        .addGap(49, 49, 49)
                        .addComponent(txtNIC4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(lblDepartment5)
                        .addGap(47, 47, 47)
                        .addComponent(txtSdepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblfname4)
                        .addGap(24, 24, 24)
                        .addComponent(txtFname4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(lbldesignation5)
                        .addGap(48, 48, 48)
                        .addComponent(txtSdesignation5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lbllname4)
                        .addGap(26, 26, 26)
                        .addComponent(txtLname4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(lbleducation4)
                        .addGap(11, 11, 11)
                        .addComponent(txteducationallevel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblDOB4)
                        .addGap(12, 12, 12)
                        .addComponent(txtDOB4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(lblBasicSalary4)
                        .addGap(46, 46, 46)
                        .addComponent(txtBasicSalary4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblAdress4)
                        .addGap(40, 40, 40)
                        .addComponent(txtAdress4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(lblgender4)
                        .addGap(78, 78, 78)
                        .addComponent(txtgender4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblAdressline4)
                        .addGap(40, 40, 40)
                        .addComponent(txtAdressline4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(lblstatus4)
                        .addGap(84, 84, 84)
                        .addComponent(txtstatus4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblContactno4)
                        .addGap(23, 23, 23)
                        .addComponent(txtContactNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel74)
                        .addGap(10, 10, 10)
                        .addComponent(updatepercentageRadio)
                        .addGap(133, 133, 133)
                        .addComponent(updateAmountRadio))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnGoBack9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel75)
                                .addGap(10, 10, 10)
                                .addComponent(updatePercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p3_UpdatesalryPaneLayout.createSequentialGroup()
                                .addGap(265, 265, 265)
                                .addComponent(basicSalaryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addComponent(jLabel76)
                        .addGap(6, 6, 6)
                        .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(header8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p3_UpdatesalryPaneLayout.setVerticalGroup(
            p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                .addComponent(header8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel73))
                    .addComponent(salaryUpdateSearch)
                    .addComponent(updateSalarySearchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblemono5)
                    .addComponent(txtempno4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail4)
                    .addComponent(txtEmail4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNic4)
                    .addComponent(txtNIC4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartment5)
                    .addComponent(txtSdepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblfname4)
                    .addComponent(txtFname4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldesignation5)
                    .addComponent(txtSdesignation5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbllname4)
                    .addComponent(txtLname4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbleducation4)
                    .addComponent(txteducationallevel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDOB4)
                    .addComponent(txtDOB4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBasicSalary4)
                    .addComponent(txtBasicSalary4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdress4)
                    .addComponent(txtAdress4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblgender4)
                    .addComponent(txtgender4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdressline4)
                    .addComponent(txtAdressline4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblstatus4)
                    .addComponent(txtstatus4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContactno4)
                    .addComponent(txtContactNo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel74))
                    .addComponent(updatepercentageRadio)
                    .addComponent(updateAmountRadio))
                .addGap(16, 16, 16)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(jLabel76)
                    .addGroup(p3_UpdatesalryPaneLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updatePercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addGroup(p3_UpdatesalryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(basicSalaryUpdate)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnGoBack9)
                .addGap(21, 21, 21))
        );

        add(p3_UpdatesalryPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        p3_PaymentPane.setBackground(new java.awt.Color(200, 210, 255));
        p3_PaymentPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p3_PaymentPane.setToolTipText("");
        p3_PaymentPane.setPreferredSize(new java.awt.Dimension(810, 712));
        p3_PaymentPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header10.setBackground(new java.awt.Color(0, 0, 104));
        header10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Payment Salary :");

        javax.swing.GroupLayout header10Layout = new javax.swing.GroupLayout(header10);
        header10.setLayout(header10Layout);
        header10Layout.setHorizontalGroup(
            header10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel80)
                .addContainerGap(673, Short.MAX_VALUE))
        );
        header10Layout.setVerticalGroup(
            header10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p3_PaymentPane.add(header10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 810, -1));

        btnGoBack11.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack11.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack11.setText("Go Back");
        btnGoBack11.setBorderPainted(false);
        btnGoBack11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack11ActionPerformed(evt);
            }
        });
        p3_PaymentPane.add(btnGoBack11, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 663, 170, -1));

        jLabel81.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(51, 51, 51));
        jLabel81.setText("Search :");
        p3_PaymentPane.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        paymentPaneSearchtxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneSearchtxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        paymentPaneSearchtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentPaneSearchtxtActionPerformed(evt);
            }
        });
        paymentPaneSearchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                paymentPaneSearchtxtKeyPressed(evt);
            }
        });
        p3_PaymentPane.add(paymentPaneSearchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 90, 150, 23));

        paymentPaneSearch.setBackground(new java.awt.Color(0, 153, 204));
        paymentPaneSearch.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneSearch.setText("Search");
        paymentPaneSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentPaneSearchActionPerformed(evt);
            }
        });
        p3_PaymentPane.add(paymentPaneSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 89, 90, -1));

        jLabel82.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 51, 51));
        jLabel82.setText("EmpNo :");
        p3_PaymentPane.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel83.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 51, 51));
        jLabel83.setText("Last Name :");
        p3_PaymentPane.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel84.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 51));
        jLabel84.setText("Department :");
        p3_PaymentPane.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel86.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(51, 51, 51));
        jLabel86.setText("Designation :");
        p3_PaymentPane.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel87.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(51, 51, 51));
        jLabel87.setText("Basic Salary :");
        p3_PaymentPane.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        jLabel90.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(51, 51, 51));
        jLabel90.setText("OT :");
        p3_PaymentPane.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel92.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(51, 51, 51));
        jLabel92.setText("Budgetary Allowance :");
        p3_PaymentPane.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 140, -1));

        jLabel93.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(51, 51, 51));
        jLabel93.setText("Attendance Allowance :");
        p3_PaymentPane.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 140, -1));

        jLabel94.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(51, 51, 51));
        jLabel94.setText("Other :");
        p3_PaymentPane.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, -1));

        jLabel95.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(51, 51, 51));
        jLabel95.setText("Deduction Details :");
        p3_PaymentPane.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, -1));

        jLabel96.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(51, 51, 51));
        jLabel96.setText("Total Deductons :");
        p3_PaymentPane.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, -1, -1));

        jLabel97.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel97.setText("Total Earning :");
        p3_PaymentPane.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 489, -1, -1));

        jLabel98.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel98.setText("Net Pay :");
        p3_PaymentPane.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 529, -1, -1));

        paymentPaneEmpNo.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneEmpNo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneEmpNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 140, 150, -1));

        paymentPaneLastName.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneLastName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 220, 150, -1));

        paymentPaneDeparment.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneDeparment.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneDeparment, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 260, 150, -1));

        paymentPaneDesignation.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneDesignation.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        paymentPaneDesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentPaneDesignationActionPerformed(evt);
            }
        });
        p3_PaymentPane.add(paymentPaneDesignation, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 300, 150, -1));

        paymentPaneBasicSalary.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneBasicSalary.setText("0.00");
        paymentPaneBasicSalary.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneBasicSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 340, 150, -1));

        paymentPaneOverTime.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneOverTime.setText("0.00");
        paymentPaneOverTime.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        paymentPaneOverTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentPaneOverTimeActionPerformed(evt);
            }
        });
        p3_PaymentPane.add(paymentPaneOverTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 380, 150, -1));

        paymentPaneMedical.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneMedical.setText("0.00");
        paymentPaneMedical.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneMedical, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 420, 150, -1));

        paymentPaneBonus.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneBonus.setText("0.00");
        paymentPaneBonus.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneBonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 460, 150, -1));

        paymentPaneOther.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneOther.setText("0.00");
        paymentPaneOther.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneOther, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 500, 150, -1));

        paymentPaneDeductionDetails.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneDeductionDetails.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneDeductionDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 540, 150, -1));

        paymentPaneTotalDeduction.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneTotalDeduction.setText("0.00");
        paymentPaneTotalDeduction.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneTotalDeduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 580, 150, -1));

        GenarateSalary.setBackground(new java.awt.Color(0, 153, 204));
        GenarateSalary.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        GenarateSalary.setText("Genarate Salary & Slip");
        GenarateSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenarateSalaryActionPerformed(evt);
            }
        });
        p3_PaymentPane.add(GenarateSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 569, 169, -1));

        paymentPaneTotalEarning.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        paymentPaneTotalEarning.setText("0.00");
        p3_PaymentPane.add(paymentPaneTotalEarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 489, -1, -1));

        paymentPaneNetPay.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        paymentPaneNetPay.setText("0.00");
        p3_PaymentPane.add(paymentPaneNetPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 529, -1, -1));

        jLabel99.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(51, 51, 51));
        jLabel99.setText("First Name :");
        p3_PaymentPane.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        paymentPaneFirstName.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneFirstName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        p3_PaymentPane.add(paymentPaneFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 180, 150, -1));

        paymentPaneClear.setBackground(new java.awt.Color(255, 102, 0));
        paymentPaneClear.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPaneClear.setForeground(new java.awt.Color(255, 255, 255));
        paymentPaneClear.setText("Clear");
        paymentPaneClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentPaneClearActionPerformed(evt);
            }
        });
        p3_PaymentPane.add(paymentPaneClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 663, 169, 27));

        paymentPanePayment.setBackground(new java.awt.Color(0, 0, 102));
        paymentPanePayment.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPanePayment.setForeground(new java.awt.Color(255, 255, 255));
        paymentPanePayment.setText("Payment");
        paymentPanePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentPanePaymentActionPerformed(evt);
            }
        });
        p3_PaymentPane.add(paymentPanePayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 621, 169, 31));

        slipArea.setEditable(false);
        slipArea.setColumns(20);
        slipArea.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        slipArea.setRows(5);
        slipArea.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 51)));
        jScrollPane5.setViewportView(slipArea);

        p3_PaymentPane.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(521, 46, 290, 590));

        paymentPanePrint.setBackground(new java.awt.Color(0, 0, 102));
        paymentPanePrint.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentPanePrint.setForeground(new java.awt.Color(255, 255, 255));
        paymentPanePrint.setText("Print");
        paymentPanePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentPanePrintActionPerformed(evt);
            }
        });
        p3_PaymentPane.add(paymentPanePrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 663, 169, 27));

        jLabel100.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(51, 51, 51));
        jLabel100.setText("Year");
        p3_PaymentPane.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 138, -1, -1));

        jLabel101.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(51, 51, 51));
        jLabel101.setText("Month");
        p3_PaymentPane.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 138, -1, -1));

        paymentPaneStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paymentPaneStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3_PaymentPane.add(paymentPaneStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 279, 180, 30));

        yearlbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        yearlbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        p3_PaymentPane.add(yearlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 60, 20));

        monthlbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        monthlbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        p3_PaymentPane.add(monthlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 100, 20));

        add(p3_PaymentPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        p3_AllowancePane.setBackground(new java.awt.Color(200, 210, 255));
        p3_AllowancePane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p3_AllowancePane.setToolTipText("");
        p3_AllowancePane.setMinimumSize(new java.awt.Dimension(810, 712));
        p3_AllowancePane.setPreferredSize(new java.awt.Dimension(810, 712));

        header4.setBackground(new java.awt.Color(0, 0, 104));
        header4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Allowance Salary :");

        javax.swing.GroupLayout header4Layout = new javax.swing.GroupLayout(header4);
        header4.setLayout(header4Layout);
        header4Layout.setHorizontalGroup(
            header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addContainerGap(681, Short.MAX_VALUE))
        );
        header4Layout.setVerticalGroup(
            header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnGoBack8.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack8.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack8.setText("Go Back");
        btnGoBack8.setBorderPainted(false);
        btnGoBack8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack8ActionPerformed(evt);
            }
        });

        allowancePaneSearchEmpidlb.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        allowancePaneSearchEmpidlb.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneSearchEmpidlb.setText("EmpNo");

        allowancePaneSearchEmpidtxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneSearchEmpidtxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneSearchEmpidtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneSearchEmpidtxtKeyPressed(evt);
            }
        });

        allowanceSearchbtn.setBackground(new java.awt.Color(0, 153, 204));
        allowanceSearchbtn.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowanceSearchbtn.setText("Search");
        allowanceSearchbtn.setBorder(null);
        allowanceSearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowanceSearchbtnActionPerformed(evt);
            }
        });

        allowancePaneEmpnolbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        allowancePaneEmpnolbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneEmpnolbl.setText("EmpNo :");

        allowancePaneCalculatebtn.setBackground(new java.awt.Color(0, 0, 102));
        allowancePaneCalculatebtn.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneCalculatebtn.setForeground(new java.awt.Color(255, 255, 255));
        allowancePaneCalculatebtn.setText("Calculate");
        allowancePaneCalculatebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allowancePaneCalculatebtnMouseClicked(evt);
            }
        });
        allowancePaneCalculatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowancePaneCalculatebtnActionPerformed(evt);
            }
        });

        allowancePaneFnamelbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        allowancePaneFnamelbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneFnamelbl.setText("First Name :");

        allowancePaneLnamelbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        allowancePaneLnamelbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneLnamelbl.setText("Last Name :");

        allowancePaneBasicsalarylbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        allowancePaneBasicsalarylbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneBasicsalarylbl.setText("Basic Salary :");

        allowancePaneDeparmentlbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        allowancePaneDeparmentlbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneDeparmentlbl.setText("Department :");

        allowancePaneOvertimelbl.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneOvertimelbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneOvertimelbl.setText("Over Time :");

        allowancePanebudetarylbl.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePanebudetarylbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePanebudetarylbl.setText("Budgetary :");

        allowancePaneBonuslbl.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneBonuslbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneBonuslbl.setText("Attendannce :");

        allowancePaneOtherlbl.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneOtherlbl.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneOtherlbl.setText("Other:");

        allowancePaneRateOfPerHour.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneRateOfPerHour.setForeground(new java.awt.Color(51, 51, 51));
        allowancePaneRateOfPerHour.setText("Rate Of Per Hour :");

        allowancePaneTotalAmount.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        allowancePaneTotalAmount.setText("Total Amount : ");

        allowancePaneSavebtn.setBackground(new java.awt.Color(0, 0, 102));
        allowancePaneSavebtn.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        allowancePaneSavebtn.setForeground(new java.awt.Color(255, 255, 255));
        allowancePaneSavebtn.setText("Save");
        allowancePaneSavebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowancePaneSavebtnActionPerformed(evt);
            }
        });

        allowancePaneClearbtn.setBackground(new java.awt.Color(204, 102, 0));
        allowancePaneClearbtn.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        allowancePaneClearbtn.setForeground(new java.awt.Color(255, 255, 255));
        allowancePaneClearbtn.setText("Clear");
        allowancePaneClearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowancePaneClearbtnActionPerformed(evt);
            }
        });

        allowancePaneEmpNotxt.setEditable(false);
        allowancePaneEmpNotxt.setBackground(new java.awt.Color(255, 255, 255));
        allowancePaneEmpNotxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneEmpNotxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneEmpNotxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allowancePaneEmpNotxtMouseClicked(evt);
            }
        });
        allowancePaneEmpNotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneEmpNotxtKeyPressed(evt);
            }
        });

        allowancePaneFnametxt.setEditable(false);
        allowancePaneFnametxt.setBackground(new java.awt.Color(255, 255, 255));
        allowancePaneFnametxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneFnametxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneFnametxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneFnametxtKeyPressed(evt);
            }
        });

        allowancePaneLnametxt.setEditable(false);
        allowancePaneLnametxt.setBackground(new java.awt.Color(255, 255, 255));
        allowancePaneLnametxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneLnametxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneLnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowancePaneLnametxtActionPerformed(evt);
            }
        });
        allowancePaneLnametxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneLnametxtKeyPressed(evt);
            }
        });

        allowancePaneBasicSalarytxt.setEditable(false);
        allowancePaneBasicSalarytxt.setBackground(new java.awt.Color(255, 255, 255));
        allowancePaneBasicSalarytxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneBasicSalarytxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneBasicSalarytxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneBasicSalarytxtKeyPressed(evt);
            }
        });

        allowancePaneDepartmenttxt.setEditable(false);
        allowancePaneDepartmenttxt.setBackground(new java.awt.Color(255, 255, 255));
        allowancePaneDepartmenttxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneDepartmenttxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneDepartmenttxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneDepartmenttxtKeyPressed(evt);
            }
        });

        allowancePaneOvertimetxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneOvertimetxt.setText("0");
        allowancePaneOvertimetxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneOvertimetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneOvertimetxtKeyPressed(evt);
            }
        });

        allowancePanebudgetaryltxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePanebudgetaryltxt.setText("0.0");
        allowancePanebudgetaryltxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePanebudgetaryltxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePanebudgetaryltxtKeyPressed(evt);
            }
        });

        allowancePaneattentxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneattentxt.setText("0.0");
        allowancePaneattentxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneattentxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneattentxtKeyPressed(evt);
            }
        });

        allowancePaneOthertxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneOthertxt.setText("0");
        allowancePaneOthertxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        allowancePaneOthertxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allowancePaneOthertxtKeyPressed(evt);
            }
        });

        allowancePaneRateOfPerHourtxt.setEditable(false);
        allowancePaneRateOfPerHourtxt.setBackground(new java.awt.Color(255, 255, 255));
        allowancePaneRateOfPerHourtxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneRateOfPerHourtxt.setText("0.0");
        allowancePaneRateOfPerHourtxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        allowancePaneUpdatebtn.setBackground(new java.awt.Color(0, 153, 204));
        allowancePaneUpdatebtn.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        allowancePaneUpdatebtn.setForeground(new java.awt.Color(255, 255, 255));
        allowancePaneUpdatebtn.setText("Update");
        allowancePaneUpdatebtn.setBorder(null);
        allowancePaneUpdatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowancePaneUpdatebtnActionPerformed(evt);
            }
        });

        allowancePaneTotalAmountDislbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        allowancePaneTotalAmountDislbl.setText("0.00");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        allowanceTable.setBackground(new java.awt.Color(122, 204, 255));
        allowanceTable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        allowanceTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        allowanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        allowanceTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        allowanceTable.setFocusable(false);
        allowanceTable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        allowanceTable.setShowVerticalLines(false);
        allowanceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allowanceTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(allowanceTable);

        jLabel55.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel55.setText("Please Enter the Value");

        allowancePaneOT.setEditable(false);
        allowancePaneOT.setBackground(new java.awt.Color(255, 255, 255));
        allowancePaneOT.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        allowancePaneOT.setText("0.0");
        allowancePaneOT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        jLabel85.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(51, 51, 51));
        jLabel85.setText("OT :");

        jLabel57.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.darkGray));

        jLabel58.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.darkGray));

        jLabel59.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        javax.swing.GroupLayout p3_AllowancePaneLayout = new javax.swing.GroupLayout(p3_AllowancePane);
        p3_AllowancePane.setLayout(p3_AllowancePaneLayout);
        p3_AllowancePaneLayout.setHorizontalGroup(
            p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(allowancePaneSearchEmpidlb))
            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(allowancePaneSearchEmpidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(allowanceSearchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(header4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(allowancePaneFnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(allowancePaneDeparmentlbl))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(allowancePaneBasicsalarylbl))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(allowancePaneDepartmenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(allowancePaneFnamelbl))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(allowancePaneLnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(allowancePaneLnamelbl))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(allowancePaneEmpnolbl))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(allowancePaneBasicSalarytxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(allowancePaneEmpNotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(allowancePaneOvertimelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(allowancePaneBonuslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(allowancePaneOT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(allowancePaneRateOfPerHour))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(allowancePaneattentxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(allowancePaneTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(allowancePaneRateOfPerHourtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(allowancePaneOvertimetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(allowancePaneTotalAmountDislbl, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(allowancePanebudetarylbl, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(allowancePanebudgetaryltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(allowancePaneOtherlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(allowancePaneCalculatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(allowancePaneOthertxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addComponent(btnGoBack8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(allowancePaneClearbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(allowancePaneUpdatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(allowancePaneSavebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        p3_AllowancePaneLayout.setVerticalGroup(
            p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(allowancePaneSearchEmpidlb))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(allowancePaneSearchEmpidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(allowanceSearchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(header4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(allowancePaneFnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)
                                .addComponent(allowancePaneDeparmentlbl))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(allowancePaneBasicsalarylbl))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(allowancePaneDepartmenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(allowancePaneFnamelbl))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(allowancePaneLnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(allowancePaneLnamelbl))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(allowancePaneEmpnolbl))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(allowancePaneBasicSalarytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(allowancePaneEmpNotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(allowancePaneRateOfPerHour))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(allowancePaneCalculatebtn))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(allowancePaneattentxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(allowancePaneTotalAmount))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(allowancePaneRateOfPerHourtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel55))
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(allowancePaneTotalAmountDislbl))
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                        .addComponent(allowancePaneOvertimelbl)
                                        .addGap(25, 25, 25)
                                        .addComponent(allowancePanebudgetaryltxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(allowancePaneOvertimetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel85)
                                    .addComponent(allowancePaneOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                                        .addComponent(allowancePaneBonuslbl)
                                        .addGap(25, 25, 25)
                                        .addComponent(allowancePaneOthertxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(allowancePanebudetarylbl))
                    .addGroup(p3_AllowancePaneLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(allowancePaneOtherlbl)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(p3_AllowancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGoBack8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allowancePaneClearbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allowancePaneUpdatebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allowancePaneSavebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(p3_AllowancePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        jLabel56.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.darkGray));
        add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        p3_DeductionPane.setBackground(new java.awt.Color(200, 210, 255));
        p3_DeductionPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p3_DeductionPane.setToolTipText("");
        p3_DeductionPane.setPreferredSize(new java.awt.Dimension(810, 712));

        header9.setBackground(new java.awt.Color(0, 0, 104));
        header9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Deduction Salary :");

        javax.swing.GroupLayout header9Layout = new javax.swing.GroupLayout(header9);
        header9.setLayout(header9Layout);
        header9Layout.setHorizontalGroup(
            header9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header9Layout.setVerticalGroup(
            header9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnGoBack10.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack10.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack10.setText("Go Back");
        btnGoBack10.setBorderPainted(false);
        btnGoBack10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack10ActionPerformed(evt);
            }
        });

        deductionPaneSearchEmpidtxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneSearchEmpidtxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        deductionPaneSearchEmpidtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPaneSearchEmpidtxtKeyPressed(evt);
            }
        });

        deductionPaneSearchEmpidlb.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        deductionPaneSearchEmpidlb.setText("EmpNo");

        deductionSearchbtn.setBackground(new java.awt.Color(0, 153, 204));
        deductionSearchbtn.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionSearchbtn.setText("Search");
        deductionSearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionSearchbtnActionPerformed(evt);
            }
        });

        jLabel78.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.darkGray));

        deductionPaneEmpNotxt.setEditable(false);
        deductionPaneEmpNotxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneEmpNotxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        deductionPaneEmpNotxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deductionPaneEmpNotxtMouseClicked(evt);
            }
        });
        deductionPaneEmpNotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPaneEmpNotxtKeyPressed(evt);
            }
        });

        deductionPaneEmpnolbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneEmpnolbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneEmpnolbl.setText("EmpNo");

        deductionPaneFnamelbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneFnamelbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneFnamelbl.setText("First Name");

        deductionPaneLnamelbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneLnamelbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneLnamelbl.setText("Last Name");

        deductionPaneBasicsalarylbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneBasicsalarylbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneBasicsalarylbl.setText("Basic Salary");

        deductionPaneDeparmentlbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneDeparmentlbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneDeparmentlbl.setText("Department");

        deductionPaneDepartmenttxt.setEditable(false);
        deductionPaneDepartmenttxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneDepartmenttxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        deductionPaneDepartmenttxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPaneDepartmenttxtKeyPressed(evt);
            }
        });

        deductionPaneBasicSalarytxt.setEditable(false);
        deductionPaneBasicSalarytxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneBasicSalarytxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        deductionPaneBasicSalarytxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPaneBasicSalarytxtKeyPressed(evt);
            }
        });

        deductionPaneLnametxt.setEditable(false);
        deductionPaneLnametxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneLnametxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        deductionPaneLnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionPaneLnametxtActionPerformed(evt);
            }
        });
        deductionPaneLnametxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPaneLnametxtKeyPressed(evt);
            }
        });

        deductionPaneFnametxt.setEditable(false);
        deductionPaneFnametxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneFnametxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        deductionPaneFnametxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPaneFnametxtKeyPressed(evt);
            }
        });

        deductionPaneDesignationtxt.setEditable(false);
        deductionPaneDesignationtxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneDesignationtxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        deductionPaneDesignationtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPaneDesignationtxtKeyPressed(evt);
            }
        });

        deductionPaneDesignationlbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneDesignationlbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneDesignationlbl.setText("Designation");

        deductionPaneUplbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneUplbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneUplbl.setText("Update Salary by :");

        deductionPanePercentageRadio.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPanePercentageRadio.setText("Percentage (%)");
        deductionPanePercentageRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionPanePercentageRadioActionPerformed(evt);
            }
        });
        deductionPanePercentageRadio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPanePercentageRadioKeyPressed(evt);
            }
        });

        deductionPaneAmountRadio.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneAmountRadio.setText("Amount");
        deductionPaneAmountRadio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deductionPaneAmountRadioMouseClicked(evt);
            }
        });
        deductionPaneAmountRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionPaneAmountRadioActionPerformed(evt);
            }
        });

        deductionPanePercentagelbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPanePercentagelbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPanePercentagelbl.setText("Percentage :");

        deductionPaneResonlbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneResonlbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneResonlbl.setText("Reason :");

        deductionPanePercentagetxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPanePercentagetxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        deductionPaneAmountlbl.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        deductionPaneAmountlbl.setForeground(new java.awt.Color(51, 51, 51));
        deductionPaneAmountlbl.setText("Amount :");

        deductionPaneAmounttxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneAmounttxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        deductionPaneResontxt.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneResontxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        deductionPaneResontxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionPaneResontxtKeyPressed(evt);
            }
        });

        additionalLeaves.setEditable(false);
        additionalLeaves.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        deductionPaneTotalDeductionlbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        deductionPaneTotalDeductionlbl.setText("Total Deduction :");

        deductionPaneTotalDeduction.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        deductionPaneTotalDeduction.setText("0.00");

        deductionPaneSadlbl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        deductionPaneSadlbl.setText("Salary After Deduction :");

        deductionPaneSad.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        deductionPaneSad.setText("0.00");

        deductionPaneSave.setBackground(new java.awt.Color(0, 0, 102));
        deductionPaneSave.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneSave.setForeground(new java.awt.Color(255, 255, 255));
        deductionPaneSave.setText("Save");
        deductionPaneSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionPaneSaveActionPerformed(evt);
            }
        });

        deductionPaneCalculate.setBackground(new java.awt.Color(0, 0, 102));
        deductionPaneCalculate.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneCalculate.setForeground(new java.awt.Color(255, 255, 255));
        deductionPaneCalculate.setText("Calculate");
        deductionPaneCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionPaneCalculateActionPerformed(evt);
            }
        });

        deductionPaneClear.setBackground(new java.awt.Color(255, 153, 0));
        deductionPaneClear.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneClear.setForeground(new java.awt.Color(255, 255, 255));
        deductionPaneClear.setText("Clear");
        deductionPaneClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionPaneClearActionPerformed(evt);
            }
        });

        deductionPaneUpdate.setBackground(new java.awt.Color(0, 153, 204));
        deductionPaneUpdate.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deductionPaneUpdate.setForeground(new java.awt.Color(255, 255, 255));
        deductionPaneUpdate.setText("Update");
        deductionPaneUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductionPaneUpdateActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Deduction of Additional Leaves");

        additionalLeavesamount.setEditable(false);
        additionalLeavesamount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Additional Leaves");

        jLabel79.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.darkGray));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        deductionTable.setBackground(new java.awt.Color(122, 204, 255));
        deductionTable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        deductionTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deductionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        deductionTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        deductionTable.setFocusable(false);
        deductionTable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        deductionTable.setShowVerticalLines(false);
        deductionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deductionTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(deductionTable);

        javax.swing.GroupLayout p3_DeductionPaneLayout = new javax.swing.GroupLayout(p3_DeductionPane);
        p3_DeductionPane.setLayout(p3_DeductionPaneLayout);
        p3_DeductionPaneLayout.setHorizontalGroup(
            p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                                .addComponent(deductionPanePercentagelbl)
                                .addGap(5, 5, 5)
                                .addComponent(deductionPanePercentagetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                                .addComponent(deductionPaneUplbl)
                                .addGap(0, 0, 0)
                                .addComponent(deductionPanePercentageRadio)))
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(deductionPaneAmountRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p3_DeductionPaneLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(deductionPaneAmountlbl)
                                .addGap(5, 5, 5)
                                .addComponent(deductionPaneAmounttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(deductionPaneResonlbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deductionPaneResontxt)
                        .addGap(58, 58, 58)))
                .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addComponent(deductionPaneTotalDeductionlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(deductionPaneTotalDeduction))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p3_DeductionPaneLayout.createSequentialGroup()
                        .addComponent(deductionPaneSadlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(deductionPaneSad))
                    .addComponent(deductionPaneCalculate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(94, 94, 94))
            .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(deductionPaneSearchEmpidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(deductionPaneSearchEmpidlb))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(deductionSearchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deductionPaneEmpnolbl)
                            .addComponent(deductionPaneFnamelbl)
                            .addComponent(deductionPaneLnamelbl))
                        .addGap(14, 14, 14)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deductionPaneEmpNotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deductionPaneFnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deductionPaneLnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(deductionPaneBasicsalarylbl)
                        .addGap(8, 8, 8)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deductionPaneDepartmenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deductionPaneDesignationtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deductionPaneBasicSalarytxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(670, 670, 670)
                        .addComponent(additionalLeavesamount, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(deductionPaneDeparmentlbl))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(deductionPaneDesignationlbl))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(670, 670, 670)
                        .addComponent(additionalLeaves, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deductionPaneClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deductionPaneUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(deductionPaneSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnGoBack10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(header9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p3_DeductionPaneLayout.setVerticalGroup(
            p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(deductionPaneSearchEmpidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(header9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(deductionPaneSearchEmpidlb))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(deductionSearchbtn))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(deductionPaneEmpnolbl)
                        .addGap(21, 21, 21)
                        .addComponent(deductionPaneFnamelbl)
                        .addGap(24, 24, 24)
                        .addComponent(deductionPaneLnamelbl))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(deductionPaneEmpNotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(deductionPaneFnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(deductionPaneLnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(deductionPaneBasicsalarylbl))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(deductionPaneDepartmenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(deductionPaneDesignationtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(deductionPaneBasicSalarytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(additionalLeavesamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(deductionPaneDeparmentlbl))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(deductionPaneDesignationlbl))
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(additionalLeaves, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deductionPaneUplbl)
                            .addComponent(deductionPanePercentageRadio)
                            .addComponent(deductionPaneAmountRadio))
                        .addGap(17, 17, 17)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deductionPanePercentagelbl)
                            .addComponent(deductionPaneAmountlbl)
                            .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(deductionPaneAmounttxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                .addComponent(deductionPanePercentagetxt, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(15, 15, 15)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deductionPaneResontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deductionPaneResonlbl))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p3_DeductionPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deductionPaneTotalDeductionlbl)
                            .addComponent(deductionPaneTotalDeduction))
                        .addGap(32, 32, 32)
                        .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deductionPaneSadlbl)
                            .addComponent(deductionPaneSad))
                        .addGap(18, 18, 18)
                        .addComponent(deductionPaneCalculate)
                        .addGap(30, 30, 30)))
                .addGroup(p3_DeductionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(p3_DeductionPaneLayout.createSequentialGroup()
                        .addComponent(deductionPaneUpdate)
                        .addGap(51, 51, 51)
                        .addComponent(deductionPaneSave)
                        .addGap(50, 50, 50)
                        .addComponent(deductionPaneClear)))
                .addGap(30, 30, 30)
                .addComponent(btnGoBack10))
        );

        add(p3_DeductionPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        p3_PaymentSummeryPane.setBackground(new java.awt.Color(200, 210, 255));
        p3_PaymentSummeryPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        p3_PaymentSummeryPane.setToolTipText("");
        p3_PaymentSummeryPane.setPreferredSize(new java.awt.Dimension(810, 712));

        header11.setBackground(new java.awt.Color(0, 0, 104));
        header11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Payment Summery :");

        javax.swing.GroupLayout header11Layout = new javax.swing.GroupLayout(header11);
        header11.setLayout(header11Layout);
        header11Layout.setHorizontalGroup(
            header11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel102)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header11Layout.setVerticalGroup(
            header11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnGoBack12.setBackground(new java.awt.Color(255, 0, 51));
        btnGoBack12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btnGoBack12.setForeground(new java.awt.Color(255, 255, 255));
        btnGoBack12.setText("Go Back");
        btnGoBack12.setBorderPainted(false);
        btnGoBack12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoBack12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBack12ActionPerformed(evt);
            }
        });

        salaryUpdateSearch1.setBackground(new java.awt.Color(0, 153, 204));
        salaryUpdateSearch1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        salaryUpdateSearch1.setText("Search");
        salaryUpdateSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryUpdateSearch1ActionPerformed(evt);
            }
        });

        jScrollPane6.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        paymentsummeryTable.setBackground(new java.awt.Color(122, 204, 255));
        paymentsummeryTable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        paymentsummeryTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paymentsummeryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paymentsummeryTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        paymentsummeryTable.setFocusable(false);
        paymentsummeryTable.setName("h"); // NOI18N
        paymentsummeryTable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        paymentsummeryTable.setShowVerticalLines(false);
        jScrollPane6.setViewportView(paymentsummeryTable);

        paymentSummeryPaneYear.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentSummeryPaneYear.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        paymentSummeryPaneYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                paymentSummeryPaneYearKeyPressed(evt);
            }
        });

        paymentSummeryPaneMonth.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paymentSummeryPaneMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        paymentSummeryPaneMonth.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        paymentSummeryPaneMonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentSummeryPaneMonthMouseClicked(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(51, 51, 51));
        jLabel103.setText("Month : ");

        jLabel104.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(51, 51, 51));
        jLabel104.setText("Year : ");

        jLabel105.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel105.setText("Total Payments :");

        paymentPaneSummeryTot.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        paymentPaneSummeryTot.setText("0.00");

        paymentSummeryPrint.setBackground(new java.awt.Color(0, 0, 102));
        paymentSummeryPrint.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        paymentSummeryPrint.setForeground(new java.awt.Color(255, 255, 255));
        paymentSummeryPrint.setText("Print");
        paymentSummeryPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentSummeryPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout p3_PaymentSummeryPaneLayout = new javax.swing.GroupLayout(p3_PaymentSummeryPane);
        p3_PaymentSummeryPane.setLayout(p3_PaymentSummeryPaneLayout);
        p3_PaymentSummeryPaneLayout.setHorizontalGroup(
            p3_PaymentSummeryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_PaymentSummeryPaneLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnGoBack12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paymentSummeryPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(p3_PaymentSummeryPaneLayout.createSequentialGroup()
                .addGroup(p3_PaymentSummeryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3_PaymentSummeryPaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel104)
                        .addGap(3, 3, 3)
                        .addComponent(paymentSummeryPaneYear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel103)
                        .addGap(11, 11, 11)
                        .addComponent(paymentSummeryPaneMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(salaryUpdateSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p3_PaymentSummeryPaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p3_PaymentSummeryPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel105)
                .addGap(24, 24, 24)
                .addComponent(paymentPaneSummeryTot, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addComponent(header11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p3_PaymentSummeryPaneLayout.setVerticalGroup(
            p3_PaymentSummeryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3_PaymentSummeryPaneLayout.createSequentialGroup()
                .addComponent(header11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(p3_PaymentSummeryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel104)
                    .addComponent(salaryUpdateSearch1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paymentSummeryPaneYear)
                    .addGroup(p3_PaymentSummeryPaneLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel103))
                    .addComponent(paymentSummeryPaneMonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(p3_PaymentSummeryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel105)
                    .addComponent(paymentPaneSummeryTot))
                .addGap(54, 54, 54)
                .addGroup(p3_PaymentSummeryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGoBack12)
                    .addComponent(paymentSummeryPrint))
                .addGap(33, 33, 33))
        );

        add(p3_PaymentSummeryPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoBack9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack9ActionPerformed
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
       
        p3.setVisible(true);
     
    }//GEN-LAST:event_btnGoBack9ActionPerformed

    private void salaryUpdateSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryUpdateSearchActionPerformed
        if(updateSalarySearchtxt.getText().isEmpty())
        {
            updateSalarySearchtxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The EmpNo field is empty !!");
        }
        else
        {
            try {
                String sql ="SELECT EmpNo,NIC_No,First_Name,Last_Name,DOB,Address1,Address2,Contact_No,Email,Department,Designation,Educational_Level,Gender,Status,Basic_Salary FROM employee where EmpNo=? ";
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=(PreparedStatement) con.prepareStatement(sql);

                pst.setString(1, updateSalarySearchtxt.getText());
                rs= (ResultSet) pst.executeQuery();
                if(rs.next()) {
                    txtempno4.setText(rs.getString("EmpNo"));
                    txtNIC4.setText(rs.getString("NIC_No"));
                    txtFname4.setText(rs.getString("First_Name"));
                    txtLname4.setText(rs.getString("Last_Name"));
                    txtDOB4.setText(rs.getString("DOB"));
                    txtAdress4.setText(rs.getString("Address1"));
                    txtAdressline4.setText(rs.getString("Address2"));
                    txtContactNo4.setText(rs.getString("Contact_No"));
                    txtEmail4.setText(rs.getString("Email"));
                    txtSdepartment.setText(rs.getString("Department"));
                    txtSdesignation5.setText(rs.getString("Designation"));
                    txteducationallevel4.setText(rs.getString("Educational_Level"));
                    txtgender4.setText(rs.getString("Gender"));
                    txtstatus4.setText(rs.getString("Status"));
                    txtBasicSalary4.setText(rs.getString("Basic_Salary"));

                }
            }

            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_salaryUpdateSearchActionPerformed

    private void updateSalarySearchtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateSalarySearchtxtKeyPressed
        updateSalarySearchtxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_updateSalarySearchtxtKeyPressed

    private void updatePercentageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePercentageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatePercentageActionPerformed

    private void updateAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateAmountActionPerformed

    private void updatepercentageRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatepercentageRadioActionPerformed
        updatepercentageRadio.setSelected(true);
        updateAmountRadio.setSelected(false);

        updateAmount.setEnabled(false);
        updatePercentage.setEditable(true);
        updatePercentage.setEnabled(true);
        updateAmount.setText("");
    }//GEN-LAST:event_updatepercentageRadioActionPerformed

    private void updateAmountRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAmountRadioActionPerformed
        updatepercentageRadio.setSelected(false);
        updateAmountRadio.setSelected(true);

        updatePercentage.setEnabled(false);
        updateAmount.setEditable(true);
        updateAmount.setEnabled(true);
        updatePercentage.setText("");
    }//GEN-LAST:event_updateAmountRadioActionPerformed

    private void basicSalaryUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basicSalaryUpdateActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to update salary?","Update Record",JOptionPane.YES_NO_OPTION);
        if(p==0){
            try{
                float salary = Float.parseFloat(txtBasicSalary4.getText());

                if(updatepercentageRadio.isSelected()== true){
                    int getPercentage = Integer.parseInt(updatePercentage.getText());
                    float calcPercentage = salary /100 * getPercentage + salary;
                    String xP = String.valueOf(calcPercentage);
                    txtBasicSalary4.setText(xP);
                }

                else if(updateAmountRadio.isSelected()==true){
                    int getAmt = Integer.parseInt(updateAmount.getText());
                    float calcAmount = salary + getAmt;
                    String xA = String.valueOf(calcAmount);
                    txtBasicSalary4.setText(xA);
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

            try{

                String sql ="UPDATE employee SET Basic_Salary=? where EmpNo=? ";
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, txtBasicSalary4.getText());
                pst.setString(2, txtempno4.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Updated Successfully");

            }

            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());

            }
        }
    }//GEN-LAST:event_basicSalaryUpdateActionPerformed

    private void txtempno4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtempno4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempno4FocusGained

    private void txtempno4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtempno4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempno4FocusLost

    private void txtempno4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtempno4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempno4ActionPerformed

    private void txtempno4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtempno4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempno4KeyPressed

    private void txtNIC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNIC4ActionPerformed

    private void txtFname4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFname4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFname4ActionPerformed

    private void txtLname4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLname4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLname4ActionPerformed

    private void txtDOB4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOB4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB4FocusGained

    private void txtDOB4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDOB4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB4FocusLost

    private void txtDOB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDOB4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB4ActionPerformed

    private void txtAdress4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdress4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdress4ActionPerformed

    private void txtAdressline4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdressline4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdressline4ActionPerformed

    private void txtContactNo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactNo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactNo4ActionPerformed

    private void txtEmail4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmail4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmail4ActionPerformed

    private void txtSdepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdepartmentActionPerformed

    private void txtSdesignation5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdesignation5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdesignation5ActionPerformed

    private void txteducationallevel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txteducationallevel4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txteducationallevel4ActionPerformed

    private void txtBasicSalary4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBasicSalary4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBasicSalary4ActionPerformed

    private void txtBasicSalary4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBasicSalary4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBasicSalary4KeyPressed

    private void txtgender4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgender4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgender4ActionPerformed

    private void txtstatus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstatus4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstatus4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        updateSalarySearchtxt.setText("");
        txtempno4.setText("");
        txtNIC4.setText("");
        txtFname4.setText("");
        txtLname4.setText("");
        txtDOB4.setText("");
        txtAdress4.setText("");
        txtAdressline4.setText("");
        txtContactNo4.setText("");
        txtEmail4.setText("");
        txtSdepartment.setText("");
        txtSdesignation5.setText("");
        txteducationallevel4.setText("");
        txtgender4.setText("");
        txtstatus4.setText("");
        txtBasicSalary4.setText("");
        updatePercentage.setText("");
        updateAmount.setText("");
        updatepercentageRadio.setSelected(false);
        updateAmountRadio.setSelected(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnGoBack11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack11ActionPerformed
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
      
        p3.setVisible(true);
      
    }//GEN-LAST:event_btnGoBack11ActionPerformed

    private void paymentPaneSearchtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentPaneSearchtxtKeyPressed
        paymentPaneSearchtxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_paymentPaneSearchtxtKeyPressed

    private void paymentPaneSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentPaneSearchActionPerformed

        String paymentPaneSalary = "0";
        String paymentPaneOT = "0";
        String paymentPaneMedi = "0";
        String paymentPaneBonu = "0";
        String paymentPaneOth = "0";
        String paymentpanetotallow="0";
        String paymentPaneDA="0";

        if(paymentPaneSearchtxt.getText().isEmpty())
        {
            paymentPaneSearchtxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The EmpNo field is empty !!");
        }
        else
        {

            try {
                String emp ="SELECT EmpNo,First_Name,Last_Name,Department,Designation,Basic_Salary FROM employee where EmpNo=? ";
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=(PreparedStatement) con.prepareStatement(emp);

                pst.setString(1, paymentPaneSearchtxt.getText());
                rs= (ResultSet) pst.executeQuery();
                if(rs.next()) {
                    paymentPaneEmpNo.setText(rs.getString("EmpNo"));
                    paymentPaneFirstName.setText(rs.getString("First_Name"));
                    paymentPaneLastName.setText(rs.getString("Last_Name"));
                    paymentPaneDeparment.setText(rs.getString("Department"));
                    paymentPaneDesignation.setText(rs.getString("Designation"));
                    paymentPaneSalary =(rs.getString("Basic_Salary"));

                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

            try {
                String allo ="SELECT OverTime,Budgetary,Attendance,Other,Total_Amount FROM allowance where EmpNo=? ";
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=(PreparedStatement) con.prepareStatement(allo);

                pst.setString(1, paymentPaneSearchtxt.getText());
                rs= (ResultSet) pst.executeQuery();
                if(rs.next()) {
                    paymentPaneOT =(rs.getString("OverTime"));
                    paymentPaneMedi =(rs.getString("Budgetary"));
                    paymentPaneBonu = (rs.getString("Attendance"));
                    paymentPaneOth =(rs.getString("Other"));
                    paymentpanetotallow =(rs.getString("Total_Amount"));

                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

            try {
                String ded ="SELECT Deduction_Reason,Deduction_Amount FROM deduction where EmpNo=? ";
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=(PreparedStatement) con.prepareStatement(ded);

                pst.setString(1, paymentPaneSearchtxt.getText());
                rs= (ResultSet) pst.executeQuery();
                if(rs.next()) {
                    paymentPaneDeductionDetails.setText(rs.getString("Deduction_Reason"));
                    paymentPaneDA=(rs.getString("Deduction_Amount"));

                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

            paymentPaneBasicSalary.setText(paymentPaneSalary);
            paymentPaneOverTime.setText(paymentPaneOT);
            paymentPaneMedical.setText(paymentPaneMedi);
            paymentPaneBonus.setText(paymentPaneBonu);
            paymentPaneOther.setText(paymentPaneOth);
            paymentPaneTotalDeduction.setText(paymentPaneDA);
            paymentPaneTotalEarning.setText(paymentpanetotallow);

        }
    }//GEN-LAST:event_paymentPaneSearchActionPerformed

    private void paymentPaneDesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentPaneDesignationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentPaneDesignationActionPerformed

    private void paymentPaneOverTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentPaneOverTimeActionPerformed

    }//GEN-LAST:event_paymentPaneOverTimeActionPerformed

    private void GenarateSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenarateSalaryActionPerformed
        

            String paymentpanetotallo = paymentPaneTotalEarning.getText();
            String paymentPaneDAd =   paymentPaneTotalDeduction.getText();
            String paymentPaneBSalary = paymentPaneBasicSalary.getText();

            float calcTotal = 0.0f;
            float x = 0.0f;

            calcTotal = Float.parseFloat(paymentPaneBSalary);
            x = Float.valueOf(paymentpanetotallo);

            float v = Float.parseFloat(paymentPaneDAd);
            float total = calcTotal+ x -v;
            String net= String.valueOf(total);
            paymentPaneNetPay.setText(net);

            slipArea.setText("***************************************************\n");
            slipArea.setText(slipArea.getText()+"                                   PAY SLIP                       \n");
            Date obj= new Date();
            String date=obj.toString();
            slipArea.setText(slipArea.getText()+"\n"+date+"\n\n");
            slipArea.setText(slipArea.getText()+"***************************************************\n");

            slipArea.setText(slipArea.getText()+"     Employee Details \n\n");
            slipArea.setText(slipArea.getText()+"EmpNo :                        "+paymentPaneEmpNo.getText()+" \n");
            slipArea.setText(slipArea.getText()+"Full Name :                   "+paymentPaneFirstName.getText()+" "+paymentPaneLastName.getText()+" \n");
            slipArea.setText(slipArea.getText()+"Department :                "+paymentPaneDeparment.getText()+" \n");
            slipArea.setText(slipArea.getText()+"Designation :                "+paymentPaneDesignation.getText()+" \n\n");

            slipArea.setText(slipArea.getText()+"***************************************************\n");
            slipArea.setText(slipArea.getText()+"     Salary \n\n");
            slipArea.setText(slipArea.getText()+"Basic Salary :               Rs."+paymentPaneBasicSalary.getText()+" \n");
            slipArea.setText(slipArea.getText()+"OT :                               Rs."+paymentPaneOverTime.getText()+" \n");
            slipArea.setText(slipArea.getText()+"Budgetary Allowance:                      Rs."+paymentPaneMedical.getText()+" \n");
            slipArea.setText(slipArea.getText()+"Attendance Allowance :                         Rs."+paymentPaneBonus.getText()+" \n");
            slipArea.setText(slipArea.getText()+"Other :                          Rs."+paymentPaneOther.getText()+" \n\n");
            slipArea.setText(slipArea.getText()+"***************************************************\n");
            slipArea.setText(slipArea.getText()+"     Deduction \n\n");
            slipArea.setText(slipArea.getText()+"Deduction Details:      "+paymentPaneDeductionDetails.getText()+" \n");
            slipArea.setText(slipArea.getText()+"Total Deduciton :        Rs."+paymentPaneTotalDeduction.getText()+" \n\n");
            slipArea.setText(slipArea.getText()+"***************************************************\n");
            slipArea.setText(slipArea.getText()+"     Total Salary \n\n");
            slipArea.setText(slipArea.getText()+"Total Earning :             Rs."+paymentPaneTotalEarning.getText()+" \n");
            slipArea.setText(slipArea.getText()+"NetPay :                         Rs."+paymentPaneNetPay.getText()+" \n\n");
            slipArea.setText(slipArea.getText()+"Pay to :                            "+yearlbl.getText()+" "+monthlbl.getText()+" \n\n");
            slipArea.setText(slipArea.getText()+"***************************************************\n");
        
    }//GEN-LAST:event_GenarateSalaryActionPerformed

    private void paymentPaneClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentPaneClearActionPerformed
        paymentPaneSearchtxt.setText("");
        paymentPaneEmpNo.setText("");
        paymentPaneFirstName.setText("");
        paymentPaneLastName.setText("");
        paymentPaneDeparment.setText("");
        paymentPaneDesignation.setText("");

        paymentPaneBasicSalary.setText("0.00");
        paymentPaneOverTime.setText("0.00");
        paymentPaneMedical.setText("0.00");
        paymentPaneBonus.setText("0.00");
        paymentPaneOther.setText("0.00");
        paymentPaneTotalDeduction.setText("0.00");
        paymentPaneDeductionDetails.setText("");
        paymentPaneTotalEarning.setText("0.00");
        paymentPaneNetPay.setText("0.00");
        slipArea.setText("");
     
    }//GEN-LAST:event_paymentPaneClearActionPerformed

    private void paymentPanePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentPanePaymentActionPerformed
        String paystatus =("");
        String payyear = ("");
        String paymonth = ("");
        String selepaymonth = monthlbl.getText() ;

        if(paymentPaneSearchtxt.getText().isEmpty())
        {
            paymentPaneSearchtxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Please Searh and genarate salary !!");
        }
        
        try {
            String ded ="SELECT Year,Month FROM salary_payments where EmpNo=? ";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
            pst=(PreparedStatement) con.prepareStatement(ded);

            pst.setString(1, paymentPaneSearchtxt.getText());
            rs= (ResultSet) pst.executeQuery();

            while(rs.next()) {
                payyear=(rs.getString("Year"));
                paymonth=(rs.getString("Month"));

                if(yearlbl.getText().equals(payyear) && selepaymonth.equals(paymonth) ) {
                    JOptionPane.showMessageDialog(this, "Payment is allready Successfully ");
                    paystatus ="Payment Successfull";
                }

            }

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        if(paystatus.equals("") ) {

            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to add this record!!","Add Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)
            {

                try{

                    String query = "INSERT INTO salary_payments(EmpNo,First_Name,Last_Name,Department,Designation,Basic_Salary,OT,Budgetary,Attendance,Other,Deduction_Details,Total_Deductions,Total_Earning,NetPay,Year,Month,Payment_Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst=con.prepareStatement(query);
                    String paysta = ("Payment Successfull");

                    pst.setString(1, paymentPaneEmpNo.getText());
                    pst.setString(2, paymentPaneFirstName.getText());
                    pst.setString(3, paymentPaneLastName.getText());
                    pst.setString(4, paymentPaneDeparment.getText());
                    pst.setString(5, paymentPaneDesignation.getText());
                    pst.setString(6, paymentPaneBasicSalary.getText());
                    pst.setString(7, paymentPaneOverTime.getText());
                    pst.setString(8, paymentPaneMedical.getText());
                    pst.setString(9, paymentPaneBonus.getText());
                    pst.setString(10, paymentPaneOther.getText());
                    pst.setString(11, paymentPaneDeductionDetails.getText());
                    pst.setString(12, paymentPaneTotalDeduction.getText());
                    pst.setString(13, paymentPaneTotalEarning.getText());
                    pst.setString(14, paymentPaneNetPay.getText());
                    pst.setString(15, yearlbl.getText());
                    pst.setString(16, monthlbl.getText());
                    pst.setString(17, paysta);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Payment Successfull ");

                    paymentPaneStatus.setText("Payment Successfull");
                    try {
                        String al = "DELETE FROM allowance WHERE EmpNo=?";
                        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                        pst = con.prepareStatement(al);
                        pst.setString(1, paymentPaneSearchtxt.getText());
                        pst.executeUpdate();

                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }

                    try {
                        String de = "DELETE FROM deduction WHERE EmpNo=?";
                        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                        pst = con.prepareStatement(de);
                        pst.setString(1, paymentPaneSearchtxt.getText());
                        pst.executeUpdate();

                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }

                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }

            }}

            allowancetable();
            deductiontable();
    }//GEN-LAST:event_paymentPanePaymentActionPerformed

    private void paymentPanePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentPanePrintActionPerformed
        try
        {
            slipArea.print();
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_paymentPanePrintActionPerformed

    private void btnGoBack8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack8ActionPerformed
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
       
        p3.setVisible(true);
        
    }//GEN-LAST:event_btnGoBack8ActionPerformed

    private void allowancePaneSearchEmpidtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneSearchEmpidtxtKeyPressed
        allowancePaneSearchEmpidtxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneSearchEmpidtxtKeyPressed

    private void allowanceSearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowanceSearchbtnActionPerformed
        if(allowancePaneSearchEmpidtxt.getText().isEmpty())
        {
            allowancePaneSearchEmpidtxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Search EmpNo field is empty !!");
        }

        else{

            try {
                String sql ="SELECT EmpNo,First_Name,Last_Name,Basic_Salary,Department FROM employee where EmpNo=? ";
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst=(PreparedStatement) con.prepareStatement(sql);

                pst.setString(1, allowancePaneSearchEmpidtxt.getText());
                rs= (ResultSet) pst.executeQuery();
                if(rs.next()) {
                    allowancePaneEmpNotxt.setText(rs.getString("EmpNo"));
                    allowancePaneFnametxt.setText(rs.getString("First_Name"));
                    allowancePaneLnametxt.setText(rs.getString("Last_Name"));
                    allowancePaneBasicSalarytxt.setText(rs.getString("Basic_Salary"));
                    allowancePaneDepartmenttxt.setText(rs.getString("Department"));
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_allowanceSearchbtnActionPerformed

    private void allowancePaneCalculatebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allowancePaneCalculatebtnMouseClicked
        // TODO add your handling code here:
        allowancePaneCalculatebtn.setBackground(new Color(0,0,102));
    }//GEN-LAST:event_allowancePaneCalculatebtnMouseClicked

    private void allowancePaneCalculatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowancePaneCalculatebtnActionPerformed
        if(allowancePaneOvertimetxt.getText().isEmpty())
        {
            allowancePaneOvertimetxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The OverTime field is empty !!");
        }
        else if(allowancePanebudgetaryltxt.getText().isEmpty())
        {
            allowancePanebudgetaryltxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Medical field is empty !!");
        }
        else if(allowancePaneattentxt.getText().isEmpty())
        {
            allowancePaneattentxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Bonus field is empty !!");
        }
        else if(allowancePaneOthertxt.getText().isEmpty())
        {
            allowancePaneOthertxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Other field is empty !!");
        }
        else
        {
            float salary = Float.parseFloat(allowancePaneBasicSalarytxt.getText());
            //        int salary = Integer.parseInt(s);
            int overtime = Integer.parseInt(allowancePaneOvertimetxt.getText());

            double eight = 8;
            double days = 25;
            double dbop = 0;

            double overtimeRate = 1.5;

           
            dbop = salary /days/eight;
            double rate_per_hour = dbop * overtimeRate;
            String s = String.valueOf(rate_per_hour);
            allowancePaneRateOfPerHourtxt.setText(s);

            int bud = Integer.parseInt(allowancePanebudgetaryltxt.getText());
            int att = Integer.parseInt(allowancePaneattentxt.getText());
            int other = Integer.parseInt(allowancePaneOthertxt.getText());
            int f = bud+att+other;
            double ot=overtime * rate_per_hour;
            double calc = ot+f;
            String c = String.valueOf(calc);
            String ovt= String.valueOf(ot);
            allowancePaneOT.setText(ovt);
            allowancePaneTotalAmountDislbl.setText(c);
        }
    }//GEN-LAST:event_allowancePaneCalculatebtnActionPerformed

    private void allowancePaneSavebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowancePaneSavebtnActionPerformed

        if(allowancePaneEmpNotxt.getText().isEmpty())
        {
            allowancePaneEmpNotxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The EmpNo field is empty !!");
        }
        else if(allowancePaneFnametxt.getText().isEmpty())
        {
            allowancePaneFnametxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The First Name field is empty !!");
        }
        else if(allowancePaneLnametxt.getText().isEmpty())
        {
            allowancePaneLnametxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Last Name field is empty !!");
        }
        else if( allowancePaneBasicSalarytxt.getText().isEmpty())
        {
            allowancePaneBasicSalarytxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The  Basic Salary field is empty !!");
        }

        else if(allowancePaneDepartmenttxt.getText().isEmpty())
        {
            allowancePaneDepartmenttxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Department field is empty !!");
        }
        else if(allowancePaneOvertimetxt.getText().isEmpty())
        {
            allowancePaneOvertimetxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The OverTime field is empty !!");
        }
        else if(allowancePanebudgetaryltxt.getText().isEmpty())
        {
            allowancePanebudgetaryltxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Medical field is empty !!");
        }
        else if(allowancePaneattentxt.getText().isEmpty())
        {
            allowancePaneattentxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Bonus field is empty !!");
        }
        else if(allowancePaneOthertxt.getText().isEmpty())
        {
            allowancePaneOthertxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Other field is empty !!");
        }

        else
        {
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to add this record!!","Add Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
                try{

                    String query = "INSERT INTO allowance(EmpNo,First_Name,Last_Name,Basic_Salary,Department,OT_Hours,OverTime,Budgetary,Attendance,Other,RateOfPerHour,Total_Amount) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst=con.prepareStatement(query);

                    pst.setString(1, allowancePaneEmpNotxt.getText());
                    pst.setString(2, allowancePaneFnametxt.getText());
                    pst.setString(3, allowancePaneLnametxt.getText());
                    pst.setString(4, allowancePaneBasicSalarytxt.getText());
                    pst.setString(5, allowancePaneDepartmenttxt.getText());
                    pst.setString(6, allowancePaneOvertimetxt.getText());
                    pst.setString(7, allowancePaneOT.getText());
                    pst.setString(8, allowancePanebudgetaryltxt.getText());
                    pst.setString(9, allowancePaneattentxt.getText());
                    pst.setString(10, allowancePaneOthertxt.getText());
                    pst.setString(11, allowancePaneRateOfPerHourtxt.getText());
                    pst.setString(12, allowancePaneTotalAmountDislbl.getText());

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Inserted Successfully ");

                    allowancePaneEmpNotxt.setText("");
                    allowancePaneFnametxt.setText("");
                    allowancePaneLnametxt.setText("");
                    allowancePaneBasicSalarytxt.setText("");
                    allowancePaneDepartmenttxt.setText("");
                    allowancePaneOvertimetxt.setText("0");
                    allowancePanebudgetaryltxt.setText("1000");
                    allowancePaneattentxt.setText("2000");
                    allowancePaneOthertxt.setText("0");
                    allowancePaneRateOfPerHourtxt.setText("0.0");
                    allowancePaneTotalAmountDislbl.setText("0.0");
                    allowancePaneSearchEmpidtxt.setText("");
                    allowancePaneOT.setText("0.0");
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
        allowancetable();
    }//GEN-LAST:event_allowancePaneSavebtnActionPerformed

    private void allowancePaneClearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowancePaneClearbtnActionPerformed
        allowancePaneEmpNotxt.setText("");
        allowancePaneFnametxt.setText("");
        allowancePaneLnametxt.setText("");
        allowancePaneBasicSalarytxt.setText("");
        allowancePaneDepartmenttxt.setText("");
        allowancePaneOvertimetxt.setText("0");
        allowancePanebudgetaryltxt.setText("1000");
        allowancePaneattentxt.setText("2000");
        allowancePaneOthertxt.setText("0");
        allowancePaneRateOfPerHourtxt.setText("0.0");
        allowancePaneTotalAmountDislbl.setText("0.0");
        allowancePaneSearchEmpidtxt.setText("");
        allowancePaneOT.setText("0.0");
      
    }//GEN-LAST:event_allowancePaneClearbtnActionPerformed

    private void allowancePaneEmpNotxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allowancePaneEmpNotxtMouseClicked

    }//GEN-LAST:event_allowancePaneEmpNotxtMouseClicked

    private void allowancePaneEmpNotxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneEmpNotxtKeyPressed
        allowancePaneEmpNotxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneEmpNotxtKeyPressed

    private void allowancePaneFnametxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneFnametxtKeyPressed
        allowancePaneFnametxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneFnametxtKeyPressed

    private void allowancePaneLnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowancePaneLnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allowancePaneLnametxtActionPerformed

    private void allowancePaneLnametxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneLnametxtKeyPressed
        allowancePaneLnametxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneLnametxtKeyPressed

    private void allowancePaneBasicSalarytxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneBasicSalarytxtKeyPressed
        allowancePaneBasicSalarytxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneBasicSalarytxtKeyPressed

    private void allowancePaneDepartmenttxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneDepartmenttxtKeyPressed
        allowancePaneDepartmenttxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneDepartmenttxtKeyPressed

    private void allowancePaneOvertimetxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneOvertimetxtKeyPressed
        allowancePaneOvertimetxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneOvertimetxtKeyPressed

    private void allowancePanebudgetaryltxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePanebudgetaryltxtKeyPressed
        allowancePanebudgetaryltxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePanebudgetaryltxtKeyPressed

    private void allowancePaneattentxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneattentxtKeyPressed
        allowancePaneattentxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneattentxtKeyPressed

    private void allowancePaneOthertxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allowancePaneOthertxtKeyPressed
        allowancePaneOthertxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_allowancePaneOthertxtKeyPressed

    private void allowancePaneUpdatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowancePaneUpdatebtnActionPerformed
        if(allowancePaneEmpNotxt.getText().isEmpty())
        {
            allowancePaneEmpNotxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The EmpNo field is empty !!");
        }
        else if(allowancePaneFnametxt.getText().isEmpty())
        {
            allowancePaneFnametxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The First Name field is empty !!");
        }
        else if(allowancePaneLnametxt.getText().isEmpty())
        {
            allowancePaneLnametxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Last Name field is empty !!");
        }
        else if( allowancePaneBasicSalarytxt.getText().isEmpty())
        {
            allowancePaneBasicSalarytxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The  Basic Salary field is empty !!");
        }

        else if(allowancePaneDepartmenttxt.getText().isEmpty())
        {
            allowancePaneDepartmenttxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Department field is empty !!");
        }
        else if(allowancePaneOvertimetxt.getText().isEmpty())
        {
            allowancePaneOvertimetxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The OverTime field is empty !!");
        }
        else if(allowancePanebudgetaryltxt.getText().isEmpty())
        {
            allowancePanebudgetaryltxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Medical field is empty !!");
        }
        else if(allowancePaneattentxt.getText().isEmpty())
        {
            allowancePaneattentxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Bonus field is empty !!");
        }
        else if(allowancePaneOthertxt.getText().isEmpty())
        {
            allowancePaneOthertxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Other field is empty !!");
        }
        else if(allowancePaneTotalAmountDislbl.getText().equals("0.00"))
        {
            allowancePaneCalculatebtn.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please Calculate !!");
        }

        else
        {
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to add this record!!","Add Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
                try{

                    String query = "UPDATE allowance SET First_Name=?,Last_Name=?,Basic_Salary=?,Department=?,OT_Hours=?,OverTime=?,Budgetary=?,Attendance=?,Other=?,RateOfPerHour=?,Total_Amount=? where EmpNo=?";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst=con.prepareStatement(query);

                    pst.setString(1, allowancePaneFnametxt.getText());
                    pst.setString(2, allowancePaneLnametxt.getText());
                    pst.setString(3, allowancePaneBasicSalarytxt.getText());
                    pst.setString(4, allowancePaneDepartmenttxt.getText());
                    pst.setString(5, allowancePaneOvertimetxt.getText());
                    pst.setString(6, allowancePaneOT.getText());
                    pst.setString(7, allowancePanebudgetaryltxt.getText());
                    pst.setString(8, allowancePaneattentxt.getText());
                    pst.setString(9, allowancePaneOthertxt.getText());
                    pst.setString(10, allowancePaneRateOfPerHourtxt.getText());
                    pst.setString(11, allowancePaneTotalAmountDislbl.getText());
                    pst.setString(12, allowancePaneEmpNotxt.getText());

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Update Successfully ");

                     allowancePaneEmpNotxt.setText("");
        allowancePaneFnametxt.setText("");
        allowancePaneLnametxt.setText("");
        allowancePaneBasicSalarytxt.setText("");
        allowancePaneDepartmenttxt.setText("");
        allowancePaneOvertimetxt.setText("0");
        allowancePanebudgetaryltxt.setText("1000");
        allowancePaneattentxt.setText("2000");
        allowancePaneOthertxt.setText("0");
        allowancePaneRateOfPerHourtxt.setText("0.0");
        allowancePaneTotalAmountDislbl.setText("0.0");
        allowancePaneSearchEmpidtxt.setText("");
        allowancePaneOT.setText("0.0");
                   
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
        allowancetable();
    }//GEN-LAST:event_allowancePaneUpdatebtnActionPerformed

    private void allowanceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allowanceTableMouseClicked
        DefaultTableModel model = (DefaultTableModel)allowanceTable.getModel();
        int selectedRowIndex = allowanceTable.getSelectedRow();

        allowancePaneEmpNotxt.setText(model.getValueAt(selectedRowIndex, 0).toString());
        allowancePaneFnametxt.setText(model.getValueAt(selectedRowIndex,1).toString());
        allowancePaneLnametxt.setText(model.getValueAt(selectedRowIndex, 2).toString());
        allowancePaneBasicSalarytxt.setText(model.getValueAt(selectedRowIndex, 3).toString());
        allowancePaneDepartmenttxt.setText(model.getValueAt(selectedRowIndex, 4).toString());
        allowancePaneOvertimetxt.setText(model.getValueAt(selectedRowIndex, 5).toString());
        allowancePanebudgetaryltxt.setText(model.getValueAt(selectedRowIndex, 7).toString());
        allowancePaneattentxt.setText(model.getValueAt(selectedRowIndex, 8).toString());
        allowancePaneOthertxt.setText(model.getValueAt(selectedRowIndex, 9).toString());
    }//GEN-LAST:event_allowanceTableMouseClicked

    private void btnGoBack10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack10ActionPerformed
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
       
        p3.setVisible(true);
    
    }//GEN-LAST:event_btnGoBack10ActionPerformed

    private void deductionPaneSearchEmpidtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPaneSearchEmpidtxtKeyPressed
        deductionPaneSearchEmpidtxt.setBackground(new Color(255,255,255));
        deductionPaneEmpNotxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_deductionPaneSearchEmpidtxtKeyPressed

    private void deductionSearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionSearchbtnActionPerformed
     String additional=null;  
     String basic=null;   
        
        if (deductionPaneSearchEmpidtxt.getText().isEmpty()) {
            deductionPaneSearchEmpidtxt.setBackground(new Color(255, 0, 51));
            JOptionPane.showMessageDialog(this, "The Search EmpNo field is empty !!");
        } else {

            try {
                String sql = "SELECT EmpNo,First_Name,Last_Name,Basic_Salary,Department,Designation FROM employee where EmpNo=? ";
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst = (PreparedStatement) con.prepareStatement(sql);

                pst.setString(1, deductionPaneSearchEmpidtxt.getText());
                rs = (ResultSet) pst.executeQuery();
                if (rs.next()) {
                    deductionPaneEmpNotxt.setText(rs.getString("EmpNo"));
                    deductionPaneFnametxt.setText(rs.getString("First_Name"));
                    deductionPaneLnametxt.setText(rs.getString("Last_Name"));
                    deductionPaneBasicSalarytxt.setText(rs.getString("Basic_Salary"));
                    deductionPaneDepartmenttxt.setText(rs.getString("Department"));
                    deductionPaneDesignationtxt.setText(rs.getString("Designation"));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            
              try {
                String sql = "SELECT Additional_Leaves FROM leaveinformation where EmpNo=? ";
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                pst = (PreparedStatement) con.prepareStatement(sql);

                pst.setString(1, deductionPaneSearchEmpidtxt.getText());
                rs = (ResultSet) pst.executeQuery();
                if (rs.next()) {
                    additionalLeaves.setText(rs.getString("Additional_Leaves"));
                   
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
              
            additional=additionalLeaves.getText();
            Double add=Double.valueOf(additional);
            
            basic=deductionPaneBasicSalarytxt.getText();
            Double basi=Double.valueOf(basic);
            
            double daydedurate = basi/25;
            double total=daydedurate*add;
            String tot=String.valueOf(total);
            additionalLeavesamount.setText(tot);
                    
           
        }
    }//GEN-LAST:event_deductionSearchbtnActionPerformed

    private void deductionPaneEmpNotxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deductionPaneEmpNotxtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionPaneEmpNotxtMouseClicked

    private void deductionPaneEmpNotxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPaneEmpNotxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionPaneEmpNotxtKeyPressed

    private void deductionPaneDepartmenttxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPaneDepartmenttxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionPaneDepartmenttxtKeyPressed

    private void deductionPaneBasicSalarytxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPaneBasicSalarytxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionPaneBasicSalarytxtKeyPressed

    private void deductionPaneLnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionPaneLnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionPaneLnametxtActionPerformed

    private void deductionPaneLnametxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPaneLnametxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionPaneLnametxtKeyPressed

    private void deductionPaneFnametxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPaneFnametxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionPaneFnametxtKeyPressed

    private void deductionPaneDesignationtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPaneDesignationtxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionPaneDesignationtxtKeyPressed

    private void deductionPanePercentageRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionPanePercentageRadioActionPerformed
        deductionPanePercentageRadio.setSelected(true);
        deductionPaneAmountRadio.setSelected(false);

        deductionPaneAmounttxt.setEnabled(false);
        deductionPanePercentagetxt.setEditable(true);
        deductionPanePercentagetxt.setEnabled(true);
        deductionPaneAmounttxt.setText("");
    }//GEN-LAST:event_deductionPanePercentageRadioActionPerformed

    private void deductionPanePercentageRadioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPanePercentageRadioKeyPressed
        deductionPanePercentageRadio.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_deductionPanePercentageRadioKeyPressed

    private void deductionPaneAmountRadioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deductionPaneAmountRadioMouseClicked
        deductionPaneAmountRadio.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_deductionPaneAmountRadioMouseClicked

    private void deductionPaneAmountRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionPaneAmountRadioActionPerformed
        deductionPanePercentageRadio.setSelected(false);
        deductionPaneAmountRadio.setSelected(true);

        deductionPanePercentagetxt.setEnabled(false);
        deductionPaneAmounttxt.setEditable(true);
        deductionPaneAmounttxt.setEnabled(true);
        deductionPanePercentagetxt.setText("");
    }//GEN-LAST:event_deductionPaneAmountRadioActionPerformed

    private void deductionPaneResontxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionPaneResontxtKeyPressed
        deductionPaneResontxt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_deductionPaneResontxtKeyPressed

    private void deductionPaneSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionPaneSaveActionPerformed
        String dedutct=null;

        if(deductionPanePercentageRadio.isSelected()){
            dedutct=deductionPanePercentageRadio.getText();
        }
        else if(deductionPaneAmountRadio.isSelected()){
            dedutct=deductionPaneAmountRadio.getText();
        }

        if(deductionPaneEmpNotxt.getText().isEmpty())
        {
            deductionPaneEmpNotxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The EmpNo field is empty !!");
        }
        else if(deductionPaneFnametxt.getText().isEmpty())
        {
            deductionPaneFnametxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The First Name field is empty !!");
        }
        else if(deductionPaneLnametxt.getText().isEmpty())
        {
            deductionPaneLnametxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Last Name field is empty !!");
        }
        else if( deductionPaneBasicSalarytxt.getText().isEmpty())
        {
            deductionPaneBasicSalarytxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The  Basic Salary field is empty !!");
        }

        else if(deductionPaneDepartmenttxt.getText().isEmpty())
        {
            deductionPaneDepartmenttxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Department field is empty !!");
        }
        else if(deductionPaneDesignationtxt.getText().isEmpty())
        {
            deductionPaneDesignationtxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Designation field is empty !!");
        }
        else if(dedutct==null){
            deductionPaneAmountRadio.setForeground(new java.awt.Color(255,0,51));
            deductionPanePercentageRadio.setForeground(new java.awt.Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Please Select the Deductions !!");
        }

        else if(deductionPaneResontxt.getText().isEmpty())
        {
            deductionPaneResontxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Reason field is empty !!");
        }
        else if(deductionPaneTotalDeduction.getText().equals("0.00"))
        {
            deductionPaneCalculate.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please Calculate !!");
        }

        else
        {
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to add this record!!","Add Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
                try{

                    String query = "INSERT INTO deduction(EmpNo,First_Name,Last_Name,Department,Designation,Basic_Salary,Deduction_Amount,Deduction_Reason,Salary_After_Deduction) VALUES (?,?,?,?,?,?,?,?,?)";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst=con.prepareStatement(query);

                    pst.setString(1, deductionPaneEmpNotxt.getText());
                    pst.setString(2, deductionPaneFnametxt.getText());
                    pst.setString(3, deductionPaneLnametxt.getText());
                    pst.setString(4, deductionPaneDepartmenttxt.getText());
                    pst.setString(5, deductionPaneDesignationtxt.getText());
                    pst.setString(6, deductionPaneBasicSalarytxt.getText());
                    pst.setString(7, deductionPaneTotalDeduction.getText());
                    pst.setString(8, deductionPaneResontxt.getText());
                    pst.setString(9, deductionPaneSad.getText());

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Inserted Successfully ");

                    deductionPaneEmpNotxt.setText("");
                    deductionPaneFnametxt.setText("");
                    deductionPaneLnametxt.setText("");
                    deductionPaneBasicSalarytxt.setText("");
                    deductionPaneDepartmenttxt.setText("");
                    deductionPaneDesignationtxt.setText("");
                    deductionPaneTotalDeduction.setText("");
                    deductionPaneResontxt.setText("");
                    deductionPaneSad.setText("");
                    deductionPaneAmountRadio.setSelected(false);
                    deductionPanePercentageRadio.setSelected(false);
                    deductionPanePercentagetxt.setText("");
                    deductionPaneAmounttxt.setText("");
                    deductionPaneSearchEmpidtxt.setText("");
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
        deductiontable();
    }//GEN-LAST:event_deductionPaneSaveActionPerformed

    private void deductionPaneCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionPaneCalculateActionPerformed

        float salary = Float.parseFloat(deductionPaneBasicSalarytxt.getText());

        if(deductionPanePercentageRadio.isSelected()== true){
            int percentage = Integer.parseInt(deductionPanePercentagetxt.getText());

            float total_percentage_deduction = salary /100 * percentage;
            String x = String.valueOf(total_percentage_deduction);
            float sal = salary - total_percentage_deduction;
            deductionPaneTotalDeduction.setText(x);
            deductionPaneSad.setText(String.valueOf(sal));
        }

        if(deductionPaneAmountRadio.isSelected()== true){
            int deduction = Integer.parseInt(deductionPaneAmounttxt.getText());

            float total_amount_deduction =  salary - deduction;
            String s = String.valueOf(total_amount_deduction);

            deductionPaneSad.setText(s);
            deductionPaneTotalDeduction.setText(String.valueOf(deduction));

        }
    }//GEN-LAST:event_deductionPaneCalculateActionPerformed

    private void deductionPaneClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionPaneClearActionPerformed
        deductionPaneEmpNotxt.setText("");
        deductionPaneFnametxt.setText("");
        deductionPaneLnametxt.setText("");
        deductionPaneBasicSalarytxt.setText("");
        deductionPaneDepartmenttxt.setText("");
        deductionPaneDesignationtxt.setText("");
        deductionPaneTotalDeduction.setText("");
        deductionPaneResontxt.setText("");
        deductionPaneSad.setText("");
        deductionPaneAmountRadio.setSelected(false);
        deductionPanePercentageRadio.setSelected(false);
        deductionPanePercentagetxt.setText("");
        deductionPaneAmounttxt.setText("");
        deductionPaneSearchEmpidtxt.setText("");
    }//GEN-LAST:event_deductionPaneClearActionPerformed

    private void deductionPaneUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductionPaneUpdateActionPerformed
        String dedutct=null;

        if(deductionPanePercentageRadio.isSelected()){
            dedutct=deductionPanePercentageRadio.getText();
        }
        else if(deductionPaneAmountRadio.isSelected()){
            dedutct=deductionPaneAmountRadio.getText();
        }

        if(deductionPaneEmpNotxt.getText().isEmpty())
        {
            deductionPaneEmpNotxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The EmpNo field is empty !!");
        }
        else if(deductionPaneFnametxt.getText().isEmpty())
        {
            deductionPaneFnametxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The First Name field is empty !!");
        }
        else if(deductionPaneLnametxt.getText().isEmpty())
        {
            deductionPaneLnametxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Last Name field is empty !!");
        }
        else if( deductionPaneBasicSalarytxt.getText().isEmpty())
        {
            deductionPaneBasicSalarytxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The  Basic Salary field is empty !!");
        }

        else if(deductionPaneDepartmenttxt.getText().isEmpty())
        {
            deductionPaneDepartmenttxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Department field is empty !!");
        }
        else if(deductionPaneDesignationtxt.getText().isEmpty())
        {
            deductionPaneDesignationtxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Designation field is empty !!");
        }
        else if(dedutct==null){
            deductionPaneAmountRadio.setForeground(new java.awt.Color(255,0,51));
            deductionPanePercentageRadio.setForeground(new java.awt.Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Please Select the Deductions !!");
        }

        else if(deductionPaneResontxt.getText().isEmpty())
        {
            deductionPaneResontxt.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "The Reason field is empty !!");
        }
        else if(deductionPaneTotalDeduction.getText().equals("0.00"))
        {
            deductionPaneCalculate.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please Calculate !!");
        }

        else
        {
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to add this record!!","Add Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
                try{

                    String query = "Update deduction SET First_Name=?,Last_Name=?,Department=?,Designation=?,Basic_Salary=?,Deduction_Amount=?,Deduction_Reason=?,Salary_After_Deduction=? where EmpNo=?";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/sr_bio_foods", "root", "");
                    pst=con.prepareStatement(query);

                    pst.setString(1, deductionPaneFnametxt.getText());
                    pst.setString(2, deductionPaneLnametxt.getText());
                    pst.setString(3, deductionPaneBasicSalarytxt.getText());
                    pst.setString(4, deductionPaneDepartmenttxt.getText());
                    pst.setString(5, deductionPaneDesignationtxt.getText());
                    pst.setString(6, deductionPaneTotalDeduction.getText());
                    pst.setString(7, deductionPaneResontxt.getText());
                    pst.setString(8, deductionPaneSad.getText());
                    pst.setString(9, deductionPaneEmpNotxt.getText());

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Update Successfully ");

                    deductionPaneEmpNotxt.setText("");
                    deductionPaneFnametxt.setText("");
                    deductionPaneLnametxt.setText("");
                    deductionPaneBasicSalarytxt.setText("");
                    deductionPaneDepartmenttxt.setText("");
                    deductionPaneDesignationtxt.setText("");
                    deductionPaneTotalDeduction.setText("");
                    deductionPaneResontxt.setText("");
                    deductionPaneSad.setText("");
                    deductionPaneAmountRadio.setSelected(false);
                    deductionPanePercentageRadio.setSelected(false);
                    deductionPanePercentagetxt.setText("");
                    deductionPaneAmounttxt.setText("");
                    deductionPaneSearchEmpidtxt.setText("");
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
        deductiontable();
    }//GEN-LAST:event_deductionPaneUpdateActionPerformed

    private void deductionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deductionTableMouseClicked
        DefaultTableModel model = (DefaultTableModel)deductionTable.getModel();
        int selectedRowIndex = deductionTable.getSelectedRow();

        deductionPaneEmpNotxt.setText(model.getValueAt(selectedRowIndex, 0).toString());
        deductionPaneFnametxt.setText(model.getValueAt(selectedRowIndex,1).toString());
        deductionPaneLnametxt.setText(model.getValueAt(selectedRowIndex, 2).toString());
        deductionPaneDepartmenttxt.setText(model.getValueAt(selectedRowIndex,3).toString());
        deductionPaneDesignationtxt.setText(model.getValueAt(selectedRowIndex, 4).toString());
        deductionPaneBasicSalarytxt.setText(model.getValueAt(selectedRowIndex, 5).toString());
        deductionPaneResontxt.setText(model.getValueAt(selectedRowIndex, 7).toString());
    }//GEN-LAST:event_deductionTableMouseClicked

    private void allowanceSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allowanceSalaryMouseClicked
        p3_AllowancePane.setVisible(true);
        p3_PaymentPane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
      
        p3.setVisible(false);
       
    }//GEN-LAST:event_allowanceSalaryMouseClicked

    private void paymentSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentSalaryMouseClicked
        p3_PaymentPane.setVisible(true);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
   
        p3.setVisible(false);
  
    }//GEN-LAST:event_paymentSalaryMouseClicked

    private void updateSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateSalaryMouseClicked
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(true);
        p3_PaymentSummeryPane.setVisible(false);

        p3.setVisible(false);
 
    }//GEN-LAST:event_updateSalaryMouseClicked

    private void deductionSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deductionSalaryMouseClicked
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(true);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
 
        p3.setVisible(false);
   
    }//GEN-LAST:event_deductionSalaryMouseClicked

    private void paymentSummeryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentSummeryMouseClicked
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(true);

        p3.setVisible(false);
 
    }//GEN-LAST:event_paymentSummeryMouseClicked

    private void btnGoBack12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBack12ActionPerformed
        p3_PaymentPane.setVisible(false);
        p3_AllowancePane.setVisible(false);
        p3_DeductionPane.setVisible(false);
        p3_UpdatesalryPane.setVisible(false);
        p3_PaymentSummeryPane.setVisible(false);
  
        p3.setVisible(true);
   
    }//GEN-LAST:event_btnGoBack12ActionPerformed

    private void salaryUpdateSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryUpdateSearch1ActionPerformed
        paymentSummerytable();

        if(paymentSummeryPaneYear.getText().isEmpty())
        {
            paymentSummeryPaneYear.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please enter year !!");
        }
        else if(paymentSummeryPaneMonth.getSelectedItem().equals("Select Month"))
        {
            paymentSummeryPaneMonth.setBackground(new Color(255,0,51));
            JOptionPane.showMessageDialog(this, "Please Select Month !!");
        }

        else
        {
            int numrow = paymentsummeryTable.getRowCount();
            double tot= 0;
            for(int i = 0;i< numrow;i++){
                double val = Double.valueOf(paymentsummeryTable.getValueAt(i, 13).toString());
                tot += val;
            }
            paymentPaneSummeryTot.setText(Double.toString(tot));
        }
    }//GEN-LAST:event_salaryUpdateSearch1ActionPerformed

    private void paymentSummeryPaneYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentSummeryPaneYearKeyPressed
        paymentSummeryPaneYear.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_paymentSummeryPaneYearKeyPressed

    private void paymentSummeryPaneMonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentSummeryPaneMonthMouseClicked
        paymentSummeryPaneMonth.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_paymentSummeryPaneMonthMouseClicked

    private void paymentSummeryPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentSummeryPrintActionPerformed
        String mpsy=paymentSummeryPaneYear.getText();
        String mpsm=String.valueOf(paymentSummeryPaneMonth.getSelectedItem());
        System.out.println(mpsm);
        String totlbl=paymentPaneSummeryTot.getText();

        try {

            java.text.MessageFormat header = new java.text.MessageFormat(mpsy+" "+mpsm);
            java.text.MessageFormat footer = new java.text.MessageFormat(" Total Payment That Month : Rs."+totlbl);

            paymentsummeryTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_paymentSummeryPrintActionPerformed

    private void paymentPaneSearchtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentPaneSearchtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentPaneSearchtxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GenarateSalary;
    private javax.swing.JTextField additionalLeaves;
    private javax.swing.JTextField additionalLeavesamount;
    private javax.swing.JTextField allowancePaneBasicSalarytxt;
    private javax.swing.JLabel allowancePaneBasicsalarylbl;
    private javax.swing.JLabel allowancePaneBonuslbl;
    private javax.swing.JButton allowancePaneCalculatebtn;
    private javax.swing.JButton allowancePaneClearbtn;
    private javax.swing.JLabel allowancePaneDeparmentlbl;
    private javax.swing.JTextField allowancePaneDepartmenttxt;
    private javax.swing.JTextField allowancePaneEmpNotxt;
    private javax.swing.JLabel allowancePaneEmpnolbl;
    private javax.swing.JLabel allowancePaneFnamelbl;
    private javax.swing.JTextField allowancePaneFnametxt;
    private javax.swing.JLabel allowancePaneLnamelbl;
    private javax.swing.JTextField allowancePaneLnametxt;
    private javax.swing.JTextField allowancePaneOT;
    private javax.swing.JLabel allowancePaneOtherlbl;
    private javax.swing.JTextField allowancePaneOthertxt;
    private javax.swing.JLabel allowancePaneOvertimelbl;
    private javax.swing.JTextField allowancePaneOvertimetxt;
    private javax.swing.JLabel allowancePaneRateOfPerHour;
    private javax.swing.JTextField allowancePaneRateOfPerHourtxt;
    private javax.swing.JButton allowancePaneSavebtn;
    private javax.swing.JLabel allowancePaneSearchEmpidlb;
    private javax.swing.JTextField allowancePaneSearchEmpidtxt;
    private javax.swing.JLabel allowancePaneTotalAmount;
    private javax.swing.JLabel allowancePaneTotalAmountDislbl;
    private javax.swing.JButton allowancePaneUpdatebtn;
    private javax.swing.JTextField allowancePaneattentxt;
    private javax.swing.JLabel allowancePanebudetarylbl;
    private javax.swing.JTextField allowancePanebudgetaryltxt;
    private javax.swing.JPanel allowanceSalary;
    private javax.swing.JButton allowanceSearchbtn;
    private javax.swing.JTable allowanceTable;
    private javax.swing.JButton basicSalaryUpdate;
    private javax.swing.JButton btnGoBack10;
    private javax.swing.JButton btnGoBack11;
    private javax.swing.JButton btnGoBack12;
    private javax.swing.JButton btnGoBack8;
    private javax.swing.JButton btnGoBack9;
    private javax.swing.JRadioButton deductionPaneAmountRadio;
    private javax.swing.JLabel deductionPaneAmountlbl;
    private javax.swing.JTextField deductionPaneAmounttxt;
    private javax.swing.JTextField deductionPaneBasicSalarytxt;
    private javax.swing.JLabel deductionPaneBasicsalarylbl;
    private javax.swing.JButton deductionPaneCalculate;
    private javax.swing.JButton deductionPaneClear;
    private javax.swing.JLabel deductionPaneDeparmentlbl;
    private javax.swing.JTextField deductionPaneDepartmenttxt;
    private javax.swing.JLabel deductionPaneDesignationlbl;
    private javax.swing.JTextField deductionPaneDesignationtxt;
    private javax.swing.JTextField deductionPaneEmpNotxt;
    private javax.swing.JLabel deductionPaneEmpnolbl;
    private javax.swing.JLabel deductionPaneFnamelbl;
    private javax.swing.JTextField deductionPaneFnametxt;
    private javax.swing.JLabel deductionPaneLnamelbl;
    private javax.swing.JTextField deductionPaneLnametxt;
    private javax.swing.JRadioButton deductionPanePercentageRadio;
    private javax.swing.JLabel deductionPanePercentagelbl;
    private javax.swing.JTextField deductionPanePercentagetxt;
    private javax.swing.JLabel deductionPaneResonlbl;
    private javax.swing.JTextField deductionPaneResontxt;
    private javax.swing.JLabel deductionPaneSad;
    private javax.swing.JLabel deductionPaneSadlbl;
    private javax.swing.JButton deductionPaneSave;
    private javax.swing.JLabel deductionPaneSearchEmpidlb;
    private javax.swing.JTextField deductionPaneSearchEmpidtxt;
    private javax.swing.JLabel deductionPaneTotalDeduction;
    private javax.swing.JLabel deductionPaneTotalDeductionlbl;
    private javax.swing.JButton deductionPaneUpdate;
    private javax.swing.JLabel deductionPaneUplbl;
    private javax.swing.JPanel deductionSalary;
    private javax.swing.JButton deductionSearchbtn;
    private javax.swing.JTable deductionTable;
    private javax.swing.JPanel header10;
    private javax.swing.JPanel header11;
    private javax.swing.JPanel header4;
    private javax.swing.JPanel header8;
    private javax.swing.JPanel header9;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdress4;
    private javax.swing.JLabel lblAdressline4;
    private javax.swing.JLabel lblBasicSalary4;
    private javax.swing.JLabel lblContactno4;
    private javax.swing.JLabel lblDOB4;
    private javax.swing.JLabel lblDepartment5;
    private javax.swing.JLabel lblEmail4;
    private javax.swing.JLabel lblNic4;
    private javax.swing.JLabel lbldesignation5;
    private javax.swing.JLabel lbleducation4;
    private javax.swing.JLabel lblemono5;
    private javax.swing.JLabel lblfname4;
    private javax.swing.JLabel lblgender4;
    private javax.swing.JLabel lbllname4;
    private javax.swing.JLabel lblstatus4;
    private javax.swing.JLabel monthlbl;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p3_AllowancePane;
    private javax.swing.JPanel p3_DeductionPane;
    private javax.swing.JPanel p3_PaymentPane;
    private javax.swing.JPanel p3_PaymentSummeryPane;
    private javax.swing.JPanel p3_UpdatesalryPane;
    private javax.swing.JTextField paymentPaneBasicSalary;
    private javax.swing.JTextField paymentPaneBonus;
    private javax.swing.JButton paymentPaneClear;
    private javax.swing.JTextField paymentPaneDeductionDetails;
    private javax.swing.JTextField paymentPaneDeparment;
    private javax.swing.JTextField paymentPaneDesignation;
    private javax.swing.JTextField paymentPaneEmpNo;
    private javax.swing.JTextField paymentPaneFirstName;
    private javax.swing.JTextField paymentPaneLastName;
    private javax.swing.JTextField paymentPaneMedical;
    private javax.swing.JLabel paymentPaneNetPay;
    private javax.swing.JTextField paymentPaneOther;
    private javax.swing.JTextField paymentPaneOverTime;
    private javax.swing.JButton paymentPanePayment;
    private javax.swing.JButton paymentPanePrint;
    private javax.swing.JButton paymentPaneSearch;
    private javax.swing.JTextField paymentPaneSearchtxt;
    private javax.swing.JLabel paymentPaneStatus;
    private javax.swing.JLabel paymentPaneSummeryTot;
    private javax.swing.JTextField paymentPaneTotalDeduction;
    private javax.swing.JLabel paymentPaneTotalEarning;
    private javax.swing.JPanel paymentSalary;
    private javax.swing.JPanel paymentSummery;
    private javax.swing.JComboBox<String> paymentSummeryPaneMonth;
    private javax.swing.JTextField paymentSummeryPaneYear;
    private javax.swing.JButton paymentSummeryPrint;
    private javax.swing.JTable paymentsummeryTable;
    private javax.swing.JButton salaryUpdateSearch;
    private javax.swing.JButton salaryUpdateSearch1;
    private javax.swing.JTextArea slipArea;
    private javax.swing.JTextField txtAdress4;
    private javax.swing.JTextField txtAdressline4;
    private javax.swing.JTextField txtBasicSalary4;
    private javax.swing.JTextField txtContactNo4;
    private javax.swing.JTextField txtDOB4;
    private javax.swing.JTextField txtEmail4;
    private javax.swing.JTextField txtFname4;
    private javax.swing.JTextField txtLname4;
    private javax.swing.JTextField txtNIC4;
    private javax.swing.JTextField txtSdepartment;
    private javax.swing.JTextField txtSdesignation5;
    private javax.swing.JTextField txteducationallevel4;
    private javax.swing.JTextField txtempno4;
    private javax.swing.JTextField txtgender4;
    private javax.swing.JTextField txtstatus4;
    private javax.swing.JTextField updateAmount;
    private javax.swing.JRadioButton updateAmountRadio;
    private javax.swing.JTextField updatePercentage;
    private javax.swing.JPanel updateSalary;
    private javax.swing.JTextField updateSalarySearchtxt;
    private javax.swing.JRadioButton updatepercentageRadio;
    private javax.swing.JLabel yearlbl;
    // End of variables declaration//GEN-END:variables
}
