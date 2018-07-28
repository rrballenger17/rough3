import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class MaxSpaceKCluster {

	int numNodes = -1;

	List<Edge> edges = new ArrayList<Edge>();

	//Map<Integer, Integer> clusters = new HashMap<Integer, Integer>();

	List<List<Integer>> clusterGroups = new ArrayList<List<Integer>>();

	Map<Integer, Integer> clusterIds = new HashMap<Integer, Integer>();


	public static void mergeClusters(Edge edge){


		List<Integer> groupOne = null;

		List<Integer> groupTwo = null;

		// find groups with endpoints
		for(List<Integer> group: clusterGroups){

			if(group.contains(edge.one)){
				groupOne = group;
			}else if(group.contains(edge.two)){
				groupTwo = group;
			}

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

	}



	public static void maxSpacingKClustering(){

		//Edge merge = null;

		while(clusters.size() > 4){


			int minDist = Integer.MAX_VALUE;

			Edge toMerge = null;


			for(Edge edge : edges){
				if(clusterIds.get(edge.one) != clusterIds.get(edge.two)){
					if(edge.cost < minDist){
						toMerge = edge;
						minDist = edge.cost;
					}
				}
			}

			mergeClusters(toMerge);

		}

	}




    public static void main (String[] args) {
		try {
			File file = new File("edges.txt");
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

				clusters.put(start, start);
				clusters.put(finish, finish);

				edges.add(e);
			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		maxSpacingKClustering();
	}












}









