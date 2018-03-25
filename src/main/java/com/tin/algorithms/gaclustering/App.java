package com.tin.algorithms.gaclustering;

import java.io.FileNotFoundException;

import org.jgap.InvalidConfigurationException;

public class App {
	public static void main(String[] args) throws FileNotFoundException, InvalidConfigurationException {
		DataClusterer clusterer = new DataClusterer(4);
		clusterer.createClusters();
	}
}
