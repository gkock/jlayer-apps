package org.jlayer.app;

import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_E;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Kohonen Map Application / User Interface
 *
 */
public class KohonenApp 
{
	final static int canvasSize = getCanvasSize();
	final static int canvasMargin = canvasSize/40;
	
	final static String startInfo1 = "Network connections under construction";
	final static String startInfo2 = "Please wait ...";
	final static String controlInfo = "Control via key input: I (Init) | S (Step) | R (Run) | P (Pause) | E (Exit)";
	
	final static Font startFont = new Font("Arial", Font.BOLD, canvasMargin);
	final static Font controlFont = new Font("Arial", Font.PLAIN, canvasMargin);
	
	final static int milliSec = 500;
	
	static boolean running = false;
	static int     counter;
	
	enum Command { INIT, STEP, RUN, PAUSE, EXIT, NN }
	
	static KohonenNet myNet = new KohonenNet();
	final static int layerWidth = 25;
	final static int layerHeight = 25;
	
	
	public static void main(String[] args) {
		
		StdDraw.setCanvasSize(canvasSize, canvasSize);
    	StdDraw.setXscale(-canvasMargin, canvasSize + canvasMargin);
    	StdDraw.setYscale(-canvasMargin, canvasSize + 2 * canvasMargin);
    	StdDraw.enableDoubleBuffering();
    	
    	drawStartWindow();
    	myNet.createNet(layerWidth, layerHeight);
    	myNet.initNet();
    	running = false;
    	counter = 0;
    	drawWindow();
    	StdDraw.pause(milliSec);
    	
    	// main loop
    	while (true) {
    		Command nextCmd = getNextCommand();
    		switch(nextCmd) {
    		case INIT:
    			myNet.initNet();
    			running = false;
    			counter = 0;
    			StdDraw.pause(milliSec);
    			break;
    		case STEP:
    			myNet.trainNet();
    			counter++;
    			running = false;
    			StdDraw.pause(milliSec);
    			break;
    		case RUN:
    			myNet.trainNet();
    			counter++;
    			running = true;
    			break;
    		case PAUSE:
    			running = false;
    			StdDraw.pause(milliSec);
    			break;
    		case NN:
    			if (running) {
    				myNet.trainNet();
    				counter++;
    			}
    			break;
    		case EXIT:
    			System.exit(0);
    		default:
    			throw new RuntimeException();
    		}
    		drawWindow();
    	}
        
    }
	
	// drawing aids
	
	static void drawStartWindow() {
		StdDraw.setFont(startFont);
		StdDraw.clear();
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.textLeft(canvasSize/8 + 2*canvasMargin, canvasSize/2 + 3*canvasMargin, startInfo1);
		StdDraw.textLeft(canvasSize/4 + 4*canvasMargin, canvasSize/2 + canvasMargin, startInfo2);
		
		StdDraw.show();
	}
	
	static void drawWindow() {
		StdDraw.setFont(controlFont);
		StdDraw.clear();
		
		// draw control info
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.textLeft(0, canvasSize + canvasMargin, controlInfo);
		
		// draw weight matrix 
		StdDraw.setPenColor(StdDraw.RED);
		double x1, y1, x2, y2;
		double[] w_ij, w_Ij, w_iJ;
		for (int i = 0; i < layerWidth; i++) {
			for (int j = 0; j < layerHeight; j++) {
				w_ij = myNet.getWeights(i, j);
				x1 = w_ij[0] * canvasSize;
				y1 = w_ij[1] * canvasSize;
				
				// draw line from w(i,j) to w(i+1,j)
				if (i+1 < layerWidth) {
					w_Ij = myNet.getWeights(i+1, j);
					x2 = w_Ij[0] * canvasSize;
					y2 = w_Ij[1] * canvasSize;
					StdDraw.line(x1, y1, x2, y2);
				}
					
				// draw line from w(i,j) to w(i,j+1)
				if (j+1 < layerHeight) {
					w_iJ = myNet.getWeights(i, j+1);
					x2 = w_iJ[0] * canvasSize;
					y2 = w_iJ[1] * canvasSize;
					StdDraw.line(x1, y1, x2, y2);
				}
			}
		}
		
		// draw status info
		String strCounter = String.valueOf(counter);
		String statusInfo = "Current training step is " + strCounter;
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.textLeft(0, 0, statusInfo);
		
		StdDraw.show();
	}
	
	static int getCanvasSize() {
		// get screen height and width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenHeight = screenSize.getHeight();
		double screenWidth = screenSize.getWidth();
		
		// set canvasSize and return it
		double canvasSize;
		if (screenHeight < screenWidth) {
			canvasSize =  (int)(screenHeight * 0.8);
		} else {
			canvasSize =  (int)(screenWidth * 0.8);
		}
		canvasSize = canvasSize - canvasSize % 100;
		return (int)canvasSize;
	}
	
	// getting the next command
	
	static Command getNextCommand() {
		Command nextCmd;
    	if (StdDraw.isKeyPressed(VK_I)) {
    		nextCmd = Command.INIT;
    	} else if (StdDraw.isKeyPressed(VK_S)) {
    		nextCmd = Command.STEP;
    	} else if (StdDraw.isKeyPressed(VK_R)) {
    		nextCmd = Command.RUN;
    	} else if (StdDraw.isKeyPressed(VK_P)) {
    		nextCmd = Command.PAUSE;
    	} else if (StdDraw.isKeyPressed(VK_E)) {
    		nextCmd = Command.EXIT;
    	} else {
    		nextCmd = Command.NN;
    	}
		return nextCmd;
	}
    
}
