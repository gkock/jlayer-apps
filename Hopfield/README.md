# Hopfield Network

In this example, a so-called **Hopfield Network** is implemented, an auto-associative network that consists of only one layer and can be used to store patterns. Here, a field layer with element type `int[]` is used as an automatically calculated **"index layer"** and a **self-defined relation** is used to link objects.

- Unit Class **HopfieldUnit** provides the core methods for saving and reconstructing patterns. 

- Class **HopfieldNet** establishes the global structure and adds the global dynamics. 

Further details  can be found on the [JLayer Website](http://www.jlayer.org/example_Hopfield_Notes.html).
