package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hometowns")
public class Hometown {

	@Id
	@Column(name = "code", nullable = false, columnDefinition = "bpchar")
	private String code;

	@Column(name = "name", nullable = false, length = 4)
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
