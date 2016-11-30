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
	double width, height, area;

	@Override
	public List<AbstractGeometry> mapData() {
		width = FdlHelper.width;
		height = FdlHelper.height;
		area = width * height;
		Graph graph = new DataProvider().getGraph();
		fruchtermanReingold(graph);
		return null;
	}

	public static void main(String[] args) {
		new Submission();

	}

	private void fruchtermanReingold(Graph graph) {
		for (GraphNode node : graph.getNodes()) {
			node.setPosition(new Vector2D(Math.random() % width, Math.random() % height));
		}
		renderCanvas(null);
	}
}
