package ro.usv.rf;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.TreeMap;

public class DistanceUtil {

	static DecimalFormat df = new DecimalFormat("#.##");

	protected static double[][] returnMinMatrix(String[][] learningSets, Double[] note)
	{
		double[][] minValue = new double[learningSets.length][note.length];

		for(int colCounter = 0; colCounter < note.length; colCounter++)
		{
			for(int rowCounter = 0; rowCounter < learningSets.length; rowCounter++)
			{
				minValue[rowCounter][colCounter] =Math.abs(Double.valueOf(learningSets[rowCounter][0])-note[colCounter]);
			}	
		}
		return minValue;
	}

	protected static ArrayList<String> calculateMinDistances(double[][] minValue, int KNN_rule, String[][] learningSet, Double[] note)
	{
		ArrayList<String> list = new ArrayList<String>();
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		String key = null;
		String comparator="";
		Double min = Double.MAX_VALUE;
		int x;

		for(int noteCounter = 0; noteCounter < note.length; noteCounter++)
		{
			for(int counter = 0; counter < KNN_rule; counter++)
			{
				x=0;

				for(int minValueCounter = 0; minValueCounter<minValue.length;minValueCounter++)
				{
					if(minValue[minValueCounter][noteCounter] <= min)
					{   
						min = minValue[minValueCounter][noteCounter];	
						key = learningSet[minValueCounter][1];
						x = minValueCounter;
					}
				}

				if(comparator == "")
				{
					comparator = key;
				}

				minValue[x][noteCounter] = Double.MAX_VALUE;
				min = Double.MAX_VALUE;

				if(map.get(key) == null)
				{
					map.put(key, 1);
				}
				else
				{
					int value = map.get(key).intValue();
					value++;
					map.put(key, value);
				}
			}

			int maxValueInMap=(Collections.max(map.values())); 

			for (Entry<String, Integer> entry : map.entrySet()) 
			{  
				if (entry.getValue()==maxValueInMap) {
					if(!comparator.equals(entry.getKey()))
						list.add("Note: "+ note[noteCounter] +" has " + map + " with '"+ entry.getKey() + "' predominant and KNN inaccurate");
					else 
						list.add("Note: "+ note[noteCounter] +" has " + map + " with '"+ entry.getKey() + "' predominant and KNN accurate");
				}
			}
			map.clear();
			comparator = "";
		}
		return list;
	}

	protected static void ShowMatrix(double[][] Matrix)
	{
		for(int col = 0; col<Matrix.length; col++) 
		{ 
			for(int row = 0;row< Matrix[0].length; row++) 
			{ 
				System.out.print(df.format(Matrix[col][row])+ " "); 
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
