package org.jlayer.app;

import org.jlayer.annotations.*;

import static org.jlayer.app.MyUtils.*;

@LayerUnit 
public class DecisionUnit {
	
	@LayerField 
	Signals[] signals;
	
	@LayerMethod
	void detWinnerIndex() {
		double 	winDist = signals[0].distance;
		int 	winIx 	= 0;
		for (int i = 1; i < signals.length; i++) {
			if (signals[i].distance < winDist) {
				winDist = signals[i].distance;
				winIx = i;
			}
		}
		for (int i = 0; i < signals.length; i++) {
			signals[i].isWinner = false;
		}
		signals[winIx].isWinner = true;
	}

}
