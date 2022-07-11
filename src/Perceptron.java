
import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;

public class Perceptron {
    private final double[] weights;
    private final double[][] xTrain;
    private final double[][] xTest;
    private final int[] yD;

    private final double learningRate = new Random().nextDouble();

    public Perceptron(int N,double[][] xTrain, double[][] xTest ,int[] yD) {
        weights = DoubleStream.generate(() -> new Random().nextDouble()).limit(N).toArray();
        this.xTrain = xTrain;
        this.xTest = xTest;
        this.yD = yD;
    }

    private double summation(double[] x){
        double summation = 0;
        for (int j = 0; j < this.weights.length; j++) {
            summation += this.weights[j] * x[j];
        }
        return summation;
    }

    private int activationFunction(double y){
        return y >= 0 ? 1 : -1;
    }

    public void train(){
        int counter = 0;
        double y;
        double error;
        while (counter<this.xTrain.length){
            y = summation(xTrain[counter]);

            if(y  >= 0){
                y = 1;
            }else{
                y = -1;
            }

            error = this.yD[counter] - y;

            if (error==0){
                counter += 1;
            }else {
                for (int i = 0; i < this.weights.length; i++) {
                    this.weights[i] += this.learningRate * error * this.xTrain[counter][i];
                }

                counter = 0;
            }
        }
        System.out.println("Final weights : "+Arrays.toString(this.weights));
    }

    public void evaluate(){
        for (double[] x:this.xTest){
            double y = summation(x);
            y = activationFunction(y);
            if(y == 1){
                System.out.println(Arrays.toString(x)+" : Iris-setosa");
            }else{
                System.out.println(Arrays.toString(x)+" : Iris-versicolor");
            }

        }
    }


}
