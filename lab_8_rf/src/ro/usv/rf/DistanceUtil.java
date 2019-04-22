package ro.usv.rf;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class DistanceUtil {

	static DecimalFormat df = new DecimalFormat("#.##");

	protected static void ShowDistances(String[][] minMatrix, int knn, String[][] evaluationSet, String[][] trainingSet)
	{
		TreeMap<String, Integer> tree = new TreeMap<String, Integer>();
		System.out.println();
		for(int col = 0; col < minMatrix[0].length; col++)
		{
			int x=0,y=0;
			for(int counter = 1; counter <= knn; counter++)
			{
				Double min = Double.MAX_VALUE;
				for(int row = 0; row < minMatrix.length; row++)
				{
					if(min >= Double.valueOf(minMatrix[row][col]))
					{
						min = Double.valueOf(minMatrix[row][col]);
						x= row;
						y=col;
					}
				}
				minMatrix[x][y] = String.valueOf(Double.MAX_VALUE);

				if(tree.get(trainingSet[x][4]) == null)
				{
					tree.put(trainingSet[x][4], 1);
				}
				else
				{
					int value = tree.get(trainingSet[x][4]).intValue();value++;
					tree.put(trainingSet[x][4], value);
				}
			}
			System.out.println("For  knn = "+ knn + " in column "+y +" in Min Matrix "+ tree);
			tree.clear();
		}
	}

	protected static String returnEuclidianDistance(String[] row1, String[] row2)
	{
		String distance;
		distance = String.valueOf(df.format(Math.abs(Math.sqrt(Math.pow(Double.valueOf(row1[0]) - Double.valueOf(row2[0]),2) + Math.pow(Double.valueOf(row1[1]) - Double.valueOf(row2[1]),2) + Math.pow(Double.valueOf(row1[2]) - Double.valueOf(row2[2]),2) +Math.pow(Double.valueOf(row1[3]) - Double.valueOf(row2[3]),2))))); 
		return distance;
	}

	protected static String[][] returnMinMatrix(String[][] trainingSet, String[][] evaluationSet)
	{
		String[][] matrix = new String[trainingSet.length][evaluationSet.length];

		for(int col = 0; col < evaluationSet.length; col++)
		{
			for(int row = 0; row < trainingSet.length; row++)
			{
				matrix[row][col] = returnEuclidianDistance(evaluationSet[col], trainingSet[row]);
			}

		}
		ShowMatrix(matrix);
		return matrix;
	}
	protected static String[][] buildTrainingSet(String[][] learningSet)
	{
		Random getRandomSets = new Random();
		int numberOfPatterns = (learningSet.length * 85) / 100;
		String[][] trainingSetMatrix = new String[numberOfPatterns][learningSet[0].length];

		for(int row = 0; row < numberOfPatterns; row++)
		{
			trainingSetMatrix[row] = learningSet[getRandomSets.nextInt(learningSet.length)];	
		}
		ShowMatrix(trainingSetMatrix);

		return trainingSetMatrix;
	}

	protected static String[][] buildEvaluationSet(String[][] learningSet, ArrayList<String> classList)
	{
		String[][] evaluationSet = new String[((learningSet.length * 15) / 100)][learningSet[0].length];
		Random getRandomSets = new Random();

		for(int row = 0; row < evaluationSet.length; row++)
		{
			evaluationSet[row] = learningSet[getRandomSets.nextInt(learningSet.length)];	
		}
		ShowMatrix(evaluationSet);

		return evaluationSet;
	}

	protected static String[] getRandomRow(String[][] learningSet, String key)
	{
		String[] value = null;
		Random getRandomNumber = new Random();
		ArrayList<Integer> list = new ArrayList<Integer>();

		for(int row = 0; row < learningSet.length; row++)
		{
			for(int col = 0; col < learningSet[0].length; col++)
			{
				if(learningSet[row][4].equals(key))
				{
					list.add(row);
				}	
			}
		}
		value = learningSet[list.get(getRandomNumber.nextInt(list.size()))];

		return value;
	}

	protected static ArrayList<String> GetDistinctClasses(String[][] learningSet)
	{
		ArrayList<String> classList = new ArrayList<String>();

		for(int row = 0; row < learningSet.length; row++)
		{
			if(!classList.contains(learningSet[row][4]))
			{
				classList.add(learningSet[row][4]);
			}
		}
		for(String s : classList)
			System.out.println(s);

		return classList;
	}

	protected static void ShowMatrix(String[][] matrix)
	{
		for(int row = 0; row < matrix.length; row++)
		{
			for(int col = 0; col < matrix[0].length; col++)
			{
				System.out.print(matrix[row][col]+ " ");
			}
			System.out.println();
		}
	}
}
