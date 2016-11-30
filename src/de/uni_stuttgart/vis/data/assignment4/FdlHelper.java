package de.uni_stuttgart.vis.data.assignment4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.uni_stuttgart.vis.data.Vector2D;
import de.uni_stuttgart.vis.framework.InfoVisFramework;
import de.uni_stuttgart.vis.geom.AbstractGeometry;
import de.uni_stuttgart.vis.geom.Circle;
import de.uni_stuttgart.vis.geom.Line;

public class FdlHelper {

	/**
	 * The number of iterations for the force directed layout algorithm
	 */
	public static final int iterations = 350;

	/**
	 * the width of the framework window that should be used for boundary collision detection
	 */
	public final static double width = InfoVisFramework.defaultScreenWidth / 2D;
	
	/**
	 * the height of the framework window that should be used for boundary collision detection
	 */
	public final static double height = InfoVisFramework.defaultScreenHeight;

	private final static double area = width * height;

	private double k;
	
	public FdlHelper(Collection<GraphNode> nodes) {
		k = Math.sqrt((double)area / (double)nodes.size());
	}
	
	/**
	 * The cooling function used to reduce the force applied to the nodes. 
	 * @param t - originally the old temperature - For this assignment, use the current iteration.
	 * @return the cooldown factor.
	 */
	public double cool(int t) {
		double maxCool = 2D;
		double coolProgress = ((double)t / (double)(iterations - 1));
		coolProgress = (Math.pow(10, -coolProgress) -0.1)*1.111;
		return coolProgress * maxCool;
	}

	/**
	 * The repulsive force function used in the Fruchterman-Reingold algorithm
	 * @param x as described in the algorithm
	 * @return
	 */
	public double fr(double x) {
		if (x == 0 || Double.isNaN(x)) return 0;
		return (double) (k * k) / x;
	}

	/**
	 * The attractive force function used in the Fruchterman-Reingold algorithm
	 * @param x as described in the algorithm
	 * @return
	 */
	public double fa(double x) {
		if (x == 0 || Double.isNaN(x)) return 0;
		return (x * x) / (double) k;
	}

	private static int DIAMETER = 20;

	/**
	 * This method takes a graph object and returns a list of abstract geometries that can be used for rendering 
	 * by the InfoVis framework
	 * @param graph Input graph
	 * @return List of geometries that can be rendered by the framework
	 */
	public List<AbstractGeometry> getGraphGeometry(Graph graph) {

		List<AbstractGeometry> graphGeometry = new LinkedList<AbstractGeometry>();

		Collection<GraphNode> nodeList = graph.getNodes();
		// add all links
		for (GraphNode currentNode : nodeList) {
			Vector2D currentPosition = currentNode.getPosition();
			// add all links to adjacent nodes
			Collection<GraphNode> adjacentList = currentNode.getAdjacentNodes();
			for (GraphNode currentAdjacent : adjacentList) {
				Vector2D currentAdjacentPos = currentAdjacent.getPosition();
				Line link = new Line(currentPosition.getX() + DIAMETER / 2, currentPosition.getY() + DIAMETER / 2,
						currentAdjacentPos.getX() + DIAMETER / 2, currentAdjacentPos.getY() + DIAMETER / 2);
				link.setColor(Color.darkGray);
				link.setStroke(new BasicStroke(2.0f));
				graphGeometry.add(link);
			}
		}
		// add all nodes
		Boolean firstNode = true;
		for (GraphNode currentNode : nodeList) {
			Vector2D currentPosition = currentNode.getPosition();
			if (firstNode) {
				firstNode = false;
//				System.out.println("First node position: " + currentPosition.getX() + ", " + currentPosition.getY());
			}
			Circle node = new Circle(currentPosition.getX(), currentPosition.getY(), DIAMETER);
			node.setColor(Color.black);
			node.setText(String.valueOf(currentNode.getNodeID()));
			node.setTextColor(Color.white);
			graphGeometry.add(node);
		}

		return graphGeometry;
	}
}
