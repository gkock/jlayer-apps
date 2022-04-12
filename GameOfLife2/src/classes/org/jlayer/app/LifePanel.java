package org.jlayer.app;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class LifePanel extends javax.swing.JPanel {
	
	// basic reference
	LifeNetwork lifeNetwork;
	
	// default color
	Color defaultColor;
	
	// parameters to be set for paintComponent()
	int lines, columns;
	int fieldSize;
	int heightOffset, widthOffset;
	
    public LifePanel(LifeNetwork myNetwork) {
    	Color defaultColor = this.getBackground();
    	setBorder(BorderFactory.createLineBorder(Color.black));
    	this.lifeNetwork = myNetwork;
    }
    
    private void setPaintParameters() {
    	lines = lifeNetwork.unitLayer.length();
    	columns = lifeNetwork.unitLayer.length(0);
    	fieldSize = lifeNetwork.fieldSize;
    	
    	heightOffset = 0;
    	int gapHeight = getHeight()/fieldSize - lines;
    	if (gapHeight>0) heightOffset = gapHeight/2*fieldSize;
    	
    	widthOffset = 0;
    	int gapWidth = getWidth()/fieldSize - columns;
    	if (gapWidth>0) widthOffset = gapWidth/2*fieldSize;
    }
    
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(dimWidth, dimHeight);
//    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g); 
    	//g.drawString("This is MyPanelBoard!",10,20);
 
        if (lifeNetwork.unitLayer==null) return;
        
        setPaintParameters();
        
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                if (lifeNetwork.unitLayer.state.get(i, j) == 1){
//                    g.setColor(Color.GREEN);
                    g.setColor(Color.RED);
                    g.fillRect(j*fieldSize + widthOffset,
                    		   i*fieldSize + heightOffset,
                    		   fieldSize,fieldSize);
                }
                g.setColor(Color.BLACK);
                g.drawRect(j*fieldSize + widthOffset,
             		       i*fieldSize + heightOffset,
             		       fieldSize,fieldSize);         
            }
        }
    }

}
