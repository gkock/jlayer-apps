# Mandelbrot Set

This program creates an image that takes a while to compute 
because it takes some computation to compute the color of each pixel in the image. 
The image represents a part of the mathematical object known as the Mandelbrot set. 
The sole purpose of this example is to compare the looping implementation of a method layer 
with the parallel implementation using all available processor cores.

- The Unit class `MandelbrotUnit` provides the core of the program, 
  specifically the two methods offered for running in a loop and for running in parallel, respectively.
- Class `MandelbrotNet` establishes the global structure and adds the global dynamics.

Further details  can be found on the [JLayer Website](http://www.jlayer.org/example_Mandelbrot_Notes.html).
