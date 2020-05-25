import java.util.Arrays;

public class Perceptron {
    private int numInputs;
    private float[] weights;
    private float THRESHOLD = 0;

    private String classifyForLabel; // this is a classifier for eg "virginica"
    private float learningRate = 0.005f;

    public Perceptron(int numInputs, String whatToClassify) {
        this.classifyForLabel = whatToClassify;
        this.numInputs = numInputs;
        weights = initWeights(numInputs);
    }

    private float[] initWeights(int numInputs) {
        float[] w = new float[numInputs];

        for (int i = 0; i < w.length; i++) {
            w[i] = (float)(2*Math.random()-1);
        }

        return w;
    }

    /***
     * Train the perceptron using the input feature vector and its correct label.
     * Return true if there was a non-zero error and training occured (weights got adjusted)
     *
     * @param input
     * @param correctLabel
     * @return
     */
    public boolean train(float[] input, String correctLabel) {
        int guess = guess(input);

        if (!isGuessCorrect(guess, correctLabel)) {
            float error = guess - getCorrectGuess(correctLabel);

            for (int i = 0; i < weights.length; i++) {
                weights[i] = weights[i] - error * input[i] * learningRate;
            }

            THRESHOLD = THRESHOLD + error * learningRate;
            return true;
        }

        return false;
    }

    public int guess(float[] input) {
        double sum = 0;

        for (int i = 0; i < input.length; i++) {
            sum += input[i] * weights[i];
        }

        return activationFunction(sum);
    }

    private int activationFunction(double sum) {
        if (sum > THRESHOLD) {
            return 1;
        } else {
            return 0;
        }
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