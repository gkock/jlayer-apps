package org.jlayer.app;

import static java.awt.event.KeyEvent.VK_L;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_E;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Runtime Demo / User Interface
 *
 */
public class DemoApp 
{
	enum Command { LOOP, PARALLEL, EXIT, NN }
	
	static int  noOfCores = Runtime.getRuntime().availableProcessors();
	static long startTime, endTime;
	static long loopRuntime;
	static long parRuntime;
	
	final static int canvasSize = getCanvasSize();
	final static int canvasMargin = canvasSize/40;
	final static Font canvasFont = new Font("Arial", Font.BOLD, canvasMargin);
	
	final static String controlInfo = "Control via key input: L (Loop) | P (Parallel) | E (Exit)";
	final static String basicInfo1 = "This program runs on a CPU with " + noOfCores + " available processor cores";
	final static String basicInfo2 = "Three types of layer methods are executed in a loop or in parallel";
	
	static DemoLayers demoLayers = new DemoLayers();
	
	// the main loop
	
	public static void main(String[] args) {
		
		StdDraw.setCanvasSize(canvasSize, canvasSize/2);
    	StdDraw.setXscale(-canvasMargin, canvasSize + canvasMargin);
    	StdDraw.setYscale(-canvasMargin, canvasSize + 2 * canvasMargin);
    	StdDraw.enableDoubleBuffering();
    	
    	demoLayers.createLayers();	
    	drawWindow(Command.NN);

    	while (true) {
    		Command nextCmd = getNextCommand();
    		switch(nextCmd) {
    		case LOOP:
    			drawWindow(Command.LOOP);
    			System.out.println("Executing methods in a loop ...");
    			
    			startTime = System.currentTimeMillis();
    			demoLayers.runLoopMethods();
    			endTime = System.currentTimeMillis();
    			loopRuntime = endTime - startTime;
    			
    			System.out.println("Execution took " + loopRuntime + " milliseconds.");
    			System.out.println();
    			break;
    		case PARALLEL:
    			drawWindow(Command.PARALLEL);
    			System.out.println("Executing methods in parallel ...");
    			
    			startTime = System.currentTimeMillis();
    			demoLayers.runForkJoinMethods();
    			endTime = System.currentTimeMillis();
    			parRuntime = endTime - startTime;
    			
    			System.out.println("Execution took " + parRuntime + " milliseconds.");
    			System.out.println();
    			break;
    		case NN:
    			drawWindow(Command.NN);
    			break;
    		case EXIT:
    			System.exit(0);
    		default:
    			throw new RuntimeException();
    		}
    	}
        
    }
	
	static void drawWindow(Command command) {
		String runInfo0 = "Press a key to execute the methods!";
		String loopString = "???";
		String parString ="???";
		
		if (command==Command.LOOP) runInfo0 = ">>> Methods are being executed in a loop. Please wait ... <<<";
		if (command==Command.PARALLEL) runInfo0 = ">>> Methods are being executed in parallel. Please wait ... <<<";
		
		if (loopRuntime != 0) loopString = String.valueOf(loopRuntime);
		final String runInfo1 = "Executing methods in a loop took " + loopString + " milliseconds";
		
		if (parRuntime != 0) parString = String.valueOf(parRuntime);
		final String runInfo2 = "Executing methods in parallel took " + parString + " milliseconds";
		
		StdDraw.clear();
		StdDraw.setFont(canvasFont);
		
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.textLeft(canvasMargin, canvasSize - canvasMargin, controlInfo);

		StdDraw.setPenColor();
		StdDraw.textLeft(canvasMargin, canvasSize/2 + 12*canvasMargin, basicInfo1);
		StdDraw.textLeft(canvasMargin, canvasSize/2 + 9*canvasMargin, basicInfo2);
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.textLeft(canvasMargin, canvasSize/2 + 1*canvasMargin, runInfo0);
		
		StdDraw.setPenColor();
		StdDraw.textLeft(canvasMargin, canvasSize/2 - 9*canvasMargin, runInfo1);
		StdDraw.textLeft(canvasMargin, canvasSize/2 - 12*canvasMargin, runInfo2);
		
		StdDraw.show();
	}
	
	
	static Command getNextCommand() {
		Command nextCmd;
    	if (StdDraw.isKeyPressed(VK_L)) {
    		nextCmd = Command.LOOP;
    	} else if (StdDraw.isKeyPressed(VK_P)) {
    		nextCmd = Command.PARALLEL;
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
		return (int)canvasSize;
	}
    
}
