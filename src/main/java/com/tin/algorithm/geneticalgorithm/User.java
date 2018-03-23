package com.tin.algorithm.geneticalgorithm;

public class User {
	private String name;

	private double[] features;
	private int numFeatures;
	private int cluster;
	private int lastCluster;
	
	public User(String name, int numFeatures) {
		this.name = name;
		this.numFeatures = numFeatures;
		
		// construct the feature array
		features = new double[numFeatures];
		
		cluster = -1;
		lastCluster = -2;
	}
	
	/*
	 * Copy constructors
	 */
	public User(User data) {
		name = data.name;
		numFeatures = data.numFeatures;
		features = new double[numFeatures];
		for (int i = 0; i < data.numFeatures; ++i) {
			features[i] = data.features[i];
		}
		
		cluster = data.cluster;
		lastCluster = data.lastCluster;
	}
	
	/*
	 * Getter for the name attribute
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Setter for the features array
	 * @param index the index value at which the featureValue is assigned
	 * @param featureValue the value which gets assigned to the index
	 */
	public void setFeature(int index, double featureValue) {
		features[index] = featureValue;
	}
	
	/*
	 * Setter for cluster value
	 */
	public void setCluster(int cluster) {
		this.cluster = cluster;
	}
	
	/*
	 * Setter for lastCluster value
	 */
	public void setLastCluster(int lastCluster) {
		this.lastCluster = lastCluster;
	}
	
	/*
	 * Getter for the feature value at a specific index from the features array
	 */
	public double getFeature(int index) {
		return features[index];
	}
	
	/*
	 * Getter for features array
	 */
	public double[] getFeatures() {
		return features;
	}
		
	/*
	 * Getter for the number of features in the data
	 */
	public int getNumFeatures() {
		return numFeatures;
	}	
	
	/*
	 * Getter for cluster value
	 */
	public int getCluster() {
		return cluster;
	}
	
	/*
	 * Getter for lastCluster value
	 */
	public int getLastCluster() {
		return lastCluster;
	}
	
	/*
	 * If the current cluster value is not equal to the last cluster value then the cluster value has changed.
	 */
	public boolean changed() {
		return cluster != lastCluster;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {	
		StringBuilder dataBuilder = new StringBuilder(name);
		for (int i = 0; i < features.length; ++i) {
			dataBuilder.append(' ');
			dataBuilder.append(features[i]);
		}
		return dataBuilder.toString();
	}
}
