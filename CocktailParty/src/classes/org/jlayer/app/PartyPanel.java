package org.jlayer.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class PartyPanel extends javax.swing.JPanel {
	
	// basic reference
	PartyNetwork partyNetwork;
	
	// default color
	Color defaultColor;
	
	// parameters to be set for paintComponent()
	int lines, columns;
	int fieldSize;
	int heightOffset, widthOffset;
	Person chosenPerson;
	
    public PartyPanel(PartyNetwork partyNetwork) {
//    	Color defaultColor = this.getBackground();
    	setBorder(BorderFactory.createLineBorder(Color.black));
    	this.partyNetwork = partyNetwork;
    }
    
    private void setPaintParameters() {
    	lines = partyNetwork.floorLayer.length();
    	columns = partyNetwork.floorLayer.length(0);
    	fieldSize = partyNetwork.fieldSize;
    	
    	heightOffset = 0;
    	int gapHeight = getHeight()/fieldSize - lines;
    	if (gapHeight>0) heightOffset = gapHeight/2*fieldSize;
    	
    	widthOffset = 0;
    	int gapWidth = getWidth()/fieldSize - columns;
    	if (gapWidth>0) widthOffset = gapWidth/2*fieldSize;
    	
    	chosenPerson = null;
    	boolean choiceOK = (partyNetwork.chosenPerson >= 0) && (partyNetwork.chosenPerson < partyNetwork.personCnt);
    	if (partyNetwork.isInitialized && choiceOK) {
    		chosenPerson = partyNetwork.personArray[partyNetwork.chosenPerson];
    	}
    }
    
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(dimWidth, dimHeight);
//    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g); 
    	//g.drawString("This is MyPanelBoard!",10,20);
 
        if (partyNetwork.floorLayer==null) return;
        
        setPaintParameters();
        
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
            	int xPos = j*fieldSize + widthOffset;
            	int yPos = i*fieldSize + heightOffset;
            	int xName = xPos;
            	int yName = yPos + fieldSize;
            	Person curPerson = partyNetwork.floorLayer.get(i, j).person;
            	if (curPerson != null){
            		String curName = curPerson.name;
            		if (curPerson == chosenPerson) {
            			g.setColor(Color.BLACK);
            		} else if (curPerson == chosenPerson.personA) {
            			g.setColor(Color.RED);
            		} else if (curPerson == chosenPerson.personB) {
            			g.setColor(Color.GREEN);
            		} else {
            			g.setColor(Color.WHITE);
            		}
                    g.fillRect(xPos, yPos, fieldSize, fieldSize);
                    g.setColor(Color.BLACK);
                    g.drawString(curName, xName, yName);
                }
                g.setColor(Color.BLACK);
                g.drawRect(xPos, yPos, fieldSize, fieldSize);        
            }
        }
    }

}
