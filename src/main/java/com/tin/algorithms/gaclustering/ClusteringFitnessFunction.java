package com.tin.algorithms.gaclustering;

import java.util.ArrayList;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

public class ClusteringFitnessFunction extends FitnessFunction {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Data> data;
	
	public ClusteringFitnessFunction(ArrayList<Data> data) {
		this.data = data;
	}
	
	/*
	 * Computes the distance between two data
	 * @param dataA one of the data
	 * @param dataB the other data
	 */
	private double findDistance(Data dataA, Data dataB) {
		double rtn = 0.0;
		
		// loop over all the featuers of both data and add the result 
		// of squared difference of each data feature for both the data
		// assumption is that both the data are consistent with the number of features
		for (int i = 0; i < dataA.getNumFeatures(); ++i) {
			rtn += (dataA.getFeature(i) - dataB.getFeature(i)) * (dataA.getFeature(i) - dataB.getFeature(i));
		}
		
		return Math.sqrt(rtn);
	}
	
	/*
	 * Sums up the distance of all the data points from the cluster
	 * @param centroid the data point relative to which all the distance gets calculated
	 */
	private double datasetClusterDistance(Data centroid) {
		double totalDistance = 0;
		for (int i = 0; i < data.size(); ++i) {
			totalDistance += findDistance(centroid, data.get(i));
		}
		return totalDistance;
	}
	
	/*
	 * Detects if a chromosome has identical clusters which is not acceptable
	 * @param chromosome the list of genes that get checked, genes/index values cannot be identical
	 */
	private boolean identicalClusterDetection(IChromosome chromosome) {
		for (int i = 0; i < chromosome.size(); ++i) {
			Gene integerGene = chromosome.getGene(i);
			int dataIndex = (Integer) integerGene.getAllele();		
			// keeps count for the occurence of that index
			int occurenceCount = 0;
			for (int j = 0; j < chromosome.size(); ++j) {
				if (dataIndex == (Integer) chromosome.getGene(j).getAllele()) {
					++occurenceCount;
					if (occurenceCount == 2) {
						return true;
					}
				}	
			}
		}
		return false;
	}
	
	/*
	 * Lets say we are determining 4 clusters. We take one cluster find the distance of that
	 * cluster with every single data point and we do the same with 3 other clusters and then add
	 * all the values together. In theory to determine if the clusters chosen are good enough we
	 * need to have this value as low as possible because if perfect clusters are chosen the distance
	 * of its surrounding data points will bring that clusters distance really down.
	 * 
	 * Another factor that is added to the fitness value is the spacing between each cluster. Spaced out cluster centroids
	 * are definitely better than centroids which are close together. Therefore this also plays a great
	 * factor in determining our choice of cluster in the fitness function. Since our genetic algorithm
	 * configuration is delta, population with the lowest fitness value wins, so we have to reward cluster centroids
	 * who have the lowest distance values between all the data points and are far apart from other centroids.
	 * To do so, I will be dividing the total distance between centroid and data points by the total distance of the cluster
	 * centroid from other centroids.
	 * 
	 * A chromosome may contain one or multiple genes, genes are the solution that
	 * we are looking for, in this case our chromosome will contain 4 genes each gene
	 * representing a cluster and a population will be made out of many chromosomes
	 * a chromosome just encapsulates a possible solution thats all.
	 */
	@Override
	protected double evaluate(IChromosome chromosome) {
		boolean identicalCheck = identicalClusterDetection(chromosome);
		
		// if centroid values are the same then the highest possible value is outputed indicating that
		// it is absolutely unacceptable to have clusters with the same centroid value
		if (identicalCheck) {
			return Double.MAX_VALUE;
		}
		
		double totalClusterDistance = 0;
		
		for (int i = 0; i < chromosome.size(); ++i) {
			// getGene method returns the Gene object
			Gene integerGene = chromosome.getGene(i);
			// getAllele method returns the value associated with that gene
			// Each gene contains the index of the data array's data that it is representing
			// using this index values we can look up the data from the data array
			// remember each gene represents a cluster
			int centroidIndex = (Integer) integerGene.getAllele();
			
			// get the data from the list of datas which represents the centroid
			Data clusterCentroid = data.get(centroidIndex);

			double distanceBetweenClusterAndDataset = datasetClusterDistance(clusterCentroid);
			
			totalClusterDistance += distanceBetweenClusterAndDataset;
		}
		
		double fitnessValue = totalClusterDistance;
		
		return fitnessValue;
	}

}
