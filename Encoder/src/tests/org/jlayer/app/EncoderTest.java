package org.jlayer.app;

import java.util.Set;
import java.util.HashSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * tests EncoderNet.
 * @author Gerd Kock
 */
@Test
public class EncoderTest {
	
	EncoderNetwork encoderNet;
	
	private void showActivations() {
		Output[] output = encoderNet.outputLayer.getD1().getBase();
		Hidden[] hidden = encoderNet.hiddenLayer.getD1().getBase();
		Input[]  input = encoderNet.inputLayer.getD1().getBase();
		StringBuilder inputString, hiddenString, outputString;
		
		for (int p = 0; p < encoderNet.patternSize; p++) {
			encoderNet.Forward(p);
			
			inputString = new StringBuilder("INPUT   ");
			for (int i = 0; i < output.length; i++) {
				inputString.append(String.format("%.2f ", input[i].y.val));
			}
			hiddenString = new StringBuilder("HIDDEN  ");
			for (int i = 0; i < hidden.length; i++) {
				hiddenString.append(String.format("%.2f ", hidden[i].y.val));
			}
			outputString = new StringBuilder("OUTPUT  ");
			for (int i = 0; i < output.length; i++) {
				outputString.append(String.format("%.2f ", output[i].y.val));
			}
			
			System.out.printf("=============================%n");
			System.out.println(inputString);
			System.out.println(hiddenString);
			System.out.println(outputString);
		}
		System.out.printf("=============================%n");
	}
	
	
	@BeforeClass
    public void beforeClass() {
		encoderNet = new EncoderNetwork();
		// fixed settings w.r.t. showActivations() !!!
		encoderNet.patternSize = 4;  //******
		encoderNet.codeSize = 2;     //******
		encoderNet.randomSeed = 1003;  //******
		encoderNet.eta = 0.25;       //******
	}

	
	@Test(groups = "createNetwork")
	public void testCreateNetwork() {
		encoderNet.createNetwork();
//		System.out.println("TEST testCreateNetwork");
	}
	
	@Test(groups = "initNetwork", dependsOnGroups  = "createNetwork")
	public void testInitNetwork() {
		encoderNet.initNetwork();
//		System.out.println("TEST testInitNetwork");
	}
	
//	private double err;
//	@Test(groups = "trainEpoch", dependsOnGroups  = "initNetwork")
//	public void testTrainEpoch() {
//		err = 0.0;
//		for (int p = 0; p < encoderNet.patternSize; p++) {
//			err += encoderNet.Forward(p);
//			encoderNet.Backward();
//		}
////		System.out.println("TEST testTrainEpoch");
//	}
//	
//	@Test(groups = "Final1", dependsOnGroups  = "trainEpoch")
//	public void testFina1() {
//		int epoch = 0;
//		do {
//			testTrainEpoch();
//			epoch++;
//		} while (err > 0.005);
//		System.out.printf("==========Final1=============%n");
//		System.out.printf("epoch number = %d%n", epoch);
//		System.out.printf("total squared error = %.4f%n", err);
//		showActivations();
////		System.out.println("TEST testFinal");
//	}
	
	@Test(groups = "Final2", dependsOnGroups  = "initNetwork")
	public void testFinal2() {
		double err;
		int epoch = 0;
		do {
			err = encoderNet.RunEpoch();
			epoch++;
		} while (err > 0.005);
		System.out.printf("==========Final2=============%n");
		System.out.printf("epoch number = %d%n", epoch);
		System.out.printf("total squared error = %.4f%n", err);
		showActivations();
//		System.out.println("TEST testFinal");
	}
		
}
