/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.srb.inventory;


import javax.swing.JPanel;

/**
 *
 * @author coolsasisndu
 */
public class JpanelLoader {
   
   public  void jPanelLoader(JPanel Invenmain,JPanel setPanel){
      Invenmain.removeAll();
     
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Invenmain);
        Invenmain.setLayout(layout);
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
