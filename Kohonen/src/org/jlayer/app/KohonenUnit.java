package org.jlayer.app;

import java.util.Random;

import org.jlayer.annotations.*;

@LayerUnit 
public class KohonenUnit { // for a two-dimensional Kohonen map
	
	static int[] winIx;    // index of winner unit        
	double[] w;            // weight vector for representing the input space
	
	// parameters for weight adaptation
	static int	   rad = 4;
	static double  eta = 0.2;
	static double  decay = 0.5;
	
	@LayerField 
	DecisionUnit.DecisionIO decisionIO;
	
	@LayerField(isIndex = true)
    int[] thisIx;         // index of this unit
	
	@LayerMethod 
	public void initWeights(Random r, double lwb, double upb) { 
		w = new double[2];
		w[0] = r.nextDouble(lwb, upb);
		w[1] = r.nextDouble(lwb, upb);
	}
	
	@LayerMethod
	public void nextInputSample(double x0, double x1) {
		decisionIO.distance = euDist(x0, x1, w[0], w[1]);
	}
	
	@LayerMethod
	public void setWinnerIndex(){
		if (decisionIO.isWinner) winIx = thisIx;
	}
	
	@LayerMethod
	public void adaptWeights(double x0, double x1){
		double euDist = euDist(thisIx, winIx);
		if (euDist < rad) {
			w[0] = w[0] + eta * phi(euDist, decay) * (x0 - w[0]);
			w[1] = w[1] + eta * phi(euDist, decay) * (x1 - w[1]);
		}
	}
	
	// utility functions
	
	// Euclidean distance
	static double euDist(double x0, double x1, double w0, double w1) {
		double delta1 = x0 - w0;
		double delta2 = x1 - w1;
		double result = Math.sqrt(delta1*delta1 + delta2*delta2);
		return result;
	}
	static double euDist(int[] ix0, int[] ix1) {
		double delta1 = ix0[0] - ix1[0];
		double delta2 = ix0[1] - ix1[1];
		double result = Math.sqrt(delta1*delta1 + delta2*delta2);
		return result;
	}
	
	// adaptation function
	static double phi(double dist, double decay) {
	    return 1.0 / Math.exp(decay * dist);
	}

}
