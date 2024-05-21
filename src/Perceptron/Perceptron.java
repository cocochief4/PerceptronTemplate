package Perceptron;

import java.util.Arrays;

public class Perceptron {
    private int numInputs;
    private float[] weights;
    private float THRESHOLD = 0;

    private String classifyForLabel; // this is a classifier for eg "virginica"
    private float learningRate = 0.001f;

    public Perceptron(int numInputs, String whatToClassify) {
        this.classifyForLabel = whatToClassify;
        this.numInputs = numInputs;
        weights = initWeights(numInputs);
    }

    /***
     * Create and return a float array with randomly initialized weights from [-1 to 1]
     * @param numInputs the number of weights needed (length of the array to create)
     * @return the initialized weights array
     */
    private float[] initWeights(int numInputs) {
        float[] weights = new float[numInputs];
        for (int i = 0; i < numInputs; i++) {
            weights[i] = (float) Math.random() * 2 - 1;
        }
        return weights;
    }

    /***
     * Run the perceptron on the input and return 0 or 1 for the output category
     * @param input input vector
     * @return 0 or 1 representing the possible output categories or -1 if there's an error
     */
    public int guess(float[] input) {
        // TODO:  Implement this.
        // Do a linear combination of the inputs multiplied by the weights.
        // Run the sum through the activiationFunction and return the result
        float sum = 0;
        
        for (int i = 0; i < input.length; i++) {
            sum += input[i] * weights[i];
        }
        
        return activationFunction(sum);
        
    }

    private int activationFunction(float sum) {
        if (sum > THRESHOLD) {
            return 1;
        } else {
            return 0;
        }
    }

    /***
     * Train the perceptron using the input feature vector and its correct label.
     *
     * @param input
     * @param correctLabel
     * @return true if there was a non-zero error and training occured (weights got adjusted)
     */
    public boolean train(float[] input, String correctLabel) {
        // run the perceptron on the input
        int g = guess(input);
        int correctGuess = getCorrectGuess(correctLabel);
        int e = g - correctGuess; // Sign tells us if over/under guessed
        
        // compare the guess with the correct label (can use already-made helper method for this).

        // If guess was incorrect
        //    update weights and THRESHOLD using learning rule
        for (int i = 0; i < input.length; i++) {
            weights[i] -= learningRate * e * input[i];
        }

        THRESHOLD += learningRate * e;

        return e != 0;
    }

    public float[] getWeights() {
        return weights;
    }

    public String getTargetLabel() {
        return this.classifyForLabel;
    }

    public boolean isGuessCorrect(int guess, String correctLabel) {
        return guess == getCorrectGuess(correctLabel);
    }

    /***
     * Return the correct output for a given class label.  ie returns 1 if input label matches
     * what this perceptron is trying to classify.
     * @param label
     * @return
     */
    public int getCorrectGuess(String label) {
        if (label.equals(this.classifyForLabel))
            return 1;
        else
            return 0;
    }

    public float getThreshold() {
        return THRESHOLD;
    }
}