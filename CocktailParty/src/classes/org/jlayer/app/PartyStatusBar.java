package org.jlayer.app;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class PartyStatusBar extends javax.swing.JPanel {
	
	enum Status {
		UNDEFINED, CONSTRUCTING, CREATED, INITIALIZED, AFTERSTEP, RUNNING, STOPPED
	}
	
	private Font myFont = new Font("MyFont", Font.BOLD, 12);
	private Status status = Status.UNDEFINED;
	private long startTime, endTime;
	private int startGen, endGen, currentGen = -1;
	private String personMe = "NN";
	private String personA = "NN";
	private String personB = "NN";
	private Decider.Strategy strategy = Decider.Strategy.A_Me_B;
	
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
	
	void setPersonsAndStrategy(PartyNetwork partyNetwork){
		personMe = personA = personB = "NN";
		if ( (partyNetwork.chosenPerson >= 0) && (partyNetwork.chosenPerson < partyNetwork.personCnt) ) {
			personMe = String.valueOf(partyNetwork.chosenPerson);
			personA = partyNetwork.personArray[partyNetwork.chosenPerson].personA.name;
			personB = partyNetwork.personArray[partyNetwork.chosenPerson].personB.name;
		}
		strategy = partyNetwork.strategy;
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
    	
    	// step counter
		g.setColor(Color.BLACK);
    	g.drawString("Step counter:",250,20);
    	g.setColor(Color.RED);
    	g.drawString(String.valueOf(currentGen), 350, 20);
    	
    	// person info
    	if ( (status != Status.UNDEFINED) && (status != Status.CONSTRUCTING) && (status != Status.CREATED) ){
    		g.setColor(Color.BLACK);
    		g.drawString("Chosen strategy:", 10, 50);
    		g.setColor(Color.RED);
    		g.drawString(String.valueOf(strategy), 110, 50);
    		g.setColor(Color.BLACK);
    		g.drawString("Me = " + personMe, 250, 50);
    		g.setColor(Color.RED);
    		g.drawString("A =", 350, 50);
    		g.setColor(Color.BLACK);
    		g.drawString(personA, 372, 50);
    		g.setColor(Color.GREEN);
    		g.drawString("B =", 450, 50);
    		g.setColor(Color.BLACK);
    		g.drawString(personB, 472, 50);
    	}
    	
    	
    	// further info
    	String furtherInfo = "";
    	String timeInfo = "";
    	if (status == Status.CREATED) {
    		furtherInfo = "Creation time (msec):";
    		timeInfo = String.valueOf(endTime-startTime);
    	} else if (status == Status.STOPPED) {
    		furtherInfo = "steps / msecs:";
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
