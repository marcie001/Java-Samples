package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TreeTests {

	@Test
	public void testAdd_Ok() {
		Tree<String> tree = new Tree<>();
		tree.add("a", "b", "c");
		tree.add("a", "c", "d");
		tree.add("e", "f", "g");
		tree.add("a", "b", "d", "h");
		tree.add("a", "b", "c");

		Node<String> root = tree.getRoot();
		assertThat(root.childrenSize(), is(2));

		Node<String> a = root.getSortedChildren().get(0);
		assertThat(a.childrenSize(), is(2));
		assertThat(a.getData(), is("a"));

		Node<String> ab = a.getSortedChildren().get(0);
		assertThat(ab.childrenSize(), is(2));
		assertThat(ab.getData(), is("b"));

		Node<String> abc = ab.getSortedChildren().get(0);
		assertThat(abc.childrenSize(), is(0));
		assertThat(abc.getData(), is("c"));

		Node<String> abd = ab.getSortedChildren().get(1);
		assertThat(abd.childrenSize(), is(1));
		assertThat(abd.getData(), is("d"));

		Node<String> abdh = abd.getSortedChildren().get(0);
		assertThat(abdh.childrenSize(), is(0));
		assertThat(abdh.getData(), is("h"));

		Node<String> ac = a.getSortedChildren().get(1);
		assertThat(ac.childrenSize(), is(1));
		assertThat(ac.getData(), is("c"));

		Node<String> acd = ac.getSortedChildren().get(0);
		assertThat(acd.childrenSize(), is(0));
		assertThat(acd.getData(), is("d"));

		Node<String> e = root.getSortedChildren().get(1);
		assertThat(e.childrenSize(), is(1));
		assertThat(e.getData(), is("e"));

		Node<String> ef = e.getSortedChildren().get(0);
		assertThat(ef.childrenSize(), is(1));
		assertThat(ef.getData(), is("f"));

		Node<String> efg = ef.getSortedChildren().get(0);
		assertThat(efg.childrenSize(), is(0));
		assertThat(efg.getData(), is("g"));

	}
}
