package ro.usv.rf;

import java.util.*;

public class MainClass {
	//static int KNN_rule = 5;
	static double[] minDistances = null;
	static String[][] learningSet;
	static double[][] minMatrix;
	static Double[] note = {3.8, 5.75, 6.25, 7.25, 8.5};
	static ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			learningSet = FileUtils.readLearningSetFromFile("gradesClasses.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			minMatrix = new double[numberOfPatterns][note.length];

			for(int KNN_rule = 1; KNN_rule <= 10; KNN_rule+=2)
			{
				minMatrix = DistanceUtil.returnMinMatrix(learningSet, note);
				list = DistanceUtil.calculateMinDistances(minMatrix, KNN_rule, learningSet, note);
				for(String s : list)
					System.out.println(s);
				list.clear();
				System.out.println();
			}
			
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures)+ System.lineSeparator());
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
