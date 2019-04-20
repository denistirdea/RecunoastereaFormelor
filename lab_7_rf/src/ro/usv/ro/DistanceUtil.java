package ro.usv.ro;

import java.util.ArrayList;

public class DistanceUtil {

	protected static ArrayList<String> retunrNumberOfClasses(String [][] learningSets) {
		ArrayList<String> list = new ArrayList<String>();

		for(int row = 0; row < learningSets.length; row++)
		{
			if(!list.contains(learningSets[row][2]))
			{
				list.add(learningSets[row][2]); 
			}
		}
		return list;
	}

	protected static String[][] return_W_Matrix(String[][] learningSet, ArrayList<String> list)
	{
		String[][] w_matrix = new String[list.size()][learningSet.length+1];

		for(int counter=  0; counter < list.size(); counter++)
		{
			w_matrix[counter] = DistanceUtil.returnRowInMatrix(learningSet, list.get(counter));
		}

		for(int counter = 0; counter < w_matrix.length; counter++)
		{
			w_matrix[counter][learningSet.length] = String.valueOf(DistanceUtil.calculateAvgForWMatrix(w_matrix[counter]));
		}
		
		return w_matrix;
	}

	protected static Double calculateAvgForWMatrix(String[] row)
	{
		Double temp = 0.0;
		for(int counter = 0; counter < row.length; counter++)
		{
			if(row[counter] == null)
				row[counter] ="0";
			temp += Math.pow(Double.valueOf(row[counter]),2)*-1/2;

		}
		return temp;
	}
	
	protected static Double calculateAvg(String[] row)
	{
		Double temp = 0.0;
		for(int counter = 0; counter < row.length; counter++)
		{
			if(row[counter] == null)
				row[counter] ="0";
			temp += Double.valueOf(row[counter]);

		}
		return temp;
		
	}
	
	protected static void ShowMatrix(String[][] Matrix)
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

	protected static String[] returnRowInMatrix(String[][] learningSet, String key)
	{
		String[] rows = new String[learningSet.length+1];

		for(int row = 0; row < learningSet.length; row++) {
			if(learningSet[row][2].equals(key))
			{
				rows[row] = String.valueOf(  (Double.valueOf(learningSet[row][0]) + Double.valueOf(learningSet[row][1]))/2   ); 
			}
		}
		return rows;
	}
	
	protected static String[] calculateDiscriminantFunction(String[] w_row, String[] row)
	{
		String[] rows = new String[w_row.length];
		
		for(int col = 0; col<row.length;col++)
		{
			rows[col] = String.valueOf(Double.valueOf(w_row[col]) * Double.valueOf(row[col]));
		}
		
		for(int counter = row.length; counter < w_row.length; counter++)
		{
			rows[counter] = w_row[counter];
		
		}

		return rows;
	}

}
