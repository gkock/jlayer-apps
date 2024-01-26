# Kohonen Network

The **Kohonen network** is a self-organising map that is trained by unsupervised competitive learning. The aim is to generate a low-dimensional (typically two-dimensional) representation of a higher-dimensional data set. A field layer with element type `int[]` is used as an automatically calculated **"index layer"** and a **self-defined relation** is used to link objects.

- The unit classes for modelling the local core functionality are **KohonenUnit** and **DecisionUnit**. 

- Class **KohonenNet** establishes the global structure and adds the global dynamics. 

Further details  can be found on the [JLayer Website](http://www.jlayer.org/example_Kohonen_Notes.html).
