package org.jlayer.app;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class EncoderStatusBar extends javax.swing.JPanel {
	
	enum Status {
		UNDEFINED, CREATED, INITIAILZED, FORWARDPASS, RUNNING, STOPPED
	}
	
	private Font myFont = new Font("MyFont", Font.BOLD, 12);
	private Status status = Status.UNDEFINED;
	private int patternSize, codeSize;
	private int epochNo;
	private double error;
	
	void setNetworkSize(int patternSize, int codeSize){
		this.patternSize = patternSize;
		this.codeSize = codeSize;
	}
	
	void setStatus(Status status){
		this.status = status;
	}
	
	void resetEpochNo() {
		epochNo = 0;
	}
	void nextEpochNo() {
		epochNo++;
	}
	
	void setError(double error){
		this.error = error;
	}
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g); 
    	g.setFont(myFont);
    	
    	// network status
    	g.setColor(Color.BLACK);
    	g.drawString("Network status:",10,20);
    	g.setColor(Color.RED);
    	g.drawString(status.name(), 110, 20);
    	
    	// network size
    	if (status != Status.UNDEFINED){
    		g.setColor(Color.BLACK);
        	g.drawString("Pattern size:",250,20);
        	g.drawString("Code size:",250,40);
        	g.setColor(Color.RED);
        	g.drawString(String.valueOf(patternSize), 350, 20);
        	g.drawString(String.valueOf(codeSize), 350, 40);
    	}
    	
    	// epoch info
    	if (status == Status.FORWARDPASS){
    		g.setColor(Color.BLACK);
        	g.drawString("Epoch no:",400,20);
        	g.drawString("Pattern error:",400,40);
        	g.setColor(Color.RED);
        	g.drawString(String.valueOf(epochNo), 550, 20);
        	g.drawString(String.valueOf(error), 550, 40);
    	}
    	
    	// epoch info
    	if ( (status == Status.RUNNING) || (status == Status.STOPPED) ){
    		g.setColor(Color.BLACK);
        	g.drawString("Epoch no:",400,20);
        	g.drawString("Epoch error:",400,40);
        	g.setColor(Color.RED);
        	g.drawString(String.valueOf(epochNo), 550, 20);
        	g.drawString(String.valueOf(error), 550, 40);
    	}

    }

}
