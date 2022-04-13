package org.jlayer.app;

import java.util.Random;

import org.jlayer.annotations.*;
import org.jlayer.app.MyUtils.Signal;

import static org.jlayer.app.MyUtils.*;

@LayerUnit 
public class Output {
	
	@LayerField Signal   y = new Signal();	// fanout signal
	@LayerField Signal[] x;          		// input vector
	@LayerField Signal[] outErr;     		// outgoing error signals
	
	Signal   b;          					// bias
	Signal[] w;        						// weight vector
	Signal   t;          					// target signal
	
	// call initSignals() after connect/associate
	@LayerMethod
	public void initSignals(Random r) {
		y = new Signal();
		b = new Signal();
		b.val = r.nextDouble();
		w = new Signal[x.length];
		for (int i = 0; i < x.length; i++) {
			w[i] = new Signal();
			w[i].val = r.nextDouble();
		}
		t = new Signal();
	}
	
	@LayerMethod
	public void setTarget(@LayerParam double target){
		t.val = target;
	}
	
	@LayerMethod
	public double getError() {
		return Math.pow(t.val - y.val, 2);
	}
	
	@LayerMethod
	public void Forward() {
		y.val = sigmoid(dotProd(w, x) + b.val);
	}
	
	@LayerMethod
	public void Backward(double eta) {
		// compute delta
		double delta = y.val * (1 - y.val) * (t.val - y.val);
		
		// set outgoing error signals
		for (int i = 0; i < w.length; i++) {
			outErr[i].val = delta * w[i].val;
		}
		
		// adjust bias and weights
		b.val = b.val + eta * delta;
		for (int i = 0; i < w.length; i++) {
			w[i].val = w[i].val + eta * delta * x[i].val;
		}
	}

}
