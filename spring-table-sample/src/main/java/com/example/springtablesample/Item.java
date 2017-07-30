package com.example.springtablesample;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Item {

	private String name;

	private List<Specification> specifications;

	public Item(String name, Specification... specifications) {
		this.name = name;
		this.setSpecifications(Arrays.asList(specifications));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Specification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<Specification> specifications) {
		this.specifications = specifications;
	}

	/**
	 * specificationName に対応した Specification を返す.
	 * 
	 * @param specificationName
	 *            取得したい specification の name
	 * @return specificationName に対応した Specification を返す.
	 */
	@JsonIgnore
	public Specification findSpecification(String specificationName) {
		return specifications.stream().filter(e -> specificationName.equals(e.getName())).findFirst().get();
	}

}
