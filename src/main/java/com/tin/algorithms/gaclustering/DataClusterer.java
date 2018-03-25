package com.tin.algorithms.gaclustering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;

public class DataClusterer {
	
	private int numClusters;
	private int populationSize;
	private int evolutionIterations;
	
	public DataClusterer(int numClusters) {
		this.numClusters = numClusters;
		populationSize = 1000;
		evolutionIterations = 100;
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
		}
		// close the scanner
		scanner.close();
		
		return users;
	}
	
	public void createClusters() throws FileNotFoundException, InvalidConfigurationException {		
		ArrayList<Data> users = loadData();
		
		// create the instance of the genetic algorithm
		GeneticAlgorithm ga = new GeneticAlgorithm(users, numClusters);	
		
		// get the population from the genetic algorithm
		Genotype population = ga.create(populationSize);
        
		for (int i = 0; i < evolutionIterations; i++) {
			population.evolve();
			IChromosome potentialSolution = population.getFittestChromosome();
			double fitness = potentialSolution.getFitnessValue();
			System.out.println("Highest Fitness on evolution " + i + " -> " + fitness);
        }
		IChromosome solution = population.getFittestChromosome();
        for (int i = 0; i < solution.size(); ++i) {
        	System.out.println(solution.getGene(i).getAllele());
        } 
	}
}
