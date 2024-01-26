package org.jlayer.app;

import org.jlayer.annotations.*;

@LayerUnit 
public class HopfieldUnit {
	
	static class Signal {
		int val;						// bipolar values
	}
	
	@LayerField 
	Signal activation = new Signal();	// activation signal
	Signal prev = new Signal();			// previous activation
	
	@LayerField
	Signal[] input;						// signals from other units
	int[] weight;						// weight vector
	
	@LayerField(isIndex = true)
    int[] ix;							// index of this unit
	
	@LayerMethod
	void initWeights() {
		weight = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			weight[i] = 0;
		}
	}
	
	@LayerMethod
	void setActivation(int[][] pattern) {
		activation.val = pattern[ix[0]][ix[1]];
	}
	
	@LayerMethod
	void storePattern() {
		for (int i = 0; i < input.length; i++) {
			weight[i] = weight[i] + activation.val * input[i].val;
		}
	}
	
	@LayerMethod
	void nextActivation() {
		int dotproduct = 0;
		for (int i = 0; i < input.length; i++) {
			dotproduct += weight[i] * input[i].val;
		}
		prev.val = activation.val;
		activation.val = (int)Math.signum(dotproduct);
		if (activation.val == 0) activation.val = prev.val;
	}
	
	@LayerMethod
	boolean isStable() {
		return activation.val == prev.val;
	}
	
	@LayerMethod
	Signal getActivation() {
		return activation;
	}

}
