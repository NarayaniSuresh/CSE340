import java.util.Scanner;
import java.util.Random;

public class NeuralNetwork {

    private final int numLayers;
    private final int[] numNodes;
    private final double[][][] weights;
    private final Random random = new Random(); // For random weight initialization

    public NeuralNetwork(int numLayers, int[] numNodes) {
        this.numLayers = numLayers;
        this.numNodes = numNodes;
        this.weights = new double[numLayers - 1][][];
        for (int i = 0; i < numLayers - 1; i++) {
            weights[i] = new double[numNodes[i] + 1][numNodes[i + 1]];
            // Initialize weights with random values between -1 and 1
            for (int j = 0; j < numNodes[i] + 1; j++) {
                for (int k = 0; k < numNodes[i + 1]; k++) {
                    weights[i][j][k] = random.nextDouble() * 2 - 1;
                }
            }
        }
    }

    public void setWeight(int layer, int fromNode, int toNode, double weight) {

        if (layer < 1 || layer >= numLayers - 1 ||
                fromNode < 0 || fromNode >= numNodes[layer - 1] + 1 ||
                toNode < 0 || toNode >= numNodes[layer]) {
            throw new IllegalArgumentException("Invalid layer or node index.");
        }
        weights[layer - 1][fromNode][toNode] = weight;
    }

    public double getWeight(int layer, int fromNode, int toNode) {
      
        if (layer < 1 || layer >= numLayers - 1 ||
                fromNode < 0 || fromNode >= numNodes[layer - 1] + 1 ||
                toNode < 0 || toNode >= numNodes[layer]) {
            throw new IllegalArgumentException("Invalid layer or node index.");
        }
        return weights[layer - 1][fromNode][toNode];
    }

   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of layers: ");
        int numLayers = scanner.nextInt();

        int[] numNodes = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            System.out.print("Enter the number of nodes in layer " + (i + 1) + ": ");
            numNodes[i] = scanner.nextInt();
        }

        NeuralNetwork neuralNetwork = new NeuralNetwork(numLayers, numNodes);

        for (int i = 1; i < numLayers; i++) {
            for (int j = 0; j < numNodes[i - 1] + 1; j++) {
                for (int k = 0; k < numNodes[i]; k++) {
                    System.out.print("Enter weight from node " + j + " in layer " + i + " to node " + k + " in layer " + (i + 1) + ": ");
                    double weight = scanner.nextDouble();
                    neuralNetwork.setWeight(i, j, k, weight);
                }
            }
        }


        System.out.println("Weight from node 0 in layer 1 to node 0 in layer 2: " +
                neuralNetwork.getWeight(1, 0, 0));
    }
}