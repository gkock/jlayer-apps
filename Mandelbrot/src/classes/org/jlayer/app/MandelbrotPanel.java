package org.jlayer.app;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class MandelbrotPanel extends javax.swing.JPanel {
	
	// basic reference
	MandelbrotNet mandelbrotNet;
	
	// constructor
    public MandelbrotPanel(MandelbrotNet mandelbrotNet) {
    	setBorder(BorderFactory.createLineBorder(Color.black));
    	this.mandelbrotNet = mandelbrotNet;
    }
    
    @Override
    public void paintComponent(Graphics g){
    	int width = getWidth();
    	int height = getHeight();
    	if (mandelbrotNet.mandelbrotImage == null) {
            super.paintComponent(g);  // fill with background color, gray
    	} else {
        	g.drawImage(mandelbrotNet.mandelbrotImage,
        			    0, 0, width, height,
        			    0, 0, mandelbrotNet.width, mandelbrotNet.height,
        			    null);
        }
    }

}
