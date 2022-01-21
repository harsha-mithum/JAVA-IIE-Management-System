/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.maindashboad;

import javax.swing.JPanel;

/**
 *
 * @author coolsasisndu
 */
public class JpanelLoader {
   
   public  void jPanelLoader(JPanel adminmenu,JPanel setPanel){
      adminmenu.removeAll();
     
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(adminmenu);
        adminmenu.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        System.gc();
    
    }

   
   
}
