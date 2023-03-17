package org.jlayer.app;

class MyUtils {
	
	static class Signals {
		double 	distance;	// Euclidean distance
		boolean isWinner;
	}
	
	// Euclidean distance
	static double euDist(double x1, double x2, double w1, double w2) {
		double delta1 = x1 - w1;
		double delta2 = x2 - w2;
		double result = Math.sqrt(delta1*delta1 + delta2*delta2);
		return result;
	}
	static double euDist(int[] ix1, int[] ix2) {
		double delta1 = ix1[0] - ix2[0];
		double delta2 = ix1[1] - ix2[1];
		double result = Math.sqrt(delta1*delta1 + delta2*delta2);
		return result;
	}
	
	// neighborhood distance
	static int nhoodDist(int[] ix1, int[] ix2) {
		int val1 = Math.abs(ix1[0] - ix2[0]);
		int val2 = Math.abs(ix1[1] - ix2[1]);
		int distance = Math.max(val1, val2);
		return distance;
	}
    
	// adaptation function
	static double phi(int dist, double decay) {
	    return 1.0 / Math.exp(decay * dist);
	}
	static double phi(double dist, double decay) {
	    return 1.0 / Math.exp(decay * dist);
	}

}
