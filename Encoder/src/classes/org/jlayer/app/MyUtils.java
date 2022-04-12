package org.jlayer.app;

import javax.swing.ImageIcon;

class MyUtils {
	
	/** Returns an ImageIcon, or null if the path was invalid. */
    static ImageIcon createNavigationIcon(String imageName) {
        String imgLocation = "images/"
                             + imageName
                             + ".gif";
        java.net.URL imageURL = MyUtils.class.getResource(imgLocation);

        if (imageURL == null) {
            System.err.println("Resource not found: "
                               + imgLocation);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
    }
    
	static class Signal {
		double val;
	}
	
	static double sigmoid(double x)
	{
	    return 1 / (1 + Math.exp(-x));
	}
	
	static double dotProd(Signal[] w, Signal[] x){
		if(w.length != x.length){
			throw new IllegalArgumentException("The dimensions have to be equal!");
		}
		double sum = 0;
		for(int i = 0; i < w.length; i++){
			sum += w[i].val * x[i].val;
		}
		return sum;
	}

}
