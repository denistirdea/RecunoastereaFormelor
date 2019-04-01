package ro.usv.rf;

public class DistanceUtil {

	protected static double returnEuclidianDistance(String[] row1, double[] row2)
	{
		double distance = 1;
		distance = Math.sqrt(Math.pow(Double.valueOf(row1[0]) - Double.valueOf(row2[0]), 2) + Math.pow(Double.valueOf(row1[1]) - Double.valueOf(row2[1]), 2));		
		return distance;
	}

	protected static void calculateMinDistances(double[][] distanceMatrix, int KNN_rule, String[][] learningSet, double[][] coordinates)
	{
		double[][] minDistances = new double[KNN_rule][distanceMatrix[0].length];
		int x=0, y=0;

		for(int col = 0; col < distanceMatrix[0].length; col++) {
			for(int count = 0; count < KNN_rule; count++)
			{
				double minim = Integer.MAX_VALUE;
				for(int row = 0; row<distanceMatrix.length;row++)
				{
					if(distanceMatrix[row][col] <= minim)
					{
						minim = distanceMatrix[row][col];	
						x=row; y=col;
					}
				}
				distanceMatrix[x][y] = Integer.MAX_VALUE;
				minDistances[count][col] = minim;
				System.out.println("The coordinates: [" + coordinates[col][0]+" "+ coordinates[col][1]+ "] are closer to the following city :" + learningSet[x][3]);
			}
			System.out.println();
		}
		System.out.println(System.lineSeparator()+"Euclidian minimum distances matrix kNN-rule: " + KNN_rule);
		ShowMatrix(minDistances);
	}

	protected static void ShowMatrix(double[][] Matrix)
	{
		for(int col = 0; col<Matrix.length; col++) 
		{ 
			for(int row = 0;row< Matrix[0].length; row++) 
			{ 
				System.out.print(Matrix[col][row]+ " "); 
			} 
			System.out.println();
		}
	}

	protected static boolean exists(double[] colDistance)
	{
		boolean statement = false;
		for(int first = 0; first < colDistance.length; first++) 
		{
			for(int second = 1; second < colDistance.length; second++)
			{
				if(colDistance[first] == colDistance[second])
					return true;
			}
		}
		return statement;
	}
}
