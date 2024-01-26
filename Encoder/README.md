# A Backpropagation Neural Network

This example implements a simple backpropagation neural network with one hidden layer, which can be used to find an encoding for a number of given patterns. It illustrates the application of the JLayer annotation `@LayerParam` and shows how a mathematical relation over indices is used to associate layers.

- The unit classes for modelling the local core functionality are `Input`, `Hidden` and `Output`.
- Class `EncoderNetwork` establishes the global structure and adds the global dynamics. 

Further details  can be found on the [JLayer Website](http://www.jlayer.org/example_Encoder_Notes.html).
