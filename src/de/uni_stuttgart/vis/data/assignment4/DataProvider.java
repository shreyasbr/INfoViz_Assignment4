package de.uni_stuttgart.vis.data.assignment4;

import de.uni_stuttgart.vis.data.Vector2D;

public class DataProvider {

	public Graph getGraph(){
		Graph graph = new Graph();

		// create new nodes
		GraphNode node1 = new GraphNode(1);
		node1.setPosition(new Vector2D(300, 300));
		GraphNode node2 = new GraphNode(2);
		node2.setPosition(new Vector2D(290, 290));
		GraphNode node3 = new GraphNode(3);
		node3.setPosition(new Vector2D(290, 310));
		GraphNode node4 = new GraphNode(4);
		node4.setPosition(new Vector2D(310, 290));
		GraphNode node5 = new GraphNode(5);
		node5.setPosition(new Vector2D(310, 310));
		GraphNode node6 = new GraphNode(6);
		node6.setPosition(new Vector2D(300, 210));
		GraphNode node7 = new GraphNode(7);
		node7.setPosition(new Vector2D(330, 310));
		GraphNode node8 = new GraphNode(8);
		node8.setPosition(new Vector2D(300, 350));

		// connect adjacent nodes
		node1.addAdjacentNode(node2);
		node1.addAdjacentNode(node3);
		node1.addAdjacentNode(node4);
		node1.addAdjacentNode(node5);
		node2.addAdjacentNode(node1);
		node2.addAdjacentNode(node3);
		node3.addAdjacentNode(node1);
		node3.addAdjacentNode(node2);
		node4.addAdjacentNode(node1);
		node4.addAdjacentNode(node6);
		node5.addAdjacentNode(node1);
		node5.addAdjacentNode(node7);
		node5.addAdjacentNode(node8);
		node6.addAdjacentNode(node4);
		node7.addAdjacentNode(node5);
		node8.addAdjacentNode(node5);

		// add nodes to the graph
		graph.addNode(node1);
		graph.addNode(node2);
		graph.addNode(node3);
		graph.addNode(node4);
		graph.addNode(node5);
		graph.addNode(node6);
		graph.addNode(node7);
		graph.addNode(node8);	
		
		return graph; 
	}
}
