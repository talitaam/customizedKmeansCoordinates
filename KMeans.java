import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import java.util.Random;

public class KMeans {

	List<Coordinate> data = new ArrayList<Coordinate>();
	List<Cluster> clusters = new ArrayList<Cluster>();
	Map<Cluster, List<Coordinate>> clusterCoordinates = new HashMap<Cluster, List<Coordinate>>();
	boolean stop = false;
	
	public static void main(String[] args) {
		
		int clusterQty = 2;
		boolean randomlySelectFirstCentroids = false;
		int maxRounds = 5;
		KMeans demo = new KMeans();
		demo.genereateCoordinate();
		demo.initiateClusterAndCentroid(clusterQty, randomlySelectFirstCentroids, maxRounds);
		demo.printCoordinateInformation();
		demo.printClusterInformation();
	}
	
	private void genereateCoordinate() {
		
		Coordinate coordinate = new Coordinate(1, 0, 5);
		data.add(coordinate);
		coordinate = new Coordinate(5, 10, 7);
		data.add(coordinate);
		coordinate = new Coordinate(2, 16, 18);
		data.add(coordinate);
		coordinate = new Coordinate(3, 12, 27);
		data.add(coordinate);
		coordinate = new Coordinate(4, 20, 30);
		data.add(coordinate);
		coordinate = new Coordinate(6, 13, 1);
		data.add(coordinate);
		coordinate = new Coordinate(7, 2, 18);
		data.add(coordinate);
		coordinate = new Coordinate(8, 25, 9);
		data.add(coordinate);
		coordinate = new Coordinate(9, 10, 3);
		data.add(coordinate);
		coordinate = new Coordinate(10, 1, 2);
		data.add(coordinate);
	}

