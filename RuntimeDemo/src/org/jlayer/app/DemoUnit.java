package org.jlayer.app;

import org.jlayer.annotations.LayerField;
import org.jlayer.annotations.LayerMethod;
import org.jlayer.annotations.LayerUnit;
import org.jlayer.annotations.RuntimeMode;

import org.jlayer.annotations.LayerParam;

@LayerUnit
public class DemoUnit {
	
	long duration = 100;
	
	@LayerMethod
	public void loopMethod1(){
		long startTime = System.currentTimeMillis();
		while ( (System.currentTimeMillis() - startTime) < duration) { }
	}
	
	@LayerMethod
	public int loopMethod2(int par){
		long startTime = System.currentTimeMillis();
		while ( (System.currentTimeMillis() - startTime) < duration) { }
		return par;
	}
	
	@LayerMethod
	public int loopMethod3(@LayerParam int par){
		long startTime = System.currentTimeMillis();
		while ( (System.currentTimeMillis() - startTime) < duration) { }
		return par;
	}
	
	@LayerMethod(runtimeMode=RuntimeMode.FORKJOIN)
	public void forkjoinMethod1(){
		long startTime = System.currentTimeMillis();
		while ( (System.currentTimeMillis() - startTime) < duration) { }
	}
	
	@LayerMethod(runtimeMode=RuntimeMode.FORKJOIN)
	public int forkjoinMethod2(int par){
		long startTime = System.currentTimeMillis();
		while ( (System.currentTimeMillis() - startTime) < duration) { }
		return par;
	}
	
	@LayerMethod(runtimeMode=RuntimeMode.FORKJOIN)
	public int forkjoinMethod3(@LayerParam int par){
		long startTime = System.currentTimeMillis();
		while ( (System.currentTimeMillis() - startTime) < duration) { }
		return par;
	}

}
