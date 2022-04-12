package org.jlayer.app;


import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class MandelbrotStatusBar extends javax.swing.JPanel {
	
	enum Status {
		UNDEFINED, INITIALIZED, RUNNINGLOOP, AFTERLOOP, RUNNINGPARALLEL, AFTERPARALLEL
	}
	
	private Font 	myFont = new Font("MyFont", Font.BOLD, 12);
	private Status 	status = Status.UNDEFINED;
	private long 	startTime, endTime;
	private int 	noOfProcessors = Runtime.getRuntime().availableProcessors();
	
	void setStatus(Status status){
		this.status = status;
	}
	
	void startCount() {
		startTime = System.currentTimeMillis();
	}
	void stopCount() {
		endTime = System.currentTimeMillis();
	}
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g); 
    	g.setFont(myFont);
    	
    	// network status
    	g.setColor(Color.BLACK);
    	g.drawString("Status:",10,20);
    	g.setColor(Color.RED);
    	g.drawString(status.name(), 70, 20);
    	
    	// generation
		g.setColor(Color.BLACK);
    	g.drawString("Available processors:", 200, 20);
    	g.setColor(Color.RED);
    	g.drawString(String.valueOf(noOfProcessors), 330, 20);
    	
    	// further info
    	String timeInfo = "";
    	String furtherInfo = "";
    	if (status == Status.RUNNINGLOOP) {
    		g.setColor(Color.BLACK);
        	g.drawString("Running loop code ...", 390, 20);
    	} else if (status == Status.AFTERLOOP) {
    		g.setColor(Color.BLACK);
        	g.drawString("Running loop code took:", 390, 20);
        	furtherInfo = String.format("%d ms", endTime-startTime);
        	g.setColor(Color.RED);
        	g.drawString(furtherInfo, 550, 20);
    	} else if (status == Status.RUNNINGPARALLEL) {
    		g.setColor(Color.BLACK);
        	g.drawString("Running parallel code ...", 390, 20);
    	} else if (status == Status.AFTERPARALLEL) {
    		g.setColor(Color.BLACK);
        	g.drawString("Running parallel code took:", 390, 20);
        	furtherInfo = String.format("%d ms", endTime-startTime);
        	g.setColor(Color.RED);
        	g.drawString(furtherInfo, 550, 20);
    	}
    }

}
