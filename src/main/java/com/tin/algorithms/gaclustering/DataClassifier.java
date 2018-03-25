package com.tin.algorithms.gaclustering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jgap.Genotype;

/*
 * Create genes and these genes will store unique user ids using those ids we can assign
 * what cluster they will belong to
 * I have to make 4 integer genes which will be my clusters
 * and my chromosomes will consist of these clusters
 * Integer gene will have a random value between 0 to length of the users data and this data
 * will get changed using the fitness function as we switch the centroids
 */

public class DataClassifier {
	
	private ArrayList<Data> users;
	
	/*
	 * Takes in a list of users that is going to be classified
	 */
	public DataClassifier(ArrayList<Data> users) {
		// will contain the list of users
		this.users = users;
	}
}
