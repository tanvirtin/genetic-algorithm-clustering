package com.tin.algorithms.gaclustering;

import java.io.FileNotFoundException;

import org.jgap.InvalidConfigurationException;

public class ClusteringController {
	public static void main(String[] args) throws FileNotFoundException, InvalidConfigurationException {
		DataClusterer clusterer = new DataClusterer(4);
		Data[] clusters = clusterer.createClusters("./data/data-2.txt");
		
		for (int i = 0; i < clusters.length; ++i) {
			System.out.println(clusters[i]);
		}
		
	}
}
