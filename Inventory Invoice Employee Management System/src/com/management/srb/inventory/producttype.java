/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.inventory;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Avishka
 */
public class producttype extends javax.swing.JPanel {
     String dburl = "jdbc:mysql://localhost:3306/sr_bio_foods";
    String dbuname = "root";
    String dbpass = "";

    Connection Con = null;
    Statement St = null;
    ResultSet Rs = null;
    /**
     * Creates new form producttype
     */
    public producttype() {
        initComponents();
         SelectType();
         setView();
    }

     public void SelectType() {
        try {
            Con = DriverManager.getConnection(dburl, dbuname, dbpass);
            St = Con.createStatement();
            Rs = St.executeQuery("select * from type");
            typeTable.setModel(DbUtils.resultSetToTableModel(Rs));
            
            
              typeTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
              typeTable.getTableHeader().setOpaque(false);
              typeTable.getTableHeader().setBackground(new Color(32,136,203));
              typeTable.getTableHeader().setForeground(new Color(0,0,0));
              typeTable.setRowHeight(25);
        } catch (SQLException e) {
        }
    }
 public void setView(){
        try {
            PreparedStatement stmtMaximumID = Con.prepareStatement("SELECT MAX(id) from type");
            ResultSet getMaxValueID = stmtMaximumID.executeQuery();

            while (getMaxValueID.next()) {
                this.typeID.setText(Integer.toString(getMaxValueID.getInt(1)+1));
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AddBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        UpBtn = new javax.swing.JButton();
        typeID = new javax.swing.JTextField();
        typeName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        typeTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        header3 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(820, 700));

        jPanel1.setBackground(new java.awt.Color(200, 210, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Type ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 94, -1, -1));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Type");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 139, -1, -1));

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
        jPanel1.add(AddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 261, 200, 32));

        DeleteBtn.setBackground(new java.awt.Color(255, 0, 51));
        DeleteBtn.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        DeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBtn.setText("Delete");
        DeleteBtn.setBorder(null);
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(DeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 381, 200, 32));

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
        jPanel1.add(UpBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 321, 200, 32));

        typeID.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        typeID.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        typeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeIDActionPerformed(evt);
            }
        });
        jPanel1.add(typeID, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 170, 35));

        typeName.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        typeName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(51, 51, 51)));
        typeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeNameActionPerformed(evt);
            }
        });
        jPanel1.add(typeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 170, 35));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        typeTable.setBackground(new java.awt.Color(122, 204, 255));
        typeTable.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 204)));
        typeTable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        typeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TYPE"
            }
        ));
        typeTable.setFocusable(false);
        typeTable.setSelectionBackground(new java.awt.Color(102, 40, 204));
        typeTable.setShowVerticalLines(false);
        typeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                typeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(typeTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(341, 130, 466, 497));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Type List");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 95, -1, -1));

        header3.setBackground(new java.awt.Color(0, 0, 104));
        header3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Product Type");

        javax.swing.GroupLayout header3Layout = new javax.swing.GroupLayout(header3);
        header3.setLayout(header3Layout);
        header3Layout.setHorizontalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(770, Short.MAX_VALUE))
        );
        header3Layout.setVerticalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(header3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, -1, -1));

        jButton1.setBackground(new java.awt.Color(204, 102, 0));
        jButton1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 441, 200, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddBtnMouseClicked
      if (typeID.getText().isEmpty())
        {
            typeID.setBackground(new java.awt.Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Type ID field is empty !!");
        }
  else if (typeName.getText().isEmpty())
        {
            typeName.setBackground(new java.awt.Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Type Name field is empty !!");
        }
      
     
         else
        {
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to Add this record!!","Add Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
        try {
            Con = DriverManager.getConnection(dburl, dbuname, dbpass);
            PreparedStatement add = Con.prepareStatement("Insert into type values(?,?)");
            add.setInt(1, Integer.valueOf(typeID.getText()));
            add.setString(2, typeName.getText());

            int row = add.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product Type Successfully Added");
            Con.close();
            SelectType();
            this.typeID.setText("");
            this.typeName.setText("");
             setView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            }
        }
    }//GEN-LAST:event_AddBtnMouseClicked

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed

    }//GEN-LAST:event_AddBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        if (typeID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Type ID is Empty. Please Enter a Type ID to be DELETED");
        } else
        {
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to delete this record!!","delete Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
            try {
                Con = DriverManager.getConnection(dburl, dbuname, dbpass);
                String ID = typeID.getText();
                String Query = "Delete from type where id=" + ID;
                Statement Add = Con.createStatement();
                Add.executeUpdate(Query);
                SelectType();
                this.typeID.setText("");
                this.typeName.setText("");
                 setView();
                JOptionPane.showMessageDialog(this, "Type Deleted Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void UpBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpBtnMouseClicked
         if (typeID.getText().isEmpty())
        {
            typeID.setBackground(new java.awt.Color(255,0,51));
            JOptionPane.showMessageDialog(this,"TypeID field is empty !!");
        }
  else if (typeName.getText().isEmpty())
        {
            typeName.setBackground(new java.awt.Color(255,0,51));
            JOptionPane.showMessageDialog(this,"Type Name field is empty !!");
        }
      
     
         else
        {
            int opt =JOptionPane.showConfirmDialog(null,"Are you sure to Update this record!!","Update Record",JOptionPane.YES_NO_OPTION);
            if (opt==0)

            {
            try {
                Con = DriverManager.getConnection(dburl, dbuname, dbpass);
                String UpdateQuery = "Update type set "
                + "name='" + typeName.getText() + "'"
                + " where id=" + typeID.getText();
                Statement Add = Con.createStatement();
                Add.executeUpdate(UpdateQuery);
                JOptionPane.showMessageDialog(this, "Type Updated Successfully.");
                SelectType();
                this.typeID.setText("");
                this.typeName.setText("");
                 setView();
            } catch (Exception e) {
            }

        }
        }
    }//GEN-LAST:event_UpBtnMouseClicked

    private void UpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UpBtnActionPerformed

    private void typeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeIDActionPerformed

    private void typeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeNameActionPerformed

    private void typeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeTableMouseClicked

        DefaultTableModel model = (DefaultTableModel) typeTable.getModel();
        int Myindex = typeTable.getSelectedRow();
        typeID.setText(model.getValueAt(Myindex, 0).toString());
        typeName.setText(model.getValueAt(Myindex, 1).toString());
    }//GEN-LAST:event_typeTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      typeID.setText("");
       typeName.setText("");
        setView();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton UpBtn;
    private javax.swing.JPanel header3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField typeID;
    private javax.swing.JTextField typeName;
    private javax.swing.JTable typeTable;
    // End of variables declaration//GEN-END:variables
}