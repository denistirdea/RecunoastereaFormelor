package ro.usv.rf;

import java.util.*;

public class MainClass {

	static String[][] learningSet;
	static String[][] trainingSetMatrix;
	static String[][] evaluationSetMatrix;
	static String[][] minMatrix;
	static ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			learningSet = FileUtils.readLearningSetFromFile("iris.csv");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			
			trainingSetMatrix = DistanceUtil.buildTrainingSet(learningSet); // getting training set
			list = DistanceUtil.GetDistinctClasses(trainingSetMatrix); // getting list with classes
			evaluationSetMatrix = DistanceUtil.buildEvaluationSet(learningSet, list); // getting evaluation set 
			minMatrix = DistanceUtil.returnMinMatrix(trainingSetMatrix, evaluationSetMatrix); //min matrix 
			
			DistanceUtil.ShowDistances(minMatrix, 15, evaluationSetMatrix, trainingSetMatrix);// showing results
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures)+ System.lineSeparator());
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
