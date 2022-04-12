package org.jlayer.app;

import java.util.Random;

import org.jlayer.model.Relation;
import org.jlayer.util.IndexTools;
import org.jlayer.util.ConnectUtil;

class LifeNetwork {
	
	int lines, columns, fieldSize, initSeed;
	boolean runFlag;
	
	LifeNetwork() {
		lines = 60;
		columns = 120;
		fieldSize = 5;
		initSeed = 27;
	}
	
	LifeUnit[][] unitArray = null;
	Layer_LifeUnit_ unitLayer = null;
	
	void print(){
    	System.out.println("CONTENT");
		for(int i = 0; i < unitLayer.length(); i++) {
			for(int j = 0; j < unitLayer.length(i); j++) {
				System.out.printf("%d ", unitLayer.state.get(i, j));
			}
			System.out.println();
		}    	
    }
	
	void createNetwork() {
		unitArray = new LifeUnit[lines][columns];
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < columns; j++) {
				unitArray[i][j] = new LifeUnit();
			}
		}    
		unitLayer = new Layer_LifeUnit_(unitArray);
		unitLayer.vector.connect(unitLayer, IndexTools.isNeighbour);
//		print();
	}
	
	void initNetwork() {
		Random r = new Random(initSeed);
		unitLayer.initState(r);
//		print();
	}
	
	void nextStep() {
		unitLayer.nextState();
		unitLayer.updateState();
//		print();
	}
	
	void stop() {
		runFlag = false;
	}

}
