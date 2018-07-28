import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Edge {

	public int one = -1;

	public int two = -1;

	public int cost = -1;

	public Edge(int start, int finish, int weight){
		this.one = start;
		this.two = finish;
		this.cost = weight;
	}

}

