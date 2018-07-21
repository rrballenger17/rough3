import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Schedule {

	public static List<Job> list = new ArrayList<Job>();

	public static long getCompletionTime(){

		long time = 0;

		long weightCTime = 0;

		for(Job job: list){
			time+= job.time;
			weightCTime += job.weight * time;
		}

		return weightCTime;

	}

	public static void checkDifferenceSort(){

		int prev = list.get(0).weight - list.get(0).time;
		int prevWeight = list.get(0).weight;

		for(Job j: list){




			int newDiff = j.weight - j.time;

			int newWeight = j.weight;


			if(newDiff > prev || (newDiff == prev && newWeight > prevWeight)){
				System.out.println("Error!");
				System.exit(1);
			}

			prev = newDiff;

			prevWeight = newWeight;


		}


	}


    public static void main (String[] args) {
		try {
			File file = new File("jobs.txt");
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

				// weight and then length
				int weight = Integer.parseInt(parts[0]);
				int length = Integer.parseInt(parts[1]);

				Job newJob = new Job(weight, length);

				list.add(newJob);

			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// #1 - sorts ascending order
		// decreasing order of the difference (weight - length)
		Collections.sort(list, new Comparator<Job>() {
  			public int compare(Job c1, Job c2) {
  				int c1val = c1.weight - c1.time;
  				int c2val = c2.weight - c2.time;
  				if(c1val == c2val ){
  					return c2.weight-c1.weight; 
  				}
  				return c2val - c1val;
  			}
  		});

		checkDifferenceSort();

  		// in decreasing order of the ratio (weight/length)
		Collections.sort(list, new Comparator<Job>() {
  			public int compare(Job c1, Job c2) {
  				
  				double c1w = c1.weight;
  				double c1t = c1.time;

  				double c2w = c2.weight;
  				double c2t = c2.time;

  				double c1val = c1w / c1t;

  				double c2val = c2w / c2t;

  				if(c1val > c2val){
  					return -1;
  				}else{
  					return 1;
  				}
  				
  			}
  		});




  		long completionTime = getCompletionTime();

  		System.out.println("List size: " + list.size());

  		System.out.println("Weighted completion time: " + completionTime);


	}





}
