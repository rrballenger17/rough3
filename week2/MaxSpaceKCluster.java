import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class MaxSpaceKCluster {

	public static int numNodes = -1;

	public static List<Edge> edges = new ArrayList<Edge>();

	//Map<Integer, Integer> clusters = new HashMap<Integer, Integer>();

	public static List<List<Integer>> clusterGroups = new ArrayList<List<Integer>>();

	public static Map<Integer, Integer> clusterIds = new HashMap<Integer, Integer>();


	public static void checkClusterIntegrity(){

		Set<Integer> usedIds = new HashSet<Integer>();

		for(List<Integer> cluster: clusterGroups){

			int id = -1;

			for(int i=0; i<cluster.size(); i++){

				int node = cluster.get(i);

				if(i == 0){
					id = clusterIds.get(node);
				}else{
					if(clusterIds.get(node) != id){
						System.out.println(cluster.toString());
						for(int x: cluster){
							System.out.println("node " + x + " id " + clusterIds.get(x));
						}
						System.out.println("ERROR INTEGRITY");
						System.exit(1);
					}
				}
			}

			if(usedIds.contains(id)){
						System.out.println("ERROR INTEGRITY 2");
						System.exit(1);

			}
			usedIds.add(id);

		}



	}



	public static void mergeClusters(Edge edge){

		//if(edge)


		List<Integer> groupOne = null;
		//int groupOneIndex = -1;

		List<Integer> groupTwo = null;
		//int groupTwoIndex = -1;

		// find groups with endpoints
		//int count = 0;
		for(List<Integer> group: clusterGroups){

			if(group.contains(edge.one) && group.contains(edge.two)){


				System.out.println("node " + edge.one + " id " + clusterIds.get(edge.one) );
				System.out.println("node " + edge.two + " id " + clusterIds.get(edge.two) );

				System.out.println("ERROR");
				System.exit(1);
			}

			if(group.contains(edge.one)){
				groupOne = group;
				//groupOneIndex = count;
			}else if(group.contains(edge.two)){
				groupTwo = group;
				//groupTwoIndex = count;
			}

			//count++;

		}

		// remove clusters
		clusterGroups.remove(groupOne);
		clusterGroups.remove(groupTwo);


		int newClusterId = clusterIds.get(edge.one);

		// add group two nodes to group one
		// reassign group two nodes cluster Ids
		for(int x: groupTwo){
			groupOne.add(x);
			clusterIds.put(x, newClusterId);
		}

		clusterGroups.add(groupOne);

		checkClusterIntegrity();


		System.out.println("merge complete");

	}



	public static void maxSpacingKClustering(){

		//Edge merge = null;

		while(clusterGroups.size() > 4){


			int minDist = Integer.MAX_VALUE;

			Edge toMerge = null;


			for(Edge edge : edges){

				int idOne = clusterIds.get(edge.one);

				int idTwo = clusterIds.get(edge.two);

				if( idOne != idTwo ){
					if(edge.cost < minDist){
						toMerge = edge;
						minDist = edge.cost;
					}
				}
			}

			mergeClusters(toMerge);

		}

	}


	public static void printMinDist(){
			int minDist = Integer.MAX_VALUE;

			Edge toMerge = null;


			for(Edge edge : edges){

				int one = clusterIds.get(edge.one);
				int two = clusterIds.get(edge.two);

				if(one != two){
					if(edge.cost < minDist){
						toMerge = edge;
						minDist = edge.cost;
					}
				}
			}

			System.out.println("Min distance: " + toMerge.cost);
	}




    public static void main (String[] args) {
		try {
			File file = new File("clustering1.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			int count = 0;
			while ((line = bufferedReader.readLine()) != null) {

				if(count++ == 0){
					numNodes = Integer.parseInt(line);
					continue;
				}

				String[] parts = line.split("\\s");

				int start = Integer.parseInt(parts[0]);
				int finish = Integer.parseInt(parts[1]);
				int weight = Integer.parseInt(parts[2]);

				Edge e = new Edge(start, finish, weight);

				edges.add(e);
			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		for(int i=1; i<=numNodes; i++){
			clusterIds.put(i, i);
			List<Integer> group = new ArrayList();
			group.add(i);
			clusterGroups.add(group);
		}


		maxSpacingKClustering();

				for(Edge e: edges){
			System.out.println(e.one + " " + e.two + " " + e.cost);
		}

		for(int key: clusterIds.keySet()){
			System.out.println(key + " " + clusterIds.get(key));
		}

		System.out.println("number of clusters: " + clusterGroups.size());


		printMinDist();
	}












}









