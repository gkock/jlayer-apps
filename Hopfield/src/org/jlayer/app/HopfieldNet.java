package org.jlayer.app;

import org.jlayer.model.BasedLayer;
import org.jlayer.model.Relation;

public class HopfieldNet {
	
	Relation others = new Relation() {
		@Override
		public boolean contains(int[] x, int[] y) {
			return (x[0] != y[0]) | (x[1] != y[1]);
		}
	};
	
	int lines, cols;
	
	HopfieldUnit[][] 	hopArray;
	Layer_HopfieldUnit_ hopLayer;
	
	public void createNet(int lines, int cols) {
		this.lines = lines;
		this.cols = cols;
		hopArray = new HopfieldUnit[lines][cols];
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < cols; j++) {
				hopArray[i][j] = new HopfieldUnit();
			}
		}
		hopLayer = new Layer_HopfieldUnit_(hopArray);
		hopLayer.input.connect(hopLayer.activation, others);
		hopLayer.initWeights();
	}
	
	public void setActivations(int[][] pattern) {
		hopLayer.setActivation(pattern);
	}
	
	public void storePattern(int[][] pattern) {
		hopLayer.storePattern();
	}
	
	public boolean nextActivations() {
		hopLayer.nextActivation();
		BasedLayer<Boolean> isStable = hopLayer.isStable();
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < cols; j++) {
				if (!isStable.get(i,j)) return false;
			}
		}
		return true;
	}
	
	public BasedLayer<HopfieldUnit.Signal> getActivations() {
		return hopLayer.getActivation();
	}

}
