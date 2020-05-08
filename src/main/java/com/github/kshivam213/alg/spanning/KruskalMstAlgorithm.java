package com.github.kshivam213.alg.spanning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.kshivam213.Edge;
import com.github.kshivam213.Graph;

public class KruskalMstAlgorithm<T> implements SpanningTreeAlgorithm<T> {

	Integer find(Map<Integer, Integer> parents, Integer i) {

		if (parents.get(i) < 0)
			return i;

		return find(parents, parents.get(i));
	}

	void union(Map<Integer, Integer> parent, int x, int y) {

		int xParent = find(parent, x);
		int yParent = find(parent, y);

		if (Math.abs(yParent) > Math.abs(xParent)) {
     
			parent.put(yParent, parent.get(yParent) + (-2));
			parent.put(xParent, yParent);
		} else {

			if (Math.abs(parent.get(xParent)) == 1)
				parent.put(xParent, parent.get(xParent) + (-2));
			else
				parent.put(xParent, parent.get(xParent));

			parent.put(yParent, xParent);
		}
	}

	@Override
	public List<Edge<T>> findMst(Graph<T> graph) {

		List<Edge<T>> edges = graph.getAllEdges();

		Collections.sort(edges, new Comparator<Edge<T>>() {
			@Override
			public int compare(Edge<T> edge1, Edge<T> edge2) {
				if (edge1.getWeight() <= edge2.getWeight()) {
					return -1;
				} else {
					return 1;
				}
			}
		});

		List<Edge<T>> result = new ArrayList<Edge<T>>();
		Map<Integer, Integer> parents = new HashMap<>();

		for (int i = 0; i < edges.size(); i++) {

			Edge<T> edge = edges.get(i);
			Integer x = find(parents, edge.getVertex1().getId());
			Integer y = find(parents, edge.getVertex2().getId());

			if (x != y) {

				result.add(edge);
				union(parents, x, y);
			}
		}

		return result;
	}
}
