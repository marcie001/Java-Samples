package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 木構造のリスト.
 * 
 * @author marcie
 *
 */
public class Tree<T extends Comparable<T>> {
	private final Node<T> root;

	public Tree() {
		root = Node.nullDataNode();
	}

	public Node<T> getRoot() {
		return root;
	}

	@SafeVarargs
	public final void add(T... dataList) {

		if (root.childrenSize() == 0) {
			root.appendChild(Node.createTree(dataList));
			return;
		}

		List<Node<T>> nodeList = root.getChildren();
		Node<T> parent = root;
		for (int i = 0; i < dataList.length; i++) {
			T data = dataList[i];
			Optional<Node<T>> n = nodeList.stream().filter(e -> data.equals(e.getData())).findFirst();

			if (n.isPresent()) {
				parent = n.get();
				nodeList = parent.getChildren();
				continue;
			}
			parent.appendChild(Node.createTree(Arrays.copyOfRange(dataList, i, dataList.length)));
			return;
		}
	}
}
