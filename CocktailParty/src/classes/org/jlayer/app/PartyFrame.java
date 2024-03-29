/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jlayer.app;

/**
 *
 * @author gko
 */
@SuppressWarnings("serial")
public class PartyFrame extends javax.swing.JFrame {

    /**
     * Creates new form LifeJFrame
     */
    public PartyFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        partyActions = new org.jlayer.app.PartyActions(this);
        jToolBar2 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton(partyActions.createAction);
        jButton7 = new javax.swing.JButton(partyActions.initAction);
        jButton1 = new javax.swing.JButton(partyActions.stepAction);
        jButton8 = new javax.swing.JButton(partyActions.runAction);
        jButton9 = new javax.swing.JButton(partyActions.stopAction);
        jScrollPane1 = new javax.swing.JScrollPane();
        partyPanel = new PartyPanel(partyActions.partyNetwork);
        partyBlinker = new javax.swing.JLabel();
        partyBlinker.setVisible(false);
        partyStatusBar = new org.jlayer.app.PartyStatusBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem(partyActions.createAction);
        jMenuItem2 = new javax.swing.JMenuItem(partyActions.initAction);
        jMenuItem6 = new javax.swing.JMenuItem(partyActions.stepAction);
        jMenuItem3 = new javax.swing.JMenuItem(partyActions.runAction);
        jMenuItem4 = new javax.swing.JMenuItem(partyActions.stopAction);
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem(partyActions.editAction);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cocktail Party");

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setAutoscrolls(true);

        jButton6.setText("Create");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.setText("");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton6);

        jButton7.setText("Init");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.setText("");
        jToolBar2.add(jButton7);

        jButton1.setText("Next");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.setText("");
        jToolBar2.add(jButton1);

        jButton8.setText("Run");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.setText("");
        jToolBar2.add(jButton8);

        jButton9.setText("Stop");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.setText("");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton9);

        partyBlinker.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        partyBlinker.setForeground(new java.awt.Color(255, 0, 0));
        partyBlinker.setText("  NETWORK UNDER CONSTRUCTION");

        javax.swing.GroupLayout partyPanelLayout = new javax.swing.GroupLayout(partyPanel);
        partyPanel.setLayout(partyPanelLayout);
        partyPanelLayout.setHorizontalGroup(
            partyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partyPanelLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(partyBlinker, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
        );
        partyPanelLayout.setVerticalGroup(
            partyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partyPanelLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(partyBlinker, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(partyPanel);

        javax.swing.GroupLayout partyStatusBarLayout = new javax.swing.GroupLayout(partyStatusBar);
        partyStatusBar.setLayout(partyStatusBarLayout);
        partyStatusBarLayout.setHorizontalGroup(
            partyStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        partyStatusBarLayout.setVerticalGroup(
            partyStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jMenu1.setText("Network");

        jMenuItem1.setText("Create");
        jMenuItem1.setIcon(null);
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Init");
        jMenuItem2.setIcon(null);
        jMenu1.add(jMenuItem2);

        jMenuItem6.setText("Next");
        jMenuItem6.setIcon(null);
        jMenu1.add(jMenuItem6);

        jMenuItem3.setText("Run");
        jMenuItem3.setIcon(null);
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Stop");
        jMenuItem4.setIcon(null);
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Options");

        jMenuItem5.setText("Parameters");
        jMenuItem5.setIcon(null);
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(partyStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 1032, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(partyStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(PartyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PartyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PartyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PartyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PartyFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar2;
    javax.swing.JLabel partyBlinker;
    javax.swing.JPanel partyPanel;
    org.jlayer.app.PartyStatusBar partyStatusBar;
    // End of variables declaration//GEN-END:variables

    // my variables
    PartyActions partyActions;
}
