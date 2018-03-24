package com.tin.algorithm.geneticalgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserClassifier {
	
	private ArrayList<User> users;
	
	/*
	 * Takes in a list of users that is going to be classified
	 */
	public UserClassifier(ArrayList<User> users) {
		// will contain the list of users
		this.users = users;
	}
	
	
	
	
	
	
	
		
	public static void main(String[] args) throws FileNotFoundException {
		
		String path = "./data/data-1.txt";
		
		ArrayList<User> users = new ArrayList<User>();
		
		File file = new File(path);
		
		Scanner scanner = new Scanner(file);
		
		int numUsers = scanner.nextInt();
		int numFeatures = scanner.nextInt();
		
		for (int i = 0; i < numUsers; ++i) {
			String name = scanner.next();
			User newUser = new User(name, numFeatures);
			for (int j = 0; j < numFeatures; ++j) {
				newUser.setFeature(j, scanner.nextDouble());
			}
			users.add(newUser);
			System.out.println(newUser);
		}
		// close the scanner
		scanner.close();
		
		
		UserClassifier userClassifier = new UserClassifier(users);
	}
	
}
