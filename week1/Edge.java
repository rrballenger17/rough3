import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Edge {

	public int start = -1;

	public int finish = -1;

	public int weight = -1;

	public Edge(int start, int finish, int weight){
		this.start = start;
		this.finish = finish;
		this.weight = weight;
	}

}

