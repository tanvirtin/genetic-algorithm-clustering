package com.tin.algorithms.gaclustering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DeltaFitnessEvaluator;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;

public class GeneticAlgorithm {
	
	private ArrayList<Data> data;
	
    private Configuration conf;
    private SwappingMutationOperator swapper;
    private ClusteringFitnessFunction fitnessFunction;
     
    private Chromosome clusterChromosome;
    private int numCluster;
    
    
	/*
	 * Takes in a list of users that is going to be classified
	 * @param data the data that will get classified into groups
	 */
	public GeneticAlgorithm(ArrayList<Data> data, int numCluster) {
		// will contain the list of data
		this.data = data;
		this.numCluster = numCluster;
	}
		
	/*
	 * Creates and configures the algorithm and returns the population
	 * @param populationSize the size of the population that this genetic algorithm
	 */
	public Genotype create(int populationSize) throws InvalidConfigurationException {
		conf = new DefaultConfiguration();
		Configuration.resetProperty(Configuration.PROPERTY_FITEVAL_INST);
		conf.setFitnessEvaluator(new DeltaFitnessEvaluator());
		
		fitnessFunction = new ClusteringFitnessFunction(data);
 
		conf.setFitnessFunction(fitnessFunction);
 
		Gene[] clusterGenes = new Gene[numCluster];
       
		for (int i = 0; i < clusterGenes.length; ++i) {
			clusterGenes[i] = new IntegerGene(conf, 0, this.data.size() - 1);
		}
		
		clusterChromosome = new Chromosome(conf, clusterGenes);
		
		conf.setSampleChromosome(clusterChromosome);
		conf.setPopulationSize(populationSize);
		
		// create the randomized population with all the information provided in the config object
		Genotype population = Genotype.randomInitialGenotype(conf);
		
		// return the population so that it can be evolved
		return population;
	}	
}
