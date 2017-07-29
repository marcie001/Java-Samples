package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class NodeTests {

	@Test
	public void testAppendTo_Ok() {
		Node<String> child = new Node<>("CHILD_NODE");
		Node<String> actual = child.appendTo("ROOT_NODE");

		assertThat(actual.getData(), is("CHILD_NODE"));
		assertThat(actual.getParent().get().getData(), is("ROOT_NODE"));
	}

	@Test
	public void testGetRoot_Ok() {
		Node<String> n1 = new Node<>("1");
		Node<String> n2 = new Node<>("2");
		Node<String> n3 = new Node<>("3");
		Node<String> n4 = new Node<>("4");
		Node<String> n5 = new Node<>("5");
		n1.appendChild(n2);
		n2.appendChild(n3);
		n3.appendChild(n4);
		n4.appendChild(n5);

		assertThat(n5.getRoot().getData(), is("1"));
		assertThat(n1.getRoot().getData(), is("1"));
	}

	@Test
	public void testCreateTree_Ok() {
		Node<String> actual = Node.createTree("1", "2", "3", "4", "5");

		assertThat(actual.getData(), is("1"));
		assertThat(actual.getParent().isPresent(), is(false));
		assertThat(actual.getChildren().size(), is(1));
		assertThat(actual.getChildren().get(0).getData(), is("2"));

		actual = actual.getChildren().get(0);
		assertThat(actual.getParent().get().getData(), is("1"));
		assertThat(actual.getChildren().size(), is(1));
		assertThat(actual.getChildren().get(0).getData(), is("3"));

		actual = actual.getChildren().get(0);
		assertThat(actual.getParent().get().getData(), is("2"));
		assertThat(actual.getChildren().size(), is(1));
		assertThat(actual.getChildren().get(0).getData(), is("4"));

		actual = actual.getChildren().get(0);
		assertThat(actual.getParent().get().getData(), is("3"));
		assertThat(actual.getChildren().size(), is(1));
		assertThat(actual.getChildren().get(0).getData(), is("5"));

		actual = actual.getChildren().get(0);
		assertThat(actual.getParent().get().getData(), is("4"));
		assertThat(actual.getChildren().size(), is(0));

	}

	@Test
	public void testCreateTree_Null() {
		Node<String> actual = Node.createTree();

		assertThat(actual, is(nullValue()));
	}
}
