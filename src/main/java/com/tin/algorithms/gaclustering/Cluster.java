package com.tin.algorithms.gaclustering;

import java.util.ArrayList;

public class Cluster {
	
	private Data centroid;
	private int clusterId;
	private ArrayList<Data> clusterData;
	private boolean changed;
	
	
	public Cluster(int clusterId, Data initialCentroid) {
		this.clusterId = clusterId;
		// deep copy of the initial centroid using the copy constructor
		centroid = new Data(initialCentroid);
		clusterData = new ArrayList<Data>();
		changed = true;
	}
		
	/*
	 * Getter for the clusterId attribute
	 */
	public int getId() {
		return clusterId;
	}
	
	
	/*
	 * Adds data object to the cluster
	 */
	public void addData(Data data) {
		clusterData.add(data);
	}
	
	/*
	 * Removes data object from the cluster
	 */
	public void removeData(Data data) {
		clusterData.remove(data);
	}
	
	/*
	 * Clears all the data stored in the cluster
	 */
	public void clearCluster() {
		clusterData.clear();
	}
	
	
	/*
	 * Getter for the centroid attribute
	 */
	public Data getCentroid() {
		return centroid;
	}
	
	/*
	 * If the current cluster value is not equal to the last cluster value then the cluster value has changed.
	 */
	public boolean changed() {
		return changed;
	}

	/*
	 * Re calculates the centroid, this method is called in each iteration
	 * This is probably the most important method in this class
	 */
	public void reCalculateCentroid() {		
		for (int i = 0; i < clusterData.size(); ++i) {
			// loops over the feature array
			for (int j = 0; j < centroid.getNumFeatures(); ++j) {
				double newValue = centroid.getFeature(j) + clusterData.get(i).getFeature(j);
				centroid.setFeature(j, newValue);
			}	
		}
		
		for (int i = 0; i < centroid.getNumFeatures(); ++i) {
			centroid.setFeature(i, centroid.getFeature(i) / clusterData.size());
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Centroid:\n");
		sb.append(centroid.toString());
		sb.append("\n");
		sb.append("Cluster data:\n");
		sb.append("[");
		for (int i = 0; i < clusterData.size(); ++i) {
			sb.append(clusterData.get(i).getName());
			// if we are at the last index then we don't append "," because it is the last index and there is no more elements
			if (i != clusterData.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		sb.append("\n");
		
		return sb.toString();
	}

}
