package com.github.kshivam213.alg.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.github.kshivam213.Graph;
import com.github.kshivam213.Vertex;

public class TopologicalSort<T> implements VisitSorted<T> {

	@Override
	public List<Vertex<T>> traverseSorted(Graph<T> graph) {

		Stack<Vertex<T>> stack = new Stack();
		
		List<Vertex<T>> result = new ArrayList<>();
		Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();

		for (Vertex<T> vertex : graph.getAllVertex()) {
			if (visited.get(vertex.getId()) == false)
				topSortUtil(vertex, visited, stack);
		}
		
		while(!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	public void topSortUtil(Vertex<T> vertex, Map<Integer, Boolean> visited, Stack<Vertex<T>> stack) {
		
		visited.put(vertex.getId(), true);
		for (Vertex<T> vtex : vertex.getAdjacentVertexes()) {

			if (visited.get(vertex.getId()) == null) {
				topSortUtil(vtex, visited, stack);
			}
		}

		stack.push(vertex);
	}

}
