import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Huffman {

	public static Set<Node> nodes = new HashSet<Node>();
	
	public static void printByGeneration(){


		Set<Node> generation = new HashSet<Node>();
		//Node firstNode = null;

		for(Node n: nodes){
			generation.add(n);
		}


		int gen = 0;
		while(generation.size() > 0){

			System.out.println("generation " + gen);

			Set<Node> nextGen = new HashSet<Node>();

			for(Node x: generation){



				if(x.left != null){
					nextGen.add(x.left);
				}

				if(x.right != null){
					nextGen.add(x.right);
				}

				if(x.left == null && x.right == null){
				System.out.println("node " + x.nodeWeight);

				}

			}

			generation = nextGen;
			gen++;

		}



	}


	public static void createHuffmanTree(){

		while(nodes.size() > 1){

			Node least = null;

			Node secondLeast = null;

			for(Node n: nodes){

				if(least == null){
					least = n;
					continue;
				}

				if(n.nodeWeight < least.nodeWeight){
					secondLeast = least;
					least = n;
					continue;
				}

				if(secondLeast == null){
					secondLeast = n;
					continue;
				}

				if(n.nodeWeight < secondLeast.nodeWeight){
					secondLeast = n;
					continue;
				}

			}


			nodes.remove(least);
			nodes.remove(secondLeast);

			Node newNode = new Node();
			newNode.left = secondLeast;
			newNode.right = least;
			newNode.nodeWeight = newNode.left.nodeWeight + newNode.right.nodeWeight;

			nodes.add(newNode);



		}

		for(Node n: nodes){
			System.out.println("all nodes    " + n.nodeWeight);

		}

		System.out.println("recorded sum " + total);


	}


	public static long total = 0;


    public static void main (String[] args) {
		try {
			File file = new File("huffman.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			int count = 0;
			while ((line = bufferedReader.readLine()) != null) {

				if(count++ == 0){
					//numNodes = Integer.parseInt(line);
					continue;
				}

				long weight = Long.parseLong(line);

				total += weight;

				Node n = new Node();

				n.letterWeight = weight;
				
				n.nodeWeight = weight;

				nodes.add(n);

			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		createHuffmanTree();

		printByGeneration();


	}












}









