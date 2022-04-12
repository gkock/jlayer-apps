package org.jlayer.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.SwingWorker;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class MandelbrotActions {
	
	MandelbrotFrame mandelbrotFrame;
	MandelbrotNet   mandelbrotNet;
	
	Action initAction, loopAction, parallelAction;
	
	// constructor
	MandelbrotActions(MandelbrotFrame frame) {
		
		mandelbrotFrame = frame;
	
		// Create network and dialog
		mandelbrotNet = new MandelbrotNet();
	        
	    // Create the actions shared by the toolbar and menu.
		initAction = new InitAction("Create",
	                                 MyUtils.createNavigationIcon("create"),
	                                "Create network", 
	                                 Integer.valueOf(KeyEvent.VK_C));
		loopAction = new LoopAction("Loop",
									 MyUtils.createNavigationIcon("forward"),
                                    "Run loop code", 
                                    Integer.valueOf(KeyEvent.VK_I));
		parallelAction =  new ParallelAction("Run parallel code",
							  				  MyUtils.createNavigationIcon("fastForward"),
							  				 "Run parallel code", 
							  				Integer.valueOf(KeyEvent.VK_R));
		
		loopAction.setEnabled(false);
		parallelAction.setEnabled(false);
    }
	
	@SuppressWarnings("serial")
	class InitAction extends AbstractAction {
        public InitAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
        	
        	mandelbrotNet.createNetwork();
        	
        	mandelbrotFrame.mandelbrotPanel.setPreferredSize(new Dimension(1400, 1150));
        	mandelbrotFrame.mandelbrotPanel.repaint();
        	mandelbrotFrame.mandelbrotStatusBar.setStatus(MandelbrotStatusBar.Status.INITIALIZED);
        	mandelbrotFrame.mandelbrotStatusBar.repaint();
        	
        	loopAction.setEnabled(true);
    		parallelAction.setEnabled(true);
    		
//    		System.out.println("InitAction");
        }
    }
	
	class LoopWorker extends SwingWorker<Void, Void> {
		@Override
        public Void doInBackground() {
			
			initAction.setEnabled(false);
			loopAction.setEnabled(false);
			parallelAction.setEnabled(false);
			
			mandelbrotFrame.mandelbrotStatusBar.setStatus(MandelbrotStatusBar.Status.RUNNINGLOOP);
        	mandelbrotFrame.mandelbrotStatusBar.repaint();
			
    		Timer timerLB = new Timer(500, new MyLabelBlinker(mandelbrotFrame.myBlinker));
    		mandelbrotFrame.myBlinker.setVisible(true);
    		timerLB.start();
    		mandelbrotFrame.mandelbrotStatusBar.startCount();
    		
    		long startTime = System.currentTimeMillis();
    		mandelbrotNet.setRGB_LOOP();
    		long stopTime = System.currentTimeMillis();
//			System.out.println("Calling setRGB_LOOP() took " + (stopTime - startTime) + " ms");
    		
            mandelbrotFrame.mandelbrotStatusBar.stopCount();
            timerLB.stop();
            mandelbrotFrame.myBlinker.setVisible(false);
            
            mandelbrotFrame.mandelbrotPanel.repaint();
            mandelbrotFrame.mandelbrotStatusBar.setStatus(MandelbrotStatusBar.Status.AFTERLOOP);
        	mandelbrotFrame.mandelbrotStatusBar.repaint();
            
            initAction.setEnabled(true);
			
			return null;
		}
	}
	
	@SuppressWarnings("serial")
	class LoopAction extends AbstractAction {
        public LoopAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
        	(new LoopWorker()).execute();
//            System.out.println("LoopAction");
        }
    }
	
	class ParallelWorker extends SwingWorker<Void, Void> {
		@Override
        public Void doInBackground() {
			
			initAction.setEnabled(false);
			loopAction.setEnabled(false);
			parallelAction.setEnabled(false);
			
			mandelbrotFrame.mandelbrotStatusBar.setStatus(MandelbrotStatusBar.Status.RUNNINGPARALLEL);
        	mandelbrotFrame.mandelbrotStatusBar.repaint();
			
    		Timer timerLB = new Timer(500, new MyLabelBlinker(mandelbrotFrame.myBlinker));
    		mandelbrotFrame.myBlinker.setVisible(true);
    		timerLB.start();
    		mandelbrotFrame.mandelbrotStatusBar.startCount();
    		
    		long startTime = System.currentTimeMillis();
    		mandelbrotNet.setRGB_PARALLEL();
    		long stopTime = System.currentTimeMillis();
//			System.out.println("Calling setRGB_PARALLEL() took " + (stopTime - startTime) + " ms");
    		
            mandelbrotFrame.mandelbrotStatusBar.stopCount();
            timerLB.stop();
            mandelbrotFrame.myBlinker.setVisible(false);
            
            mandelbrotFrame.mandelbrotPanel.repaint();
            mandelbrotFrame.mandelbrotStatusBar.setStatus(MandelbrotStatusBar.Status.AFTERPARALLEL);
        	mandelbrotFrame.mandelbrotStatusBar.repaint();
            
            initAction.setEnabled(true);
			
			return null;
		}
	}
	
	@SuppressWarnings("serial")
	class ParallelAction extends AbstractAction {
        public ParallelAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
			(new ParallelWorker()).execute();
//            System.out.println("ParallelAction");
        }
    }

}
