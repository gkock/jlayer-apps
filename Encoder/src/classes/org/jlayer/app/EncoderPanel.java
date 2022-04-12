package org.jlayer.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class EncoderPanel extends javax.swing.JPanel {
	
	private Font myFont = new Font("MyFont", Font.BOLD, 12);
	
	// basic reference
	EncoderNetwork encoderNetwork;
	
	// default color
	Color defaultColor;
	
	// parameters to be set for paintComponent()
	int patternSize, codeSize;
	int unitHeight, unitWidth, unitGap;
	int fieldSize;
	int heightOffset, patternOffset, codeOffset;
	
    public EncoderPanel(EncoderNetwork encoderNetwork) {
    	Color defaultColor = this.getBackground();
    	setBorder(BorderFactory.createLineBorder(Color.black));
    	this.encoderNetwork = encoderNetwork;
    }
    
    private void setPaintParameters() {
    	patternSize = encoderNetwork.patternSize;
    	codeSize = encoderNetwork.codeSize;
    	
    	unitHeight = 100;
    	unitWidth = 10;
    	unitGap = 5;
    	
    	heightOffset = 50;
    	
    	patternOffset = unitWidth;
    	int gapPattern = getWidth() - ( patternSize * (unitWidth + unitGap) - unitGap );
    	if (gapPattern > 2 * patternOffset) patternOffset = gapPattern / 2;
    	
    	codeOffset = unitWidth;
    	int gapCode = getWidth() - ( codeSize * (unitWidth + unitGap) - unitGap );
    	if (gapCode > 2 * codeOffset) codeOffset = gapCode / 2;
    }
    
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(dimWidth, dimHeight);
//    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g); 
//    	g.drawString("This is MyPanelBoard!",10,20);
 
        if (encoderNetwork.inputLayer==null) return;
        
        setPaintParameters();
        
        g.setFont(myFont);
        g.setColor(Color.RED);
        g.drawString("OUTPUT LAYER", patternOffset, heightOffset - 5);
        g.drawString("HIDDEN LAYER", codeOffset, 5 * heightOffset - 5);
        g.drawString("INPUT LAYER", patternOffset, 9 * heightOffset - 5);
        
        for (int i = 0; i < patternSize; i++) {
        	g.setColor(Color.BLACK);
    		g.drawRect(patternOffset + i * (unitWidth + unitGap),
       	     	   	   heightOffset,
       	     	       unitWidth, unitHeight);
    		
    		double value = encoderNetwork.outputLayer.y.get(i).val;
    		int redHeight = (int)Math.round(unitHeight * value);
    		
    		g.setColor(Color.GREEN);
    		g.fillRect(patternOffset + i * (unitWidth + unitGap),
          	     	   heightOffset,
          	     	   unitWidth, unitHeight - redHeight);
    		g.setColor(Color.RED);
    		g.fillRect(patternOffset + i * (unitWidth + unitGap),
       	     	   heightOffset + unitHeight - redHeight,
       	     	   unitWidth, redHeight);
        }
        for (int i = 0; i < codeSize; i++) {
        	g.setColor(Color.BLACK);
    		g.drawRect(codeOffset + i * (unitWidth + unitGap),
       	     	       5 * heightOffset,
       	     	       unitWidth, unitHeight);
    		
    		double value = encoderNetwork.hiddenLayer.y.get(i).val;
    		int redHeight = (int)Math.round(unitHeight * value);
    		
    		g.setColor(Color.GREEN);
    		g.fillRect(codeOffset + i * (unitWidth + unitGap),
          	     	   5 * heightOffset,
          	     	   unitWidth, unitHeight - redHeight);
    		g.setColor(Color.RED);
    		g.fillRect(codeOffset + i * (unitWidth + unitGap),
          	     	   5 * heightOffset + unitHeight - redHeight,
          	     	   unitWidth, redHeight);
        }
        for (int i = 0; i < patternSize; i++) {
        	g.setColor(Color.BLACK);
    		g.drawRect(patternOffset + i * (unitWidth + unitGap),
       	     	       9 * heightOffset,
       	     	       unitWidth, unitHeight);
    		
        	double value = encoderNetwork.inputLayer.y.get(i).val;
        	int redHeight = (int)Math.round(unitHeight * value);
        	
    		g.setColor(Color.GREEN);
    		g.fillRect(patternOffset + i * (unitWidth + unitGap),
          	     	   9 * heightOffset,
          	     	   unitWidth, unitHeight - redHeight);
    		g.setColor(Color.RED);
    		g.fillRect(patternOffset + i * (unitWidth + unitGap),
       	     	   9 * heightOffset + unitHeight - redHeight,
       	     	   unitWidth, redHeight);
        }
    }

}
