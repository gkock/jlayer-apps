package org.jlayer.app;

import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_E;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.jlayer.model.BasedLayer;
import static org.jlayer.app.Patterns.*;

/**
 * Hopfield Network Application / Graphical User Interface
 *
 */
public class HopfieldApp 
{
	// GUI items
	
	static int canvasHeight;
	static int canvasWidth;
	static int canvasMargin;
	
	final static String startInfo1 = "Network connections under construction";
	final static String startInfo2 = "Please wait ...";
	final static String hopfieldInfo = "The underlying Hopfield Network stores two patterns:";
	final static String inputInfo = "Disturbed input pattern:";
	final static String controlInfo = "Control via key input: I (next input) | S (next step) | R (run) | E (Exit)";
	
	final static int milliSec = 500;
	
	enum Command { INIT, STEP, RUN, EXIT, NN }
	
	// Hopfield Network
	
	static HopfieldNet myNet = new HopfieldNet();
	
	// the program
	
	public static void main(String[] args) {
		
		int curRecStep = 0;
		
		// GUI items
		setCanvasSizes();
		StdDraw.setCanvasSize(canvasWidth, canvasHeight);
    	StdDraw.setXscale(-canvasMargin, canvasWidth + canvasMargin);
    	StdDraw.setYscale(-canvasMargin, canvasHeight + 2 * canvasMargin);
    	StdDraw.enableDoubleBuffering();
    	
    	// draw start window
    	drawStartWindow();
    	
    	// create trainPatterns and set recallPat
    	createTrainPatterns(linesNo, colsNo);
    	setRecallPattern(linesNo, colsNo);
    	
    	// create net and store patterns
    	myNet.createNet(linesNo, colsNo);
    	for (int p = 0; p < trainPatsNo; p++) {
    		myNet.setActivations(trainPatterns[p]);
			myNet.storePattern(trainPatterns[p]);
		}
    	
    	// set activations to current recall pattern
    	myNet.setActivations(recallPat);
    	drawWindow(curRecStep);
    	
    	// the runtime loop
    	while (true) {
    		Command nextCmd = getNextCommand();
    		switch(nextCmd) {
    		case INIT:
    			setRecallPattern(linesNo, colsNo);
    			myNet.setActivations(recallPat);
    			curRecStep = 0;
    			drawWindow(curRecStep);
    			StdDraw.pause(milliSec);
    			break;
    		case STEP:
    			myNet.nextActivations();
    			curRecStep++;
    			drawWindow(curRecStep);
    			StdDraw.pause(milliSec);
    			break;
    		case RUN:
    			while (!myNet.nextActivations()) {
    				curRecStep++;
    				drawWindow(curRecStep);
    			}
    			StdDraw.pause(milliSec);
    			break;
    		case NN:
    			StdDraw.pause(milliSec);
    			break;
    		case EXIT:
    			System.exit(0);
    		default:
    			throw new RuntimeException();
    		}
    	}
        
    }
	
	static void drawStartWindow() {
		Font startFont = new Font("Arial", Font.BOLD, canvasMargin);
		StdDraw.setFont(startFont);
		
		StdDraw.clear();
		
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY );;
		StdDraw.filledRectangle(0.0,0.0,canvasMargin+canvasWidth,canvasMargin+2*canvasHeight);
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.textLeft(canvasMargin, canvasHeight/2 + 2*canvasMargin, startInfo1);
		StdDraw.textLeft(canvasMargin, canvasHeight/2, startInfo2);
		
		StdDraw.show();
	}
	
	static void drawWindow(int curStep) {
		
		Font font = new Font("Arial", Font.BOLD, canvasMargin);
		StdDraw.setFont(font);
		
		StdDraw.clear();
		
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY );;
		StdDraw.filledRectangle(0.0,0.0,canvasMargin+canvasWidth,canvasMargin+2*canvasHeight);
		
		// display hopfieldInfo
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.textLeft(0, canvasHeight, hopfieldInfo);
		
		// display training patterns
		double cellSize = canvasHeight/180;
		double deltaX = canvasWidth/6;
		double[] baseCols = new double[] {cellSize, cellSize+2*deltaX};
		
		for (int p = 0; p < trainPatsNo; p++) {
			drawPattern(trainPatterns[p], baseCols[p], canvasHeight-10*cellSize, cellSize);
		}
		
		// display random input pattern
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.textLeft(0, canvasHeight/2 + 2*canvasMargin, inputInfo);
		drawPattern(recallPat, baseCols[0], canvasHeight/2, cellSize);
		
		// display current network activation
		BasedLayer<HopfieldUnit.Signal> curNetState = myNet.getActivations();
		int[][] curState = new int[linesNo][colsNo];
		for (int n = 0; n < linesNo; n++) {
			for (int m = 0; m < colsNo; m++) {
				curState[n][m] = curNetState.get(n,m).val;
			}
		}
		
		String stateInfo = "Network activation step " + Integer.toString(curStep) + ":";
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.textLeft(baseCols[1], canvasHeight/2 + 2*canvasMargin, stateInfo);
		drawPattern(curState, baseCols[1], canvasHeight/2, cellSize);
		
		// display controlInfo
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.textLeft(0, 2*canvasMargin, controlInfo);
        
		StdDraw.show();
	}
	
	static void drawPattern(int[][] pattern, double x, double y, double cellSize) {
		// drawing a pattern, pattern[0][0] at top left position with center (x,y)
		for (int n = 0; n < pattern.length; n++) {
			for (int m = 0; m < pattern[0].length; m++) {
				if (pattern[n][m] == 1) {
					StdDraw.setPenColor(StdDraw.BLUE);
				} else {
					StdDraw.setPenColor(StdDraw.RED);
				}
				StdDraw.filledSquare(x+cellSize*m, y-cellSize*n, cellSize/2);
			}
		}
	}
	
	static Command getNextCommand() {
		Command nextCmd;
    	if (StdDraw.isKeyPressed(VK_I)) {
    		nextCmd = Command.INIT;
    	} else if (StdDraw.isKeyPressed(VK_S)) {
    		nextCmd = Command.STEP;
    	} else if (StdDraw.isKeyPressed(VK_R)) {
    		nextCmd = Command.RUN;
    	} else if (StdDraw.isKeyPressed(VK_E)) {
    		nextCmd = Command.EXIT;
    	} else {
    		nextCmd = Command.NN;
    	}
		return nextCmd;
	}
	
	static void setCanvasSizes() {
		// get screen height and width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenHeight = screenSize.getHeight();
		double screenWidth = screenSize.getWidth();
		
		// set canvasHeight and canvasWidth
		canvasHeight = (int)(screenHeight * 0.8);
		canvasWidth  = (int)(screenWidth * 0.8);
		canvasMargin = 40;
	}

}
