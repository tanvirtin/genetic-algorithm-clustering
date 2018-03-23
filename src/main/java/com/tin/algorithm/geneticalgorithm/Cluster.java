package com.tin.algorithm.geneticalgorithm;

import java.util.ArrayList;

public class Cluster {
	
	private User centroid;
	private int clusterId;
	private ArrayList<User> clusterData;
	private boolean changed;
	
	public Cluster(int clusterId, User initialCentroid) {
		this.clusterId = clusterId;
		// deep copy of the initial centroid using the copy constructor
		centroid = new User(initialCentroid);
		clusterData = new ArrayList<User>();
		changed = true;
	}
	
	/*
	 * Getter for the clusterId Attribute
	 */
	public int getId() {
		return clusterId;
	}
	
	/*
	 * Adds user object to the cluster
	 */
	public void addUser(User user) {
		clusterData.add(user);
	}
	
	/*
	 * Removes user object from the cluster
	 */
	public void removeUser(User user) {
		clusterData.remove(user);
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
	public User getCentroid() {
		return centroid;
	}
	
	/*
	 * If the current cluster value is not equal to the last cluster value then the cluster value has changed.
	 */
	public boolean changed() {
		return changed;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
