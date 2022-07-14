package org.jlayer.app;

import org.jlayer.model.*;
import org.jlayer.util.*;

import java.util.Random;

public class EncoderNetwork {
	
	EncoderNetwork() {
		patternSize = 64;
		codeSize = 6;
		randomSeed = 4711;
		eta = 0.6;
	}
	
	int patternSize, codeSize;
	int randomSeed;
	double eta;
	
	Output[] outputArray;
	Hidden[] hiddenArray;
	Input[]  inputArray;
	
	Layer_Output_ outputLayer;
	Layer_Hidden_ hiddenLayer;
	Layer_Input_  inputLayer;
	
	Relation full = new Relation() {
		@Override
		public boolean contains(int[] x, int[] y) {
			return true;
		}
	};
	
	Double[][] patternPool;
	
	void createNetwork() {
		
		inputArray  = new Input[patternSize];
		hiddenArray = new Hidden[codeSize];
		outputArray = new Output[patternSize];
		
		for (int i = 0; i < patternSize; i++) {
			inputArray[i] = new Input();
			outputArray[i] = new Output();
			
		}
		for (int i = 0; i < codeSize; i++) {
			hiddenArray[i] = new Hidden();
			
		}
		
		outputLayer = new Layer_Output_(outputArray);
		hiddenLayer = new Layer_Hidden_(hiddenArray);
		inputLayer = new Layer_Input_(inputArray);
		
		hiddenLayer.x.connect(inputLayer.y, full);
		outputLayer.x.connect(hiddenLayer.y, full);
		outputLayer.outErr.associate(hiddenLayer.inErr, full);
		
		patternPool = new Double[patternSize][patternSize];
		for (int i = 0; i < patternSize; i++) {
			for (int j = 0; j < patternSize; j++) {
				patternPool[i][j] = (i == j) ? 1.0 : 0.0;
			}
		}
	}
	
	void initNetwork() {
		Random random = new Random(randomSeed);
		
		hiddenLayer.initSignals(random);
		outputLayer.initSignals(random);
	}
	
	double Forward(int patternNo) {
		BasedLayer<Double> thisPatterns = new LayerBase<Double>(patternPool[patternNo]);
		inputLayer.setInput(thisPatterns);
		outputLayer.setTarget(thisPatterns);
		
		hiddenLayer.Forward();
		outputLayer.Forward();
		
		Double[] outputErrors = outputLayer.getError().getD1().getBase();
		double accumulator = 0.0;
		for (Double error : outputErrors) { 
			accumulator += error;
		}
		return Math.sqrt(accumulator);
	}
	
	void Backward() {
		outputLayer.Backward(eta);
		hiddenLayer.Backward(eta);
	}
	
	double RunEpoch() {
		double err = 0.0;
		for (int p = 0; p < patternSize; p++) {
			err += Forward(p);
			Backward();
		}
		return err/patternSize;
	}

}
