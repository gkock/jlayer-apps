package org.jlayer.app;

import org.jlayer.annotations.*;
import org.jlayer.app.DecisionUnit.DecisionIO;

@LayerUnit 
public class DecisionUnit {
	
	static class DecisionIO {
		double  distance;				// Euclidean distance
		boolean isWinner; 				// smallest distance to input
	}
	
	@LayerField 
	DecisionIO[] decisionIO;
	
	@LayerMethod
	void detWinnerIndex() {
		double 	winDist = decisionIO[0].distance;
		int 	winIx 	= 0;
		for (int i = 1; i < decisionIO.length; i++) {
			if (decisionIO[i].distance < winDist) {
				winDist = decisionIO[i].distance;
				winIx = i;
			}
		}
		for (int i = 0; i < decisionIO.length; i++) {
			decisionIO[i].isWinner = false;
		}
		decisionIO[winIx].isWinner = true;
	}

}
