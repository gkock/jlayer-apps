package org.jlayer.app;

import java.util.Random;

import org.jlayer.annotations.*;

import static org.jlayer.app.MyUtils.*;

@LayerUnit 
public class KohonenUnit {
	
	// weights
	double w1, w2;
	
	// parameters for weight adaptation
	static int	   rad = 4;
	static double  eta = 0.2;
	static double  decay = 0.5;
	
	@LayerField 
	Signals	signals;			// decisionMaker connection
	
	@LayerField(isIndex = true)
    int[] thisIx;				// index of this unit
	static int[] winIx;			// index of winner
	
	@LayerMethod 
	public void initWeights(Random r, double lwb, double upb) { 
		w1 = r.nextDouble(lwb, upb);
		w2 = r.nextDouble(lwb, upb);
	}
	
	@LayerMethod
	public void setDistance(double x1, double x2) {
		signals.distance = euDist(x1, x2, w1, w2);
	}
	
	@LayerMethod
	public void setWinnerIndex(){
		if (signals.isWinner) winIx = thisIx;
	}
	
	@LayerMethod
	public void adaptWeights(double x1, double x2){
		double euDist = euDist(thisIx, winIx);
		if (euDist < rad) {
			w1 = w1 + eta * phi(euDist, decay) * (x1 - w1);
			w2 = w2 + eta * phi(euDist, decay) * (x2 - w2);
		}
	}

}
