package de.uni_stuttgart.vis.data.assignment4;

import java.util.Collection;
import java.util.HashSet;

import de.uni_stuttgart.vis.data.Vector2D;

public class GraphNode {

	private HashSet<GraphNode> adjacentNodes;
	private int nodeID;
	private Vector2D position;
	private Vector2D displacement;

	public GraphNode() {
		adjacentNodes = new HashSet<GraphNode>();
		position = new Vector2D();
		nodeID = 0;
	}

	public GraphNode(int id) {
		adjacentNodes = new HashSet<GraphNode>();
		position = new Vector2D();
		nodeID = id;
	}

	public Collection<GraphNode> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void addAdjacentNode(GraphNode node) {
		this.adjacentNodes.add(node);
	}

	public int getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public Vector2D getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Vector2D displacement) {
		this.displacement = displacement;
	}

	
}
