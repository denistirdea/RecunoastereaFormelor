package ro.usv.ro;

import java.util.*;

public class MainClass {

	static String[][] learningSet;
	static String[][] matrix;
	static String[] pattern = {"4" , "5"};
	static ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			
			System.out.println("W - Matrix: ");
			list=DistanceUtil.retunrNumberOfClasses(learningSet);
			Double[] avgValues = new Double[list.size()];
			matrix = DistanceUtil.return_W_Matrix(learningSet, list);
			DistanceUtil.ShowMatrix(matrix);

			System.out.println(System.lineSeparator() + "Discriminant function: ");
			for(int counter = 0; counter < matrix.length; counter++)
			{
				matrix[counter] =  DistanceUtil.calculateDiscriminantFunction(matrix[counter], pattern);
			}
			
			DistanceUtil.ShowMatrix(matrix);
			System.out.println(System.lineSeparator());
			for(int listSize = 0; listSize < list.size(); listSize++)
			{
				avgValues[listSize] = DistanceUtil.calculateAvg(matrix[listSize]);
				System.out.println("Clasa: " + list.get(listSize) + " are valoarea: " + avgValues[listSize]);
			}
			
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures)+ System.lineSeparator());
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
