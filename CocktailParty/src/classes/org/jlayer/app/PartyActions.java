package org.jlayer.app;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.SwingWorker;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.jlayer.app.PartyStatusBar.Status;

class PartyActions {
	
	PartyFrame partyFrame;
	
	PartyNetwork partyNetwork;
	PartyDialog partyDialog;
	
	Action createAction, initAction, stepAction, runAction, stopAction;
	Action editAction;
	
	PartyActions(PartyFrame frame) {
		
		this.partyFrame = frame;
	
		// Create network and dialog
		partyNetwork = new PartyNetwork();
		partyDialog = new PartyDialog(frame, partyNetwork);
	        
	    // Create the actions shared by the toolbar and menu.
		createAction = new CreateAction("Create",
	                                     MyUtils.createNavigationIcon("create"),
	                                    "Create network", 
	                                     Integer.valueOf(KeyEvent.VK_C));
		initAction = new InitAction("Init",
									 MyUtils.createNavigationIcon("init"),
                                    "Initialize network", 
                                    Integer.valueOf(KeyEvent.VK_I));
		stepAction = new StepAction("Next",
									 MyUtils.createNavigationIcon("forward"),
								   "Next generation", 
								   Integer.valueOf(KeyEvent.VK_N));
		runAction =  new RunAction("Run",
									MyUtils.createNavigationIcon("fastForward"),
                                   "Run network", 
                                   Integer.valueOf(KeyEvent.VK_R));
		stopAction =  new StopAction("Stop",
									  MyUtils.createNavigationIcon("pause"),
					                 "Stop running", 
					                 Integer.valueOf(KeyEvent.VK_S));
		
		editAction = new EditAction("Edit",
									 MyUtils.createNavigationIcon("gears"),
					                "This is the edit action.", 
					                Integer.valueOf(KeyEvent.VK_E));
		
		initAction.setEnabled(false);
		stepAction.setEnabled(false);
		runAction.setEnabled(false);
		stopAction.setEnabled(false);
    }
	
    class CreateWorker extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
        	partyFrame.partyStatusBar.setStatus(Status.CONSTRUCTING);
        	partyFrame.partyStatusBar.repaint();
        	initAction.setEnabled(false);
            stepAction.setEnabled(false);
    		runAction.setEnabled(false);
    		stopAction.setEnabled(false);
    		
    		Timer timerLB = new Timer(500, new MyLabelBlinker(partyFrame.partyBlinker));
    		partyFrame.partyBlinker.setVisible(true);
    		timerLB.start();
        	partyFrame.partyStatusBar.startCount();
            partyNetwork.createNetwork();
            partyFrame.partyStatusBar.stopCount();
            timerLB.stop();
            partyFrame.partyBlinker.setVisible(false);
            
            initAction.setEnabled(true);
    		
    		int dimHeight = partyNetwork.lines * partyNetwork.fieldSize;
    		int dimWidth = partyNetwork.columns * partyNetwork.fieldSize;
    		Dimension dim = new Dimension(dimWidth, dimHeight);
    		
    		partyFrame.partyPanel.setPreferredSize(dim);
    		partyFrame.partyPanel.repaint();
    		partyFrame.partyStatusBar.setStatus(Status.CREATED);
    		partyFrame.partyStatusBar.resetGenNo();
    		partyFrame.partyStatusBar.repaint();
			return null ;
        }
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
        	(new CreateWorker()).execute();
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
            partyNetwork.initNetwork();
            
            stepAction.setEnabled(true);
            runAction.setEnabled(true);
            stopAction.setEnabled(true);
            
            partyFrame.partyPanel.repaint();
            partyFrame.partyStatusBar.setStatus(Status.INITIALIZED);
    		partyFrame.partyStatusBar.resetGenNo();
    		partyFrame.partyStatusBar.setPersonsAndStrategy(partyNetwork);
            partyFrame.partyStatusBar.repaint();
        }
    }
    
    @SuppressWarnings("serial")
    class StepAction extends AbstractAction {
        public StepAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            partyNetwork.moveMe();
            
            partyFrame.partyPanel.repaint();
            partyFrame.partyStatusBar.setStatus(Status.AFTERSTEP);
		    partyFrame.partyStatusBar.incGenNo();
		    partyFrame.partyStatusBar.setPersonsAndStrategy(partyNetwork);
		    partyFrame.partyStatusBar.repaint();
        }
    }
    
    class RunWorker extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
			createAction.setEnabled(false);
			initAction.setEnabled(false);
			stepAction.setEnabled(false);
			runAction.setEnabled(false);
			
			partyNetwork.runFlag = true;
			partyFrame.partyStatusBar.setStatus(Status.RUNNING);
			partyFrame.partyStatusBar.startCount();
			while (partyNetwork.runFlag) {
			   partyNetwork.moveMe();
			   partyFrame.partyPanel.repaint();
			   partyFrame.partyStatusBar.incGenNo();
			   partyFrame.partyStatusBar.setPersonsAndStrategy(partyNetwork);
			   partyFrame.partyStatusBar.repaint();
			}
			partyFrame.partyStatusBar.stopCount();
			
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
            partyNetwork.stop();
            
            createAction.setEnabled(true);
        	initAction.setEnabled(true);
        	stepAction.setEnabled(true);
        	runAction.setEnabled(true);
            
        	partyFrame.partyStatusBar.stopCount();
        	partyFrame.partyStatusBar.setStatus(Status.STOPPED);
        	partyFrame.partyStatusBar.setPersonsAndStrategy(partyNetwork);
        	partyFrame.partyStatusBar.repaint();
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
            partyDialog.getReferences();
            partyDialog.setVisible(true);
        }
    }

}
