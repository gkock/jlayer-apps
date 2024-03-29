package org.jlayer.app;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class MyLabelBlinker implements ActionListener {  
    private javax.swing.JLabel label;
    private java.awt.Color cor1 = java.awt.Color.red;
    private java.awt.Color cor2 = java.awt.Color.gray;
    private int count;

    public MyLabelBlinker(javax.swing.JLabel label){
        this.label = label;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(count % 2 == 0)
            label.setForeground(cor1);
        else
            label.setForeground(cor2);
        count++;
    }  
}
