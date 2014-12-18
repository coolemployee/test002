package com.implemica.task.antsyferov;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Anton Antsyferov
 * 
 */
public class Test002 {

    static final int MAX_COST = 200001;

    /**
     * @param args
     *            args[0] input filename
     */
    public static void main(String[] args) {

	// simply reading input data, data check is not implemented
	try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

	    int numberOfTests = Integer.parseInt(br.readLine());

	    while (numberOfTests > 0) {
		// reading number of cities
		int numberOfCities = Integer.parseInt(br.readLine());
		// map for storing city names (key: name of city, value: index
		// of city)
		Map<String, Integer> cityNames = new HashMap<String, Integer>(
			numberOfCities);
		int[][] incidenceMatrix = new int[numberOfCities][numberOfCities];
		// initializing incidence matrix
		for (int i = 0; i < numberOfCities; i++) {
		    Arrays.fill(incidenceMatrix[i], MAX_COST);
		}

		// reading connections between cities and filling matrix of
		// incidence
		for (int currentCity = 0; currentCity < numberOfCities; currentCity++) {
		    cityNames.put(br.readLine(), currentCity);
		    int numberOfNeighboors = Integer.parseInt(br.readLine());
		    for (int neighboor = 0; neighboor < numberOfNeighboors; neighboor++) {
			Scanner scanner = new Scanner(br.readLine());
			// city indexation in input data begins from 1, fixing
			int currentNeighboor = scanner.nextInt() - 1;
			int currentCost = scanner.nextInt();
			scanner.close();
			incidenceMatrix[currentCity][currentNeighboor] = currentCost;

		    }
		}

		int numberOfPaths = Integer.parseInt(br.readLine());

		while (numberOfPaths > 0) {
		    Scanner scanner = new Scanner(br.readLine());
		    String cityFrom = scanner.next();
		    String cityTo = scanner.next();
		    scanner.close();

		    System.out.println(getMinimalDistance(
			    cityNames.get(cityFrom), cityNames.get(cityTo),
			    incidenceMatrix, numberOfCities));
		    numberOfPaths--;
		}

		numberOfTests--;
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    /**
     * Using standard Dijkstra’s algorithm
     * 
     * @param startNode
     *            start node for way calculating
     * @param endNode
     *            end node for way calculating
     * @param incidenceMatrix
     *            incidence matrix
     * @param n
     *            total number of nodes
     * @return minimal distance between startNode and endNode
     */
    static int getMinimalDistance(int startNode, int endNode,
	    int[][] incidenceMatrix, int n) {
	// array of minimal costs from node startNode
	int[] cost = new int[n];
	// set for storing nodes to be processed
	Set<Integer> unprocessedNodes = new HashSet<Integer>(n);

	// initializing
	for (int i = 0; i < n; i++) {
	    cost[i] = incidenceMatrix[startNode][i];
	    unprocessedNodes.add(i);
	}

	// removing startNode
	cost[startNode] = 0;
	unprocessedNodes.remove(startNode);
	// processing all nodes
	while (!unprocessedNodes.isEmpty()) {
	    int currentMinimalCost = MAX_COST;
	    int currentMinimalNode = 0;
	    // searching for minimal distance from our node to other unprocessed
	    // nodes
	    for (int i = 0; i < n; i++) {
		if (unprocessedNodes.contains(i)) {
		    currentMinimalCost = Math.min(currentMinimalCost, cost[i]);
		    // if cost[i] is min, then storing index i as
		    // currentMinimalNode value
		    if (currentMinimalCost == cost[i]) {
			currentMinimalNode = i;
		    }
		}

	    }
	    // found currentMinimalNode - number of node with minimal cost
	    unprocessedNodes.remove(currentMinimalNode);
	    // updating cost array with found currentMinimalNode
	    for (int i = 0; i < n; i++) {
		if (unprocessedNodes.contains(i)) {
		    cost[i] = Math.min(cost[i], cost[currentMinimalNode]
			    + incidenceMatrix[currentMinimalNode][i]);
		}
	    }
	}

	return cost[endNode];

    }

}
