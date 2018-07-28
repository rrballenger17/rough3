import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class ClusterHamming2 {

	public static int numNodes = 200000;



	// cluster-id
	public static Map<Integer, List<Integer>> clusterGroups = new HashMap<Integer, List<Integer>>();

	// node number, cluster-id
	public static Map<Integer, Integer> clusterIds = new HashMap<Integer, Integer>();


	public static Set<String> hammingStrings = new HashSet<String>();

	// hamming, node number
	public static Map<String, Integer> hammingNumbers = new HashMap<String, Integer>();



	// node numbers
	public static void mergeClusters(int one, int two){

		int groupOneId = clusterIds.get(one);

		int groupTwoId = clusterIds.get(two);

		if(groupOneId == groupTwoId){
			return;
		}



		List<Integer> groupOne = clusterGroups.get(groupOneId);

		List<Integer> groupTwo = clusterGroups.get(groupTwoId);


		clusterGroups.remove(groupOneId);

		clusterGroups.remove(groupTwoId);


		if(groupTwo.size() < groupOne.size()){
			List<Integer> temp = groupOne;
			groupOne = groupTwo;
			groupTwo = temp;

			int tempId = groupOneId;
			groupOneId = groupTwoId;
			groupTwoId = tempId;
		}



		// add group two nodes to group one
		// reassign group two nodes cluster Ids
		for(int x: groupOne){
			groupTwo.add(x);
			clusterIds.put(x, groupTwoId);
		}

		clusterGroups.put(groupTwoId, groupTwo);


		//System.out.println("merge");

	}








	public static void mergeWithNeighbors(String entry){




				// 1 neighbors
				for(int i=0; i<24; i++){

					StringBuilder builder = new StringBuilder(entry);

					char newChar = builder.charAt(2 * i) == '0' ? '1' : '0';

					builder.setCharAt(2 * i, newChar);

					String searchString = builder.toString();

					if(hammingStrings.contains(searchString)){



						mergeClusters(hammingNumbers.get(entry), hammingNumbers.get(searchString));
					}

					//System.out.println(searchString);
				}



				// 2 neighbors
				for(int i=0; i<24; i++){

					for(int j =i+1; j<24; j++){


						StringBuilder builder = new StringBuilder(entry);

						char newChar = builder.charAt(2 * i) == '0' ? '1' : '0';

						char newChar2 = builder.charAt(2 * j) == '0' ? '1' : '0';


						builder.setCharAt(2 * i, newChar);
						
						builder.setCharAt(2 * j, newChar2);



						String searchString = builder.toString();

						if(hammingStrings.contains(searchString)){

							//int groupONe

							mergeClusters(hammingNumbers.get(entry), hammingNumbers.get(searchString));
						}

					}

					//System.out.println(searchString);
				}




	}




	// hash set of hamming strings 
	// hash map, hamming string to node number 




    public static void main (String[] args) {
		try {
			File file = new File("clustering_big.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			int count = 0;
			while ((line = bufferedReader.readLine()) != null) {

				if(count == 0){
					//numNodes = Integer.parseInt(line);
					count++;
					continue;
				}

				//String[] parts = line.split("\\s");

			//	List<Integer> bits = new ArrayList<Integer>();

			//	for(String part: parts){
			//		bits.add( Integer.parseInt(part) );
			//	}
				hammingStrings.add(line);
				hammingNumbers.put(line, count);

			//	hammingEntries.add(bits);
				//Edge e = new Edge(start, finish, weight);
				//edges.add(e);
				count++;
			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		// for(int i=0; i< 200000; i++){

		// 	if(i % 100 == 0){
		// 		System.out.println("making edges " + i);
		// 	}

		// 	for(int j=i; j<200000; j++){

		// 		if(i == j){
		// 			continue;
		// 		}

		// 		int cost = 0;


		// 		List<Integer> hamOne = hammingEntries.get(i);

		// 		List<Integer> hamTwo = hammingEntries.get(j);

		// 		for(int x = 0; x < hamOne.size(); x++){


		// 			cost += Math.abs( hamOne.get(x) - hamTwo.get(x) );

		// 		}

		// 		Edge newEdge = new Edge(i+1, j+1, cost);

		// 		edges.add(newEdge);


		// 	}

		// }

		// System.out.println("finished edge calculations");



		for(String key: hammingNumbers.keySet()){

			int x = hammingNumbers.get(key);

			clusterIds.put(x, x);
			List<Integer> group = new ArrayList();
			group.add(x);
			clusterGroups.put(x, group);
		}


		int count = 0;

		for(String entry: hammingStrings){

			if(count % 1000 == 0){
				System.out.println("count " + count + " groups " + clusterGroups.size());
			}

			count++;

			mergeWithNeighbors( entry );
			//break;
		}



		System.out.println("groups " + clusterGroups.size());

	}

}