	private void initiateClusterAndCentroid(int clusterQty, boolean randomlySelectFirstCentroids, int maxRounds) {
		
		System.out.println("\nNumber of Clusters: " + clusterQty);
		System.out.println("Number of Coordinates: " + data.size());
		
		if (data.size() <= clusterQty) {
			System.out.println("\nNumber of Clusters is smaller than number of Coordinates\n\nEnd of program");
			System.exit(1);
		}
		
		Coordinate coordinate = null;
		int counter = 1;
		int round = 0;
		Iterator<Coordinate> iterator = data.iterator();
		
		if (randomlySelectFirstCentroids == true) {
			System.out.println("\nRandomly Selecting First Centroids...");
			int min = 1;
			int max = data.size();
			Random random = new Random();
			int[] randomlySelectCoordinates = new int[clusterQty];
			int r = 0;
			boolean repetedElement = false;
			for(int i = 0; i < randomlySelectCoordinates.length; i++) {
				do {
					repetedElement = false;
					r = random.ints(min,(max+1)).findFirst().getAsInt();
					for (int element : randomlySelectCoordinates) {
					    if (element == r) {
					    	repetedElement = true;
					    }
					}
				} while (repetedElement == true && i > 0);
				randomlySelectCoordinates[i] = r;
			}
			System.out.println("\n******** Cluster Information - Round " + round + " ********");
			while(iterator.hasNext()) {
				coordinate = iterator.next();
				for (int element : randomlySelectCoordinates) {
				    if (element == coordinate.getId()) {
						System.out.println("Cluster " + counter + " - [id=" + coordinate.getId() + ", X=" + coordinate.getAxisX() + ", Y=" + coordinate.getAxisY() + ", cluster=" + coordinate.getClusterNum() + "]");
				    	coordinate.setClusterNum(counter);
						initializeCluster(counter, coordinate);
						counter++;
				    }
				}
			}
		}
		
		while (stop == false){
			stop = true;
			iterator = data.iterator();
			System.out.println("\n\n<<<<<<<<<<<<<<<<<<< Start Round " + round + " >>>>>>>>>>>>>>>>>>>");
			if (round > 0) {
				System.out.println("\n******** Cluster Information - Round " + round + " ********");
				for(Cluster cluster : clusters) {
					System.out.println(cluster);
				}
			}
			
			while(iterator.hasNext()) {
				coordinate = iterator.next();
				System.out.println("\nid " + coordinate.getId() + "\nstart coordinate: " + "[id=" + coordinate.getId() + ", X=" + coordinate.getAxisX() + ", Y=" + coordinate.getAxisY() + ", cluster=" + coordinate.getClusterNum() + "]");
				if(counter <= clusterQty && randomlySelectFirstCentroids == false) {
					coordinate.setClusterNum(counter);
					initializeCluster(counter, coordinate);
					counter++;
				}else {
					System.out.println("calculating disctance new coordinate... ");
					double minDistance = Integer.MAX_VALUE;
					boolean firstRound = true;
					Cluster whichCluster = null;
          
					for(Cluster cluster : clusters) {
						DecimalFormat formatador = new DecimalFormat("0.00");
						double distance = cluster.calculateDistance(coordinate);
						if (firstRound == true){
							minDistance = distance;
							whichCluster = cluster;
							firstRound = false;
						}
						System.out.println("Distance: " + formatador.format(distance) + " from Cluster " + cluster.getClusterNum());
						if(minDistance > distance) {
							minDistance = distance;
							whichCluster = cluster;
						}
					}
                
					if (whichCluster.getClusterNum() != coordinate.getClusterNum()) {
						stop = false;
						if (coordinate.getClusterNum() != 0) {
							System.out.println("--------------------->CHANGE CLUSTER<---------------------");
						}
					}
					coordinate.setClusterNum(whichCluster.getClusterNum());
					clusterCoordinates.get(whichCluster).add(coordinate);
					System.out.println("Shortest distance: Cluster " + whichCluster.getClusterNum());
				}
				
				System.out.println("end coordinate: " + "[id=" + coordinate.getId() + ", X=" + coordinate.getAxisX() + ", Y=" + coordinate.getAxisY() + ", cluster=" + coordinate.getClusterNum() + "]");
				if (clusterQty == counter-1 && randomlySelectFirstCentroids == false) {
					System.out.println("\n******** Cluster Information - Round " + round + " ********");
					for(Cluster cluster : clusters) {
						System.out.println(cluster);
					}
					counter++;
				}
			}
			round++;
			
			System.out.println("");
			printClusterInformation();
			
			if (stop == false && round < maxRounds) {
				System.out.println("\nTHERE WAS CHANGE OF CLUSTER. PROCEED WITH CENTROID CALCULATION AND RUN ANOTHER ROUND.");
			}
			else {
				if (maxRounds == round && stop == false) {
					System.out.println("\n\n\n------->>> MAXIMUM NUMBER OF ROUNDS REACHED <<<-------");
					stop = true;
				}
				else{
					System.out.println("\n\n\n------->>> THERE WAS NO CHANGE OF CLUSTER. NO MORE ROUNDS NEEDED <<<-------");
				}
			}
			
			System.out.println("\n\n<<<<<<<<<<<<<<<<<<< End Round " + round + " >>>>>>>>>>>>>>>>>>>\n");
			
			
			if (stop == false) {
				System.out.println("\n----- Calculating New Centroids -----\n");
				System.out.println("Average of current coordinates of each cluster:");
				for(Cluster cluster : clusters) {
					int count = 0;
					System.out.println("Cluster " + cluster.getClusterNum() + " - recalculating centroid");
					for(Coordinate coordinates : data) {
						if (cluster.getClusterNum() == coordinates.getClusterNum()) {
							if (count == 0){
								cluster.defineCentroid(coordinates);
							}
							else {
								cluster.updateCentroid(coordinates);
							}
							count++;
						}
					}
					cluster.calculateCentroid(count);
					clusterCoordinates.get(cluster).clear();
				}
				System.out.println("\n----- End Calculating New Centroids -----");
			}
		}
		
		System.out.println("\n********************* END OF ROUNDS *********************");
		System.out.println("\n\n");
		System.out.println("Total rounds: " + round + "\n");
	}

	private void initializeCluster(int clusterNum, Coordinate coordinate) {
		
		Cluster cluster = new Cluster(clusterNum,coordinate.getAxisX(),coordinate.getAxisY());
		clusters.add(cluster);
		List<Coordinate> clusterCoordinate = new ArrayList<Coordinate>();
		clusterCoordinate.add(coordinate);
		clusterCoordinates.put(cluster, clusterCoordinate);
	}

	private void printCoordinateInformation() {
		   System.out.println("****** Each Coordinate INFORMATION *********");
		   for(Coordinate coordinate : data) {
			   System.out.println("Coordinate [id=" + coordinate.getId() + ", X=" + coordinate.getAxisX() + ", Y=" + coordinate.getAxisY() + ", cluster=" + coordinate.getClusterNum() + "]");
		   }
	   }

	private void printClusterInformation() {
	   System.out.println("\n****** FINAL CLUSTER INFORMATION *********");
	   for (Map.Entry<Cluster, List<Coordinate>> entry : clusterCoordinates.entrySet())  {
        System.out.println("Key = " + entry.getKey() + 
                         ", Value = " + entry.getValue()); 
	   }
	}
}
