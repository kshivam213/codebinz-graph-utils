package com.gutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph<T> {

	private List<Edge<T>> allEdges;
	private Map<Integer, Vertex<T>> allVertex;
	boolean isDirected = false;

	public Graph(boolean isDirected) {
		allEdges = new ArrayList<Edge<T>>();
		allVertex = new HashMap<Integer, Vertex<T>>();
		this.isDirected = isDirected;
	}

	public void addEdge(Integer id1, Integer id2) {
		addEdge(id1, id2, 0);
	}

	public void addVertex(Vertex<T> vertex) {
		if (allVertex.containsKey(vertex.getId())) {
			return;
		}
		allVertex.put(vertex.getId(), vertex);
		for (Edge<T> edge : vertex.getEdges()) {
			allEdges.add(edge);
		}
	}

	public Vertex<T> addSingleVertex(Integer id) {
		if (allVertex.containsKey(id)) {
			return allVertex.get(id);
		}
		Vertex<T> v = new Vertex<T>(id);
		allVertex.put(id, v);
		return v;
	}

	public Vertex<T> getVertex(Integer id) {
		return allVertex.get(id);
	}

	public void addEdge(Integer id1, Integer id2, int weight) {
		Vertex<T> vertex1 = null;
		if (allVertex.containsKey(id1)) {
			vertex1 = allVertex.get(id1);
		} else {
			vertex1 = new Vertex<T>(id1);
			allVertex.put(id1, vertex1);
		}
		Vertex<T> vertex2 = null;
		if (allVertex.containsKey(id2)) {
			vertex2 = allVertex.get(id2);
		} else {
			vertex2 = new Vertex<T>(id2);
			allVertex.put(id2, vertex2);
		}

		Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
		allEdges.add(edge);
		vertex1.addAdjacentVertex(edge, vertex2);
		if (!isDirected) {
			vertex2.addAdjacentVertex(edge, vertex1);
		}

	}

	public List<Edge<T>> getAllEdges() {
		return allEdges;
	}

	public Collection<Vertex<T>> getAllVertex() {
		return allVertex.values();
	}

	public void setDataForVertex(Integer id, T data) {
		if (allVertex.containsKey(id)) {
			Vertex<T> vertex = allVertex.get(id);
			vertex.setData(data);
		}
	}

	public List<Vertex<T>> bfs(Vertex<T> source) {

		Map<Integer, Boolean> visited = new HashMap<>();

		Queue<Vertex<T>> queue = new LinkedList<>();

		queue.add(source);

		visited.put(source.getId(), true);

		List<Vertex<T>> visitedData = new ArrayList<>();

		while (!queue.isEmpty()) {

			Vertex<T> poll = queue.poll();
			for (Vertex<T> data : poll.getAdjacentVertexes()) {

				if (visited.get(data.getId())) {
					visitedData.add(data);
					visited.put(data.getId(), true);
					queue.add(data);
				}
			}
		}
		return visitedData;
	}

	public List<Vertex<T>> dfs(Vertex<T> source) {

		Map<Integer, Boolean> visited = new HashMap<>();
		
		List<Vertex<T>> result = new ArrayList<>();
		dfsUtil(source, visited, result);
		
		return result;
	}

	private void dfsUtil(Vertex<T> source, Map<Integer, Boolean> visited, List<Vertex<T>> result) {
		
		visited.put(source.getId(), true);
		
		result.add(source);
		
		List<Vertex<T>> list = source.getAdjacentVertexes();
		
		for (Vertex<T> data : list) {
			
			if (!visited.get(data.getId())) {
				dfsUtil(data, visited, result);
			}
		}
	}
	
	private int find(Map<Integer, Integer> parent, Integer i) {
		if (parent.get(i) < 0)
			return i;
		return find(parent, parent.get(i));
	}

	private void Union(Map<Integer, Integer> parent, int x, int y) {
		
		int xset = find(parent, x);
		int yset = find(parent, y);
		
		if(Math.abs(parent.get(yset)) > Math.abs(parent.get(xset))) {
			
			parent.put(yset, parent.get(yset)+ (-2));
			parent.put(xset, yset);
		}else {
			
			if(Math.abs(parent.get(xset)) == 1) {
				parent.put(xset, -2);
			}else
				parent.put(xset, parent.get(xset) + (-2));
			
			parent.put(yset, xset);
		}
	}

	public boolean isCycle() {
		
		Map<Integer, Integer> parent = new HashMap<>();
		
		for (Edge<T> edge: allEdges) {
			
			int x = find(parent, edge.getVertex1().getId());
			int y = find(parent, edge.getVertex2().getId());

			if (x == y)
				return true;
			
			Union(parent, x, y);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Edge<T> edge : getAllEdges()) {
			buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
			buffer.append("\n");
		}
		return buffer.toString();
	}
}