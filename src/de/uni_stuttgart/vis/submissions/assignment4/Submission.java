package de.uni_stuttgart.vis.submissions.assignment4;

import java.util.List;

import de.uni_stuttgart.vis.data.Vector2D;
import de.uni_stuttgart.vis.data.assignment4.DataProvider;
import de.uni_stuttgart.vis.data.assignment4.FdlHelper;
import de.uni_stuttgart.vis.data.assignment4.Graph;
import de.uni_stuttgart.vis.data.assignment4.GraphNode;
import de.uni_stuttgart.vis.framework.InfoVisFramework;
import de.uni_stuttgart.vis.geom.AbstractGeometry;

public class Submission extends InfoVisFramework {
	double width, height, area, iterations;

	@Override
	public List<AbstractGeometry> mapData() {
		width = FdlHelper.width;
		height = FdlHelper.height;
		area = width * height;
		iterations = FdlHelper.iterations;
		Graph graph = new DataProvider().getGraph();
		fruchtermanReingold(graph);
		return null;
	}

	public static void main(String[] args) {
		new Submission();

	}

	private void fruchtermanReingold(Graph graph) {
		FdlHelper fdlHelper = new FdlHelper(graph.getNodes());
		for (GraphNode node : graph.getNodes()) {
			node.setPosition(new Vector2D(Math.random() % width, Math.random() % height));
		}
		renderCanvas(fdlHelper.getGraphGeometry(graph));
		double k = Math.sqrt(area / graph.getNodes().size());
		for (int i = 1; i <= iterations; i++) {
			for (GraphNode u : graph.getNodes()) {
				u.setDisplacement(new Vector2D(0, 0));
				for (GraphNode v : graph.getNodes()) {
					if (!u.equals(v)) {
						double delta = v.getPosition().distance(u.getPosition());
						double factor = (delta / Math.abs(delta)) * (fdlHelper.fr(Math.abs(delta)));
						Vector2D vDisplacement = v.getDisplacement();
						vDisplacement.setX(v.getDisplacement().getX() + factor);
						vDisplacement.setY(v.getDisplacement().getY() + factor);
						v.setDisplacement(vDisplacement);
						
					}
				}
			}
			
			// for each edge
			for (GraphNode v : graph.getNodes()) {
				for (GraphNode u : v.getAdjacentNodes()) {
					double delta = v.getPosition().distance(u.getPosition());
					
				}
			}
		}
	}
}
