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
public class MandelbrotTest {
	
	MandelbrotNet mandelbrotNet;
	
	@BeforeClass
    public void beforeClass() {
		mandelbrotNet = new MandelbrotNet();
	}

	
	@Test(groups = "createNetwork")
	public void testCreateNetwork() {
		mandelbrotNet.createNetwork();
	}
	
	@Test(groups = "testSetRGB_LOOP", dependsOnGroups  = "createNetwork")
	public void testSetRGB_LOOP() {
		long startTime, stopTime;
        startTime = System.currentTimeMillis();
        System.out.println("setRGB_LOOP() starts");
		mandelbrotNet.setRGB_LOOP();
		stopTime = System.currentTimeMillis();
		System.out.println("Calling setRGB_LOOP() took " + (stopTime - startTime) + " ms");
	}
	
	@Test(groups = "testSetRGB_PARALLEL", dependsOnGroups  = "createNetwork")
	public void testSetRGB_PARALLEL() {
		long startTime, stopTime;
        startTime = System.currentTimeMillis();
        System.out.println("setRGB_PARALLEL() starts");
		mandelbrotNet.setRGB_PARALLEL();
		stopTime = System.currentTimeMillis();
		System.out.println("Calling setRGB_PARALLEL() took " + (stopTime - startTime) + " ms");
	}

}
