package org.jlayer.app;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.jlayer.model.*;
import org.jlayer.util.IndexTools;
import org.jlayer.util.LayerBase;

/**
 * tests LifeUnit.
 * @author Gerd Kock
 */
@Test(groups = {"suiteC"})
public class LifeTest {
	
	final int size = 10;
	LifeUnit[][] testArray;
	Layer_LifeUnit_ testLayer;
	
	@BeforeClass
    public void beforeClass() {
		testArray = new LifeUnit[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				testArray[i][j] = new LifeUnit();
				testArray[i][j].state = 1;
			}
		}
		testLayer = new Layer_LifeUnit_(testArray);
		testLayer.vector.connect(testLayer, IndexTools.isNeighbour);
	}
	
	private int valueAtIndex(int i, int j) {
		if ( i==0 && j==0 ) return 1;
		if ( i==0 && j==(size-1) ) return 1;
		if ( i==(size-1) && j==0 ) return 1;
		if ( i==(size-1) && j==(size-1) ) return 1;
		return 0;
	}
	
	@Test
	public void test() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				assertEquals(testLayer.get(i, j).state, 1);
			}
		}
		testLayer.nextState();
		testLayer.updateState();
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				assertEquals(testLayer.get(i, j).state, valueAtIndex(i, j));
			}
		}
	}
		
}
