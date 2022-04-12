package org.jlayer.app;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class LifeStatusBar extends javax.swing.JPanel {
	
	enum Status {
		UNDEFINED, CONSTRUCTING, CREATED, INITIALIZED, AFTERSTEP, RUNNING, STOPPED
	}
	
	private Font myFont = new Font("MyFont", Font.BOLD, 12);
	private Status status = Status.UNDEFINED;
	private long startTime, endTime;
	private int startGen, endGen, currentGen = -1;
	
	void setStatus(Status status){
		this.status = status;
	}
	
	void startCount() {
		startTime = System.currentTimeMillis();
		startGen = currentGen;
	}
	void stopCount() {
		endTime = System.currentTimeMillis();
		endGen = currentGen;
	}
	
	void resetGenNo(){
		currentGen = 0;
	}
	void incGenNo(){
		currentGen++;
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
    	
    	// generation
		g.setColor(Color.BLACK);
    	g.drawString("Generation:",250,20);
    	g.setColor(Color.RED);
    	g.drawString(String.valueOf(currentGen), 350, 20);
    	
    	// further info
    	String furtherInfo = "";
    	String timeInfo = "";
    	if (status == Status.CREATED) {
    		furtherInfo = "Creation time (msec):";
    		timeInfo = String.valueOf(endTime-startTime);
    	} else if (status == Status.STOPPED) {
    		furtherInfo = "Generations / msecs:";
    		float genCnt = endGen-startGen;
    		float timeCnt = endTime-startTime;
    		timeInfo = String.valueOf(genCnt / timeCnt);
    	}
    	if (furtherInfo != "") {
    		g.setColor(Color.BLACK);
        	g.drawString(furtherInfo,400,20);
        	g.setColor(Color.RED);
        	g.drawString(timeInfo, 550, 20);
    	}
    }

}
