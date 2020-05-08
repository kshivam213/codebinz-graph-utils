package com.gutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinHeap<T> {

	private Map<T, Integer> nodePosition = new HashMap<>();
	private List<Node> allNodes = new ArrayList<>();

	public class Node {

		private int weight;
		private T key;
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public T getKey() {
			return key;
		}
		public void setKey(T key) {
			this.key = key;
		}
	}
	
	/**
	 * Checks where the key exists in heap or not
	 */
	public boolean containsData(T key) {
		return nodePosition.containsKey(key);
	}

	public int getSize() {
		return allNodes.size();
	}

	public void add(int weight, T key) {

		Node node = new Node();
		node.weight = weight;
		node.key = key;

		allNodes.add(node);

		int current = getSize() - 1;
		nodePosition.put(key, current);

		int parent = (current - 1) / 2;

		while (parent >= 0) {

			Node parentN = allNodes.get(parent);
			Node currentN = allNodes.get(current);

			if (parentN.weight > currentN.weight) {
				swap(parentN, currentN);
				updatePositionMap(parentN.key, currentN.key, parent, current);
				current = parent;
				parent = (parent - 1) / 2;
			} else {
				break;
			}
		}
	}

	private void swap(Node node1, Node node2) {
		int weight = node1.weight;
		T data = node1.key;

		node1.key = node2.key;
		node1.weight = node2.weight;

		node2.key = data;
		node2.weight = weight;
	}

	private void updatePositionMap(T data1, T data2, int pos1, int pos2) {
		nodePosition.remove(data1);
		nodePosition.remove(data2);
		nodePosition.put(data1, pos1);
		nodePosition.put(data2, pos2);
	}

	public T min() {
		return allNodes.get(0).key;
	}

	public void decrease(T key, int newWeight) {

		Integer position = nodePosition.get(key);
		allNodes.get(position).weight = newWeight;
		int parent = (position - 1) / 2;
		while (parent >= 0) {
			if (allNodes.get(parent).weight > allNodes.get(position).weight) {
				swap(allNodes.get(parent), allNodes.get(position));
				updatePositionMap(allNodes.get(parent).key, allNodes.get(position).key, parent, position);
				position = parent;
				parent = (parent - 1) / 2;
			} else {
				break;
			}
		}
	}

	/**
	 * Get the weight of given key
	 */
	public Integer getWeight(T key) {
		Integer position = nodePosition.get(key);
		if (position == null) {
			return null;
		} else {
			return allNodes.get(position).weight;
		}
	}
	
	public Node extractMinNode() {
		
		int size =allNodes.size()-1;
		
		Node minNode = new Node();
		minNode.key= allNodes.get(0).key;
		minNode.weight= allNodes.get(0).weight;
		
		int lastNodeWeight= allNodes.get(size).weight;
		allNodes.get(0).weight= lastNodeWeight;
		allNodes.get(0).key= allNodes.get(size).key;
		
		nodePosition.remove(minNode.key);
        nodePosition.remove(allNodes.get(0));
		
        nodePosition.put(allNodes.get(0).key, 0);
        
        allNodes.remove(size);
        
        int currentIndex = 0;
        size--;
        while(true){
            int left = 2*currentIndex + 1;
            int right = 2*currentIndex + 2;
            if(left > size){
                break;
            }
            if(right > size){
                right = left;
            }
            int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
            if(allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight){
                swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
                updatePositionMap(allNodes.get(currentIndex).key,allNodes.get(smallerIndex).key,currentIndex,smallerIndex);
                currentIndex = smallerIndex;
            }else{
                break;
            }
        }
        return minNode;
	}
	
	/**
     * Extract min value key from the heap
     */
    public T extractMin(){
        Node node = extractMinNode();
        return node.key;
    }
    
    public void printHeap(){
        for(Node n : allNodes){
            System.out.println(n.weight + " " + n.key);
        }
    }
    
    private void printPositionMap(){
        System.out.println(nodePosition);
    }
    
    /**
     * Checks with heap is empty or not
     */
    public boolean empty(){
        return allNodes.size() == 0;
    }

    public static void main(String args[]){
        MinHeap<String> heap = new MinHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.add(2,"AFR");
        heap.decrease("Pramila", 1);
        heap.printHeap();
        heap.printPositionMap();
    }
}
