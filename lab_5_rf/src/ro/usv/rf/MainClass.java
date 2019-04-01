package ro.usv.rf;

public class MainClass {
	static int KNN_rule = 50;
	static double[] minDistances = null;
	static double[][] distanceMatrix = null;
	static double[][] coordinates = {   
			{25.89, 47.56},
			{24, 45.15},
			{25.33, 45.44}
	};

	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("data.csv");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			distanceMatrix = new double[numberOfPatterns][coordinates.length];

			for(int row = 0; row<numberOfPatterns; row++) 
			{ 
				for(int col = 0;col<coordinates.length; col++) 
				{ 
					distanceMatrix[row][col] = DistanceUtil.returnEuclidianDistance(learningSet[row], coordinates[col]);  
				}
			}
			DistanceUtil.ShowMatrix(distanceMatrix);
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures)+ System.lineSeparator());
			DistanceUtil.calculateMinDistances(distanceMatrix, KNN_rule, learningSet, coordinates);
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
