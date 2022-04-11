package org.jlayer.app;

import java.util.Random;

import org.jlayer.util.IndexTools;

public class LifeUniverse {
	
	private LifeCell[][] lifeArray;
	private Layer_LifeCell_ lifeLayer;
	
	public void createNet(int width, int heigth) {
		lifeArray = new LifeCell[width][heigth];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < heigth; j++) {
				lifeArray[i][j] = new LifeCell();
			}
		}
		lifeLayer = new Layer_LifeCell_(lifeArray);
		lifeLayer.vector.connect(lifeLayer, IndexTools.isNeighbour);
	}
	
	public void initNet(int initSeed) {
		Random r = new Random(initSeed);
		lifeLayer.initState(r);
	}
	
	public void updateNet() {
		lifeLayer.nextState();
		lifeLayer.updateState();
	}
	
	public int getState(int i, int j) {
		return lifeArray[i][j].state;
	}

}
