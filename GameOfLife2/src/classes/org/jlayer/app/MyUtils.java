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

}
