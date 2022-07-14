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
 * Game of Life / User Interface
 *
 */
public class LifeApp 
{
	final static int canvasSize = getCanvasSize();
	final static int canvasMargin = canvasSize/40;
	final static int cellSize = canvasSize/200;
	
	final static int layerWidth = canvasSize / cellSize;
	final static int layerHeight = canvasSize / cellSize;
	
	final static int milliSec = 500;
	
	final static String controlInfo = "Control via key input: I (Init) | S (Step) | R (Run) | P (Pause) | E (Exit)";
	final static String startInfo1 = "Network connections under construction";
	final static String startInfo2 = "Please wait ...";
	
	final static Font controlFont = new Font("Arial", Font.PLAIN, canvasMargin);
	final static Font startFont = new Font("Arial", Font.BOLD, canvasMargin);
	
	enum Command { INIT, STEP, RUN, PAUSE, EXIT, NN }
	
	static LifeUniverse myNet = new LifeUniverse();
	static int seed = 4711;
	static boolean running = false;
	
	public static void main(String[] args) {
		
		StdDraw.setCanvasSize(canvasSize, canvasSize);
    	StdDraw.setXscale(-canvasMargin, canvasSize + canvasMargin);
    	StdDraw.setYscale(-canvasMargin, canvasSize + 2 * canvasMargin);
    	StdDraw.enableDoubleBuffering();
    	
    	drawStartWindow();
    	myNet.createNet(layerWidth, layerHeight);
    	myNet.initNet(seed++);
    	drawWindow();
    	StdDraw.pause(milliSec);
    	
    	// TEST OUTPUT
//    	System.out.printf("canvasSize = %d%n", canvasSize);
//    	System.out.printf("layerWidth = %d%n", layerWidth);
//    	System.out.printf("layerHeight = %d%n", layerHeight);
    	
    	while (true) {
    		Command nextCmd = getNextCommand();
    		switch(nextCmd) {
    		case INIT:
    			myNet.initNet(seed++);
    			running = false;
    			StdDraw.pause(milliSec);
    			break;
    		case STEP:
    			myNet.updateNet();
    			running = false;
    			StdDraw.pause(milliSec);
    			break;
    		case RUN:
    			myNet.updateNet();
    			running = true;
    			break;
    		case PAUSE:
    			running = false;
    			StdDraw.pause(milliSec);
    			break;
    		case NN:
    			if (running) myNet.updateNet();
    			break;
    		case EXIT:
    			System.exit(0);
    		default:
    			throw new RuntimeException();
    		}
    		drawWindow();
    	}
        
    }
	
	static void drawStartWindow() {
		StdDraw.setFont(startFont);
		StdDraw.setPenColor(StdDraw.RED);
		
		StdDraw.clear();
		StdDraw.textLeft(canvasSize/8 + 2*canvasMargin, canvasSize/2 + 3*canvasMargin, startInfo1);
		StdDraw.textLeft(canvasSize/4 + 4*canvasMargin, canvasSize/2 + canvasMargin, startInfo2);
		StdDraw.show();
	}
	
	static void drawWindow() {
		StdDraw.setFont(controlFont);
		StdDraw.setPenColor(StdDraw.BLUE);
		
		StdDraw.clear();
		StdDraw.textLeft(0, canvasSize + canvasMargin, controlInfo);
		StdDraw.setPenColor(StdDraw.RED);
		for (int i = 0; i < layerWidth; i++) {
			for (int j = 0; j < layerHeight; j++) {
				if (myNet.getState(i, j) == 1) {
					StdDraw.filledSquare(cellSize*i, cellSize*j, cellSize);
				}
			}
		}
		StdDraw.show();
	}
	
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
    
}
