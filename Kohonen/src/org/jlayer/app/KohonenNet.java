package org.jlayer.app;

import java.util.Random;

import org.jlayer.model.Relation;

public class KohonenNet {
	
	Relation full = new Relation() {
		@Override
		public boolean contains(int[] x, int[] y) {
			return true;
		}
	};
	
	Random 	rgen;
	int 	seed = 4711;
	double 	lwb = 0.4, upb = 0.5;
	
	private DecisionUnit[] 		decisionUnit;
	private Layer_DecisionUnit_ decisionMaker;
	
	private KohonenUnit[][] 	kohArray;
	private Layer_KohonenUnit_ 	kohLayer;
	
	public void createNet(int width, int heigth) {
		// create decisionMaker
		decisionUnit 	= new DecisionUnit[1];
		decisionUnit[0]	= new DecisionUnit();
		decisionMaker 	= new Layer_DecisionUnit_(decisionUnit);
		
		// create Kohonen layer
		kohArray = new KohonenUnit[width][heigth];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < heigth; j++) {
				kohArray[i][j] = new KohonenUnit();
			}
		}
		kohLayer = new Layer_KohonenUnit_(kohArray);
		
		// establish connections
		decisionMaker.decisionIO.connect(kohLayer.decisionIO, full);
	}
	
	public void initNet() {
		rgen = new Random(seed++);
		kohLayer.initWeights(rgen, lwb, upb);
	}
	
	public void trainNet() {
		double x1 = rgen.nextDouble(0.0, 1.0);
		double x2 = rgen.nextDouble(0.0, 1.0);
		kohLayer.nextInputSample(x1, x2);
		decisionMaker.detWinnerIndex();
		kohLayer.setWinnerIndex();
		kohLayer.adaptWeights(x1, x2);
	}
	
	public double[] getWeights(int i, int j) {
		double[] weights = new double[2];
		weights[0] = kohArray[i][j].w[0];
		weights[1] = kohArray[i][j].w[1];
		return weights;
	}

}
