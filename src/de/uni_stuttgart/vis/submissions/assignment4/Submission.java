package de.uni_stuttgart.vis.submissions.assignment4;

import java.util.LinkedList;
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
	double t;

	@Override
	public List<AbstractGeometry> mapData() {
		width = FdlHelper.width;
		height = FdlHelper.height;
		area = width * height;
		iterations = FdlHelper.iterations;
		t = 1;
		Graph graph = new DataProvider().getGraph();
		fruchtermanReingold(graph);

		return null;
	}

	public static void main(String[] args) {
		new Submission();
	}

	private void fruchtermanReingold(Graph graph) {
		FdlHelper fdlHelper = new FdlHelper(graph.getNodes());

		renderCanvas(fdlHelper.getGraphGeometry(graph));

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 1; i <= iterations; i++) {
			for (GraphNode v : graph.getNodes()) {
				v.setDisplacement(new Vector2D(0, 0));
				for (GraphNode u : graph.getNodes()) {
					if (v.getNodeID() != u.getNodeID()) {
						Vector2D delta = v.getPosition().subtract(u.getPosition());
						Vector2D normDelta = delta.normalize();
						Vector2D factor = normDelta.multiply(fdlHelper.fr(delta.norm()));
						v.setDisplacement(v.getDisplacement().add(factor));
					}
				}
			}

			// for each edge
			for (GraphNode v : graph.getNodes()) {
				for (GraphNode u : v.getAdjacentNodes()) {
					Vector2D delta = v.getPosition().subtract(u.getPosition());
					Vector2D normDelta = delta.normalize();
					Vector2D factor = normDelta.multiply(fdlHelper.fa(delta.norm()));

					v.setDisplacement(v.getDisplacement().subtract(factor));
					u.setDisplacement(u.getDisplacement().add(factor));
				}
			}

			for(GraphNode v: graph.getNodes())
			{
				//Use temp. t to scale; limit max displacement to frame
				Vector2D factor = v.getDisplacement().normalize().multiply(Math.min(v.getDisplacement().norm(),t));
				v.setPosition(v.getPosition().add(factor));
				v.getPosition().setX(Math.min(width/2,Math.max(-1*(width/2),v.getPosition().getX())));
				v.getPosition().setY(Math.min(height/2,Math.max(-1*(height/2),v.getPosition().getY())));
			}

			t = fdlHelper.cool(i);

			//Render the Graph
			renderCanvas(fdlHelper.getGraphGeometry(graph));

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
