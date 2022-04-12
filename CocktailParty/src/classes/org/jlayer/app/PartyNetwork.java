package org.jlayer.app;

import java.util.Random;



import org.jlayer.util.IndexTools;

public class PartyNetwork {
	
	PartyNetwork() {
		fieldSize = 25;
		lines = 30;
		columns = 50;
		personCnt = 180;
		chosenPerson = 27;
		initSeed = 4711;
		isInitialized = false;
		strategy = Decider.Strategy.A_Me_B;
	}
	
	int fieldSize, lines, columns, personCnt, chosenPerson, initSeed;
	boolean isInitialized;
	boolean runFlag;
	Decider.Strategy strategy;
	
	Decider		 decider;
	Position[][] floorArray;
	Person[]     personArray;
	
	Layer_Position_ floorLayer;
	Layer_Person_ personLayer;
	
	void createNetwork() {
		// create the decider
		decider = new Decider(lines, columns);
		
		// create the floor
		floorArray = new Position[lines][columns];
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				floorArray[i][j] = new Position();
			}
		}
		floorLayer = new Layer_Position_(floorArray);
		floorLayer.neighbours.connect(floorLayer, IndexTools.isNeighbour);
		
		// create the persons
		personArray = new Person[personCnt];
		for (int i = 0; i < personCnt; i++) {
			personArray[i] = new Person(String.valueOf(i), decider);
		}
		personLayer = new Layer_Person_(personArray);
		
		// set isInitialized
		isInitialized = false;
	}
	
	void initNetwork() {
		Random random = new Random(initSeed);
		personLayer.initPos(floorArray, random);
		personLayer.initPersonAandB(personArray, random);
		isInitialized = true;
	}
	
	void moveMe() {
		personLayer.moveMe(strategy);
	}

	void stop() {
		runFlag = false;
	}

}
