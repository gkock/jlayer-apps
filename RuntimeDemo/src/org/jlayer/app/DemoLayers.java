package org.jlayer.app;

//import org.jlayer.util.IndexTools;
import org.jlayer.model.BasedLayer;
import org.jlayer.util.LayerBase;

public class DemoLayers {
	
	// the underlying demo layer
	DemoUnit[] demoArray;
	Layer_DemoUnit_ demoLayer;
	
	// parameter layer
	Integer[]           paramArray;
	BasedLayer<Integer> paramLayer;
	
	// result layer
	BasedLayer<Integer> resultLayer;
	
	// the size of all layers (and arrays)
	int size = 50;
	
	public void createLayers() {
		// demoLayer wraps demoArray
		demoArray = new DemoUnit[size];
		for(int i = 0; i < size; i++) {
			demoArray[i] = new DemoUnit();
		}
		demoLayer = new Layer_DemoUnit_(demoArray);
	
		// paramLayer wraps paramArray
		paramArray = new Integer[size];
		for(int i = 0; i < size; i++) {
			paramArray[i] = i;
		}
		paramLayer = new LayerBase<Integer>(paramArray);
	}
	
	public void runLoopMethods() {
		// simple layer method
		demoLayer.loopMethod1();
		
		// test output
		System.out.println("loopMethod1() ready");
		
		// layer method with parameter
		resultLayer = demoLayer.loopMethod2(0);	
		
		// test output
		System.out.print("loopMethod2() returns: ");
		for(int i = 0; i < size; i++) {
			System.out.print(resultLayer.get(i));
		}
		System.out.println();
		
		// layer method with layer parameter
		resultLayer = demoLayer.loopMethod3(paramLayer);
		
		// test output
		System.out.print("loopMethod3() returns: ");
		for(int i = 0; i < size; i++) {
			System.out.print(resultLayer.get(i));
		}
		System.out.println();
	}
	
	public void runForkJoinMethods() {
		// simple layer method
		demoLayer.forkjoinMethod1();
		
		// test output
		System.out.println("loopMethod1() ready");
		
		// layer method with parameter
		resultLayer = demoLayer.forkjoinMethod2(0);	
		
		// test output
		System.out.print("loopMethod2() returns: ");
		for(int i = 0; i < size; i++) {
			System.out.print(resultLayer.get(i));
		}
		System.out.println();
		
		// layer method with layer parameter
		resultLayer = demoLayer.forkjoinMethod3(paramLayer);
		
		// test output
		System.out.print("loopMethod3() returns: ");
		for(int i = 0; i < size; i++) {
			System.out.print(resultLayer.get(i));
		}
		System.out.println();
	}

}
