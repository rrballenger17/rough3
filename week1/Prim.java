import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Prim {

	public static List<Edge> list = new ArrayList<Edge>();

	public static Set<Integer> connected = new HashSet<Integer>();


	public static List<Edge> tree = new ArrayList<Edge>();

	public static void doPrim(){

		while(connected.size() < 500){


			Edge minEdge = null;


			for(Edge e: list){

				boolean firstTest = connected.contains(e.start) && !connected.contains(e.finish);

				boolean secondTest = connected.contains(e.finish) && !connected.contains(e.start);

				if(firstTest || secondTest){
					if(minEdge == null){
						minEdge = e;
					}else if(e.weight < minEdge.weight){
						minEdge = e;
					}
				}

			}

			connected.add(minEdge.finish);
			connected.add(minEdge.start);
			tree.add(minEdge);
		}
	}

	public static void printTreeSum(){

		long sum = 0;

		for(Edge e: tree){
			sum += e.weight;
		}

		System.out.println("Tree sum: " + sum);


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
					continue;
				}

				String[] parts = line.split("\\s");

				int start = Integer.parseInt(parts[0]);
				int finish = Integer.parseInt(parts[1]);
				int weight = Integer.parseInt(parts[2]);

				Edge e = new Edge(start, finish, weight);

				list.add(e);

			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		connected.add(1);

		doPrim();

		printTreeSum();



	}





}