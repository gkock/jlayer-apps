package org.jlayer.app;

import java.util.Random;

import org.jlayer.annotations.*;
import org.jlayer.app.MyUtils.Signal;

import static org.jlayer.app.MyUtils.*;

@LayerUnit 
public class Hidden {
	
	@LayerField	Signal   y = new Signal();	// fanout signal
	@LayerField	Signal[] x;          		// input vector
	@LayerField	Signal[] inErr;      		// incoming error signals
	
	// outgoing error signals     NOT FOR FIRST HIDDEN LAYER !!!
//	@LayerField	Signal[] outErr;     		// outgoing error signals
	
	Signal   b;          		// bias
	Signal[] w;          		// weight vector
	
	
	// call initSignals() after connect/associate
	@LayerMethod
	public void initSignals(Random r) {
		b = new Signal();
		b.val = r.nextDouble();
		w = new Signal[x.length];
		for (int i = 0; i < x.length; i++) {
			w[i] = new Signal();
			w[i].val = r.nextDouble();
		}
	}
	
	@LayerMethod
	public void Forward() {
		y.val = sigmoid(dotProd(w, x) + b.val);
	}
	
	@LayerMethod
	public void Backward(double eta) {
		// compute delta
		double sum = 0.0;
		for (Signal s : inErr) { sum += s.val; }
		double delta = y.val * (1 - y.val) * sum;
		
		// set outgoing error signals     NOT FOR FIRST HIDDEN LAYER !!!
//		for (int i = 0; i < w.length; i++) {
//			outErr[i].val = delta * w[i].val;
//		}
		
		// adjust bias and weights
		b.val = b.val + eta * delta;
		for (int i = 0; i < w.length; i++) {
			w[i].val = w[i].val + eta * delta * x[i].val;
		}
	}

}
