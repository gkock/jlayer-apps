package org.jlayer.app;

import java.util.Random;

import org.jlayer.annotations.*;

import static org.jlayer.app.MyUtils.*;

@LayerUnit 
public class KohUnit {
	
	@LayerField 
	Signals	signals;			// signals for decisionMaker
	
	static double  eta, decay;	// parameters for function phi
	static int	   rad;			// radius for weight adaptation
	
	double x1, x2;		// current input
	double w1, w2;		// weights
	
	@LayerField(isIndex = true)
    int[] thisIx;			// index of this unit
	static int[] winIx;		// index of winner
	
	@LayerMethod 
	public void initWeights(Random r) { 
//		System.out.printf("thisIx[0] = %d%n", thisIx[0]);
//		System.out.printf("thisIx[1] = %d%n", thisIx[1]);
		w1 = Double.valueOf(thisIx[0]) / 19;
		w2 = Double.valueOf(thisIx[1]) / 19;
	}
	
	@LayerMethod
	public void nextInput(double x1, double x2){
		this.x1 = x1;
		this.x2 = x2;
		signals.distance = euDist(x1, x2, w1, w2);
	}
	
	@LayerMethod
	public void setWinnerIndex(){
		if (signals.isWinner) winIx = thisIx;
	}
	
	@LayerMethod
	public void adaptWeights(){
		int nhoodDist = nhoodDist(thisIx, winIx);
		if (nhoodDist < rad) {
			w1 = w1 + eta * phi(nhoodDist, decay) * (x1 - w1);
			w2 = w2 + eta * phi(nhoodDist, decay) * (x2 - w2);
		}
	}

}
