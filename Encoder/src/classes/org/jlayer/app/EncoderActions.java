package org.jlayer.app;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.SwingWorker;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import org.jlayer.app.EncoderStatusBar.Status;

class EncoderActions {
	
	EncoderFrame encoderFrame;
	
	EncoderNetwork encoderNetwork;
	EncoderDialog encoderDialog;
	
	Action createAction, initAction, forwardAction, runAction, stopAction;
	Action editAction;
	
    int curPattern = 0;			// used by forwardAction;
    boolean runFlag = false;  	// used by runAction and stopAction;
    
	EncoderActions(EncoderFrame frame) {
		
		this.encoderFrame = frame;
	
		// Create network and dialog
		encoderNetwork = new EncoderNetwork();
		encoderDialog = new EncoderDialog(frame, encoderNetwork);
	        
	    // Create the actions shared by the toolbar and menu.
		createAction = new CreateAction("Create",
	                                     MyUtils.createNavigationIcon("create"),
	                                    "Create network", 
	                                     Integer.valueOf(KeyEvent.VK_C));
		initAction = new InitAction("Init",
									 MyUtils.createNavigationIcon("init"),
                                    "Initialize network", 
                                     Integer.valueOf(KeyEvent.VK_I));
		forwardAction = new ForwardAction("Next",
									 MyUtils.createNavigationIcon("forward"),
								   "Next pattern", 
								    Integer.valueOf(KeyEvent.VK_N));
		runAction =  new RunAction("Run",
									MyUtils.createNavigationIcon("fastForward"),
                                   "Train network", 
                                    Integer.valueOf(KeyEvent.VK_R));
		stopAction =  new StopAction("Stop",
									  MyUtils.createNavigationIcon("pause"),
					                 "Stop training", 
					                  Integer.valueOf(KeyEvent.VK_S));
		
		editAction = new EditAction("Edit",
									 MyUtils.createNavigationIcon("gears"),
					                "Setting parameter", 
					                 Integer.valueOf(KeyEvent.VK_E));
		
		initAction.setEnabled(false);
		forwardAction.setEnabled(false);
		runAction.setEnabled(false);
		stopAction.setEnabled(false);
    }
    
	@SuppressWarnings("serial")
    class CreateAction extends AbstractAction {
        public CreateAction(String text, ImageIcon icon,
                            String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
        	encoderNetwork.createNetwork();
        	curPattern = 0;
        	
        	initAction.setEnabled(true);
        	forwardAction.setEnabled(false);
    		runAction.setEnabled(false);
    		stopAction.setEnabled(false);
    		
    		encoderFrame.encoderPanel.repaint();
    		
    		encoderFrame.encoderStatusBar.setStatus(Status.CREATED);
    		encoderFrame.encoderStatusBar.setNetworkSize(encoderNetwork.patternSize, encoderNetwork.codeSize);
    		encoderFrame.encoderStatusBar.repaint();
    		
        	System.out.println("CreateAction");
        }
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
        	encoderNetwork.initNetwork();
        	
        	forwardAction.setEnabled(true);
    		runAction.setEnabled(true);
    		
        	encoderFrame.encoderPanel.repaint();
        	encoderFrame.encoderStatusBar.setStatus(Status.INITIAILZED);
        	encoderFrame.encoderStatusBar.resetEpochNo();
    		encoderFrame.encoderStatusBar.repaint();
    		
    		System.out.println("InitAction");
        }
    }

    @SuppressWarnings("serial")
    class ForwardAction extends AbstractAction {
        public ForwardAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
        	double error = encoderNetwork.Forward(curPattern);
        	if (++curPattern == encoderNetwork.patternSize) curPattern = 0;
        	encoderFrame.encoderPanel.repaint();
        	
        	encoderFrame.encoderStatusBar.setStatus(Status.FORWARDPASS);
        	encoderFrame.encoderStatusBar.setError(error);
    		encoderFrame.encoderStatusBar.repaint();
    		
        	System.out.println("ForwardAction");
        }
    }
    
    class RunWorker extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
			createAction.setEnabled(false);
			initAction.setEnabled(false);
			forwardAction.setEnabled(false);
			runAction.setEnabled(false);
			stopAction.setEnabled(true);
			
			runFlag = true;
			
			encoderFrame.encoderStatusBar.setStatus(Status.RUNNING);
			encoderFrame.encoderStatusBar.nextEpochNo();
    		encoderFrame.encoderStatusBar.repaint();

			while (runFlag) {
			encoderFrame.encoderStatusBar.nextEpochNo();
			   double error = encoderNetwork.RunEpoch();
			   encoderFrame.encoderPanel.repaint();
			   encoderFrame.encoderStatusBar.setError(error);
			   encoderFrame.encoderStatusBar.repaint();
			}
			createAction.setEnabled(true);
			initAction.setEnabled(true);
			forwardAction.setEnabled(true);
			runAction.setEnabled(true);
			stopAction.setEnabled(false);
			return null ;
        }
    }
    
    @SuppressWarnings("serial")
    class RunAction extends AbstractAction {
        RunAction(String text, ImageIcon icon,
                            String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
        	(new RunWorker()).execute();
        	System.out.println("RunAction");
        }
    }
    
    @SuppressWarnings("serial")
    class StopAction extends AbstractAction {
        StopAction(String text, ImageIcon icon,
                            String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
        	runFlag = false;
        	encoderFrame.encoderStatusBar.setStatus(Status.STOPPED);
    		encoderFrame.encoderStatusBar.repaint();
        	System.out.println("StopAction");
        }
    }
    
    @SuppressWarnings("serial")
    class EditAction extends AbstractAction {
        EditAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            encoderDialog.getReferences();
            encoderDialog.setVisible(true);
        	System.out.println("EditAction");
        }
    }

}
