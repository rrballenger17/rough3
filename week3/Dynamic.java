



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Dynamic {

	
	public static List<Long> weights = new ArrayList<Long>();

	public static List<Long> maxWeights = new ArrayList<Long>();

	public static List<String> maxSelection = new ArrayList<String>();



	public static void calculateMaxWeights(){

		maxWeights.add(weights.get(0));

		maxSelection.add("1");

		long secondMax = weights.get(0) > weights.get(1) ? weights.get(0) : weights.get(1);

		String secondMaxString = weights.get(0) > weights.get(1) ? "10" : "01";

		maxWeights.add(secondMax);

		maxSelection.add(secondMaxString);



		for(int i=2; i<weights.size(); i++){

			long maxOption1 = maxWeights.get(i - 1);

			long maxOption2 = weights.get(i)  + maxWeights.get(i - 2);


			if(maxOption1 >= maxOption2){

				// add 0 to recent string


				String selects = maxSelection.get(i-1);
				selects = selects + "0";
				maxSelection.add(selects);

				maxWeights.add(maxOption1);
			}else{

				// add 10 to old string
				String selects = maxSelection.get(i-2);
				selects = selects + "01";
				maxSelection.add(selects);
				maxWeights.add(maxOption2);
			}
		}

		System.out.println("max weight " + maxWeights.get(999));
		System.out.println(maxSelection.get(999));

		String maxs = maxSelection.get(999);

		String answer = "" + maxs.charAt(0) + maxs.charAt(1) +maxs.charAt(2) + maxs.charAt(3) 
			+maxs.charAt(16) + maxs.charAt(116) +maxs.charAt(516) + maxs.charAt(996);

		System.out.println(answer);	

	}



    public static void main (String[] args) {
		try {
			File file = new File("mwis.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			int count = 0;
			while ((line = bufferedReader.readLine()) != null) {

				if(count++ == 0){
					//numNodes = Long.parseInt(line);
					continue;
				}

				weights.add( Long.parseLong(line) );

			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		calculateMaxWeights();

		//createHuffmanTree();

		//printByGeneration();
	}












}









