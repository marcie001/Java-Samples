package com.example.springtablesample;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 規格を表すクラス.
 * 
 * @author marcie
 *
 */
public class Specification implements Serializable, Comparable<Specification> {
	private static final long serialVersionUID = 1L;

	/** 規格の名前. */
	private final String name;

	/** 規格の値. */
	private final String value;

	public Specification(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	/**
	 * 並び替え用固定長文字列を返す.
	 * 
	 * @return 並び替え用固定長文字列
	 */
	@JsonIgnore
	public String getFixedLengthValue() {
		return String.format("%20s", value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specification other = (Specification) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int compareTo(Specification o) {
		int result = this.getName().compareTo(o.getName());
		if (result != 0) {
			return result;
		}
		return this.getFixedLengthValue().compareTo(o.getFixedLengthValue());
	}

	@Override
	public String toString() {
		return value;
	}
}
