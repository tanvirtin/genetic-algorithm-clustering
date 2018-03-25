package com.tin.algorithms.gaclustering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GeneticAlgorithmController {
	
	private static GeneticAlgorithmController instance = null;
	
	private GeneticAlgorithmController() {
		
	}
	
	static GeneticAlgorithmController getInstance() {
		if (instance == null) {
			instance = new GeneticAlgorithmController();
		}
		return instance;
	}
	
	public ArrayList<Data> loadData() throws FileNotFoundException {
		String path = "./data/data-1.txt";
		
		ArrayList<Data> users = new ArrayList<Data>();
		
		File file = new File(path);
		
		Scanner scanner = new Scanner(file);
		
		int numUsers = scanner.nextInt();
		int numFeatures = scanner.nextInt();
		
		for (int i = 0; i < numUsers; ++i) {
			String name = scanner.next();
			Data newUser = new Data(name, numFeatures);
			for (int j = 0; j < numFeatures; ++j) {
				newUser.setFeature(j, scanner.nextDouble());
			}
			users.add(newUser);
			System.out.println(newUser);
		}
		// close the scanner
		scanner.close();
		
		return users;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		GeneticAlgorithmController gac = GeneticAlgorithmController.getInstance();
		
		ArrayList<Data> users = gac.loadData();
		
		DataClassifier userClassifier = new DataClassifier(users);	
	}
}
