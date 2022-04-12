package org.jlayer.app;

import java.util.Random;

import org.jlayer.annotations.*;

@LayerUnit 
public class MatrixUnit {
	
	double[][] opA, opB;
	int l, m, n;
	
	@LayerField(isIndex = true)
	int[] ix;
	
	@LayerField 
	public double result;
	
	@LayerField 
	public MatrixUnit[] vector;
	
	@LayerMethod 
	public void setOperands(double[][] opA, double[][] opB) { 
		this.opA = opA; 
		this.opB = opB;
		this.l = opA.length;
		this.m = opB.length;
		this.n = opB[0].length;
	}
	
	@LayerMethod 
	public void multiply() { 
		int il = ix[0];
		int in = ix[1];
		double result = 0.0;
		for(int lm = 0; lm < m; lm++) {
			result += opA[il][lm] * opB[lm][in];
		}
		this.result = result;
	}
	
	@LayerMethod(runtimeMode=RuntimeMode.FORKJOIN)
	public void multiplyP() { 
		int il = ix[0];
		int in = ix[1];
		double result = 0.0;
		for(int lm = 0; lm < m; lm++) {
			result += opA[il][lm] * opB[lm][in];
		}
		this.result = result;
	}

	@LayerMethod 
	public double getResult() { 
		return result;
	}
}
