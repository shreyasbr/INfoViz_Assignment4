package de.uni_stuttgart.vis.data.assignment4;

import java.util.Collection;
import java.util.HashSet;

public class Graph {
	
	private HashSet<GraphNode> nodes;

	public Graph() {
		setNodes(new HashSet<GraphNode>());
	}

	public Collection<GraphNode> getNodes() {
		return nodes;
	}

	public void setNodes(HashSet<GraphNode> nodes) {
		this.nodes = nodes;
	}

	public void addNode(GraphNode node) {
		this.nodes.add(node);
	}
}
