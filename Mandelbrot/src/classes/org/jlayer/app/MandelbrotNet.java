package org.jlayer.app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.jlayer.model.*;


public class MandelbrotNet {
	
	int width = 1600;
	int height = 1200;
	int maxIterations = 10000;
	
	MandelbrotUnit[][] mandelbrotArray = null;
	Layer_MandelbrotUnit_ mandelbrotLayer = null;
	
	BufferedImage mandelbrotImage = null;
	
	void createNetwork() {
		
		MandelbrotUnit.setGlobalParams(width, height, maxIterations);
		
		mandelbrotArray = new MandelbrotUnit[height][width];
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				mandelbrotArray[row][col] = new MandelbrotUnit();
			}
		}    
		mandelbrotLayer = new Layer_MandelbrotUnit_(mandelbrotArray);
		
		mandelbrotImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = mandelbrotImage.createGraphics(); // fill image with gray
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,width,height);
        g.dispose();

	}
	
	private void updateImage() {
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				mandelbrotImage.setRGB(col, row, mandelbrotLayer.rgbValue.get(row, col));
			}
		}   
	}
	
	void setRGB_LOOP() {
		mandelbrotLayer.setRgbValue_LOOP();
		updateImage();
	}
	
	void setRGB_PARALLEL() {
		mandelbrotLayer.setRgbValue_PARALLEL();
		updateImage();
	}

}
