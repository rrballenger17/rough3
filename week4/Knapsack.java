import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Knapsack {



	public static List<Integer> values = new ArrayList<Integer>();

	public static List<Integer> weights = new ArrayList<Integer>();



	// 10,000
	public static int capacity = -1;





	public static int[][] grid;


	public static void initializeGrid(){

		grid = new int[values.size() + 1][capacity+ 1];

		for(int i=0; i< capacity+ 1; i++){
			grid[0][i] = 0;
		}
	}



	public static void knapsackAlgorithm(){


		initializeGrid();


		for(int i=1; i<values.size() + 1; i++){


			for(int x=0; x<capacity + 1; x++){


				int option1 = grid[i-1][x];// A[i-1][x]

				int option2 = -1;


				int wi = weights.get(i-1);

				int vi = values.get(i-1);


				if(wi <= x){


					option2 = grid[i-1][x-wi] + vi;
				}

				int choice = option2 > option1 ? option2 : option1;

				grid[i][x] = choice;

				// A[i-1][x-wi] + vi
			}


		}


		System.out.println("max value " + grid[values.size()][capacity]);



	}




    public static void main (String[] args) {
		try {
			File file = new File("knapsack1.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			int count = 0;
			while ((line = bufferedReader.readLine()) != null) {

				if(count++ == 0){
					String[] parts = line.split("\\s");
					capacity = Integer.parseInt(parts[0]);

					continue;
				}

				String[] parts = line.split("\\s");

				// weight and then length
				int value = Integer.parseInt(parts[0]);
				int weight = Integer.parseInt(parts[1]);

				values.add(value);

				weights.add(weight);

			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		knapsackAlgorithm();


	}



}