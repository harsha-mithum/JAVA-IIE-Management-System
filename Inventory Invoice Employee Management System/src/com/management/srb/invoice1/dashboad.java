/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.invoice1;

import com.management.srb.util.DBConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Avishka
 */
public class dashboad extends javax.swing.JPanel {

    /**
     * Creates new form dashboad
     */
    public dashboad() {
        initComponents();
        
        showLineChart();
        showLineChart1();
        showSales();
        showPurchases();
    }

   private void showLineChart() {
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT SUM(total_amount) AS amount,date FROM sales_orders WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 WEEK ) AND CURDATE() GROUP BY date";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dataset.setValue(rs.getDouble("amount"), "date", rs.getString("date"));
            }
        } catch (Exception e) {

        }
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Sales", "date", "amount",
                dataset, PlotOrientation.VERTICAL, false, true, true);

        //create plot object
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);

        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        //create chartPanel to display chart(graph)

    }
    private void showLineChart1() {
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT SUM(total_amount) AS amount,date FROM purchase_orders WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 WEEK ) AND CURDATE() GROUP BY date";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dataset.setValue(rs.getDouble("amount"), "date", rs.getString("date"));
            }
        } catch (Exception e) {

        }
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Purchases", "date", "amount",
                dataset, PlotOrientation.VERTICAL, false, true, true);

        //create plot object
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);

        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);

    }
      
 private void showSales() {
        try {
            try (Connection connection = DBConnection.getConnection()) {
                String sql = "SELECT SUM(total_amount) FROM sales_orders";
                
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    this.lblTotal.setText(String.valueOf(rs.getInt(1)));
                }
            }
        } catch (SQLException e) {

        }
         try {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT COUNT(*) FROM sales_orders WHERE date = DATE(NOW()) AND order_status='Complete order'";
                
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    this.lblCompleted.setText(String.valueOf(rs.getInt(1)));
                }
            }
        } catch (SQLException e) {

        }
         try {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT COUNT(*) FROM sales_orders WHERE date = DATE(NOW()) AND order_status='Incomplete order'";
                
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    this.lblPending.setText(String.valueOf(rs.getInt(1)));
                }
            }
        } catch (SQLException e) {

        }
    }

    private void showPurchases() {
        try {
            try (Connection connection = DBConnection.getConnection()) {
                String sql = "SELECT SUM(total_amount) FROM purchase_orders";
                
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    this.lblTotal1.setText(String.valueOf(rs.getInt(1)));
                }
            }
        } catch (SQLException e) {

        }
         try {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT COUNT(*) FROM purchase_orders WHERE date = DATE(NOW()) AND order_status='paid'";
                
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    this.lblCompleted1.setText(String.valueOf(rs.getInt(1)));
                }
            }
        } catch (SQLException e) {

        }
         try {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT COUNT(*) FROM purchase_orders WHERE date = DATE(NOW()) AND order_status='Incomplete order'";
                
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    this.lblPending1.setText(String.valueOf(rs.getInt(1)));
                }
            }
        } catch (SQLException e) {

        }
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDetails1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblPending1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCompleted1 = new javax.swing.JLabel();
        lblTotal1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelDetails = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPending = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblCompleted = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(180, 170, 255));
        setMinimumSize(new java.awt.Dimension(820, 720));
        setPreferredSize(new java.awt.Dimension(820, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDetails1.setBackground(new java.awt.Color(204, 204, 204));
        panelDetails1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(102, 102, 102)));
        panelDetails1.setPreferredSize(new java.awt.Dimension(400, 250));
        panelDetails1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Pending     :");
        panelDetails1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 24, 161, 50));

        lblPending1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPending1.setForeground(new java.awt.Color(255, 102, 51));
        lblPending1.setText("pend");
        panelDetails1.add(lblPending1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 35, 63, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Completed :");
        panelDetails1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 84, 161, 50));

        lblCompleted1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblCompleted1.setForeground(new java.awt.Color(0, 255, 0));
        lblCompleted1.setText("com");
        panelDetails1.add(lblCompleted1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 95, 90, -1));

        lblTotal1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTotal1.setForeground(new java.awt.Color(0, 0, 102));
        lblTotal1.setText("sales");
        panelDetails1.add(lblTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 151, 155, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Total Sales :");
        panelDetails1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 140, 161, 50));

        add(panelDetails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 360, 220));

        panelDetails.setBackground(new java.awt.Color(204, 204, 204));
        panelDetails.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(102, 102, 102)));
        panelDetails.setPreferredSize(new java.awt.Dimension(400, 250));
        panelDetails.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Pending     :");
        panelDetails.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 24, 161, 50));

        lblPending.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPending.setForeground(new java.awt.Color(255, 102, 51));
        lblPending.setText("pend");
        panelDetails.add(lblPending, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 35, 63, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Completed :");
        panelDetails.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 84, 161, 50));

        lblCompleted.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblCompleted.setForeground(new java.awt.Color(0, 255, 0));
        lblCompleted.setText("com");
        panelDetails.add(lblCompleted, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 95, 90, -1));

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 102));
        lblTotal.setText("sales");
        panelDetails.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 151, 155, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Total Sales :");
        panelDetails.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 140, 161, 50));

        add(panelDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 310, 220));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblCompleted;
    private javax.swing.JLabel lblCompleted1;
    private javax.swing.JLabel lblPending;
    private javax.swing.JLabel lblPending1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JPanel panelDetails;
    private javax.swing.JPanel panelDetails1;
    // End of variables declaration//GEN-END:variables
}
