import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Random;

public class Main {
    public static void main(String[] args)  {
        double[][] x = getX(4,90, "training");
        int[] yD = getYD(4, 100, "Iris-setosa","Iris-versicolor");

        double[][] xTest =getX(4,10, "test");
        Perceptron perceptron = new Perceptron(4,x,xTest,yD);
        perceptron.train();
        perceptron.evaluate();
    }

    static int[] getYD(int yIndex, int fileSize, String category1,String category2) {
        int[] yD = new int[fileSize];
        try(BufferedReader br = new BufferedReader(new FileReader("src/iris/training.txt"))){
            String contentLine = br.readLine();
            int i = 0;
            do {
                String[] line = contentLine.split((","));
                if(line[yIndex].equals(category1)) {
                    yD[i] = 1;
                } else if (line[yIndex].equals(category2)) {
                    yD[i] = -1;
                }
                i++;
            } while ((contentLine = br.readLine()) != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return yD;
    }
    
    static double[][] getX(int x, int fileSize, String fileName) {
        double[][] xN = new double[fileSize][x];
        try(BufferedReader br = new BufferedReader(new FileReader("src/iris/"+fileName+".txt"))){
            String contentLine = br.readLine();
            int i = 0;
            do {
                String[] line = contentLine.split((","));
                for (int j = 0; j < x; j++) {
                    xN[i][j] = Double.parseDouble(line[j]);
                }
                i++;
            } while ((contentLine = br.readLine()) != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xN;
    }
}
