package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 木構造のノード.
 * 
 * @author marcie
 *
 * @param <T>
 *            データ
 */
public class Node<T extends Comparable<T>> {

	private final T data;
	private Node<T> parent;
	private final List<Node<T>> children;

	/**
	 * コンストラクタ.
	 * 
	 * @param data
	 *            データ
	 */
	public Node(T data) {
		if (data == null) {
			throw new IllegalArgumentException("data is null.");
		}
		this.data = data;
		this.children = new ArrayList<>();
	}

	/**
	 * コンストラクタ. data が null なノードは基本的に考慮していない。
	 */
	private Node() {
		this.data = null;
		this.children = new ArrayList<>();
	}

	/**
	 * データを返す.
	 * 
	 * @return データ
	 */
	public T getData() {
		return data;
	}

	/**
	 * 親ノードを返す.
	 * 
	 * @return 親ノードのオプショナル
	 */
	@JsonIgnore
	public Optional<Node<T>> getParent() {
		return Optional.ofNullable(parent);
	}

	/**
	 * 親ノードをセットする.
	 * 
	 * @param node
	 *            親となるノード
	 */
	protected void setParent(Node<T> node) {
		if (this.getData() == null) {
			throw new IllegalArgumentException("data が null なノードは子ノードになることはできません。");
		}
		this.parent = node;
	}

	/**
	 * すべての子ノードをソートして返す.
	 * 
	 * @return すべての子ノード
	 */
	public List<Node<T>> getSortedChildren() {
		return children.stream().sorted((e0, e1) -> e0.getData().compareTo(e1.getData())).collect(Collectors.toList());
	}

	/**
	 * すべての子ノードを返す.
	 * 
	 * @return すべての子ノード
	 */
	@JsonIgnore
	public List<Node<T>> getChildren() {
		return new ArrayList<>(children);
	}

	/**
	 * 子ノードの数を返す.
	 * 
	 * @return 子ノードの数
	 */
	public int childrenSize() {
		return children.size();
	}

	/**
	 * 子ノードから t と同じものを検索する
	 * 
	 * @param t
	 *            検索条件となるインスタンス
	 * @return Node インスタンスのオプションナル
	 */
	public Optional<Node<T>> findChild(T t) {
		return children.stream().filter(t::equals).findFirst();
	}

	/**
	 * このインスタンスに node を子ノードとして追加する.
	 * 
	 * @param node
	 *            子ノードとなるノード
	 * @return このインスタンス
	 */
	public Node<T> appendChild(Node<T> node) {
		node.setParent(this);
		this.children.add(node);
		return this;
	}

	/**
	 * data から Node のインスタンスを作成し、それをこのインスタンスに子ノードとして追加する.
	 * 
	 * @param data
	 *            子ノードとなるデータ
	 * @return このインスタンス
	 */
	public Node<T> appendChild(T data) {
		Node<T> node = new Node<>(data);
		return appendChild(node);
	}

	/**
	 * node にこのインスタンスを子ノードとして追加する.
	 * 
	 * @param node
	 *            親ノードとなるノード
	 * @return このインスタンス
	 */
	public Node<T> appendTo(Node<T> node) {
		node.appendChild(this);
		return this;
	}

	/**
	 * data から Node のインスタンスを作成し、それにこのインスタンスを子ノードとして追加する.
	 * 
	 * @param data
	 *            親ノードとなるデータ
	 * @return このインスタンス
	 */
	public Node<T> appendTo(T data) {
		Node<T> node = new Node<>(data);
		return appendTo(node);
	}

	/**
	 * このインスタンスがルートノードのとき true, そうでないとき false を返す.
	 * 
	 * @return このインスタンスがルートノードのとき true, そうでないとき false
	 */
	@JsonIgnore
	public boolean isRoot() {
		return parent == null;
	}

	/**
	 * ルートノードを取得する.
	 * 
	 * @return ルートノード
	 */
	@JsonIgnore
	public Node<T> getRoot() {
		Node<T> n = this;
		while (!n.isRoot()) {
			n = n.getParent().get();
		}
		return n;
	}

	/**
	 * 第1引数の子に第2引数、第2引数の子に第3引数... というような木構造を生成する. ただし、 引数の長さが 0 の場合 null を返す.
	 * 
	 * @param dataList
	 *            データ
	 * @return 生成した木構造のルート
	 */
	@SafeVarargs
	public static <T extends Comparable<T>> Node<T> createTree(T... dataList) {
		return createTree(Arrays.stream(dataList));
	}

	/**
	 * 第1要素の子に第2要素、第2要素の子に第3要素... というような木構造を生成する. ただし、 引数の長さが 0 の場合 null を返す.
	 * 
	 * @param dataList
	 *            データ
	 * @return 生成した木構造のルート
	 */
	public static <T extends Comparable<T>> Node<T> createTree(Collection<T> dataList) {
		return createTree(dataList.stream());
	}

	private static <T extends Comparable<T>> Node<T> createTree(Stream<T> dataStream) {
		return dataStream.map(Node<T>::new).reduce((e0, e1) -> e1.appendTo(e0)).map(Node::getRoot).orElse(null);
	}

	/**
	 * データが null のノードを作成する.
	 * 
	 * @return データが null のノード
	 */
	public static <T extends Comparable<T>> Node<T> nullDataNode() {
		return new Node<>();
	}
}
