package com.tin.algorithm.geneticalgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserClassifier {
	
	private List<User> users;
	
	
	public UserClassifier() {
		// will contain the list of users
		users = new ArrayList<User>();
	}
	
	public void loadUsersFromFile(String path) throws FileNotFoundException {
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
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		UserClassifier userClassifier = new UserClassifier();
		
		userClassifier.loadUsersFromFile("./data/data-1.txt");
		
	}
	
}
