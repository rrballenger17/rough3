import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class ClusterHamming2 {

	public static int numNodes = 200000;

	//public static List<Edge> edges = new ArrayList<Edge>();

	//Map<Integer, Integer> clusters = new HashMap<Integer, Integer>();

	public static List<List<Integer>> clusterGroups = new ArrayList<List<Integer>>();

	public static Map<Integer, Integer> clusterIds = new HashMap<Integer, Integer>();


	public static List<List<Integer>> hammingEntries = new ArrayList<List<Integer>>();







	public static void mergeClusters(int one, int two){

		//if(edge)


		List<Integer> groupOne = null;
		//int groupOneIndex = -1;

		List<Integer> groupTwo = null;
		//int groupTwoIndex = -1;

		// find groups with endpoints
		//int count = 0;
		for(List<Integer> group: clusterGroups){

			if(group.contains(one) && group.contains(two)){


				System.out.println("node " + one + " id " + clusterIds.get(one) );
				System.out.println("node " + two + " id " + clusterIds.get(two) );

				System.out.println("ERROR");
				System.exit(1);
			}

			if(group.contains(one)){
				groupOne = group;
				//groupOneIndex = count;
			}else if(group.contains(two)){
				groupTwo = group;
				//groupTwoIndex = count;
			}

			//count++;

		}

		// remove clusters
		clusterGroups.remove(groupOne);
		clusterGroups.remove(groupTwo);


		int newClusterId = clusterIds.get(one);

		// add group two nodes to group one
		// reassign group two nodes cluster Ids
		for(int x: groupTwo){
			groupOne.add(x);
			clusterIds.put(x, newClusterId);
		}

		clusterGroups.add(groupOne);

		//checkClusterIntegrity();


		System.out.println("merge complete");

	}

	public static int getEdgeCost(int i, int j){
		 		int cost = 0;


		 		List<Integer> hamOne = hammingEntries.get(i);

				List<Integer> hamTwo = hammingEntries.get(j);

		 		for(int x = 0; x < hamOne.size(); x++){


		 			cost += Math.abs( hamOne.get(x) - hamTwo.get(x) );

		 		}


		 		return cost;
	}








	public static void maxSpacingKClustering(){





		for(int i=1; i<200000; i++){

			int cost = getEdgeCost(1, i);

			if(cost <= 2){
				mergeClusters(0, i);
			}

		}














		// //Edge merge = null;

		// while(clusterGroups.size() > 4){


		// 	int minDist = Integer.MAX_VALUE;

		// 	//Edge toMerge = null;

		// 	int one = -1;
		// 	int two = -1;


		// 	//for(Edge edge : edges){

		// 	for(int i=0; i<numNodes; i++){

		// 		for(int j=i; j<numNodes; j++){

		// 			if (i == j){
		// 				continue;
		// 			}

		// 			int idOne = clusterIds.get(i);

		// 			int idTwo = clusterIds.get(j);

		// 			if( idOne != idTwo ){

		// 				int edgeCost = getEdgeCost(i, j);

		// 				if(edgeCost < minDist){

		// 					one = i;
		// 					two = j;
		// 					minDist = edgeCost;
		// 				}
		// 			}

		// 		}

		// 	}




		

		// 	mergeClusters(one, two);

		// 	System.out.println("clusters left " + clusterGroups.size());

		// }

	}




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

				String[] parts = line.split("\\s");

				List<Integer> bits = new ArrayList<Integer>();

				for(String part: parts){
					bits.add( Integer.parseInt(part) );
				}


				hammingEntries.add(bits);
				//Edge e = new Edge(start, finish, weight);
				//edges.add(e);

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



		for(int i=0; i<numNodes; i++){
			clusterIds.put(i, i);
			List<Integer> group = new ArrayList();
			group.add(i);
			clusterGroups.add(group);
		}


		maxSpacingKClustering();

		//for(Edge e: edges){
	//		System.out.println(e.one + " " + e.two + " " + e.cost);
	//	}

	//	for(int key: clusterIds.keySet()){
	//		System.out.println(key + " " + clusterIds.get(key));
	//	}

		//System.out.println("number of clusters: " + clusterGroups.size());


		//printMinDist();
	}

}
