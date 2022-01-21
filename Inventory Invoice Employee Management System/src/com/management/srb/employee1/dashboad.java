/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.employee1;

import com.management.srb.util.DBConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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
         showPieChart();
        showLineChart();
        absentCount();
        presentCount();
        leaveCount();
        totalCount();
    }

  private void showPieChart() {
         DefaultPieDataset pieDataset = new DefaultPieDataset();
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(Attend) AS count,Attend FROM attend WHERE AttendDate = DATE(NOW()) GROUP BY Attend";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pieDataset.setValue(rs.getString("Attend"), new Double(rs.getDouble("count")));
            }
        } catch (SQLException e) {

        }

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Attendance", pieDataset, true, true, true);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        piePlot.setLabelGap(0.1);
        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel pieChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(pieChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
    }
    private void showLineChart() {
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) AS count,AttendDate FROM attend WHERE AttendDate BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 WEEK ) AND CURDATE() GROUP BY AttendDate";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dataset.setValue(rs.getDouble("count"), "date", rs.getString("AttendDate"));
            }
        } catch (Exception e) {

        }
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Attendance in This Week", "date", "attendance",
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
        panelLineChart.removeAll();
        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
    }
        private void absentCount(){
      try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM attend WHERE Attend = 'Absent' AND AttendDate = DATE(NOW())";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.lblAbsent.setText(String.valueOf(rs.getInt(1)));
            }
        } catch (Exception e) {

        }  
    }
        private void presentCount(){
      try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM attend WHERE Attend = 'Arrival' AND AttendDate = DATE(NOW())";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.lblPresent.setText(String.valueOf(rs.getInt(1)));
            }
        } catch (Exception e) {

        }  
    }
        private void leaveCount(){
         //   this.lblLeave.setText("");
      try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM attend WHERE Attend = 'Leave' AND AttendDate = DATE(NOW())";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.lblLeave.setText(String.valueOf(rs.getInt(1)));
            }
        } catch (Exception e) {

        }  
    }
        private void totalCount(){
         //   this.lblLeave.setText("");
      try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM employee";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.lblTotal.setText(String.valueOf(rs.getInt(1)));
            }
        } catch (Exception e) {

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

        panelLineChart = new javax.swing.JPanel();
        panelPieChart = new javax.swing.JPanel();
        panelDetails = new javax.swing.JPanel();
        lblAbsent = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblLeave = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPresent = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(180, 170, 255));
        setPreferredSize(new java.awt.Dimension(820, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLineChart.setBackground(new java.awt.Color(204, 204, 204));
        panelLineChart.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(102, 102, 102)));
        panelLineChart.setPreferredSize(new java.awt.Dimension(400, 250));
        panelLineChart.setLayout(new java.awt.BorderLayout());
        add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 410, 270));

        panelPieChart.setBackground(new java.awt.Color(204, 204, 204));
        panelPieChart.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(102, 102, 102)));
        panelPieChart.setPreferredSize(new java.awt.Dimension(400, 250));
        panelPieChart.setLayout(new java.awt.BorderLayout());
        add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 410, 270));

        panelDetails.setBackground(new java.awt.Color(204, 204, 204));
        panelDetails.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(102, 102, 102)));
        panelDetails.setPreferredSize(new java.awt.Dimension(400, 250));

        lblAbsent.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAbsent.setForeground(new java.awt.Color(255, 0, 51));
        lblAbsent.setText("absent");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("No.of Absent  :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("No.of Leave    :");

        lblLeave.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblLeave.setForeground(new java.awt.Color(255, 102, 51));
        lblLeave.setText("leave");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("No.of Present :");

        lblPresent.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPresent.setForeground(new java.awt.Color(0, 255, 0));
        lblPresent.setText("present");

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 102));
        lblTotal.setText("present");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Total Employees :");

        javax.swing.GroupLayout panelDetailsLayout = new javax.swing.GroupLayout(panelDetails);
        panelDetails.setLayout(panelDetailsLayout);
        panelDetailsLayout.setHorizontalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAbsent, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelDetailsLayout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelDetailsLayout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblPresent, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38))
        );
        panelDetailsLayout.setVerticalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAbsent)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLeave)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPresent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(panelDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 340, 270));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblAbsent;
    private javax.swing.JLabel lblLeave;
    private javax.swing.JLabel lblPresent;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelDetails;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JPanel panelPieChart;
    // End of variables declaration//GEN-END:variables
}
