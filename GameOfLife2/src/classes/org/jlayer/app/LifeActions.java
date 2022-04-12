package org.jlayer.app;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.SwingWorker;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.jlayer.app.LifeStatusBar.Status;

class LifeActions {
	
	LifeFrame lifeFrame;
	
	LifeNetwork lifeNetwork;
	LifeDialog lifeDialog;
	
	Action createAction, initAction, stepAction, runAction, stopAction;
	Action editAction;
	
	LifeActions(LifeFrame frame) {
		
		this.lifeFrame = frame;
	
		// Create network and dialog
		lifeNetwork = new LifeNetwork();
		lifeDialog = new LifeDialog(frame, lifeNetwork);
	        
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
        	lifeFrame.lifeStatusBar.setStatus(Status.CONSTRUCTING);
        	lifeFrame.lifeStatusBar.repaint();
        	initAction.setEnabled(false);
            stepAction.setEnabled(false);
    		runAction.setEnabled(false);
    		stopAction.setEnabled(false);
    		
    		Timer timerLB = new Timer(500, new MyLabelBlinker(lifeFrame.lifeBlinker));
    		lifeFrame.lifeBlinker.setVisible(true);
    		timerLB.start();
        	lifeFrame.lifeStatusBar.startCount();
            lifeNetwork.createNetwork();
            lifeFrame.lifeStatusBar.stopCount();
            timerLB.stop();
            lifeFrame.lifeBlinker.setVisible(false);
            
            initAction.setEnabled(true);
            stepAction.setEnabled(true);
    		runAction.setEnabled(true);
    		stopAction.setEnabled(true);
    		
    		int dimHeight = lifeNetwork.lines * lifeNetwork.fieldSize;
    		int dimWidth = lifeNetwork.columns * lifeNetwork.fieldSize;
    		Dimension dim = new Dimension(dimWidth, dimHeight);
    		
    		lifeFrame.lifePanel.setPreferredSize(dim);
    		lifeFrame.lifePanel.repaint();
    		lifeFrame.lifeStatusBar.setStatus(Status.CREATED);
    		lifeFrame.lifeStatusBar.resetGenNo();
    		lifeFrame.lifeStatusBar.repaint();
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
            lifeNetwork.initNetwork();
            lifeFrame.lifePanel.repaint();
            lifeFrame.lifeStatusBar.setStatus(Status.INITIALIZED);
    		lifeFrame.lifeStatusBar.resetGenNo();
            lifeFrame.lifeStatusBar.repaint();
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
			lifeFrame.lifeStatusBar.setStatus(Status.AFTERSTEP);
            lifeNetwork.nextStep();
            lifeFrame.lifePanel.repaint();
		    lifeFrame.lifeStatusBar.incGenNo();
		    lifeFrame.lifeStatusBar.repaint();
        }
    }
    
    class RunWorker extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
			createAction.setEnabled(false);
			initAction.setEnabled(false);
			stepAction.setEnabled(false);
			lifeNetwork.runFlag = true;
			lifeFrame.lifeStatusBar.setStatus(Status.RUNNING);
			lifeFrame.lifeStatusBar.startCount();
			while (lifeNetwork.runFlag) {
			   lifeNetwork.nextStep();
			   lifeFrame.lifePanel.repaint();
			   lifeFrame.lifeStatusBar.incGenNo();
			   lifeFrame.lifeStatusBar.repaint();
			}
			lifeFrame.lifeStatusBar.stopCount();
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
            lifeNetwork.stop();
        	lifeFrame.lifeStatusBar.stopCount();
        	lifeFrame.lifeStatusBar.setStatus(Status.STOPPED);
        	lifeFrame.lifeStatusBar.repaint();
        	createAction.setEnabled(true);
        	initAction.setEnabled(true);
        	stepAction.setEnabled(true);
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
            lifeDialog.getReferences();
            lifeDialog.setVisible(true);
        }
    }

}
