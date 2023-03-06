package org.jlayer.app;

import java.util.Random;

import org.jlayer.model.Relation;

public class KohNet {
	
	Relation full = new Relation() {
		@Override
		public boolean contains(int[] x, int[] y) {
			return true;
		}
	};
	
	private DecisionUnit[] decisionArray;
	private Layer_DecisionUnit_ decisionMaker;
	
	private KohUnit[][] kohArray;
	private Layer_KohUnit_ kohLayer;
	
	public void createNet(int width, int heigth) {
		// create decisionMaker
		decisionArray 		= new DecisionUnit[1];
		decisionArray[0]	= new DecisionUnit();
		decisionMaker 		= new Layer_DecisionUnit_(decisionArray);
		
		// create kohonenArray
		kohArray = new KohUnit[width][heigth];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < heigth; j++) {
				kohArray[i][j] = new KohUnit();
			}
		}
		kohLayer = new Layer_KohUnit_(kohArray);
		
		// establish connections
		decisionMaker.signals.connect(kohLayer.signals, full);
	}
	
	public void initNet(int initSeed) {
		Random r = new Random(initSeed);
		kohLayer.initWeights(r);
	}
	
	public void updateNet(double x1, double x2) {
		kohLayer.nextInput(x1, x2);
		decisionMaker.detWinner();
		kohLayer.setWinnerIndex();
		kohLayer.adaptWeights();
	}
	
	public double[] getWeights(int i, int j) {
		double[] weights = new double[2];
		weights[0] = kohArray[i][j].w1;
		weights[1] = kohArray[i][j].w2;
		return weights;
	}

}
